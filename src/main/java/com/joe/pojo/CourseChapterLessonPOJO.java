package com.joe.pojo;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2020/3/5
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 课程章节课时POJO
// +----------------------------------------------------------------------

import com.joe.entity.IndexCourse;
import com.joe.entity.IndexUserCourse;

import java.util.List;

public class CourseChapterLessonPOJO {
    private IndexCourse course;

    private List<ChapterLessonPOJO> chapterList;

    public IndexCourse getCourse() {
        return course;
    }

    public void setCourse(IndexCourse course) {
        this.course = course;
    }

    public List<ChapterLessonPOJO> getChapterList() {
        return chapterList;
    }

    public void setChapterList(List<ChapterLessonPOJO> chapterList) {
        this.chapterList = chapterList;
    }

    @Override
    public String toString() {
        return "CourseChapterLessonPOJO{" +
                "course=" + course +
                ", chapterList=" + chapterList +
                '}';
    }
}
