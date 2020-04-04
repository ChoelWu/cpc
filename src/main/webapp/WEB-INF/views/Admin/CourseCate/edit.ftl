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
            <input type="hidden" name="courseCateId" value="${indexCourseCate.courseCateId}">
            <div class="layui-form-item">
                <label for="courseCateName" class="layui-form-label">
                    <span class="x-red">*</span>课程分类名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="courseCateName" name="courseCateName" required=""
                           lay-verify="required|cateName" value="${indexCourseCate.courseCateName}"
                           autocomplete="off" class="layui-input" placeholder="不超过16个字符">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="courseCateLevel" class="layui-form-label">
                    <span class="x-red">*</span>课程分类级别</label>
                <div class="layui-input-inline">
                    <select id="courseCateLevel" name="courseCateLevel" class="valid"
                            lay-verify="cateLevelAndParentCate">
                        <#list courseCateLevelDictList as courseCateLevelDict>
                            <option value="${courseCateLevelDict.dictKey}"
                                    <#if courseCateLevelDict.dictKey=indexCourseCate.courseCateLevel>selected</#if>>${courseCateLevelDict.dictValue}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="courseCateIndex" class="layui-form-label"><span class="x-red">*</span>课程分类编号</label>
                <div class="layui-input-inline">
                    <input type="text" id="courseCateIndex" name="courseCateIndex" required=""
                           lay-verify="required|cateIndex" autocomplete="off" class="layui-input"
                           placeholder="请填写 4 位数字" value="${indexCourseCate.courseCateIndex}">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    编号越小排序越靠前
                </div>
            </div>
            <div class="layui-form-item">
                <label for="parentCourseCateNo" class="layui-form-label">
                    <span class="x-red">*</span>父级课程分类</label>
                <div class="layui-input-inline">
                    <select id="parentCourseCateNo" name="parentCourseCateNo" class="valid"
                            lay-verify="cateLevelAndParentCate">
                        <option value="0">无</option>
                        <#list parentIndexCourseCateList as parentIndexCourseCate>
                            <#if parentIndexCourseCate.courseCateNo != indexCourseCate.courseCateNo>
                                <option value="${parentIndexCourseCate.courseCateNo}"
                                        <#if parentIndexCourseCate.courseCateNo=indexCourseCate.parentCourseCateNo>selected</#if>>${parentIndexCourseCate.courseCateName}</option>
                            </#if>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label"></label>
                <button class="layui-btn" id="sb-button" lay-filter="add" lay-submit="">提交</button>
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
            cateName: function (value, item) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '课程分类名称不能有特殊字符';
                }
                if (value.length > 16) {
                    return '课程分类名称不能超过16字符';
                }
                var checkRel = checkCourseCateName("${indexCourseCate.courseCateNo}", value);
                if ("false" === checkRel || !checkRel) {
                    return '课程分类名称不能重复！';
                }
            },
            cateIndex: function (value, item) {
                if (!/^\d{4}$/.test(value)) {
                    return '请填写四位数字';
                }
            },
            cateLevelAndParentCate: function (value, item) {
                var courseCateLevel = $("#courseCateLevel").val();
                var parentCourseCateNo = $("#parentCourseCateNo").val();

                if (("2" === courseCateLevel && "0" === parentCourseCateNo) || ("1" === courseCateLevel && "0" !== parentCourseCateNo)) {
                    return '栏目级别和父级栏目请填写规范！';
                }
            },
        });

        //监听提交
        $("#sb-button").click(function (event) {
            event.preventDefault();
        });

        // 监听提交
        form.on('submit(add)', function (data) {
            $.ajax({
                url: '/admin/course_cate/edit.do',
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
    function checkCourseCateName(courseCateNo, courseCateName) {
        var rel = false;
        $.ajax({
            url: '/admin/course_cate/check_course_cate_name.do',
            type: 'post',
            async: false,
            data: {
                courseCateNo: courseCateNo,
                courseCateName: courseCateName
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
