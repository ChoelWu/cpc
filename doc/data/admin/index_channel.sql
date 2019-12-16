DROP TABLE IF EXISTS `cpc_index_channel`;
CREATE TABLE `cpc_index_channel`
(
    `channel_id`        bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '栏目ID',
    `channel_no`        char(24)    NOT NULL COMMENT '栏目编号 ICNO+yyyymmddhhmmss+1位随机大写字母+5位随机数字',
    `channel_name`      varchar(50) NOT NULL COMMENT '栏目名称',
    `channel_level`     char(1)     NOT NULL COMMENT '栏目级别 1-父级栏目 2-子级栏目',
    `channel_type`      char(1)     NOT NULL COMMENT '栏目类型 1-列表页 2-文章页 3-超链接',
    `channel_url`       varchar(100) COMMENT '栏目链接地址(超链接时)',
    `channel_index`     char(4)     NOT NULL COMMENT '栏目编号',
    `channel_intro`     varchar(512) COMMENT '栏目简介',
    `parent_channel_no` varchar(40) COMMENT '父级栏目',
    `is_nav`            char(1)     NOT NULL COMMENT '是否是导航 0-否 1-是',
    `channel_status`    char(1)     NOT NULL COMMENT '栏目状态 0-禁用 1-启用',
    `channel_pic`       varchar(100) COMMENT '栏目图',
    `bak`               varchar(50) COMMENT '备用字段',
    `add_time`          datetime COMMENT '添加时间',
    `add_user_no`       varchar(24) COMMENT '添加人',
    `update_time`       datetime COMMENT '更新时间',
    `update_user_no`    varchar(24) COMMENT '更新人',
    PRIMARY KEY (`channel_id`),
    UNIQUE INDEX `idx_index_channel_no` (`channel_no`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;