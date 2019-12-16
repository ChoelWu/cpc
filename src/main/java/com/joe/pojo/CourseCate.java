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

import com.joe.entity.IndexCourseCate;

import java.io.Serializable;
import java.util.List;

public class CourseCate implements Serializable {
    // 父分类
    private IndexCourseCate indexCourseCate;

    // 子分类
    private List<IndexCourseCate> childCourseCateList;

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

    @Override
    public String toString() {
        return "CourseCate{" +
                "indexCourseCate=" + indexCourseCate +
                ", childCourseCateList=" + childCourseCateList +
                '}';
    }
}
