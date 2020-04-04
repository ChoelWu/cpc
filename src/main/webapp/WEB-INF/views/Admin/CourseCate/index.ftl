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
    <script type="text/javascript" src="/static/admin/js/jquery.min.js"></script>
    <script src="/static/admin/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/static/admin/js/xadmin.js"></script>
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a>课程管理</a>
        <a><cite>课程分类</cite></a>
    </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       href="/admin/course_cate/view.do" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
    </a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <@shiro.hasPermission name="indexCourseCate:add">
                    <div class="layui-card-header">
                        <button class="layui-btn"
                                onclick="xadmin.open('添加课程分类','/admin/course_cate/add_page.do',500,400)">
                            <i class="layui-icon"></i>添加
                        </button>
                    </div>
                </@shiro.hasPermission>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>分类名称</th>
                            <th>分类级别</th>
                            <th>排序</th>
<#--                            <th>推荐课程</th>-->
                            <th>操作</th>
                        </thead>
                        <tbody class="x-cate">
                        <#list courseCateList as courseCate>
                            <tr cate-id='${courseCate.indexCourseCate.courseCateNo}'
                                fid='${courseCate.indexCourseCate.parentCourseCateNo}'>
                                <td>${courseCate_index + 1}</td>
                                <td>
                                    <i class="layui-icon x-show" status='true'>&#xe623;</i>
                                    ${courseCate.indexCourseCate.courseCateName}
                                </td>
                                <td>
                                    <#list courseCateLevelDictList as courseCateLevelDict>
                                        <#if courseCate.indexCourseCate.courseCateLevel=courseCateLevelDict.dictKey>
                                            ${courseCateLevelDict.dictValue}
                                        </#if>
                                    </#list>
                                </td>
                                <td>
                                    <@shiro.hasPermission name="indexCourseCate:sort">
                                        <input type="text" class="layui-input x-sort" name="order"
                                               value="${courseCate.indexCourseCate.courseCateIndex}"
                                               onchange="updateIndex('${courseCate.indexCourseCate.courseCateNo}', this.value)">
                                    </@shiro.hasPermission>
                                </td>
<#--                                <td>&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;&nbsp;</td>-->
                                <td class="td-manage">
                                    <@shiro.hasPermission name="indexCourseCate:edit">
                                        <a title="编辑"
                                           onclick="xadmin.open('编辑课程分类','/admin/course_cate/edit_page.do?courseCateNo=${courseCate.indexCourseCate.courseCateNo}',500,400)"
                                           href="javascript:;">
                                            <i class="layui-icon">&#xe642;</i>
                                        </a>
                                    </@shiro.hasPermission>
                                    <@shiro.hasPermission name="indexCourseCate:delete">
                                        <a title="删除" onclick="deleteCate('${courseCate.indexCourseCate.courseCateNo}')"
                                           href="javascript:;">
                                            <i class="layui-icon">&#xe640;</i>
                                        </a>
                                    </@shiro.hasPermission>
                                </td>
                            </tr>
                            <#list courseCate.childCourseCateList as childCourseCate>
                                <tr cate-id='${childCourseCate.courseCateNo}'
                                    fid='${childCourseCate.parentCourseCateNo}'>
                                    <td></td>
                                    <td>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        ├${childCourseCate.courseCateName}
                                    </td>
                                    <td>
                                        <#list courseCateLevelDictList as courseCateLevelDict>
                                            <#if childCourseCate.courseCateLevel=courseCateLevelDict.dictKey>
                                                ${courseCateLevelDict.dictValue}
                                            </#if>
                                        </#list>
                                    </td>
                                    <td>
                                        <@shiro.hasPermission name="indexCourseCate:sort">
                                            <input type="text" class="layui-input x-sort" name="order"
                                               value="${childCourseCate.courseCateIndex}"
                                               onchange="updateIndex('${childCourseCate.courseCateNo}', this.value)">
                                        </@shiro.hasPermission>
                                    </td>
                                    <td class="td-manage">
                                        <@shiro.hasPermission name="indexCourseCate:edit">
                                            <a title="编辑"
                                               onclick="xadmin.open('编辑','/admin/course_cate/edit_page.do?courseCateNo=${childCourseCate.courseCateNo}',500,400)"
                                               href="javascript:;">
                                                <i class="layui-icon">&#xe642;</i>
                                            </a>
                                        </@shiro.hasPermission>
                                        <@shiro.hasPermission name="indexCourseCate:delete">
                                            <a title="删除" onclick="deleteCate('${childCourseCate.courseCateNo}')"
                                               href="javascript:;">
                                                <i class="layui-icon">&#xe640;</i>
                                            </a>
                                        </@shiro.hasPermission>
                                    </td>
                                </tr>
                            </#list>
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
    layui.use(['form'], function () {
        form = layui.form;

        // 监听switch
        form.on('switch(switch)', function (data) {
            if (data.elem.checked || "true" === data.elem.checked) {
                changeStatus("enable_cate", $(data.elem).data("no"));
            } else {
                changeStatus("disable_cate", $(data.elem).data("no"));
            }
        });
    });

    // 分类展开收起的分类的逻辑
    $(function () {
        $("tbody.x-cate tr[fid!='0']").hide();
        // 栏目多级显示效果
        $('.x-show').click(function () {
            if ($(this).attr('status') == 'true') {
                $(this).html('&#xe625;');
                $(this).attr('status', 'false');
                cateId = $(this).parents('tr').attr('cate-id');
                $("tbody tr[fid=" + cateId + "]").show();
            } else {
                cateIds = [];
                $(this).html('&#xe623;');
                $(this).attr('status', 'true');
                cateId = $(this).parents('tr').attr('cate-id');
                getCateId(cateId);
                for (var i in cateIds) {
                    $("tbody tr[cate-id=" + cateIds[i] + "]").hide().find('.x-show').html('&#xe623;').attr('status', 'true');
                }
            }
        })
    });

    var cateIds = [];

    function getCateId(cateId) {
        $("tbody tr[fid=" + cateId + "]").each(function (index, el) {
            id = $(el).attr('cate-id');
            cateIds.push(id);
            getCateId(id);
        });
    }

    // 更新索引
    function updateIndex(cateNo, cateIndex) {
        $.ajax({
            url: '/admin/course_cate/update_index.do',
            type: 'post',
            async: true,
            data: {courseCateNo: cateNo, courseCateIndex: cateIndex},
            dataType: 'json',
            success: function (res) {
                if ("1" !== res.status && 1 !== res.status) {
                    layui.use('layer', function () {
                        layer.msg(res.message, {time: 1500});
                    });
                    setTimeout(function () {
                        window.location.href = "/admin/course_cate/view.do";
                    }, 2000);
                } else {
                    window.location.href = "/admin/course_cate/view.do";
                }
            }
        });
    }

    // 删除栏目
    function deleteCate(courseCateNo) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "/admin/course_cate/delete.do",
                type: 'post',
                data: {courseCateNo: courseCateNo},
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
                            window.location.href = "/admin/course_cate/view.do";
                        }
                    );
                }
            });
        });
    }
</script>
</html>
