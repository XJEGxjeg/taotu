package com.taotu.hv.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.taotu.hv.model.entity.News;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface NewsMapper extends BaseMapper<News> {
    @Select("SELECT DISTINCT category FROM t_news WHERE is_deleted = 0")
    List<String> selectCategories();
}
