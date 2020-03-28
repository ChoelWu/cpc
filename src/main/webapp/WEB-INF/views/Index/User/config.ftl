<!DOCTYPE html>
<!-- saved from url=(0037)https://www.imooc.com/user/setbindsns -->
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>个人信息设置</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <meta name="renderer" content="webkit">

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
            <li><a href="#" target="_self">专题页面1</a></li>
            <li><a href="#" target="_self">专题页面2</a></li>
            <li><a href="#" target="_self">专题页面3</a></li>
            <li><a href="#" target="_self">专题页面4</a></li>
            <li><a href="#" target="_self">专题页面5</a></li>
        </ul>
        <div class="header-right">
            <div class="app-download" id="js-app-load">
                <a onclick="logout();" style="cursor: pointer;">退出登录</a>
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
                                        <p class="font2">此用户名将用于平台交流，让别人更容易记住你吧！</p>
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
                                        <p class="font2">此账号为登录账号，无法修改！若存在疑问请联系管理员！</p>
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
                                        <p class="font2">您在平台的身份！</p>
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
                                        <p class="font2">未来将用于联系您！</p>
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
            </div>
        </div>
    </div>

</div>

<div id="footer" data="user,setbindsns">
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
                <p>Copyright © 西北师范大学-化工学院 Copyright © 2019-2030 All Rights Reserved 　陇ICP备17000462号-1 </p>
            </div>
        </div>
    </div>
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
                            logout();
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

    function logout() {
        $.ajax({
            url: '/index/login/logout.do',
            type: 'post',
            success: function (res) {
                // 退出登录失败
                if('1' !== res.status && 1 !== res.status) {
                    setTimeout(function() {
                        layui.use(['layer'], function(){
                            var layer = layui.layer;
                            layer.msg("操作失败！");
                        });
                    }, 1000);
                }

                // 跳转至课程首页
                window.location.href = "/index/course/index.do";
            }
        });
    }
</script>
</body>
</html>