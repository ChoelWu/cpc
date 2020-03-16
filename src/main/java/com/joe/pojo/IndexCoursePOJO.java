package com.joe.pojo;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2020/2/20
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 
// +----------------------------------------------------------------------

import com.joe.entity.IndexCourse;
import com.joe.entity.IndexCourseTag;
import com.joe.entity.IndexUserCourse;

import java.util.List;

public class IndexCoursePOJO {
    // 课程对象
    private IndexCourse course;

    // 课程所对应的标签
    private List<IndexCourseTag> courseTagList;

    private IndexUserCourse indexUserCourse;

    public IndexCourse getCourse() {
        return course;
    }

    public void setCourse(IndexCourse course) {
        this.course = course;
    }

    public List<IndexCourseTag> getCourseTagList() {
        return courseTagList;
    }

    public void setCourseTagList(List<IndexCourseTag> courseTagList) {
        this.courseTagList = courseTagList;
    }

    public IndexUserCourse getIndexUserCourse() {
        return indexUserCourse;
    }

    public void setIndexUserCourse(IndexUserCourse indexUserCourse) {
        this.indexUserCourse = indexUserCourse;
    }

    @Override
    public String toString() {
        return "IndexCoursePOJO{" +
                "course=" + course +
                ", courseTagList=" + courseTagList +
                ", indexUserCourse=" + indexUserCourse +
                '}';
    }
}
