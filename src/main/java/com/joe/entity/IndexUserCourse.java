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
 * @since 2020-03-14
 */
@TableName("cpc_index_user_course")
public class IndexUserCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户课程ID
     */
    @TableId(value = "user_course_id", type = IdType.AUTO)
    private Long userCourseId;

    /**
     * 用户课程 用户课程 IUCNO+yyyymmddhhmmss+1位随机大写字母+5位随机数字
     */
    private String userCourseNo;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 课程学习状态
     */
    private String status;

    /**
     * 最后学习时间
     */
    private Date lastTime;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getUserCourseId() {
        return userCourseId;
    }

    public void setUserCourseId(Long userCourseId) {
        this.userCourseId = userCourseId;
    }

    public String getUserCourseNo() {
        return userCourseNo;
    }

    public void setUserCourseNo(String userCourseNo) {
        this.userCourseNo = userCourseNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
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
        return "IndexUserCourse{" +
                "userCourseId=" + userCourseId +
                ", userCourseNo='" + userCourseNo + '\'' +
                ", userNo='" + userNo + '\'' +
                ", courseNo='" + courseNo + '\'' +
                ", status='" + status + '\'' +
                ", lastTime=" + lastTime +
                ", bak='" + bak + '\'' +
                ", addTime=" + addTime +
                ", addUserNo='" + addUserNo + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserNo='" + updateUserNo + '\'' +
                '}';
    }
}
