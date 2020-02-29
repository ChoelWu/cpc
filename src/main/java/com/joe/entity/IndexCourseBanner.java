package com.joe.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author joe
 * @since 2020-02-24
 */
@TableName("cpc_index_course_banner")
public class IndexCourseBanner implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 推荐位ID
     */
    @TableId(value = "course_banner_id", type = IdType.AUTO)
    private Long courseBannerId;

    /**
     * 推荐位编号 ICNO+yyyymmddhhmmss+1位随机大写字母+5位随机数字
     */
    private String courseBannerNo;

    /**
     * 推荐位名称
     */
    private String courseBannerName;

    /**
     * 推荐位链接地址(超链接时)
     */
    private String courseBannerUrl;

    /**
     * 推荐位编号
     */
    private String courseBannerIndex;

    /**
     * 推荐位简介
     */
    private String courseBannerIntro;

    /**
     * 推荐位状态 0-禁用 1-启用
     */
    private String courseBannerStatus;

    /**
     * 推荐位封面
     */
    private String courseBannerPic;

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


    public Long getCourseBannerId() {
        return courseBannerId;
    }

    public void setCourseBannerId(Long courseBannerId) {
        this.courseBannerId = courseBannerId;
    }

    public String getCourseBannerNo() {
        return courseBannerNo;
    }

    public void setCourseBannerNo(String courseBannerNo) {
        this.courseBannerNo = courseBannerNo;
    }

    public String getCourseBannerName() {
        return courseBannerName;
    }

    public void setCourseBannerName(String courseBannerName) {
        this.courseBannerName = courseBannerName;
    }

    public String getCourseBannerUrl() {
        return courseBannerUrl;
    }

    public void setCourseBannerUrl(String courseBannerUrl) {
        this.courseBannerUrl = courseBannerUrl;
    }

    public String getCourseBannerIndex() {
        return courseBannerIndex;
    }

    public void setCourseBannerIndex(String courseBannerIndex) {
        this.courseBannerIndex = courseBannerIndex;
    }

    public String getCourseBannerIntro() {
        return courseBannerIntro;
    }

    public void setCourseBannerIntro(String courseBannerIntro) {
        this.courseBannerIntro = courseBannerIntro;
    }

    public String getCourseBannerStatus() {
        return courseBannerStatus;
    }

    public void setCourseBannerStatus(String courseBannerStatus) {
        this.courseBannerStatus = courseBannerStatus;
    }

    public String getCourseBannerPic() {
        return courseBannerPic;
    }

    public void setCourseBannerPic(String courseBannerPic) {
        this.courseBannerPic = courseBannerPic;
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
        return "IndexCourseBanner{" +
        "courseBannerId=" + courseBannerId +
        ", courseBannerNo=" + courseBannerNo +
        ", courseBannerName=" + courseBannerName +
        ", courseBannerUrl=" + courseBannerUrl +
        ", courseBannerIndex=" + courseBannerIndex +
        ", courseBannerIntro=" + courseBannerIntro +
        ", courseBannerStatus=" + courseBannerStatus +
        ", courseBannerPic=" + courseBannerPic +
        ", bak=" + bak +
        ", addTime=" + addTime +
        ", addUserNo=" + addUserNo +
        ", updateTime=" + updateTime +
        ", updateUserNo=" + updateUserNo +
        "}";
    }
}
