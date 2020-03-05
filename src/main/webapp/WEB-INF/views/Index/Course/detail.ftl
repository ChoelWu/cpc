<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
        物理化学（上）
    </title>
    <link rel="stylesheet" href="/static/index/course/css/moco.css" type="text/css">
    <link rel="stylesheet" href="/static/index/course/css/swiper-3.css">
    <link rel="stylesheet" href="/static/index/course/css/lessonList.css">
    <script src="/static/index/course/js/jquery.js"></script>
<body>
<!-- 窄条导航 -->
<div id="globalTopBanner" style="display: none;">
    <div>
        <div class="close-adv imv2-close"></div>
        <img src="/static/index/course/images/course07.jpg"></div>
    <div>
        <div class="topInnerCon js-topInnerCon">
            <div class="topInnerTimeCon js-topInnerTimeCon"><span>距结束</span>
                <div class="timeout">06</div>
                <span>天</span>
                <div class="timeout">23</div>
                <span>时</span>
                <div class="timeout">20</div>
                <span>分</span>
                <div class="timeout">50</div>
                <span>秒</span></div>
        </div>
    </div>
</div>
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
                    <li class="header-signin">
                        <a href="#//www.imooc.com/user/newlogin" id="js-signin-btn">登录</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div id="main">
    <div class="course-infos">
        <div class="w pr">
            <div class="path">
                <a href="#//www.imooc.com/course/list">课程</a>
                <i class="path-split">\</i><a href="#//www.imooc.com/course/list?c=be">无机化学</a>
                <i class="path-split">\</i><a href="#//www.imooc.com/course/list?c=java">物理化学（上）</a>
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
                <a href="#//www.imooc.com/u/7459484/courses?sort=publish" target="_blank">白守理</a>
            </span>
                    <span class="job">教授</span>
                </div>

                <div class="static-item l">
                    <span class="meta">难度</span><span class="meta-value">困难</span>
                </div>
                <div class="static-item l">
                    <span class="meta">时长</span><span class="meta-value"> 2小时10分</span>
                </div>
                <div class="static-item l">
                    <span class="meta">学习人数</span><span class="meta-value js-learn-num">248</span>
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
                        <div class="chapter-description">
                            本章课程的主要内容说明。
                        </div>
                        <!-- 章节标题 end -->
                        <!-- 章节小节 -->
                        <ul class="video">
                            <#if chapter.lessonList??>
                            <#list chapter.lessonList as lesson>
                            <li data-media-id="20083">
                                <a href="lessonVideoAndSwf.html" class="J-media-item">
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
                    <div class="learn-btn">
                        <a href="#//www.imooc.com/video/20083"
                           class="moco-btn moco-btn-red moco-btn-lg J-learn-course">开始学习</a>
                    </div>
                    <div class="course-info-tip">
                        <dl class="first">
                            <dt>课程须知</dt>
                            <dd class="autowrap">
                                物理化学又称理论化学，是化学类的基础和门边缘学科，它是应用物理学原理和方法研究有关化学现象和化学过程的一门科学。
                            </dd>
                        </dl>
                        <dl>
                            <dt>老师告诉你能学到什么？</dt>
                            <dd class="autowrap">（１）物理化学的理论研究方法分为热力学方法、统计力学方法和量子力学方法。本课程要求较好地掌握应用经典热力学方法。
                                （２）通过本门课学习使学生比较牢固地掌握物理化学基础理论知识和计算方法，增长提出问题，分析问题和解决问题的能力。
                                （３）通过抽象思维建立起来的概念、模型的讲述，训练学生的科学抽象、逻辑推理能力。
                            </dd>
                        </dl>
                    </div>
                </div>
                <div class="js-commend-box">
                    <div class="mb40 recom-course-list-box"><h4>推荐课程</h4>
                        <ul class="js-recom-course moco-aside-course clearfix">
                            <li>
                                <div class="aside-course-img"><img src="/static/index/course/images/course03.jpg">
                                    <p class="aside-course-type">实战</p>
                                </div>
                                <div class="aside-course-content">
                                    <a href="#//coding.imooc.com/class/96.html" class="aside-course-name" target="_blank">推荐课程（此处为推荐课程）</a>
                                    <p class="aside-course-grade">普通</p>
                                    <div class="aside-course-dot"><i class="imv2-dot_samll"></i></div>
                                    <p class="aside-course-people"><i class="imv2-set-sns"></i><span>8034</span></p>
                                </div>
                            </li>
                            <li>
                                <div class="aside-course-img"><img src="/static/index/course/images/course05.jpg">
                                    <p class="aside-course-type">实战</p>
                                </div>
                                <div class="aside-course-content">
                                    <a href="#//coding.imooc.com/class/117.html" class="aside-course-name" target="_blank">24小时备考大学化学/普通化学/无机化学原理/竞赛入门</a>
                                    <p class="aside-course-grade">普通</p>
                                    <div class="aside-course-dot"><i class="imv2-dot_samll"></i></div>
                                    <p class="aside-course-people"><i class="imv2-set-sns"></i><span>4822</span></p>
                                </div>
                            </li>
                            <li>
                                <div class="aside-course-img">
                                    <img src="/static/index/course/images/course06.png">
                                    <p class="aside-course-type">实战</p>
                                </div>
                                <div class="aside-course-content">
                                    <a href="#//coding.imooc.com/class/134.html" class="aside-course-name" target="_blank">24小时备考大学化学/普通化学/无机化学原理/竞赛入门</a>
                                    <p class="aside-course-grade">困难</p>
                                    <div class="aside-course-dot"><i class="imv2-dot_samll"></i></div>
                                    <p class="aside-course-people"><i class="imv2-set-sns"></i><span>2166</span></p>
                                </div>
                            </li>
                            <li>
                                <div class="aside-course-img">
                                    <img src="/static/index/course/images/course09.jpg">
                                    <p class="aside-course-type">实战</p>
                                </div>
                                <div class="aside-course-content">
                                    <a href="#//coding.imooc.com/class/187.html" class="aside-course-name" target="_blank">24小时备考大学化学/普通化学/无机化学原理/竞赛入门</a>
                                    <p class="aside-course-grade">困难</p>
                                    <div class="aside-course-dot"><i class="imv2-dot_samll"></i></div>
                                    <p class="aside-course-people"><i class="imv2-set-sns"></i><span>4572</span></p>
                                </div>
                            </li>
                            <li>
                                <div class="aside-course-img">
                                    <img src="/static/index/course/images/course08.jpg">
                                    <p class="aside-course-type">实战</p>
                                </div>
                                <div class="aside-course-content">
                                    <a href="#//coding.imooc.com/class/303.html" class="aside-course-name" target="_blank">24小时备考大学化学/普通化学/无机化学原理/竞赛入门</a>
                                    <p class="aside-course-grade">普通</p>
                                    <div class="aside-course-dot"><i class="imv2-dot_samll"></i></div>
                                    <p class="aside-course-people"><i class="imv2-set-sns"></i><span>4671</span></p>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="js-tag-box">
                    <div class="mb40 all-attention-box">
                        <h4>热门专题标签</h4>
                        <div class="js-all-attention all-attention">
                            <a href="#//www.imooc.com/topic/ecom" target="_blank" data-id="8" class="style1">有机化学</a>
                            <a href="#//www.imooc.com/topic/javaecom" target="_blank" data-id="15" class="style2">无机化学</a>
                            <a href="#//www.imooc.com/topic/azmianshi" target="_blank" data-id="28" class="style5">大学化学</a>
                            <a href="#//www.imooc.com/topic/spring" target="_blank" data-id="23" class="style4">spring 实例教程</a>
                            <a href="#//www.imooc.com/topic/aztest" target="_blank" data-id="18" class="style6">物理化学</a>
                            <a href="#//www.imooc.com/topic/fullstack" target="_blank" data-id="29" class="style1">实验</a>
                            <a href="#//www.imooc.com/topic/androidsdk" target="_blank" data-id="17" class="style2">高分子</a>
                            <a href="#//www.imooc.com/topic/suanfa" target="_blank" data-id="11" class="style3">分析化学</a>
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
                        <li><a href="#//www.imooc.com/" target="_blank">西北师范大学</a></li>
                        <li><a href="#//www.imooc.com/index/companytrain" target="_blank" title="西北师范大学化学化工学院">西北师范大学化学化工学院</a>
                        </li>
                        <li><a href="#//www.imooc.com/about/job" target="_blank">快速连接二</a></li>
                        <li><a href="#//www.imooc.com/about/contact" target="_blank">快速连接三</a></li>
                        <li><a href="#//www.imooc.com/about/recruit" target="_blank">快速连接四</a></li>
                    </ul>
                </div>
                <p>Copyright <img draggable="false" class="moco-emoji" alt="©" src="course/files/a9.png"> 2019 imooc.com All
                    Rights Reserved | 京ICP备 12003892号-11 北京奥鹏文化传媒有限公司</p>
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