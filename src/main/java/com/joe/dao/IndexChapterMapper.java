package com.joe.dao;

import com.joe.entity.IndexChapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joe.pojo.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author joe
 * @since 2019-12-03
 */
public interface IndexChapterMapper extends BaseMapper<IndexChapter> {
    List<Chapter> listChapter(@Param("courseNo")String courseNo);
}
