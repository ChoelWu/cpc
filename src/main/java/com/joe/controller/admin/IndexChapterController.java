package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/12/2
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 课程章节管理
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.AdminDict;
import com.joe.entity.AdminUser;
import com.joe.entity.IndexChapter;
import com.joe.entity.IndexLesson;
import com.joe.pojo.Chapter;
import com.joe.pojo.ChapterLesson;
import com.joe.service.system.AdminDictService;
import com.joe.service.system.IndexChapterService;
import com.joe.service.system.IndexLessonService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/chapter")
public class IndexChapterController {
    @Resource
    private IndexChapterService indexChapterService;

    @Resource
    private IndexLessonService indexLessonService;

    @Resource
    private AdminDictService adminDictService;

    /**
     * 栏目列表显示
     *
     * @param model model
     * @return 返回页面
     */
    @RequestMapping("view.do")
    public String view(Model model, String courseNo) {
        // 查询出所有的章节
        List<Chapter> chapterList = indexChapterService.listChapter(courseNo);

        // 查询出所有的课时，根据排序字段排序
        QueryWrapper<IndexLesson> indexLessonQueryWrapper = new QueryWrapper<>();
        indexLessonQueryWrapper.orderByAsc("lesson_index");
        List<IndexLesson> indexLessonList = indexLessonService.list(indexLessonQueryWrapper);

        // 创建前台输出的章节课程列表
        List<ChapterLesson> chapterLessonList = Lists.newArrayList();

        // 循环遍历章节和课时，生成前台需要的格式
        for (Chapter chapter : chapterList) {
            // 章节课时对象
            ChapterLesson chapterLesson = new ChapterLesson();
            // 初始化章节属性
            chapterLesson.setChapter(chapter);
            // 课时列表
            List<IndexLesson> indexLessons = Lists.newArrayList();
            // 循环课时List，找出当前章节的课时，形成列表
            for (IndexLesson indexLesson : indexLessonList) {
                if (StringUtils.equals(indexLesson.getChapterNo(), chapter.getChapterNo())) {
                    // 课时加入本章课时列表
                    indexLessons.add(indexLesson);
                }
            }
            // 将本章课时列表初始化
            chapterLesson.setIndexLessonList(indexLessons);
            // 将课时章节对象加入列表
            chapterLessonList.add(chapterLesson);
        }


        // 查询可是类型
        List<AdminDict> lessonTypeAdminDictList = adminDictService.getDictListByDictName("lessonType");

        // 绑定数据
        model.addAttribute("lessonTypeAdminDictList", lessonTypeAdminDictList);
        model.addAttribute("chapterLessonList", chapterLessonList);
        model.addAttribute("courseNo", courseNo);

        return "Admin/Chapter/index";
    }

    /**
     * 章节新增页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("add_page.do")
    public String addPage(Model model, String courseNo) {
        // 绑定章节编号
        model.addAttribute("courseNo", courseNo);
        // 返回页面视图
        return "Admin/Chapter/add";
    }

    /**
     * 添加章节
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("add.do")
    @ResponseBody
    public AppResponse<IndexChapter> add(String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        IndexChapter indexChapter = gson.fromJson(data, new TypeToken<IndexChapter>() {
        }.getType());

        // 章节编号
        indexChapter.setChapterNo(CommonFunctions.generateNo("ICHANO"));

        // 操作信息
        indexChapter.setAddUserNo(adminUser.getUserNo());
        indexChapter.setAddTime(new Date());
        indexChapter.setUpdateUserNo(adminUser.getUserNo());
        indexChapter.setUpdateTime(new Date());

        // 新增数据
        boolean rel = indexChapterService.save(indexChapter);
        if (rel) {
            return AppResponse.success("章节新增成功！");
        }

        // 操作失败
        return AppResponse.fail("章节新增失败，请刷新页面后重新提交！");
    }

    /**
     * 字典编辑页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("edit_page.do")
    public String editPage(Model model, String chapterNo) {

        // 根据字典编号查询
        QueryWrapper<IndexChapter> indexChapterQueryWrapper = new QueryWrapper<>();
        indexChapterQueryWrapper.eq("chapter_no", chapterNo);
        IndexChapter indexChapter = indexChapterService.getOne(indexChapterQueryWrapper);

        // 绑定数据
        model.addAttribute("indexChapter", indexChapter);
        return "Admin/Chapter/edit";
    }

    /**
     * 添加章节
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AppResponse<IndexChapter> edit(String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        IndexChapter indexChapter = gson.fromJson(data, new TypeToken<IndexChapter>() {
        }.getType());

        // 更新操作信息
        indexChapter.setUpdateUserNo(adminUser.getUserNo());
        indexChapter.setUpdateTime(new Date());

        // 更新数据
        boolean rel = indexChapterService.updateById(indexChapter);
        if (rel) {
            return AppResponse.success("章节编辑成功！");
        }

        // 操作做失败
        return AppResponse.fail("章节编辑失败，请刷新页面后重新提交！");
    }

    /**
     * 删除章节
     *
     * @param chapterNo 章节编号
     * @return 返回操作结果
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public AppResponse<IndexChapter> deleteChapter(String chapterNo) {
        // 根据课程编号查询
        QueryWrapper<IndexChapter> indexChapterQueryWrapper = new QueryWrapper<>();
        indexChapterQueryWrapper.eq("chapter_no", chapterNo);
        IndexChapter indexChapter = indexChapterService.getOne(indexChapterQueryWrapper);

        if (null == indexChapter) {
            return AppResponse.fail("章节删除失败，请刷新页面后重试！");
        }

        // 删除课程课时
        QueryWrapper<IndexLesson> indexLessonQueryWrapper = new QueryWrapper<>();
        indexLessonQueryWrapper.eq("chapter_no", indexChapter.getChapterNo());
        indexLessonService.remove(indexLessonQueryWrapper);

        // 删除章节
        indexChapterService.removeById(indexChapter);

        return AppResponse.success("章节删除成功！");
    }
}
