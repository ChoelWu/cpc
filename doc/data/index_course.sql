DROP TABLE IF EXISTS `cpc_index_course`;
CREATE TABLE `cpc_index_course`
(
    `course_id`              bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '课程ID',
    `course_no`              char(25)    NOT NULL COMMENT '课程编号',
    `course_name`            varchar(50) NOT NULL COMMENT '课程名称',
    `course_tags`            varchar(100) COMMENT '课程标签',
    `course_cate_no`         varchar(25) COMMENT '课程分类',
    `course_intro`           varchar(512) COMMENT '课程简介',
    `course_author`          varchar(50) COMMENT '课程作者',
    `course_difficult_level` char(1) COMMENT '课程困难程度 1-简单 2-普通 3-困难',
    `course_fit_people`      varchar(10) COMMENT '课程适合人群',
    `visit_times`            int(11) COMMENT '课程访问次数',
    `course_cover`           varchar(100) COMMENT '课程封面',
    `is_top`                 char(1)     NOT NULL COMMENT '是否置顶 0-不置顶 1-置顶',
    `publish_time`           datetime COMMENT '发布时间',
    `role`                   varchar(10) COMMENT '1-校外学生 2-校内学生 3-教师',
    `course_status`          char(1)     NOT NULL COMMENT '课程状态 0-未审核 1-已发布',
    `course_learn_num`       int(11)     NOT NULL COMMENT '课程学习人次',
    `bak`                    varchar(50) COMMENT '备用字段',
    `add_time`               datetime COMMENT '添加时间',
    `add_user_no`            varchar(24) COMMENT '添加人',
    `update_time`            datetime COMMENT '更新时间',
    `update_user_no`         varchar(24) COMMENT '更新人',
    PRIMARY KEY (`course_id`),
    UNIQUE INDEX `idx_index_course_no` (`course_no`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;