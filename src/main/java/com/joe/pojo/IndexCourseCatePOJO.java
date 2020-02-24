package com.joe.pojo;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/13
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 
// +----------------------------------------------------------------------

import com.joe.entity.IndexCourse;
import com.joe.entity.IndexCourseCate;

import java.io.Serializable;
import java.util.List;

public class IndexCourseCatePOJO implements Serializable {
    // 父分类
    private IndexCourseCate indexCourseCate;

    // 子分类
    private List<IndexCourseCate> childCourseCateList;

    // 推荐课程
    private List<IndexCourse> presentCourseList;

    public IndexCourseCate getIndexCourseCate() {
        return indexCourseCate;
    }

    public void setIndexCourseCate(IndexCourseCate indexCourseCate) {
        this.indexCourseCate = indexCourseCate;
    }

    public List<IndexCourseCate> getChildCourseCateList() {
        return childCourseCateList;
    }

    public void setChildCourseCateList(List<IndexCourseCate> childCourseCateList) {
        this.childCourseCateList = childCourseCateList;
    }

    public List<IndexCourse> getPresentCourseList() {
        return presentCourseList;
    }

    public void setPresentCourseList(List<IndexCourse> presentCourseList) {
        this.presentCourseList = presentCourseList;
    }

    @Override
    public String toString() {
        return "IndexCourseCatePOJO{" +
                "indexCourseCate=" + indexCourseCate +
                ", childCourseCateList=" + childCourseCateList +
                ", presentCourseList=" + presentCourseList +
                '}';
    }
}
