package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/25
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 课程管理
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.*;
import com.joe.pojo.Course;
import com.joe.pojo.CourseCate;
import com.joe.pojo.CourseTagsJson;
import com.joe.pojo.Page;
import com.joe.service.common.PageService;
import com.joe.service.system.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/course")
public class IndexCourseController {
    private Logger logger = LoggerFactory.getLogger(IndexCourseController.class);

    @Resource
    private IndexCourseService indexCourseService;

    @Resource
    private IndexCourseCateService indexCourseCateService;

    @Resource
    private IndexChapterService indexChapterService;

    @Resource
    private IndexLessonService indexLessonService;

    @Resource
    private PageService pageService;

    @Resource
    private AdminDictService adminDictService;

    @Resource
    private IndexCourseTagService indexCourseTagService;

    /**
     * 栏目列表显示
     *
     * @param model model
     * @return 返回页面
     */
    @RequestMapping("view.do")
    public String view(Model model, @RequestParam(required = false, defaultValue = "1") int currentPage, @RequestParam(required = false) String conditions) {
        Gson gson = new Gson();
        Map<String, Object> conditionMap = gson.fromJson(conditions, new TypeToken<Map<String, Object>>() {
        }.getType());

        // 查询条数
        int adminCourseNum = indexCourseService.countCourse(conditionMap);

        // 分页
        Page page = pageService.Pagination(currentPage, adminCourseNum, 10);

        // 获取用户列表
        int start = page.getRecordNum() * (page.getCurrentPage() - 1);
        Map<String, Integer> pageInfoMap = Maps.newHashMap();
        pageInfoMap.put("startRow", start);
        pageInfoMap.put("rowNum", page.getRecordNum());
        List<Course> courseList = indexCourseService.listCourse(conditionMap, pageInfoMap);

        // 查询出所有的菜单
        QueryWrapper<IndexCourseCate> indexCourseCateQueryWrapper = new QueryWrapper<>();
        indexCourseCateQueryWrapper.orderByAsc("course_cate_level", "course_cate_index");
        List<IndexCourseCate> indexCourseCateList = indexCourseCateService.list(indexCourseCateQueryWrapper);

        // 创建前台输出的菜单列表
        List<CourseCate> courseCateList = Lists.newArrayList();

        // 循环遍历菜单，生成前台需要的格式
        for (IndexCourseCate indexCourseCate : indexCourseCateList) {
            if (StringUtils.equals("1", indexCourseCate.getCourseCateLevel())) {
                // 创建菜单对象
                CourseCate courseCate = new CourseCate();
                // 子菜单列表
                List<IndexCourseCate> indexChildCourseCateList = Lists.newArrayList();

                // 循环组织子菜单
                for (IndexCourseCate indexChildCourseCate : indexCourseCateList) {
                    if (StringUtils.equals(indexCourseCate.getCourseCateNo(), indexChildCourseCate.getParentCourseCateNo())) {
                        indexChildCourseCateList.add(indexChildCourseCate);
                    }
                }

                // 存储父菜单
                courseCate.setIndexCourseCate(indexCourseCate);
                // 存储子菜单
                courseCate.setChildCourseCateList(indexChildCourseCateList);

                // 存储菜单
                courseCateList.add(courseCate);
            }
        }

        // 课程状态数据字典
        List<AdminDict> courseStatusAdminDictList = adminDictService.getDictListByDictName("courseStatus");
        // 课程是否制定数据字典
        List<AdminDict> isTopAdminDictList = adminDictService.getDictListByDictName("courseIsTop");

        // 绑定数据
        model.addAttribute("courseList", courseList);
        model.addAttribute("courseCateList", courseCateList);
        model.addAttribute("courseStatusAdminDictList", courseStatusAdminDictList);
        model.addAttribute("isTopAdminDictList", isTopAdminDictList);
        model.addAttribute("page", page);
        model.addAttribute("conditions", conditionMap);

        return "Admin/Course/index";
    }

    /**
     * 课程新增页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("add_page.do")
    public String addPage(Model model) {
        // 查询出所有的菜单
        QueryWrapper<IndexCourseCate> indexCourseCateQueryWrapper = new QueryWrapper<>();
        indexCourseCateQueryWrapper.orderByAsc("course_cate_level", "course_cate_index");
        List<IndexCourseCate> indexCourseCateList = indexCourseCateService.list(indexCourseCateQueryWrapper);

        // 创建前台输出的菜单列表
        List<CourseCate> courseCateList = Lists.newArrayList();

        // 循环遍历菜单，生成前台需要的格式
        for (IndexCourseCate indexCourseCate : indexCourseCateList) {
            if (StringUtils.equals("1", indexCourseCate.getCourseCateLevel())) {
                // 创建菜单对象
                CourseCate courseCate = new CourseCate();
                // 子菜单列表
                List<IndexCourseCate> indexChildCourseCateList = Lists.newArrayList();

                // 循环组织子菜单
                for (IndexCourseCate indexChildCourseCate : indexCourseCateList) {
                    if (StringUtils.equals(indexCourseCate.getCourseCateNo(), indexChildCourseCate.getParentCourseCateNo())) {
                        indexChildCourseCateList.add(indexChildCourseCate);
                    }
                }

                // 存储父菜单
                courseCate.setIndexCourseCate(indexCourseCate);
                // 存储子菜单
                courseCate.setChildCourseCateList(indexChildCourseCateList);

                // 存储菜单
                courseCateList.add(courseCate);
            }
        }

        // 课程是否置顶字典查询
        List<AdminDict> courseIsTopAdminDictList = adminDictService.getDictListByDictName("courseIsTop");
        // 课程是否置顶字典查询
        List<AdminDict> courseRoleAdminDictList = adminDictService.getDictListByDictName("courseRole");
        // 课程难度字典查询
        List<AdminDict> courseDifficultLevelAdminDictList = adminDictService.getDictListByDictName("courseDifficultLevel");

        // 数据绑定
        model.addAttribute("courseCateList", courseCateList);
        model.addAttribute("courseIsTopAdminDictList", courseIsTopAdminDictList);
        model.addAttribute("courseRoleAdminDictList", courseRoleAdminDictList);
        model.addAttribute("courseDifficultLevelAdminDictList", courseDifficultLevelAdminDictList);

        return "Admin/Course/add";
    }

    /**
     * 添加课程
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("add.do")
    @ResponseBody
    public AppResponse<IndexCourse> add(String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        IndexCourse indexCourse = gson.fromJson(data, new TypeToken<IndexCourse>() {
        }.getType());

        // 课程编号
        indexCourse.setCourseNo(CommonFunctions.generateNo("ICONO"));
        // 课程状态-未审核
        indexCourse.setCourseStatus("0");
        // 课程学习次数设置为 0
        indexCourse.setCourseLearnNum(0);

        // 操作信息
        indexCourse.setAddUserNo(adminUser.getUserNo());
        indexCourse.setAddTime(new Date());
        indexCourse.setUpdateUserNo(adminUser.getUserNo());
        indexCourse.setUpdateTime(new Date());

        // 新增数据
        boolean rel = indexCourseService.save(indexCourse);
        if (rel) {
            return AppResponse.success("课程新增成功！");
        }

        // 操作失败
        return AppResponse.fail("课程新增失败，请刷新页面后重新提交！");
    }

    /**
     * 字典编辑页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("edit_page.do")
    public String editPage(Model model, String courseNo) {
        // 根据字典编号查询
        QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
        indexCourseQueryWrapper.eq("course_no", courseNo);
        IndexCourse indexCourse = indexCourseService.getOne(indexCourseQueryWrapper);

        // 查询出所有的菜单
        QueryWrapper<IndexCourseCate> indexCourseCateQueryWrapper = new QueryWrapper<>();
        indexCourseCateQueryWrapper.orderByAsc("course_cate_level", "course_cate_index");
        List<IndexCourseCate> indexCourseCateList = indexCourseCateService.list(indexCourseCateQueryWrapper);

        // 创建前台输出的菜单列表
        List<CourseCate> courseCateList = Lists.newArrayList();

        // 循环遍历菜单，生成前台需要的格式
        for (IndexCourseCate indexCourseCate : indexCourseCateList) {
            if (StringUtils.equals("1", indexCourseCate.getCourseCateLevel())) {
                // 创建菜单对象
                CourseCate courseCate = new CourseCate();
                // 子菜单列表
                List<IndexCourseCate> indexChildCourseCateList = Lists.newArrayList();

                // 循环组织子菜单
                for (IndexCourseCate indexChildCourseCate : indexCourseCateList) {
                    if (StringUtils.equals(indexCourseCate.getCourseCateNo(), indexChildCourseCate.getParentCourseCateNo())) {
                        indexChildCourseCateList.add(indexChildCourseCate);
                    }
                }

                // 存储父菜单
                courseCate.setIndexCourseCate(indexCourseCate);
                // 存储子菜单
                courseCate.setChildCourseCateList(indexChildCourseCateList);

                // 存储菜单
                courseCateList.add(courseCate);
            }
        }

        // 课程角色权限
        List<String> roleList = Lists.newArrayList(indexCourse.getRole().split(","));

        // 课程是否置顶字典查询
        List<AdminDict> courseIsTopAdminDictList = adminDictService.getDictListByDictName("courseIsTop");
        // 课程是否置顶字典查询
        List<AdminDict> courseRoleAdminDictList = adminDictService.getDictListByDictName("courseRole");
        // 课程难度字典查询
        List<AdminDict> courseDifficultLevelAdminDictList = adminDictService.getDictListByDictName("courseDifficultLevel");

        // 绑定数据
        model.addAttribute("courseCateList", courseCateList);
        model.addAttribute("indexCourse", indexCourse);
        model.addAttribute("roleList", roleList);
        model.addAttribute("courseIsTopAdminDictList", courseIsTopAdminDictList);
        model.addAttribute("courseRoleAdminDictList", courseRoleAdminDictList);
        model.addAttribute("courseDifficultLevelAdminDictList", courseDifficultLevelAdminDictList);

        return "Admin/Course/edit";
    }

    /**
     * 添加课程
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AppResponse<IndexCourse> edit(String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        IndexCourse indexCourse = gson.fromJson(data, new TypeToken<IndexCourse>() {
        }.getType());

        indexCourse.setCourseStatus("0");

        // 更新操作信息
        indexCourse.setUpdateUserNo(adminUser.getUserNo());
        indexCourse.setUpdateTime(new Date());

        // 更新数据
        boolean rel = indexCourseService.updateById(indexCourse);
        if (rel) {
            return AppResponse.success("课程编辑成功！");
        }

        // 操作做失败
        return AppResponse.fail("课程编辑失败，请刷新页面后重新提交！");
    }

    /**
     * 删除课程
     *
     * @param courseNo 课程编号
     * @return 返回操作结果
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public AppResponse<IndexCourse> deleteCourse(String courseNo) {
        // 根据课程编号查询
        QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
        indexCourseQueryWrapper.eq("course_no", courseNo);
        IndexCourse indexCourse = indexCourseService.getOne(indexCourseQueryWrapper);

        if (null == indexCourse) {
            return AppResponse.fail("课程删除失败，请刷新页面后重试！");
        }

        // 删除课程章节
        QueryWrapper<IndexChapter> indexChapterQueryWrapper = new QueryWrapper<>();
        indexChapterQueryWrapper.eq("course_no", indexCourse.getCourseNo());
        indexChapterService.remove(indexChapterQueryWrapper);

        // 删除课程课时
        QueryWrapper<IndexLesson> indexLessonQueryWrapper = new QueryWrapper<>();
        indexLessonQueryWrapper.eq("course_no", indexCourse.getCourseNo());
        indexLessonService.remove(indexLessonQueryWrapper);

        // 删除课程
        indexCourseService.removeById(indexCourse);

        return AppResponse.success("课程删除成功！");
    }

    /**
     * 审核课程
     *
     * @param courseNo 课程编号
     * @return 返回操作结果
     */
    @RequestMapping("/enable.do")
    @ResponseBody
    public AppResponse<IndexCourse> enable(String courseNo) {
        // 查询用户
        QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
        indexCourseQueryWrapper.eq("course_no", courseNo).eq("course_status", "0");
        IndexCourse indexCourse = indexCourseService.getOne(indexCourseQueryWrapper);

        // 更改状态为未激活
        indexCourse.setCourseStatus("1");

        // 更新状态
        indexCourseService.updateById(indexCourse);
        return AppResponse.success("课程发布成功！");
    }

    /**
     * 取消审核
     *
     * @param courseNo 课程编号
     * @return 返回操作结果
     */
    @RequestMapping("/disable.do")
    @ResponseBody
    public AppResponse<IndexCourse> disable(String courseNo) {
        // 查询用户
        QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
        indexCourseQueryWrapper.eq("course_no", courseNo).ne("course_status", "0");
        IndexCourse indexCourse = indexCourseService.getOne(indexCourseQueryWrapper);

        // 更改状态为未激活
        indexCourse.setCourseStatus("0");

        // 更新状态
        indexCourseService.updateById(indexCourse);
        return AppResponse.success("课程取消发布成功！");
    }

    /**
     * 图片上传
     *
     * @param multipartFile 文件
     * @param request       request
     * @return 返回上传结果
     */
    @RequestMapping("/upload_cover.do")
    @ResponseBody
    public AppResponse<String> uploadCover(MultipartFile multipartFile, HttpServletRequest request) {
        if (!multipartFile.isEmpty()) {
            // 获取文件名
            String originFileName = multipartFile.getOriginalFilename();
            String fileName = CommonFunctions.getRandomFileName(originFileName.substring(originFileName.lastIndexOf(".")));
            // 设置文件的保存路径
            String filePath = request.getServletContext().getRealPath("/") + "file_upload" + File.separator + "course" + File.separator + fileName;

            // 转存文件
            try {
                multipartFile.transferTo(new File(filePath));
            } catch (IOException e) {
                logger.error(e.getMessage());
                return AppResponse.fail("文件上传失败！");
            }

            return AppResponse.success("上传成功！", File.separator + "file_upload" + File.separator + "course" + File.separator + fileName);
        }

        logger.error("文件不存在！");
        return AppResponse.fail("文件上传失败！");
    }

    /**
     * 检查课程名称是否合法
     *
     * @param courseNo   课程编号
     * @param courseName 课程名称
     * @return 返回检查结果
     */
    @RequestMapping("check_course_name.do")
    @ResponseBody
    public AppResponse<String> checkCourseName(String courseNo, String courseName) {
        // 查询出courseNo不是当前记录的但是课程名称相同的所有的记录
        QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
        indexCourseQueryWrapper.ne("course_no", courseNo).eq("course_name", courseName);
        List<IndexCourse> indexCourseList = indexCourseService.list(indexCourseQueryWrapper);

        // 名称可用
        if (indexCourseList.isEmpty()) {
            return AppResponse.success("课程名称可用！", "true");
        }

        return AppResponse.success("课程名称不可用！", "false");
    }

    /**
     * 标签配置
     *
     * @param courseNo 课程标签
     * @param model    model
     * @return 返回视图文件
     */
    @RequestMapping("course_tags_page.do")
    public String courseTagsPage(String courseNo, Model model) {
        Gson gson = new Gson();

        QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
        indexCourseQueryWrapper.eq("course_no", courseNo);
        IndexCourse indexCourse = indexCourseService.getOne(indexCourseQueryWrapper);

        String courseTags = "";
        if (null != indexCourse) {
            courseTags = indexCourse.getCourseTags();
            if (StringUtils.isBlank(indexCourse.getCourseTags())) {
                courseTags = "";
            }
        }

        // 课程已经选择的标签
        String[] courseTagArray = courseTags.split(",");

        // Json 化
        model.addAttribute("selectedCourseTag", gson.toJson(courseTagArray));

        // 所有的标签
        List<CourseTagsJson> indexCourseTagJsonList = Lists.newArrayList();
        List<IndexCourseTag> indexCourseTagList = indexCourseTagService.list();
        if (!indexCourseTagList.isEmpty()) {
            for (IndexCourseTag indexCourseTag : indexCourseTagList) {
                // 组织Json
                CourseTagsJson courseTagsJson = new CourseTagsJson();
                courseTagsJson.setValue(indexCourseTag.getCourseTagNo());
                courseTagsJson.setTitle(indexCourseTag.getCourseTagName());
                indexCourseTagJsonList.add(courseTagsJson);
            }
        } else {
            CourseTagsJson courseTagsJson = new CourseTagsJson();
            courseTagsJson.setValue("");
            courseTagsJson.setTitle("空");
            courseTagsJson.setDisabled(true);
            indexCourseTagJsonList.add(courseTagsJson);
        }

        String indexCourseTag = gson.toJson(indexCourseTagJsonList);
        model.addAttribute("indexCourseTag", indexCourseTag);
        model.addAttribute("courseNo", courseNo);

        // 返回视图页面
        return "Admin/Course/config_tags";
    }

    /**
     * 为课程添加标签
     *
     * @param courseTagNos 课程标签编号字符串
     * @param courseNo     课程编号
     * @return 返回操作结果
     */
    @RequestMapping("add_course_tags.do")
    @ResponseBody
    public AppResponse<IndexCourse> addCourseTags(String courseTagNos, String courseNo) {
        if (StringUtils.isNotBlank(courseTagNos) && StringUtils.isNotBlank(courseNo)) {
            Gson gson = new Gson();
            List<CourseTagsJson> courseTagsJsonList = gson.fromJson(courseTagNos, new TypeToken<List<CourseTagsJson>>() {
            }.getType());

            if (3 < courseTagsJsonList.size()) {
                return AppResponse.fail("标签添加失败，至多选择三个标签！");
            }

            StringBuilder courseTagsNoString = new StringBuilder();
            for (CourseTagsJson courseTagsJson : courseTagsJsonList) {
                // 标签更新使用次数
                String courseTagNo = courseTagsJson.getValue();
                QueryWrapper<IndexCourseTag> indexCourseTagQueryWrapper = new QueryWrapper<>();
                indexCourseTagQueryWrapper.eq("course_tag_no", courseTagNo);
                IndexCourseTag indexCourseTag = indexCourseTagService.getOne(indexCourseTagQueryWrapper);
                int courseTagUseNum = null == indexCourseTag.getCourseTagUseNum() ? 0 : indexCourseTag.getCourseTagUseNum();
                indexCourseTag.setCourseTagUseNum(courseTagUseNum + 1);
                indexCourseTagService.updateById(indexCourseTag);

                int index = courseTagsJsonList.indexOf(courseTagsJson);
                if (0 == index) {
                    courseTagsNoString.append(courseTagsJson.getValue());
                } else {
                    courseTagsNoString.append(",").append(courseTagsJson.getValue());
                }
            }

            QueryWrapper<IndexCourse> indexCourseQueryWrapper = new QueryWrapper<>();
            indexCourseQueryWrapper.eq("course_no", courseNo);
            IndexCourse indexCourse = indexCourseService.getOne(indexCourseQueryWrapper);

            if (null != indexCourse) {
                indexCourse.setCourseTags(courseTagsNoString.toString());
                indexCourseService.updateById(indexCourse);

                return AppResponse.success("标签添加成功！");
            }
        }

        return AppResponse.fail("标签添加失败！");
    }
}
