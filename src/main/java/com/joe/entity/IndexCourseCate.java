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
 * @since 2019-12-01
 */
@TableName("cpc_index_course_cate")
public class IndexCourseCate implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 课程分类ID
     */
    @TableId(value = "course_cate_id", type = IdType.AUTO)
    private Long courseCateId;

    /**
     * 课程分类编号
     */
    private String courseCateNo;

    /**
     * 课程分类名称
     */
    private String courseCateName;

    /**
     * 课程分类级别 1-父级课程分类 2-子级课程分类
     */
    private String courseCateLevel;

    /**
     * 课程分类编号
     */
    private String courseCateIndex;

    /**
     * 父级课程分类
     */
    private String parentCourseCateNo;

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


    public Long getCourseCateId() {
        return courseCateId;
    }

    public void setCourseCateId(Long courseCateId) {
        this.courseCateId = courseCateId;
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

    public String getCourseCateLevel() {
        return courseCateLevel;
    }

    public void setCourseCateLevel(String courseCateLevel) {
        this.courseCateLevel = courseCateLevel;
    }

    public String getCourseCateIndex() {
        return courseCateIndex;
    }

    public void setCourseCateIndex(String courseCateIndex) {
        this.courseCateIndex = courseCateIndex;
    }

    public String getParentCourseCateNo() {
        return parentCourseCateNo;
    }

    public void setParentCourseCateNo(String parentCourseCateNo) {
        this.parentCourseCateNo = parentCourseCateNo;
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
        return "IndexCourseCate{" +
        "courseCateId=" + courseCateId +
        ", courseCateNo=" + courseCateNo +
        ", courseCateName=" + courseCateName +
        ", courseCateLevel=" + courseCateLevel +
        ", courseCateIndex=" + courseCateIndex +
        ", parentCourseCateNo=" + parentCourseCateNo +
        ", bak=" + bak +
        ", addTime=" + addTime +
        ", addUserNo=" + addUserNo +
        ", updateTime=" + updateTime +
        ", updateUserNo=" + updateUserNo +
        "}";
    }
}
