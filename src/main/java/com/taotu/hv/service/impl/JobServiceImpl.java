package com.taotu.hv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotu.hv.common.exception.BusinessException;
import com.taotu.hv.common.result.PageResult;
import com.taotu.hv.common.result.ResultCode;
import com.taotu.hv.mapper.JobMapper;
import com.taotu.hv.mapper.JobTypeMapper;
import com.taotu.hv.model.dto.JobDTO;
import com.taotu.hv.model.entity.Job;
import com.taotu.hv.model.entity.JobType;
import com.taotu.hv.model.param.JobQueryParam;
import com.taotu.hv.model.vo.JobVO;
import com.taotu.hv.service.JobService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class JobServiceImpl implements JobService {
    @Autowired
    private JobMapper jobMapper;

    @Autowired
    private JobTypeMapper jobTypeMapper;

    @Override
    public List<JobType> getJobTypes() {
        return jobTypeMapper.selectList(null);
    }

    @Override
    public PageResult<JobVO> getJobList(JobQueryParam param) {
        Page<Job> page = new Page<>(param.getPage(), param.getPageSize());

        LambdaQueryWrapper<Job> wrapper = new LambdaQueryWrapper<Job>()
                .eq(param.getTypeId() != null, Job::getTypeId, param.getTypeId())
                .like(StrUtil.isNotBlank(param.getKeyword()), Job::getTitle, param.getKeyword())
                .eq(Job::getStatus, 1)
                .orderByDesc(Job::getCreateTime);

        Page<Job> jobPage = jobMapper.selectPage(page, wrapper);

        List<JobVO> voList = jobPage.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());

        Page<JobVO> voPage = new Page<>();
        BeanUtils.copyProperties(jobPage, voPage, "records");
        voPage.setRecords(voList);

        return PageResult.build(voPage);
    }

    @Override
    public JobVO getJobDetail(Long id) {
        Job job = jobMapper.selectJobWithType(id);
        if (job == null) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }
        return convertToVO(job);
    }

    @Override
    public Long createJob(JobDTO jobDTO) {
        Job job = new Job();
        BeanUtils.copyProperties(jobDTO, job);
        jobMapper.insert(job);
        return job.getId();
    }

    @Override
    public void updateJob(Long id, JobDTO jobDTO) {
        Job job = jobMapper.selectById(id);
        if (job == null) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }
        BeanUtils.copyProperties(jobDTO, job);
        jobMapper.updateById(job);
    }

    @Override
    public void deleteJob(Long id) {
        jobMapper.deleteById(id);
    }

    private JobVO convertToVO(Job job) {
        JobVO vo = new JobVO();
        BeanUtils.copyProperties(job, vo);
        return vo;
    }
}
