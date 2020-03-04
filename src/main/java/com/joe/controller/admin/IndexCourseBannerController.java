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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.*;
import com.joe.pojo.Page;
import com.joe.service.common.PageService;
import com.joe.service.system.AdminDictService;
import com.joe.service.system.IndexCourseBannerService;
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
@RequestMapping("/admin/course_banner")
public class IndexCourseBannerController {
    private Logger logger = LoggerFactory.getLogger(IndexCourseController.class);

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
        List<IndexCourseBanner> courseBannerList = indexCourseBannerService.listCourseBanner(conditionMap, pageInfoMap);

        // 查询课程推荐位状态列表
        List<AdminDict> courseBannerStatusAdminDictList = adminDictService.getDictListByDictName("courseBannerStatus");

        // 数据绑定
        model.addAttribute("courseBannerList", courseBannerList);
        model.addAttribute("courseBannerStatusAdminDictList", courseBannerStatusAdminDictList);
        model.addAttribute("page", page);
        model.addAttribute("conditions", conditionMap);

        // 返回页面视图
        return "Admin/CourseBanner/index";
    }

    /**
     * 课程推荐位新增页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("add_page.do")
    public String addPage(Model model) {
        // 查询课程推荐位状态列表
        List<AdminDict> courseBannerStatusAdminDictList = adminDictService.getDictListByDictName("courseBannerStatus");

        // 数据绑定
        model.addAttribute("courseBannerStatusAdminDictList", courseBannerStatusAdminDictList);

        return "Admin/CourseBanner/add";
    }

    /**
     * 添加课程推荐位
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("add.do")
    @ResponseBody
    public AppResponse<IndexCourseBanner> add(String data, HttpSession session) {
        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        IndexCourseBanner indexCourseBanner = gson.fromJson(data, new TypeToken<IndexCourseBanner>() {
        }.getType());

        // 课程编号
        indexCourseBanner.setCourseBannerNo(CommonFunctions.generateNo("ICBNO"));
        // 课程状态-未审核
        indexCourseBanner.setCourseBannerStatus("0");

        // 操作信息
        indexCourseBanner.setAddUserNo("1");
        indexCourseBanner.setAddTime(new Date());
        indexCourseBanner.setUpdateUserNo("1");
        indexCourseBanner.setUpdateTime(new Date());

        // 新增数据
        boolean rel = indexCourseBannerService.save(indexCourseBanner);
        if (rel) {
            return AppResponse.success("课程推荐位新增成功！");
        }

        // 操作失败
        return AppResponse.fail("课程推荐位新增失败，请刷新页面后重新提交！");
    }

    /**
     * 课程推荐位编辑页面
     *
     * @param model          model
     * @param courseBannerNo 推荐位编号
     * @return 返回页面视图
     */
    @RequestMapping("edit_page.do")
    public String editPage(Model model, String courseBannerNo) {
        // 根据字典编号查询
        QueryWrapper<IndexCourseBanner> indexCourseBannerQueryWrapper = new QueryWrapper<>();
        indexCourseBannerQueryWrapper.eq("course_banner_no", courseBannerNo);
        IndexCourseBanner indexCourseBanner = indexCourseBannerService.getOne(indexCourseBannerQueryWrapper);

        // 查询课程推荐位状态列表
        List<AdminDict> courseBannerStatusAdminDictList = adminDictService.getDictListByDictName("courseBannerStatus");

        // 绑定数据
        model.addAttribute("indexCourseBanner", indexCourseBanner);
        model.addAttribute("courseBannerStatusAdminDictList", courseBannerStatusAdminDictList);

        return "Admin/CourseBanner/edit";
    }

    /**
     * 编辑课程推荐位
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AppResponse<IndexCourse> edit(String data, HttpSession session) {
        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        IndexCourseBanner indexCourseBanner = gson.fromJson(data, new TypeToken<IndexCourseBanner>() {
        }.getType());

        indexCourseBanner.setCourseBannerStatus("0");

        // 更新操作信息
        indexCourseBanner.setUpdateUserNo("1l");
        indexCourseBanner.setUpdateTime(new Date());

        // 更新数据
        boolean rel = indexCourseBannerService.updateById(indexCourseBanner);
        if (rel) {
            return AppResponse.success("课程推荐位编辑成功！");
        }

        // 操作做失败
        return AppResponse.fail("课程推荐位编辑失败，请刷新页面后重新提交！");
    }

    /**
     * 删除课程推荐位
     *
     * @param courseBannerNo 课程推荐位编号
     * @return 返回操作结果
     */
    @RequestMapping("/delete.do")
    @ResponseBody
    public AppResponse<IndexCourse> deleteCourse(String courseBannerNo) {
        // 根据课程编号查询
        QueryWrapper<IndexCourseBanner> indexCourseBannerQueryWrapper = new QueryWrapper<>();
        indexCourseBannerQueryWrapper.eq("course_banner_no", courseBannerNo);
        IndexCourseBanner indexCourseBanner = indexCourseBannerService.getOne(indexCourseBannerQueryWrapper);

        if (null == indexCourseBanner) {
            return AppResponse.fail("课程推荐位删除失败，请刷新页面后重试！");
        }

        // 删除课程
        indexCourseBannerService.removeById(indexCourseBanner);

        return AppResponse.success("课程推荐位删除成功！");
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
            String filePath = request.getServletContext().getRealPath("/") + "file_upload" + File.separator + "course_banner" + File.separator + fileName;

            // 转存文件
            try {
                multipartFile.transferTo(new File(filePath));
            } catch (IOException e) {
                logger.error(e.getMessage());
                return AppResponse.fail("文件上传失败！");
            }

            return AppResponse.success("上传成功！", File.separator + "file_upload" + File.separator + "course_banner" + File.separator + fileName);
        }

        logger.error("文件不存在！");
        return AppResponse.fail("文件上传失败！");
    }

    /**
     * 检查课程推荐位标题名称是否合法
     *
     * @param courseBannerNo   课程推荐位编号
     * @param courseBannerName 课程推荐位名称
     * @return 返回检查结果
     */
    @RequestMapping("check_course_banner_name.do")
    @ResponseBody
    public AppResponse<String> checkCourseName(String courseBannerNo, String courseBannerName) {
        // 查询出courseNo不是当前记录的但是课程名称相同的所有的记录
        QueryWrapper<IndexCourseBanner> indexCourseBannerQueryWrapper = new QueryWrapper<>();
        indexCourseBannerQueryWrapper.ne("course_banner_no", courseBannerNo).eq("course_banner_name", courseBannerName);
        List<IndexCourseBanner> indexCourseBannerList = indexCourseBannerService.list(indexCourseBannerQueryWrapper);

        // 名称可用
        if (indexCourseBannerList.isEmpty()) {
            return AppResponse.success("课程推荐位名称可用！", "true");
        }

        return AppResponse.success("课程名称不可用！", "false");
    }
}
