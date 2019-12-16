DROP TABLE IF EXISTS `cpc_admin_auth`;
CREATE TABLE `cpc_admin_auth`
(
    `auth_id`         bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '权限ID',
    `auth_no`         char(24)    NOT NULL COMMENT '权限编号 AANO+yyyymmddhhmmss+1位随机大写字母+5位随机数字',
    `auth_name`       varchar(50) NOT NULL COMMENT '权限名称',
    `auth_permission` varchar(50) NOT NULL COMMENT '权限 shiro',
    `menu_no`         char(24)    NOT NULL COMMENT '所属菜单',
    `bak`             varchar(50) COMMENT '备用字段',
    `add_time`        datetime COMMENT '添加时间',
    `add_user_no`     varchar(24) COMMENT '添加人',
    `update_time`     datetime COMMENT '更新时间',
    `update_user_no`  varchar(24) COMMENT '更新人',
    PRIMARY KEY (`auth_id`),
    UNIQUE INDEX `idx_admin_auth_no` (`auth_no`),
    UNIQUE INDEX `idx_admin_auth_permission` (`auth_permission`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;