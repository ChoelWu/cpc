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
    <![endif]--></head>

<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form" id="channel-form">
            <div class="layui-form-item">
                <label for="file" class="layui-form-label">
                    <span class="x-red">*</span>用户信息表</label>
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="file">上传文件</button>
                    <div class="layui-upload-list" style="padding-left: 110px;">
                        <p id="demoText"></p>
                    </div>
                </div>
                <input type="hidden" name="channelPic" id="channelPic" value="">
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label"></label>
                <button class="layui-btn" onclick="closeModal();">关闭</button>
            </div>
        </form>
    </div>
</div>
<script>
    layui.use(['form', 'layer', 'upload'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer,
            upload = layui.upload;

        // 普通图片上传
        var uploadInst = upload.render({
            elem: '#file',
            url: '/admin/index_user/import_user.do',
            field: "file",
            accept: "file",
            exts: "xls|xlsx",
            before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demoText').html(file.name);
                });
            },
            done: function (res) {
                $("#channelPic").val(res.data);
                layer.msg(res.message);
                setTimeout(function () {
                    parent.layer.close(parent.layer.index);
                }, 1500);
            },
            error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });
    });

    // 关闭弹框
    function closeModal() {
        parent.layer.close(parent.layer.index);
    }
</script>
<script>var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();</script>
</body>
</html>
