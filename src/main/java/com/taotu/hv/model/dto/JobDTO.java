package com.taotu.hv.model.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class JobDTO {
    @NotNull(message = "职位类型不能为空")
    private Long typeId;

    @NotBlank(message = "职位名称不能为空")
    private String title;

    @NotBlank(message = "所属部门不能为空")
    private String department;

    @NotBlank(message = "工作地点不能为空")
    private String location;

    private String salaryRange;
    private String experience;
    private String education;
    private String description;
    private String requirements;
    private String benefits;
    private Integer status;
}