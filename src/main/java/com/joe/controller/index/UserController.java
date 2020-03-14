package com.joe.controller.index;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2020/3/13
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 用户端用户信息
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.joe.entity.IndexCourse;
import com.joe.entity.IndexUser;
import com.joe.entity.IndexUserCourse;
import com.joe.pojo.CourseLogPOJO;
import com.joe.service.system.IndexCourseService;
import com.joe.service.system.IndexUserCourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/index/user")
public class UserController {
    @Resource
    private IndexUserCourseService indexUserCourseService;

    @Resource
    private IndexCourseService indexCourseService;

    /**
     * 用户中心
     *
     * @param session session
     * @param model   model
     * @return 返回页面视图
     */
    @RequestMapping("/index.do")
    public String view(HttpSession session, Model model) {
        IndexUser indexUser = (IndexUser) session.getAttribute("indexUser");

        // 查询课时记录，倒序排序
        QueryWrapper<IndexUserCourse> indexUserCourseQueryWrapper = new QueryWrapper<>();
        indexUserCourseQueryWrapper.orderByDesc("last_time");
        List<IndexUserCourse> indexUserCourseList = indexUserCourseService.list();

        List<CourseLogPOJO> courseLogPOJOList = Lists.newArrayList();
        for(IndexUserCourse indexUserCourse : indexUserCourseList) {
            // 查询课程信息
            QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
            indexCourseQueryWrapper.eq("course_no", indexUserCourse.getCourseNo());
            IndexCourse indexCourse = indexCourseService.getOne(indexCourseQueryWrapper);

            CourseLogPOJO courseLogPOJO = new CourseLogPOJO();
            courseLogPOJO.setUserCourse(indexUserCourse);
            courseLogPOJO.setCourse(indexCourse);

            courseLogPOJOList.add(courseLogPOJO);
        }

        model.addAttribute("indexUser", indexUser);
        model.addAttribute("courseLogList", courseLogPOJOList);

        return "Index/User/index";
    }
}
