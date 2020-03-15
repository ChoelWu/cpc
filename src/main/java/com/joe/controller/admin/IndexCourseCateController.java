package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/25
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 课程分类管理
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.AdminDict;
import com.joe.entity.AdminUser;
import com.joe.entity.IndexCourseCate;
import com.joe.pojo.CourseCate;
import com.joe.service.system.AdminDictService;
import com.joe.service.system.IndexCourseCateService;
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

@RequestMapping("/admin/course_cate")
@Controller
public class IndexCourseCateController {
    private Logger logger = LoggerFactory.getLogger(IndexCourseCateController.class);

    @Resource
    private IndexCourseCateService indexCourseCateService;

    @Resource
    private AdminDictService adminDictService;

    /**
     * 课程分类列表显示
     *
     * @param model model
     * @return 返回页面
     */
    @RequestMapping("view.do")
    public String view(Model model) {
        // 查询出所有的课程分类
        QueryWrapper<IndexCourseCate> indexCourseCateQueryWrapper = new QueryWrapper<>();
        indexCourseCateQueryWrapper.orderByAsc("course_cate_level", "course_cate_index");
        List<IndexCourseCate> indexCourseCateList = indexCourseCateService.list(indexCourseCateQueryWrapper);

        // 创建前台输出的课程分类列表
        List<CourseCate> courseCateList = Lists.newArrayList();

        // 循环遍历课程分类，生成前台需要的格式
        for (IndexCourseCate indexCourseCate : indexCourseCateList) {
            if (StringUtils.equals("1", indexCourseCate.getCourseCateLevel())) {
                // 创建课程分类对象
                CourseCate courseCate = new CourseCate();
                // 子课程分类列表
                List<IndexCourseCate> adminChildCourseCateList = Lists.newArrayList();

                // 循环组织子课程分类
                for (IndexCourseCate adminChildCourseCate : indexCourseCateList) {
                    if (StringUtils.equals(indexCourseCate.getCourseCateNo(), adminChildCourseCate.getParentCourseCateNo())) {
                        adminChildCourseCateList.add(adminChildCourseCate);
                    }
                }

                // 存储父课程分类
                courseCate.setIndexCourseCate(indexCourseCate);
                // 存储子课程分类
                courseCate.setChildCourseCateList(adminChildCourseCateList);

                // 存储课程分类
                courseCateList.add(courseCate);
            }
        }

        // 课程分类类型数据字典
        List<AdminDict> courseCateLevelDictList = adminDictService.getDictListByDictName("courseCateLevel");

        // 绑定课程分类数据到前台
        model.addAttribute("courseCateList", courseCateList);
        model.addAttribute("courseCateLevelDictList", courseCateLevelDictList);

        // 返回页面
        return "Admin/CourseCate/index";
    }

    /**
     * 新增页面显示
     *
     * @return 返回新增页面
     */
    @RequestMapping("add_page.do")
    public String addPage(Model model) {
        // 所有启用的父级课程分类列表
        QueryWrapper<IndexCourseCate> indexCourseCateQueryWrapper = new QueryWrapper<>();
        indexCourseCateQueryWrapper.eq("course_cate_level", "1").orderByAsc("course_cate_index");
        List<IndexCourseCate> parentIndexCourseCateList = indexCourseCateService.list(indexCourseCateQueryWrapper);

        // 课程分类类型数据字典
        List<AdminDict> courseCateLevelDictList = adminDictService.getDictListByDictName("courseCateLevel");

        // 绑定数据到前台
        model.addAttribute("courseCateLevelDictList", courseCateLevelDictList);
        model.addAttribute("parentIndexCourseCateList", parentIndexCourseCateList);

        // 返回页面
        return "Admin/CourseCate/add";
    }

    /**
     * 新增课程分类
     *
     * @param data    前端表单数据
     * @param session session
     * @return 返回新增结果
     */
    @RequestMapping("add.do")
    @ResponseBody
    public AppResponse<IndexCourseCate> add(@RequestParam String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为对象
        Gson gson = new Gson();
        IndexCourseCate indexCourseCate = gson.fromJson(data, new TypeToken<IndexCourseCate>() {
        }.getType());

        // 课程分类编号
        indexCourseCate.setCourseCateNo(CommonFunctions.generateNo("ICCNO"));
        // 操作信息
        indexCourseCate.setAddUserNo(adminUser.getUserNo());
        indexCourseCate.setAddTime(new Date());
        indexCourseCate.setUpdateUserNo(adminUser.getUserNo());
        indexCourseCate.setUpdateTime(new Date());

        // 新增数据
        boolean rel = indexCourseCateService.save(indexCourseCate);
        if (rel) {
            return AppResponse.success("课程分类新增成功！");
        }

        // 操作失败
        return AppResponse.fail("课程分类新增失败，请刷新页面后重新提交！");
    }

    /**
     * 编辑页面显示
     *
     * @return 返回编辑页面
     */
    @RequestMapping("edit_page.do")
    public String editPage(String courseCateNo, Model model) {
        // 查询课程分类
        QueryWrapper<IndexCourseCate> indexCourseCateQueryWrapper = new QueryWrapper<>();
        indexCourseCateQueryWrapper.eq("course_cate_no", courseCateNo);
        IndexCourseCate indexCourseCate = indexCourseCateService.getOne(indexCourseCateQueryWrapper);

        // 所有启用的父级课程分类列表
        QueryWrapper<IndexCourseCate> indexCourseCateListQueryWrapper = new QueryWrapper<>();
        indexCourseCateListQueryWrapper.eq("course_cate_level", "1").orderByAsc("course_cate_index");
        List<IndexCourseCate> parentIndexCourseCateList = indexCourseCateService.list(indexCourseCateListQueryWrapper);

        // 查询是否有子课程分类
        QueryWrapper<IndexCourseCate> chileIndexCourseCateQueryWrapper = new QueryWrapper<>();
        chileIndexCourseCateQueryWrapper.eq("parent_course_cate_no", indexCourseCate.getCourseCateNo());
        List<IndexCourseCate> chileIndexCourseCateList = indexCourseCateService.list(chileIndexCourseCateQueryWrapper);
        boolean hasChild = !chileIndexCourseCateList.isEmpty();

        // 课程分类等级
        List<AdminDict> courseCateLevelDictList = adminDictService.getDictListByDictName("courseCateLevel");

        // 绑定数据到前台
        model.addAttribute("indexCourseCate", indexCourseCate);
        model.addAttribute("courseCateLevelDictList", courseCateLevelDictList);
        model.addAttribute("parentIndexCourseCateList", parentIndexCourseCateList);
        model.addAttribute("hasChild", hasChild);

        // 返回页面
        return "Admin/CourseCate/edit";
    }

    /**
     * 编辑课程分类
     *
     * @param data    表单数据
     * @param session session
     * @return 返回编辑修改结果
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AppResponse<IndexCourseCate> edit(String data, HttpSession session) {
        // 前台字符串数据转化为对象
        Gson gson = new Gson();
        IndexCourseCate indexCourseCate = gson.fromJson(data, new TypeToken<IndexCourseCate>() {
        }.getType());

        // 更新数据
        boolean rel = indexCourseCateService.updateById(indexCourseCate);
        if (rel) {
            return AppResponse.success("课程分类编辑成功！");
        }

        // 操作失败，返回
        return AppResponse.fail("课程分类编辑失败，请刷新页面后重新提交！");
    }

    /**
     * 删除课程分类
     *
     * @param courseCateNo 课程分类编号
     * @return 返回操作结果
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public AppResponse<IndexCourseCate> deleteCourseCate(String courseCateNo) {
        // 根据课程分类编号查询
        QueryWrapper<IndexCourseCate> indexCourseCateQueryWrapper = new QueryWrapper<>();
        indexCourseCateQueryWrapper.eq("course_cate_no", courseCateNo);
        IndexCourseCate indexCourseCate = indexCourseCateService.getOne(indexCourseCateQueryWrapper);

        // 判断课程分类是否存在
        if (null == indexCourseCate) {
            return AppResponse.fail("删除课程分类失败，请刷新页面后重新操作！");
        } else {
            // 检查是否存在在子课程分类
            QueryWrapper<IndexCourseCate> chileIndexCourseCateQueryWrapper = new QueryWrapper<>();
            chileIndexCourseCateQueryWrapper.eq("parent_course_cate_no", indexCourseCate.getCourseCateNo());
            List<IndexCourseCate> chileIndexCourseCateList = indexCourseCateService.list(chileIndexCourseCateQueryWrapper);

            // 如果没有子课程分类，允许删除，否则不允许删除
            if (chileIndexCourseCateList.isEmpty()) {
                indexCourseCateService.removeById(indexCourseCate);
                return AppResponse.success("删除课程分类成功！");
            } else {
                return AppResponse.fail("删除课程分类失败，该课程分类下存在子课程分类！");
            }
        }
    }

    /**
     * 更新课程分类的排序
     *
     * @param courseCateNo    课程分类编号
     * @param courseCateIndex 课程分类序号
     * @param session         session
     * @return 返回操作结果
     */
    @RequestMapping("update_index.do")
    @ResponseBody
    public AppResponse<IndexCourseCate> updateIndex(String courseCateNo, String courseCateIndex, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 根据课程分类编号查询
        QueryWrapper<IndexCourseCate> indexCourseCateQueryWrapper = new QueryWrapper<>();
        indexCourseCateQueryWrapper.eq("course_cate_no", courseCateNo);
        IndexCourseCate indexCourseCate = indexCourseCateService.getOne(indexCourseCateQueryWrapper);

        // 判断是不是数字
        if (StringUtils.isNumeric(courseCateIndex) && 4 == courseCateIndex.length()) {
            indexCourseCate.setCourseCateIndex(courseCateIndex);
            indexCourseCate.setUpdateUserNo(adminUser.getUserNo());
            indexCourseCate.setUpdateTime(new Date());
            // 更新
            indexCourseCateService.updateById(indexCourseCate);
            // 返回结果
            return AppResponse.success("索引修改成功！");
        }

        // 更新失败
        return AppResponse.fail("索引修改失败！");
    }

    /**
     * 检查分类名称是否合法
     *
     * @param courseCateNo   分类编号
     * @param courseCateName 分类名称
     * @return 返回检查结果
     */
    @RequestMapping("check_course_cate_name.do")
    @ResponseBody
    public AppResponse<String> checkCourseCateName(String courseCateNo, String courseCateName) {
        // 查询出courseCateNo不是当前记录的但是分类名称相同的所有的记录
        QueryWrapper<IndexCourseCate> indexCourseCateQueryWrapper = new QueryWrapper<>();
        indexCourseCateQueryWrapper.ne("course_cate_no", courseCateNo).eq("course_cate_name", courseCateName);
        List<IndexCourseCate> indexCourseCateList = indexCourseCateService.list(indexCourseCateQueryWrapper);

        // 名称可用
        if (indexCourseCateList.isEmpty()) {
            return AppResponse.success("分类名称可用！", "true");
        }

        return AppResponse.success("分类名称不可用！", "false");
    }
}
