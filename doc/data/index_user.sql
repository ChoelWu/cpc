DROP TABLE IF EXISTS `cpc_index_user`;
CREATE TABLE `cpc_index_user`
(
    `user_id`             bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `user_no`             char(24)    NOT NULL COMMENT '用户编号 用户编号 IUNO+yyyymmddhhmmss+1位随机大写字母+5位随机数字',
    `user_name`           varchar(50) NOT NULL COMMENT '用户名称',
    `user_account`        varchar(50) NOT NULL COMMENT '用户帐号',
    `user_password`       varchar(32) NOT NULL COMMENT '用户密码',
    `user_mobile`         char(11) COMMENT '用户电话',
    `user_email`          varchar(50) COMMENT '用户电子邮件',
    `user_head_sculpture` varchar(100) COMMENT '用户头像',
    `user_type`           char(1)     NOT NULL COMMENT '用户类型 1-校内学生 2-校外学生 3-老师',
    `user_status`         char(1)     NOT NULL COMMENT '用户状态 0-禁止使用 1-等待激活 2-正常使用',
    `bak`                 varchar(50) COMMENT '备用字段',
    `add_time`            datetime COMMENT '添加时间',
    `add_user_no`         varchar(24) COMMENT '添加人',
    `update_time`         datetime COMMENT '更新时间',
    `update_user_no`      varchar(24) COMMENT '更新人',
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `idx_index_user_no` (`user_no`),
    UNIQUE INDEX `idx_index_user_account` (`user_account`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;