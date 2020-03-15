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
    <script src="/static/admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/static/admin/js/xadmin.js"></script>
    <script type="text/javascript" src="/static/admin/js/jquery.min.js"></script>
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="x-nav">
    <#if '1' = adminUserPOJO.userStatus>
        账户激活
    <#else>
        <span class="layui-breadcrumb">
            <a href="/admin/index/home.do">首页</a>
            <a>个人中心</a>
            <a>
                <cite>个人信息</cite>
            </a>
        </span>
        <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
           href="/admin/user/personal_page.do" title="刷新">
            <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
    </#if>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <#if '1' = adminUserPOJO.userStatus>
            <div class="layui-col-md12">
                <div class="layui-card">
                    <div class="layui-card-body ">
                        <blockquote class="layui-elem-quote">
                            <span class="x-red">请先完善信息激活账户！</span>
                        </blockquote>
                    </div>
                </div>
            </div>
        </#if>
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form">
                        <input type="hidden" name="userId" value="${adminUserPOJO.userId}">
                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="x-red">*</span>用户名</label>
                            <div class="layui-input-inline">
                                <input type="text" name="userName" value="${adminUserPOJO.userName}"
                                       lay-verify="userName|required" autocomplete="off" placeholder="请输入昵称"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="x-red">*</span>登录账号</label>
                            <div class="layui-input-inline">
                                <input type="text" name="userAccount" value="${adminUserPOJO.userAccount}"
                                       readonly="" class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">不可修改，一般用于后台登入名</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="x-red">*</span>用户角色</label>
                            <div class="layui-input-inline">
                                <input type="text" value="${adminUserPOJO.roleName!''}" readonly=""
                                       class="layui-input">
                            </div>
                            <div class="layui-form-mid layui-word-aux">不可修改</div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="x-red">*</span>联系电话</label>
                            <div class="layui-input-inline">
                                <input type="text" name="userMobile" value="${adminUserPOJO.userMobile}"
                                       lay-verify="phone|required" autocomplete="off" placeholder="请输入昵称"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="x-red">*</span>电子邮箱</label>
                            <div class="layui-input-inline">
                                <input type="text" name="userEmail" value="${adminUserPOJO.userEmail}"
                                       lay-verify="email|required" autocomplete="off" placeholder="请输入昵称"
                                       class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><span class="x-red">*</span>头像</label>
                            <div class="layui-upload">
                                <button type="button" class="layui-btn" id="userHeadSculpture">上传图片</button>
                                <div class="layui-upload-list" style="padding-left: 110px;">
                                    <img class="layui-upload-img" id="userHeadSculpturePic"
                                         <#if adminUserPOJO.userHeadSculpture?? && adminUserPOJO.userHeadSculpture != "">src="${adminUserPOJO.userHeadSculpture!''}"
                                         <#else>src="/static/admin/images/unknow.jpg"</#if> height="100">
                                    <p id="userHeadSculptureText"></p>
                                </div>
                            </div>
                            <input type="hidden" name="userHeadSculpture" lay-verify="userHeadSculpture"
                                   id="userHeadSculptureInput" value="${adminUserPOJO.userHeadSculpture!''}">
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">备注</label>
                            <div class="layui-input-block">
                                            <textarea name="bak" placeholder="请输入内容"
                                                      class="layui-textarea">${adminUserPOJO.bak!''}</textarea>
                            </div>
                        </div>
                        <input type="hidden" name="userStatus" value="${adminUserPOJO.userStatus}">
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <#if '1' = adminUserPOJO.userStatus>
                                    <button class="layui-btn" lay-submit="" lay-filter="edit" id="sb-button">激活账户
                                    </button>
                                <#else>
                                    <button class="layui-btn" lay-submit="" lay-filter="edit" id="sb-button">确认修改
                                    </button>
                                </#if>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    layui.use(['form', 'upload'], function () {
        var upload = layui.upload,
            form = layui.form;

        // 表单验证
        form.verify({
            userName: function (value) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '用户名不能有特殊字符';
                }
                if (value.length > 16) {
                    return '用户名长度在 16 字符之内';
                }

                var checkRel = checkUser("${adminUserPOJO.userNo}", "user_name", value);
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
                var checkRel = checkUser("", "user_account", value);
                if ("false" === checkRel || !checkRel) {
                    return '用户账户不能重复！';
                }
            },
            userEmail: function (value) {
                var checkRel = checkUser("", "user_email", value);
                if ("false" === checkRel || !checkRel) {
                    return '此邮箱已经被使用！';
                }
            },
            userHeadSculpture: function (value) {
                if ("" === value) {
                    return '请上传头像！';
                }
            }
        });

        //监听提交
        $("#sb-button").click(function (event) {
            event.preventDefault();
        });

        form.on('submit(edit)', function (data) {
            $.ajax({
                url: '/admin/user/personal.do',
                type: 'post',
                data: {data: JSON.stringify(data.field)},
                success: function (res) {
                    var icon = 5;
                    if (1 === res.status || "1" === res.status) {
                        icon = 6;
                    }

                    layer.alert(res.message, {
                            icon: icon
                        }, function () {
                            <#if '1' = adminUserPOJO.userStatus>
                            window.location.href = "/admin/index/index.do";
                            <#else>
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                            parent.location.reload();
                            </#if>
                        }
                    );
                }
            });
        });

        //普通图片上传
        upload.render({
            elem: '#userHeadSculpture',
            url: '/admin/user/upload_pic.do',
            field: "userHeadSculpture",
            before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#userHeadSculpturePic').attr('src', result).css("height", "100px");
                });
            },
            done: function (res) {
                $("#userHeadSculptureInput").val(res.data);
                return layer.msg(res.message);
            },
            error: function () {
                //演示失败状态
                var demoText = $('#userHeadSculptureText');
                demoText.html('<span style="color: #FF5722;">上传失败</span>');
            }
        });
    });

    // 检查用户名是否重复
    function checkUser(userNo, field, value) {
        var rel = false;
        $.ajax({
            url: '/admin/index_user/check_user.do',
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
</html>