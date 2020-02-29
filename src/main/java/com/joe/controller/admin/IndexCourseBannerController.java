package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2020/2/26
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 课程推荐位
// +----------------------------------------------------------------------

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.entity.AdminDict;
import com.joe.entity.IndexCourseBanner;
import com.joe.pojo.Course;
import com.joe.pojo.Page;
import com.joe.service.common.PageService;
import com.joe.service.system.AdminDictService;
import com.joe.service.system.IndexCourseBannerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/course_banner")
public class IndexCourseBannerController {
    @Resource
    private IndexCourseBannerService indexCourseBannerService;

    @Resource
    private AdminDictService adminDictService;

    @Resource
    private PageService pageService;

    @RequestMapping("view.do")
    public String view(Model model, @RequestParam(required = false, defaultValue = "1") int currentPage, @RequestParam(required = false) String conditions) {
        Gson gson = new Gson();
        Map<String, Object> conditionMap = gson.fromJson(conditions, new TypeToken<Map<String, Object>>() {
        }.getType());

        // 查询条数
        int adminCourseNum = indexCourseBannerService.countCourseBanner(conditionMap);

        // 分页
        Page page = pageService.Pagination(currentPage, adminCourseNum, 10);

        // 获取Banner列表
        int start = page.getRecordNum() * (page.getCurrentPage() - 1);
        Map<String, Integer> pageInfoMap = Maps.newHashMap();
        pageInfoMap.put("startRow", start);
        pageInfoMap.put("rowNum", page.getRecordNum());
        List<IndexCourseBanner> courseList = indexCourseBannerService.listCourseBanner(conditionMap, pageInfoMap);

        // 查询课程推荐位状态列表
        List<AdminDict> courseBannerStatusAdminDictList = adminDictService.getDictListByDictName("courseBannerStatus");

        // 数据绑定
        model.addAttribute("courseList", courseList);
        model.addAttribute("courseBannerStatusAdminDictList", courseBannerStatusAdminDictList);

        // 返回页面视图
        return "Admin/CourseBanner/index";
    }
}
