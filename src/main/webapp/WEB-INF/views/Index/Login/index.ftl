<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>紫色背景简洁登陆页面演示_dowebok</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="/static/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/static/login/css/util.css">
    <link rel="stylesheet" type="text/css" href="/static/login/css/main.css">
	<script src="/static/index/index/js/jquery.min.js"></script>
</head>

<body>
<div class="dowebok">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="/static/login/images/img-01.png" alt="IMG">
            </div>

            <form class="login100-form validate-form">
				<span class="login100-form-title">
					会员登陆
				</span>

                <div class="wrap-input100 validate-input">
                    <input class="input100" type="text" id="account" name="account" placeholder="帐号">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
						<i class="fa fa-user" aria-hidden="true"></i>
					</span>
                </div>

                <div class="wrap-input100 validate-input">
                    <input class="input100" type="password" id="password" name="pass" placeholder="密码">
                    <span class="focus-input100"></span>
                    <span class="symbol-input100">
						<i class="fa fa-lock" aria-hidden="true"></i>
					</span>
                </div>

                <div class="container-login100-form-btn">
                    <a type="button" id="submit-btn" class="login100-form-btn" href="javascript:;">
                        登陆
                    </a>
                </div>

                <div class="text-center p-t-12">
                    <a class="txt2" href="javascript:">
                        忘记密码？
                    </a>
                </div>

                <div class="text-center p-t-136">
                    <a class="txt2" href="http://www.dowebok.com/" target="_blank">
                        还没有账号？联系管理员
                        <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $("#submit-btn").click(function () {
    	var data = {
    		"account": $("#account").val(),
			"password": $("#password").val()
		};

        $.ajax({
            url: '/index/login/login.do',
            type: 'post',
            async: true,
            data: {data: JSON.stringify(data)},
            dataType: 'json',
            success: function (res) {
                console.log(res);
            }
        });
    });
</script>
</body>
</html>