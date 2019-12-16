package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/17
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 课程标签
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.IndexCourseTag;
import com.joe.pojo.Page;
import com.joe.service.common.PageService;
import com.joe.service.system.IndexCourseTagService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/admin/course_tag")
public class IndexCourseTagController {
    private Logger logger = LoggerFactory.getLogger(IndexCourseTagController.class);

    @Resource
    private IndexCourseTagService indexCourseTagService;

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
        QueryWrapper<IndexCourseTag> indexCourseTagQueryWrapper = new QueryWrapper<>();

        Gson gson = new Gson();
        Map<String, String> conditionMap = gson.fromJson(conditions, new TypeToken<Map<String, String>>() {
        }.getType());

        // 课程标签名模糊查询
        if (null != conditionMap) {
            if (conditionMap.containsKey("courseTagName") && StringUtils.isNoneEmpty(conditionMap.get("courseTagName"))) {
                indexCourseTagQueryWrapper.like("course_tag_name", conditionMap.get("courseTagName"));
            }
        }
        // 查询条数
        int indexCourseTagNum = indexCourseTagService.count(indexCourseTagQueryWrapper);

        // 分页
        Page page = pageService.Pagination(currentPage, indexCourseTagNum);

        // 获取用户列表
        int start = page.getRecordNum() * (page.getCurrentPage() - 1);
        indexCourseTagQueryWrapper.orderByDesc("course_tag_no").last("limit " + start + "," + page.getRecordNum());
        List<IndexCourseTag> indexCourseTagList = indexCourseTagService.list(indexCourseTagQueryWrapper);

        // 绑定数据
        model.addAttribute("indexCourseTagList", indexCourseTagList);
        model.addAttribute("page", page);
        model.addAttribute("conditions", conditionMap);

        // 返回视图页面
        return "Admin/CourseTag/index";
    }

    /**
     * 课程标签新增页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("add_page.do")
    public String addPage() {
        // 返回页面视图
        return "Admin/CourseTag/add";
    }

    /**
     * 添加用户
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("add.do")
    @ResponseBody
    public AppResponse<IndexCourseTag> add(String data, HttpSession session) {
        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        IndexCourseTag indexCourseTag = gson.fromJson(data, new TypeToken<IndexCourseTag>() {
        }.getType());

        // 课程标签编号
        indexCourseTag.setCourseTagNo(CommonFunctions.generateNo("ICTNO"));

        // 操作信息
        indexCourseTag.setAddUserNo("1");
        indexCourseTag.setAddTime(new Date());
        indexCourseTag.setUpdateUserNo("1");
        indexCourseTag.setUpdateTime(new Date());

        // 新增数据
        boolean rel = indexCourseTagService.save(indexCourseTag);
        if (rel) {
            return AppResponse.success("课程标签新增成功！");
        }

        // 操作失败
        return AppResponse.fail("课程标签新增失败，请刷新页面后重新提交！");
    }

    /**
     * 课程标签编辑页面
     *
     * @param model  model
     * @param courseTagNo 课程标签编号
     * @return 返回页面视图
     */
    @RequestMapping("edit_page.do")
    public String editPage(Model model, String courseTagNo) {
        // 根据用户编号查询
        QueryWrapper<IndexCourseTag> indexCourseTagQueryWrapper = new QueryWrapper<>();
        indexCourseTagQueryWrapper.eq("course_tag_no", courseTagNo);
        IndexCourseTag indexCourseTag = indexCourseTagService.getOne(indexCourseTagQueryWrapper);

        // 绑定数据
        model.addAttribute("indexCourseTag", indexCourseTag);

        return "Admin/CourseTag/edit";
    }

    /**
     * 编辑课程标签
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AppResponse<IndexCourseTag> edit(String data, HttpSession session) {
        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        IndexCourseTag indexCourseTag = gson.fromJson(data, new TypeToken<IndexCourseTag>() {
        }.getType());

        // 更新操作信息
        indexCourseTag.setUpdateUserNo("1l");
        indexCourseTag.setUpdateTime(new Date());

        // 更新数据
        boolean rel = indexCourseTagService.updateById(indexCourseTag);
        if (rel) {
            return AppResponse.success("课程标签编辑成功！");
        }

        // 操作做失败
        return AppResponse.fail("课程标签编辑失败，请刷新页面后重新提交！");
    }

    /**
     * 删除课程标签
     *
     * @param courseTagNo 课程标签编号
     * @return 返回操作结果
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public AppResponse<IndexCourseTag> deleteCourseTag(String courseTagNo) {
        // 根据课程标签编号查询
        QueryWrapper<IndexCourseTag> indexCourseTagQueryWrapper = new QueryWrapper<>();
        indexCourseTagQueryWrapper.eq("course_tag_no", courseTagNo);
        IndexCourseTag indexCourseTag = indexCourseTagService.getOne(indexCourseTagQueryWrapper);

        // 判断课程标签是否存在
        if (null == indexCourseTag) {
            return AppResponse.fail("删除课程标签失败，请刷新页面后重新操作！");
        } else {
            indexCourseTagService.removeById(indexCourseTag);
            return AppResponse.success("删除课程标签成功！");
        }
    }

    /**
     * 批量删除
     *
     * @param courseTagNos 课程标签编号串
     * @return 返回操作结果
     */
    @RequestMapping("/delete_batch.do")
    @ResponseBody
    public AppResponse<IndexCourseTag> deleteBatch(String courseTagNos) {
        String[] courseTagNoArr = courseTagNos.split(",");

        List<Long> courseTagIdList = Lists.newArrayList();

        // 查询要删除的课程标签列表
        for (String courseTagNo : courseTagNoArr) {
            QueryWrapper<IndexCourseTag> indexCourseTagQueryWrapper = new QueryWrapper<>();
            indexCourseTagQueryWrapper.eq("course_tag_no", courseTagNo);
            IndexCourseTag indexCourseTag = indexCourseTagService.getOne(indexCourseTagQueryWrapper);
            if (null == indexCourseTag) {
                return AppResponse.fail("删除课程标签失败，请刷新页面后重新操作！");
            } else {
                courseTagIdList.add(indexCourseTag.getCourseTagId());
            }
        }

        // 删除课程标签
        if (courseTagIdList.size() == courseTagNoArr.length) {
            indexCourseTagService.removeByIds(courseTagIdList);
            return AppResponse.success("删除课程标签成功！");
        } else {
            return AppResponse.fail("删除课程标签失败，请刷新页面后重新操作！");
        }
    }

    /**
     * 检查课程标签是否合法
     *
     * @param courseTagNo   课程标签编号
     * @param courseTagName 课程标签名称
     * @return 返回检查结果
     */
    @RequestMapping("check_course_tag_name.do")
    @ResponseBody
    public AppResponse<String> checkCourseTagName(String courseTagNo, String courseTagName) {
        // 查询出courseCateNo不是当前记录的但是分类名称相同的所有的记录
        QueryWrapper<IndexCourseTag> indexCourseTagQueryWrapper = new QueryWrapper<>();
        indexCourseTagQueryWrapper.ne("course_tag_no", courseTagNo).eq("course_tag_name", courseTagName);
        List<IndexCourseTag> indexCourseTagList = indexCourseTagService.list(indexCourseTagQueryWrapper);

        // 名称可用
        if (indexCourseTagList.isEmpty()) {
            return AppResponse.success("课程标签可用！", "true");
        }

        return AppResponse.success("课程标签不可用！", "false");
    }
}
