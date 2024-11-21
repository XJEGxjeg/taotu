package com.taotu.hv.service;

import  com.taotu.hv.common.result.PageResult;
import  com.taotu.hv.model.dto.JobDTO;
import  com.taotu.hv.model.entity.JobType;
import  com.taotu.hv.model.param.JobQueryParam;
import  com.taotu.hv.model.vo.JobVO;
import java.util.List;

public interface JobService {
    // 获取职位类型列表
    List<JobType> getJobTypes();

    // 分页查询职位列表
    PageResult<JobVO> getJobList(JobQueryParam param);

    // 获取职位详情
    JobVO getJobDetail(Long id);

    // 创建职位
    Long createJob(JobDTO jobDTO);

    // 更新职位
    void updateJob(Long id, JobDTO jobDTO);

    // 删除职位
    void deleteJob(Long id);
}