package com.taotu.hv.model.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
public class ProductDTO {
    @NotNull(message = "产品分类不能为空")
    private Long categoryId;

    @NotBlank(message = "产品名称不能为空")
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
}