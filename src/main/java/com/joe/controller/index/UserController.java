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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.*;
import com.joe.pojo.CourseLogPOJO;
import com.joe.pojo.LearnLogPOJO;
import com.joe.pojo.Page;
import com.joe.service.common.PageService;
import com.joe.service.system.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    @Resource
    private PageService pageService;

    @Resource
    private IndexUserService indexUserService;

    /**
     * 用户中心
     *
     * @param session     session
     * @param model       model
     * @param currentPage 当前页
     * @return 返回页面视图
     */
    @RequestMapping("/index.do")
    public String view(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1") int currentPage) {
        IndexUser indexUser = (IndexUser) session.getAttribute("indexUser");

        // 查询课时记录，倒序排序
        QueryWrapper<IndexUserCourse> indexUserCourseQueryWrapper = new QueryWrapper<>();
        indexUserCourseQueryWrapper.eq("user_no", indexUser.getUserNo()).orderByDesc("last_time");

        int userCourseNum = indexUserCourseService.count(indexUserCourseQueryWrapper);

        // 分页
        Page page = pageService.Pagination(currentPage, userCourseNum, 10);
        int start = page.getRecordNum() * (page.getCurrentPage() - 1);

        indexUserCourseQueryWrapper.last("limit " + start + "," + page.getRecordNum());

        List<IndexUserCourse> indexUserCourseList = indexUserCourseService.list(indexUserCourseQueryWrapper);

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
        model.addAttribute("page", page);

        // 返回页面视图
        return "Index/User/index";
    }

    /**
     * 学习日志
     *
     * @param session     session
     * @param model       model
     * @param currentPage 当前页
     * @return 返回页面视图
     */
    @RequestMapping("/learn_log.do")
    public String learnLog(HttpSession session, Model model, @RequestParam(required = false, defaultValue = "1") int currentPage) {
        IndexUser indexUser = (IndexUser) session.getAttribute("indexUser");

        // 查询历史记录
        QueryWrapper<IndexLearnLog> indexLearnLogQueryWrapper = new QueryWrapper<>();
        indexLearnLogQueryWrapper.eq("user_no", indexUser.getUserNo()).orderByDesc("add_time");

        int learnLogNum = indexLearnLogService.count(indexLearnLogQueryWrapper);

        // 分页
        Page page = pageService.Pagination(currentPage, learnLogNum, 20);
        int start = page.getRecordNum() * (page.getCurrentPage() - 1);

        indexLearnLogQueryWrapper.last("limit " + start + "," + page.getRecordNum());

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
        model.addAttribute("page", page);

        // 返回页面视图
        return "Index/User/learn_log";
    }

    /**
     * 用户信息配置
     *
     * @param session session
     * @param model   model
     * @return 返回页面视图
     */
    @RequestMapping("config.do")
    public String config(HttpSession session, Model model) {
        IndexUser indexUser = (IndexUser) session.getAttribute("indexUser");
        model.addAttribute("indexUser", indexUser);
        return "Index/User/config";
    }

    /**
     * 修改用户信息
     *
     * @param data    前端数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("/modify_info.do")
    @ResponseBody
    public AppResponse<IndexUser> modifyInfo(String data, HttpSession session) {
        IndexUser indexUser = (IndexUser) session.getAttribute("indexUser");

        Gson gson = new Gson();
        Map<String, String> dataMap = gson.fromJson(data, new TypeToken<Map<String, String>>() {
        }.getType());

        if (StringUtils.equals("userPassword", dataMap.get("field"))) {
            if (StringUtils.equals(dataMap.get("password"), dataMap.get("rePassword"))) {
                indexUser.setUserPassword(CommonFunctions.encryptPassword(dataMap.get("password"), indexUser.getUserNo()));
            } else {
                return AppResponse.fail("密码不一致，请重新输入！");
            }
        } else if (StringUtils.equals("userEmail", dataMap.get("field"))) {
            indexUser.setUserEmail(dataMap.get("value"));
        } else if (StringUtils.equals("userMobile", dataMap.get("field"))) {
            indexUser.setUserMobile(dataMap.get("value"));
        } else if (StringUtils.equals("userName", dataMap.get("field"))) {
            indexUser.setUserName(dataMap.get("value"));
        }

        indexUser.setUpdateUserNo(indexUser.getUserNo());
        indexUser.setUpdateTime(new Date());
        indexUserService.updateById(indexUser);

        return AppResponse.success("用户信息修改成功！");
    }
}
