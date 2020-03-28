<!DOCTYPE html>
<!-- saved from url=(0040)https://www.imooc.com/u/index/allcourses -->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>教学课程平台-${indexUser.userName}的个人中心</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <meta property="qc:admins" content="77103107776157736375">
    <meta property="wb:webmaster" content="c4f857219bfae3cb">
    <link rel="stylesheet" href="/static/index/user/css/moco.min.css" type="text/css">
    <link rel="stylesheet" href="/static/index/user/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="/static/index/user/css/layer.css">
    <link rel="stylesheet" href="/static/index/user/saved_resource" type="text/css">
    <link rel="stylesheet" href="/static/index/user/css/share_style0_16.css">
    <link rel="stylesheet" href="/static/index/user/css/base.css">
    <script charset="utf-8" async="" src="/static/index/user/js/jquery.js"></script>
</head>

<body>
<!-- 窄条导航 -->
<div id="globalTopBanner"></div>
<div id="new_header">
    <div class="page-container new-header clearfix" id="nav">
        <ul class="nav-item">
            <li><a href="#" target="_self" class="imooc">专题页面1</a></li>
            <li><a href="#" target="_self">专题页面2</a></li>
            <li><a href="#" target="_self">专题页面3</a></li>
            <li><a href="#" class="" target="_self">专题页面4</a></li>
            <li><a href="#" target="_self">专题页面5</a></li>
        </ul>
        <div class="header-right">
            <div class="app-download" id="js-app-load">
                <a href="/index/login/logout.do" target="_blank">退出登录</a>
            </div>
        </div>
    </div>
</div>

<div id="main">
    <div class="bg-other user-head-info">
        <div class="user-info clearfix">
            <div class="user-pic" data-is-fans="0" data-is-follows="">
                <div class="user-pic-bg">
                    <img class="img" src="${indexUser.userHeadSculpture!''}" alt="">
                </div><!--user-pic-big end-->
            </div>
            <div class="user-info-right">
                <h3 class="user-name clearfix">
                    <span>${indexUser.userName}</span>
                </h3>
                <!--25-->
                <p class="about-info">
                    <span>
                        <#if indexUser.userType='1'>校内学生<#elseif indexUser.userType='2'>校外学生<#elseif indexUser.userType='3'>老师</#if>
                    </span>
                    <a class="more-user-info" href="/index/user/config.do"><i class="imv2-edit"></i> 编辑信息</a>
                </p>
            </div>
            <div class="study-info clearfix">
<#--                <div class="item follows">-->
<#--                    <div class="u-info-learn" title="学习时长224小时5分" style="cursor:pointer;">-->
<#--                        <em>224h</em>-->
<#--                        <span>学习时长 </span>-->
<#--                    </div>-->
<#--                </div>-->
<#--                <div class="item follows">-->
<#--                    <a href="https://www.imooc.com/u/index/experience"><em>10611</em></a>-->
<#--                    <span>经验</span>-->
<#--                </div>-->
<#--                <div class="item follows">-->
<#--                    <a href="https://www.imooc.com/u/index/credit"><em>1</em></a>-->
<#--                    <span>积分</span>-->
<#--                </div>-->
<#--                <div class="item follows">-->
<#--                    <a href="https://www.imooc.com/u/index/follows"><em>1</em></a>-->
<#--                    <span>关注</span>-->
<#--                </div>-->
<#--                <div class="item follows">-->
<#--                    <a href="https://www.imooc.com/u/index/fans"><em>0</em></a>-->
<#--                    <span>粉丝</span>-->
<#--                </div>-->
                <div class="item follows">
                    <a href="/index/user/config.do" class="set-btn">
                        <i class="icon-set"></i>个人设置
                    </a>
                </div>
            </div><!--.study-info end-->
        </div><!-- .user-info end -->
    </div><!-- .big-pic end -->
    <div class="wrap">

        <div class="slider">
            <ul>
                <li>
                    <a href="/index/user/index.do">
                        <i class="imv2-war"></i><span>我的课程</span><b class="icon-drop_right"></b>
                    </a>
                </li>
                <li>
                    <a href="/index/user/learn_log.do" class="active">
                        <i class="new-icn"></i> <i class="imv2-list_numbered"></i><span>历史记录</span><b
                                class="icon-drop_right"></b>
                    </a>
                </li>
                <#--                <li>-->
                <#--                    <a href="https://www.imooc.com/u/index/allfollows">-->
                <#--                        <i class="imv2-tick"></i><span>收藏</span><b class="icon-drop_right"></b>-->
                <#--                    </a>-->
                <#--                </li>-->
                <!-- 只能自己看的 -->
                <#--                <li>-->
                <#--                    <a href="https://www.imooc.com/u/index/notebook">-->
                <#--                        <i class="imv2-nav_note"></i><span>笔记</span><b class="icon-drop_right"></b>-->
                <#--                    </a>-->
                <#--                </li>-->

                <#--                <li>-->
                <#--                    <a href="https://www.imooc.com/u/index/bbs">-->
                <#--                        <i class="icon-yuanwen"></i><span>猿问</span><b class="icon-drop_right"></b>-->
                <#--                    </a>-->
                <#--                </li>-->

                <#--                <li>-->
                <#--                    <a href="https://www.imooc.com/u/index/articles">-->
                <#--                        <i class="icon-blog"></i><span>手记</span><b class="icon-drop_right"></b>-->
                <#--                    </a>-->
                <#--                </li>-->

                <#--                <li>-->
                <#--                    <a href="https://www.imooc.com/u/index/read">-->
                <#--                        <i class="imv2-feather-o"></i><span>专栏</span><b class="icon-drop_right"></b>-->
                <#--                    </a>-->
                <#--                </li>-->
            </ul>
        </div>
        <div class="u-container">
            <div class="c-tab clearfix">
                <div class="tool-left l">
                    <a class="sort-item active">我参与的课程</a>
                </div>
            </div>

            <div class="all-course-main">
                <div class="allcourse-content js-course-list ">
                    <div id="qaContainer" class="answertabcon">
                        <div class="course_quescon mod-post">
                            <#list learnLogPOJOList as learnLogPOJO>
                            <div class="wenda-listcon mod-qa-list clearfix">
                                <div class="icon-wenda"></div>
                                <div class="headslider qa-medias l">
                                    <a href="/index/course/detail.do?courseNo=${learnLogPOJO.indexCourse.courseNo}" class="media" target="_blank" title="${learnLogPOJO.indexCourse.courseName}">
                                        <img src="${learnLogPOJO.indexCourse.courseCover}" width="40"
                                                height="40">
                                    </a>
                                </div>
                                <div class="wendaslider qa-content">
                                    <h2 class="wendaquetitle qa-header">
                                        <div class="wendatitlecon qa-header-cnt clearfix">
                                            <a href="/index/course/video.do?lessonNo=${learnLogPOJO.indexLesson.lessonNo}" target="_blank" class="qa-tit">${learnLogPOJO.indexLesson.lessonName}</a>
                                        </div>
                                    </h2>
                                    <div class="replycont qa-body clearfix">
                                        <div class="l replydes">
                                            <span class="replysign">
                                                <a href="/index/course/detail.do?courseNo=${learnLogPOJO.indexCourse.courseNo}" class="nickname">${learnLogPOJO.indexCourse.courseName}</a> /
                                                ${learnLogPOJO.indexChapter.chapterName} /
                                                <a href="/index/course/video.do?lessonNo=${learnLogPOJO.indexLesson.lessonNo}" target="_blank" title="GXHCT"
                                                        class="nickname">${learnLogPOJO.indexLesson.lessonName}</a>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="replymegfooter qa-footer clearfix">
                                        <div class="l-box l">
                                            <a href="/index/course/video.do?lessonNo=${learnLogPOJO.indexLesson.lessonNo}" target="_blank" class="replynumber static-count "></a>
                                        </div>
                                        <em class="r">${learnLogPOJO.indexLearnLog.addTime?string('yyyy-MM-dd HH:mm:ss')}</em>
                                    </div>
                                </div>
                            </div>
                            </#list>
                        </div>
                    </div>
                </div>
                <!-- 分页 -->
                <div class="qa-comment-page">
                    <div class="page">
                        <#if -1 = page.prePage>
                            <span class="disabled_page">首页</span>
                            <span class="disabled_page">上一页</span>
                        <#else>
                            <a href="/index/user/learn_log.do?currentPage=1">首页</a>
                            <a href="/index/user/learn_log.do?currentPage=${page.prePage}">上一页</a>
                        </#if>
                        <#if 0 lt page.currentPage - 2>
                            <a class="text-page-tag" href="/index/user/learn_log.do?currentPage=${page.currentPage - 2}">${page.currentPage - 2}</a>
                        </#if>
                        <#if 0 lt page.currentPage - 1>
                            <a class="text-page-tag" href="/index/user/learn_log.do?currentPage=${page.currentPage - 1}">${page.currentPage - 1}</a>
                        </#if>
                        <a href="javascript:void(0)" class="active text-page-tag">${page.currentPage}</a>
                        <#if page.totalPage gte page.currentPage + 1>
                            <a class="text-page-tag" href="/index/user/learn_log.do?currentPage=${page.currentPage + 1}">${page.currentPage + 1}</a>
                        </#if>
                        <#if page.totalPage gte page.currentPage + 2>
                            <a class="text-page-tag" href="/index/user/learn_log.do?currentPage=${page.currentPage + 2}">${page.currentPage + 2}</a>
                        </#if>

                        <#if -1 = page.nextPage>
                            <span class="disabled_page">下一页</span>
                            <span class="disabled_page">尾页</span>
                        <#else>
                            <a href="/index/user/learn_log.do?currentPage=${page.nextPage}">下一页</a>
                            <a href="/index/user/learn_log.do?currentPage=${page.totalPage}">尾页</a>
                        </#if>
                    </div>
                </div>
            </div>
        </div><!-- .container end -->
    </div><!-- .wrap end-->
</div>

<div id="footer" data="u,allcourses">
    <div class="waper">
        <div class="footerwaper clearfix">
            <#--            <div class="followus r">-->
            <#--                <a class="followus-weixin" href="javascript:;" target="_blank" title="微信">-->
            <#--                    <div class="flw-weixin-box"></div>-->
            <#--                </a>-->
            <#--                <a class="followus-weibo" href="http://weibo.com/u/3306361973" target="_blank" title="新浪微博"></a>-->
            <#--                <a class="followus-qzone" href="http://user.qzone.qq.com/1059809142/" target="_blank" title="QQ空间"></a>-->
            <#--            </div>-->
            <div class="footer_intro l">
                <div class="footer_link">
                    <ul>
                        <li><a href="https://www.nwnu.edu.cn/" target="_blank" title="西北师范大学">西北师范大学</a></li>
                        <li><a href="https://chem.nwnu.edu.cn/" target="_blank" title="西北师范大学化学化工学院">西北师范大学化学化工学院</a></li>
                        <li><a href="#" target="_blank" title="快速连接二">快速连接二</a></li>
                        <li><a href="#" target="_blank" title="快速连接三">快速连接三</a></li>
                        <li><a href="#" target="_blank" title="快速连接四">快速连接四</a></li>
                    </ul>
                </div>
                <p>Copyright <img draggable="false" class="moco-emoji" alt="©" src="/static/index/user/image/a9.png"> 西北师范大学-化工学院 Copyright © 2019-2030 All Rights Reserved 　陇ICP备17000462号-1 </p>
            </div>
        </div>
    </div>
</div>

<div id="J_GotoTop" class="elevator">
    <a href="https://www.imooc.com/user/feedback" class="elevator-msg" target="_blank">
        <i class="icon-feedback"></i>
        <span class="">意见反馈</span>
    </a>
    <a href="https://order.imooc.com/pay/sharegoods" class="elevator-dist" style="display: none;" target="_blank">
        <i class=""></i>
        <span class="">分销返利</span>
    </a>
    <!-- <a href="//www.imooc.com/act/invite" class="elevator-dist" target="_blank">
        <i class=""></i>
        <span class="">邀请有奖</span>
    </a> -->
    <a href="https://www.imooc.com/help" class="elevator-faq" target="_blank">
        <i class="icon-ques"></i>
        <span class="">帮助中心</span>
    </a>
    <a href="https://www.imooc.com/mobile/app" target="_blank" class="elevator-app">
        <i class="icon-appdownload"></i>
        <span class="">APP下载</span>
        <div class="elevator-app-box"></div>
    </a>
    <a href="javascript:void(0)" class="elevator-weixin no-goto" id="js-elevator-weixin">
        <i class="icon-wxgzh"></i>
        <span class="">官方微信</span>
        <div class="elevator-weixin-box"></div>
    </a>
    <a href="javascript:void(0)" class="elevator-top no-goto" style="display:none" id="backTop">
        <i class="icon-up2"></i>
        <span class="">返回顶部</span>
    </a>
</div>


<div id="globalRightFloat"></div>

<style type="text/css">
    .myClassList table td dl dd {
        margin-top: 88px
    }

    #face_panel {
        z-index: 99999999
    }
</style>

<div style="display: none">
    <script>
        (function () {
            var bp = document.createElement('script');
            var curProtocol = window.location.protocol.split(':')[0];
            if (curProtocol === 'https') {
                bp.src = 'https://zz.bdstatic.com/linksubmit/push.js';
            } else {
                bp.src = 'http://push.zhanzhang.baidu.com/push.js';
            }
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(bp, s);
        })();
    </script>
</div>
</body>
</html>