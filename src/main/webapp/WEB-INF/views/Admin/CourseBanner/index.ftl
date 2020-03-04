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
       onclick="window.location.href='/admin/course/view.do'" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5" lay-filter="search-box">
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="courseBannerName" id="courseBannerName" placeholder="请输入推荐位标题"
                                   autocomplete="off" class="layui-input">
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <select name="courseBannerStatus" lay-filter="status-select" id="courseBannerStatus">
                                <option value="">推荐位状态</option>
                                <#list courseBannerStatusAdminDictList as courseBannerStatusAdminDict>
                                    <option value="${courseBannerStatusAdminDict.dictKey}">${courseBannerStatusAdminDict.dictValue}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" lay-submit="" lay-filter="sreach" id="sb-search"><i
                                        class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-header">
                    <button class="layui-btn" onclick="xadmin.open('添加课程推荐位','/admin/course_banner/add_page.do',600,500)"><i
                                class="layui-icon"></i>添加
                    </button>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>推荐位标题</th>
                            <th>推荐位链接</th>
                            <th>推荐位排序</th>
                            <th>推荐位状态</th>
                            <th>操作</th>
                        </thead>
                        <tbody>
                        <#list courseBannerList as courseBanner>
                            <tr>
                                <td>${courseBanner_index + 1}</td>
                                <td>${courseBanner.courseBannerName}</td>
                                <td>${courseBanner.courseBannerUrl}</td>
                                <td>${courseBanner.courseBannerIndex}</td>
                                <td>${courseBanner.courseBannerStatus}</td>
                                <td class="td-manage">
                                    <a title="编辑"
                                       onclick="xadmin.open('编辑课程','/admin/course_banner/edit_page.do?courseBannerNo=${courseBanner.courseBannerNo}',600,450)"
                                       href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                    </a>
                                    <a title="删除" onclick="deleteCourse('${courseBanner.courseBannerNo}')" href="javascript:;">
                                        <i class="layui-icon">&#xe640;</i>
                                    </a>
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
    layui.use(['form'], function () {
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

        <#if conditions??>
        form.val("search-box", {
            "courseBannerName": "${conditions.courseBannerName}",
            "courseBannerStatus": "${conditions.courseBannerStatus}"
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

    // 更改状态
    function changeStatus(option, courseNo) {
        var url = "/admin/course/" + option + ".do";
        var tips = option === "enable" ? "审核并发布" : "取消发布";
        layer.confirm('确认要' + tips + '课程吗？', function (index) {
            $.ajax({
                url: url,
                type: 'post',
                data: {courseNo: courseNo},
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

    // 删除用户
    function deleteCourse(courseBannerNo) {
        layer.confirm('将删除该课程推荐位，确认要删除吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "/admin/course_banner/delete.do",
                type: 'post',
                data: {courseBannerNo: courseBannerNo},
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
                            window.location.href = "/admin/course_banner/view.do";
                        }
                    );
                }
            });
        });
    }

    // 请求页面
    function queryPage(currentPage) {
        var data = {
            courseBannerName: $("#courseBannerName").val(),
            courseBannerStatus: $("#courseBannerStatus").val()
        };

        window.location.href = "/admin/course_banner/view.do" + "?conditions=" + encodeURI(JSON.stringify(data)) + "&currentPage=" + currentPage;
    }

    // 页面跳转
    function skipToPage(page) {
        queryPage(page);
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
</html>
