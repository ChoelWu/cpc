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
        <a>管理员设置</a>
        <a><cite>用户管理</cite></a>
    </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       href="/admin/user/view.do" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5" lay-filter="search-box">
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="userName" placeholder="请输入用户名" autocomplete="off"
                                   class="layui-input" id="userName">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="userAccount" placeholder="请输入用户帐号" autocomplete="off"
                                   class="layui-input" id="userAccount">
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <select name="userStatus" lay-filter="status-select" id="userStatus">
                                <option value="">用户状态</option>
                                <option value="0">禁止使用</option>
                                <option value="1">正常使用</option>
                            </select>
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <button class="layui-btn" lay-submit="" lay-filter="sreach" id="sb-search">
                                <i class="layui-icon">&#xe615;</i></button>
                        </div>
                    </form>
                </div>
                <div class="layui-card-header">
                    <@shiro.hasPermission name="adminUser:batchDelete">
                        <button class="layui-btn layui-btn-danger" onclick="delAll()">
                            <i class="layui-icon"></i>批量删除
                        </button>
                    </@shiro.hasPermission>
                    <@shiro.hasPermission name="adminUser:add">
                        <button class="layui-btn" onclick="xadmin.open('添加用户','/admin/user/add_page.do',600,400)">
                            <i class="layui-icon"></i>添加
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
                            <th>用户名</th>
                            <th>用户角色</th>
                            <th>帐号</th>
                            <th>手机</th>
                            <th>邮箱</th>
                            <th>状态</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list adminUserList as adminUser>
                            <tr>
                                <td>
                                    <input type="checkbox" name="id" value="${adminUser.userNo}" lay-skin="primary">
                                </td>
                                <td>${adminUser_index + 1}</td>
                                <td>${adminUser.userName}</td>
                                <td>${adminUser.roleName}</td>
                                <td>${adminUser.userAccount}</td>
                                <td>${adminUser.userMobile}</td>
                                <td>${adminUser.userEmail}</td>
                                <td class="td-status">
                                    <@shiro.hasPermission name="adminUser:changeStatus">
                                        <#if adminUser.userStatus == "0">
                                            <a class="layui-btn layui-btn-warm layui-btn-mini"
                                               onclick="changeStatus('disable', '${adminUser.userNo}')">禁用</a>
                                        <#elseif adminUser.userStatus == "1">
                                            <a class="layui-btn layui-btn-normal layui-btn-mini"
                                               onclick="changeStatus('disable', '${adminUser.userNo}')">启用</a>
                                        </#if>
                                    </@shiro.hasPermission>
                                </td>
                                <td class="td-manage">
                                    <@shiro.hasPermission name="adminUser:edit">
                                        <a title="编辑"
                                           onclick="xadmin.open('编辑','/admin/user/edit_page.do?userNo=${adminUser.userNo}',600,400)"
                                           href="javascript:;">
                                            <i class="layui-icon">&#xe642;</i>
                                        </a>
                                    </@shiro.hasPermission>
                                    <@shiro.hasPermission name="adminUser:resetPassword">
                                        <a onclick="recoveryPassword('${adminUser.userNo}')"
                                           title="重置密码"
                                           href="javascript:;">
                                            <i class="layui-icon">&#xe669;</i>
                                        </a>
                                    </@shiro.hasPermission>
                                    <@shiro.hasPermission name="adminUser:delete">
                                        <a title="删除" onclick="deleteUser('${adminUser.userNo}')"
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
                                <a class="prev" skipToPage(${page.prePage})>&lt;&lt;</a>
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
            "userName": "${conditions.userName}"
            , "userAccount": "${conditions.userAccount}"
            , "userStatus": "${conditions.userStatus}"
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
                url: "/admin/user/delete_batch.do",
                type: 'post',
                data: {userNos: ids.toString()},
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
</script>
<script>
    // 更改状态
    function changeStatus(option, userNo) {
        var url = "/admin/user/" + option + ".do";
        var tips = option === "enable" ? "启用" : "禁用";
        layer.confirm('确认要' + tips + '用户吗？', function (index) {
            $.ajax({
                url: url,
                type: 'post',
                data: {userNo: userNo},
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
    function deleteUser(userNo) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "/admin/user/delete.do",
                type: 'post',
                data: {userNo: userNo},
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

    // 重置密码
    function recoveryPassword(userNo) {
        layer.confirm('确认要重置密码吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "/admin/user/recovery_password.do",
                type: 'post',
                data: {userNo: userNo},
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
    function queryPage(currentPage) {
        var data = {
            userName: $("#userName").val(),
            userAccount: $("#userAccount").val(),
            userStatus: $("#userStatus").val()
        };

        window.location.href = "/admin/user/view.do" + "?conditions=" + encodeURI(JSON.stringify(data)) + "&currentPage=" + currentPage;
    }

    // 页面跳转
    function skipToPage(page) {
        queryPage(page);
    }
</script>
</html>