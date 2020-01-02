DROP TABLE IF EXISTS `cpc_index_lesson`;
CREATE TABLE `cpc_index_lesson`
(
    `lesson_id`      bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '课时ID',
    `lesson_no`      char(24)    NOT NULL COMMENT '课时编号 ILNO',
    `lesson_name`    varchar(50) NOT NULL COMMENT '课时名称',
    `course_no`      char(25)    NOT NULL COMMENT '课程编号',
    `chapter_no`     char(26)    NOT NULL COMMENT '章节编号',
    `lesson_index`   int(2)      NOT NULL COMMENT '课时编号',
    `lesson_type`    char(1) COMMENT '课时类型 1-vedio 2-swf 3-vedio+swf',
    `lesson_video`   varchar(100) COMMENT '视频文件位置',
    `lesson_swf`     varchar(100) COMMENT '动画文件位置',
    `bak`            varchar(50) COMMENT '备用字段',
    `add_time`       datetime COMMENT '添加时间',
    `add_user_no`    varchar(24) COMMENT '添加人',
    `update_time`    datetime COMMENT '更新时间',
    `update_user_no` varchar(24) COMMENT '更新人',
    PRIMARY KEY (`lesson_id`),
    UNIQUE INDEX `idx_index_lesson_no` (`lesson_no`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;