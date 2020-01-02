DROP TABLE IF EXISTS `cpc_index_chapter`;
CREATE TABLE `cpc_index_chapter`
(
    `chapter_id`     bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '章节ID',
    `chapter_no`     char(26)    NOT NULL COMMENT '章节编号 ICHANO',
    `chapter_name`   varchar(50) NOT NULL COMMENT '章节名称',
    `course_no`      char(24)    NOT NULL COMMENT '课程编号',
    `chapter_index`  int(2)      NOT NULL COMMENT '章节序号',
    `bak`            varchar(50) COMMENT '备用字段',
    `add_time`       datetime COMMENT '添加时间',
    `add_user_no`    varchar(24) COMMENT '添加人',
    `update_time`    datetime COMMENT '更新时间',
    `update_user_no` varchar(24) COMMENT '更新人',
    PRIMARY KEY (`chapter_id`),
    UNIQUE INDEX `idx_index_chapter_no` (`chapter_no`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;