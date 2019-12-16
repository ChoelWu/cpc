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
            <input type="hidden" name="courseNo" value="${courseNo}">
            <input type="hidden" name="chapterNo" value="${chapterNo}">
            <div class="layui-form-item">
                <label for="lessonName" class="layui-form-label">
                    <span class="x-red">*</span>课时名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="lessonName" name="lessonName" required="" lay-verify="required|lessonName"
                           autocomplete="off" class="layui-input" placeholder="请输入课时名称">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    不超过16字符，不能使用特殊字符
                </div>
            </div>
            <div class="layui-form-item">
                <label for="lessonIndex" class="layui-form-label">
                    <span class="x-red">*</span>课时排序</label>
                <div class="layui-input-inline">
                    <input type="text" id="lessonIndex" name="lessonIndex" required="" lay-verify="required|lessonIndex"
                           autocomplete="off" class="layui-input" placeholder="请输入课时序号">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    序号越小排序越靠前
                </div>
            </div>
            <div class="layui-form-item">
                <label for="lessonType" class="layui-form-label">
                    <span class="x-red">*</span>课时类型</label>
                <div class="layui-input-inline">
                    <select id="lessonType" name="lessonType" class="valid">
                        <#list lessonTypeAdminDictList as lessonTypeAdminDict>
                            <option value="${lessonTypeAdminDict.dictKey}">${lessonTypeAdminDict.dictValue}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="multipartVideo" class="layui-form-label">视频文件</label>
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="multipartVideo">上传视频</button>
                    <div class="layui-upload-list" style="padding-left: 110px;">
                        <p id="multipartVideoText"></p>
                    </div>
                </div>
                <input type="hidden" name="lessonVideo" id="lessonVideo" value="">
            </div>
            <div class="layui-form-item">
                <label for="multipartSwf" class="layui-form-label">动画文件</label>
                <div class="layui-upload">
                    <button type="button" class="layui-btn layui-btn-danger" id="multipartSwf">上传动画</button>
                    <div class="layui-upload-list" style="padding-left: 110px;">
                        <p id="multipartSwfText"></p>
                    </div>
                </div>
                <input type="hidden" name="lessonSwf" id="lessonSwf" value="">
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label"></label>
                <button id="sb-button" class="layui-btn" lay-filter="add" lay-submit="">增加</button>
            </div>
        </form>
    </div>
</div>
<script>
    layui.use(['form', 'layer', 'jquery', 'upload'], function () {
        $ = layui.jquery;
        var form = layui.form,
            upload = layui.upload,
            layer = layui.layer;

        //自定义验证规则
        form.verify({
            lessonName: function (value) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '课时名称不能有特殊字符';
                }
                if (value.length > 16) {
                    return '课时名称长度在 16 字符之内';
                }
            }
        });

        //监听提交
        $("#sb-button").click(function (event) {
            event.preventDefault();
        });

        form.on('submit(add)', function (data) {
            $.ajax({
                url: '/admin/lesson/add.do',
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

        var videoIndex;

        // 视频上传
        var uploadInst = upload.render({
            elem: '#multipartVideo',
            url: '/admin/lesson/upload_video.do',
            field: "multipartVideo",
            accept: "video",
            ext: 'mp4',
            data: {
                courseNo: "${courseNo}",
                chapterNo: "${chapterNo}",
            },
            before: function (obj) {
                //loading层
                videoIndex = parent.layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });

                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#multipartVideoText').html(file.name);
                });
            },
            done: function (res) {
                parent.layer.close(videoIndex);
                $("#lessonVideo").val(res.data);
                return layer.msg(res.message);
            },
            error: function () {
                parent.layer.close(videoIndex);
                //演示失败状态，并实现重传
                var multipartVideoText = $('#multipartVideoText');
                multipartVideoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                multipartVideoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });

        var swfIndex;
        // 动画上传
        var uploadInstSwf = upload.render({
            elem: '#multipartSwf',
            url: '/admin/lesson/upload_swf.do',
            field: "multipartSwf",
            accept: "file",
            ext: 'swf',
            before: function (obj) {
                //loading层
                videoIndex = parent.layer.load(1, {
                    shade: [0.1,'#fff'] //0.1透明度的白色背景
                });

                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#multipartVideoText').html(file.name);
                });
            },
            done: function (res) {
                parent.layer.close(swfIndex);
                $("#lessonVideo").val(res.data);
                return layer.msg(res.message);
            },
            error: function () {
                parent.layer.close(swfIndex);
                //演示失败状态，并实现重传
                var multipartSwfText = $('#multipartSwfText');
                multipartSwfText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                multipartSwfText.find('.demo-reload').on('click', function () {
                    uploadInstSwf.upload();
                });
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
