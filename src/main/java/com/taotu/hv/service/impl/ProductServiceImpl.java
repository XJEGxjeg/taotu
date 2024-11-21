package com.taotu.hv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotu.hv.common.exception.BusinessException;
import com.taotu.hv.common.result.PageResult;
import com.taotu.hv.common.result.ResultCode;
import com.taotu.hv.mapper.ProductMapper;
import com.taotu.hv.mapper.ProductCategoryMapper;
import com.taotu.hv.model.dto.ProductDTO;
import com.taotu.hv.model.entity.Product;
import com.taotu.hv.model.entity.ProductCategory;
import com.taotu.hv.model.param.ProductQueryParam;
import com.taotu.hv.model.vo.ProductVO;
import com.taotu.hv.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.hutool.core.util.StrUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductCategoryMapper categoryMapper;

    @Override
    public List<ProductCategory> getCategoryTree() {
        // 获取所有分类
        List<ProductCategory> allCategories = categoryMapper.selectList(
                new LambdaQueryWrapper<ProductCategory>()
                        .orderByAsc(ProductCategory::getSortOrder)
        );

        // 构建分类树
        Map<Long, List<ProductCategory>> childrenMap = allCategories.stream()
                .filter(category -> category.getParentId() != 0)
                .collect(Collectors.groupingBy(ProductCategory::getParentId));

        allCategories.forEach(category ->
                category.setChildren(childrenMap.getOrDefault(category.getId(), List.of()))
        );

        return allCategories.stream()
                .filter(category -> category.getParentId() == 0)
                .collect(Collectors.toList());
    }

    @Override
    public Long createCategory(ProductCategory category) {
        categoryMapper.insert(category);
        return category.getId();
    }

    @Override
    public PageResult<ProductVO> getProductList(ProductQueryParam param) {
        Page<Product> page = new Page<>(param.getPage(), param.getPageSize());

        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<Product>()
                .eq(param.getCategoryId() != null, Product::getCategoryId, param.getCategoryId())
                .like(StrUtil.isNotBlank(param.getKeyword()), Product::getName, param.getKeyword())
                .eq(Product::getStatus, 1)
                .orderByAsc(Product::getSortOrder);

        Page<Product> productPage = productMapper.selectPage(page, wrapper);

        List<ProductVO> voList = productPage.getRecords().stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());

        Page<ProductVO> voPage = new Page<>();
        BeanUtils.copyProperties(productPage, voPage, "records");
        voPage.setRecords(voList);

        return PageResult.build(voPage);
    }

    @Override
    public ProductVO getProductDetail(Long id) {
        Product product = productMapper.selectProductWithCategory(id);
        if (product == null) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }
        return convertToVO(product);
    }

    @Override
    public Long createProduct(ProductDTO productDTO) {
        Product product = new Product();
        BeanUtils.copyProperties(productDTO, product);
        productMapper.insert(product);
        return product.getId();
    }

    @Override
    public void updateProduct(Long id, ProductDTO productDTO) {
        Product product = productMapper.selectById(id);
        if (product == null) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }
        BeanUtils.copyProperties(productDTO, product);
        productMapper.updateById(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productMapper.deleteById(id);
    }

    private ProductVO convertToVO(Product product) {
        ProductVO vo = new ProductVO();
        BeanUtils.copyProperties(product, vo);
        return vo;
    }
}