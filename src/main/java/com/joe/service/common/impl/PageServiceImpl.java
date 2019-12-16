package com.joe.service.common.impl;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/19
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 分页
// +----------------------------------------------------------------------

import com.joe.pojo.Page;
import com.joe.service.common.PageService;
import org.springframework.stereotype.Service;

@Service
public class PageServiceImpl implements PageService {
    /**
     * 生成分页信息
     *
     * @param currentPage 当前页
     * @param rowNum      查询出的所有数据数目
     * @return 返回分页结果
     */
    public Page Pagination(int currentPage, int rowNum) {
        Page page = new Page();

        int recordNum = 10;

        int totalNum = (int) Math.floor(rowNum * 1.0 / recordNum) + 1;
        // 当前页
        page.setCurrentPage(totalNum);
        if (currentPage <= totalNum) {
            page.setCurrentPage(currentPage);
        }

        int finalRow = recordNum * page.getCurrentPage();
        // 上一页
        page.setPrePage(-1);
        if (page.getCurrentPage() != 1) {
            page.setPrePage(page.getCurrentPage() - 1);
        }

        // 下一页
        page.setNextPage(-1);
        if (finalRow < rowNum) {
            page.setNextPage(page.getCurrentPage() + 1);
        }

        // 每页的记录数目
        page.setRecordNum(recordNum);

        // 所有记录数目
        page.setTotalPage(totalNum);

        // 返回分页对象
        return page;
    }
}
