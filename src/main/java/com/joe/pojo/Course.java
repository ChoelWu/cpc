package com.joe.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author joe
 * @since 2019-12-02
 */
public class Course implements Serializable {
    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 课程标签
     */
    private String courseTags;

    /**
     * 课程分类
     */
    private String courseCateNo;

    /**
     * 课程分类
     */
    private String courseCateName;

    /**
     * 课程简介
     */
    private String courseIntro;

    /**
     * 课程作者
     */
    private String courseAuthor;

    /**
     * 课程难度
     */
    private String courseDifficultLevel;

    /**
     * 课程适合人群
     */
    private String courseFitPeople;

    /**
     * 课程访问次数
     */
    private Integer visitTimes;

    /**
     * 课程封面
     */
    private String courseCover;

    /**
     * 是否置顶 0-不置顶 1-置顶
     */
    private String isTop;

    /**
     * 课程时长-小时
     */
    private Integer courseDurationHour;

    /**
     * 课程时长-分钟
     */
    private Integer courseDurationMinute;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 1-校外学生 2-校内学生 3-教师
     */
    private String role;

    /**
     * 课程状态 0-未审核 1-已发布
     */
    private String courseStatus;

    /**
     * 课程学习人次
     */
    private Integer courseLearnNum;

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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseTags() {
        return courseTags;
    }

    public void setCourseTags(String courseTags) {
        this.courseTags = courseTags;
    }

    public String getCourseCateNo() {
        return courseCateNo;
    }

    public void setCourseCateNo(String courseCateNo) {
        this.courseCateNo = courseCateNo;
    }

    public String getCourseCateName() {
        return courseCateName;
    }

    public void setCourseCateName(String courseCateName) {
        this.courseCateName = courseCateName;
    }

    public String getCourseIntro() {
        return courseIntro;
    }

    public void setCourseIntro(String courseIntro) {
        this.courseIntro = courseIntro;
    }

    public String getCourseAuthor() {
        return courseAuthor;
    }

    public void setCourseAuthor(String courseAuthor) {
        this.courseAuthor = courseAuthor;
    }

    public String getCourseDifficultLevel() {
        return courseDifficultLevel;
    }

    public void setCourseDifficultLevel(String courseDifficultLevel) {
        this.courseDifficultLevel = courseDifficultLevel;
    }

    public String getCourseFitPeople() {
        return courseFitPeople;
    }

    public void setCourseFitPeople(String courseFitPeople) {
        this.courseFitPeople = courseFitPeople;
    }

    public Integer getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(Integer visitTimes) {
        this.visitTimes = visitTimes;
    }

    public String getCourseCover() {
        return courseCover;
    }

    public void setCourseCover(String courseCover) {
        this.courseCover = courseCover;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public Integer getCourseDurationHour() {
        return courseDurationHour;
    }

    public void setCourseDurationHour(Integer courseDurationHour) {
        this.courseDurationHour = courseDurationHour;
    }

    public Integer getCourseDurationMinute() {
        return courseDurationMinute;
    }

    public void setCourseDurationMinute(Integer courseDurationMinute) {
        this.courseDurationMinute = courseDurationMinute;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public Integer getCourseLearnNum() {
        return courseLearnNum;
    }

    public void setCourseLearnNum(Integer courseLearnNum) {
        this.courseLearnNum = courseLearnNum;
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
        return "Course{" +
                "courseId=" + courseId +
                ", courseNo='" + courseNo + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseTags='" + courseTags + '\'' +
                ", courseCateNo='" + courseCateNo + '\'' +
                ", courseCateName='" + courseCateName + '\'' +
                ", courseIntro='" + courseIntro + '\'' +
                ", courseAuthor='" + courseAuthor + '\'' +
                ", courseDifficultLevel='" + courseDifficultLevel + '\'' +
                ", courseFitPeople='" + courseFitPeople + '\'' +
                ", visitTimes=" + visitTimes +
                ", courseCover='" + courseCover + '\'' +
                ", isTop='" + isTop + '\'' +
                ", courseDurationHour=" + courseDurationHour +
                ", courseDurationMinute=" + courseDurationMinute +
                ", publishTime=" + publishTime +
                ", role='" + role + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                ", courseLearnNum=" + courseLearnNum +
                ", bak='" + bak + '\'' +
                ", addTime=" + addTime +
                ", addUserNo='" + addUserNo + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserNo='" + updateUserNo + '\'' +
                '}';
    }
}
