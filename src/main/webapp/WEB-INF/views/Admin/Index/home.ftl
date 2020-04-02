<!DOCTYPE html>
<html class="x-admin-sm">
    <head>
        <meta charset="UTF-8">
        <title>欢迎页面-X-admin2.2</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="stylesheet" href="/static/admin/css/font.css">
        <link rel="stylesheet" href="/static/admin/css/xadmin.css">
        <script src="/static/admin/lib/layui/layui.js" charset="utf-8"></script>
        <script type="text/javascript" src="/static/admin/js/xadmin.js"></script>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
          <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
          <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <div class="layui-fluid">
            <div class="layui-row layui-col-space15">
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-body ">
                            <blockquote class="layui-elem-quote">欢迎管理员：
                                <span class="x-red">test</span>！当前时间:2018-04-25 20:50:53
                            </blockquote>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header"><@shiro.hasPermission name="adminMenu:view">数据统计1</@shiro.hasPermission><@shiro.hasPermission name="adminMenu:add">数据统计2</@shiro.hasPermission></div>
                        <div class="layui-card-body ">
                            <ul class="layui-row layui-col-space10 layui-this x-admin-carousel x-admin-backlog">
                                <li class="layui-col-md3 layui-col-xs6">
                                    <a href="javascript:;" class="x-admin-backlog-body">
                                        <h3>待审核文章数</h3>
                                        <p><cite>${broadList.auditNum}</cite></p>
                                    </a>
                                </li>
                                <li class="layui-col-md3 layui-col-xs6">
                                    <a href="javascript:;" class="x-admin-backlog-body">
                                        <h3>累计文章数</h3>
                                        <p><cite>${broadList.articleNum}</cite></p>
                                    </a>
                                </li>
                                <li class="layui-col-md3 layui-col-xs6">
                                    <a href="javascript:;" class="x-admin-backlog-body">
                                        <h3>发布课程总数</h3>
                                        <p>
                                            <cite>67</cite></p>
                                    </a>
                                </li>
                                <li class="layui-col-md3 layui-col-xs6">
                                    <a href="javascript:;" class="x-admin-backlog-body">
                                        <h3>课程学习人次</h3>
                                        <p>
                                            <cite>67</cite></p>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="layui-col-sm6 layui-col-md6">
                    <div class="layui-card">
                        <div class="layui-card-header">本月文章发布量
                            <span class="layui-badge layui-bg-cyan layuiadmin-badge">月</span>
                        </div>
                        <div class="layui-card-body" style="min-height: 380px;">
                            <div id="main1" class="layui-col-sm12" style="height: 400px;"></div>
                        </div>
                    </div>
                </div>
                <div class="layui-col-sm6 layui-col-md6">
                    <div class="layui-card">
                        <div class="layui-card-header">本月课程学习人次
                            <span class="layui-badge layui-bg-cyan layuiadmin-badge">月</span>
                        </div>
                        <div class="layui-card-body" style="min-height: 380px;">
                            <div id="main2" class="layui-col-sm12" style="height: 400px;"></div>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">系统信息</div>
                        <div class="layui-card-body ">
                            <table class="layui-table">
                                <tbody>
                                    <tr>
                                        <th>xxx版本</th>
                                        <td>1.0.180420</td></tr>
                                    <tr>
                                        <th>服务器地址</th>
                                        <td>x.xuebingsi.com</td></tr>
                                    <tr>
                                        <th>操作系统</th>
                                        <td>WINNT</td></tr>
                                    <tr>
                                        <th>运行环境</th>
                                        <td>Apache/2.4.23 (Win32) OpenSSL/1.0.2j mod_fcgid/2.3.9</td></tr>
                                    <tr>
                                        <th>PHP版本</th>
                                        <td>5.6.27</td></tr>
                                    <tr>
                                        <th>PHP运行方式</th>
                                        <td>cgi-fcgi</td></tr>
                                    <tr>
                                        <th>MYSQL版本</th>
                                        <td>5.5.53</td></tr>
                                    <tr>
                                        <th>ThinkPHP</th>
                                        <td>5.0.18</td></tr>
                                    <tr>
                                        <th>上传附件限制</th>
                                        <td>2M</td></tr>
                                    <tr>
                                        <th>执行时间限制</th>
                                        <td>30s</td></tr>
                                    <tr>
                                        <th>剩余空间</th>
                                        <td>86015.2M</td></tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="layui-col-md12">
                    <div class="layui-card">
                        <div class="layui-card-header">开发团队</div>
                        <div class="layui-card-body ">
                            <table class="layui-table">
                                <tbody>
                                    <tr>
                                        <th>版权所有</th>
                                        <td>xuebingsi(xuebingsi)
                                            <a href="http://x.xuebingsi.com/" target="_blank">访问官网</a></td>
                                    </tr>
                                    <tr>
                                        <th>开发者</th>
                                        <td>马志斌(113664000@qq.com)</td></tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <style id="welcome_style"></style>
                <div class="layui-col-md12">
                    <blockquote class="layui-elem-quote layui-quote-nm">感谢layui,百度Echarts,jquery,本系统由x-admin提供技术支持。</blockquote></div>
            </div>
        </div>
        </div>
        <script src="https://cdn.bootcss.com/echarts/4.2.1-rc1/echarts.min.js"></script>
        <script type="text/javascript">
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main1'));

            // 指定图表的配置项和数据
            var option = {
                grid: {
                    top: '5%',
                    right: '1%',
                    left: '1%',
                    bottom: '10%',
                    containLabel: true
                },
                tooltip: {
                    trigger: 'axis'
                },
                xAxis: {
                    type: 'category',
                    data: ['周一','周二','周三','周四','周五','周六','周日']
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    name:'用户量',
                    data: [820, 932, 901, 934, 1290, 1330, 1320],
                    type: 'line',
                    smooth: true
                }]
            };


            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main2'));

            // 指定图表的配置项和数据
            var option = {
                tooltip : {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'cross',
                        label: {
                            backgroundColor: '#6a7985'
                        }
                    }
                },
                grid: {
                    top: '5%',
                    right: '2%',
                    left: '1%',
                    bottom: '10%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data : ['周一','周二','周三','周四','周五','周六','周日']
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'PV',
                        type:'line',
                        areaStyle: {normal: {}},
                        data:[120, 132, 101, 134, 90, 230, 210],
                        smooth: true
                    },
                    {
                        name:'UV',
                        type:'line',
                        areaStyle: {normal: {}},
                        data:[45, 182, 191, 234, 290, 330, 310],
                        smooth: true,

                    }
                ]
            };


            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);


            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main3'));

            // 指定图表的配置项和数据
            var option = {
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
                },
                series : [
                    {
                        name: '访问来源',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:[
                            {value:335, name:'直接访问'},
                            {value:310, name:'邮件营销'},
                            {value:234, name:'联盟广告'},
                            {value:135, name:'视频广告'},
                            {value:1548, name:'搜索引擎'}
                        ],
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };



            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);

            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main4'));

            // 指定图表的配置项和数据
            var option = {
                tooltip : {
                    formatter: "{a} <br/>{b} : {c}%"
                },
                series: [
                    {
                        name: '硬盘使用量',
                        type: 'gauge',
                        detail: {formatter:'{value}%'},
                        data: [{value: 88, name: '已使用'}]
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        </script>
    </body>
</html>