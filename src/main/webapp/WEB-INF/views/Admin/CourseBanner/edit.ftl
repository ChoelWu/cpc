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
<style>
    .layui-layedit-tool .layui-colorpicker-xs {
        border: 0;
    }

    .layui-layedit-tool .layui-colorpicker-trigger-span i {
        display: none !important;
    }

    .layui-layedit-iframe {
        background-color: #FFFFFF;
    }
</style>
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form">
            <div class="layui-form-item">
                <label for="courseBannerName" class="layui-form-label">
                    <span class="x-red">*</span>推荐位名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="courseBannerName" name="courseBannerName" required="" lay-verify="required|courseBannerName"
                           autocomplete="off" class="layui-input" placeholder="请输入课程推荐位名称" value="${indexCourseBanner.courseBannerName}">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    不超过16字符，不能使用特殊字符
                </div>
            </div>
            <div class="layui-form-item">
                <label for="courseBannerUrl" class="layui-form-label">
                    <span class="x-red">*</span>推荐位地址</label>
                <div class="layui-input-inline">
                    <input type="text" id="courseBannerUrl" name="courseBannerUrl" required="" lay-verify="required|url"
                           autocomplete="off" class="layui-input" placeholder="请输入课程推荐位名称" value="${indexCourseBanner.courseBannerUrl}">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="courseBannerIndex" class="layui-form-label">
                    <span class="x-red">*</span>推荐位编号</label>
                <div class="layui-input-inline">
                    <input type="text" id="courseBannerIndex" name="courseBannerIndex" required="" lay-verify="required|courseBannerIndex"
                           autocomplete="off" class="layui-input" placeholder="请填写 4 位数字" value="${indexCourseBanner.courseBannerIndex}">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    例: 1234
                </div>
            </div>
            <div class="layui-form-item">
                <label for="courseBannerStatus" class="layui-form-label">
                    <span class="x-red">*</span>推荐位状态</label>
                <div class="layui-input-inline">
                    <input type="checkbox" checked="" lay-skin="switch" lay-filter="switch" lay-text="启用|禁用">
                    <input type="hidden" name="courseBannerStatus" value="1">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="courseBannerPic" class="layui-form-label">推荐位封面</label>
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="multipartFile">上传图片</button>
                    <div class="layui-upload-list" style="padding-left: 110px;">
                        <img class="layui-upload-img" id="coverPic" <#if indexCourseBanner.courseBannerPic?? || "" = indexCourseBanner.courseBannerPic>src="${indexCourseBanner.courseBannerPic}" style="height: 100px;"</#if>>
                        <img class="layui-upload-img" id="coverPic">
                        <p id="demoText"></p>
                    </div>
                </div>
                <input type="hidden" name="courseBannerPic" id="courseBannerPic" value="${indexCourseBanner.courseBannerPic}">
            </div>

            <div class="layui-form-item layui-form-text">
                <label for="courseIntro" class="layui-form-label">推荐位简介</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" id="courseBannerIntro" name="courseBannerIntro"
                              class="layui-textarea">${indexCourseBanner.courseBannerIntro}</textarea>
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
    layui.use(['form', 'layer', 'jquery', 'laydate', 'upload'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer,
            laydate = layui.laydate,
            upload = layui.upload;

        laydate.render({
            elem: '#publishTime'
        });

        //自定义验证规则
        form.verify({
            courseBannerName: function (value) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '用户名不能有特殊字符';
                }
                if (value.length > 16) {
                    return '用户名长度在 16 字符之内';
                }
                var checkRel = checkcourseBannerName("${indexCourseBanner.courseBannerNo}", value);
                if ("false" === checkRel || !checkRel) {
                    return '课程名称不能重复！';
                }
            },
            courseBannerIndex: function (value, item) {
                if (!/^\d{4}$/.test(value)) {
                    return '只能填写四位数字';
                }
            }
        });

        //监听提交
        $("#sb-button").click(function (event) {
            event.preventDefault();
        });

        form.on('submit(add)', function (data) {
            var rawData = data.field;
            $.ajax({
                url: '/admin/course_banner/add.do',
                type: 'post',
                data: {data: JSON.stringify(rawData)},
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

        // checkbox 监听
        form.on('checkbox(role)', function (data) {
            var roleHiddenValue = $("#roleHidden").val();
            var roleHiddenValueArray =roleHiddenValue.split(",");
            var indexRel = roleHiddenValueArray.indexOf(data.value);
            if("" === roleHiddenValue) {
                roleHiddenValueArray = [];
            }
            if(data.elem.checked) {
                if(-1 === indexRel) {
                    roleHiddenValueArray.push(data.value);
                }
            } else {
                if(-1 !== indexRel) {
                    roleHiddenValueArray.splice(indexRel, 1);
                }
            }

            $("#roleHidden").val(roleHiddenValueArray.join(","))
        });

        // switch 开关监听
        form.on('switch(switch)', function (data) {
            if (data.elem.checked || "true" === data.elem.checked) {
                $("input[name=courseBannerStatus]").val("1");
            } else {
                $("input[name=courseBannerStatus]").val("0");
            }
        });

        // 普通图片上传
        var uploadInst = upload.render({
            elem: '#multipartFile',
            url: '/admin/course_banner/upload_cover.do',
            field: "multipartFile",
            before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#coverPic').attr('src', result).css("height", "100px");
                });
            },
            done: function (res) {
                $("#courseBannerPic").val(res.data);
                return layer.msg(res.message);
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

    // 检查分类名称是否重复
    function checkcourseBannerName(courseBannerNo, courseBannerName) {
        var rel = false;
        $.ajax({
            url: '/admin/course_banner/check_course_banner_name.do',
            type: 'post',
            async: false,
            data: {
                courseBannerNo: courseBannerNo,
                courseBannerName: courseBannerName
            },
            dataType: 'json',
            success: function (res) {
                rel = res.data;
            }
        });

        return rel;
    }
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
