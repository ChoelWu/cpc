package com.joe.service.system.impl;

import com.google.common.collect.Maps;
import com.joe.entity.AdminUser;
import com.joe.dao.AdminUserMapper;
import com.joe.pojo.AdminUserPOJO;
import com.joe.service.system.AdminUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author joe
 * @since 2019-11-17
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {
    @Resource
    private AdminUserMapper adminUserMapper;

    /**
     * 获取用户信息
     *
     * @param conditions 查询条件
     * @return 返回结果
     */
    public AdminUserPOJO getAdminUser(Map<String, Object> conditions) {
        return adminUserMapper.getAdminUser(conditions);
    }

    /**
     * 根据条件查询用户数据条数（联表查菜单）
     *
     * @param conditions 查询条件
     * @return 返回查询结果
     */
    public int countAdminUser(Map<String, Object> conditions) {
        if (null == conditions || conditions.isEmpty()) {
            conditions = Maps.newHashMap();
            conditions.put("1", "2");
        }
        return adminUserMapper.countAdminUser(conditions);
    }

    /**
     * 根据条件查询用户（联表查菜单）
     *
     * @param conditions 查询条件
     * @param pageInfo   分页信息
     * @return 返回查询结果
     */
    public List<AdminUserPOJO> listAdminUser(Map<String, Object> conditions, Map<String, Integer> pageInfo) {
        if (null == conditions || conditions.isEmpty()) {
            conditions = Maps.newHashMap();
            conditions.put("1", "2");
        }
        conditions.putAll(pageInfo);
        return adminUserMapper.listAdminUser(conditions);
    }
}
