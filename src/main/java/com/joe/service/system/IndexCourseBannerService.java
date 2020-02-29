package com.joe.service.system;

import com.joe.entity.IndexCourseBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author joe
 * @since 2020-02-24
 */
public interface IndexCourseBannerService extends IService<IndexCourseBanner> {
    int countCourseBanner(Map<String, Object> conditions);

    List<IndexCourseBanner> listCourseBanner(Map<String, Object> conditions, Map<String, Integer> pageInfo);
}
