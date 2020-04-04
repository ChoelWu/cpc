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
    <script src="/static/admin/lib/layedit/Content/Layui-KnifeZ/layui.js"></script>
    <script src="/static/admin/lib/layedit/Content/ace/ace.js"></script>
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
            <input type="hidden" name="articleId" value="${indexArticle.articleId}">
            <div class="layui-form-item">
                <label for="userName" class="layui-form-label">
                    <span class="x-red">*</span>文章标题</label>
                <div class="layui-input-inline">
                    <input type="text" id="articleName" name="articleName" required="" lay-verify="required|articleName"
                           autocomplete="off" class="layui-input" placeholder="请输入文章标题" value="${indexArticle.articleName}">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    40 字符以内，不能使用特殊字符
                </div>
            </div>
            <div class="layui-form-item">
                <label for="articleType" class="layui-form-label">
                    <span class="x-red">*</span>文章类型</label>
                <div class="layui-input-inline">
                    <select id="articleType" name="articleType" class="valid" lay-verify="required" lay-filter="articleTypeSelect">
                        <#list articleTypeAdminDictList as articleTypeAdminDict>
                            <option value="${articleTypeAdminDict.dictKey}" <#if indexArticle.articleType = articleTypeAdminDict.dictKey>selected</#if>>${articleTypeAdminDict.dictValue}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item" id="articleUrl" style="display: none;"></div>
            <div class="layui-form-item">
                <label for="channelNo" class="layui-form-label">
                    <span class="x-red">*</span>所属栏目</label>
                <div class="layui-input-inline">
                    <select id="channelNo" name="channelNo" class="valid" lay-verify="required">
                        <#list channelList as channel>
                            <option value="${channel.indexChannel.channelNo}" <#if indexArticle.channelNo = channel.indexChannel.channelNo>selected</#if>>${channel.indexChannel.channelName}</option>
                            <#list channel.childChannelList as childChannel>
                                <option value="${childChannel.channelNo}" <#if indexArticle.channelNo = childChannel.channelNo>selected</#if>>|--${childChannel.channelName}</option>
                            </#list>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="articleSource" class="layui-form-label">
                    <span class="x-red">*</span>文章来源</label>
                <div class="layui-input-inline">
                    <input type="text" id="articleSource" name="articleSource" required="" lay-verify="required|articleSource"
                           autocomplete="off" class="layui-input" placeholder="请输入文章来源" value="${indexArticle.articleSource}">
                </div>
                <div class="layui-form-mid layui-word-aux">
                    16字符以内，不能使用特殊字符
                </div>
            </div>
            <div class="layui-form-item">
                <label for="publishTime" class="layui-form-label">
                    <span class="x-red">*</span>发布时间</label>
                <div class="layui-input-inline">
                    <input type="text" id="publishTime" name="publishTime" id="publishTime" required="" lay-verify="required"
                           autocomplete="off" class="layui-input" placeholder="请输入发布时间" value="${indexArticle.publishTime?string('yyyy-MM-dd')}">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="isTop" class="layui-form-label">
                    <span class="x-red">*</span>是否置顶</label>
                <div class="layui-input-inline">
                    <select id="isTop" name="isTop" class="valid" lay-verify="required">
                        <#list isTopAdminDictList as isTopAdminDict>
                            <option value="${isTopAdminDict.dictKey}" <#if indexArticle.isTop = isTopAdminDict.dictKey>selected</#if>>${isTopAdminDict.dictValue}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="isBold" class="layui-form-label">
                    <span class="x-red">*</span>是否加粗</label>
                <div class="layui-input-inline">
                    <select id="isBold" name="isBold" class="valid" lay-verify="required">
                        <#list isBoldAdminDictList as isBoldAdminDict>
                            <option value="${isBoldAdminDict.dictKey}" <#if indexArticle.isBold = isBoldAdminDict.dictKey>selected</#if>>${isBoldAdminDict.dictValue}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="articleCover" class="layui-form-label">
                    <span class="x-red">*</span>栏目图</label>
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="multipartFile">上传图片</button>
                    <div class="layui-upload-list" style="padding-left: 110px;">
                        <img class="layui-upload-img" id="coverPic" <#if indexArticle.articleCover?? || "" = indexArticle.articleCover>src="${indexArticle.articleCover}" style="height: 100px;"</#if>>
                        <p id="demoText"></p>
                    </div>
                </div>
                <input type="hidden" name="articleCover" id="articlePic" value="${indexArticle.articleCover}">
            </div>
            <div class="layui-form-item layui-form-text">
                <label for="articleSummary" class="layui-form-label">文章简介</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" id="articleSummary" name="articleSummary"
                              class="layui-textarea">${indexArticle.articleSummary}</textarea>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label for="articleContent" class="layui-form-label">文章内容</label>
                <div class="layui-input-block">
                    <textarea placeholder="请输入内容" id="articleContent" name="articleContent"
                              class="layui-textarea"></textarea>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label"></label>
                <button id="sb-button" class="layui-btn" lay-filter="add" lay-submit="">提交</button>
            </div>
        </form>
    </div>
</div>
<script>
    $(document).ready(function() {
        <#if '2' = indexArticle.articleType>
        $("#articleUrl").css("display", "block");
        var html = '<label for="articleUrl" class="layui-form-label"><span class="x-red">*</span>链接地址</label>' +
            '<div class="layui-input-inline"><input type="text" id="articleUrl" name="articleUrl" required="" ' +
            'lay-verify="required|articleUrl|url" autocomplete="off" value="${indexArticle.articleUrl!''}" class="layui-input" ' +
            'placeholder="请输入文章链接地址"></div><div class="layui-form-mid layui-word-aux">' +
            '16字符以内，不能使用特殊字符</div>';
        $("#articleUrl").html(html);
        </#if>
    });

    layui.use(['form', 'layer', 'jquery', 'layedit', 'laydate', 'upload'], function () {
        $ = layui.jquery;
        var form = layui.form,
            layer = layui.layer,
            laydate = layui.laydate,
            upload = layui.upload,
            layedit = layui.layedit;

        laydate.render({
            elem: '#publishTime'
        });

        //自定义验证规则
        form.verify({
            articleName: function (value) {
                if (value.length > 40) {
                    return '文章标题长度在 40 字符之内';
                }
            },
            articleUrl: function (value) {
                if (value.length > 90) {
                    return '链接地址长度在 90 字符之内';
                }
            },
            articleSource: function (value) {
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '文章来源不能有特殊字符';
                }
                if (value.length > 10) {
                    return '文章来源长度在 10 字符之内';
                }
            }
        });

        //监听提交
        $("#sb-button").click(function(event) {
            event.preventDefault();
        });

        form.on('submit(add)', function (data) {
            var rawData = data.field;
            console.log(rawData);
            $.ajax({
                url: '/admin/article/edit.do',
                type: 'post',
                data: {data: JSON.stringify(rawData)},
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

        // select 开关监听
        form.on('select(articleTypeSelect)', function (data) {
            if ("2" === data.value) {
                $("#articleUrl").css("display", "block");
                var html = '<label for="articleUrl" class="layui-form-label"><span class="x-red">*</span>链接地址</label>' +
                    '<div class="layui-input-inline"><input type="text" id="articleUrl" name="articleUrl" required="" ' +
                    'lay-verify="required|articleUrl|url" autocomplete="off" value="${indexArticle.articleUrl!''}" class="layui-input" ' +
                    'placeholder="请输入文章链接地址"></div><div class="layui-form-mid layui-word-aux">' +
                    '16字符以内，不能使用特殊字符</div>';
                $("#articleUrl").html(html);
            } else {
                $("#articleUrl").css("display", "none");
                $("#articleUrl").empty();
            }
        });

        //普通图片上传
        upload.render({
            elem: '#multipartFile'
            , url: '/admin/article/upload_cover.do'
            , field: "multipartFile"
            , accept: "file"
            , before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#coverPic').attr('src', result).css("height", "100px");
                });
            }
            , done: function (res) {
                $("#articlePic").val(res.data);
                return layer.msg(res.message);
            }
            , error: function () {
                //演示失败状态，并实现重传
                var demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">上传失败</span>');
            }
        });

        // 富文本编辑器
        layedit.set({
            //暴露layupload参数设置接口 --详细查看layupload参数说明
            uploadImage: {
                url: 'your url',
                accept: 'image',
                acceptMime: 'image/*',
                exts: 'jpg|png|gif|bmp|jpeg',
                size: 1024 * 10,
                data: {
                    name: "测试参数",
                    age:99
                }
                ,done: function (data) {
                    console.log(data);
                }
            },
            uploadVideo: {
                url: 'your url',
                accept: 'video',
                acceptMime: 'video/*',
                exts: 'mp4|flv|avi|rm|rmvb',
                size: 1024 * 10 * 2,
                done: function (data) {
                    console.log(data);
                }
            }
            , uploadFiles: {
                url: 'your url',
                accept: 'file',
                acceptMime: 'file/*',
                size: '20480',
                autoInsert: true , //自动插入编辑器设置
                done: function (data) {
                    console.log(data);
                }
            }
            //右键删除图片/视频时的回调参数，post到后台删除服务器文件等操作，
            //传递参数：
            //图片： imgpath --图片路径
            //视频： filepath --视频路径 imgpath --封面路径
            //附件： filepath --附件路径
            , calldel: {
                url: 'your url',
                done: function (data) {
                    console.log(data);
                }
            }
            , rightBtn: {
                type: "layBtn",//default|layBtn|custom  浏览器默认/layedit右键面板/自定义菜单 default和layBtn无需配置customEvent
                customEvent: function (targetName, event) {
                    //根据tagName分类型设置
                    switch (targetName) {
                        case "img":
                            alert("this is img");
                            break;
                        default:
                            alert("hello world");
                            break;
                    };
                    //或者直接统一设定
                    //alert("all in one");
                }
            }
            //测试参数
            , backDelImg: true
            //开发者模式 --默认为false
            , devmode: true
            //是否自动同步到textarea
            , autoSync: true
            //内容改变监听事件
            , onchange: function (content) {
                console.log(content);
            }
            //插入代码设置 --hide:false 等同于不配置codeConfig
            , codeConfig: {
                hide: true,  //是否隐藏编码语言选择框
                default: 'javascript', //hide为true时的默认语言格式
                encode: true //是否转义
                ,class:'layui-code' //默认样式
            }
            //新增iframe外置样式和js
            , quote:{
                style: ['Content/css.css'],
                //js: ['/Content/Layui-KnifeZ/lay/modules/jquery.js']
            }
            //自定义样式-暂只支持video添加
            //, customTheme: {
            //    video: {
            //        title: ['原版', 'custom_1', 'custom_2']
            //        , content: ['', 'theme1', 'theme2']
            //        , preview: ['', '/images/prive.jpg', '/images/prive2.jpg']
            //    }
            //}
            //插入自定义链接
            , customlink:{
                title: '插入layui官网'
                , href: 'https://www.layui.com'
                ,onmouseup:''
            }
            , facePath: 'http://knifez.gitee.io/kz.layedit/Content/Layui-KnifeZ/'
            , devmode: true
            , videoAttr: ' preload="none" '
            //预览样式设置，等同layer的offset和area规则，暂时只支持offset ,area两个参数
            //默认为 offset:['r'],area:['50%','100%']
            //, previewAttr: {
            //    offset: 'r'
            //    ,area:['50%','100%']
            //}
            , tool: [
                'html', 'undo', 'redo', 'code', 'strong', 'italic', 'underline', 'del', 'addhr', '|','removeformat', 'fontFomatt', 'fontfamily','fontSize', 'fontBackColor', 'colorpicker', 'face'
                , '|', 'left', 'center', 'right', '|', 'link', 'unlink', 'images', 'image_alt', 'video','attachment', 'anchors'
                , '|'
                , 'table'
                , 'fullScreen','preview'
            ]
            , height: '500px'
        });

        var ieditor = layedit.build('articleContent');
        //设置编辑器内容
        layedit.setContent(ieditor, "${indexArticle.articleContent!''}", false);
    });
</script>
</body>
</html>
