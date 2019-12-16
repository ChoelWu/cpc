package com.joe.service.system;

import com.joe.entity.AdminAuth;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joe.pojo.Auth;

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
public interface AdminAuthService extends IService<AdminAuth> {
    public int countAuth(Map<String, Object> conditions);

    public List<Auth> listAuth(Map<String, Object> conditions, Map<String, Integer> pageInfo);
}
