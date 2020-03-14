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
import com.joe.entity.*;
import com.joe.pojo.CourseLogPOJO;
import com.joe.pojo.LearnLogPOJO;
import com.joe.service.system.*;
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

    @Resource
    private IndexLearnLogService indexLearnLogService;

    @Resource
    private IndexChapterService indexChapterService;

    @Resource
    private IndexLessonService indexLessonService;

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
        for (IndexUserCourse indexUserCourse : indexUserCourseList) {
            // 查询课程信息
            QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
            indexCourseQueryWrapper.eq("course_no", indexUserCourse.getCourseNo());
            IndexCourse indexCourse = indexCourseService.getOne(indexCourseQueryWrapper);

            CourseLogPOJO courseLogPOJO = new CourseLogPOJO();
            courseLogPOJO.setUserCourse(indexUserCourse);
            courseLogPOJO.setCourse(indexCourse);

            courseLogPOJOList.add(courseLogPOJO);
        }

        // 绑定数据
        model.addAttribute("indexUser", indexUser);
        model.addAttribute("courseLogList", courseLogPOJOList);

        // 返回页面视图
        return "Index/User/index";
    }

    /**
     * 学习日志
     *
     * @param session session
     * @param model   model
     * @return 返回页面视图
     */
    @RequestMapping("/learn_log.do")
    public String learnLog(HttpSession session, Model model) {
        IndexUser indexUser = (IndexUser) session.getAttribute("indexUser");

        // 查询历史记录
        QueryWrapper<IndexLearnLog> indexLearnLogQueryWrapper = new QueryWrapper<>();
        indexLearnLogQueryWrapper.eq("user_no", indexUser.getUserNo()).orderByDesc("add_time");
        List<IndexLearnLog> indexLearnLogList = indexLearnLogService.list(indexLearnLogQueryWrapper);

        // 组装数据
        List<LearnLogPOJO> learnLogPOJOList = Lists.newArrayList();
        for (IndexLearnLog indexLearnLog : indexLearnLogList) {
            LearnLogPOJO learnLogPOJO = new LearnLogPOJO();
            learnLogPOJO.setIndexLearnLog(indexLearnLog);

            QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
            indexCourseQueryWrapper.eq("course_no", indexLearnLog.getCourseNo());
            learnLogPOJO.setIndexCourse(indexCourseService.getOne(indexCourseQueryWrapper));

            QueryWrapper<IndexChapter> indexChapterQueryWrapper = new QueryWrapper<>();
            indexChapterQueryWrapper.eq("chapter_no", indexLearnLog.getChapterNo());
            learnLogPOJO.setIndexChapter(indexChapterService.getOne(indexChapterQueryWrapper));

            QueryWrapper<IndexLesson> indexLessonQueryWrapper = new QueryWrapper<>();
            indexLessonQueryWrapper.eq("lesson_no", indexLearnLog.getLessonNo());
            learnLogPOJO.setIndexLesson(indexLessonService.getOne(indexLessonQueryWrapper));

            learnLogPOJOList.add(learnLogPOJO);
        }

        // 绑定数据
        model.addAttribute("indexLearnLogList", indexLearnLogList);
        model.addAttribute("learnLogPOJOList", learnLogPOJOList);

        // 返回页面视图
        return "Index/User/learn_log";
    }
}
