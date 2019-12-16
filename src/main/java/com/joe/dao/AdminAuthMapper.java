package com.joe.dao;

import com.joe.entity.AdminAuth;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joe.pojo.Auth;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author joe
 * @since 2019-11-17
 */
public interface AdminAuthMapper extends BaseMapper<AdminAuth> {
    List<Auth> listAuth(Map<String, Object> conditions);

    Integer countAuth(Map<String, Object> conditions);
}
