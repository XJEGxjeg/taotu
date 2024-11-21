package com.taotu.hv.controller;

import com.taotu.hv.common.result.ApiResponse;
import com.taotu.hv.common.result.PageResult;
import com.taotu.hv.model.dto.JobDTO;
import com.taotu.hv.model.entity.JobType;
import com.taotu.hv.model.param.JobQueryParam;
import com.taotu.hv.model.vo.JobVO;
import com.taotu.hv.service.JobService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/jobs")
@Validated
public class JobController {
    @Autowired
    private JobService jobService;

    @GetMapping("/types")
    public ApiResponse<List<JobType>> getJobTypes() {
        System.out.println("正在调用http://localhost:8099/jobs/types");
        return ApiResponse.success(jobService.getJobTypes());
    }

    @GetMapping("/list")
    public ApiResponse<PageResult<JobVO>> getJobList(@Valid JobQueryParam param) {
        return ApiResponse.success(jobService.getJobList(param));
    }

    @GetMapping("/social")
    public ApiResponse<PageResult<JobVO>> getSocialJobs(@Valid JobQueryParam param) {
        param.setTypeId(1L);  // 社会招聘
        return ApiResponse.success(jobService.getJobList(param));
    }

    @GetMapping("/campus")
    public ApiResponse<PageResult<JobVO>> getCampusJobs(@Valid JobQueryParam param) {
        param.setTypeId(2L);  // 校园招聘
        return ApiResponse.success(jobService.getJobList(param));
    }

    @GetMapping("/{id}")
    public ApiResponse<JobVO> getJobDetail(@PathVariable Long id) {
        return ApiResponse.success(jobService.getJobDetail(id));
    }

    @PostMapping
    public ApiResponse<Long> createJob(@RequestBody @Valid JobDTO jobDTO) {
        return ApiResponse.success(jobService.createJob(jobDTO));
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateJob(@PathVariable Long id, @RequestBody @Valid JobDTO jobDTO) {
        jobService.updateJob(id, jobDTO);
        return ApiResponse.success(null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ApiResponse.success(null);
    }
}