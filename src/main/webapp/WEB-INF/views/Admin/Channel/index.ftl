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
        <a>内容管理</a>
        <a><cite>栏目管理</cite></a>
    </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       href="/admin/channel/view.do" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i>
    </a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <@shiro.hasPermission name="indexChannel:add">
                    <div class="layui-card-header">
                        <button class="layui-btn"
                                onclick="xadmin.open('添加栏目','/admin/channel/add_page.do',600,500)">
                            <i class="layui-icon"></i>添加
                        </button>
                    </div>
                </@shiro.hasPermission>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>栏目名称</th>
                            <th>栏目类型</th>
                            <th>是否是导航</th>
                            <th width="50">排序</th>
                            <th>状态</th>
                            <th>操作</th>
                        </thead>
                        <tbody class="x-cate">

                        <#list channelList as channel>
                            <tr cate-id='${channel.indexChannel.channelNo}' fid='${channel.indexChannel.parentChannelNo}'>
                                <td>${channel_index + 1}</td>
                                <td>
                                    <i class="layui-icon x-show" status='true'>&#xe623;</i>
                                    ${channel.indexChannel.channelName}
                                </td>
                                <td>
                                    <#list channelTypeAdminDictList as channelTypeAdminDict>
                                        <#if channelTypeAdminDict.dictKey = channel.indexChannel.channelType>
                                            ${channelTypeAdminDict.dictValue}
                                        </#if>
                                    </#list>
                                </td>
                                <td>
                                    <#list isNavAdminDictList as isNavAdminDict>
                                        <#if isNavAdminDict.dictKey = channel.indexChannel.isNav>
                                            ${isNavAdminDict.dictValue}
                                        </#if>
                                    </#list>
                                </td>
                                <td>
                                    <@shiro.hasPermission name="indexChannel:sort">
                                        <input type="text" class="layui-input x-sort" name="order"
                                               value="${channel.indexChannel.channelIndex}"
                                               onchange="updateIndex('${channel.indexChannel.channelNo}', this.value)">
                                    </@shiro.hasPermission>
                                </td>
                                <td>
                                    <@shiro.hasPermission name="indexChannel:changeStatus">
                                        <input type="checkbox" name="switch" lay-text="开启|停用"
                                           data-no="${channel.indexChannel.channelNo}" lay-filter="switch"
                                           <#if '1' = channel.indexChannel.channelStatus>checked=""</#if> lay-skin="switch">
                                    </@shiro.hasPermission>
                                </td>
                                <td class="td-manage">
                                    <@shiro.hasPermission name="indexChannel:edit">
                                        <a title="编辑"
                                           onclick="xadmin.open('编辑','/admin/channel/edit_page.do?channelNo=${channel.indexChannel.channelNo}',600,500)"
                                           href="javascript:;">
                                            <i class="layui-icon">&#xe642;</i>
                                        </a>
                                    </@shiro.hasPermission>
                                    <@shiro.hasPermission name="indexChannel:delete">
                                        <a title="删除" onclick="deleteChannel('${channel.indexChannel.channelNo}')"
                                           href="javascript:;">
                                            <i class="layui-icon">&#xe640;</i>
                                        </a>
                                    </@shiro.hasPermission>
                                </td>
                            </tr>
                            <#list channel.childChannelList as childChannel>
                                <tr cate-id='${childChannel.channelNo}' fid='${childChannel.parentChannelNo}'>
                                    <td></td>
                                    <td>
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        ├${childChannel.channelName}
                                    </td>
                                    <td>
                                        <#list channelTypeAdminDictList as channelTypeAdminDict>
                                            <#if channelTypeAdminDict.dictValue = childChannel.channelType>
                                                ${channelTypeAdminDict.dictValue}
                                            </#if>
                                        </#list>
                                    </td>
                                    <td>
                                        <#list isNavAdminDictList as isNavAdminDict>
                                            <#if isNavAdminDict.dictKey = childChannel.isNav>
                                                ${isNavAdminDict.dictValue}
                                            </#if>
                                        </#list>
                                    </td>
                                    <td><input type="text" class="layui-input x-sort" name="order"
                                               value="${childChannel.channelIndex}"
                                               onchange="updateIndex('${childChannel.channelNo}', this.value)"></td>
                                    <td>
                                        <input type="checkbox" name="switch" lay-filter="switch" lay-text="开启|停用"
                                               <#if '1' = childChannel.channelStatus>checked=""</#if>
                                               lay-skin="switch" data-no="${childChannel.channelNo}">
                                    </td>
                                    <td class="td-manage">
                                        <a title="编辑"
                                           onclick="xadmin.open('编辑','/admin/channel/edit_page.do?channelNo=${childChannel.channelNo}',600,500)"
                                           href="javascript:;">
                                            <i class="layui-icon">&#xe642;</i>
                                        </a>
                                        <a title="删除" onclick="deleteChannel('${childChannel.channelNo}')"
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
                changeStatus("enable_channel", $(data.elem).data("no"));
            } else {
                changeStatus("disable_channel", $(data.elem).data("no"));
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
    function updateIndex(channelNo, channelIndex) {
        $.ajax({
            url: '/admin/channel/update_index.do',
            type: 'post',
            async: true,
            data: {channelNo: channelNo, channelIndex: channelIndex},
            dataType: 'json',
            success: function (res) {
                if ("1" !== res.status && 1 !== res.status) {
                    layui.use('layer', function () {
                        layer.msg(res.message, {time: 1500});
                    });
                    setTimeout(function () {
                        window.location.href = "/admin/channel/view.do";
                    }, 2000);
                } else {
                    window.location.href = "/admin/channel/view.do";
                }
            }
        });
    }

    // 更改栏目状态
    function changeStatus(option, channelNo) {
        var url = "/admin/channel/" + option + ".do";
        var tips = option === "enable_channel" ? "启用" : "禁用";
        $.ajax({
            url: url,
            type: 'post',
            data: {channelNo: channelNo},
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
                        window.location.href = "/admin/channel/view.do";
                    }
                );
            }
        });
    }

    // 删除栏目
    function deleteChannel(channelNo) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "/admin/channel/delete.do",
                type: 'post',
                data: {channelNo: channelNo},
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
                            window.location.href = "/admin/channel/view.do";
                        }
                    );
                }
            });
        });
    }
</script>
</html>
