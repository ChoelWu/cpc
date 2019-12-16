package com.joe.dao;

import com.joe.entity.AdminDict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joe.pojo.Dict;

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
public interface AdminDictMapper extends BaseMapper<AdminDict> {
    List<Dict> listDict(Map<String, Object> conditions);

    Integer countDict(Map<String, Object> conditions);
}
