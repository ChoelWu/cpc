package com.joe.pojo;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2020/3/14
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 
// +----------------------------------------------------------------------

import com.joe.entity.IndexChapter;
import com.joe.entity.IndexCourse;
import com.joe.entity.IndexLearnLog;
import com.joe.entity.IndexLesson;

public class LearnLogPOJO {
    private IndexLearnLog indexLearnLog;

    private IndexCourse indexCourse;

    private IndexChapter indexChapter;

    private IndexLesson indexLesson;

    public IndexLearnLog getIndexLearnLog() {
        return indexLearnLog;
    }

    public void setIndexLearnLog(IndexLearnLog indexLearnLog) {
        this.indexLearnLog = indexLearnLog;
    }

    public IndexCourse getIndexCourse() {
        return indexCourse;
    }

    public void setIndexCourse(IndexCourse indexCourse) {
        this.indexCourse = indexCourse;
    }

    public IndexChapter getIndexChapter() {
        return indexChapter;
    }

    public void setIndexChapter(IndexChapter indexChapter) {
        this.indexChapter = indexChapter;
    }

    public IndexLesson getIndexLesson() {
        return indexLesson;
    }

    public void setIndexLesson(IndexLesson indexLesson) {
        this.indexLesson = indexLesson;
    }

    @Override
    public String toString() {
        return "LearnLogPOJO{" +
                "indexLearnLog=" + indexLearnLog +
                ", indexCourse=" + indexCourse +
                ", indexChapter=" + indexChapter +
                ", indexLesson=" + indexLesson +
                '}';
    }
}
