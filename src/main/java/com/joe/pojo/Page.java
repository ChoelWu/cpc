package com.joe.pojo;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/19
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 分页
// +----------------------------------------------------------------------

public class Page {
    // 前一页
    private int prePage;

    // 当前页
    private int currentPage;

    // 下一页
    private int nextPage;

    // 总页数
    private int totalPage;

    // 每页记录条数
    private int recordNum;

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(int recordNum) {
        this.recordNum = recordNum;
    }

    @Override
    public String toString() {
        return "Page{" +
                "prePage=" + prePage +
                ", currentPage=" + currentPage +
                ", nextPage=" + nextPage +
                ", totalPage=" + totalPage +
                ", recordNum=" + recordNum +
                '}';
    }
}
