package com.joe.service.system;

import com.joe.entity.IndexCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joe.pojo.Course;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author joe
 * @since 2019-12-02
 */
public interface IndexCourseService extends IService<IndexCourse> {
    int countCourse(Map<String, Object> conditions);

    List<Course> listCourse(Map<String, Object> conditions, Map<String, Integer> pageInfo);
}
