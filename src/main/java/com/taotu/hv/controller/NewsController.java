package com.taotu.hv.controller;

import com.taotu.hv.common.result.ApiResponse;
import com.taotu.hv.common.result.PageResult;
import com.taotu.hv.model.dto.NewsDTO;
import com.taotu.hv.model.param.NewsQueryParam;
import com.taotu.hv.model.vo.NewsVO;
import com.taotu.hv.service.NewsService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/news")
@Validated
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/list")
    public ApiResponse<PageResult<NewsVO>> getNewsList(@Valid NewsQueryParam param) {
        log.info("获取新闻列表，参数：{}", param);
        return ApiResponse.success(newsService.getNewsList(param));
    }

    @GetMapping("/categories")
    public ApiResponse<List<String>> getCategories() {
        return ApiResponse.success(newsService.getCategories());
    }

    @GetMapping("/{id}")
    public ApiResponse<NewsVO> getNewsDetail(@PathVariable Long id) {
        return ApiResponse.success(newsService.getNewsDetail(id));
    }

    @PostMapping
    public ApiResponse<Long> createNews(@RequestBody @Valid NewsDTO newsDTO) {
        return ApiResponse.success(newsService.createNews(newsDTO));
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateNews(@PathVariable Long id,
                                        @RequestBody @Valid NewsDTO newsDTO) {
        newsService.updateNews(id, newsDTO);
        return ApiResponse.success(null);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return ApiResponse.success(null);
    }
}