package com.joe.service.system.impl;

import com.joe.entity.IndexUser;
import com.joe.dao.IndexUserMapper;
import com.joe.service.system.IndexUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author joe
 * @since 2019-11-25
 */
@Service
public class IndexUserServiceImpl extends ServiceImpl<IndexUserMapper, IndexUser> implements IndexUserService {

}
