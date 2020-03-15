package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/17
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 数据字典
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.AdminDict;
import com.joe.entity.AdminMenu;
import com.joe.entity.AdminUser;
import com.joe.pojo.Dict;
import com.joe.pojo.Menu;
import com.joe.pojo.Page;
import com.joe.service.common.PageService;
import com.joe.service.system.AdminDictService;
import com.joe.service.system.AdminMenuService;
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
@RequestMapping("/admin/dict")
public class AdminDictController {
    private Logger logger = LoggerFactory.getLogger(AdminDictController.class);

    @Resource
    private AdminDictService adminDictService;

    @Resource
    private PageService pageService;

    @Resource
    private AdminMenuService adminMenuService;

    /**
     * 列表页面显示
     *
     * @return 返回页面视图
     */
    @RequestMapping("view.do")
    public String view(Model model, @RequestParam(required = false, defaultValue = "1") int currentPage, @RequestParam(required = false) String conditions) {
        Gson gson = new Gson();
        Map<String, Object> conditionMap = gson.fromJson(conditions, new TypeToken<Map<String, Object>>() {
        }.getType());

        // 查询条数
        int adminDictNum = adminDictService.countDict(conditionMap);

        // 分页
        Page page = pageService.Pagination(currentPage, adminDictNum, 10);

        // 获取用户列表
        int start = page.getRecordNum() * (page.getCurrentPage() - 1);
        Map<String, Integer> pageInfoMap = Maps.newHashMap();
        pageInfoMap.put("startRow", start);
        pageInfoMap.put("rowNum", page.getRecordNum());
        List<Dict> dictList = adminDictService.listDict(conditionMap, pageInfoMap);

        // 查询出所有的菜单
        QueryWrapper<AdminMenu> adminMenuQueryWrapper = new QueryWrapper<>();
        adminMenuQueryWrapper.orderByAsc("menu_level", "menu_index");
        List<AdminMenu> adminMenuList = adminMenuService.list(adminMenuQueryWrapper);

        // 创建前台输出的菜单列表
        List<Menu> menuList = Lists.newArrayList();

        // 循环遍历菜单，生成前台需要的格式
        for (AdminMenu adminMenu : adminMenuList) {
            if (StringUtils.equals("1", adminMenu.getMenuLevel())) {
                // 创建菜单对象
                Menu menu = new Menu();
                // 子菜单列表
                List<AdminMenu> adminChildMenuList = Lists.newArrayList();

                // 循环组织子菜单
                for (AdminMenu adminChildMenu : adminMenuList) {
                    if (StringUtils.equals(adminMenu.getMenuNo(), adminChildMenu.getParentMenuNo())) {
                        adminChildMenuList.add(adminChildMenu);
                    }
                }

                // 存储父菜单
                menu.setAdminMenu(adminMenu);
                // 存储子菜单
                menu.setChildMenuList(adminChildMenuList);

                // 存储菜单
                menuList.add(menu);
            }
        }

        // 绑定数据
        model.addAttribute("dictList", dictList);
        model.addAttribute("menuList", menuList);
        model.addAttribute("page", page);
        model.addAttribute("conditions", conditionMap);

        return "Admin/Dict/index";
    }

    /**
     * 字典新增页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("add_page.do")
    public String addPage(Model model) {
        // 查询出所有的菜单
        QueryWrapper<AdminMenu> adminMenuQueryWrapper = new QueryWrapper<>();
        adminMenuQueryWrapper.orderByAsc("menu_level", "menu_index");
        List<AdminMenu> adminMenuList = adminMenuService.list(adminMenuQueryWrapper);

        // 创建前台输出的菜单列表
        List<Menu> menuList = Lists.newArrayList();

        // 循环遍历菜单，生成前台需要的格式
        for (AdminMenu adminMenu : adminMenuList) {
            if (StringUtils.equals("1", adminMenu.getMenuLevel())) {
                // 创建菜单对象
                Menu menu = new Menu();
                // 子菜单列表
                List<AdminMenu> adminChildMenuList = Lists.newArrayList();

                // 循环组织子菜单
                for (AdminMenu adminChildMenu : adminMenuList) {
                    if (StringUtils.equals(adminMenu.getMenuNo(), adminChildMenu.getParentMenuNo())) {
                        adminChildMenuList.add(adminChildMenu);
                    }
                }

                // 存储父菜单
                menu.setAdminMenu(adminMenu);
                // 存储子菜单
                menu.setChildMenuList(adminChildMenuList);

                // 存储菜单
                menuList.add(menu);
            }
        }

        model.addAttribute("menuList", menuList);

        return "Admin/Dict/add";
    }

    /**
     * 添加字典
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("add.do")
    @ResponseBody
    public AppResponse<AdminDict> add(String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        AdminDict adminDict = gson.fromJson(data, new TypeToken<AdminDict>() {
        }.getType());

        // 用户编号
        adminDict.setDictNo(CommonFunctions.generateNo("ADNO"));

        // 操作信息
        adminDict.setAddUserNo(adminUser.getUserNo());
        adminDict.setAddTime(new Date());
        adminDict.setUpdateUserNo(adminUser.getUserNo());
        adminDict.setUpdateTime(new Date());

        // 新增数据
        boolean rel = adminDictService.save(adminDict);
        if (rel) {
            return AppResponse.success("字典新增成功！");
        }

        // 操作失败
        return AppResponse.fail("字典新增失败，请刷新页面后重新提交！");
    }

    /**
     * 字典编辑页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("edit_page.do")
    public String editPage(Model model, String dictNo) {

        // 根据字典编号查询
        QueryWrapper<AdminDict> adminUserQueryWrapper = new QueryWrapper<>();
        adminUserQueryWrapper.eq("dict_no", dictNo);
        AdminDict adminDict = adminDictService.getOne(adminUserQueryWrapper);

        // 查询出所有的菜单
        QueryWrapper<AdminMenu> adminMenuQueryWrapper = new QueryWrapper<>();
        adminMenuQueryWrapper.orderByAsc("menu_level", "menu_index");
        List<AdminMenu> adminMenuList = adminMenuService.list(adminMenuQueryWrapper);

        // 创建前台输出的菜单列表
        List<Menu> menuList = Lists.newArrayList();

        // 循环遍历菜单，生成前台需要的格式
        for (AdminMenu adminMenu : adminMenuList) {
            if (StringUtils.equals("1", adminMenu.getMenuLevel())) {
                // 创建菜单对象
                Menu menu = new Menu();
                // 子菜单列表
                List<AdminMenu> adminChildMenuList = Lists.newArrayList();

                // 循环组织子菜单
                for (AdminMenu adminChildMenu : adminMenuList) {
                    if (StringUtils.equals(adminMenu.getMenuNo(), adminChildMenu.getParentMenuNo())) {
                        adminChildMenuList.add(adminChildMenu);
                    }
                }

                // 存储父菜单
                menu.setAdminMenu(adminMenu);
                // 存储子菜单
                menu.setChildMenuList(adminChildMenuList);

                // 存储菜单
                menuList.add(menu);
            }
        }

        // 绑定数据
        model.addAttribute("menuList", menuList);
        model.addAttribute("adminDict", adminDict);
        return "Admin/Dict/edit";
    }

    /**
     * 添加字典
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AppResponse<AdminDict> edit(String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        AdminDict adminDict = gson.fromJson(data, new TypeToken<AdminDict>() {
        }.getType());

        // 更新操作信息
        adminDict.setUpdateUserNo(adminUser.getUserNo());
        adminDict.setUpdateTime(new Date());

        // 更新数据
        boolean rel = adminDictService.updateById(adminDict);
        if (rel) {
            return AppResponse.success("字典编辑成功！");
        }

        // 操作做失败
        return AppResponse.fail("字典新增失败，请刷新页面后重新提交！");
    }

    /**
     * 删除字典
     *
     * @param dictNo 字典编号
     * @return 返回操作结果
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public AppResponse<AdminDict> deleteDict(String dictNo) {
        // 根据用户编号查询
        QueryWrapper<AdminDict> adminDictQueryWrapper = new QueryWrapper<>();
        adminDictQueryWrapper.eq("dict_no", dictNo);
        AdminDict adminDict = adminDictService.getOne(adminDictQueryWrapper);

        // 判断用户是否存在
        if (null == adminDict) {
            return AppResponse.fail("删除字典失败，请刷新页面后重新操作！");
        } else {
            adminDictService.removeById(adminDict);
            return AppResponse.success("删除字典成功！");
        }
    }

    /**
     * 批量删除
     *
     * @param dictNos 字典编号串
     * @return 返回操作结果
     */
    @RequestMapping("/delete_batch.do")
    @ResponseBody
    public AppResponse<AdminDict> deleteBatch(String dictNos) {
        String[] dictNoArr = dictNos.split(",");

        List<Long> dictIdList = Lists.newArrayList();

        // 查询要删除的用户列表
        for (String dictNo : dictNoArr) {
            QueryWrapper<AdminDict> adminDictQueryWrapper = new QueryWrapper<>();
            adminDictQueryWrapper.eq("dict_no", dictNo);
            AdminDict adminDict = adminDictService.getOne(adminDictQueryWrapper);
            if (null == adminDict) {
                return AppResponse.fail("删除字典失败，请刷新页面后重新操作！");
            } else {
                dictIdList.add(adminDict.getDictId());
            }
        }

        // 删除字典
        if (dictIdList.size() == dictNoArr.length) {
            adminDictService.removeByIds(dictIdList);
            return AppResponse.success("删除字典成功！");
        } else {
            return AppResponse.fail("删除字典失败，请刷新页面后重新操作！");
        }
    }
}
