DROP TABLE IF EXISTS `cpc_index_course_tag`;
CREATE TABLE `cpc_index_course_tag`
(
    `course_tag_id`   bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '课程标签ID',
    `course_tag_no`   char(25)    NOT NULL COMMENT '课程标签编号',
    `course_tag_name` varchar(50) NOT NULL COMMENT '课程标签名称',
    `bak`             varchar(50) COMMENT '备用字段',
    `add_time`        datetime COMMENT '添加时间',
    `add_user_no`     varchar(24) COMMENT '添加人',
    `update_time`     datetime COMMENT '更新时间',
    `update_user_no`  varchar(24) COMMENT '更新人',
    PRIMARY KEY (`course_tag_id`),
    UNIQUE INDEX `idx_index_course_tag_no` (`course_tag_no`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;