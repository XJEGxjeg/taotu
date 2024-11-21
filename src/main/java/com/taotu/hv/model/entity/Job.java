package com.taotu.hv.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("t_job")
public class Job {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long typeId;
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

    @TableField(exist = false)
    private String typeName;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Boolean isDeleted;
}