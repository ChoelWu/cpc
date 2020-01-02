DROP TABLE IF EXISTS `cpc_admin_dict`;
CREATE TABLE `cpc_admin_dict`
(
    `dict_id`        bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '字典ID',
    `dict_no`        char(24)    NOT NULL COMMENT '字典编号 ADNO+yyyymmddhhmmss+1位随机大写字母+5位随机数字',
    `dict_name`      varchar(50) NOT NULL COMMENT '字典名称',
    `dict_key`       varchar(20) NOT NULL COMMENT '字典键名',
    `dict_value`     varchar(20) NOT NULL COMMENT '字典键值',
    `dict_index`     char(4)     NOT NULL COMMENT '字典排序',
    `menu_no`        char(24)    NOT NULL COMMENT '所属菜单',
    `bak`            varchar(50) COMMENT '备用字段',
    `add_time`       datetime COMMENT '添加时间',
    `add_user_no`    varchar(24) COMMENT '添加人',
    `update_time`    datetime COMMENT '更新时间',
    `update_user_no` varchar(24) COMMENT '更新人',
    PRIMARY KEY (`dict_id`),
    UNIQUE INDEX  `idx_admin_dict_no` (`dict_no`),
    KEY `key_admin_dict_key_value` (`dict_key`, `dict_value`),
    KEY `key_menu_no` (`menu_no`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;