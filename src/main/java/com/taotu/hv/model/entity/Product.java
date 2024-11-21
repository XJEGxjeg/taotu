package com.taotu.hv.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@TableName("t_product")
public class Product {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long categoryId;
    private String name;
    private String model;
    private String description;
    private String features;
    private String specifications;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> parameters;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> imageUrls;

    private String videoUrl;
    private Integer sortOrder;
    private Integer status;

    @TableField(exist = false)
    private String categoryName;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableLogic
    private Boolean isDeleted;
}