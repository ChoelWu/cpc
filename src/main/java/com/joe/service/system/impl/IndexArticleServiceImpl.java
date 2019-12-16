package com.joe.service.system.impl;

import com.google.common.collect.Maps;
import com.joe.entity.IndexArticle;
import com.joe.dao.IndexArticleMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.joe.pojo.Article;
import com.joe.service.system.IndexArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author joe
 * @since 2019-11-25
 */
@Service
public class IndexArticleServiceImpl extends ServiceImpl<IndexArticleMapper, IndexArticle> implements IndexArticleService {
    @Resource
    private IndexArticleMapper indexArticleMapper;
    
    /**
     * 根据条件查询字典数据条数（联表查菜单）
     *
     * @param conditions 查询条件
     * @return 返回查询结果
     */
    public int countArticle(Map<String, Object> conditions) {
        if(null == conditions || conditions.isEmpty()) {
            conditions = Maps.newHashMap();
            conditions.put("1", "2");
        }
        return indexArticleMapper.countArticle(conditions);
    }

    /**
     * 根据条件查询字典数据（联表查菜单）
     *
     * @param conditions 查询条件
     * @param pageInfo   分页信息
     * @return 返回查询结果
     */
    public List<Article> listArticle(Map<String, Object> conditions, Map<String, Integer> pageInfo) {
        if(null == conditions || conditions.isEmpty()) {
            conditions = Maps.newHashMap();
            conditions.put("1", "2");
        }
        conditions.putAll(pageInfo);

        return indexArticleMapper.listArticle(conditions);
    }
}
