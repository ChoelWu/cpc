package com.joe.dao;

import com.joe.entity.AdminUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joe.pojo.AdminUserPOJO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author joe
 * @since 2019-11-17
 */
public interface AdminUserMapper extends BaseMapper<AdminUser> {
    AdminUserPOJO getAdminUser(Map<String, Object> conditions);

    List<AdminUserPOJO> listAdminUser(Map<String, Object> conditions);

    Integer countAdminUser(Map<String, Object> conditions);
}
