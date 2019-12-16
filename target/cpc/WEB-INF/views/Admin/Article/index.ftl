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
       onclick="window.location.href='/admin/article/view.do'" title="刷新">
        <i class="layui-icon layui-icon-refresh" style="line-height:30px"></i></a>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body ">
                    <form class="layui-form layui-col-space5" lay-filter="search-box">
                        <div class="layui-inline layui-show-xs-block">
                            <input type="text" name="articleName" id="articleName" placeholder="请输入文章标题" autocomplete="off"
                                   class="layui-input">
                        </div>
                        <div class="layui-inline layui-show-xs-block">
                            <select name="channelNo" id="channelNo">
                                <option value="">所属栏目</option>
                                <#list channelList as channel>
                                    <option value="${channel.indexChannel.channelNo}">${channel.indexChannel.channelName}</option>
                                    <#list channel.childChannelList as childChannel>
                                        <option value="${childChannel.channelNo}">|--${childChannel.channelName}</option>
                                    </#list>
                                </#list>
                            </select>
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <select name="articleType" lay-filter="status-select" id="articleType">
                                <option value="">文章类型</option>
                                <#list articleTypeAdminDictList as articleTypeAdminDict>
                                    <option value="${articleTypeAdminDict.dictKey}">${articleTypeAdminDict.dictValue}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <select name="isTop" lay-filter="status-select" id="isTop">
                                <option value="">是否置顶</option>
                                <#list isTopAdminDictList as isTopAdminDict>
                                    <option value="${isTopAdminDict.dictKey}">${isTopAdminDict.dictValue}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <select name="isBold" lay-filter="status-select" id="isBold">
                                <option value="">是否加粗</option>
                                <#list isBoldAdminDictList as isBoldAdminDict>
                                    <option value="${isBoldAdminDict.dictKey}">${isBoldAdminDict.dictValue}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="layui-input-inline layui-show-xs-block">
                            <select name="articleStatus" lay-filter="status-select" id="articleStatus">
                                <option value="">文章状态</option>
                                <#list articleStatusAdminDictList as articleStatusAdminDict>
                                    <option value="${articleStatusAdminDict.dictKey}">${articleStatusAdminDict.dictValue}</option>
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
                    <button class="layui-btn" onclick="xadmin.open('添加文章','/admin/article/add_page.do',900,700)"><i
                                class="layui-icon"></i>添加
                    </button>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table layui-form">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>文章标题</th>
                            <th>所属栏目</th>
                            <th>文章类型</th>
                            <th>是否置顶</th>
                            <th>是否加粗</th>
                            <th>文章状态</th>
                            <th>文章来源</th>
                            <th>发布时间</th>
                            <th>操作</th>
                        </thead>
                        <tbody>
                        <#list ArticleList as article>
                            <tr>
                                <td>${article_index + 1}</td>
                                <td>${article.articleName}</td>
                                <td>${article.channelName}</td>
                                <td>
                                    <#list articleTypeAdminDictList as articleTypeAdminDict>
                                        <#if articleTypeAdminDict.dictKey = article.articleType>${articleTypeAdminDict.dictValue}</#if>
                                    </#list>
                                </td>
                                <td>
                                    <#list isTopAdminDictList as isTopAdminDict>
                                        <#if isTopAdminDict.dictKey = article.isTop>${isTopAdminDict.dictValue}</#if>
                                    </#list>
                                </td>
                                <td>
                                    <#list isBoldAdminDictList as isBoldAdminDict>
                                        <#if isBoldAdminDict.dictKey = article.isBold>${isBoldAdminDict.dictValue}</#if>
                                    </#list>
                                </td>
                                <td class="td-status">
                                    <#if article.articleStatus == "1">
                                        <a class="layui-btn layui-btn-normal layui-btn-mini" onclick="changeStatus('dis_audit','${article.articleNo}')">已发布</a>
                                    <#else>
                                        <a class="layui-btn layui-btn-danger layui-btn-mini" onclick="changeStatus('audit','${article.articleNo}')">待审核</a>
                                    </#if>
                                </td>
                                <td>${article.articleSource}</td>
                                <td>${article.publishTime?string('yyyy-MM-dd')}</td>
                                <td class="td-manage">
                                    <a title="编辑" onclick="xadmin.open('编辑文章','/admin/article/edit_page.do?articleNo=${article.articleNo}',900,700)" href="javascript:;">
                                        <i class="layui-icon">&#xe642;</i>
                                    </a>
                                    <a title="删除" onclick="deleteArticle('${article.articleNo}')" href="javascript:;">
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

        <#if conditions??>
        form.val("search-box", {
            "articleName": "${conditions.articleName}",
            "channelNo": "${conditions.channelNo}",
            "articleStatus": "${conditions.articleStatus}",
            "articleType": "${conditions.articleType}",
            "isTop": "${conditions.isTop}",
            "isBold": "${conditions.isBold}"
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
    function changeStatus(option, articleNo) {
        var url = "/admin/article/" + option + ".do";
        var tips = option === "audit" ? "审核并发布" : "取消发布";
        layer.confirm('确认要' + tips + '文章吗？', function (index) {
            $.ajax({
                url: url,
                type: 'post',
                data: {articleNo: articleNo},
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
    function deleteUser(articleNo) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "/admin/article/delete.do",
                type: 'post',
                data: {articleNo: articleNo},
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

    // 删除文章
    function deleteArticle(articleNo) {
        layer.confirm('确认要删除吗？', function (index) {
            //发异步删除数据
            $.ajax({
                url: "/admin/article/delete.do",
                type: 'post',
                data: {articleNo: articleNo},
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
            articleName: $("#articleName").val(),
            channelNo: $("#channelNo").val(),
            articleStatus: $("#articleStatus").val(),
            articleType: $("#articleType").val(),
            isTop: $("#isTop").val(),
            isBold: $("#isBold").val()
        };

        window.location.href = "/admin/article/view.do" + "?conditions=" + encodeURI(JSON.stringify(data)) + "&currentPage=" + currentPage;
    }

    // 页面跳转
    function skipToPage(page) {
        queryPage(page);
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