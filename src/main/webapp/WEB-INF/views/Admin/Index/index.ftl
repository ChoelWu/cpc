<!doctype html>
<html class="x-admin-sm">
<head>
    <meta charset="UTF-8">

    <title>课程内容管理平台</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="stylesheet" href="/static/admin/css/font.css">
    <link rel="stylesheet" href="/static/admin/css/xadmin.css">
    <!-- <link rel="stylesheet" href="/static/admin/css/theme5.css"> -->
    <script src="/static/admin/lib/layui/layui.js" charset="UTF-8"></script>
    <script type="text/javascript" src="/static/admin/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>
        // 是否开启刷新记忆tab功能
        // var is_remember = false;
    </script>
</head>
<body class="index">
<!-- 顶部开始 -->
<div class="container">
    <div class="logo">
        <a href="/static/admin/index.html">X-admin v2.2</a></div>
    <div class="left_open">
        <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
    </div>
<#--    <ul class="layui-nav left fast-add" lay-filter="">-->
<#--        <li class="layui-nav-item">-->
<#--            <a href="javascript:;">+新增</a>-->
<#--            <dl class="layui-nav-child">-->
<#--                <!-- 二级菜单 &ndash;&gt;-->
<#--                <dd>-->
<#--                    <a onclick="xadmin.open('最大化','http://www.baidu.com','','',true)">-->
<#--                        <i class="iconfont">&#xe6a2;</i>弹出最大化</a></dd>-->
<#--                <dd>-->
<#--                    <a onclick="xadmin.open('弹出自动宽高','http://www.baidu.com')">-->
<#--                        <i class="iconfont">&#xe6a8;</i>弹出自动宽高</a></dd>-->
<#--                <dd>-->
<#--                    <a onclick="xadmin.open('弹出指定宽高','http://www.baidu.com',500,300)">-->
<#--                        <i class="iconfont">&#xe6a8;</i>弹出指定宽高</a></dd>-->
<#--                <dd>-->
<#--                    <a onclick="xadmin.add_tab('在tab打开','member-list.html')">-->
<#--                        <i class="iconfont">&#xe6b8;</i>在tab打开</a></dd>-->
<#--                <dd>-->
<#--                    <a onclick="xadmin.add_tab('在tab打开刷新','member-del.html',true)">-->
<#--                        <i class="iconfont">&#xe6b8;</i>在tab打开刷新</a></dd>-->
<#--            </dl>-->
<#--        </li>-->
<#--    </ul>-->
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">${adminUser.userName}</a>
            <dl class="layui-nav-child">
                <!-- 二级菜单 -->
                <dd><a onclick="xadmin.add_tab('个人信息','/admin/user/personal_page.do')">个人信息</a></dd>
                <dd><a onclick="logout();">退出登录</a></dd>
            </dl>
        </li>
<#--        <li class="layui-nav-item to-index">-->
<#--            <a href="/">前台首页</a>-->
<#--        </li>-->
    </ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <#list menuList as menu>
                <li>
                    <a href="javascript:;">
                        <i class="iconfont"
                           lay-tips="${menu.adminMenu.menuName}">&#${menu.adminMenu.menuIcon};</i>
                        <cite>${menu.adminMenu.menuName}</cite>
                        <i class="iconfont nav_right">&#xe697;</i></a>
                    <ul class="sub-menu">
                        <#list menu.childMenuList as childMenu>
                            <li>
                                <a onclick="xadmin.add_tab('${childMenu.menuName}','${childMenu.menuUrl}')">
                                    <i class="iconfont">&#xe6a7;</i>
                                    <cite>${childMenu.menuName}</cite></a>
                            </li>
                        </#list>
                    </ul>
                </li>
            </#list>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li class="home">
                <i class="layui-icon">&#xe68e;</i>我的桌面
            </li>
        </ul>
        <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
            <dl>
                <dd data-type="this">关闭当前</dd>
                <dd data-type="other">关闭其它</dd>
                <dd data-type="all">关闭全部</dd>
            </dl>
        </div>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='/admin/index/home.do' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
        <div id="tab_show"></div>
    </div>
</div>
<div class="page-content-bg"></div>
<style id="theme_style"></style>
<!-- 右侧主体结束 -->
<script>
    function logout() {
        $.ajax({
            url: '/admin/login/logout.do',
            success: function (res) {
                if (1 === res.status || "1" === res.status) {
                    parent.window.location.href = "/admin/index/index.do";
                } else {
                    layer.alert(res.message, {
                            icon: 5
                        }, function () {
                            //关闭当前frame
                            parent.location.reload();
                        }
                    );
                }
            }
        });
    }
</script>
<!-- 中部结束 -->
<script>//百度统计可去掉
    var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
</body>
</html>