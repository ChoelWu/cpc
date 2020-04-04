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
                <a href="">首页</a>
                <a href="">演示</a>
                <a>
                    <cite>导航元素</cite></a>
            </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       href="/admin/menu/view.do" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
    </a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <button class="layui-btn"
                            onclick="xadmin.open('添加用户','/admin/menu/add_page.do',600,500)">
                        <i class="layui-icon"></i>添加
                    </button>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>菜单名称</th>
                            <th>菜单图标</th>
                            <th>菜单地址</th>
                            <th width="50">排序</th>
                            <th>状态</th>
                            <th>操作</th>
                        </thead>
                        <tbody class="x-cate">

                        <#list menuList as menu>
                            <tr cate-id='${menu.adminMenu.menuNo}' fid='${menu.adminMenu.parentMenuNo}'>
                                <td>${menu_index + 1}</td>
                                <td>
                                    <i class="layui-icon x-show" status='true'>&#xe623;</i>
                                    ${menu.adminMenu.menuName}
                                </td>
                                <td>
                                    <i class="iconfont">&#${menu.adminMenu.menuIcon};</i>
                                </td>
                                <td>
                                    ${menu.adminMenu.menuUrl}
                                </td>
                                <td>
                                    <input type="text" class="layui-input x-sort" name="order"
                                           value="${menu.adminMenu.menuIndex}"
                                           onchange="updateIndex('${menu.adminMenu.menuNo}', this.value)">
                                </td>
                                <td>
                                    <input type="checkbox" name="switch" lay-text="开启|停用"
                                           data-no="${menu.adminMenu.menuNo}" lay-filter="switch"
                                           <#if '1' = menu.adminMenu.menuStatus>checked=""</#if> lay-skin="switch">
                                </td>
                                <td class="td-manage">
                                    <a title="编辑"
                                       onclick="xadmin.open('编辑','/admin/menu/edit_page.do?menuNo=${menu.adminMenu.menuNo}',600,500)"
                                       href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                    </a>
                                    <a title="删除" onclick="deleteMenu('${menu.adminMenu.menuNo}')"
                                       href="javascript:;">
                                        <i class="layui-icon">&#xe640;</i>
                                    </a>
                                </td>
                            </tr>
                            <#list menu.childMenuList as childMenu>
                                <tr cate-id='${childMenu.menuNo}' fid='${childMenu.parentMenuNo}'>
                                    <td></td>
                                    <td>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        ├${childMenu.menuName}
                                    </td>
                                    <td>
                                        <i class="iconfont">&#${childMenu.menuIcon};</i>
                                    </td>
                                    <td>
                                        ${childMenu.menuUrl}
                                    </td>
                                    <td><input type="text" class="layui-input x-sort" name="order"
                                               value="${childMenu.menuIndex}"
                                               onchange="updateIndex('${childMenu.menuNo}', this.value)"></td>
                                    <td>
                                        <input type="checkbox" name="switch" lay-filter="switch" lay-text="开启|停用"
                                               <#if '1' = childMenu.menuStatus>checked=""</#if>
                                               lay-skin="switch" data-no="${childMenu.menuNo}">
                                    </td>
                                    <td class="td-manage">
                                        <a title="编辑"
                                           onclick="xadmin.open('编辑','/admin/menu/edit_page.do?menuNo=${childMenu.menuNo}',600,500)"
                                           href="javascript:;">
                                            <i class="layui-icon">&#xe642;</i>
                                        </a>
                                        <a title="删除" onclick="deleteMenu('${childMenu.menuNo}')"
                                           href="javascript:;">
                                            <i class="layui-icon">&#xe640;</i>
                                        </a>
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
                changeStatus("enable_menu", $(data.elem).data("no"));
            } else {
                changeStatus("disable_menu", $(data.elem).data("no"));
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
    function updateIndex(menuNo, menuIndex) {
        $.ajax({
            url: '/admin/menu/update_index.do',
            type: 'post',
            async: true,
            data: {menuNo: menuNo, menuIndex: menuIndex},
            dataType: 'json',
            success: function (res) {
                if ("1" !== res.status && 1 !== res.status) {
                    layui.use('layer', function () {
                        layer.msg(res.message, {time: 1500});
                    });
                    setTimeout(function () {
                        window.location.href = "/admin/menu/view.do";
                    }, 2000);
                } else {
                    window.location.href = "/admin/menu/view.do";
                }
            }
        });
    }

    // 更改菜单状态
    function changeStatus(option, menuNo) {
        var url = "/admin/menu/" + option + ".do";
        var tips = option === "enable_menu" ? "启用" : "禁用";
        $.ajax({
            url: url,
            type: 'post',
            data: {menuNo: menuNo},
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
                        window.location.href = "/admin/menu/view.do";
                    }
                );
            }
        });
    }

    // 删除用户
    function deleteMenu(menuNo) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "/admin/menu/delete.do",
                type: 'post',
                data: {menuNo: menuNo},
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
                            window.location.href = "/admin/menu/view.do";
                        }
                    );
                }
            });
        });
    }
</script>
</html>
