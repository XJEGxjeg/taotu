package com.taotu.hv.model.vo;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
public class ProductVO {
    private Long id;
    private Long categoryId;
    private String categoryName;
    private String name;
    private String model;
    private String description;
    private String features;
    private String specifications;
    private Map<String, Object> parameters;
    private List<String> imageUrls;
    private String videoUrl;
    private Integer sortOrder;
    private Integer status;
    private LocalDateTime createTime;
}
