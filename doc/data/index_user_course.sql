DROP TABLE IF EXISTS `cpc_index_user_course`;
CREATE TABLE `cpc_index_user_course`
(
    `user_course_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户课程ID',
    `user_course_no` char(25)   NOT NULL COMMENT '用户课程 用户课程 IUCNO+yyyymmddhhmmss+1位随机大写字母+5位随机数字',
    `user_no`        char(24)   NOT NULL COMMENT '用户编号',
    `course_no`      char(24)   NOT NULL COMMENT '课程编号',
    `last_time`      datetime   NOT NULL COMMENT '最后学习时间',
    `bak`            varchar(50) COMMENT '备用字段',
    `add_time`       datetime COMMENT '添加时间',
    `add_user_no`    varchar(24) COMMENT '添加人',
    `update_time`    datetime COMMENT '更新时间',
    `update_user_no` varchar(24) COMMENT '更新人',
    PRIMARY KEY (`user_course_id`),
    UNIQUE INDEX `idx_index_user_course_no` (`user_course_no`),
    UNIQUE INDEX `idx_index_user_no` (`user_no`),
    UNIQUE INDEX `idx_index_course_no` (`course_no`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;