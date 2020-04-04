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
    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]--></head>

<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form" id="menu-form">
            <input type="hidden" name="menuId" value="${adminMenu.menuId}">
            <div class="layui-form-item">
                <label for="menuName" class="layui-form-label">
                    <span class="x-red">*</span>菜单名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="menuName" name="menuName" required="" lay-verify="required|menuName"
                           autocomplete="off" class="layui-input" placeholder="不超过16个字符" value="${adminMenu.menuName}">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="menuLevel" class="layui-form-label">
                    <span class="x-red">*</span>级别类型</label>
                <div class="layui-input-inline">
                    <select id="menuLevel" name="menuLevel" class="valid" <#if hasChild>disabled="disabled"</#if>>
                        <#list menuLevelDictList as menuLevelDict>
                            <option value="${menuLevelDict.dictKey}" <#if adminMenu.menuLevel = menuLevelDict.dictKey>selected</#if>>${menuLevelDict.dictValue}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="menuUrl" class="layui-form-label">
                    <span class="x-red">*</span>菜单链接</label>
                <div class="layui-input-inline">
                    <input type="text" id="menuUrl" name="menuUrl" required="" lay-verify="required"
                           autocomplete="off" class="layui-input" placeholder="请填写菜单地址" value="${adminMenu.menuUrl}">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    例: /admin/index/index.do
                </div>
            </div>
            <div class="layui-form-item">
                <label for="menuIndex" class="layui-form-label">
                    <span class="x-red">*</span>菜单编号</label>
                <div class="layui-input-inline">
                    <input type="text" id="menuIndex" name="menuIndex" required="" lay-verify="required|menuIndex"
                           autocomplete="off" class="layui-input" placeholder="请填写 4 位数字"  value="${adminMenu.menuIndex}">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    例: 1234
                </div>
            </div>
            <div class="layui-form-item">
                <label for="menuIcon" class="layui-form-label">
                    <span class="x-red">*</span>菜单图标</label>
                <div class="layui-input-inline">
                    <input type="text" id="menuIcon" name="menuIcon" required="" lay-verify="required"
                           autocomplete="off" class="layui-input" placeholder="请填写菜单图标" value="${adminMenu.menuIcon}">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    例: left-nav-li
                </div>
            </div>
            <div class="layui-form-item">
                <label for="parentMenuNo" class="layui-form-label">
                    <span class="x-red">*</span>父级菜单</label>
                <div class="layui-input-inline">
                    <select id="parentMenuNo" name="parentMenuNo" class="valid" <#if hasChild>disabled="disabled"</#if>>
                        <option value="0">无</option>
                        <#list parentAdminMenuList as parentAdminMenu>
                            <option value="${parentAdminMenu.menuNo}" <#if adminMenu.parentMenuNo = parentAdminMenu.menuNo>selected</#if>>${parentAdminMenu.menuName}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="menuStatus" class="layui-form-label">
                    <span class="x-red">*</span>菜单状态</label>
                <div class="layui-input-inline">
                    <input type="checkbox" <#if '1' = adminMenu.menuStatus>checked=""</#if> lay-skin="switch" lay-filter="switch" lay-text="启用|禁用">
                    <input type="hidden" name="menuStatus"  value="${adminMenu.menuStatus}">
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
            menuName: function (value, item) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '菜单名称不能有特殊字符';
                }
                if (value.length > 16) {
                    return '菜单名称不能超过16字符';
                }
            },
            menuIndex: function (value, item) {
                if (!/^\d{4}$/.test(value)) {
                    return '只能填写四位数字';
                }
            },
            menuUrl: function (value, item) {
                if (value.length > 100) {
                    return '菜单地址不能超过100字符';
                }
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '菜单地址不能有特殊字符';
                }
            }
        });

        //监听提交
        $("#sb-button").click(function(event) {
            event.preventDefault();
        });

        // 监听提交
        form.on('submit(add)', function (data) {
            $.ajax({
                url: '/admin/menu/edit.do',
                type: 'post',
                async: true,
                data: {data: JSON.stringify(data.field)},
                dataType: 'json',
                success: function (res) {
                    var icon = 5;
                    if(1 === res.status || "1" === res.status) {
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

        // switch 开关监听
        form.on('switch(switch)', function (data) {
            if (data.elem.checked || "true" === data.elem.checked) {
                $("input[name=menuStatus]").val("1");
            } else {
                $("input[name=menuStatus]").val("0");
            }
        });
    });
</script>
</body>

</html>