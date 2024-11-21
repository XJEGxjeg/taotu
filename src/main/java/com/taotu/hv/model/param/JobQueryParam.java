package com.taotu.hv.model.param;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class JobQueryParam {
    @Min(value = 1, message = "页码必须大于0")
    private Long page = 1L;

    @Min(value = 1, message = "每页条数必须大于0")
    private Long pageSize = 10L;

    private Long typeId;  // 职位类型ID(1:社会招聘 2:校园招聘)
    private String keyword; // 关键词搜索(职位名称)
    private String department; // 部门筛选
    private String location; // 地点筛选
    private String experience; // 经验要求
    private String education; // 学历要求
}
