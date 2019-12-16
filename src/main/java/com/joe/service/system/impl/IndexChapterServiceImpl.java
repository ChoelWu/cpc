package com.joe.service.system.impl;

import com.google.common.collect.Maps;
import com.joe.entity.IndexChapter;
import com.joe.dao.IndexChapterMapper;
import com.joe.pojo.Chapter;
import com.joe.service.system.IndexChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2019-12-03
 */
@Service
public class IndexChapterServiceImpl extends ServiceImpl<IndexChapterMapper, IndexChapter> implements IndexChapterService {
    @Resource
    private IndexChapterMapper indexChapterMapper;

    /**
     * 根据条件查询字典数据（联表查菜单）
     *
     * @param courseNo   课程编号
     * @return 返回查询结果
     */
    public List<Chapter> listChapter(String courseNo) {
        return indexChapterMapper.listChapter(courseNo);
    }
}
