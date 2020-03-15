package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/25
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 栏目管理
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.AdminDict;
import com.joe.entity.AdminUser;
import com.joe.entity.IndexChannel;
import com.joe.pojo.Channel;
import com.joe.service.system.AdminDictService;
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

@RequestMapping("/admin/channel")
@Controller
public class IndexChannelController {
    private Logger logger = LoggerFactory.getLogger(IndexChannelController.class);

    @Resource
    private IndexChannelService indexChannelService;

    @Resource
    private AdminDictService adminDictService;

    /**
     * 栏目列表显示
     *
     * @param model model
     * @return 返回页面
     */
    @RequestMapping("view.do")
    public String view(Model model) {
        // 查询出所有的栏目
        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.orderByAsc("channel_level", "channel_index");
        List<IndexChannel> indexChannelList = indexChannelService.list(indexChannelQueryWrapper);

        // 创建前台输出的栏目列表
        List<Channel> channelList = Lists.newArrayList();

        // 循环遍历栏目，生成前台需要的格式
        for (IndexChannel indexChannel : indexChannelList) {
            if (StringUtils.equals("1", indexChannel.getChannelLevel())) {
                // 创建栏目对象
                Channel channel = new Channel();
                // 子栏目列表
                List<IndexChannel> adminChildChannelList = Lists.newArrayList();

                // 循环组织子栏目
                for (IndexChannel adminChildChannel : indexChannelList) {
                    if (StringUtils.equals(indexChannel.getChannelNo(), adminChildChannel.getParentChannelNo())) {
                        adminChildChannelList.add(adminChildChannel);
                    }
                }

                // 存储父栏目
                channel.setIndexChannel(indexChannel);
                // 存储子栏目
                channel.setChildChannelList(adminChildChannelList);

                // 存储栏目
                channelList.add(channel);
            }
        }

        // 查询栏目类型
        List<AdminDict> channelTypeAdminDictList = adminDictService.getDictListByDictName("channelType");
        List<AdminDict> isNavAdminDictList = adminDictService.getDictListByDictName("channelIsNav");

        // 绑定栏目数据到前台
        model.addAttribute("channelList", channelList);
        model.addAttribute("channelTypeAdminDictList", channelTypeAdminDictList);
        model.addAttribute("isNavAdminDictList", isNavAdminDictList);

        // 返回页面
        return "Admin/Channel/index";
    }

    /**
     * 新增页面显示
     *
     * @return 返回新增页面
     */
    @RequestMapping("add_page.do")
    public String addPage(Model model) {
        // 栏目等级
        List<AdminDict> channelLevelDictList = adminDictService.getDictListByDictName("channelLevel");
        // 栏目状态
        List<AdminDict> channelStatusDictList = adminDictService.getDictListByDictName("channelStatus");

        // 所有启用的父级栏目列表
        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.eq("channel_level", "1").eq("channel_status", "1").orderByAsc("channel_index");
        List<IndexChannel> parentIndexChannelList = indexChannelService.list(indexChannelQueryWrapper);

        // 栏目类型数据字典
        List<AdminDict> channelTypeDictList = adminDictService.getDictListByDictName("channelType");

        // 绑定数据到前台
        model.addAttribute("channelLevelDictList", channelLevelDictList);
        model.addAttribute("channelStatusDictList", channelStatusDictList);
        model.addAttribute("channelTypeDictList", channelTypeDictList);
        model.addAttribute("parentIndexChannelList", parentIndexChannelList);

        // 返回页面
        return "Admin/Channel/add";
    }

    /**
     * 新增栏目
     *
     * @param data    前端表单数据
     * @param session session
     * @return 返回新增结果
     */
    @RequestMapping("add.do")
    @ResponseBody
    public AppResponse<IndexChannel> add(@RequestParam String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为对象
        Gson gson = new Gson();
        IndexChannel indexChannel = gson.fromJson(data, new TypeToken<IndexChannel>() {
        }.getType());

        // 栏目编号
        indexChannel.setChannelNo(CommonFunctions.generateNo("ICNO"));
        // 操作信息
        indexChannel.setAddUserNo(adminUser.getUserNo());
        indexChannel.setAddTime(new Date());
        indexChannel.setUpdateUserNo(adminUser.getUserNo());
        indexChannel.setUpdateTime(new Date());

        // 新增数据
        boolean rel = indexChannelService.save(indexChannel);
        if (rel) {
            return AppResponse.success("栏目新增成功！");
        }

        // 操作失败
        return AppResponse.fail("栏目新增失败，请刷新页面后重新提交！");
    }

    /**
     * 编辑页面显示
     *
     * @return 返回编辑页面
     */
    @RequestMapping("edit_page.do")
    public String editPage(String channelNo, Model model) {
        // 查询栏目
        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.eq("channel_no", channelNo);
        IndexChannel indexChannel = indexChannelService.getOne(indexChannelQueryWrapper);

        // 所有启用的父级栏目列表
        QueryWrapper<IndexChannel> indexChannelListQueryWrapper = new QueryWrapper<>();
        indexChannelListQueryWrapper.eq("channel_level", "1").eq("channel_status", "1").orderByAsc("channel_index");
        List<IndexChannel> parentIndexChannelList = indexChannelService.list(indexChannelListQueryWrapper);

        // 查询是否有子栏目
        QueryWrapper<IndexChannel> chileIndexChannelQueryWrapper = new QueryWrapper<>();
        chileIndexChannelQueryWrapper.eq("parent_channel_no", indexChannel.getChannelNo());
        List<IndexChannel> chileIndexChannelList = indexChannelService.list(chileIndexChannelQueryWrapper);
        boolean hasChild = !chileIndexChannelList.isEmpty();


        // 栏目等级
        List<AdminDict> channelLevelDictList = adminDictService.getDictListByDictName("channelLevel");
        // 栏目状态
        List<AdminDict> channelStatusDictList = adminDictService.getDictListByDictName("channelStatus");
        // 栏目类型数据字典
        List<AdminDict> channelTypeDictList = adminDictService.getDictListByDictName("channelType");

        // 绑定数据到前台
        model.addAttribute("indexChannel", indexChannel);
        model.addAttribute("channelLevelDictList", channelLevelDictList);
        model.addAttribute("channelStatusDictList", channelStatusDictList);
        model.addAttribute("parentIndexChannelList", parentIndexChannelList);
        model.addAttribute("channelTypeDictList", channelTypeDictList);
        model.addAttribute("hasChild", hasChild);

        // 返回页面
        return "Admin/Channel/edit";
    }

    /**
     * 编辑栏目
     *
     * @param data    表单数据
     * @param session session
     * @return 返回编辑修改结果
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AppResponse<IndexChannel> edit(String data, HttpSession session) {
        // 前台字符串数据转化为对象
        Gson gson = new Gson();
        IndexChannel indexChannel = gson.fromJson(data, new TypeToken<IndexChannel>() {
        }.getType());

        // 更新数据
        boolean rel = indexChannelService.updateById(indexChannel);
        if (rel) {
            return AppResponse.success("栏目编辑成功！");
        }

        // 操作失败，返回
        return AppResponse.fail("栏目编辑失败，请刷新页面后重新提交！");
    }

    /**
     * 启用栏目
     *
     * @param channelNo 栏目编号
     * @return 返回操作结果
     */
    @RequestMapping("enable_channel.do")
    @ResponseBody
    public AppResponse<IndexChannel> enableChannel(String channelNo) {
        // 根据栏目编号查询
        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.eq("channel_no", channelNo).eq("channel_status", "0");
        IndexChannel indexChannel = indexChannelService.getOne(indexChannelQueryWrapper);

        // 判断栏目是否存在
        if (null == indexChannel) {
            return AppResponse.fail("启用栏目失败，请刷新页面后重新操作！");
        } else {
            indexChannel.setChannelStatus("1");
            indexChannelService.updateById(indexChannel);
            return AppResponse.success("启用栏目成功！");
        }
    }

    /**
     * 禁用栏目
     *
     * @param channelNo 栏目编号
     * @return 返回操作结果
     */
    @RequestMapping("disable_channel.do")
    @ResponseBody
    public AppResponse<IndexChannel> disableChannel(String channelNo) {
        // 根据栏目编号查询
        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.eq("channel_no", channelNo).eq("channel_status", "1");
        IndexChannel indexChannel = indexChannelService.getOne(indexChannelQueryWrapper);

        // 判断栏目是否存在
        if (null == indexChannel) {
            return AppResponse.fail("禁用栏目失败，请刷新页面后重新操作！");
        } else {
            // 检查是否存在在子栏目
            QueryWrapper<IndexChannel> chileIndexChannelQueryWrapper = new QueryWrapper<>();
            chileIndexChannelQueryWrapper.eq("parent_channel_no", indexChannel.getChannelNo());
            List<IndexChannel> chileIndexChannelList = indexChannelService.list(chileIndexChannelQueryWrapper);

            // 如果没有子栏目，允许删除，否则不允许删除
            if (chileIndexChannelList.isEmpty()) {
                indexChannel.setChannelStatus("0");
                indexChannelService.updateById(indexChannel);
                return AppResponse.success("禁用栏目成功！");
            } else {
                return AppResponse.fail("禁用栏目失败，该栏目下存在子栏目！");
            }
        }
    }

    /**
     * 删除栏目
     *
     * @param channelNo 栏目编号
     * @return 返回操作结果
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public AppResponse<IndexChannel> deleteChannel(String channelNo) {
        // 根据栏目编号查询
        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.eq("channel_no", channelNo);
        IndexChannel indexChannel = indexChannelService.getOne(indexChannelQueryWrapper);

        // 判断栏目是否存在
        if (null == indexChannel) {
            return AppResponse.fail("删除栏目失败，请刷新页面后重新操作！");
        } else {
            // 检查是否存在在子栏目
            QueryWrapper<IndexChannel> chileIndexChannelQueryWrapper = new QueryWrapper<>();
            chileIndexChannelQueryWrapper.eq("parent_channel_no", indexChannel.getChannelNo());
            List<IndexChannel> chileIndexChannelList = indexChannelService.list(chileIndexChannelQueryWrapper);

            // 如果没有子栏目，允许删除，否则不允许删除
            if (chileIndexChannelList.isEmpty()) {
                indexChannelService.removeById(indexChannel);
                return AppResponse.success("删除栏目成功！");
            } else {
                return AppResponse.fail("删除栏目失败，该栏目下存在子栏目！");
            }
        }
    }

    /**
     * 更新栏目索引
     *
     * @param channelNo    栏目编号
     * @param channelIndex 栏目序号
     * @param session      session
     * @return 返回操作结果
     */
    @RequestMapping("update_index.do")
    @ResponseBody
    public AppResponse<IndexChannel> updateIndex(String channelNo, String channelIndex, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 根据栏目编号查询
        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.eq("channel_no", channelNo);
        IndexChannel indexChannel = indexChannelService.getOne(indexChannelQueryWrapper);

        boolean a = StringUtils.isNumeric(channelIndex);
        boolean b = 4 == channelIndex.length();
        // 判断是不是数字
        if (StringUtils.isNumeric(channelIndex) && 4 == channelIndex.length()) {
            indexChannel.setChannelIndex(channelIndex);
            indexChannel.setUpdateUserNo(adminUser.getUserNo());
            indexChannel.setUpdateTime(new Date());
            // 更新
            indexChannelService.updateById(indexChannel);
            // 返回结果
            return AppResponse.success("索引修改成功！");
        }

        // 更新失败
        return AppResponse.fail("索引修改失败！");
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
            String filePath = request.getServletContext().getRealPath("/") + "file_upload" + File.separator + "channel" + File.separator + fileName;

            // 转存文件
            try {
                multipartFile.transferTo(new File(filePath));
            } catch (IOException e) {
                logger.error(e.getMessage());
                return AppResponse.fail("文件上传失败！");
            }

            return AppResponse.success("上传成功！", File.separator + "file_upload" + File.separator + "channel" + File.separator + fileName);
        }

        logger.error("文件不存在！");
        return AppResponse.fail("文件上传失败！");
    }
}
