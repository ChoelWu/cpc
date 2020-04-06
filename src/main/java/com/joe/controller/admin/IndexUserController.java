package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/17
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 用户端用户
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.AdminDict;
import com.joe.entity.AdminUser;
import com.joe.entity.IndexUser;
import com.joe.pojo.Page;
import com.joe.service.common.PageService;
import com.joe.service.system.AdminDictService;
import com.joe.service.system.IndexUserService;
import com.joe.util.ImportExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping("/admin/index_user")
public class IndexUserController {
    private Logger logger = LoggerFactory.getLogger(IndexUserController.class);

    @Resource
    private IndexUserService indexUserService;

    @Resource
    private PageService pageService;

    @Resource
    private AdminDictService adminDictService;

    /**
     * 列表页面显示
     *
     * @return 返回页面视图
     */
    @RequestMapping("view.do")
    public String view(Model model, @RequestParam(required = false, defaultValue = "1") int currentPage, @RequestParam(required = false) String conditions) {
        // 查询数据条数
        QueryWrapper<IndexUser> indexUserQueryWrapper = new QueryWrapper<>();

        Gson gson = new Gson();
        Map<String, String> conditionMap = gson.fromJson(conditions, new TypeToken<Map<String, String>>() {
        }.getType());

        // 用户名模糊查询
        if (null != conditionMap) {
            if (conditionMap.containsKey("userName") && StringUtils.isNoneEmpty(conditionMap.get("userName"))) {
                indexUserQueryWrapper.like("user_name", conditionMap.get("userName"));
            }
            if (conditionMap.containsKey("userAccount") && StringUtils.isNoneEmpty(conditionMap.get("userAccount"))) {
                indexUserQueryWrapper.eq("user_account", conditionMap.get("userAccount"));
            }
            if (conditionMap.containsKey("userStatus") && StringUtils.isNoneEmpty(conditionMap.get("userStatus"))) {
                indexUserQueryWrapper.eq("user_status", conditionMap.get("userStatus"));
            }
        }
        // 查询条数
        int adminMenuNum = indexUserService.count(indexUserQueryWrapper);

        // 分页
        Page page = pageService.Pagination(currentPage, adminMenuNum, 10);

        // 获取用户列表
        int start = page.getRecordNum() * (page.getCurrentPage() - 1);
        indexUserQueryWrapper.orderByDesc("user_no").last("limit " + start + "," + page.getRecordNum());
        List<IndexUser> indexUserList = indexUserService.list(indexUserQueryWrapper);

        // 查询用户角色类型
        List<AdminDict> userTypeAdminDictList = adminDictService.getDictListByDictName("indexUserType");

        // 绑定数据
        model.addAttribute("userTypeAdminDictList", userTypeAdminDictList);
        model.addAttribute("indexUserList", indexUserList);
        model.addAttribute("page", page);
        model.addAttribute("conditions", conditionMap);

        return "Admin/IndexUser/index";
    }

    /**
     * 用户新增页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("add_page.do")
    public String addPage(Model model) {
        // 查询用户角色类型
        List<AdminDict> userTypeAdminDictList = adminDictService.getDictListByDictName("indexUserType");

        // 绑定数据
        model.addAttribute("userTypeAdminDictList", userTypeAdminDictList);

        // 返回页面视图
        return "Admin/IndexUser/add";
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
    public AppResponse<IndexUser> add(String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        IndexUser indexUser = gson.fromJson(data, new TypeToken<IndexUser>() {
        }.getType());

        // 用户编号
        indexUser.setUserNo(CommonFunctions.generateNo("IUNO"));

        // 用户密码
        indexUser.setUserPassword(CommonFunctions.encryptPassword("000000", indexUser.getUserNo()));

        // 用户状态-未激活
        indexUser.setUserStatus("1");

        // 操作信息
        indexUser.setAddUserNo(adminUser.getUserNo());
        indexUser.setAddTime(new Date());
        indexUser.setUpdateUserNo(adminUser.getUserNo());
        indexUser.setUpdateTime(new Date());

        // 新增数据
        boolean rel = indexUserService.save(indexUser);
        if (rel) {
            return AppResponse.success("用户新增成功！");
        }

        // 操作失败
        return AppResponse.fail("用户新增失败，请刷新页面后重新提交！");
    }

    /**
     * 用户编辑页面
     *
     * @param model  model
     * @param userNo 用户编号
     * @return 返回页面视图
     */
    @RequestMapping("edit_page.do")
    public String editPage(Model model, String userNo) {
        // 根据用户编号查询
        QueryWrapper<IndexUser> indexUserQueryWrapper = new QueryWrapper<>();
        indexUserQueryWrapper.eq("user_no", userNo);
        IndexUser indexUser = indexUserService.getOne(indexUserQueryWrapper);

        // 查询用户角色类型
        List<AdminDict> userTypeAdminDictList = adminDictService.getDictListByDictName("indexUserType");

        // 绑定数据
        model.addAttribute("userTypeAdminDictList", userTypeAdminDictList);
        model.addAttribute("indexUser", indexUser);

        return "Admin/IndexUser/edit";
    }

    /**
     * 编辑用户
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AppResponse<IndexUser> edit(String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        IndexUser indexUser = gson.fromJson(data, new TypeToken<IndexUser>() {
        }.getType());

        // 更新操作信息
        indexUser.setUpdateUserNo(adminUser.getUserNo());
        indexUser.setUpdateTime(new Date());

        // 更新数据
        boolean rel = indexUserService.updateById(indexUser);
        if (rel) {
            return AppResponse.success("用户编辑成功！");
        }

        // 操作做失败
        return AppResponse.fail("用户编辑失败，请刷新页面后重新提交！");
    }

    /**
     * 删除用户
     *
     * @param userNo 用户编号
     * @return 返回操作结果
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public AppResponse<IndexUser> deleteUser(String userNo) {
        // 根据用户编号查询
        QueryWrapper<IndexUser> indexUserQueryWrapper = new QueryWrapper<>();
        indexUserQueryWrapper.eq("user_no", userNo);
        IndexUser indexUser = indexUserService.getOne(indexUserQueryWrapper);

        // 判断用户是否存在
        if (null == indexUser) {
            return AppResponse.fail("删除用户失败，请刷新页面后重新操作！");
        } else {
            indexUserService.removeById(indexUser);
            return AppResponse.success("删除用户成功！");
        }
    }

    /**
     * 批量删除
     *
     * @param userNos 用户编号串
     * @return 返回操作结果
     */
    @RequestMapping("/delete_batch.do")
    @ResponseBody
    public AppResponse<IndexUser> deleteBatch(String userNos) {
        String[] userNoArr = userNos.split(",");

        List<Long> userIdList = Lists.newArrayList();

        // 查询要删除的用户列表
        for (String userNo : userNoArr) {
            QueryWrapper<IndexUser> indexUserQueryWrapper = new QueryWrapper<>();
            indexUserQueryWrapper.eq("user_no", userNo);
            IndexUser indexUser = indexUserService.getOne(indexUserQueryWrapper);
            if (null == indexUser) {
                return AppResponse.fail("删除用户失败，请刷新页面后重新操作！");
            } else {
                userIdList.add(indexUser.getUserId());
            }
        }

        // 删除用户
        if (userIdList.size() == userNoArr.length) {
            indexUserService.removeByIds(userIdList);
            return AppResponse.success("删除用户成功！");
        } else {
            return AppResponse.fail("删除用户失败，请刷新页面后重新操作！");
        }
    }

    /**
     * 启用用户
     *
     * @param userNo  用户编号
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("/enable.do")
    @ResponseBody
    public AppResponse<IndexUser> enable(String userNo, HttpSession session) {
        // 获取session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 查询用户
        QueryWrapper<IndexUser> indexUserQueryWrapper = new QueryWrapper<>();
        indexUserQueryWrapper.eq("user_no", userNo).eq("user_status", "0");
        IndexUser indexUser = indexUserService.getOne(indexUserQueryWrapper);

        // 更改状态为未激活
        indexUser.setUserStatus("2");
        indexUser.setUpdateUserNo(adminUser.getUserNo());
        indexUser.setUpdateTime(new Date());

        // 更新状态
        indexUserService.updateById(indexUser);
        return AppResponse.success("启用用户成功！");
    }

    /**
     * 禁用用户
     *
     * @param userNo  用户编号
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("/disable.do")
    @ResponseBody
    public AppResponse<IndexUser> disable(String userNo, HttpSession session) {
        // 获取session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 查询用户
        QueryWrapper<IndexUser> indexUserQueryWrapper = new QueryWrapper<>();
        indexUserQueryWrapper.eq("user_no", userNo).ne("user_status", "0");
        IndexUser indexUser = indexUserService.getOne(indexUserQueryWrapper);

        // 更改状态为未激活
        indexUser.setUserStatus("0");
        indexUser.setUpdateUserNo(adminUser.getUserNo());
        indexUser.setUpdateTime(new Date());

        // 更新状态
        indexUserService.updateById(indexUser);
        return AppResponse.success("禁用用户成功！");
    }

    /**
     * 重置密码
     *
     * @param userNo 用户编号
     * @return 返回操作结果
     */
    @RequestMapping("/recovery_password.do")
    @ResponseBody
    public AppResponse<IndexUser> recoveryPassword(String userNo) {
        // 查询用户
        QueryWrapper<IndexUser> indexUserQueryWrapper = new QueryWrapper<>();
        indexUserQueryWrapper.eq("user_no", userNo).ne("user_status", "0");
        IndexUser indexUser = indexUserService.getOne(indexUserQueryWrapper);

        // 更改密码为 000000
        indexUser.setUserPassword(CommonFunctions.encryptPassword("000000", indexUser.getUserNo()));

        // 更新密码
        indexUserService.updateById(indexUser);
        return AppResponse.success("重置密码成功！");
    }

    /**
     * 检查用户信息是否合法
     *
     * @param userNo 用户编号
     * @param field  字段
     * @param value  值
     * @return 返回检查结果 true-可用  false-不可用
     */
    @RequestMapping("check_user.do")
    @ResponseBody
    public AppResponse<String> checkUser(String userNo, String field, String value) {
        // 查询出 userNo 不是当前记录的但是字段值相同的所有的记录
        QueryWrapper<IndexUser> indexUserQueryWrapper = new QueryWrapper<>();
        indexUserQueryWrapper.ne("user_no", userNo).eq(field, value);
        List<IndexUser> indexUserList = indexUserService.list(indexUserQueryWrapper);

        // 名称可用
        if (indexUserList.isEmpty()) {
            return AppResponse.success("此字段值可用！", "true");
        }

        return AppResponse.success("此字段值不可用！", "false");
    }

    /**
     * 下载用户信息模板
     *
     * @param request request
     * @return 返回文件下载结果
     * @throws Exception 文件异常
     */
    @RequestMapping("/download_template.do")
    public ResponseEntity<byte[]> downloadTemplate(HttpServletRequest request) throws Exception {
        return CommonFunctions.downloadFile(request.getServletContext().getRealPath("/file/index_user_template.xls"), "用户信息导入表.xls");
    }

    /**
     * 导入用户信息页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("/import_page.do")
    public String importPage() {
        return "Admin/IndexUser/import_page";
    }

    /**
     * 导入用户信息
     *
     * @param request request
     * @param session session
     * @return 返回结果
     * @throws Exception e
     */
    @RequestMapping("/import_user.do")
    @ResponseBody
    public AppResponse<IndexUser> importUser(HttpServletRequest request, HttpSession session) throws Exception {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        // 获取上传到的文件
        MultipartFile file = multipartRequest.getFile("file");
        // 文件是否存在判断
        if (null == file || file.isEmpty()) {
            logger.error("文件不存在！");
            throw new Exception("文件不存在！");
        }
        // 将上传的文件转化为输入流
        InputStream in = file.getInputStream();
        // 原始数据列表
        List<List<Object>> originObjectList = new ImportExcelUtil().getBankListByExcel(in, file.getOriginalFilename());
        // 读取到文件后关闭输入流
        in.close();

        // 错误数据列表
        List<Map<Integer, String>> errorList = Lists.newArrayList();

        // 判断是否读取到数据
        if (null == originObjectList || originObjectList.isEmpty()) {
            return AppResponse.fail("未读取到数据，请刷新页面后重新上传！");
        }

        // 循环处理数据
        for (List<Object> recordList : originObjectList) {
            // 前两行不进行处理
            if (0 == originObjectList.indexOf(recordList)) {
                continue;
            }

            // 信息合法标志
            boolean flag = true;

            // 用户编号
            String userNo = CommonFunctions.generateNo("IUNO");
            // 用户名
            String userName = recordList.get(0).toString();
            // 用户账号
            String userAccount = recordList.get(1).toString();
            // 用户邮箱
            String userEmail = recordList.get(2).toString();
            // 用户电话
            String userMobile = recordList.get(3).toString();
            // 用户角色类型
            String userType = recordList.get(4).toString();
            // 用户状态
            String userStatus = recordList.get(5).toString();
            // 备注
            String bak = recordList.get(6).toString();

            // 错误数据list
            Map<Integer, String> errorInfoMap = Maps.newHashMap();

            // 错误信息
            String errorMsg = "";

            // 检查用户名称
            if (StringUtils.isEmpty(userName)) {
                flag = false;
                errorMsg += "用户名为空！";
            } else if (userName.length() > 16) {
                flag = false;
                errorMsg += "用户名长度过长，请不要超过16！";
            } else if (!StringUtils.equals("true", this.checkUser("", "user_name", userName).getData())) {
                flag = false;
                errorMsg += "用户名已存在！";
            }

            // 检查用户账号
            if (StringUtils.isEmpty(userAccount)) {
                flag = false;
                errorMsg += "用户账号为空！";
            } else if (userAccount.length() > 16) {
                flag = false;
                errorMsg += "用户账号长度不合要求，请在 6~16 的范围内！";
            } else if (!StringUtils.equals("true", this.checkUser("", "user_account", userAccount).getData())) {
                flag = false;
                errorMsg += "用户账号已存在！";
            }

            // 检查邮箱
            if (StringUtils.isEmpty(userEmail)) {
                flag = false;
                errorMsg += "用户邮箱为空！";
            } else if (userEmail.length() > 50) {
                flag = false;
                errorMsg += "用户邮箱长度不合要求，请不要超过50！";
            } else if (!StringUtils.equals("true", this.checkUser("", "user_email", userEmail).getData())) {
                flag = false;
                errorMsg += "用户邮箱已存在！";
            }

            // 用户电话
            if (StringUtils.isEmpty(userMobile)) {
                flag = false;
                errorMsg += "用户电话为空！";
            } else if (userMobile.length() != 11) {
                flag = false;
                errorMsg += "用户电话不合法！";
            }

            // 用户角色类型
            if (StringUtils.isEmpty(userType)) {
                flag = false;
                errorMsg += "用户角色类型为空！";
            } else {
                if (StringUtils.equals(userType, "校内学生")) {
                    userType = "1";
                } else if (StringUtils.equals(userType, "校外学生")) {
                    userType = "2";
                } else if (StringUtils.equals(userType, "老师")) {
                    userType = "3";
                } else {
                    flag = false;
                    errorMsg += "用户角色类型不合法！";
                }
            }

            // 用户状态
            if (StringUtils.isEmpty(userStatus)) {
                flag = false;
                errorMsg += "用户状态为空！";
            } else {
                if (StringUtils.equals(userStatus, "禁用")) {
                    userStatus = "0";
                } else if (StringUtils.equals(userStatus, "启用")) {
                    userStatus = "1";
                } else {
                    flag = false;
                    userStatus += "用户状态不合法！";
                }
            }

            // 添加记录
            if (flag) {
                // 对象赋值
                IndexUser indexUser = new IndexUser();
                indexUser.setUserNo(userNo);
                indexUser.setUserName(userName);
                indexUser.setUserAccount(userAccount);
                indexUser.setUserPassword(CommonFunctions.encryptPassword("000000", indexUser.getUserNo()));
                indexUser.setUserEmail(userEmail);
                indexUser.setUserMobile(userMobile);
                indexUser.setUserType(userType);
                indexUser.setUserStatus(userStatus);
                indexUser.setAddTime(new Date());
                indexUser.setAddUserNo(adminUser.getUserNo());
                indexUser.setUpdateTime(new Date());
                indexUser.setUpdateUserNo(adminUser.getUserNo());

                indexUserService.save(indexUser);
            } else {
                // 添加错误信息到错误列表
                errorInfoMap.put(originObjectList.indexOf(recordList), errorMsg);
                errorList.add(errorInfoMap);
            }
        }

        logger.error(errorList.toString());

        return AppResponse.success("共" + (originObjectList.size() - 2) + "条数据，用户信息导入成功" + (originObjectList.size() - errorList.size() - 2) + "条，失败" + errorList.size() + "！");
    }
}
