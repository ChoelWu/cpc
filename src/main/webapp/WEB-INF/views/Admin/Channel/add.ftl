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
        <form class="layui-form" id="channel-form">
            <div class="layui-form-item">
                <label for="channelName" class="layui-form-label">
                    <span class="x-red">*</span>栏目名称</label>
                <div class="layui-input-inline">
                    <input type="text" id="channelName" name="channelName" required="" lay-verify="required|channelName"
                           autocomplete="off" class="layui-input" placeholder="不超过16个字符">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="channelLevel" class="layui-form-label">
                    <span class="x-red">*</span>栏目级别</label>
                <div class="layui-input-inline">
                    <select id="channelLevel" name="channelLevel" class="valid">
                        <#list channelLevelDictList as channelLevelDict>
                            <option value="${channelLevelDict.dictKey}">${channelLevelDict.dictValue}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="channelType" class="layui-form-label">
                    <span class="x-red">*</span>栏目类型</label>
                <div class="layui-input-inline">
                    <select id="channelType" name="channelType" class="valid" lay-filter="channelTypeSelect">
                        <#list channelTypeDictList as channelTypeDict>
                            <option value="${channelTypeDict.dictKey}">${channelTypeDict.dictValue}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item" id="channelUrl" style="display: none;"></div>
            <div class="layui-form-item">
                <label for="parentChannelNo" class="layui-form-label">
                    <span class="x-red">*</span>父级栏目</label>
                <div class="layui-input-inline">
                    <select id="parentChannelNo" name="parentChannelNo" class="valid">
                        <option value="0">无</option>
                        <#list parentIndexChannelList as parentIndexChannel>
                            <option value="${parentIndexChannel.channelNo}">${parentIndexChannel.channelName}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="channelIndex" class="layui-form-label">
                    <span class="x-red">*</span>栏目编号</label>
                <div class="layui-input-inline">
                    <input type="text" id="channelIndex" name="channelIndex" required=""
                           lay-verify="required|channelIndex"
                           autocomplete="off" class="layui-input" placeholder="请填写 4 位数字">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    例: 1234
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label for="channelIntro" class="layui-form-label">栏目简介</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" id="channelIntro" name="channelIntro"
                              class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="isNav" class="layui-form-label">
                    <span class="x-red">*</span>是否是导航</label>
                <div class="layui-input-inline">
                    <input type="checkbox" checked="" lay-skin="switch" lay-filter="isNavSwitch" lay-text="是|否">
                    <input type="hidden" name="isNav" value="1">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="channelStatus" class="layui-form-label">
                    <span class="x-red">*</span>栏目状态</label>
                <div class="layui-input-inline">
                    <input type="checkbox" checked="" lay-skin="switch" lay-filter="switch" lay-text="启用|禁用">
                    <input type="hidden" name="channelStatus" value="1">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="channelStatus" class="layui-form-label">
                    <span class="x-red">*</span>栏目图</label>
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="multipartFile">上传图片</button>
                    <div class="layui-upload-list" style="padding-left: 110px;">
                        <img class="layui-upload-img" id="coverPic">
                        <p id="demoText"></p>
                    </div>
                </div>
                <input type="hidden" name="channelPic" id="channelPic" value="">
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label"></label>
                <button class="layui-btn" id="sb-button" lay-filter="add" lay-submit="">增加</button>
            </div>
        </form>
    </div>
</div>
<script>
    layui.use(['form', 'layer', 'upload'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer,
            upload = layui.upload;


        //自定义验证规则
        form.verify({
            channelName: function (value, item) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '栏目名称不能有特殊字符';
                }
                if (value.length > 16) {
                    return '栏目名称不能超过16字符';
                }
            },
            channelIndex: function (value, item) {
                if (!/^\d{4}$/.test(value)) {
                    return '只能填写四位数字';
                }
            },
            channelUrl: function (value, item) {
                if (value.length > 100) {
                    return '栏目地址不能超过100字符';
                }
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '栏目地址不能有特殊字符';
                }
            }
        });

        //监听提交
        $("#sb-button").click(function (event) {
            event.preventDefault();
        });

        // 监听提交
        form.on('submit(add)', function (data) {
            $.ajax({
                url: '/admin/channel/add.do',
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

        // switch 开关监听
        form.on('switch(switch)', function (data) {
            if (data.elem.checked || "true" === data.elem.checked) {
                $("input[name=channelStatus]").val("1");
            } else {
                $("input[name=channelStatus]").val("0");
            }
        });

        form.on('switch(isNavSwitch)', function (data) {
            if (data.elem.checked || "true" === data.elem.checked) {
                $("input[name=isNav]").val("1");
            } else {
                $("input[name=isNav]").val("0");
            }
        });

        // select 开关监听
        form.on('select(channelTypeSelect)', function (data) {
            if ("3" === data.value) {
                $("#channelUrl").css("display", "block");
                var html = '<label for="channelUrl" ' +
                    'class="layui-form-label"> <span class="x-red">*</span>栏目链接</label> <div class="layui-input-inline"> ' +
                    '<input type="text" id="channelUrl" name="channelUrl" required="" lay-verify="required" ' +
                    'autocomplete="off" class="layui-input" placeholder="请填写栏目地址"> </div> <div class="layui-form-mid ' +
                    'layui-word-aux">例: /admin/index/index.do </div>';
                $("#channelUrl").html(html);
            } else {
                $("#channelUrl").css("display", "none");
                $("#channelUrl").empty();
            }
        });

        //普通图片上传
        var uploadInst = upload.render({
            elem: '#multipartFile'
            , url: '/admin/channel/upload_cover.do'
            , field: "multipartFile"
            , accept: "file"
            , before: function (obj) {
                console.log(obj);
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#coverPic').attr('src', result).css("height", "100px");
                });
            }
            , done: function (res) {
                $("#channelPic").val(res.data);
                return layer.msg(res.message);
            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function () {
                    uploadInst.upload();
                });
            }
        });
    });
</script>
<script>var _hmt = _hmt || [];
    (function () {
        var hm = document.createElement("script");
        hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();</script>
</body>

</html>