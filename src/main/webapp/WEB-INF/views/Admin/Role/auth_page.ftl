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
        <div class="layui-form-item">
            <label class="layui-form-label">选择权限</label>
            <div class="layui-input-block" id="test12">
                <div id="LAY-auth-tree-index"></div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" type="submit" id="sb-button">提交</button>
            </div>
        </div>
    </div>

</div>
<script>
    layui.use(['form', 'layer', 'jquery', 'tree', 'util'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer,
            tree = layui.tree,
            //模拟数据
            data = ${tree};
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

        // switch 开关监听
        form.on('switch(switch)', function (data) {
            if (data.elem.checked || "true" === data.elem.checked) {
                $("input[name=roleStatus]").val("1");
            } else {
                $("input[name=roleStatus]").val("0");
            }
        });

        $("#sb-button").click(function() {
            var checkData = tree.getChecked('demoId1');
            console.log(checkData);
            $.ajax({
                url: '/admin/role/edit_auth.do',
                type: 'post',
                data: {
                    auth: JSON.stringify(checkData),
                    roleNo: "${roleNo}"
                },
                success: function (res) {
                    var icon = 5;
                    if(1 === res.status || "1" === res.status) {
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
