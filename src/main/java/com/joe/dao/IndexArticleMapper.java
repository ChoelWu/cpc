package com.joe.dao;

import com.joe.entity.IndexArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joe.pojo.Article;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author joe
 * @since 2019-11-25
 */
public interface IndexArticleMapper extends BaseMapper<IndexArticle> {
    List<Article> listArticle(Map<String, Object> conditions);

    Integer countArticle(Map<String, Object> conditions);
}
