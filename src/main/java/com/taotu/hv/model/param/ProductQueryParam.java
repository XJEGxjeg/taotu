package com.taotu.hv.model.param;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class ProductQueryParam {
    @Min(value = 1, message = "页码必须大于0")
    private Long page = 1L;

    @Min(value = 1, message = "每页条数必须大于0")
    private Long pageSize = 10L;

    private Long categoryId;  // 产品分类ID
    private String keyword;   // 关键词搜索(产品名称)
    private String model;     // 产品型号
    private Integer status;   // 产品状态(0:下架 1:上架)
}
