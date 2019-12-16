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
 * @since 2019-12-03
 */
@TableName("cpc_index_lesson")
public class IndexLesson implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 课时ID
     */
    @TableId(value = "lesson_id", type = IdType.AUTO)
    private Long lessonId;

    /**
     * 课时编号 ILNO
     */
    private String lessonNo;

    /**
     * 课时名称
     */
    private String lessonName;

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
    private Integer lessonIndex;

    /**
     * 课时类型 1-vedio 2-swf 3-vedio+swf
     */
    private String lessonType;

    /**
     * 视频文件位置
     */
    private String lessonVideo;

    /**
     * 动画文件位置
     */
    private String lessonSwf;

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


    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonNo() {
        return lessonNo;
    }

    public void setLessonNo(String lessonNo) {
        this.lessonNo = lessonNo;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
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

    public Integer getLessonIndex() {
        return lessonIndex;
    }

    public void setLessonIndex(Integer lessonIndex) {
        this.lessonIndex = lessonIndex;
    }

    public String getLessonType() {
        return lessonType;
    }

    public void setLessonType(String lessonType) {
        this.lessonType = lessonType;
    }

    public String getLessonVideo() {
        return lessonVideo;
    }

    public void setLessonVideo(String lessonVideo) {
        this.lessonVideo = lessonVideo;
    }

    public String getLessonSwf() {
        return lessonSwf;
    }

    public void setLessonSwf(String lessonSwf) {
        this.lessonSwf = lessonSwf;
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
        return "IndexLesson{" +
        "lessonId=" + lessonId +
        ", lessonNo=" + lessonNo +
        ", lessonName=" + lessonName +
        ", courseNo=" + courseNo +
        ", chapterNo=" + chapterNo +
        ", lessonIndex=" + lessonIndex +
        ", lessonType=" + lessonType +
        ", lessonVideo=" + lessonVideo +
        ", lessonSwf=" + lessonSwf +
        ", bak=" + bak +
        ", addTime=" + addTime +
        ", addUserNo=" + addUserNo +
        ", updateTime=" + updateTime +
        ", updateUserNo=" + updateUserNo +
        "}";
    }
}
