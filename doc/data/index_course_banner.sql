DROP TABLE IF EXISTS `cpc_index_course_banner`;
CREATE TABLE `cpc_index_course_banner`
(
    `course_banner_id`        bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '推荐位ID',
    `course_banner_no`        char(24)    NOT NULL COMMENT '推荐位编号 ICNO+yyyymmddhhmmss+1位随机大写字母+5位随机数字',
    `course_banner_name`      varchar(50) NOT NULL COMMENT '推荐位名称',
    `course_banner_url`       varchar(100) COMMENT '推荐位链接地址(超链接时)',
    `course_banner_index`     char(4)     NOT NULL COMMENT '推荐位编号',
    `course_banner_intro`     varchar(512) COMMENT '推荐位简介',
    `course_banner_status`    char(1)     NOT NULL COMMENT '推荐位状态 0-禁用 1-启用',
    `course_banner_pic`       varchar(100) COMMENT '推荐位封面',
    `bak`               varchar(50) COMMENT '备用字段',
    `add_time`          datetime COMMENT '添加时间',
    `add_user_no`       varchar(24) COMMENT '添加人',
    `update_time`       datetime COMMENT '更新时间',
    `update_user_no`    varchar(24) COMMENT '更新人',
    PRIMARY KEY (`course_banner_id`),
    UNIQUE INDEX `idx_index_channel_no` (`course_banner_no`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;