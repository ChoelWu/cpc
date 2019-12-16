DROP TABLE IF EXISTS `cpc_admin_role`;
CREATE TABLE `cpc_admin_role`
(
    `role_id`        bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_no`        char(24)    NOT NULL COMMENT '角色编号 ARNO+yyyymmddhhmmss+1位随机大写字母+5位随机数字',
    `role_name`      varchar(50) NOT NULL COMMENT '角色名称',
    `role_auth`      varchar(512) COMMENT '拥有权限编号',
    `role_status`    char(1)     NOT NULL COMMENT '角色状态 0-禁用 1-启用',
    `bak`            varchar(50) COMMENT '备用字段',
    `add_time`       datetime COMMENT '添加时间',
    `add_user_no`    varchar(24) COMMENT '添加人',
    `update_time`    datetime COMMENT '更新时间',
    `update_user_no` varchar(24) COMMENT '更新人',
    PRIMARY KEY (`role_id`),
    UNIQUE INDEX `idx_admin_role_no` (`role_no`),
    UNIQUE INDEX `idx_admin_role_name` (`role_name`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;