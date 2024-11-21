package com.taotu.hv.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.taotu.hv.common.exception.BusinessException;
import com.taotu.hv.common.result.PageResult;
import com.taotu.hv.common.result.ResultCode;
import com.taotu.hv.mapper.NewsMapper;
import com.taotu.hv.model.entity.News;
import com.taotu.hv.model.dto.NewsDTO;
import com.taotu.hv.model.param.NewsQueryParam;
import com.taotu.hv.model.vo.NewsVO;
import com.taotu.hv.service.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 新闻服务实现类
 * 处理新闻的增删改查等业务逻辑
 *
 * @author taotu
 * @version 1.0
 * @date 2024/03/15
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)  // 开启事务管理，任何异常都会回滚
public class NewsServiceImpl implements NewsService {

    /**
     * 新闻数据访问接口
     */
    @Autowired
    private NewsMapper newsMapper;

    /**
     * 分页查询新闻列表
     * 支持按分类、关键词、日期范围进行筛选
     *
     * @param param 查询参数对象，包含分页信息和筛选条件
     * @return 分页结果，包含新闻列表和分页信息
     */
    @Override
    public PageResult<NewsVO> getNewsList(NewsQueryParam param) {
        // 构建分页对象
        Page<News> page = new Page<>(param.getPage(), param.getPageSize());

        // 构建查询条件
        LambdaQueryWrapper<News> wrapper = Wrappers.lambdaQuery(News.class)
                // 如果分类不为空，则按分类筛选
                .eq(StrUtil.isNotBlank(param.getCategory()), News::getCategory, param.getCategory())
                // 如果关键词不为空，则按标题模糊查询
                .like(StrUtil.isNotBlank(param.getKeyword()), News::getTitle, param.getKeyword())
                // 如果开始日期不为空，则筛选大于等于该日期的新闻
                .ge(param.getStartDate() != null, News::getPublishDate, param.getStartDate())
                // 如果结束日期不为空，则筛选小于等于该日期的新闻
                .le(param.getEndDate() != null, News::getPublishDate, param.getEndDate())
                // 按发布日期降序排序
                .orderByDesc(News::getPublishDate);

        // 执行分页查询
        Page<News> newsPage = newsMapper.selectPage(page, wrapper);

        // 将实体对象转换为视图对象
        Page<NewsVO> voPage = new Page<>();
        BeanUtil.copyProperties(newsPage, voPage, "records");
        voPage.setRecords(BeanUtil.copyToList(newsPage.getRecords(), NewsVO.class));

        return PageResult.build(voPage);
    }

    /**
     * 获取所有新闻分类
     *
     * @return 分类列表
     */
    @Override
    public List<String> getCategories() {
        return newsMapper.selectCategories();
    }

    /**
     * 根据ID获取新闻详情
     *
     * @param id 新闻ID
     * @return 新闻详情视图对象
     * @throws BusinessException 当新闻不存在时抛出异常
     */
    @Override
    public NewsVO getNewsDetail(Long id) {
        News news = newsMapper.selectById(id);
        if (news == null) {
            throw new BusinessException(ResultCode.NOT_FOUND);
        }
        return BeanUtil.copyProperties(news, NewsVO.class);
    }

    /**
     * 创建新闻
     *
     * @param newsDTO 新闻数据传输对象
     * @return 新创建的新闻ID
     */
    @Override
    public Long createNews(NewsDTO newsDTO) {
        News news = BeanUtil.copyProperties(newsDTO, News.class);
        newsMapper.insert(news);
        return news.getId();
    }

    /**
     * 更新新闻信息
     *
     * @param id 新闻ID
     * @param newsDTO 新闻更新数据
     * @throws BusinessException 当新闻不存在时抛出异常
     */
    @Override
    public void updateNews(Long id, NewsDTO newsDTO) {
        // 检查新闻是否存在
        News existingNews = newsMapper.selectById(id);
        if (existingNews == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "新闻不存在");
        }

        News news = BeanUtil.copyProperties(newsDTO, News.class);
        news.setId(id);
        newsMapper.updateById(news);
    }

    /**
     * 删除新闻
     *
     * @param id 新闻ID
     * @throws BusinessException 当新闻不存在时抛出异常
     */
    @Override
    public void deleteNews(Long id) {
        // 检查新闻是否存在
        News existingNews = newsMapper.selectById(id);
        if (existingNews == null) {
            throw new BusinessException(ResultCode.NOT_FOUND, "新闻不存在");
        }

        newsMapper.deleteById(id);
    }
}