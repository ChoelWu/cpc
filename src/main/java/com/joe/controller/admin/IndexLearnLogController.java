package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2020/3/9
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 学习日志
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.entity.IndexLearnLog;
import com.joe.pojo.Page;
import com.joe.service.common.PageService;
import com.joe.service.system.IndexLearnLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/learn_log")
public class IndexLearnLogController {
    @Resource
    private IndexLearnLogService indexLearnLogService;

    @Resource
    private PageService pageService;

    /**
     * 列表页面显示
     *
     * @return 返回页面视图
     */
    @RequestMapping("view.do")
    public String view(Model model, @RequestParam(required = false, defaultValue = "1") int currentPage, @RequestParam(required = false) String conditions) {
        // 查询数据条数
        QueryWrapper<IndexLearnLog> indexLearnLogQueryWrapper = new QueryWrapper<>();

        Gson gson = new Gson();
        Map<String, String> conditionMap = gson.fromJson(conditions, new TypeToken<Map<String, String>>() {
        }.getType());

        // 课程标签名模糊查询
        if (null != conditionMap) {
            if (conditionMap.containsKey("courseTagName") && StringUtils.isNoneEmpty(conditionMap.get("courseTagName"))) {
                indexLearnLogQueryWrapper.like("course_tag_name", conditionMap.get("courseTagName"));
            }
        }
        // 查询条数
        int indexCourseTagNum = indexLearnLogService.count(indexLearnLogQueryWrapper);

        // 分页
        Page page = pageService.Pagination(currentPage, indexCourseTagNum, 10);

        // 获取用户列表
        int start = page.getRecordNum() * (page.getCurrentPage() - 1);
        indexLearnLogQueryWrapper.orderByDesc("course_tag_no").last("limit " + start + "," + page.getRecordNum());
        List<IndexLearnLog> indexLearnLogList = indexLearnLogService.list(indexLearnLogQueryWrapper);

        // 绑定数据
        model.addAttribute("indexLearnLogList", indexLearnLogList);
        model.addAttribute("page", page);
        model.addAttribute("conditions", conditionMap);

        // 返回视图页面
        return "Admin/LearnLog/index";
    }
}
