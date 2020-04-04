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
        <a>课程管理</a>
        <a><cite>课程标签</cite></a>
    </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       href="/admin/course_tag/view.do" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5" lay-filter="search-box">
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="courseTagName" placeholder="请输入标签名" autocomplete="off"
                                   class="layui-input" id="courseTagName">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" lay-submit="" lay-filter="sreach" id="sb-search">
                                <i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-header">
                    <@shiro.hasPermission name="indexCourseTag:batchDelete">
                        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量删除
                        </button>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="indexCourseTag:add">
                        <button class="layui-btn" onclick="xadmin.open('添加用户','/admin/course_tag/add_page.do',400,300)"><i
                                    class="layui-icon"></i>添加
                        </button>
                    </@shiro.hasPermission>
                </div>
                <div class="layui-card-body layui-table-body layui-table-main">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>
                                <input type="checkbox" lay-filter="checkall" name="" lay-skin="primary">
                            </th>
                            <th>ID</th>
                            <th>课程标签</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list indexCourseTagList as indexCourseTag>
                            <tr>
                                <td>
                                    <input type="checkbox" name="id" value="${indexCourseTag.courseTagNo}" lay-skin="primary">
                                </td>
                                <td>${indexCourseTag_index + 1}</td>
                                <td><span class="layui-badge layui-bg-blue">${indexCourseTag.courseTagName}</span></td>
                                <td class="td-manage">
                                    <@shiro.hasPermission name="indexCourseTag:edit">
                                        <a title="编辑"
                                           onclick="xadmin.open('编辑','/admin/course_tag/edit_page.do?courseTagNo=${indexCourseTag.courseTagNo}',400,300)"
                                           href="javascript:;">
                                            <i class="layui-icon">&#xe642;</i>
                                        </a>
                                    </@shiro.hasPermission>
                                    <@shiro.hasPermission name="indexCourseTag:delete">
                                        <a title="删除" onclick="deleteCourseTag('${indexCourseTag.courseTagNo}')"
                                           href="javascript:;">
                                            <i class="layui-icon">&#xe640;</i>
                                        </a>
                                    </@shiro.hasPermission>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div class="layui-card-body ">
                    <div class="page" id="page">
                        <div>
                            <#if -1 = page.prePage>
                                <a class="layui-disabled">&lt;&lt;</a>
                            <#else>
                                <a class="prev" onclick="skipToPage(${page.prePage})">&lt;&lt;</a>
                                <a class="num" onclick="skipToPage(${page.prePage})">${page.prePage}</a>
                            </#if>
                            <span class="current">${page.currentPage}</span>
                            <#if -1 != page.nextPage>
                                <a class="num" onclick="skipToPage(${page.nextPage})">${page.nextPage}</a>
                            </#if>
                            <#if -1 = page.nextPage>
                                <a class="layui-disabled">&gt;&gt;</a>
                            <#else>
                                <a class="next" onclick="skipToPage(${page.nextPage})">&gt;&gt;</a>
                            </#if>
                            &nbsp;&nbsp;&nbsp;&nbsp;跳转至
                            <span style="border: none !important; width: 40px;">
                                <input type="text" id="page-skip" class="layui-input">
                            </span>
                            页
                            <span style="border: none !important; padding-left: 20px; !important;">共 ${page.totalPage} 页</span>
                        </div>
                    </div>
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

        // 监听全选
        form.on('checkbox(checkall)', function (data) {
            if (data.elem.checked) {
                $('tbody input').prop('checked', true);
            } else {
                $('tbody input').prop('checked', false);
            }
            form.render('checkbox');
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });

        <#if conditions??>
        form.val("search-box", {
            "courseTagName": "${conditions.courseTagName}"
        });
        </#if>


        //监听提交
        $("#sb-search").click(function (event) {
            event.preventDefault();
        });

        form.on('submit(sreach)', function (data) {
            queryPage(1);
        });
    });

    $("#page-skip").change(function () {
        var page = $(this).val();
        skipToPage(page);
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
                url: "/admin/course_tag/delete_batch.do",
                type: 'post',
                data: {courseTagNos: ids.toString()},
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
                            window.location.href = "/admin/course_tag/view.do";
                        }
                    );
                }
            });
        });
    }

    // 删除课程标签
    function deleteCourseTag(courseTagNo) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "/admin/course_tag/delete.do",
                type: 'post',
                data: {courseTagNo: courseTagNo},
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
                            window.location.href = "/admin/course_tag/view.do";
                        }
                    );
                }
            });
        });
    }

    // 请求页面
    function queryPage(currentPage) {
        var data = {
            courseTagName: $("#courseTagName").val()
        };

        window.location.href = "/admin/course_tag/view.do" + "?conditions=" + encodeURI(JSON.stringify(data)) + "&currentPage=" + currentPage;
    }

    // 页面跳转
    function skipToPage(page) {
        queryPage(page);
    }
</script>
</html>