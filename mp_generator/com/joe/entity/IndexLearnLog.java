package com.joe.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author joe
 * @since 2020-03-09
 */
@TableName("cpc_index_learn_log")
public class IndexLearnLog implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 日志ID
     */
    @TableId(value = "learn_log_id", type = IdType.AUTO)
    private Long learnLogId;

    /**
     * 日志编号
     */
    private String learnLogNo;

    /**
     * 课程编号
     */
    private String courseNo;

    /**
     * 章节编号
     */
    private String chapterNo;

    /**
     * 课时编号
     */
    private String lessonNo;

    /**
     * 用户编号
     */
    private String userNo;

    /**
     * 备用字段
     */
    private String bak;

    /**
     * 添加时间
     */
    private LocalDateTime addTime;

    /**
     * 添加人
     */
    private String addUserNo;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人
     */
    private String updateUserNo;


    public Long getLearnLogId() {
        return learnLogId;
    }

    public void setLearnLogId(Long learnLogId) {
        this.learnLogId = learnLogId;
    }

    public String getLearnLogNo() {
        return learnLogNo;
    }

    public void setLearnLogNo(String learnLogNo) {
        this.learnLogNo = learnLogNo;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getChapterNo() {
        return chapterNo;
    }

    public void setChapterNo(String chapterNo) {
        this.chapterNo = chapterNo;
    }

    public String getLessonNo() {
        return lessonNo;
    }

    public void setLessonNo(String lessonNo) {
        this.lessonNo = lessonNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
    }

    public LocalDateTime getAddTime() {
        return addTime;
    }

    public void setAddTime(LocalDateTime addTime) {
        this.addTime = addTime;
    }

    public String getAddUserNo() {
        return addUserNo;
    }

    public void setAddUserNo(String addUserNo) {
        this.addUserNo = addUserNo;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
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
        return "IndexLearnLog{" +
        "learnLogId=" + learnLogId +
        ", learnLogNo=" + learnLogNo +
        ", courseNo=" + courseNo +
        ", chapterNo=" + chapterNo +
        ", lessonNo=" + lessonNo +
        ", userNo=" + userNo +
        ", bak=" + bak +
        ", addTime=" + addTime +
        ", addUserNo=" + addUserNo +
        ", updateTime=" + updateTime +
        ", updateUserNo=" + updateUserNo +
        "}";
    }
}
