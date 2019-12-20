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
<#--    <script type="text/javascript" src="/static/admin/lib/layui/layui.js" charset="utf-8"></script>-->
    <script type="text/javascript" src="/static/admin/lib/layui-v2.5.5/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/static/admin/js/xadmin.js"></script>
    <script type="text/javascript" src="/static/admin/js/jquery.min.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="layui-fluid">
    <div class="layui-row">

            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
                <legend>基本演示</legend>
            </fieldset>
            <div class="layui-btn-container">
                <button type="button" class="layui-btn layui-btn-sm" lay-demo="getChecked">获取选中节点数据</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-demo="setChecked">勾选指定节点</button>
                <button type="button" class="layui-btn layui-btn-sm" lay-demo="reload">重载实例</button>
            </div>

            <div id="test12" class="demo-tree-more"></div>

    </div>

</div>
<script>
    layui.use(['form', 'layer', 'jquery', 'tree', 'util'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer,
            tree = layui.tree,
            util = layui.util,

            //模拟数据
            data = [{
                title: '一级1',
                id: 1,
                field: 'name1',
                checked: true,
                spread: true,
                children: [{
                    title: '二级1-1 可允许跳转'
                    , id: 3
                    , field: 'name11'
                    , href: 'https://www.layui.com/'
                    , children: [{
                        title: '三级1-1-3'
                        , id: 23
                        , field: ''
                        , children: [{
                            title: '四级1-1-3-1'
                            , id: 24
                            , field: ''
                            , children: [{
                                title: '五级1-1-3-1-1'
                                , id: 30
                                , field: ''
                            }, {
                                title: '五级1-1-3-1-2'
                                , id: 31
                                , field: ''
                            }]
                        }]
                    }, {
                        title: '三级1-1-1'
                        , id: 7
                        , field: ''
                        , children: [{
                            title: '四级1-1-1-1 可允许跳转'
                            , id: 15
                            , field: ''
                            , href: 'https://www.layui.com/doc/'
                        }]
                    }, {
                        title: '三级1-1-2'
                        , id: 8
                        , field: ''
                        , children: [{
                            title: '四级1-1-2-1'
                            , id: 32
                            , field: ''
                        }]
                    }]
                }, {
                    title: '二级1-2'
                    , id: 4
                    , spread: true
                    , children: [{
                        title: '三级1-2-1'
                        , id: 9
                        , field: ''
                        , disabled: true
                    }, {
                        title: '三级1-2-2'
                        , id: 10
                        , field: ''
                    }]
                }, {
                    title: '二级1-3'
                    , id: 20
                    , field: ''
                    , children: [{
                        title: '三级1-3-1'
                        , id: 21
                        , field: ''
                    }, {
                        title: '三级1-3-2'
                        , id: 22
                        , field: ''
                    }]
                }]
            }, {
                title: '一级2'
                , id: 2
                , field: ''
                , spread: true
                , children: [{
                    title: '二级2-1'
                    , id: 5
                    , field: ''
                    , spread: true
                    , children: [{
                        title: '三级2-1-1'
                        , id: 11
                        , field: ''
                    }, {
                        title: '三级2-1-2'
                        , id: 12
                        , field: ''
                    }]
                }, {
                    title: '二级2-2'
                    , id: 6
                    , field: ''
                    , children: [{
                        title: '三级2-2-1'
                        , id: 13
                        , field: ''
                    }, {
                        title: '三级2-2-2'
                        , id: 14
                        , field: ''
                        , disabled: true
                    }]
                }]
            }, {
                title: '一级3'
                , id: 16
                , field: ''
                , children: [{
                    title: '二级3-1'
                    , id: 17
                    , field: ''
                    , fixed: true
                    , children: [{
                        title: '三级3-1-1'
                        , id: 18
                        , field: ''
                    }, {
                        title: '三级3-1-2'
                        , id: 19
                        , field: ''
                    }]
                }, {
                    title: '二级3-2'
                    , id: 27
                    , field: ''
                    , children: [{
                        title: '三级3-2-1'
                        , id: 28
                        , field: ''
                    }, {
                        title: '三级3-2-2'
                        , id: 29
                        , field: ''
                    }]
                }]
            }];

        // 基本演示
        tree.render({
            elem: '#test12',
            data: data,
            showCheckbox: true,  //是否显示复选框
            id: 'demoId1',
            isJump: true, //是否允许点击节点时弹出新窗口跳转
            click: function (obj) {
                var data = obj.data;  //获取当前点击的节点数据
                layer.msg('状态：' + obj.state + '<br>节点数据：' + JSON.stringify(data));
            }
        });




        //按钮事件
        util.event('lay-demo', {
            getChecked: function (othis) {
                var checkedData = tree.getChecked('demoId1'); //获取选中节点的数据

                layer.alert(JSON.stringify(checkedData), {shade: 0});
                console.log(checkedData);
            }
            , setChecked: function () {
                tree.setChecked('demoId1', [12, 16]); //勾选指定节点
            }
            , reload: function () {
                //重载实例
                tree.reload('demoId1', {});

            }
        });

        //自定义验证规则
        form.verify({
            roleName: function (value) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '用户名不能有特殊字符';
                }
                if (value.length > 16 || value.length < 6) {
                    return '用户名长度在6 - 16字符之间';
                }
            }
        });

        //监听提交
        $("#sb-button").click(function (event) {
            event.preventDefault();
        });

        form.on('submit(add)', function (data) {
            $.ajax({
                url: '/admin/role/edit.do',
                type: 'post',
                data: {data: JSON.stringify(data.field)},
                success: function (res) {
                    var icon = 5;
                    if (1 === res.status || "1" === res.status) {
                        icon = 6;
                    }

                    layer.alert(res.message, {
                            icon: icon
                        },
                        function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                            parent.location.reload();
                        }
                    );
                }
            });
        });

        // switch 开关监听
        form.on('switch(switch)', function (data) {
            if (data.elem.checked || "true" === data.elem.checked) {
                $("input[name=roleStatus]").val("1");
            } else {
                $("input[name=roleStatus]").val("0");
            }
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
