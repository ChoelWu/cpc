package com.joe.pojo;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2020/2/12
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 课程标签前台返回Json格式
// +----------------------------------------------------------------------

public class CourseTagsJson {
    private String value;

    private String title;

    private boolean disabled = false;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "CourseTagsJson{" +
                "value='" + value + '\'' +
                ", title='" + title + '\'' +
                ", disabled=" + disabled +
                '}';
    }
}
