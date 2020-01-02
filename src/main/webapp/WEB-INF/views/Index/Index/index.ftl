<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="format-detection" content="telephone=no"><!-- 禁止IPHONE识别手机号码 -->
    <meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
    <title>化学实验教学中心</title>
    <!-- css -->
    <link rel="stylesheet" type="text/css" href="/static/index/index/css/index.css">
    <link rel="stylesheet" type="text/css" href="/static/index/index/css/aos.css">
    <link rel="stylesheet" type="text/css" href="/static/index/index/css/style.css">
    <style>
        .ship .btns {

        }

        .ship .btns .btn {
            background-color: #0075bd;
            color: #fff;
            padding: 20px;
            text-align: center;
            margin: 24px 0;
            border-radius: 10px;
            cursor: pointer;
        }

        .ship .btns .btn:hover {
            background-color: #FFCC00;
        }

        .ship .btns .btn.active {
            background-color: #FFCC00;
        }
    </style>
    <!-- js -->
    <script type="text/javascript" src="/static/index/index/js/jquery1.42.min.js"></script>
    <script type="text/javascript" src="/static/index/index/js/jquery.SuperSlide.2.1.3.js"></script>
</head>

<body>

<!-- 头部 -->
<div class="head">
    <div>
        <div class="shangb">
            <div class="logo"><a href="index.html"><img src="/static/index/index/images/logo.png"></a></div>
        </div>
        <div class="dh">
            <ul>
                <#list navList as nav>
                    <li>
                        <a href="<#if nav.indexChannel.channelType='3'>${nav.indexChannel.channelUrl}<#else>/index/index/cate.do?no=${nav.indexChannel.channelNo}</#if>"><p>${nav.indexChannel.channelName?substring(0,2)}</p><p>${nav.indexChannel.channelName?substring(2,4)}</p></a>
                        <#list nav.childChannelList as childNav>
                            <dl><dd><a href="<#if childNav.channelType='3'>${childNav.channelUrl}<#else>/index/index/cate.do?no=${childNav.channelNo}</#if>">${childNav.channelName}</a></dd></dl>
                        </#list>
                    </li>
                </#list>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $(".head .dh ul li").hover(function () {
            $(this).find("dl").slideToggle(200);
            // $(this).find("dl").stop().slideToggle(200);
        });
    });
</script>


<div class="bj_huaw" aos="fade-up" style="width:1200px; margin: 0 auto"></div>
<div class="newsTop" style="width: 1200px; margin: 0 auto;">
    <div class="left" style="float: left;border:0; padding-right: 0;" aos="fade-left">
        <div id="slideBox" class="slideBox" style="">
            <div class="hd">
                <ul>
                    <#list sliderArticleList as article>
                        <li>${article_index + 1}</li>
                    </#list>
                </ul>
            </div>
            <div class="bd">
                <ul>
                    <#list sliderArticleList as article>
                    <li><a href="/index/index/article.do?no=${article.articleNo}" target="_blank"><img src="${article.articleCover}"/></a></li>
                    </#list>
                </ul>
            </div>

            <!-- 下面是前/后按钮代码，如果不需要删除即可 -->
            <a class="prev" href="javascript:void(0)"></a>
            <a class="next" href="javascript:void(0)"></a>

        </div>
    </div>
    <div class="left" style="float: left;border:0; margin-left: 140px; padding-right: 0;" aos="fade-left">
        <div><a href="/index/index/cate.do?no=${newArticleList[0].channelNo}"><h2>新闻通知</h2></a></div>
        <div class="below-left-text">
            <div class="left-text-1">
                <dl>
                    <dt> <a href="/index/index/article.do?no=${newArticleList[0].articleNo}"><img src="<#if !(newArticleList[0].articleCover?? && "" != newArticleList[0].articleCover)>/static/index/index/images/IMG_1243.JPG<#else>${newArticleList[0].articleCover}</#if>" width="145" height="73"></a> </dt>
                    <dd>
                        <p class="text-title"><a href="/index/index/article.do?no=${newArticleList[0].articleNo}" target="_blank" title="${newArticleList[0].articleName}">${newArticleList[0].articleName}</a></p>
                        <p class="text-elli"><a href="/index/index/article.do?no=${newArticleList[0].articleNo}" target="_blank">${newArticleList[0].articleSummary}...</a></p>
                        <p style="color: #ccc; font-size: 12px;">[${newArticleList[0].publishTime?string("yyyy-MM-dd")}]</p>
                    </dd>
                </dl>

            </div>
            <div class="left-text-2">
                <ul>
                    <#list newArticleList as article>
                        <#if article_index != 0>
                            <li><a href="/index/index/article.do?no=${article.articleNo}" target="_blank" title="${article.articleName}"><#if (article.articleName?length > 24)>${article.articleName?substring(0,24)} ...<#else>${article.articleName}</#if> <span style="height: 35px;display: block;line-height: 50px;">[${article.publishTime?string("yyyy-MM-dd")}]</span></a></li>
                        </#if>
                    </#list>
                </ul>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    jQuery(".slideBox").slide({mainCell: ".bd ul", autoPlay: true});
</script>

<!-- 新闻 -->
<div class="px1200 cgNews" style="margin-top: 30px;">
    <div class="newsTop">
        <div class="left" aos="fade-right">
            <div><a href="/index/index/cate.do?no=${trendArticleList[0].channelNo}"><h2>社会服务</h2></a></div>
            <div class="gonggao-list" id="gonggao-list">
                <#list trendArticleList as article>
                <dl>
                    <a href="/index/index/article.do?no=${article.articleNo}">
                        <dt><p class="riqi">${article.publishTime?string("dd")}</p><p class="nian">${article.publishTime?string("yyyy-MM")}</p></dt>
                        <dd><p class="hd-title">&nbsp;${article.articleName}</p></dd>
                        <div class="clear"></div>
                    </a>
                </dl>
                </#list>
            </div>
        </div>
        <div class="left" style="float: right;border:0; padding-right: 0;" aos="fade-left">
            <div><h2>更多信息</h2></div>
            <div class="paragraph" style="margin-top: 15px;">
                ${indexMoreInfoChannel.channelIntro} ...&nbsp;&nbsp;&nbsp;&nbsp; <a href="#" style="color: #999; font-size: 12px;">>>&nbsp;阅读更多</a>
            </div>
        </div>
    </div>
    <div class="ship" style="margin-bottom: 20px;" aos="fade-up">
        <div class="bt"><h2>视频资源</h2></div>
        <div class="shipN">
            <div class="left">
                <!--<a href="html/zhongxingaikuang.html">
                    <img src="images/principal.jpg" alt="中心主任">
                    <span>中心主任：权正军</span>
                </a>-->
                <div id="video" style="width: 600px;height: 344px;"></div>
            </div>
            <div class="right">
                <div class="btns">
                    <div class="btn active" data-url="/static/index/index/mp4/实验教学中心总体情况视频文件.mp4">实验教学中心总体情况视频文件</div>
                    <div class="btn" data-url="/static/index/index/mp4/典型教学案例视频文件1.mp4">典型教学案例视频文件1</div>
                    <div class="btn" data-url="/static/index/index/mp4/典型教学案例视频文件2.mp4">典型教学案例视频文件2</div>
                    <div class="btn" data-url="/static/index/index/mp4/典型教学案例视频文件3.mp4">典型教学案例视频文件3</div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="px1200" style="overflow: hidden; padding-bottom: 30px;" aos="fade-up">
    <div class="dtAn"><a href="#">获奖证书</a></div>
    <div class="picScroll-left">
        <div class="bd">
            <ul class="picList">
                <li>
                    <div class="pic"><a href="html/jiaoshijiaogai.html" target="_blank"><img
                            src="/static/index/index/images/jiaoshijiaogai1.jpg"/></a></div>
                </li>
                <li>
                    <div class="pic"><a href="html/jiaoshijiaogai.html" target="_blank"><img
                            src="/static/index/index/images/jiaoshijiaogai2.jpg"/></a></div>
                </li>
                <li>
                    <div class="pic"><a href="html/xueshenghuojiang.html" target="_blank"><img
                            src="/static/index/index/images/xueshenghuojiang2.jpg"/></a></div>
                </li>
                <li>
                    <div class="pic"><a href="html/xueshenghuojiang.html" target="_blank"><img
                            src="/static/index/index/images/xueshenghuojiang3.jpg"/></a></div>
                </li>
                <li>
                    <div class="pic"><a href="html/xueshenghuojiang.html" target="_blank"><img
                            src="/static/index/index/images/xueshenghuojiang4.jpg"/></a></div>
                </li>
                <li>
                    <div class="pic"><a href="html/xueshenghuojiang.html" target="_blank"><img
                            src="/static/index/index/images/xueshenghuojiang5.jpg"/></a></div>
                </li>
                <li>
                    <div class="pic"><a href="html/xueshenghuojiang.html" target="_blank"><img
                            src="/static/index/index/images/xueshenghuojiang7.jpg"/></a></div>
                </li>
                <li>
                    <div class="pic"><a href="html/xueshenghuojiang.html" target="_blank"><img
                            src="/static/index/index/images/xueshenghuojiang8.jpg"/></a></div>
                </li>
                <li>
                    <div class="pic"><a href="html/xueshenghuojiang.html" target="_blank"><img
                            src="/static/index/index/images/xueshenghuojiang9.jpg"/></a></div>
                </li>
                <li>
                    <div class="pic"><a href="html/xueshenghuojiang.html" target="_blank"><img
                            src="/static/index/index/images/xueshenghuojiang10.jpg"/></a></div>
                </li>
                <li>
                    <div class="pic"><a href="html/xueshenghuojiang.html" target="_blank"><img
                            src="/static/index/index/images/xueshenghuojiang11.jpg"/></a></div>
                </li>
                <li>
                    <div class="pic"><a href="html/xueshenghuojiang.html" target="_blank"><img
                            src="/static/index/index/images/xueshenghuojiang12.jpg"/></a></div>
                </li>
                <li>
                    <div class="pic"><a href="html/xueshenghuojiang.html" target="_blank"><img
                            src="/static/index/index/images/xueshenghuojiang13.jpg"/></a></div>
                </li>
                <li>
                    <div class="pic"><a href="html/xueshenghuojiang.html" target="_blank"><img
                            src="/static/index/index/images/xueshenghuojiang14.jpg"/></a></div>
                </li>
                <li>
                    <div class="pic"><a href="html/xueshenghuojiang.html" target="_blank"><img
                            src="/static/index/index/images/xueshenghuojiang15.jpg"/></a></div>
                </li>
            </ul>
        </div>
    </div>

    <script type="text/javascript">
        jQuery(".picScroll-left").slide({
            titCell: ".hd ul",
            mainCell: ".bd ul",
            autoPage: true,
            effect: "leftMarquee",
            autoPlay: true,
            vis: 4,
            trigger: "click",
            pnLoop: true,
            interTime: 10
        });
    </script>
</div>
<div class="bj_huaw1"></div>
<!-- footer -->
<div class="footerBeiJ" aos="fade-up">
    <div class="px1200">
        <div class="ditu_t">
            <ul>
                <li><a href="http://chem.nwnu.edu.cn/2012/0420/c373a20203/page.htm"><p><img src="/static/index/index/images/quan.jpg"></p>
                    <span>权正军教授</span></a></li>
                <li><a href="http://chem.nwnu.edu.cn/2012/0420/c373a20191/page.htm"><p><img src="/static/index/index/images/yzw.jpg"></p>
                    <span>杨志旺教授</span></a></li>
                <li><a href="http://chem.nwnu.edu.cn/2012/0420/c373a20242/page.htm"><p><img src="/static/index/index/images/xzh.jpg"></p>
                    <span>薛中华教授</span></a></li>
                <li><a href="http://chem.nwnu.edu.cn/2019/0116/c373a103357/page.htm"><p><img src="/static/index/index/images/mhc.jpg"></p>
                    <span>马恒昌教授</span></a></li>
            </ul>
        </div>
    </div>
    <div class="footerZd">
        <div class="px1200">
            <div class="footerLogo"><img src="/static/index/index/images/logoFooter.png"></div>
            <div class="lianxDiz">
                <p>0931-7972626</p>
                <p class="p1">quanzj@nwnu.edu.cn</p>
                <p class="p2">西北师范大学新校区</p>
            </div>
        </div>
        <div class="beian">
            <div class="px1200">
                <p>西北师范大学-化工学院 Copyright © 2019-2030 All Rights Reserved 　陇ICP备17000462号-1 技术支持: Venus</p>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {

        $(".footerZd .yq").hover(function () {
            $(".xl").slideToggle();
        });

    });
</script>


</body>
</html>
<script type="text/javascript" src="/static/index/index/js/highlight.min.js"></script>
<script type="text/javascript" src="/static/index/index/js/aos.js"></script>
<script type="text/javascript" src="/static/index/index/ckplayer/ckplayer/ckplayer.js"></script>

<script>
    var player = new ckplayer({
        container: '#video',//“#”代表容器的ID，“.”或“”代表容器的class
        variable: 'player',//该属性必需设置，值等于下面的new chplayer()的对象
        video: 'mp4/实验教学中心总体情况视频文件.mp4',//视频地址
        autoplay: false
    });
    AOS.init({
        easing: 'ease-out-back',
        duration: 1000
    });
    var prev = $('.ship .btns .btn.active');
    $('.ship .btns .btn').click(function (e) {
        if ($(e.target).hasClass('active')) {
            return;
        }
        $(e.target).addClass('active');
        if (prev !== null) {
            prev.removeClass('active');
        }
        prev = $(e.target);
        var url = $(e.target).attr('data-url');
        player.newVideo({
            autoplay: true,
            video: url
        });
    })
</script>

<script>
    hljs.initHighlightingOnLoad();

    $('.hero__scroll').click(function (e) {
        $('html, body').animate({
            scrollTop: $(window).height()
        }, 1200);
    });
</script>