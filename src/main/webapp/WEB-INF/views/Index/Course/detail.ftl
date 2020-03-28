<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        课程平台-${course.course.courseName}
    </title>
    <link rel="stylesheet" href="/static/index/course/css/moco.css" type="text/css">
    <link rel="stylesheet" href="/static/index/course/css/swiper-3.css">
    <link rel="stylesheet" href="/static/index/course/css/lessonList.css">
    <script src="/static/index/course/js/jquery.js"></script>
<body>
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
    <div class="course-infos">
        <div class="w pr">
            <div class="path">
                <a href="#">课程平台首页</a>
                <i class="path-split">\</i><a href="#">${parentIndexCourseCate.courseCateName!''}</a>
                <i class="path-split">\</i><a href="#">${indexCourseCate.courseCateName!''}</a>
            </div>
            <div class="hd clearfix">
                <h2 class="l">${course.course.courseName}</h2>
            </div>

            <div class="statics clearfix">
                <div class="teacher-info l">
                    <a href="#//www.imooc.com/u/7459484/courses?sort=publish" target="_blank">
                        <img data-userid="7459484" class="js-usercard-dialog"
                             src="/static/index/course/images/course07.jpg" width="80" height="80">
                    </a>
                    <span class="tit">
                        <a>${course.course.courseAuthor!''}</a>
                    </span>
                    <span class="job">老师</span>
                </div>

                <div class="static-item l">
                    <span class="meta">难度</span><span class="meta-value">
                         <#if course.course.courseDifficultLevel = "1">
                             <span>简单</span>
                         <#elseif course.course.courseDifficultLevel = "2">
                             <span>普通</span>
                         <#elseif course.course.courseDifficultLevel = "3">
                             <span>困难</span>
                         </#if>
                    </span>
                </div>
                <div class="static-item l">
                    <span class="meta">时长</span><span class="meta-value"> ${course.course.courseDurationHour!'0'}小时${course.course.courseDurationMinute!'0'}分</span>
                </div>
                <div class="static-item l">
                    <span class="meta">学习人数</span><span class="meta-value js-learn-num">${course.course.courseLearnNum}</span>
                </div>
            </div>
        </div>
    </div>
    <div class="course-info-menu ">
        <div class="w">
            <ul class="course-menu">
                <li><a class="moco-change-big-btn active" id="learnOn" href="#//www.imooc.com/learn/1171">课程章节</a>
                </li>
            </ul>
        </div>
    </div>

    <!-- 课程面板 -->
    <div class="course-info-main clearfix w">
        <div class="content-wrap clearfix">
            <div class="content">
                <!-- 课程简介 -->
                <div class="course-description course-wrap">${course.course.courseIntro}</div>
                <!-- 课程简介 end -->

                <!-- 课程章节 -->
                <div class="course-chapters">
                    <#list course.chapterList as chapter>
                    <div class="chapter course-wrap ">
                        <!-- 章节标题 -->
                        <h3>
                            第${chapter_index + 1}章 ${chapter.chapter.chapterName}
                        </h3>
                        <!-- 章节标题 end -->
                        <!-- 章节小节 -->
                        <ul class="video">
                            <#if chapter.lessonList??>
                            <#list chapter.lessonList as lesson>
                            <li data-media-id="20083">
                                <a href="/index/course/video.do?lessonNo=${lesson.lessonNo}" class="J-media-item">
                                    <i class="imv2-play_circle type"></i>${chapter_index + 1}-${lesson_index + 1} ${lesson.lessonName} (02:13)
                                    <button class="r moco-btn moco-btn-red preview-btn">开始学习</button>
                                </a>
                            </li>
                            </#list>
                            </#if>
                        </ul>
                        <!-- 章节小节 end -->
                    </div>
                    </#list>
                </div>
                <!-- 课程章节 end -->
            </div><!--content end-->
            <div class="aside r">
                <div class="course-wrap course-aside-info js-usercard-box">
                    <div class="course-info-tip">
                        <dl class="first">
                            <dt>课程须知</dt>
                            <dd class="autowrap">
                                ${course.course.courseCaution!''}
                            </dd>
                        </dl>
                        <dl>
                            <dt>老师告诉你能学到什么？</dt>
                            <dd class="autowrap">
                                ${course.course.courseGoal!''}
                            </dd>
                        </dl>
                    </div>
                </div>
                <div class="js-commend-box">
                    <div class="mb40 recom-course-list-box"><h4>推荐课程</h4>
                        <ul class="js-recom-course moco-aside-course clearfix">
                            <#list hotIndexCourseList as hotIndexCourse>
                                <li>
                                    <div class="aside-course-img"><img src="${hotIndexCourse.course.courseCover}">
                                    </div>
                                    <div class="aside-course-content">
                                        <a href="#//coding.imooc.com/class/96.html" class="aside-course-name" target="_blank">${hotIndexCourse.course.courseName}</a>
                                        <#if hotIndexCourse.course.courseDifficultLevel = "1">
                                            <p class="aside-course-grade">简单</p>
                                        <#elseif hotIndexCourse.course.courseDifficultLevel = "2">
                                            <p class="aside-course-grade">普通</p>
                                        <#elseif hotIndexCourse.course.courseDifficultLevel = "3">
                                            <p class="aside-course-grade">困难</p>
                                        </#if>
                                        <div class="aside-course-dot"><i class="imv2-dot_samll"></i></div>
                                        <p class="aside-course-people"><i class="imv2-set-sns"></i><span>${hotIndexCourse.course.courseLearnNum!'0'}</span></p>
                                    </div>
                                </li>
                            </#list>
                        </ul>
                    </div>
                </div>
                <div class="js-tag-box">
                    <div class="mb40 all-attention-box">
                        <h4>热门专题标签</h4>
                        <div class="js-all-attention all-attention">
                            <#list indexCourseTagList as indexCourseTag>
                                <a href="" target="_blank" class="style${indexCourseTag_index + 1}">${indexCourseTag.courseTagName}</a>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <!-- 视频介绍 -->
    <div id="js-video-wrap" class="video pop-video" style="display:none">
        <div class="video_box" id="js-video"></div>
        <a href="javascript:;" class="pop-close icon-close"></a>
    </div>
</div>

<div id="footer" data="course,learn">
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
                        <li><a href="https://www.nwnu.edu.cn/" target="_blank" title="西北师范大学">西北师范大学</a></li>
                        <li><a href="https://chem.nwnu.edu.cn/" target="_blank" title="西北师范大学化学化工学院">西北师范大学化学化工学院</a>
                        </li>
                        <li><a href="#" target="_blank">快速连接二</a></li>
                        <li><a href="#" target="_blank">快速连接三</a></li>
                        <li><a href="#" target="_blank">快速连接四</a></li>
                    </ul>
                </div>
                <p>Copyright <img draggable="false" class="moco-emoji" alt="©" src="/static/index/course/images/a9.png"> 西北师范大学-化工学院 Copyright © 2019-2030 All Rights Reserved 　陇ICP备17000462号-1 </p>
            </div>
        </div>
    </div>
</div>

<div id="J_GotoTop" class="elevator">
    <a href="javascript:void(0)" class="elevator-top no-goto" id="backTop">
        <i class="icon-up2"></i>
        <span class="">返回顶部</span>
    </a>
</div>

<script>
    // 返回顶部
    $("#J_GotoTop").click(function () {
        $(document).scrollTop(0);
    });
</script>
</body>
</html>