<!DOCTYPE html>
<!-- saved from url=(0037)https://www.imooc.com/user/setbindsns -->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>慕课网</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">

    <meta property="qc:admins" content="77103107776157736375">
    <meta property="wb:webmaster" content="c4f857219bfae3cb">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta http-equiv="Cache-Control" content="no-transform ">
    <meta http-equiv="Cache-Control" content="no-siteapp">
    <link rel="dns-prefetch" href="https://www.imooc.com/">
    <link rel="dns-prefetch" href="https://img.imooc.com/">
    <link rel="dns-prefetch" href="https://img1.sycdn.imooc.com/">
    <link rel="apple-touch-icon" sizes="76x76" href="https://www.imooc.com/static/img/common/touch-icon-ipad.png">
    <link rel="apple-touch-icon" sizes="120x120"
          href="https://www.imooc.com/static/img/common/touch-icon-iphone-retina.png">
    <link rel="apple-touch-icon" sizes="152x152"
          href="https://www.imooc.com/static/img/common/touch-icon-ipad-retina.png">
    <meta name="keywords"
          content="慕课网，慕课官网，MOOC，移动开发，IT技能培训，免费编程视频，php开发教程，web前端开发，在线编程学习，html5视频教程，css教程，ios开发培训，安卓开发教程">
    <meta name="description"
          content="慕课网（IMOOC）是学习编程最简单的免费平台。慕课网提供了丰富的移动端开发、php开发、web前端、html5教程以及css3视频教程等课程资源。它富有交互性及趣味性，并且你可以和朋友一起编程。">

    <script charset="utf-8" async="" src="/static/index/config/js/jquery.js"></script>
    <link rel="stylesheet" href="/static/index/config/css/base.css" type="text/css">
    <link rel="stylesheet" href="/static/index/config/css/common-less.css" type="text/css">
    <link rel="stylesheet" href="/static/index/config/css/moco.min.css" type="text/css">
    <link rel="stylesheet" href="/static/index/config/css/swiper-3.4.2.min.css">
    <link rel="stylesheet" href="/static/index/config/css/common-less(1).css" type="text/css">
    <link rel="stylesheet" href="/static/index/config/css/profile-less.css" type="text/css">
    <link rel="stylesheet" href="/static/index/layui/css/layui.css" type="text/css">
    
    <script charset="utf-8" async="" src="/static/index/layui/layui.js"></script>
</head>
<body class="">

<!-- 窄条导航 -->
<div id="globalTopBanner"></div>
<div id="new_header">
    <div class="page-container new-header clearfix" id="nav">
        <ul class="nav-item">
            <li><a href="https://www.imooc.com/" target="_self" class="imooc">课程网站首页</a></li>
            <li><a href="https://www.imooc.com/course/list" target="_self">原创课程</a></li>
            <li><a href="https://coding.imooc.com/" target="_self">引进课程</a></li>
            <li><a href="https://class.imooc.com/" class="" target="_self">就业培训<i class="icn-new"></i></a></li>
            <li><a href="https://www.imooc.com/read" target="_self">其他链接</a></li>
            <li><a href="https://www.imooc.com/wenda" target="_self">其他链接</a></li>
        </ul>
        <div class="header-right">
            <div class="app-download" id="js-app-load">
                <a href="/index/login/logout.do" target="_blank">退出登录</a>
            </div>
        </div>
    </div>
</div>

<div id="main">
    <div class="settings-cont clearfix">
        <div class="setting-left l">
            <div class="avator-wapper">
                <div class="avator-mode">
                    <#if indexUser.userHeadSculpture?? && indexUser.userHeadSculpture=''>
                        <img class="avator-img" src="${indexUser.userHeadSculpture}" width="92" height="92">
                    <#else>
                        <img class="avator-img" src="/static/index/user/image/unkonw-user.jpg" width="92" height="92">
                    </#if>
                    <div class="update-avator" style="bottom: 0px;">
                        <p><a href="javascript:void(0);" class="js-avator-link">更换头像</a></p>
                    </div>
                </div>
                <div class="des-mode">
                    <p>${indexUser.userName!''}</p>
                    <span>${indexUser.userAccount!''}</span>
                    <dl class="js-auth-list clearfix auth-list">
                        <dd><i class="icon-set_sns"></i>
                            <div class="icon-tips hide">
                                <div class="triangle_border_up"><span></span></div>
                                还未实名认证
                            </div>
                        </dd>

                        <dd><i class="imv2-school"></i>
                            <div class="icon-tips hide">
                                <div class="triangle_border_up"><span></span></div>
                                还未学籍认证
                            </div>
                        </dd>

                        <dd><i class="icon-set_phone"></i>
                            <div class="icon-tips hide">
                                <div class="triangle_border_up"><span></span></div>
                                还未绑定手机
                            </div>
                        </dd>
                        <dd class="active"><i class="icon-set_email"></i>
                            <div class="icon-tips hide">
                                <div class="triangle_border_up"><span></span></div>
                                你已绑定邮箱
                            </div>
                        </dd>
                    </dl>
                </div>
            </div>
            <iframe src="/static/index/config/saved_resource.html" id="uploadtarget" name="uploadtarget" frameborder="0"
                    style="display:none;"></iframe>
            <div class="list-wapper">
                <h2>账户管理</h2>
                <div class="line"></div>
                <ul class="menu">
                    <li>
                        <a href="https://www.imooc.com/user/setbindsns" class="on">
                            帐号信息<span class="arr"><i class="icon-right2"></i></span>
                        </a>
                    </li>
<#--                    <li><a href="https://www.imooc.com/user/setprofile">个人信息<span class="arr"><i-->
<#--                                        class="icon-right2"></i></span></a></li>-->
<#--                    <li><a href="https://www.imooc.com/user/oplog">操作记录<span class="arr"><i-->
<#--                                        class="icon-right2"></i></span></a></li>-->
<#--                    <li><a href="https://www.imooc.com/user/authenticate">实名认证<span class="arr"><i-->
<#--                                        class="icon-right2"></i></span></a></li>-->
<#--                    <li><a href="https://www.imooc.com/user/certificate">学籍认证<span class="arr"><i-->
<#--                                        class="icon-right2"></i></span></a></li>-->
<#--                    <li><a href="https://www.imooc.com/user/address">收件地址<span class="arr"><i-->
<#--                                        class="icon-right2"></i></span></a></li>-->
                </ul>
            </div>
        </div>
        <div class="setting-right">
            <div class="setting-right-wrap wrap-boxes settings">

                <div class="page-settings">
                    <div class="common-title">账号信息</div>
                    <div class="line"></div>
                    <div class="setting">
                        <div class="contentBox">
                            <div class="bingd">
                                <div class="itemBox">
                                    <div class="left"><i class="icon-user"></i></div>
                                    <div class="center">
                                        <p><span class="font1">用户名： </span>
                                            <span class="font3">${indexUser.userName!''}
                                            </span>
                                        </p>
                                        <p class="font2">请使用正确的邮箱，此邮箱将会收到我们的通知信息！</p>
                                    </div>
                                    <div class="right">
                                        <a onclick="changeInfo('userName', '${indexUser.userName!''}')" class="change js-change moco-btn moco-btn-normal">更改</a>
                                    </div>
                                </div>
                                <div class="itemBox">
                                    <div class="left"><i class="icon-feedback"></i></div>
                                    <div class="center">
                                        <p><span class="font1">帐号： </span>
                                            <span class="font3">${indexUser.userAccount!''}
                                            </span>
                                        </p>
                                        <p class="font2">请使用正确的邮箱，此邮箱将会收到我们的通知信息！</p>
                                    </div>
                                </div>
                                <div class="itemBox">
                                    <div class="left"><i class="imv2-school"></i></div>
                                    <div class="center">
                                        <p>
                                            <span class="font1">用户类型： </span>
                                            <span class="font3">
                                                <#if indexUser.userType='1'>
                                                    校内学生
                                                <#elseif indexUser.userType='2'>
                                                    校外学生
                                                <#else>
                                                    老师
                                                </#if>
                                            </span>
                                        </p>
                                        <p class="font2">可用手机号加密码登录慕课网，可通过手机号找回密码</p>
                                    </div>
                                </div>
                                <div class="itemBox">
                                    <div class="left"><i class="icon-set_email"></i></div>
                                    <div class="center">
                                        <p><span class="font1">邮箱： </span>
                                            <span class="font3">${indexUser.userEmail!''}</span>
                                        </p>
                                        <p class="font2">请使用正确的邮箱，此邮箱将会收到我们的通知信息！</p>
                                    </div>
                                    <div class="right">
                                        <a onclick="changeInfo('userEmail', '${indexUser.userEmail!''}')" class="change js-change moco-btn moco-btn-normal">更改</a>
                                    </div>
                                </div>
                                <div class="itemBox">
                                    <div class="left"><i class="icon-set_phone"></i></div>
                                    <div class="center">
                                        <p>
                                            <span class="font1">手机： </span>
                                            <span class="font3">${indexUser.userMobile!''}</span>
                                        </p>
                                        <p class="font2">可用手机号加密码登录慕课网，可通过手机号找回密码</p>
                                    </div>
                                    <div class="right">
                                        <a onclick="changeInfo('userMobile', '${indexUser.userMobile!''}')" class="change js-change moco-btn moco-btn-normal">更改</a>
                                    </div>
                                </div>
                                <div class="itemBox bb0 h380">
                                    <div class="left"><i class="icon-set_key"></i></div>
                                    <div class="center">
                                        <p><span class="font1">密码</span> 已设置</p>
                                        <p class="font2">用于保护账号信息和登录安全</p>
                                    </div>
                                    <div class="right">
                                        <a onclick="changeInfo('userPassword', '${indexUser.userPassword!''}')" class="moco-btn moco-btn-normal js-changePWD">修改</a>
                                    </div>
                                    <div class="right"></div>
                                    <div class="cb"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <script type="text/javascript">
                    var hasPass;
                    hasPass = true;</script>


            </div>
        </div>
    </div>

</div>

<div id="footer" data="user,setbindsns">
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
                        <li><a href="https://www.imooc.com/index/companytrain" target="_blank" title="企业合作">企业合作</a>
                        </li>
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
                <p>Copyright <img draggable="false" class="moco-emoji" alt="©" src="/static/index/config/a9.png"> 2020 imooc.com
                    All Rights Reserved | 京ICP备 12003892号-11 <a
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
<script src="/static/index/config/visual"></script>
<script>
    var index;
    function changeInfo(filed, value) {
        layui.use(['layer', 'form'], function(){
            var layer = layui.layer
                ,form = layui.form;

            var commonHtml1 = '<div class="layui-form-item">' +
                '    <div class="layui-inline">' +
                '      <label class="layui-form-label">';
            var commonHtml2 = '</label>' +
                '      <div class="layui-input-inline">' +
                '        <input type="text" name="' + filed + '" id="' + filed + '" value="' + value + '" autocomplete="off" class="layui-input">' +
                '      </div>' +
                '    </div>';
            var html = '';
            if("userPassword" === filed) {
                html = '<div class="layui-form-item">' +
                    '    <div class="layui-inline">' +
                    '      <label class="layui-form-label">新密码</label>' +
                    '      <div class="layui-input-inline">' +
                    '        <input type="password" name="userPassword" id="userPassword" autocomplete="off" class="layui-input">' +
                    '      </div>' +
                    '    </div>' +
                    '    <div class="layui-inline">' +
                    '      <label class="layui-form-label">重复密码</label>' +
                    '      <div class="layui-input-inline">' +
                    '        <input type="password" name="rePassword" id="rePassword" autocomplete="off" class="layui-input">' +
                    '      </div>' +
                    '    </div>' +
                    '  </div>';
            } else if("userName" === filed) {
                html = commonHtml1 + '用户名' + commonHtml2;
            } else if("userEmail" === filed) {
                html = commonHtml1 + '用户邮箱' + commonHtml2;
            } else if("userMobile" === filed) {
                html = commonHtml1 + '用户电话' + commonHtml2;
            }
            html = html + '<div class="layui-form-item" style="margin-top: 50px;">' +
                '    <div class="layui-input-block">' +
                '      <a onclick="editInfo(\'' + filed + '\')" class="layui-btn layui-btn-normal layui-btn-sm">立即提交</a>' +
                '    </div>' +
                '  </div>';
            index = layer.open({
                type: '0',
                title: '修改信息',
                content: html
            });
        });
    }

    function editInfo(filed) {
        var data;
        if("userPassword" === filed) {
            data = {
                "field": filed,
                "password": $("#" + filed).val(),
                "rePassword": $("#rePassword").val(),
            };
        } else {
            data = {
                "field": filed,
                "value": $("#" + filed).val()
            };
        }

        $.ajax({
            url: '/index/user/modify_info.do',
            type: 'post',
            data: {data: JSON.stringify(data)},
            success: function (res) {
                layui.use(['layer', 'form'], function(){
                    layer.msg(res.message);
                    layer.close(index);
                    if("userPassword" === filed) {
                        setTimeout(function() {
                            window.location.href = "/index/login/logout.do";
                        }, 1000);
                    } else {
                        setTimeout(function() {
                            window.location.reload();
                        }, 1000);
                    }
                });
            }
        });
    }
</script>
</body>
</html>