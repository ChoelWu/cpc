package com.joe.service.system.impl;

import com.google.common.collect.Maps;
import com.joe.dao.IndexCourseMapper;
import com.joe.entity.IndexCourse;
import com.joe.dao.IndexCourseMapper;
import com.joe.pojo.Course;
import com.joe.service.system.IndexCourseService;
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
 * @since 2019-12-02
 */
@Service
public class IndexCourseServiceImpl extends ServiceImpl<IndexCourseMapper, IndexCourse> implements IndexCourseService {
    @Resource
    private IndexCourseMapper indexCourseMapper;

    /**
     * 根据条件查询字典数据条数（联表查菜单）
     *
     * @param conditions 查询条件
     * @return 返回查询结果
     */
    public int countCourse(Map<String, Object> conditions) {
        if(null == conditions || conditions.isEmpty()) {
            conditions = Maps.newHashMap();
            conditions.put("1", "2");
        }
        return indexCourseMapper.countCourse(conditions);
    }

    /**
     * 根据条件查询字典数据（联表查菜单）
     *
     * @param conditions 查询条件
     * @param pageInfo   分页信息
     * @return 返回查询结果
     */
    public List<Course> listCourse(Map<String, Object> conditions, Map<String, Integer> pageInfo) {
        if(null == conditions || conditions.isEmpty()) {
            conditions = Maps.newHashMap();
            conditions.put("1", "2");
        }
        conditions.putAll(pageInfo);

        return indexCourseMapper.listCourse(conditions);
    }
}
