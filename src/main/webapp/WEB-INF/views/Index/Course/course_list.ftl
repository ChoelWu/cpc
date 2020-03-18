<!DOCTYPE html>
<!-- saved from url=(0039)https://www.imooc.com/search/?words=Vue -->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Vue_搜索_慕课网</title>
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
            <li><a href="#//www.imooc.com/" target="_self" class="imooc">课程网站首页</a></li>
            <li><a href="#//www.imooc.com/course/list" class="active" target="_self">原创课程</a></li>
            <li><a href="#//coding.imooc.com/" target="_self">引进课程</a></li>
            <li><a href="#//class.imooc.com/" class="" target="_self">就业培训</a></li>
            <li><a href="#//www.imooc.com/read" target="_self">其他链接</a></li>
            <li><a href="#//www.imooc.com/wenda" target="_self">其他链接</a></li>
        </ul>
        <div class="header-right">
            <div id="login-area">
                <ul class="header-unlogin clearfix">
                    <#if indexUser??>
                        <li class="user-card-box" id="header-user-card">
                            <a id="header-avator" class="user-card-item js-header-avator" action-type="my_menu" href="/index/user/index.do" target="_self" title="个人中心">
                                <img width="40" height="40" src="//img2.mukewang.com/5cfb1b9e0001542e09600960-100-100.jpg" style="display: inline-block; width: 24px; height: 24px; border-radius: 50%; vertical-align: middle;">
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
            <div class="followus r">
                <a class="followus-weixin" href="javascript:;" target="_blank" title="微信">
                    <div class="flw-weixin-box"></div>
                </a>
                <a class="followus-weibo" href="http://weibo.com/u/3306361973" target="_blank" title="新浪微博"></a>
                <a class="followus-qzone" href="http://user.qzone.qq.com/1059809142/" target="_blank" title="QQ空间"></a>
            </div>
            <div class="footer_intro l">
                <div class="footer_link">
                    <ul>
                        <li><a href="https://www.imooc.com/" target="_blank">网站首页</a></li>
                        <li><a href="https://www.imooc.com/about/cooperate" target="_blank" title="企业合作">企业合作</a></li>
                        <li><a href="https://www.imooc.com/about/job" target="_blank">人才招聘</a></li>
                        <li><a href="https://www.imooc.com/about/contact" target="_blank">联系我们</a></li>
                        <li><a href="https://www.imooc.com/about/recruit" target="_blank">讲师招募</a></li>
                        <li><a href="https://www.imooc.com/help" target="_blank">帮助中心</a></li>
                        <li><a href="https://www.imooc.com/user/feedback" target="_blank">意见反馈</a></li>
                        <li><a href="http://daxue.imooc.com/" target="_blank">慕课大学</a></li>
                        <li><a href="https://git.imooc.com/" target="_blank">代码托管</a></li>
                        <li><a href="https://www.imooc.com/about/friendly" target="_blank">友情链接</a></li>
                        <!--  <li><a href="/corp/index" target="_blank">合作专区</a></li>
                         <li><a href="/about/us" target="_blank">关于我们</a></li> -->
                    </ul>
                </div>
                <p>Copyright <img draggable="false" class="moco-emoji" alt="©" src="/static/index/course/a9.png"> 2020
                    imooc.com All Rights Reserved | 京ICP备 12003892号-11 <a
                            href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11010802030151"
                            style="color: #93999F;margin:0 5px;" target="_blank"><i class="beian"></i>京公网安备11010802030151号</a>
                </p>
            </div>
        </div>
    </div>
</div>
<div id="J_GotoTop" class="elevator">
    <a href="https://www.imooc.com/user/feedback" class="elevator-msg" target="_blank">
        <i class="icon-feedback"></i>
        <span class="">意见反馈</span>
    </a>
    <a href="https://order.imooc.com/pay/sharegoods" class="elevator-dist" target="_blank">
        <i class=""></i>
        <span class="">分销返利</span>
    </a>
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