DROP TABLE IF EXISTS `cpc_index_learn_log`;
CREATE TABLE `cpc_index_learn_log`
(
    `learn_log_id`     bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '日志ID',
    `learn_log_no`     char(24)    NOT NULL COMMENT '日志编号',
    `course_no`      char(24)    NOT NULL COMMENT '课程编号',
    `chapter_no`      char(24)    NOT NULL COMMENT '章节编号',
    `lesson_no`      char(24)    NOT NULL COMMENT '课时编号',
    `user_no`      char(24)    NOT NULL COMMENT '用户编号',
    `bak`            varchar(50) COMMENT '备用字段',
    `add_time`       datetime COMMENT '添加时间',
    `add_user_no`    varchar(24) COMMENT '添加人',
    `update_time`    datetime COMMENT '更新时间',
    `update_user_no` varchar(24) COMMENT '更新人',
    PRIMARY KEY (`learn_log_id`),
    UNIQUE INDEX `idx_index_learn_log_no` (`learn_log_no`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;