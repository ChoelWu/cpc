package com.joe.service.system.impl;

import com.google.common.collect.Maps;
import com.joe.entity.AdminAuth;
import com.joe.dao.AdminAuthMapper;
import com.joe.pojo.Auth;
import com.joe.service.system.AdminAuthService;
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
 * @since 2019-11-17
 */
@Service
public class AdminAuthServiceImpl extends ServiceImpl<AdminAuthMapper, AdminAuth> implements AdminAuthService {
    @Resource
    private AdminAuthMapper adminAuthMapper;

    /**
     * 根据条件查询字典数据条数（联表查菜单）
     *
     * @param conditions 查询条件
     * @return 返回查询结果
     */
    public int countAuth(Map<String, Object> conditions) {
        if(null == conditions || conditions.isEmpty()) {
            conditions = Maps.newHashMap();
            conditions.put("1", "2");
        }
        return adminAuthMapper.countAuth(conditions);
    }

    /**
     * 根据条件查询字典数据（联表查菜单）
     *
     * @param conditions 查询条件
     * @param pageInfo   分页信息
     * @return 返回查询结果
     */
    public List<Auth> listAuth(Map<String, Object> conditions, Map<String, Integer> pageInfo) {
        if(null == conditions || conditions.isEmpty()) {
            conditions = Maps.newHashMap();
            conditions.put("1", "2");
        }
        conditions.putAll(pageInfo);
        return adminAuthMapper.listAuth(conditions);
    }
}
