<!DOCTYPE html>
<html class="x-admin-sm">

<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.2</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="/static/admin/css/font.css">
    <link rel="stylesheet" href="/static/admin/css/xadmin.css">
    <link rel="stylesheet" href="/static/admin/lib/layui-v2.5.5/css/layui.css">
    <#--    <script src="/static/admin/lib/layui/layui.js" charset="utf-8"></script>-->
    <script type="text/javascript" src="/static/admin/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/static/admin/js/xadmin.js"></script>
    <script type="text/javascript" src="/static/admin/js/jquery.min.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form" style="margin-top: 20px;">
            <div class="layui-form-item">
                <div id="test1" class="demo-transfer"></div>
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label"></label>
                <button id="sb-button" class="layui-btn" lay-filter="add" lay-submit="">增加</button>
            </div>
        </form>
    </div>
</div>
<script>
    layui.use(['transfer', 'layer', 'util'], function(){
        var $ = layui.$
            ,transfer = layui.transfer
            ,layer = layui.layer
            ,util = layui.util;

        //模拟数据
        var data1 = ${indexCourseTag};

        //定义标题及数据源
        transfer.render({
            elem: '#test1'
            ,title: ['候选文人', '获奖文人']  //自定义标题
            ,data: data1,
            value: ${selectedCourseTag!''},
            // width: 150,
            height: 300 //定义高度
        });
    });
</script>
<script>
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
