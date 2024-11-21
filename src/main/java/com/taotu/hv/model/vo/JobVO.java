package com.taotu.hv.model.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class JobVO {
    private Long id;
    private Long typeId;
    private String typeName;
    private String title;
    private String department;
    private String location;
    private String salaryRange;
    private String experience;
    private String education;
    private String description;
    private String requirements;
    private String benefits;
    private Integer status;
    private LocalDateTime createTime;
}