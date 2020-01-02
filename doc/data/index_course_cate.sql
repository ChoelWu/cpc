DROP TABLE IF EXISTS `cpc_index_course_cate`;
CREATE TABLE `cpc_index_course_cate`
(
    `course_cate_id`        bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '课程分类ID',
    `course_cate_no`        char(25)     NOT NULL COMMENT '课程分类编号',
    `course_cate_name`      varchar(50)  NOT NULL COMMENT '课程分类名称',
    `course_cate_level`     char(1)      NOT NULL COMMENT '课程分类级别 1-父级课程分类 2-子级课程分类',
    `course_cate_index`     char(4)      NOT NULL COMMENT '课程分类编号',
    `parent_course_cate_no` varchar(25) COMMENT '父级课程分类',
    `bak`                   varchar(50) COMMENT '备用字段',
    `add_time`              datetime COMMENT '添加时间',
    `add_user_no`           varchar(24) COMMENT '添加人',
    `update_time`           datetime COMMENT '更新时间',
    `update_user_no`        varchar(24) COMMENT '更新人',
    PRIMARY KEY (`course_cate_id`),
    UNIQUE INDEX `idx_index_course_cate_no` (`course_cate_no`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;