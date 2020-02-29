package com.joe.dao;

import com.joe.entity.IndexCourseBanner;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author joe
 * @since 2020-02-24
 */
public interface IndexCourseBannerMapper extends BaseMapper<IndexCourseBanner> {
    List<IndexCourseBanner> listCourseBanner(Map<String, Object> conditions);

    Integer countCourseBanner(Map<String, Object> conditions);
}
