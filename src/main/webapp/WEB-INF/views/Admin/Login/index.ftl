<!doctype html>
<html  class="x-admin-sm">
<head>
	<meta charset="UTF-8">
	<title>管理端登录</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="/static/admin/css/font.css">
    <link rel="stylesheet" href="/static/admin/css/login.css">
	  <link rel="stylesheet" href="/static/admin/css/xadmin.css">
    <script type="text/javascript" src="/static/admin/js/jquery.min.js"></script>
    <script src="/static/admin/lib/layui/layui.js" charset="utf-8"></script>
    <!--[if lt IE 9]>
      <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
      <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="login-bg">
    
    <div class="login layui-anim layui-anim-up">
        <div class="message">管理登录</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form" >
            <input name="userAccount" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="userPassword" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" id="login-button" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
    </div>

    <script>
        //监听提交
        $("#login-button").click(function(event) {
            event.preventDefault();
        });

        $(function  () {
            layui.use('form', function(){
              var form = layui.form;
              //监听提交
              form.on('submit(login)', function(data){
                  $("#login-button").attr("disabled", "disabled");
                  $.ajax({
                      url: '/admin/login/login.do',
                      type: 'post',
                      async: true,
                      data: {data: JSON.stringify(data.field)},
                      dataType: 'json',
                      success: function (res) {
                          if("1" === res.status || 1 === res.status) {
                              layer.msg(res.message,function(){
                                  location.href='http://localhost:8080/admin/index/index.do'
                              });
                          } else {
                              layer.msg(res.message);
                              setTimeout(function() {
                                  $("#login-button").removeAttr("disabled");
                              }, 1000);
                          }
                      }
                  });
              });
            });
        })
    </script>
    <!-- 底部结束 -->
</body>
</html>