package com.joe.service.system;

import com.baomidou.mybatisplus.extension.service.IService;
import com.joe.entity.IndexArticle;
import com.joe.pojo.Article;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author joe
 * @since 2019-11-17
 */
public interface IndexArticleService extends IService<IndexArticle> {
    int countArticle(Map<String, Object> conditions);

    List<Article> listArticle(Map<String, Object> conditions, Map<String, Integer> pageInfo);
}
