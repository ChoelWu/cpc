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
          <span class="layui-breadcrumb">
            <a href="">首页</a>
            <a href="">演示</a>
            <a>
              <cite>导航元素</cite></a>
          </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       onclick="window.location.href='/admin/chapter/view.do?courseNo=${courseNo}'" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <button class="layui-btn"
                            onclick="xadmin.open('添加章节','/admin/chapter/add_page.do?courseNo=${courseNo}',500,310)"><i
                                class="layui-icon"></i>添加章节
                    </button>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>章节名称</th>
                            <th>所属课程</th>
                            <th>章节排序</th>
                            <th>操作</th>
                        </thead>
                        <tbody>
                        <#list chapterLessonList as chapterLesson>
                            <tr>
                                <td>第 ${chapterLesson_index + 1} 章</td>
                                <td>${chapterLesson.chapter.chapterName}</td>
                                <td>${chapterLesson.chapter.courseName}</td>
                                <td>${chapterLesson.chapter.chapterIndex}</td>
                                <td class="td-manage">
                                    <a title="新增课时" onclick="xadmin.open('新增课时','/admin/lesson/add_page.do?chapterNo=${chapterLesson.chapter.chapterNo}',600,500)"
                                       href="javascript:;">
                                        <i class="iconfont">&#xe6b9;</i>
                                    </a>
                                    <a title="编辑"
                                       onclick="xadmin.open('编辑章节','/admin/chapter/edit_page.do?chapterNo=${chapterLesson.chapter.chapterNo}',500,310)"
                                       href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                    </a>
                                    <a title="删除" onclick="deleteChapter('${chapterLesson.chapter.chapterNo}')" href="javascript:;">
                                        <i class="layui-icon">&#xe640;</i>
                                    </a>
                                </td>
                            </tr>
                            <#if chapterLesson.indexLessonList?? && (chapterLesson.indexLessonList?size > 0)>
                                <tr>
                                    <td>
                                        |---- 课时
                                    </td>
                                    <td colspan="5">
                                        <table class="layui-table layui-form">
                                            <thead>
                                            <tr>
                                                <th></th>
                                                <th>课时名称</th>
                                                <th>课时排序</th>
                                                <th>视频文件</th>
                                                <th>动画文件</th>
                                                <th>课时类型</th>
                                                <th>操作</th>
                                            </thead>
                                            <tbody>
                                            <#list chapterLesson.indexLessonList as indexLesson>
                                                <tr>
                                                    <td>第 ${indexLesson_index + 1} 课</td>
                                                    <td>${indexLesson.lessonName}</td>
                                                    <td>${indexLesson.lessonIndex}</td>
                                                    <td><#if indexLesson.lessonVideo?? && (indexLesson.lessonVideo?length >0)><span class="layui-btn layui-btn-xs">已上传</span><#else><span class="layui-btn layui-btn-danger layui-btn-xs">未上传</span></#if></td>
                                                    <td><#if indexLesson.lessonSwf?? && (indexLesson.lessonSwf?length>0)><span class="layui-btn layui-btn-xs">已上传</span><#else><span class="layui-btn layui-btn-danger layui-btn-xs">未上传</span></#if></td>
                                                    <td class="td-status">
                                                        <#list lessonTypeAdminDictList as lessonTypeAdminDict>
                                                            <#if lessonTypeAdminDict.dictKey = indexLesson.lessonType>
                                                                ${lessonTypeAdminDict.dictValue}
                                                            </#if>
                                                        </#list>
                                                    </td>
                                                    <td class="td-manage">
                                                        <a title="编辑"
                                                           onclick="xadmin.open('编辑课时','/admin/lesson/edit_page.do?lessonNo=${indexLesson.lessonNo}',600,500)"
                                                           href="javascript:;">
                                                            <i class="layui-icon">&#xe642;</i>
                                                        </a>
                                                        <a title="删除" onclick="deleteLesson('${indexLesson.lessonNo}')"
                                                           href="javascript:;">
                                                            <i class="layui-icon">&#xe640;</i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </#list>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </#if>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    layui.use(['laydate', 'form'], function () {
        var laydate = layui.laydate;
        var form = layui.form;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });

        // 监听全选
        form.on('checkbox(checkall)', function (data) {
            if (data.elem.checked) {
                $('tbody input').prop('checked', true);
            } else {
                $('tbody input').prop('checked', false);
            }
            form.render('checkbox');
        });
    });

    // 批量删除
    function delAll(argument) {
        var ids = [];

        // 获取选中的id
        $('tbody input').each(function (index, el) {
            if ($(this).prop('checked')) {
                ids.push($(this).val())
            }
        });

        layer.confirm('确认要删除吗？', function (index) {
            $.ajax({
                url: "/admin/chapter/delete_batch.do",
                type: 'post',
                data: {chapterNos: ids.toString()},
                success: function (res) {
                    var icon = 5;
                    if (1 === res.status || "1" === res.status) {
                        icon = 6;
                    }

                    layer.alert(res.message, {
                            icon: icon
                        },
                        function (index) {
                            // 关闭当前frame
                            layer.close(index);
                            location.reload();
                        }
                    );
                }
            });
        });
    }

    // 删除章节
    function deleteChapter(chapterNo) {
        layer.confirm('将同时删除该课程下的所有课时，确认要删除吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "/admin/chapter/delete.do",
                type: 'post',
                data: {chapterNo: chapterNo},
                success: function (res) {
                    var icon = 5;
                    if (1 === res.status || "1" === res.status) {
                        icon = 6;
                    }

                    layer.alert(res.message, {
                            icon: icon
                        },
                        function (index) {
                            // 关闭当前frame
                            layer.close(index);
                            location.reload();
                        }
                    );
                }
            });
        });
    }

    // 删除课时
    function deleteLesson(lessonNo) {
        layer.confirm('确认要删除该课时吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "/admin/lesson/delete.do",
                type: 'post',
                data: {lessonNo: lessonNo},
                success: function (res) {
                    var icon = 5;
                    if (1 === res.status || "1" === res.status) {
                        icon = 6;
                    }

                    layer.alert(res.message, {
                            icon: icon
                        },
                        function (index) {
                            // 关闭当前frame
                            layer.close(index);
                            location.reload();
                        }
                    );
                }
            });
        });
    }

    // 请求页面
    function queryPage() {
        window.location.href = "/admin/chapter/view.do" + "?courseNo=${courseNo}";
    }
</script>
<script>var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();</script>
</html>