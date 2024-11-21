package com.taotu.hv.service;

import com.taotu.hv.common.result.PageResult;
import com.taotu.hv.model.dto.ProductDTO;
import com.taotu.hv.model.entity.ProductCategory;
import com.taotu.hv.model.param.ProductQueryParam;
import com.taotu.hv.model.vo.ProductVO;
import java.util.List;

public interface ProductService {
    // 获取分类树
    List<ProductCategory> getCategoryTree();

    // 创建分类
    Long createCategory(ProductCategory category);

    // 分页查询产品列表
    PageResult<ProductVO> getProductList(ProductQueryParam param);

    // 获取产品详情
    ProductVO getProductDetail(Long id);

    // 创建产品
    Long createProduct(ProductDTO productDTO);

    // 更新产品
    void updateProduct(Long id, ProductDTO productDTO);

    // 删除产品
    void deleteProduct(Long id);
}