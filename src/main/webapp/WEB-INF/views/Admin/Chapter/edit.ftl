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
            <input type="hidden" name="chapterId" value="${indexChapter.chapterId}">
            <div class="layui-form-item">
                <label for="chapterName" class="layui-form-label">
                    <span class="x-red">*</span>章节名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="chapterName" name="chapterName" required="" lay-verify="required|chapterName"
                           autocomplete="off" class="layui-input" placeholder="请输入章节名称" value="${indexChapter.chapterName}">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    不超过16字符，不能使用特殊字符
                </div>
            </div>
            <div class="layui-form-item">
                <label for="chapterIndex" class="layui-form-label">
                    <span class="x-red">*</span>章节排序</label>
                <div class="layui-input-inline">
                    <input type="text" id="chapterIndex" name="chapterIndex" required="" lay-verify="required|chapterIndex"
                           autocomplete="off" class="layui-input" placeholder="请输入4位数字" value="${indexChapter.chapterIndex}">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    序号越小排序靠前
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
            chapterName: function (value) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '章节名称不能有特殊字符';
                }
                if (value.length > 16) {
                    return '章节名称长度在 16 字符之内';
                }
            },
            chapterIndex: function (value, item) {
                if (!/^\d{4}$/.test(value)) {
                    return '只能填写四位数字';
                }
            }
        });

        //监听提交
        $("#sb-button").click(function(event) {
            event.preventDefault();
        });

        form.on('submit(add)', function (data) {
            $.ajax({
                url: '/admin/chapter/edit.do',
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
</body>
</html>
