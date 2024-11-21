package com.taotu.hv.service;

import com.taotu.hv.common.result.PageResult;
import com.taotu.hv.model.param.NewsQueryParam;
import com.taotu.hv.model.vo.NewsVO;
import com.taotu.hv.model.dto.NewsDTO;

import java.util.List;

public interface NewsService {
    PageResult<NewsVO> getNewsList(NewsQueryParam param);
    List<String> getCategories();
    NewsVO getNewsDetail(Long id);

    // 创建新闻
    Long createNews(NewsDTO newsDTO);

    // 更新新闻
    void updateNews(Long id, NewsDTO newsDTO);

    // 删除新闻
    void deleteNews(Long id);
}