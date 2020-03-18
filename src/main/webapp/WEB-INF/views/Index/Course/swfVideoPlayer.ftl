<!DOCTYPE html>
<html xml:lang="zh" lang="zh" id="auto-id-1573148872711">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${courseChapterLesson.course.courseName}</title>
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
        <a hidefocus="true" id="j-prev" class="cl-pre" style=""></a>
        <a hidefocus="true" id="j-next" class="cl-next" style=""></a>
        <div id="course-learn-box" class="course-learn">
            <div class="m-courselearn" id="auto-id-1573148872709">
                <p class="cl-title f-thide j-clTitle">
                    <span class="cl-chapter j-chapter">章节5</span>
                    <span class="cl-lesson j-lessonIndex">课时</span>
                    <span class="cl-lessonnum j-lessonnum">5-1</span>
                    <span class="cl-lessonname j-lessonname">热力学第三定律</span>
                </p>
                <div class="learn-total" id="auto-id-1573148872708">
                    <div id="lesson-learn-box" class="learn-box">
                        <div class="m-video-box" id="video-warp">
                            <object  classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000"
                                    codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0"
                                    width="100%" height="100%" align="middle">
                                <param name="allowScriptAccess" value="sameDomain"/>
                                <param name="movie" value="/static/index/course/flash1022.swf"/>
                                <param name="menu" value="false"/>
                                <param name="quality" value="high"/>
                                <param name="bgcolor" value="#000000"/>
                                <embed id="mySwf" src="/static/index/course/flash1022.swf" menu="false" quality="high" bgcolor="#000000" width="100%"
                                       height="100%" name="movie" align="middle" allowScriptAccess="sameDomain"
                                       type="application/x-shockwave-flash"
                                       pluginspage="http://www.macromedia.com/go/getflashplayer"/>
                            </object>
                            <br>
                        </div>
                    </div>
                    <div class="cl-info f-cb j-info" id="auto-id-1573149662855">
                        <a class="cli-base cli-intro j-introbox" title="暂停" onClick="movie.IsPlaying()?movie.StopPlay():movie.Play()">
                            <span class="j-introbtntxt" title="暂停">暂停</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="course-toolbar-box" class="g-sd2">
    <div class="m-ctb">
        <div class="courseintro">
            <div class="f-fl courseTitle j-courseintro">
                <div class="u-coursetitle f-fl"><a
                        href="https://study.163.com/course/courseMain.htm?courseId=1004570029" target="_blank"
                        class="j-info f-thide">${courseChapterLesson.course.courseName}</a>
                    <p class="j-info f-thide">讲师：${courseChapterLesson.course.courseAuthor}</p></div>
            </div>
            <a class="j-courseimg-link courseImgCover" target="_blank"
               href="#">
                <div class="courseImg-link"></div>
                <img src="${courseChapterLesson.course.courseCover}" class="f-fr courseImg j-courseimg"></a>
        </div>

        <div style=" height: 300px; background: #00b33b">
            <video id="example_video_1" class="video-js vjs-default-skin" controls preload="none" width="320" height="300"
                   poster="oceans.png">
                <source src="${indexLesson.lessonVideo}" type="video/mp4">
            </video>
        </div>
        <script>
            var player = videojs('example_video_1',{
                muted: true,
                controls : true/false,
                height: 300,
                width:320,
                loop : true,
                // 更多配置.....
            });
        </script>
        <div class="tabs j-tab-tabwrap">
            <a class="tabitem j-tab-tabitem tab-forum tabitem-pos" style="" id="auto-id-1573148873030">
                <span class="f-fl tabicon tabicon-pos0"></span>
                <span class="f-fl">目录</span>
            </a>
        </div>
        <div id="toolbar-box" class="tabarea j-tabarea" style="top: 442px !important;">
            <div class="tabbox j-tabbox" id="chapterList-tabbox" style="display: block;">
                <div class="bd j-bd"></div>
                <div id="tb-chapterlist-box" class="m-chapterlistBox">
                    <div class="m-chapterList" id="auto-id-1573148873048">
                        <#list courseChapterLesson.chapterList as chapter>
                            <div class="chapter">
                                <span class="f-fl ch">章节</span>
                                <span class="f-fl f-thide chaptericon">
                                <span>1</span>
                            </span>
                                <span class="f-fl f-thide chaptername">第${chapter_index + 1}章 ${chapter.chapter.chapterName}</span>
                            </div>
                            <#list chapter.lessonList as lesson>
                                <#if indexLesson.lessonNo = lesson.lessonNo>
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
<script>
    $(".section").click(function() {
        var lessonNo = $(this).data("lesson");
        window.location.href = "/index/course/video.do?lessonNo=" + lessonNo;
    });
</script>
</body>
</html>