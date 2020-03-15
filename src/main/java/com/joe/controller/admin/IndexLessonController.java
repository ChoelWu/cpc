package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/12/2
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 课时管理
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
import com.joe.service.system.AdminDictService;
import com.joe.service.system.IndexChapterService;
import com.joe.service.system.IndexLessonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/lesson")
public class IndexLessonController {
    private Logger logger = LoggerFactory.getLogger(IndexLessonController.class);

    @Resource
    private IndexLessonService indexLessonService;

    @Resource
    private AdminDictService adminDictService;

    @Resource
    private IndexChapterService indexChapterService;

    /**
     * 课时新增页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("add_page.do")
    public String addPage(Model model, String chapterNo) {
        // 查询可是类型
        List<AdminDict> lessonTypeAdminDictList = adminDictService.getDictListByDictName("lessonType");

        QueryWrapper<IndexChapter> indexChapterQueryWrapper = new QueryWrapper<>();
        indexChapterQueryWrapper.eq("chapter_no", chapterNo);
        IndexChapter indexChapter = indexChapterService.getOne(indexChapterQueryWrapper);

        // 绑定数据
        model.addAttribute("lessonTypeAdminDictList", lessonTypeAdminDictList);
        model.addAttribute("chapterNo", chapterNo);
        model.addAttribute("courseNo", indexChapter.getCourseNo());

        // 返回页面视图
        return "Admin/Lesson/add";
    }

    /**
     * 添加课时
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("add.do")
    @ResponseBody
    public AppResponse<IndexLesson> add(String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        IndexLesson indexLesson = gson.fromJson(data, new TypeToken<IndexLesson>() {
        }.getType());

        // 课时编号
        indexLesson.setLessonNo(CommonFunctions.generateNo("ILNO"));

        // 操作信息
        indexLesson.setAddUserNo(adminUser.getUserNo());
        indexLesson.setAddTime(new Date());
        indexLesson.setUpdateUserNo(adminUser.getUserNo());
        indexLesson.setUpdateTime(new Date());

        // 新增数据
        boolean rel = indexLessonService.save(indexLesson);
        if (rel) {
            return AppResponse.success("课时新增成功！");
        }

        // 操作失败
        return AppResponse.fail("课时新增失败，请刷新页面后重新提交！");
    }

    /**
     * 课时编辑页面
     *
     * @param model    model
     * @param lessonNo 课时编号
     * @return 返回页面视图
     */
    @RequestMapping("edit_page.do")
    public String editPage(Model model, String lessonNo) {
        // 根据课时编号查询
        QueryWrapper<IndexLesson> indexLessonQueryWrapper = new QueryWrapper<>();
        indexLessonQueryWrapper.eq("lesson_no", lessonNo);
        IndexLesson indexLesson = indexLessonService.getOne(indexLessonQueryWrapper);

        // 查询课时类型
        List<AdminDict> lessonTypeAdminDictList = adminDictService.getDictListByDictName("lessonType");

        // 绑定数据
        model.addAttribute("indexLesson", indexLesson);
        model.addAttribute("lessonTypeAdminDictList", lessonTypeAdminDictList);

        return "Admin/Lesson/edit";
    }

    /**
     * 编辑课时
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AppResponse<IndexLesson> edit(String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        IndexLesson indexLesson = gson.fromJson(data, new TypeToken<IndexLesson>() {
        }.getType());

        // 更新操作信息
        indexLesson.setUpdateUserNo(adminUser.getUserNo());
        indexLesson.setUpdateTime(new Date());

        // 更新数据
        boolean rel = indexLessonService.updateById(indexLesson);
        if (rel) {
            return AppResponse.success("课时编辑成功！");
        }

        // 操作做失败
        return AppResponse.fail("课时编辑失败，请刷新页面后重新提交！");
    }

    /**
     * 删除课时
     *
     * @param lessonNo 课时编号
     * @return 返回操作结果
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public AppResponse<IndexLesson> deleteLesson(String lessonNo) {
        // 根据课时编号查询
        QueryWrapper<IndexLesson> indexLessonQueryWrapper = new QueryWrapper<>();
        indexLessonQueryWrapper.eq("lesson_no", lessonNo);
        IndexLesson indexLesson = indexLessonService.getOne(indexLessonQueryWrapper);

        // 判断课时是否存在
        if (null == indexLesson) {
            return AppResponse.fail("删除课时失败，请刷新页面后重新操作！");
        } else {
            indexLessonService.removeById(indexLesson);
            return AppResponse.success("删除课时成功！");
        }
    }

    /**
     * 批量删除
     *
     * @param lessonNos 课时编号串
     * @return 返回操作结果
     */
    @RequestMapping("/delete_batch.do")
    @ResponseBody
    public AppResponse<IndexLesson> deleteBatch(String lessonNos) {
        String[] lessonNoArr = lessonNos.split(",");

        List<Long> lessonIdList = Lists.newArrayList();

        // 查询要删除的课时列表
        for (String lessonNo : lessonNoArr) {
            QueryWrapper<IndexLesson> indexLessonQueryWrapper = new QueryWrapper<>();
            indexLessonQueryWrapper.eq("lesson_no", lessonNo);
            IndexLesson indexLesson = indexLessonService.getOne(indexLessonQueryWrapper);
            if (null == indexLesson) {
                return AppResponse.fail("删除课时失败，请刷新页面后重新操作！");
            } else {
                lessonIdList.add(indexLesson.getLessonId());
            }
        }

        // 删除课时
        if (lessonIdList.size() == lessonNoArr.length) {
            indexLessonService.removeByIds(lessonIdList);
            return AppResponse.success("删除课时成功！");
        } else {
            return AppResponse.fail("删除课时失败，请刷新页面后重新操作！");
        }
    }

    /**
     * 视频上传
     *
     * @param multipartVideo 视频文件
     * @param request        request
     * @return 返回上传结果
     */
    @RequestMapping("/upload_video.do")
    @ResponseBody
    public AppResponse<String> uploadVideo(MultipartFile multipartVideo, String courseNo, String chapterNo, HttpServletRequest request) {
        if (!multipartVideo.isEmpty()) {
            // 获取文件名
            String originFileName = multipartVideo.getOriginalFilename();
            String fileName = CommonFunctions.getRandomFileName(originFileName.substring(originFileName.lastIndexOf(".")));
            // 设置文件的保存路径
            String filePath = request.getServletContext().getRealPath("/") + "upload" + File.separator + "video" + File.separator + courseNo + File.separator + chapterNo + File.separator + fileName;

            // 转存文件
            try {
                multipartVideo.transferTo(new File(filePath));
            } catch (IOException e) {
                logger.error(e.getMessage());
                return AppResponse.fail("视频文件上传失败！");
            }

            return AppResponse.success("视频上传成功！", File.separator + "upload" + File.separator + "video" + File.separator + courseNo + File.separator + chapterNo + File.separator + fileName);
        }

        logger.error("视频文件不存在！");
        return AppResponse.fail("视频文件上传失败！");
    }

    /**
     * 动画上传
     *
     * @param multipartSwf 动画文件
     * @param request      request
     * @return 返回上传结果
     */
    @RequestMapping("/upload_swf.do")
    @ResponseBody
    public AppResponse<String> uploadSwf(MultipartFile multipartSwf, String courseNo, String chapterNo, HttpServletRequest request) {
        if (!multipartSwf.isEmpty()) {
            // 获取文件名
            String originFileName = multipartSwf.getOriginalFilename();
            String fileName = CommonFunctions.getRandomFileName(originFileName.substring(originFileName.lastIndexOf(".")));
            // 设置文件的保存路径
            String filePath = request.getServletContext().getRealPath("/") + "upload" + File.separator + "flash" + File.separator + courseNo + File.separator + chapterNo + File.separator + fileName;

            // 转存文件
            try {
                multipartSwf.transferTo(new File(filePath));
            } catch (IOException e) {
                logger.error(e.getMessage());
                return AppResponse.fail("动画文件上传失败！");
            }

            return AppResponse.success("动画上传成功！", File.separator + "upload" + File.separator + "flash" + File.separator + courseNo + File.separator + chapterNo + File.separator + fileName);
        }

        logger.error("动画文件不存在！");
        return AppResponse.fail("动画文件上传失败！");
    }
}
