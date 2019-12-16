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
    <script type="text/javascript" src="/static/admin/lib/layui/layui.js" charset="utf-8"></script>
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
        <form class="layui-form">
            <div class="layui-form-item">
                <label for="authName" class="layui-form-label">
                    <span class="x-red">*</span>权限名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="authName" name="authName" required="" lay-verify="required|textValidation"
                           autocomplete="off" class="layui-input" placeholder="请输入权限名称">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    不超过20个字符
                </div>
            </div>
            <div class="layui-form-item">
                <label for="authPermission" class="layui-form-label">
                    <span class="x-red">*</span>权限规则</label>
                <div class="layui-input-inline">
                    <input type="text" id="authPermission" name="authPermission" required="" lay-verify="required"
                           autocomplete="off" class="layui-input" placeholder="请输入权限规则">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    不超过20个字符
                </div>
            </div>
            <div class="layui-form-item">
                <label for="menuNo" class="layui-form-label">
                    <span class="x-red">*</span>所属菜单</label>
                <div class="layui-input-inline">
                    <select id="menuNo" name="menuNo" class="valid" lay-verify="required">
                        <option value="">无</option>
                        <#list menuList as menu>
                            <option value="${menu.adminMenu.menuNo}">${menu.adminMenu.menuName}</option>
                            <#list menu.childMenuList as childMenu>
                                <option value="${childMenu.menuNo}">|--${childMenu.menuName}</option>
                            </#list>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label"></label>
                <button id="sb-button" class="layui-btn" lay-filter="add" lay-submit="">增加</button>
            </div>
        </form>
    </div>
</div>
<script>
    layui.use(['form', 'layer', 'jquery'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;

        //自定义验证规则
        form.verify({
            textValidation: function (value) {
                if (value.length > 20) {
                    return '权限名称长度小于20字符';
                }
            }
        });

        //监听提交
        $("#sb-button").click(function(event) {
            event.preventDefault();
        });

        form.on('submit(add)', function (data) {
            $.ajax({
                url: '/admin/auth/add.do',
                type: 'post',
                data: {data: JSON.stringify(data.field)},
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
