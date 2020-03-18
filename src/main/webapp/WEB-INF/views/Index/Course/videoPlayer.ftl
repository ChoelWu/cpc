<!DOCTYPE html>
<html xml:lang="zh" lang="zh" id="auto-id-1573148872711">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${courseChapterLesson.course.courseName}-${lessonInfo.indexLesson.lessonName}</title>
    <link rel="stylesheet" href="/static/index/course/css/pt_newpages_common_head_6b95ec34c7e4083bcbf1986fcf77fadb.css">
    <link rel="stylesheet" name="cssUrl" href="/static/index/course/css/a75e718e9c6704281a16855bf43a5155.css">
    <link rel="stylesheet" name="cssUrl" href="/static/index/course/css/lessonVideo.css">
    <link rel="stylesheet" name="cssUrl" href="/static/index/course/css/lessonVideo2.css">
    <link href="/static/index/course/css/video-js.mmin.css" rel="stylesheet">
    <script src="/static/index/course/js/video.min.js"></script>
    <script src="/static/index/course/js/jquery.js"></script>
</head>
<body class="m-learn-web auto-1573148872634-parent" id="auto-id-1573148872652" style="padding-top: 0px;">
<div class="coursebox f-cb" id="j-coursebox">
    <div class="m-courselb" id="auto-id-1573148872710">
        <div class="cl-bg"></div>
        <a hidefocus="true" id="j-back" class="cl-back" href="/index/course/detail.do?courseNo=${courseChapterLesson.course.courseNo}">返回课程主页</a>
        <#if lessonInfo.preLesson??>
            <a hidefocus="true" id="j-prev" class="cl-pre" href="/index/course/video.do?lessonNo=${lessonInfo.preLesson.lessonNo}" style=""></a>
        </#if>
        <#if lessonInfo.nextLesson??>
        <a hidefocus="true" id="j-next" class="cl-next" href="/index/course/video.do?lessonNo=${lessonInfo.nextLesson.lessonNo}" style=""></a>
        </#if>
        <div id="course-learn-box" class="course-learn">
            <div class="m-courselearn" id="auto-id-1573148872709">
                <p class="cl-title f-thide j-clTitle">
                    <span class="cl-chapter j-chapter">章节${lessonInfo.chapterSort}</span>
                    <span class="cl-lesson j-lessonIndex">课时</span>
                    <span class="cl-lessonnum j-lessonnum">${lessonInfo.chapterSort}-${lessonInfo.lessonSort}</span>
                    <span class="cl-lessonname j-lessonname">${lessonInfo.indexLesson.lessonName}</span>
                </p>
                <div class="learn-total" id="auto-id-1573148872708">
                    <div id="lesson-learn-box" class="learn-box">
                        <div class="m-video-box" id="video-warp">
                            <video id="example_video_1" class="video-js vjs-default-skin" controls preload="none"
                                   width="100%" height="100%" poster="/static/index/course/oceans.png">
                                <source src="${lessonInfo.indexLesson.lessonVideo}" type="video/mp4">
                            </video>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var warp_width = $("#video-warp").width();
    var warp_height = $("#video-warp").height();
    var player = videojs('example_video_1', {
        muted: true,
        controls: true / false,
        height: warp_height,
        width: warp_width,
        loop: true,
        // 更多配置.....
    });
</script>
<div id="course-toolbar-box" class="g-sd2">
    <div class="m-ctb">
        <div class="courseintro">
            <div class="f-fl courseTitle j-courseintro">
                <div class="u-coursetitle f-fl">
                    <a href="/index/course/detail.do?courseNo=${courseChapterLesson.course.courseNo}" target="_blank"
                            class="j-info f-thide">${courseChapterLesson.course.courseName}</a>
                    <p class="j-info f-thide">讲师：${courseChapterLesson.course.courseAuthor}</p>
                </div>
            </div>
            <a class="j-courseimg-link courseImgCover" target="_blank" href="#">
                <div class="courseImg-link"></div>
                <img src="${courseChapterLesson.course.courseCover}" class="f-fr courseImg j-courseimg">
            </a>
        </div>
        <div class="tabs j-tab-tabwrap">
            <a class="tabitem j-tab-tabitem tab-forum tabitem-pos" style="" id="auto-id-1573148873030">
                <span class="f-fl tabicon tabicon-pos0"></span>
                <span class="f-fl">目录</span>
            </a>
        </div>
        <div id="toolbar-box" class="tabarea j-tabarea">
            <div class="tabbox j-tabbox" id="chapterList-tabbox" style="display: block;">
                <div class="bd j-bd"></div>
                <div id="tb-chapterlist-box" class="m-chapterlistBox">
                    <div class="m-chapterList" id="auto-id-1573148873048">
                        <#list courseChapterLesson.chapterList as chapter>
                            <div class="chapter">
                                <span class="f-fl ch">章节</span>
                                <span class="f-fl f-thide chaptericon">
                                <span>${chapter_index + 1}</span>
                            </span>
                                <span class="f-fl f-thide chaptername">第${chapter_index + 1}章 ${chapter.chapter.chapterName}</span>
                            </div>
                            <#list chapter.lessonList as lesson>
                                <#if lessonInfo.indexLesson.lessonNo = lesson.lessonNo>
                                    <div class="section section-cur" data-lesson="${lesson.lessonNo}">
                                        <span class="f-fl f-thide ks" style="color: rgb(153, 153, 153);">${chapter_index + 1}-${lesson_index + 1}</span>
                                        <span class="f-fl ksw" style="background: rgb(58, 62, 74);">
                                            <span class="u-clicon ksicon ksicon-30" title="已完成"></span>
                                        </span>
                                        <span class="f-fl ksname" style="color: rgb(153, 153, 153);">${lesson.lessonName}</span>
                                        <span class="f-fr ksinfo">
                                            <span class="f-fr ksinfoicon ksinfoicon-2" title="视频"></span>
                                            <span class="f-fr flag flag-10" title=""></span>
                                        </span>
                                    </div>
                                <#else>
                                    <div class="section" data-lesson="${lesson.lessonNo}">
                                        <span class="f-fl f-thide ks" style="color: rgb(153, 153, 153);">${chapter_index + 1}-${lesson_index + 1}</span>
                                        <span class="f-fl ksw" style="background: rgb(245, 247, 250);">
                                            <span class="u-clicon ksicon ksicon-30" title="已完成"></span>
                                        </span>
                                        <span class="f-fl ksname" style="color: rgb(153, 153, 153);">${lesson.lessonName}</span>
                                        <span class="f-fr ksinfo">
                                            <span class="f-fr ksinfoicon ksinfoicon-2" title="视频"></span>
                                            <span class="f-fr flag flag-10" title=""></span>
                                        </span>
                                    </div>
                                </#if>
                            </#list>
                        </#list>
                    </div>
                </div>
            </div>
            <div class="tabbox j-tabbox" id="forum-tabbox"></div>
            <div class="tabbox j-tabbox" id="note-tabbox"></div>
            <div class="tabbox asktabbox j-tabbox" id="ask-tabbox"></div>
        </div>
    </div>
    <div class="m-cltip x-hide" id="auto-id-1573148873047"><p class="title j-title"></p>
        <p class="content j-content"></p>
        <div class="j-corner corner coursesprite f-pa"></div>
    </div>
</div>
<div class="ux-notify ux-notify-topcenter ">
    <!--Regular list-->
</div>
<div class="ux-loading" style="display: none;">
    <!--Regular if0-->
    <!--Regular if1-->
    <div class="ux-loading_spinner">
        <div class="ux-loading_spinner_rect1"></div>
        <div class="ux-loading_spinner_rect2"></div>
        <div class="ux-loading_spinner_rect3"></div>
        <div class="ux-loading_spinner_rect4"></div>
        <div class="ux-loading_spinner_rect5"></div>
    </div>

</div>
<div class="um-notify ">
    <!--Regular list-->
</div>

<div class="ux-notify ux-notify-topcenter ">
    <!--Regular list-->
</div>
<div class="um-notify ">
    <!--Regular list-->
</div>

<div data-v-57888216="" class="micro-live-alert">
    <div data-v-57888216="" class="micro-live-alert-wrap"><a data-v-57888216=""><img data-v-57888216=""
                                                                                     class="live-img"></a> <span
                data-v-57888216="" class="close-btn"><span data-v-57888216=""
                                                           class="ux-icon-broswer-close-slim"></span></span></div>
</div>


<div class="m-side-operation auto-1573148872634" id="j-side-operation">
    <div class="global-promotion-item"><a class="promotion-link"
                                          href="https://study.163.com/smartSpec/intro.htm?inref=web_index_right_wzy"
                                          target="_blank"> <img src="./files/c2d85ad0ca91493c96bdf90c1e2e7fa7.png"
                                                                alt=""> </a></div>
    <div class="side-service-item"><a class="item-link-block feedback" data-name="在线咨询" id="auto-id-1573148872702"> <i
                    class="f-icon ux-icon-service  icon-hover-none"></i> <span class="item-hover-text">在线咨询</span> </a>
    </div>
    <div class="line-wrap">
        <div class="line"></div>
    </div>
    <div class="side-service-item "><a class="item-link-block app-download" data-name="手机课堂" href="javascript:void(0)">
            <i class="f-icon icon-mobile ux-icon ux-icon-mobile-plat icon-hover-none"></i> <span
                    class="item-hover-text">手机课堂</span>
            <div class="qrcode-bubble">
                <div class="qrcode">
                    <div class="download"><img src="./files/sideBar90.png">
                        <p>下载App</p></div>
                    <div class="follow-weixin"><img src="./files/weixin.png">
                        <p>关注微信</p></div>
                </div>
                <div class="arrow">
                    <div class="arrow-border"></div>
                    <div class="arrow-cnt"></div>
                </div>
            </div>
        </a></div>
    <div class="line-wrap">
        <div class="line"></div>
    </div>
    <div class="side-service-item "><a class="item-link-block side-service-top" data-name="回到顶部"
                                       href="javascript:void(0)" id="auto-id-1573148872701"> <i
                    class="f-icon icon-totop ux-icon  ux-icon-scroll-top icon-hover-none"></i> <span
                    class="item-hover-text scrtop-text">返回顶部</span> </a></div>
</div>
<iframe src="./files/localsync(1).html" style="display: none;"></iframe>
<div style="top: 0px; left: 0px; visibility: hidden; position: absolute; width: 1px; height: 1px;">
    <iframe style="height:0px; width:0px;" src="./files/delegate.html"></iframe>
</div>
<div class="layer-1" id="YSF-BTN-HOLDER">
    <div id="YSF-CUSTOM-ENTRY-1"><img src="./files/sdk_res_kefu_custom_1.png"></div>
    <span id="YSF-BTN-CIRCLE" style="display: none;"></span>
    <div id="YSF-BTN-BUBBLE" style="display: none;">
        <div id="YSF-BTN-CONTENT"></div>
        <span id="YSF-BTN-ARROW"></span><span id="YSF-BTN-CLOSE"></span></div>
</div>
<div id="auto-id-1573148872976"></div>
<div>
    <div id="loadingMask" class="loading-mask" style="z-index: 10001; display: none;"></div>
    <div id="loadingPb" class="ui-loading f-cb" style="z-index: 10002; display: none;"></div>
</div>
<script>
    $(".section").click(function() {
        var lessonNo = $(this).data("lesson");
        window.location.href = "/index/course/video.do?lessonNo=" + lessonNo;
    });
</script>
</body>
</html>