define(function(require){function a(a){var c="";return c+='<div class="yanzheng-id">	                    <div class="keybox"><i class="icon-key"></i></div>				             <p class="font1 mb20">请输入登录密码验证身份<br/>'+a+'</p>	                    <div class="dialogBox yanZhengBox" style="width: 430px;">				            <div class="moco-form-group">				                <label for="inputEmail3" class="moco-control-label">密码：</label>				                <div class="moco-control-input">				                    <input type="password"  placeholder="请输入密码" class="js-pwd moco-form-control" data-validate="require-password">				                    <div class="moco-control-tip errorHint color_red"></div>				                </div>				            </div>				             <div class="moco-form-group">				                <label for="inputEmail3" class="moco-control-label">验证码：</label>				                <div class="moco-control-input w135 mr12">				                    <input data-callback="checkverity"   maxLength=4 placeholder="输入验证码" class="js-verify moco-form-control w140 fl js-phoneCode" data-validate="require-string" data-minLength="4">				                    <div class="moco-control-tip errorHint color_red" data-error-hint="请输入4位验证码"></div>				                </div>				                <div class="yzmBox verify-img-wrap "><img class="verify-img"/><a href="javascript:void(0)" hidefocus="true" class="icon-refresh js-verify-refresh"></a></div>				            </div>				            <div class="moco-form-group">				                <label for="inputEmail3" class="moco-control-label"></label>				                <div class="moco-control-input">				                    <a href="javascript:void(0);" class="moco-btn moco-btn-blue js-sf-submit">确定</a>				                    <a href="javascript:void(0);" class="moco-btn moco-btn-normal js-modal-close">取消</a>				                    <a href="//www.imooc.com/user/newforgot" target="_blank">找回密码</a>				                	<p class="js-gerror tl g_error js-error"></p>				                </div>				            </div>				        </div>            		</div>'}function c(){$(".js-verify-row").show(),$(".verify-img").attr("src","/user/verifycode?t="+(new Date).getTime())}function h(){var a='<div class="yanzheng-phone">            <div class="keybox">解绑后，您将无法再使用该手机号对此账号进行登陆及找回密码等操作</div>            <div class="dialogBox yanZhengBox" style="width: 430px;">	            <div class="moco-form-group">	                <label for="inputEmail3" class="moco-control-label">当前绑定手机号：</label>	                <div class="moco-control-input"><span class="yanzheng-phone-text">'+$("#account").val()+'</span></div>	            </div>	            <div class="moco-form-group">	                <label for="inputEmail3" class="moco-control-label">请输入当前绑定手机号：</label>	                <div class="moco-control-input">	                    <input  data-callback="checkPhone"  placeholder="请输入手机号" class="moco-form-control js-phoneNumber" id="">	                    <div class="moco-control-tip errorHint color_red"></div>	                </div>	            </div>	            <div class="moco-form-group">	                <label for="inputEmail3" class="moco-control-label btn-box"></label>	                <div class="moco-control-input">	                	<a href="javascript:void(0);" class="moco-btn moco-btn-normal js-modal-close">取消</a>	                    <a href="javascript:void(0);" class="moco-btn moco-btn-blue js-phone-submit">下一步</a>	                	<p class="js-gerror tl g_error js-error"></p>	                </div>	            </div>	        </div>		</div>';$(".moco-modal-overlay").remove(),$.dialog(a,{title:"验证当前绑定手机号",modal:!0})}function v(){var a='<div class="remove-phone">			<h3 class="tipbox">确认解绑手机号？</h3>			<div class="keybox">'+(weixin_unionid||email_count||qq_sid||weibo_sid?"解绑手机号后，您只能通过已绑定的邮箱/微信/QQ/微博登录。":"由于该账号未绑定其他信息，解绑手机号后该账号将无法找回。<br>如需保留该账号请先完善其他绑定信息。")+'</div>			<div class="dialogBox yanZhengBox">				<div class="moco-form-group">					<label for="inputEmail3" class="moco-control-label btn-box"></label>					<div class="moco-control-input">						<a href="javascript:void(0);" class="moco-btn moco-btn-normal js-modal-close">取消</a>						<a href="javascript:void(0);" class="moco-btn moco-btn-blue js-do-remove">确认解绑</a>						<p class="js-gerror tl g_error js-error"></p>					</div>				</div>			</div>		</div>';$(".moco-modal-overlay").remove(),$.dialog(a,{title:"解绑手机号",modal:!0,width:"456px"})}function g(){var a=$(".yanzheng-phone .js-phoneNumber").val();return""==a?($(".yanzheng-phone input").addClass("inpt-error").focus(),void $(".yanzheng-phone .js-error").html("手机号码不能为空")):void(W.validate($(".yanzheng-phone"))&&($(".yanzheng-phone .js-error").html(""),y.errortipshow($(".msg-layer input"),""),$(".yanzheng-phone input").removeClass("inpt-error"),"验证中..."!=$(".yanzheng-phone .js-phone-submit").html()&&($(".yanzheng-phone .js-phone-submit").html("验证中..."),$.ajax({url:"/passport/user/checkphone",data:{phone:a,type:1,action:"remove"==Z?"untying":""},dataType:"json",success:function(a){$(".yanzheng-phone .js-phone-submit").html("确定"),10001==a.status?($(".moco-modal-overlay").remove(),"change"==Z?(z||(z=new P({el:"body"})),z.render()):"remove"==Z&&v()):($(".yanzheng-phone input").addClass("inpt-error").focus(),$(".yanzheng-phone .js-error").html(a.msg))},error:function(){$(".yanzheng-phone .js-phone-submit").html("确定"),$(".yanzheng-phone .js-error").html("服务器错误，请稍后重试！")}}))))}function b(a){moco.validateCallback.rel=!1,$.ajax({url:"/user/verifycheck",method:"get",async:!1,data:{verify:a},dataType:"json",success:function(a){0==a.result?(moco.validateCallback.errorHint="",moco.validateCallback.rel=!0):moco.validateCallback.errorHint=a.msg},error:function(){moco.validateCallback.errorHint="网络错误"}})}require("common"),require("./common/common.js");var y=(require("/static/page/user/messagebox.js"),require("/static/page/user/common/settingUitl.js"));require("/static/component/base/util/modal.button.js");var j,w,k,z,_,B,C=require("./settingBindDialogs/setting_phone/setting_phone.js"),D=require("./settingBindDialogs/setting_email/setting_email.js"),E=require("./settingBindDialogs/yanzheng_email/yanzheng_email.js"),T=require("./settingBindDialogs/change_email/change_email.js"),H=require("./settingBindDialogs/setting_password/setting_password.js"),P=require("./settingBindDialogs/change_phone/change_phone.js"),Z="";$(document).on("click",".js-bindemail",function(){B=new D({el:"body",hasPass:hasPass}),B.render()}),$(document).on("click",".js-changePWD",function(){w||(w=new H({el:"body"})),w.render()}),$.ajaxSetup({cache:!1});var N=function(){function a(){for(var a in o)a.indexOf("/user")>-1&&(o[a].close&&o[a].close(),o[a]=null,delete o[a])}var o={};return{open:function(c){var l,t;return o[c]&&o[c].closed===!1?void(o[c].focus&&o[c].focus()):(a(),l=(screen.width-600)/2,t=(screen.height-400)/2,void(o[c]=window.open(c,"_blank","toolbar=no, directories=no, status=no, menubar=no, width=600, height=500, top="+t+", left="+l)).focus())},clear:a}}();$(document).on("click",".js-bindphone",function(){j||(j=new C({el:"body",mode:"signup",hasPass:hasPass})),j.render()}),$(document).on("focus","input",function(){$(".errorHint , .js-gerror").html("")}),$(document).on("click",".js-verify-refresh",function(){c()}),$(document).on("click",".js-bind",function(e){e.preventDefault(),N.open($(this).attr("href")+"&referer="+window.location.protocol+"//"+window.location.hostname)}),$(document).on("click","[data-unbind]",function(){var a=$(this).attr("data-unbind"),c={qq:"QQ",weibo:"新浪微博",weixin:"微信"}[a],m=$(".msg-layer");m.length||($("body").append('<div class="msg-layer" style="height:300px!important">                <h3>解除绑定</h3>                <p>解绑后将不能再使用'+c+'帐号登录慕课网。</p>                <div class="error js-error"></div>                <input type="password" placeholder="请输入慕课网登录密码" />                <div class="error"></div>                <a href="javascript:void(0)" class="js-sf-submit btn-submit">确定</a>                <a href="//www.imooc.com/user/newforgot" target="_blank" style="float: right;margin-right: 30px;margin-top: 10px;">找回密码</a>                <button aria-hidden="true" hidefocus="true" data-dismiss="modal" class="btn-close" type="button"></button>            </div>'),m=$(".msg-layer"),m.on("shown",function(){function c(){$(".js-error").html(),y.errortiphide($(".msg-layer input"));var c=$(".msg-layer input").val();return 0==c.length?($(".msg-layer input").addClass("inpt-error").focus(),void y.errortipshow($(".msg-layer input"),"请输入慕课网登录密码")):void $.ajax({url:"/passport/user/tpbindcancel",data:{password:c,tp:a},dataType:"json",success:function(a){10001==a.status?window.location.reload():$(".js-error").html(a.msg)},error:function(){$(".js-error").html("服务器错误，请稍后重试！")}})}$(".msg-layer .js-sf-submit").on("click",function(){c()}),$(".msg-layer input").on("keydown",function(a){switch(a.keyCode){case 13:c()}})}).on("hidden",function(){$(this).remove()})),m.modal("show")}),window.__fireLogined=function(){window.location.reload()};var Q=$("#jsMail").val(),L=$("#jsPhone").text();$(document).on("click",".js-change-phone",function(){Z="change",h()}),$(document).on("click",".js-remove-phone",function(){Z="remove";var a='<div class="remove-phone">			<h3 class="tipbox">是否继续解绑手机号？</h3>			<div class="keybox">账号在未绑定手机号状态下将无法观看付费课程。</div>			<div class="dialogBox yanZhengBox">				<div class="moco-form-group">					<label for="inputEmail3" class="moco-control-label btn-box"></label>					<div class="moco-control-input">						<a href="javascript:void(0);" class="moco-btn moco-btn-normal js-modal-close">取消</a>						<a href="javascript:void(0);" class="moco-btn moco-btn-blue js-continue-remove">继续解绑</a>						<p class="js-gerror tl g_error js-error"></p>					</div>				</div>			</div>		</div>';$(".moco-modal-overlay").remove(),$.dialog(a,{title:"解绑手机号",modal:!0,width:"456px"})}),$(document).on("click",".js-continue-remove",function(){h()}),$(document).on("click",".js-do-remove",function(){$.ajax({url:"/passport/user/untyingphone",type:"get",success:function(a){"string"==typeof a&&(a=JSON.parse(a)),10001==a.status?($(".js-modal-close").click(),setTimeout(function(){$.alert("解绑成功",{info:"您的手机号现在可用于平台其他账号的绑定",callback:function(){window.location.reload()}})},200)):($(".js-modal-close").click(),$.alert(a.msg||"操作失败"))}})}),$(".js-operate-phone").click(function(){var a='<div class="operate-phone">			<div class="keybox">为保障您的账号安全和收费课程的正常学习，小慕希望您能为帐号绑定手机号，更换手机号后请及时换绑。（1个手机号只能绑定1个慕课网帐号）</div>			<div class="dialogBox yanZhengBox">				<div class="moco-form-group">					<label for="inputEmail3" class="moco-control-label btn-box"></label>					<div class="moco-control-input">						<a href="javascript:void(0);" class="moco-btn moco-btn-normal js-remove-phone">解绑手机号</a>						<a href="javascript:void(0);" class="moco-btn moco-btn-blue js-change-phone">更换手机号</a>						<p class="js-gerror tl g_error js-error"></p>					</div>				</div>			</div>		</div>';$.dialog(a,{title:"当前绑定手机号："+L,modal:!0,width:"456px"})}),$(document).on("click",".js-phone-submit",function(){g()}).on("blur",".js-phoneNumber",function(){var a=$(this).val(),r=W.Validater.validateRules["require-mobile-phone"].regex;return r.lastIndex=0,a.length?0==a.indexOf("+")||r.test(a)?void 0:void $(this).next(".moco-control-tip").html("请输入正确手机号"):void $(this).next(".moco-control-tip").html("请输入手机号")}),$(".yanzheng-phone input").on("keydown",function(a){switch(a.keyCode){case 13:g()}}),$(".js-change").click(function(){function h(){if(W.validate($(".yanzheng-id"))&&($(".yanzheng-id .js-error").html(""),y.errortipshow($(".msg-layer input"),""),$(".yanzheng-id input").removeClass("inpt-error"),"验证中..."!=$(".yanzheng-id .js-sf-submit").html())){$(".yanzheng-id .js-sf-submit").html("验证中...");var a=$(".yanzheng-id .js-pwd").val(),h=$(".yanzheng-id .js-verify").val();$.ajax({url:"/user/ajaxcheckpass",data:{passwd:a,verify:h},dataType:"json",success:function(a){$(".yanzheng-id .js-sf-submit").html("确定"),0==a.result?($(".js-modal-close").click(),"email"==g&&(_||(_=new T({el:"body",mall:Q})),_.render()),"phone"==g&&(z||(z=new P({el:"body"})),z.render())):(c(),$(".yanzheng-id input").addClass("inpt-error").focus(),$(".yanzheng-id .js-error").html(a.msg))},error:function(){$(".yanzheng-id .js-sf-submit").html("确定"),$(".yanzheng-id .js-error").html("服务器错误，请稍后重试！")}})}}var v,g=$(this).attr("changeType");v=a("email"==g?Q:L),$.dialog(v,{title:"验证身份",modal:!0}),c(),$(".verify-img").attr("src","/user/verifycode?t="+(new Date).getTime()),$(document).on("click",".js-sf-submit",function(){h()}),$(".yanzheng-id input").on("keydown",function(a){switch(a.keyCode){case 13:h()}})}),moco.validateCallback.checkverity=function(a){b(a)},$(".js-verify").click(function(){var a=$(this);"正在发送..."!=a.text()&&(a.text("正在发送..."),$.ajax({url:"/user/verificationmail",dataType:"json",success:function(c){0==c.status?(k||(k=new E({el:"body",mall:Q})),k.render(),a.text("立即验证")):($.alert("error",{info:c.msg}),a.text("立即验证"))},error:function(){$.alert("error",{info:"系统错误！"}),a.text("立即验证")}}))})});