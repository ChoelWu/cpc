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
        <form class="layui-form" id="cate-form">
            <div class="layui-form-item" style="margin-top: 30px;">
                <label for="courseTagName" class="layui-form-label">
                    <span class="x-red">*</span>课程标签名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="courseTagName" name="courseTagName" required=""
                           lay-verify="required|cateTagName"
                           autocomplete="off" class="layui-input" placeholder="不超过16个字符">
                </div>
            </div>
            <div class="layui-form-item" style="margin-top: 50px;">
                <label for="L_repass" class="layui-form-label"></label>
                <button class="layui-btn" id="sb-button" lay-filter="add" lay-submit="">增加</button>
            </div>
        </form>
    </div>
</div>
<script>
    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer;

        //自定义验证规则
        form.verify({
            cateTagName: function (value, item) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '课程标签名称不能有特殊字符';
                }
                if (value.length > 16) {
                    return '课程标签名称不能超过16字符';
                }
                var checkRel = checkCourseTagName("", value);
                if ("false" === checkRel || !checkRel) {
                    return '课程标签名称不能重复！';
                }
            }
        });

        //监听提交
        $("#sb-button").click(function (event) {
            event.preventDefault();
        });

        // 监听提交
        form.on('submit(add)', function (data) {
            $.ajax({
                url: '/admin/course_tag/add.do',
                type: 'post',
                async: true,
                data: {data: JSON.stringify(data.field)},
                dataType: 'json',
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
    });

    // 检查分类名称是否重复
    function checkCourseTagName(courseTagNo, courseTagName) {
        var rel = false;
        $.ajax({
            url: '/admin/course_tag/check_course_tag_name.do',
            type: 'post',
            async: false,
            data: {
                courseTagNo: courseTagNo,
                courseTagName: courseTagName
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
