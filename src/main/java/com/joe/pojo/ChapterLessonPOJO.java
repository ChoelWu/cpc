package com.joe.pojo;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2020/3/5
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 章节课时POJO
// +----------------------------------------------------------------------

import com.joe.entity.IndexChapter;
import com.joe.entity.IndexLesson;

import java.util.List;

public class ChapterLessonPOJO {
    private IndexChapter chapter;

    private List<IndexLesson> lessonList;

    public IndexChapter getChapter() {
        return chapter;
    }

    public void setChapter(IndexChapter chapter) {
        this.chapter = chapter;
    }

    public List<IndexLesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<IndexLesson> lessonList) {
        this.lessonList = lessonList;
    }

    @Override
    public String toString() {
        return "ChapterLessonPOJO{" +
                "chapter=" + chapter +
                ", lessonList=" + lessonList +
                '}';
    }
}
