DROP TABLE IF EXISTS `cpc_index_article`;
CREATE TABLE `cpc_index_article`
(
    `article_id`      bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '文章ID',
    `article_no`      char(24)     NOT NULL COMMENT '文章编号 IANO+yyyymmddhhmmss+1位随机大写字母+5位随机数字',
    `article_name`    varchar(100) NOT NULL COMMENT '文章标题',
    `article_type`    char(1)      NOT NULL COMMENT '文章类型 1-原创文章 2-超级链接',
    `article_url`     varchar(100) COMMENT '文章链接地址(类型为超链接时)',
    `channel_no`      varchar(24) COMMENT '所属栏目',
    `is_top`          char(1)      NOT NULL COMMENT '是否制定 0-不置顶 1-置顶',
    `is_bold`         char(1)      NOT NULL COMMENT '是否加粗 0-不加粗 1-加粗',
    `article_status`  char(1)      NOT NULL COMMENT '文章状态 0-待审核 1-已发布',
    `article_summary` varchar(512) COMMENT '文章简介',
    `article_cover`   varchar(100) COMMENT '文章封面',
    `article_content` longtext COMMENT '文章内容',
    `article_source`  varchar(20) COMMENT '文章来源',
    `publish_time`    datetime COMMENT '发布时间',
    `bak`             varchar(50) COMMENT '备用字段',
    `add_time`        datetime COMMENT '添加时间',
    `add_user_no`     varchar(24) COMMENT '添加人',
    `update_time`     datetime COMMENT '更新时间',
    `update_user_no`  varchar(24) COMMENT '更新人',
    PRIMARY KEY (`article_id`),
    UNIQUE INDEX `idx_index_article_no` (`article_no`)
) ENGINE = InnoDB
  CHARACTER SET = utf8;