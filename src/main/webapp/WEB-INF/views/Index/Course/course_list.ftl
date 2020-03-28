<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>教学课程平台</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="/static/index/course/css/base.css" type="text/css">
    <link rel="stylesheet" href="/static/index/course/css/common-less.css" type="text/css">
    <link rel="stylesheet" href="/static/index/course/css/moco.min.css" type="text/css">
    <link rel="stylesheet" href="/static/index/course/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="/static/index/course/saved_resource" type="text/css">
    <script charset="utf-8" async="" src="/static/index/course/js/jquery.js"></script>
</head>
<body>
<div id="globalTopBanner"></div>
<div id="new_header">
    <div class="page-container new-header clearfix" id="nav">
        <ul class="nav-item">
            <li><a href="#" target="_self">专题页面1</a></li>
            <li><a href="#" target="_self">专题页面2</a></li>
            <li><a href="#" target="_self">专题页面3</a></li>
            <li><a href="#" target="_self">专题页面4</a></li>
            <li><a href="#" target="_self">专题页面5</a></li>
        </ul>
        <div class="header-right">
            <div id="login-area">
                <ul class="header-unlogin clearfix">
                    <#if indexUser??>
                        <li class="user-card-box" id="header-user-card">
                            <a id="header-avator" class="user-card-item js-header-avator" action-type="my_menu" href="/index/user/index.do" target="_self" title="个人中心">
                                <#if indexUser.userHeadSculpture?? && indexUser.userHeadSculpture!=''>
                                    <img width="40" height="40" src="${indexUser.userHeadSculpture!''}" style="display: inline-block; width: 24px; height: 24px; border-radius: 50%; vertical-align: middle;">
                                <#else>
                                    <img width="40" height="40" src="/static/index/user/image/unkonw-user.jpg" style="display: inline-block; width: 24px; height: 24px; border-radius: 50%; vertical-align: middle;">
                                </#if>
                            </a>
                        </li>
                    <#else>
                        <li class="header-signin">
                            <a href="/index/login/login_page.do" target="_blank">登录</a>
                        </li>
                    </#if>
                </ul>
            </div>
        </div>
    </div>
</div>
<div id="main">
    <div class="search-main">
        <div class="search-header js-search-header">
            <div class="search-form">
                <input type="text" class="search-form-ipt js-search-ipt l" id="search-input" value="${courseName!''}" placeholder="请输入想搜索的内容">
                <button class="search-form-btn js-search-btn r" id="btn_search">搜索</button>
                <div class="search-tips-area js-search-tips">
                </div>
            </div>
        </div>

        <div class="js-header-placeholder" style="height: 88px; display: none;"></div>

        <div class="search-body">
            <div class="search-content">
                <#if courseName?? && courseName != ''>
                    <div class="search-classify">
                        <div class="all_content">
                            <a class="active">搜索结果</a>
                        </div>
                        <div class="tab_con">
                            <span>搜索 “${courseName}” 找到相关内容 ${courseNum} 个</span>
                        </div>
                    </div>
                </#if>
                <div class="search-course-list">
                    <#list indexCourseList as indexCourse>
                        <div class="search-item js-search-item clearfix">
                            <a href="/index/course/detail.do?courseNo=${indexCourse.courseNo}" target="_blank"
                               class="js-zhuge-allResult js-result-item item-img l ">
                                <img src="${indexCourse.courseCover!''}">
                            </a>
                            <div class="item-detail l">
                                <a href="/index/course/detail.do?courseNo=${indexCourse.courseNo}" target="_blank"
                                   class="js-zhuge-allResult item-title js-result-item js-item-title">
                                    ${indexCourse.courseName!''}
                                </a>
                                <p class="item-desc">${indexCourse.courseIntro!''}</p>
                                <div class="item-classify">
                                    <span>讲师：<a target="_blank">${indexCourse.courseAuthor!''}</a></span>
                                    <span>
                                        <#if indexCourse.courseDifficultLevel = "1">
                                            简单
                                        <#elseif indexCourse.courseDifficultLevel = "2">
                                            普通
                                        <#elseif indexCourse.courseDifficultLevel = "3">
                                            困难
                                        </#if>
                                    </span>
                                    <i class="imv2-set-sns"></i>
                                    <span>${indexCourse.courseLearnNum!''}</span>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
                <div class="page">
                    <#if -1 = page.prePage>
                        <span class="disabled_page">首页</span>
                        <span class="disabled_page">上一页</span>
                    <#else>
                        <a href="/index/course/list.do?currentPage=1${conditionString}">首页</a>
                        <a href="/index/course/list.do?currentPage=${page.prePage}${conditionString}">上一页</a>
                    </#if>
                    <#if 0 lt page.currentPage - 2>
                    <a class="text-page-tag" href="/index/course/list.do?currentPage=${page.currentPage - 2}${conditionString}">${page.currentPage - 2}</a>
                    </#if>
                    <#if 0 lt page.currentPage - 1>
                    <a class="text-page-tag" href="/index/course/list.do?currentPage=${page.currentPage - 1}${conditionString}">${page.currentPage - 1}</a>
                    </#if>
                    <a href="javascript:void(0)" class="active text-page-tag">${page.currentPage}</a>
                    <#if page.totalPage gte page.currentPage + 1>
                    <a class="text-page-tag" href="/index/course/list.do?currentPage=${page.currentPage + 1}${conditionString}">${page.currentPage + 1}</a>
                    </#if>
                    <#if page.totalPage gte page.currentPage + 2>
                    <a class="text-page-tag" href="/index/course/list.do?currentPage=${page.currentPage + 2}${conditionString}">${page.currentPage + 2}</a>
                    </#if>

                    <#if -1 = page.nextPage>
                        <span class="disabled_page">下一页</span>
                        <span class="disabled_page">尾页</span>
                    <#else>
                        <a href="/index/course/list.do?currentPage=${page.nextPage}${conditionString}">下一页</a>
                        <a href="/index/course/list.do?currentPage=${page.totalPage}${conditionString}">尾页</a>
                    </#if>
                </div>
            </div>

            <div class="search-recommend r">
                <div class="swiper-container swiper-container-horizontal swiper-con">
                    <div class="swiper-wrapper js-zhuge-searchAd"
                         style="transition-duration: 0ms; transform: translate3d(-1050px, 0px, 0px);"><a
                                class="swiper-slide item swiper-slide-duplicate swiper-slide-duplicate-active"
                                style="background-image: url('https://www.imooc.com/static/img/search/java2.png'); width: 320px; margin-right: 30px;"
                                target="_blank" href="https://class.imooc.com/sale/javaarchitect"
                                data-swiper-slide-index="2">
                        </a>
                        <a class="swiper-slide item swiper-slide-duplicate-next"
                           style="background-image: url('https://www.imooc.com/static/img/search/full-java.png'); width: 320px; margin-right: 30px;"
                           target="_blank" href="https://class.imooc.com/sale/javafullstack"
                           data-swiper-slide-index="0">
                        </a>
                        <a class="swiper-slide item swiper-slide-prev"
                           style="background-image: url('https://www.imooc.com/static/img/search/full-fe.png'); width: 320px; margin-right: 30px;"
                           target="_blank" href="https://class.imooc.com/sale/webfullstack" data-swiper-slide-index="1">
                        </a>
                        <a class="swiper-slide item swiper-slide-active"
                           style="background-image: url('https://www.imooc.com/static/img/search/java2.png'); width: 320px; margin-right: 30px;"
                           target="_blank" href="https://class.imooc.com/sale/javaarchitect"
                           data-swiper-slide-index="2">
                        </a>
                        <a class="swiper-slide item swiper-slide-duplicate swiper-slide-next"
                           style="background-image: url('https://www.imooc.com/static/img/search/full-java.png'); width: 320px; margin-right: 30px;"
                           target="_blank" href="https://class.imooc.com/sale/javafullstack"
                           data-swiper-slide-index="0">
                        </a></div>
                    <!-- 如果需要分页器 -->
                    <div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets"><span
                                class="swiper-pagination-bullet" tabindex="0" role="button"
                                aria-label="Go to slide 1"></span><span class="swiper-pagination-bullet" tabindex="0"
                                                                        role="button" aria-label="Go to slide 2"></span><span
                                class="swiper-pagination-bullet swiper-pagination-bullet-active" tabindex="0"
                                role="button" aria-label="Go to slide 3"></span></div>
                    <!-- 如果需要导航按钮 -->
                    <div class="swiper-button-prev" tabindex="0" role="button" aria-label="Previous slide"><i
                                class="imv2-arrow2_l"></i></div>
                    <div class="swiper-button-next" tabindex="0" role="button" aria-label="Next slide"><i
                                class="imv2-arrow2_r"></i></div>
                    <span class="swiper-notification" aria-live="assertive" aria-atomic="true"></span></div>
                <div class="jiuyeban-recommend">
                    <div class="title">
                        金牌课程
                    </div>
                    <div class="direct-con js-zhuge-searchRecommend">
                        <a class="direct" target="_blank" href="https://class.imooc.com/sale/newfe">
                            <div class="img"
                                 style="background-image:url('https://www.imooc.com/static/img/search/fe.png')"></div>
                            <div class="text">
                                <div>Web前端攻城狮</div>
                                <p>踏入IT行业从此开始</p>
                            </div>
                        </a>
                        <a class="direct" target="_blank" href="https://class.imooc.com/sale/newjava">
                            <div class="img"
                                 style="background-image:url('https://www.imooc.com/static/img/search/java.png')"></div>
                            <div class="text">
                                <div>Java攻城狮</div>
                                <p>全球3大编程语言之一</p>
                            </div>
                        </a>
                        <a class="direct" target="_blank" href="https://class.imooc.com/sale/newandroid">
                            <div class="img"
                                 style="background-image:url('https://www.imooc.com/static/img/search/android.png')"></div>
                            <div class="text">
                                <div>Android攻城狮</div>
                                <p>5G物联网通万物</p>
                            </div>
                        </a>
                        <a class="direct" target="_blank" href="https://class.imooc.com/sale/php">
                            <div class="img"
                                 style="background-image:url('https://www.imooc.com/static/img/search/php.png')"></div>
                            <div class="text">
                                <div>PHP攻城狮</div>
                                <p>打通前后端一步到位</p>
                            </div>
                        </a>
                        <a class="direct" target="_blank" href="https://class.imooc.com/sale/python">
                            <div class="img"
                                 style="background-image:url('https://www.imooc.com/static/img/search/python.png')"></div>
                            <div class="text">
                                <div>Python攻城狮</div>
                                <p>爬虫大数据抢占先机</p>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 播放器出来才会展示的按钮 -->
    <a href="https://www.imooc.com/search/?words=Vue" target="_blank" class="godetails js-godetails">查看课程详情</a>
</div>
<div id="footer" data="search,index">
    <div class="waper">
        <div class="footerwaper clearfix">
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
                <p>Copyright <img draggable="false" class="moco-emoji" alt="©" src="/static/index/course/images/a9.png"> 西北师范大学-化工学院 Copyright © 2019-2030 All Rights Reserved 　陇ICP备17000462号-1 </p>
            </div>
        </div>
    </div>
</div>
<script src="/static/index/course/js/jquery.js"></script>
<div style="display: none">
    <script>
        $(document).ready(function() {
            // 查询框
            $("#btn_search").click(function() {
                var courseName = $("#search-input").val();
                window.location.href = "/index/course/list.do?currentPage=1&courseName=" + courseName;
            });
        });
    </script>

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