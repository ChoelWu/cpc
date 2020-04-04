package com.joe.controller.index;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/12/2
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 门户首页
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.joe.entity.IndexArticle;
import com.joe.entity.IndexChannel;
import com.joe.pojo.Article;
import com.joe.pojo.Channel;
import com.joe.pojo.Page;
import com.joe.service.common.PageService;
import com.joe.service.system.IndexArticleService;
import com.joe.service.system.IndexChannelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/index/index")
public class IndexController {
    @Resource
    private IndexChannelService indexChannelService;

    @Resource
    private IndexArticleService indexArticleService;

    @Resource
    private PageService pageService;

    @RequestMapping("/index.do")
    public String index(Model model) {
        // 获取列表
        List<Channel> navList = getNav();

        // 获取内容模块
        List<IndexChannel> blockList = getBlockList();

        // 新闻
        List<IndexArticle> newsList = getArticleListByChannelNo("ICNO20200405011634M39547", "6", false);

        // 通知
        List<IndexArticle> noticeList = getArticleListByChannelNo("ICNO20200405011547T22779", "5", false);

        // 轮播图
        List<IndexArticle> sliderArticleList = getArticleListByChannelNo("ICNO20200405011700B97682", "3", true);

        // 查询栏目 更多信息
        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.eq("channel_no", "ICNO20200405011855L49997");
        IndexChannel indexMoreInfoChannel = indexChannelService.getOne(indexChannelQueryWrapper);

        // 绑定文章列表数据
        model.addAttribute("newsList", newsList);
        model.addAttribute("noticeList", noticeList);
        model.addAttribute("indexMoreInfoChannel", indexMoreInfoChannel);
        model.addAttribute("sliderArticleList", sliderArticleList);
        model.addAttribute("navList", navList);
        model.addAttribute("blockList", blockList);

        return "Index/Index/index";
    }

    /**
     * 查询导航列表
     *
     * @return 返回导航列表
     */
    private List<Channel> getNav() {
        // 查询出所有的菜单
        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.orderByAsc("channel_level", "channel_index").eq("is_nav", "1");
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

        return channelList;
    }

    /**
     * 获取首页内容块
     *
     * @return 返回内容块栏目列表
     */
    private List<IndexChannel> getBlockList() {
        // 查询非菜单
        QueryWrapper<IndexChannel> indexChannelBlockQueryWrapper = new QueryWrapper<>();
        indexChannelBlockQueryWrapper.orderByAsc("channel_level", "channel_index").eq("is_nav", "0");
        return indexChannelService.list(indexChannelBlockQueryWrapper);
    }

    /**
     * 根据栏目编号查询文章列表
     *
     * @param channelNo 栏目编号
     * @param hasPic    是否只查询包含图片的文章
     * @return 返回文章列表
     */
    private List<IndexArticle> getArticleListByChannelNo(String channelNo, String num, boolean hasPic) {
        QueryWrapper<IndexArticle> indexArticleQueryWrapper = new QueryWrapper<>();
        if (hasPic) {
            indexArticleQueryWrapper.isNotNull("article_cover");
        }
        indexArticleQueryWrapper.eq("channel_no", channelNo).orderByDesc("is_top", "publish_time", "article_no").eq("article_status", "1").last("limit " + num);
        return indexArticleService.list(indexArticleQueryWrapper);
    }

    @RequestMapping("/article.do")
    public String article(Model model, String no) {
        // 获取列表
        List<Channel> navList = getNav();

        QueryWrapper<IndexArticle> indexArticleQueryWrapper = new QueryWrapper<>();
        indexArticleQueryWrapper.eq("article_no", no).eq("article_status", "1");
        IndexArticle indexArticle = indexArticleService.getOne(indexArticleQueryWrapper);

        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.eq("channel_no", indexArticle.getChannelNo());
        IndexChannel indexChannel = indexChannelService.getOne(indexChannelQueryWrapper);

        Channel leftChannel = getLeftNavList(indexChannel.getChannelNo());

        model.addAttribute("indexArticle", indexArticle);
        model.addAttribute("indexChannel", indexChannel);
        model.addAttribute("leftChannel", leftChannel);
        model.addAttribute("navList", navList);

        return "Index/Index/article";
    }

    /**
     * 查询出相关菜单
     *
     * @param channelNo 菜单编号
     * @return 返回相关菜单
     */
    private Channel getLeftNavList(String channelNo) {
        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.eq("channel_no", channelNo);
        IndexChannel indexChannel = indexChannelService.getOne(indexChannelQueryWrapper);

        Channel channel = new Channel();

        // 父级菜单
        if (StringUtils.equals(indexChannel.getChannelLevel(), "1")) {
            channel.setIndexChannel(indexChannel);
            // 查询出子级菜单
            QueryWrapper<IndexChannel> indexChildChannelQueryWrapper = new QueryWrapper<>();
            indexChildChannelQueryWrapper.eq("parent_channel_no", indexChannel.getChannelNo());
            List<IndexChannel> indexChannelList = indexChannelService.list(indexChildChannelQueryWrapper);

            channel.setChildChannelList(indexChannelList);
        } else {
            // 查询出父级菜单
            QueryWrapper<IndexChannel> indexParentChannelQueryWrapper = new QueryWrapper<>();
            indexParentChannelQueryWrapper.eq("channel_no", indexChannel.getParentChannelNo());
            IndexChannel indexParentChannel = indexChannelService.getOne(indexParentChannelQueryWrapper);

            channel.setIndexChannel(indexParentChannel);

            // 查询出子级菜单
            QueryWrapper<IndexChannel> indexChildChannelQueryWrapper = new QueryWrapper<>();
            indexChildChannelQueryWrapper.eq("parent_channel_no", indexParentChannel.getChannelNo());
            List<IndexChannel> indexChannelList = indexChannelService.list(indexChildChannelQueryWrapper);

            channel.setChildChannelList(indexChannelList);
        }

        return channel;
    }

    /**
     * 显示文章列表
     *
     * @param model model
     * @param no    菜单编号
     * @return 返回文章列表数据与视图
     */
    @RequestMapping("cate.do")
    public String cate(Model model, String no, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
        // 获取列表
        List<Channel> navList = getNav();

        Map<String, Object> conditionMap = Maps.newHashMap();
        conditionMap.put("channel_no", no);

        // 查询条数
        int adminArticleNum = indexArticleService.countArticle(conditionMap);

        // 分页
        Page page = pageService.Pagination(currentPage, adminArticleNum, 20);

        // 获取文章列表
        int start = page.getRecordNum() * (page.getCurrentPage() - 1);
        Map<String, Integer> pageInfoMap = Maps.newHashMap();
        pageInfoMap.put("startRow", start);
        pageInfoMap.put("rowNum", page.getRecordNum());
        List<Article> articleList = indexArticleService.listArticle(conditionMap, pageInfoMap);

        Channel leftChannel = getLeftNavList(no);

        // 获取栏目信息
        QueryWrapper<IndexChannel> indexChannelQueryWrapper = new QueryWrapper<>();
        indexChannelQueryWrapper.eq("channel_no", no);
        IndexChannel indexChannel = indexChannelService.getOne(indexChannelQueryWrapper);

        model.addAttribute("navList", navList);
        model.addAttribute("articleList", articleList);
        model.addAttribute("page", page);
        model.addAttribute("leftChannel", leftChannel);
        model.addAttribute("indexChannel", indexChannel);
        model.addAttribute("adminArticleNum", adminArticleNum);
        return "Index/Index/cate";
    }
}
