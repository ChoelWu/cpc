package com.joe.service.system;

import com.joe.entity.AdminDict;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joe.pojo.Dict;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author joe
 * @since 2019-11-17
 */
public interface AdminDictService extends IService<AdminDict> {
    String getDictValueByKey(String dictName, String dictKey);

    List<AdminDict> getDictListByDictName(String dictName);

    int countDict(Map<String, Object> conditions);

    List<Dict> listDict(Map<String, Object> conditions, Map<String, Integer> pageInfo);
}
