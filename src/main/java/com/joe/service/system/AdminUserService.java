package com.joe.service.system;

import com.joe.entity.AdminUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joe.pojo.AdminUserPOJO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author joe
 * @since 2019-11-17
 */
public interface AdminUserService extends IService<AdminUser> {
    AdminUserPOJO getAdminUser(Map<String, Object> conditions);

    int countAdminUser(Map<String, Object> conditions);

    List<AdminUserPOJO> listAdminUser(Map<String, Object> conditions, Map<String, Integer> pageInfo);
}
