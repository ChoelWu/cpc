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
                <label for="courseName" class="layui-form-label">
                    <span class="x-red">*</span>课程标题</label>
                <div class="layui-input-inline">
                    <input type="text" id="courseName" name="courseName" required="" lay-verify="required|courseName"
                           autocomplete="off" class="layui-input" placeholder="请输入课程标题">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    不超过16字符，不能使用特殊字符
                </div>
            </div>
            <div class="layui-form-item">
                <label for="courseCateNo" class="layui-form-label">
                    <span class="x-red">*</span>所属分类</label>
                <div class="layui-input-inline">
                    <select id="courseCateNo" name="courseCateNo" class="valid" lay-verify="required">
                        <option value="">无</option>
                        <#list courseCateList as courseCate>
                            <option value="${courseCate.indexCourseCate.courseCateNo}">${courseCate.indexCourseCate.courseCateName}</option>
                            <#list courseCate.childCourseCateList as childCourseCate>
                                <option value="${childCourseCate.courseCateNo}">|--${childCourseCate.courseCateName}</option>
                            </#list>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="courseAuthor" class="layui-form-label">
                    <span class="x-red">*</span>课程讲师</label>
                <div class="layui-input-inline">
                    <input type="text" id="courseAuthor" name="courseAuthor" required="" lay-verify="required"
                           autocomplete="off" class="layui-input" placeholder="请输入课程讲师">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    不超过16字符，不能使用特殊字符
                </div>
            </div>
            <div class="layui-form-item">
                <label for="courseDifficultLevel" class="layui-form-label">
                    <span class="x-red">*</span>课程难度</label>
                <div class="layui-input-inline">
                    <select id="courseDifficultLevel" name="courseDifficultLevel" class="valid" lay-verify="required">
                        <#list courseDifficultLevelAdminDictList as courseDifficultLevelAdminDict>
                            <option value="${courseDifficultLevelAdminDict.dictKey}">${courseDifficultLevelAdminDict.dictValue}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="courseFitPeople" class="layui-form-label">
                    <span class="x-red">*</span>课程适宜人群</label>
                <div class="layui-input-inline">
                    <input type="text" id="courseFitPeople" name="courseFitPeople" required="" lay-verify="required"
                           autocomplete="off" class="layui-input" placeholder="请输入课程适宜人群">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    不超过16字符，不能使用特殊字符
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">可观看角色</label>
                <div class="layui-input-block">
                    <#list courseRoleAdminDictList as courseRoleAdminDict>
                        <input type="checkbox" value="${courseRoleAdminDict.dictKey}" title="${courseRoleAdminDict.dictValue}" lay-filter="role">
                    </#list>
                </div>
                <input type="hidden" id="roleHidden" name="role" value="">
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">课程时长</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="courseDurationHour" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid">小时 </div>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" name="courseDurationMinute" autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-mid">分钟</div>
            </div>
            <div class="layui-form-item">
                <label for="publishTime" class="layui-form-label">
                    <span class="x-red">*</span>发布时间</label>
                <div class="layui-input-inline">
                    <input type="text" id="publishTime" name="publishTime" id="publishTime" required=""
                           lay-verify="required"
                           autocomplete="off" class="layui-input" placeholder="请输入发布时间">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="isTop" class="layui-form-label">
                    <span class="x-red">*</span>是否置顶</label>
                <div class="layui-input-inline">
                    <select id="isTop" name="isTop" class="valid" lay-verify="required">
                        <#list courseIsTopAdminDictList as courseIsTopAdminDict>
                            <option value="${courseIsTopAdminDict.dictKey}">${courseIsTopAdminDict.dictValue}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="courseCover" class="layui-form-label">课程封面</label>
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="multipartFile">上传图片</button>
                    <div class="layui-upload-list" style="padding-left: 110px;">
                        <img class="layui-upload-img" id="coverPic">
                        <p id="demoText"></p>
                    </div>
                </div>
                <input type="hidden" name="courseCover" id="courseCover" value="">
            </div>
            <div class="layui-form-item layui-form-text">
                <label for="courseIntro" class="layui-form-label">课程简介</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" id="courseIntro" name="courseIntro"
                              class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label for="courseCaution" class="layui-form-label">课程须知</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" id="courseCaution" name="courseCaution"
                              class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label for="courseGoal" class="layui-form-label">课程目标</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" id="courseGoal" name="courseGoal"
                              class="layui-textarea"></textarea>
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
            elem: '#publishTime',
            trigger:'click'
        });

        //自定义验证规则
        form.verify({
            courseName: function (value) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '用户名不能有特殊字符';
                }
                if (value.length > 16) {
                    return '用户名长度在 16 字符之内';
                }
                var checkRel = checkCourseName("", value);
                if ("false" === checkRel || !checkRel) {
                    return '课程名称不能重复！';
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
                url: '/admin/course/add.do',
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

        // 普通图片上传
        var uploadInst = upload.render({
            elem: '#multipartFile',
            url: '/admin/course/upload_cover.do',
            field: "multipartFile",
            before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#coverPic').attr('src', result).css("height", "100px");
                });
            },
            done: function (res) {
                $("#courseCover").val(res.data);
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
    function checkCourseName(courseNo, courseName) {
        var rel = false;
        $.ajax({
            url: '/admin/course/check_course_name.do',
            type: 'post',
            async: false,
            data: {
                courseNo: courseNo,
                courseName: courseName
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
