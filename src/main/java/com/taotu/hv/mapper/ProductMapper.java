package com.taotu.hv.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotu.hv.model.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    @Select("SELECT p.*, c.name as category_name FROM t_product p " +
            "LEFT JOIN t_product_category c ON p.category_id = c.id " +
            "WHERE p.id = #{id} AND p.is_deleted = 0")
    Product selectProductWithCategory(Long id);
}