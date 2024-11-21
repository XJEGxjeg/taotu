package com.taotu.hv.controller;

import com.taotu.hv.common.result.ApiResponse;
import com.taotu.hv.common.result.PageResult;
import com.taotu.hv.model.dto.ProductDTO;
import com.taotu.hv.model.entity.ProductCategory;
import com.taotu.hv.model.param.ProductQueryParam;
import com.taotu.hv.model.vo.ProductVO;
import com.taotu.hv.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/categories")
    public ApiResponse<List<ProductCategory>> getCategories() {
        return ApiResponse.success(productService.getCategoryTree());
    }

    @GetMapping("/list")
    public ApiResponse<PageResult<ProductVO>> getProductList(@Valid ProductQueryParam param) {
        return ApiResponse.success(productService.getProductList(param));
    }

    @GetMapping("/category/{categoryId}")
    public ApiResponse<PageResult<ProductVO>> getProductsByCategory(
            @PathVariable Long categoryId,
            @Valid ProductQueryParam param) {
        param.setCategoryId(categoryId);
        return ApiResponse.success(productService.getProductList(param));
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductVO> getProductDetail(@PathVariable Long id) {
        return ApiResponse.success(productService.getProductDetail(id));
    }

    @PostMapping("/categories")
    public ApiResponse<Long> createCategory(@RequestBody @Valid ProductCategory category) {
        return ApiResponse.success(productService.createCategory(category));
    }

    @PostMapping
    public ApiResponse<Long> createProduct(@RequestBody @Valid ProductDTO productDTO) {
        return ApiResponse.success(productService.createProduct(productDTO));
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) {
        productService.updateProduct(id, productDTO);
        return ApiResponse.success(null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ApiResponse.success(null);
    }
}