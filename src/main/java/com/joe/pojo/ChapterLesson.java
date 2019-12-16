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

import com.joe.entity.IndexLesson;

import java.io.Serializable;
import java.util.List;

public class ChapterLesson implements Serializable {

    // 父菜单
    private Chapter chapter;

    // 子菜单
    private List<IndexLesson> indexLessonList;

    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public List<IndexLesson> getIndexLessonList() {
        return indexLessonList;
    }

    public void setIndexLessonList(List<IndexLesson> indexLessonList) {
        this.indexLessonList = indexLessonList;
    }

    @Override
    public String toString() {
        return "ChapterLesson{" +
                "chapter=" + chapter +
                ", indexLessonList=" + indexLessonList +
                '}';
    }
}
