<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>教学课程平台</title>
    <link rel="stylesheet" href="/static/index/course/css/index.css" type="text/css">
    <link rel="stylesheet" href="/static/index/course/css/moco.css" type="text/css">
    <link rel="stylesheet" href="/static/index/course/css/swiper-3.css">
    <script src="/static/index/course/js/jquery.js"></script>
    <script src="/static/index/course/js/jquery.SuperSlide.2.1.1.js"></script>
<body id="index">
<!-- 顶部广告位 -->
<div id="globalTopBanner"></div>
<div class="js-header-padding" style="width:100%;height:72px;"></div>
<div id="header" class="fix-header">
    <div class="page-container clearfix" id="nav" style="background-color: #fff !important;">
        <div id="logo" class="logo"><a href="#/" target="_self" title="首页">
            <img title="logo" src="/static/index/course/images/logo.png" width="150"></a>
        </div>
        <button type="button" class="navbar-toggle visible-xs-block js-show-menu">
            <i class="icon-menu"></i>
        </button>
        <ul class="nav-item">
            <li>
                <a href="#" target="_self">专题页面1</a>
            </li>
            <li>
                <a href="#" target="_self">专题页面2</a>
            </li>
            <li>
                <a href="#" target="_self">专题页面3</a>
            </li>
            <li>
                <a href="#" target="_self">专题页面4</a>
            </li>
            <li>
                <a href="#" target="_self">专题页面5</a>
            </li>
        </ul>
        <div id="login-area">
            <ul class="header-unlogin clearfix">
                <li class="header-signin">
                    <#if indexUser??>
                        <#if indexUser.userHeadSculpture?? && indexUser.userHeadSculpture!=''>
                            <a href="/index/user/index.do" id="js-signin-btn" title="个人中心"><img width="40" height="40" src="${indexUser.userHeadSculpture!''}"></a>
                        <#else>
                            <a href="/index/user/index.do" id="js-signin-btn" title="个人中心"><img width="40" height="40" src="/static/index/user/image/unkonw-user.jpg"></a>
                        </#if>
                    <#else>
                        <a href="/index/login/login_page.do" id="js-signin-btn">登录&nbsp;&nbsp;&nbsp;&nbsp;</a>
                    </#if>
                </li>
            </ul>
        </div>
        <div class="search-warp clearfix" style="min-width: 32px; height: 72px;">
            <div class="search-area" data-search="top-banner">
                <input class="search-input" id="search-input" data-suggest-trigger="suggest-trigger" placeholder="请输入关键字..." type="text"
                       autocomplete="off">
                <input type="hidden" class="btn_search" data-search-btn="search-btn">
            </div>
            <div class="showhide-search" id="btn_search" data-show="no"><i class="icon-search"></i></div>
        </div>
    </div>
</div>

<div id="main">
    <div class="bk" style="background-image: url(http://img.mukewang.com/5dbfd1160001147918720764.jpg);"></div>
    <div class="bgfff banner-box">
        <div class="g-banner pr">
            <div class="menuwrap"></div>
            <#list courseCateList as courseCate>
                <div class="submenu ${courseCate.indexCourseCate.courseCateNo} hide" data-id="${courseCate.indexCourseCate.courseCateNo}" style="display: none;">
                    <div class="innerBox clearfix">
                        <div class="subinnerBox">
                            <div class="cates-box">
                                <div class="fe-base-box clearfix">
                                    <div class="banner-line">
                                        <span class="bold mr10 small-title">${courseCate.indexCourseCate.courseCateName}</span>
                                    </div>
                                    <div class="tag-box l">
                                        <#list courseCate.childCourseCateList as childCourseCate>
                                            <a target="_blank" href="/index/course/list.do?courseCateNo=${childCourseCate.courseCateNo}">${childCourseCate.courseCateName}</a>
                                        </#list>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="recomment-box">
                        <#if courseCate.presentCourseList??>
                            <#list courseCate.presentCourseList as presentCourse>
                                <div class="l banner-course-card">
                                    <a href="#/217.html?mc_marking=26dd86266f55cfc7a3b8b624af302664&amp;mc_channel=qianduankaifa1"
                                       target="_blank" title="大学化学实验" class="clearfix">
                                        <img src="${presentCourse.courseCover}" class="l">
                                        <div class="l course-card">
                                            <h3 class="course-card-name">${presentCourse.courseName}</h3>
                                            <div class="course-card-price l">大三（下）</div>
                                            <div class="course-card-dot l"><i class="imv2-dot_samll"></i></div>
                                            <div class="course-card-info l">普通</div>
                                            <div class="course-card-dot l"><i class="imv2-dot_samll"></i></div>
                                            <div class="course-card-info l"><i class="icon-set_sns"></i>1708</div>
                                        </div>
                                    </a>
                                </div>
                            </#list>
                        </#if>
                    </div>
                </div>
            </#list>

            <div class="menuContent">
                <#list courseCateList as courseCate>
                    <div class="item" data-id="${courseCate.indexCourseCate.courseCateNo}">
                        <a href="javascript:void(0)">
                            <span class="group">${courseCate.indexCourseCate.courseCateName}</span>
                            <i class="imv2-arrow1_r"></i>
                        </a>
                    </div>
                </#list>
            </div>
            <div class="g-banner-content">
                <div class="g-banner-box">
                    <div class="focusBox" style="margin:0 auto">
                        <ul class="pic">
                            <#list indexCourseBannerList as indexCourseBanner>
                                <li><a href="${indexCourseBanner.courseBannerUrl}" target="_blank" title="${indexCourseBanner.courseBannerName}"><img src="${indexCourseBanner.courseBannerPic}"/></a></li>
                            </#list>
                        </ul>
                        <a class="prev" href="javascript:void(0)"></a>
                        <a class="next" href="javascript:void(0)"></a>
                        <ul class="hd">
                            <#list indexCourseBannerList as indexCourseBanner>
                            <li></li>
                            </#list>
                        </ul>
                    </div>

                    <script type="text/javascript">
                        jQuery(".focusBox").slide({ mainCell:".pic",effect:"left", autoPlay:true, delayTime:300});
                    </script>
                </div>
            </div>

        </div>
        <div class="container-types container-recommend clearfix"><h3 class="types-title clearfix">
            <span>精品课程</span><span class="intro">简短的介绍</span></h3>
            <div class="recommend-box"><a
                    href="#.com/sale/javafullstack?mc_marking=53bac465973c096055bae2d7a383fde5&amp;mc_channel=syzcjj2"
                    target="_blank">
                <div class="recommend l" style="background-image: url(/static/index/course/images/box-java1.png);"></div>
            </a>
                <a href="#.com/sale/webfullstack?mc_marking=1eb5ce0be2ada8da4a6387b391f00b92&amp;mc_channel=syzcjj1"
                   target="_blank">
                    <div class="recommend l" style="background-image: url(/static/index/course/images/box-fe.png);"></div>
                </a>
                <a href="#.com/sale/javaarchitect?mc_marking=6b18866d206ad1c75273bf8eb194a0f9&amp;mc_channel=syzcjj3"
                   target="_blank">
                    <div class="recommend l" style="background-image: url(/static/index/course/images/box-java2.png);"></div>
                </a></div>
        </div>
    </div>
    <div class="bg000">
        <div class="container-types clearfix"><h3 class="types-title clearfix"><span>新上好课</span></h3>
            <div class="clearfix types-content">
                <#list newIndexCourseList as newIndexCourse>
                <div class="index-card-container course-card-container container">
                    <a target="_blank" class="course-card" href="/index/course/detail.do?courseNo=${newIndexCourse.course.courseNo}" data-track="sztj-1-1">
                        <#if newIndexCourse.indexUserCourse??>
                            <#if newIndexCourse.indexUserCourse.status = "1">
                                <div class="course-stat new">在学</div>
                            <#elseif newIndexCourse.indexUserCourse.status = "2">
                                <div class="course-stat new">学完</div>
                            </#if>
                        </#if>
                        <div class="course-card-top hashadow">
                            <img class="course-banner" src="${newIndexCourse.course.courseCover}">
                            <div class="course-label">
                                <#list newIndexCourse.courseTagList as courseTag>
                                    <label>${courseTag.courseTagName}</label>
                                </#list>
                            </div>
                        </div>
                        <div class="course-card-content">
                            <h3 class="course-card-name">${newIndexCourse.course.courseName}</h3>
                            <div class="clearfix course-card-bottom">
                                <div class="course-card-info">
                                    <#if newIndexCourse.course.courseDifficultLevel = "1">
                                        <span>简单</span>
                                    <#elseif newIndexCourse.course.courseDifficultLevel = "2">
                                        <span>普通</span>
                                    <#elseif newIndexCourse.course.courseDifficultLevel = "3">
                                        <span>困难</span>
                                    </#if>
                                    <span><i class="imv2-set-sns"></i>${newIndexCourse.course.courseLearnNum!'0'}</span>
                                    <span class="r js-hover-evaluation">${newIndexCourse.course.visitTimes!'0'}人次</span>
                                </div>
                                <div class="course-card-price">${newIndexCourse.course.courseFitPeople!''}</div>
                            </div>
                        </div>
                    </a>
                </div>
                </#list>
            </div>
        </div>
    </div>
    <div class="bgfff">
        <div class="container-types container-rank clearfix">
            <h3 class="types-title clearfix"><span>热门课程</span>
                <a href="#"
                   class="banner_wk r hide" target="_blank"></a></h3>
            <div class="clearfix types-content js-rank-content">
                <#list hotIndexCourseList as hotIndexCourse>
                <div class="index-card-container course-card-container container">
                    <a target="_blank" class="course-card" href="/index/course/detail.do?courseNo=${hotIndexCourse.course.courseNo}" data-track="xshk-1-1">
                        <#if hotIndexCourse.indexUserCourse??>
                            <#if hotIndexCourse.indexUserCourse.status = "1">
                                <div class="course-stat new">在学</div>
                            <#elseif hotIndexCourse.indexUserCourse.status = "2">
                                <div class="course-stat new">学完</div>
                            </#if>
                        </#if>
                        <div class="course-card-top hashadow">
                            <img class="course-banner" src="${hotIndexCourse.course.courseCover}">
                            <div class="course-label">
                                <#list hotIndexCourse.courseTagList as courseTag>
                                    <label>${courseTag.courseTagName}</label>
                                </#list>
                            </div>
                        </div>
                        <div class="course-card-content">
                            <h3 class="course-card-name">${hotIndexCourse.course.courseName}</h3>
                            <div class="clearfix course-card-bottom">
                                <div class="course-card-info">
                                    <#if hotIndexCourse.course.courseDifficultLevel = "1">
                                        <span>简单</span>
                                    <#elseif hotIndexCourse.course.courseDifficultLevel = "2">
                                        <span>普通</span>
                                    <#elseif hotIndexCourse.course.courseDifficultLevel = "3">
                                        <span>困难</span>
                                    </#if>
                                    <span><i class="imv2-set-sns"></i>${hotIndexCourse.course.courseLearnNum!'0'}</span>
                                    <span class="r js-hover-evaluation">${hotIndexCourse.course.visitTimes!'0'}人次</span>
                                </div>
                                <div class="course-card-price">${hotIndexCourse.course.courseFitPeople!''}</div>
                            </div>
                        </div>
                    </a>
                </div>
                </#list>
            </div>
        </div>
    </div>
</div>
<div class="footer idx-minwidth">
    <div class="container">
        <div class="footer-wrap idx-width">
            <div class="footer-sns clearfix">
<#--                <div class="l"><a href="javascript:void(0);" class="footer-sns-weixin" target="_blank" title="微信"> <i-->
<#--                        class="footer-sns-weixin-expand"></i> </a>-->
<#--                    <p>官方公众号</p></div>-->
<#--                <div class="l">-->
<#--                    <a href="http://weibo.com/u/3306361973" class="footer-sns-weibo hide-text"-->
<#--                                  target="_blank" title="新浪微博">新浪微博</a>-->
<#--                    <p>官方微博</p>-->
<#--                </div>-->
            </div>
        </div>
        <div class="footer-link">
            <a href="https://www.nwnu.edu.cn/" target="_blank" title="西北师范大学">西北师范大学</a>
            <a href="https://chem.nwnu.edu.cn/" target="_blank" title="西北师范大学化学化工学院">西北师范大学化学化工学院</a>
            <a href="#" target="_blank" title="快速连接二">快速连接二</a>
            <a href="#" target="_blank" title="快速连接三">快速连接二</a>
            <a href="#" target="_blank" title="快速连接四">快速连接四</a>
        </div>
        <div class="footer-copyright"><p><img draggable="false" class="moco-emoji" alt="©" src="/static/index/course/images/a9.png">西北师范大学-化工学院 Copyright © 2019-2030 All Rights Reserved 　陇ICP备17000462号-1</p></div>
    </div>
</div>
<div id="J_GotoTop" class="elevator">
    <a href="javascript:void(0)" class="elevator-top no-goto" style="" id="backTop">
        <i class="icon-up2"></i> <span class="">返回顶部</span>
    </a>
</div>
<script>
    // 菜单
    $(".menuContent .item a").mouseover(function () {
        let id = $(this).parent().data("id");
        $(this).css("background-color", "#6b7176");
        let className = ".submenu." + id;
        $(className).css("display", "block");
    }).mouseout(function () {
        let id = $(this).parent().data("id");
        $(this).css("background-color", "#2b333b");
        let className = ".submenu." + id;
        $(className).css("display", "none");
    });

    $(".submenu").mouseover(function () {
        let id = $(this).data("id");
        $.each($(".menuContent .item"), function (i, val) {
            if (id === $(val).data("id")) {
                $(val).find("a").css("background-color", "#6b7176");
                let className = ".submenu." + id;
                $(className).css("display", "block");
            }
        });
    }).mouseout(function () {
        let id = $(this).data("id");
        $.each($(".menuContent .item"), function (i, val) {
            if (id === $(val).data("id")) {
                $(val).find("a").css("background-color", "#2b333b");
                let className = ".submenu." + id;
                $(className).css("display", "none");
            }
        });
    });

    // 返回顶部
    $("#J_GotoTop").click(function () {
        $(document).scrollTop(0);
    });

    // 查询框
    $("#btn_search").click(function() {
        var courseName = $("#search-input").val();
        window.location.href = "/index/course/list.do?currentPage=1&courseName=" + courseName;
    });
</script>
</body>
</html>