package com.joe.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author joe
 * @since 2019-11-25
 */
@TableName("cpc_index_article")
public class IndexArticle implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 文章ID
     */
    @TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;

    /**
     * 文章编号 IANO+yyyymmddhhmmss+1位随机大写字母+5位随机数字
     */
    private String articleNo;

    /**
     * 文章标题
     */
    private String articleName;

    /**
     * 文章类型 1-超级链接 2-原创文章
     */
    private String articleType;

    /**
     * 文章链接地址(类型为超链接时)
     */
    private String articleUrl;

    /**
     * 所属栏目
     */
    private String channelNo;

    /**
     * 是否制定 0-不置顶 1-置顶
     */
    private String isTop;

    /**
     * 是否加粗 0-不加粗 1-加粗
     */
    private String isBold;

    /**
     * 文章状态 0-待审核 1-已发布
     */
    private String articleStatus;

    /**
     * 文章简介
     */
    private String articleSummary;

    /**
     * 文章封面
     */
    private String articleCover;

    /**
     * 文章简介
     */
    private String articleContent;

    /**
     * 文章来源
     */
    private String articleSource;

    /**
     * 发布时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishTime;

    /**
     * 备用字段
     */
    private String bak;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 添加人
     */
    private String addUserNo;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateUserNo;


    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(String articleNo) {
        this.articleNo = articleNo;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getIsBold() {
        return isBold;
    }

    public void setIsBold(String isBold) {
        this.isBold = isBold;
    }

    public String getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }

    public String getArticleSummary() {
        return articleSummary;
    }

    public void setArticleSummary(String articleSummary) {
        this.articleSummary = articleSummary;
    }

    public String getArticleCover() {
        return articleCover;
    }

    public void setArticleCover(String articleCover) {
        this.articleCover = articleCover;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public String getArticleSource() {
        return articleSource;
    }

    public void setArticleSource(String articleSource) {
        this.articleSource = articleSource;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddUserNo() {
        return addUserNo;
    }

    public void setAddUserNo(String addUserNo) {
        this.addUserNo = addUserNo;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserNo() {
        return updateUserNo;
    }

    public void setUpdateUserNo(String updateUserNo) {
        this.updateUserNo = updateUserNo;
    }

    @Override
    public String toString() {
        return "IndexArticle{" +
        "articleId=" + articleId +
        ", articleNo=" + articleNo +
        ", articleName=" + articleName +
        ", articleType=" + articleType +
        ", articleUrl=" + articleUrl +
        ", channelNo=" + channelNo +
        ", isTop=" + isTop +
        ", isBold=" + isBold +
        ", articleStatus=" + articleStatus +
        ", articleSummary=" + articleSummary +
        ", articleCover=" + articleCover +
        ", articleContent=" + articleContent +
        ", articleSource=" + articleSource +
        ", publishTime=" + publishTime +
        ", bak=" + bak +
        ", addTime=" + addTime +
        ", addUserNo=" + addUserNo +
        ", updateTime=" + updateTime +
        ", updateUserNo=" + updateUserNo +
        "}";
    }
}
