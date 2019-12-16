DROP TABLE IF EXISTS `cpc_admin_menu`;
CREATE TABLE `cpc_admin_menu`
(
    `menu_id`        bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `menu_no`        char(24)     NOT NULL COMMENT '菜单编号 AMNO+yyyymmddhhmmss+1位随机大写字母+5位随机数字',
    `menu_name`      varchar(50)  NOT NULL COMMENT '菜单名称',
    `menu_level`     char(1)      NOT NULL COMMENT '菜单级别 1-父级菜单 2-子级菜单',
    `menu_url`       varchar(100) NOT NULL COMMENT '菜单链接地址',
    `menu_index`     char(4)      NOT NULL COMMENT '菜单编号',
    `menu_icon`      varchar(20)  NOT NULL COMMENT '菜单图标',
    `parent_menu_no` varchar(24) COMMENT '父级菜单',
    `menu_status`    char(1)      NOT NULL COMMENT '菜单状态 0-禁用 1-启用',
    `bak`            varchar(50) COMMENT '备用字段',
    `add_time`       datetime COMMENT '添加时间',
    `add_user_no`    varchar(24) COMMENT '添加人',
    `update_time`    datetime COMMENT '更新时间',
    `update_user_no` varchar(24) COMMENT '更新人',
    PRIMARY KEY (`menu_id`),
    UNIQUE INDEX `idx_admin_menu_no` (`menu_no`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;