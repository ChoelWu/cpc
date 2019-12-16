package com.joe.service.system;

import com.joe.entity.AdminMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.joe.entity.IndexArticle;
import com.joe.entity.IndexChannel;
import com.joe.entity.IndexUser;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author joe
 * @since 2019-11-17
 */
public interface AdminMenuService extends IService<AdminMenu> {

    /**
     * <p>
     *  服务类
     * </p>
     *
     * @author joe
     * @since 2019-11-25
     */
    interface IndexArticleService extends IService<IndexArticle> {

    }

    /**
     * <p>
     *  服务类
     * </p>
     *
     * @author joe
     * @since 2019-11-25
     */
    interface IndexChannelService extends IService<IndexChannel> {

    }

    /**
     * <p>
     *  服务类
     * </p>
     *
     * @author joe
     * @since 2019-11-25
     */
    interface IndexUserService extends IService<IndexUser> {

    }
}
