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
 * @since 2019-12-02
 */
@TableName("cpc_index_course_tag")
public class IndexCourseTag implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 课程标签ID
     */
    @TableId(value = "course_tag_id", type = IdType.AUTO)
    private Long courseTagId;

    /**
     * 课程标签编号
     */
    private String courseTagNo;

    /**
     * 课程标签名称
     */
    private String courseTagName;

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


    public Long getCourseTagId() {
        return courseTagId;
    }

    public void setCourseTagId(Long courseTagId) {
        this.courseTagId = courseTagId;
    }

    public String getCourseTagNo() {
        return courseTagNo;
    }

    public void setCourseTagNo(String courseTagNo) {
        this.courseTagNo = courseTagNo;
    }

    public String getCourseTagName() {
        return courseTagName;
    }

    public void setCourseTagName(String courseTagName) {
        this.courseTagName = courseTagName;
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
        return "IndexCourseTag{" +
        "courseTagId=" + courseTagId +
        ", courseTagNo=" + courseTagNo +
        ", courseTagName=" + courseTagName +
        ", bak=" + bak +
        ", addTime=" + addTime +
        ", addUserNo=" + addUserNo +
        ", updateTime=" + updateTime +
        ", updateUserNo=" + updateUserNo +
        "}";
    }
}
