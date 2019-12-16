package com.joe.dao;

import com.joe.entity.IndexCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joe.pojo.Course;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author joe
 * @since 2019-12-02
 */
public interface IndexCourseMapper extends BaseMapper<IndexCourse> {
    List<Course> listCourse(Map<String, Object> conditions);

    Integer countCourse(Map<String, Object> conditions);
}
