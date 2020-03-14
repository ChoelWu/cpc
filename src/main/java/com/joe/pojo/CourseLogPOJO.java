package com.joe.pojo;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2020/3/14
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 前台课程记录
// +----------------------------------------------------------------------

import com.joe.entity.IndexCourse;
import com.joe.entity.IndexUserCourse;

public class CourseLogPOJO {
    private IndexUserCourse userCourse;

    private IndexCourse course;

    public IndexUserCourse getUserCourse() {
        return userCourse;
    }

    public void setUserCourse(IndexUserCourse userCourse) {
        this.userCourse = userCourse;
    }

    public IndexCourse getCourse() {
        return course;
    }

    public void setCourse(IndexCourse course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "CourseLogPOJO{" +
                "userCourse=" + userCourse +
                ", course=" + course +
                '}';
    }
}
