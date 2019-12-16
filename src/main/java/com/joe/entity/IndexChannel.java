package com.joe.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("cpc_index_channel")
public class IndexChannel implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 栏目ID
     */
    @TableId(value = "channel_id", type = IdType.AUTO)
    private Long channelId;

    /**
     * 栏目编号 ICNO+yyyymmddhhmmss+1位随机大写字母+5位随机数字
     */
    private String channelNo;

    /**
     * 栏目名称
     */
    private String channelName;

    /**
     * 栏目级别 1-父级栏目 2-子级栏目
     */
    private String channelLevel;

    /**
     * 栏目类型 1-列表页 2-文章页 3-超链接
     */
    private String channelType;

    /**
     * 栏目链接地址(超链接时)
     */
    private String channelUrl;

    /**
     * 栏目编号
     */
    private String channelIndex;

    /**
     * 栏目简介
     */
    private String channelIntro;

    /**
     * 父级栏目
     */
    private String parentChannelNo;

    /**
     * 是否是导航 0-否 1-是
     */
    private String isNav;

    /**
     * 栏目状态 0-禁用 1-启用
     */
    private String channelStatus;

    /**
     * 栏目图
     */
    private String channelPic;

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


    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelLevel() {
        return channelLevel;
    }

    public void setChannelLevel(String channelLevel) {
        this.channelLevel = channelLevel;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public String getChannelIndex() {
        return channelIndex;
    }

    public void setChannelIndex(String channelIndex) {
        this.channelIndex = channelIndex;
    }

    public String getChannelIntro() {
        return channelIntro;
    }

    public void setChannelIntro(String channelIntro) {
        this.channelIntro = channelIntro;
    }

    public String getParentChannelNo() {
        return parentChannelNo;
    }

    public void setParentChannelNo(String parentChannelNo) {
        this.parentChannelNo = parentChannelNo;
    }

    public String getIsNav() {
        return isNav;
    }

    public void setIsNav(String isNav) {
        this.isNav = isNav;
    }

    public String getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(String channelStatus) {
        this.channelStatus = channelStatus;
    }

    public String getChannelPic() {
        return channelPic;
    }

    public void setChannelPic(String channelPic) {
        this.channelPic = channelPic;
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
        return "IndexChannel{" +
        "channelId=" + channelId +
        ", channelNo=" + channelNo +
        ", channelName=" + channelName +
        ", channelLevel=" + channelLevel +
        ", channelType=" + channelType +
        ", channelUrl=" + channelUrl +
        ", channelIndex=" + channelIndex +
        ", channelIntro=" + channelIntro +
        ", parentChannelNo=" + parentChannelNo +
        ", isNav=" + isNav +
        ", channelStatus=" + channelStatus +
        ", channelPic=" + channelPic +
        ", bak=" + bak +
        ", addTime=" + addTime +
        ", addUserNo=" + addUserNo +
        ", updateTime=" + updateTime +
        ", updateUserNo=" + updateUserNo +
        "}";
    }
}
