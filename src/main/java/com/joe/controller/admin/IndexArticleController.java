package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/25
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 文章管理
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.*;
import com.joe.pojo.Channel;
import com.joe.pojo.Article;
import com.joe.pojo.Page;
import com.joe.service.common.PageService;
import com.joe.service.system.AdminDictService;
import com.joe.service.system.IndexArticleService;
import com.joe.service.system.IndexChannelService;
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
@RequestMapping("/admin/article")
public class IndexArticleController {
    private Logger logger = LoggerFactory.getLogger(IndexArticleController.class);

    @Resource
    private IndexArticleService indexArticleService;

    @Resource
    private IndexChannelService indexChannelService;

    @Resource
    private PageService pageService;

    @Resource
    private AdminDictService adminDictService;

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
        int adminArticleNum = indexArticleService.countArticle(conditionMap);

        // 分页
        Page page = pageService.Pagination(currentPage, adminArticleNum, 10);

        // 获取用户列表
        int start = page.getRecordNum() * (page.getCurrentPage() - 1);
        Map<String, Integer> pageInfoMap = Maps.newHashMap();
        pageInfoMap.put("startRow", start);
        pageInfoMap.put("rowNum", page.getRecordNum());
        List<Article> ArticleList = indexArticleService.listArticle(conditionMap, pageInfoMap);

        // 查询出所有的菜单
        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.orderByAsc("channel_level", "channel_index");
        List<IndexChannel> indexChannelList = indexChannelService.list(indexChannelQueryWrapper);

        // 创建前台输出的菜单列表
        List<Channel> channelList = Lists.newArrayList();

        // 循环遍历菜单，生成前台需要的格式
        for (IndexChannel indexChannel : indexChannelList) {
            if (StringUtils.equals("1", indexChannel.getChannelLevel())) {
                // 创建菜单对象
                Channel channel = new Channel();
                // 子菜单列表
                List<IndexChannel> indexChildChannelList = Lists.newArrayList();

                // 循环组织子菜单
                for (IndexChannel indexChildChannel : indexChannelList) {
                    if (StringUtils.equals(indexChannel.getChannelNo(), indexChildChannel.getParentChannelNo())) {
                        indexChildChannelList.add(indexChildChannel);
                    }
                }

                // 存储父菜单
                channel.setIndexChannel(indexChannel);
                // 存储子菜单
                channel.setChildChannelList(indexChildChannelList);

                // 存储菜单
                channelList.add(channel);
            }
        }

        // 文章类型数据字典
        List<AdminDict> articleTypeAdminDictList = adminDictService.getDictListByDictName("articleType");
        // 文章是否置顶数据字典
        List<AdminDict> isTopAdminDictList = adminDictService.getDictListByDictName("articleIsTop");
        // 文章是否加粗数据字典
        List<AdminDict> isBoldAdminDictList = adminDictService.getDictListByDictName("articleIsBold");
        // 文章状态数据字典
        List<AdminDict> articleStatusAdminDictList = adminDictService.getDictListByDictName("articleStatus");

        // 绑定数据
        model.addAttribute("ArticleList", ArticleList);
        model.addAttribute("channelList", channelList);
        model.addAttribute("page", page);
        model.addAttribute("conditions", conditionMap);
        model.addAttribute("articleTypeAdminDictList", articleTypeAdminDictList);
        model.addAttribute("isTopAdminDictList", isTopAdminDictList);
        model.addAttribute("isBoldAdminDictList", isBoldAdminDictList);
        model.addAttribute("articleStatusAdminDictList", articleStatusAdminDictList);

        return "/Admin/Article/index";
    }

    /**
     * 文章新增页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("add_page.do")
    public String addPage(Model model) {
        // 查询出所有的菜单
        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.orderByAsc("channel_level", "channel_index");
        List<IndexChannel> indexChannelList = indexChannelService.list(indexChannelQueryWrapper);

        // 创建前台输出的菜单列表
        List<Channel> channelList = Lists.newArrayList();

        // 循环遍历菜单，生成前台需要的格式
        for (IndexChannel indexChannel : indexChannelList) {
            if (StringUtils.equals("1", indexChannel.getChannelLevel())) {
                // 创建菜单对象
                Channel channel = new Channel();
                // 子菜单列表
                List<IndexChannel> indexChildChannelList = Lists.newArrayList();

                // 循环组织子菜单
                for (IndexChannel indexChildChannel : indexChannelList) {
                    if (StringUtils.equals(indexChannel.getChannelNo(), indexChildChannel.getParentChannelNo())) {
                        indexChildChannelList.add(indexChildChannel);
                    }
                }

                // 存储父菜单
                channel.setIndexChannel(indexChannel);
                // 存储子菜单
                channel.setChildChannelList(indexChildChannelList);

                // 存储菜单
                channelList.add(channel);
            }
        }

        // 文章类型数据字典
        List<AdminDict> articleTypeAdminDictList = adminDictService.getDictListByDictName("articleType");
        // 文章是否置顶数据字典
        List<AdminDict> isTopAdminDictList = adminDictService.getDictListByDictName("articleIsTop");
        // 文章是否加粗数据字典
        List<AdminDict> isBoldAdminDictList = adminDictService.getDictListByDictName("articleIsBold");

        // 数据绑定
        model.addAttribute("channelList", channelList);
        model.addAttribute("articleTypeAdminDictList", articleTypeAdminDictList);
        model.addAttribute("isTopAdminDictList", isTopAdminDictList);
        model.addAttribute("isBoldAdminDictList", isBoldAdminDictList);

        return "Admin/Article/add";
    }

    /**
     * 添加文章
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("add.do")
    @ResponseBody
    public AppResponse<IndexArticle> add(String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        IndexArticle indexArticle = gson.fromJson(data, new TypeToken<IndexArticle>() {
        }.getType());

        // 文章编号
        indexArticle.setArticleNo(CommonFunctions.generateNo("IANO"));
        // 文章状态-未审核
        indexArticle.setArticleStatus("0");

        // 操作信息
        indexArticle.setAddUserNo(adminUser.getUserNo());
        indexArticle.setAddTime(new Date());
        indexArticle.setUpdateUserNo(adminUser.getUserNo());
        indexArticle.setUpdateTime(new Date());

        // 新增数据
        boolean rel = indexArticleService.save(indexArticle);
        if (rel) {
            return AppResponse.success("文章新增成功！");
        }

        // 操作失败
        return AppResponse.fail("文章新增失败，请刷新页面后重新提交！");
    }

    /**
     * 字典编辑页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("edit_page.do")
    public String editPage(Model model, String articleNo) {

        // 根据字典编号查询
        QueryWrapper<IndexArticle> indexArticleQueryWrapper = new QueryWrapper<>();
        indexArticleQueryWrapper.eq("article_no", articleNo);
        IndexArticle indexArticle = indexArticleService.getOne(indexArticleQueryWrapper);

        // 查询出所有的菜单
        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.orderByAsc("channel_level", "channel_index");
        List<IndexChannel> indexChannelList = indexChannelService.list(indexChannelQueryWrapper);

        // 创建前台输出的菜单列表
        List<Channel> channelList = Lists.newArrayList();

        // 循环遍历菜单，生成前台需要的格式
        for (IndexChannel indexChannel : indexChannelList) {
            if (StringUtils.equals("1", indexChannel.getChannelLevel())) {
                // 创建菜单对象
                Channel channel = new Channel();
                // 子菜单列表
                List<IndexChannel> indexChildChannelList = Lists.newArrayList();

                // 循环组织子菜单
                for (IndexChannel indexChildChannel : indexChannelList) {
                    if (StringUtils.equals(indexChannel.getChannelNo(), indexChildChannel.getParentChannelNo())) {
                        indexChildChannelList.add(indexChildChannel);
                    }
                }

                // 存储父菜单
                channel.setIndexChannel(indexChannel);
                // 存储子菜单
                channel.setChildChannelList(indexChildChannelList);

                // 存储菜单
                channelList.add(channel);
            }
        }

        // 文章类型数据字典
        List<AdminDict> articleTypeAdminDictList = adminDictService.getDictListByDictName("articleType");
        // 文章是否置顶数据字典
        List<AdminDict> isTopAdminDictList = adminDictService.getDictListByDictName("articleIsTop");
        // 文章是否加粗数据字典
        List<AdminDict> isBoldAdminDictList = adminDictService.getDictListByDictName("articleIsBold");

        // 绑定数据
        model.addAttribute("channelList", channelList);
        model.addAttribute("indexArticle", indexArticle);
        model.addAttribute("articleTypeAdminDictList", articleTypeAdminDictList);
        model.addAttribute("isTopAdminDictList", isTopAdminDictList);
        model.addAttribute("isBoldAdminDictList", isBoldAdminDictList);
        return "Admin/Article/edit";
    }

    /**
     * 添加文章
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AppResponse<IndexArticle> edit(String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        IndexArticle indexArticle = gson.fromJson(data, new TypeToken<IndexArticle>() {
        }.getType());

        // 更新操作信息
        indexArticle.setUpdateUserNo(adminUser.getUserNo());
        indexArticle.setUpdateTime(new Date());

        // 更新数据
        boolean rel = indexArticleService.updateById(indexArticle);
        if (rel) {
            return AppResponse.success("文章编辑成功！");
        }

        // 操作做失败
        return AppResponse.fail("文章编辑失败，请刷新页面后重新提交！");
    }

    /**
     * 文章审核
     *
     * @param articleNo 文章编号
     * @param session   session
     * @return 返回修改结果
     */
    @RequestMapping("/audit.do")
    @ResponseBody
    public AppResponse<IndexArticle> audit(String articleNo, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        QueryWrapper<IndexArticle> indexArticleQueryWrapper = new QueryWrapper<>();
        indexArticleQueryWrapper.eq("article_no", articleNo).eq("article_status", "0");
        IndexArticle indexArticle = indexArticleService.getOne(indexArticleQueryWrapper);

        // 未查询到记录
        if (null == indexArticle) {
            return AppResponse.fail("请刷新页面后重新操作！");
        }

        // 修改文章状态
        indexArticle.setArticleStatus("1");

        // 添加修改信息
        indexArticle.setUpdateTime(new Date());
        indexArticle.setUpdateUserNo(adminUser.getUserNo());

        // 更新信息
        indexArticleService.updateById(indexArticle);

        return AppResponse.success("文章审核成功！");
    }

    /**
     * 文章取消审核
     *
     * @param articleNo 文章编号
     * @param session   session
     * @return 返回修改结果
     */
    @RequestMapping("/dis_audit.do")
    @ResponseBody
    public AppResponse<IndexArticle> disAudit(String articleNo, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        QueryWrapper<IndexArticle> indexArticleQueryWrapper = new QueryWrapper<>();
        indexArticleQueryWrapper.eq("article_no", articleNo).eq("article_status", "1");
        IndexArticle indexArticle = indexArticleService.getOne(indexArticleQueryWrapper);

        // 未查询到记录
        if (null == indexArticle) {
            return AppResponse.fail("请刷新页面后重新操作！");
        }

        // 修改文章状态
        indexArticle.setArticleStatus("0");

        // 添加修改信息
        indexArticle.setUpdateTime(new Date());
        indexArticle.setUpdateUserNo(adminUser.getUserNo());

        // 更新信息
        indexArticleService.updateById(indexArticle);

        return AppResponse.success("文章已取消审核！");
    }

    /**
     * 删除文章
     *
     * @param articleNo 文章编号
     * @return 返回操作结果
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public AppResponse<IndexUser> deleteArticle(String articleNo) {
        // 根据用户编号查询
        QueryWrapper<IndexArticle> indexArticleQueryWrapper = new QueryWrapper<>();
        indexArticleQueryWrapper.eq("article_no", articleNo);
        IndexArticle indexArticle = indexArticleService.getOne(indexArticleQueryWrapper);

        // 判断用户是否存在
        if (null == indexArticle) {
            return AppResponse.fail("删除文章失败，请刷新页面后重新操作！");
        } else {
            indexArticleService.removeById(indexArticle);
            return AppResponse.success("删除文章成功！");
        }
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
            String filePath = request.getServletContext().getRealPath("/") + "file_upload" + File.separator + "article" + File.separator + fileName;

            // 转存文件
            try {
                multipartFile.transferTo(new File(filePath));
            } catch (IOException e) {
                logger.error(e.getMessage());
                return AppResponse.fail("文件上传失败！");
            }

            return AppResponse.success("上传成功！", File.separator + "file_upload" + File.separator + "article" + File.separator + fileName);
        }

        logger.error("文件不存在！");
        return AppResponse.fail("文件上传失败！");
    }
}
