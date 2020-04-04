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
            <input type="hidden" name="userId" value="${adminUser.userId}">
            <div class="layui-form-item">
                <label for="userName" class="layui-form-label">
                    <span class="x-red">*</span>用户名</label>
                <div class="layui-input-inline">
                    <input type="text" id="userName" name="userName" required="" lay-verify="required|userName"
                           autocomplete="off" class="layui-input" placeholder="请输入用户名" value="${adminUser.userName}">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    16字符之内，不能使用特殊字符
                </div>
            </div>
            <div class="layui-form-item">
                <label for="userAccount" class="layui-form-label">
                    <span class="x-red">*</span>用户帐号</label>
                <div class="layui-input-inline">
                    <input type="text" id="userAccount" name="userAccount" required="" lay-verify="required|userAccount"
                           autocomplete="off" value="${adminUser.userAccount}"
                           class="layui-input" placeholder="请输入用户账号">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    6到16字符，只能使用英文、数字和下划线
                </div>
            </div>
            <div class="layui-form-item">
                <label for="userEmail" class="layui-form-label"><span class="x-red">*</span>用户邮箱</label>
                <div class="layui-input-inline">
                    <input type="text" id="userEmail" name="userEmail" required="" lay-verify="required|email|userEmail"
                           autocomplete="off" value="${adminUser.userEmail}"
                           class="layui-input" placeholder="请输入电子邮箱">
                </div>
                <div class="layui-form-mid layui-word-aux">不超过50个字符</div>
            </div>
            <div class="layui-form-item">
                <label for="userMobile" class="layui-form-label"><span class="x-red">*</span>用户电话</label>
                <div class="layui-input-inline">
                    <input type="text" id="userMobile" name="userMobile" required="" lay-verify="required|phone"
                           autocomplete="off" class="layui-input" placeholder="请输入 11 的位用户电话" value="${adminUser.userMobile}">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="roleNo" class="layui-form-label">
                    <span class="x-red">*</span>用户角色</label>
                <div class="layui-input-inline">
                    <select id="roleNo" name="roleNo" class="valid">
                        <#list adminRoleList as adminRole>
                            <option value="${adminRole.roleNo}" <#if adminRole.roleNo=adminUser.roleNo>selected</#if>>${adminRole.roleName}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label"></label>
                <button id="sb-button" class="layui-btn" lay-filter="add" lay-submit="">提交</button>
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
            userName: function (value) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '用户名不能有特殊字符';
                }
                if (value.length > 16) {
                    return '用户名长度在 16 字符之内';
                }

                var checkRel = checkUser("${adminUser.userNo}", "user_name", value);
                if ("false" === checkRel || !checkRel) {
                    return '用户名不能重复！';
                }
            },
            userAccount: function (value) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '用户账户不能有特殊字符';
                }
                if (value.length > 16 || value.length < 6) {
                    return '用户账户长度在6 - 16字符之间';
                }
                var checkRel = checkUser("${adminUser.userNo}", "user_account", value);
                if ("false" === checkRel || !checkRel) {
                    return '用户账户不能重复！';
                }
            },
            userEmail: function (value) {
                var checkRel = checkUser("${adminUser.userNo}", "user_email", value);
                if ("false" === checkRel || !checkRel) {
                    return '此邮箱已经被使用！';
                }
            }
        });

        //监听提交
        $("#sb-button").click(function(event) {
            event.preventDefault();
        });

        form.on('submit(add)', function (data) {
            $.ajax({
                url: '/admin/user/edit.do',
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

    // 检查用户名是否重复
    function checkUser(userNo, field, value) {
        var rel = false;
        $.ajax({
            url: '/admin/user/check_user.do',
            type: 'post',
            async: false,
            data: {
                userNo: userNo,
                field: field,
                value: value
            },
            dataType: 'json',
            success: function (res) {
                rel = res.data;
            }
        });

        return rel;
    }
</script>
</body>
</html>
