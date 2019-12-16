package com.joe.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.joe.entity.AdminDict;
import com.joe.dao.AdminDictMapper;
import com.joe.pojo.Dict;
import com.joe.service.system.AdminDictService;
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
public class AdminDictServiceImpl extends ServiceImpl<AdminDictMapper, AdminDict> implements AdminDictService {
    @Resource
    private AdminDictMapper adminDictMapper;

    /**
     * 根据字典键名返回键值
     *
     * @param dictName 字典名称
     * @param dictKey  字典键名
     * @return 返回键值
     */
    public String getDictValueByKey(String dictName, String dictKey) {
        // 查询条件
        QueryWrapper<AdminDict> adminDictQueryWrapper = new QueryWrapper<>();
        adminDictQueryWrapper.eq("dict_name", dictName).eq("dict_key", dictKey);
        AdminDict adminDict = this.getOne(adminDictQueryWrapper);

        // 返回键值
        return adminDict.getDictValue();
    }

    /**
     * 根据字典名称获取字典列表
     *
     * @param dictName 字典名称
     * @return 但会字典列表
     */
    public List<AdminDict> getDictListByDictName(String dictName) {
        // 查询条件
        QueryWrapper<AdminDict> adminDictQueryWrapper = new QueryWrapper<>();
        adminDictQueryWrapper.eq("dict_name", dictName).orderByAsc("dict_index");

        // 返回字典列表
        return this.list(adminDictQueryWrapper);
    }

    /**
     * 根据条件查询字典数据条数（联表查菜单）
     *
     * @param conditions 查询条件
     * @return 返回查询结果
     */
    public int countDict(Map<String, Object> conditions) {
        if(null == conditions || conditions.isEmpty()) {
            conditions = Maps.newHashMap();
            conditions.put("1", "2");
        }
        return adminDictMapper.countDict(conditions);
    }

    /**
     * 根据条件查询字典数据（联表查菜单）
     *
     * @param conditions 查询条件
     * @param pageInfo   分页信息
     * @return 返回查询结果
     */
    public List<Dict> listDict(Map<String, Object> conditions, Map<String, Integer> pageInfo) {
        if(null == conditions || conditions.isEmpty()) {
            conditions = Maps.newHashMap();
            conditions.put("1", "2");
        }
        conditions.putAll(pageInfo);
        return adminDictMapper.listDict(conditions);
    }
}
