define(function(require){function a(a,c,g){function v(a){if(document.selection){_.focus();var c=document.selection.createRange();c.text=a,c.collapse(),c.select()}else if(_.selectionStart||"0"==_.selectionStart){var g=_.selectionStart,$=_.selectionEnd;_.value=_.value.substring(0,g)+a+_.value.substring($,_.value.length),_.selectionStart=_.selectionEnd=g+a.length}else _.value+=a}function h(){$(document).on("click",function(a){if("face_panel"!==a.target.id)$("#face_panel").hide();else{var g=$(c)[0];g.focus()}})}var y=[{title:"[微笑]",pic:g+"1.png"},{title:"[不]",pic:g+"2.png"},{title:"[亲亲]",pic:g+"3.png"},{title:"[无聊]",pic:g+"4.png"},{title:"[鼓掌]",pic:g+"5.png"},{title:"[伤心]",pic:g+"6.png"},{title:"[害羞]",pic:g+"7.png"},{title:"[闭嘴]",pic:g+"8.png"},{title:"[耍酷]",pic:g+"9.png"},{title:"[无语]",pic:g+"10.png"},{title:"[发怒]",pic:g+"11.png"},{title:"[惊讶]",pic:g+"12.png"},{title:"[委屈]",pic:g+"13.png"},{title:"[酷]",pic:g+"14.png"},{title:"[汗]",pic:g+"15.png"},{title:"[闪]",pic:g+"16.png"},{title:"[放屁]",pic:g+"17.png"},{title:"[洗澡]",pic:g+"18.png"},{title:"[偶耶]",pic:g+"19.png"},{title:"[困]",pic:g+"20.png"},{title:"[咒骂]",pic:g+"21.png"},{title:"[疑问]",pic:g+"22.png"},{title:"[晕]",pic:g+"23.png"},{title:"[衰]",pic:g+"24.png"},{title:"[装鬼]",pic:g+"25.png"},{title:"[受伤]",pic:g+"26.png"},{title:"[再见]",pic:g+"27.png"},{title:"[抠鼻]",pic:g+"28.png"},{title:"[心寒]",pic:g+"29.png"},{title:"[怒]",pic:g+"30.png"},{title:"[凄凉]",pic:g+"31.png"},{title:"[悄悄]",pic:g+"32.png"},{title:"[奋斗]",pic:g+"33.png"},{title:"[哭]",pic:g+"34.png"},{title:"[赞]",pic:g+"35.png"},{title:"[开心]",pic:g+"36.png"}];a.stopPropagation();({left:a.pageX-10,top:a.pageY-300});if($("#face_panel").size()>0)$("#face_panel").fadeIn(function(){h()});else{var _=$(c)[0];$("#layer_sendmsg").css("position","relative"),$('<div id="face_panel" style="display:block;left:-70px;bottom:50px; z-index:99999999; margin:0"><div id="choose_face"></div></div>').appendTo($("#layer_sendmsg"));for(var i=0;i<y.length;i++){var I=y[i].title.substr(1).replace("]","");$('<a title="'+y[i].title+'"  href="javascript:;"><img class="ph_face_s" src='+y[i].pic+"><p>"+I+"</p></a>").appendTo($("#choose_face")).on("click",function(a){a.stopPropagation(),v($(this).attr("title")),$("#face_panel").hide()})}h()}}function c(a){if(document.selection){b.focus();var c=document.selection.createRange();c.text=a,c.collapse(),c.select()}else if(b.selectionStart||"0"==b.selectionStart){var g=b.selectionStart,$=b.selectionEnd;b.value=b.value.substring(0,g)+a+b.value.substring($,b.value.length),b.selectionStart=b.selectionEnd=g+a.length}else b.value+=a}function g(a,c){if(""==$("#textInput").val())return void alert("请输入内容");var g=$("#textInput").val();$.ajax({type:"post",url:a,dataType:"json",data:{uid:c,message:g},success:function(a){layer.close(k),k="",layer.msg(a.data.msg,1,1)}})}{var v,h,y,_,I=document,j=window,w=!0,C=!1;require("./studyplan/modifystudyplan.js")}$(function(){$(j).height()>172&&$(I).on("scroll",function(){if(w){var a=244,c=$(".slider"),g=$(".wrap").offset().left,v=($(".wrap").offset().top,$(j).scrollTop());v>a?c.css("position","fixed").css("left",g+24+"px").css("top","20px"):c.css("position","absolute").css("left","24px").css("top","0")}}),$(window).resize(function(){if($(window).height()>172)w=!0;else{w=!1;var a=$(".slider");a.css("position","absolute").css("left","24px").css("top","0")}}),$(".signicon").hover(function(){$(this).find("span").show()},function(){$(this).find("span").hide()}),$(".already-follow").hover(function(){$(this).text("取消关注")},function(){$(this).html('<i class="imv2-check"></i>已关注')}),$(".js-add-to-plan").on("click",function(){var a=$(this),c=a.data("type"),g=a.data("tid");if(scheduleId&&scheduleId>0){if(C)return;$.ajax({beforeSend:function(){C=!0},url:"/u/add_course?schId="+scheduleId+"&type="+c+"&typeId="+g,type:"get",dataType:"json",timeout:2e3,complete:function(){C=!1}}).done(function(c){0===c.result&&(a.text("已加入课表"),a.attr("disabled",!0)),$.prompt(c.msg)})}else window.location.replace("/u/study_plan")})}),require("/static/lib/layer/1.6.0/layer.min.js");var k="",b=$("#textInput")[0];$("#face_panel").find("a").each(function(){$(this).click(function(){$("#face_panel").hide(),c("["+$(this).attr("title")+"]")})}),$(".chatSend").click(function(e){return e.preventDefault(),g("/u/ajaxsendmessage",$("#js-send-msg").data("uid")),!1}),$(".sendInvite").click(function(e){return e.preventDefault(),g("/u/ajaxaddfriend",$("#js-add-frd").data("uid")),!1}),$("#main").delegate("#js-add-frd, #js-send-msg","click",function(){function a(){require.async("login_sns",function(a){a.init()})}OP_CONFIG.userInfo?($("#textInput").val(""),k=$.layer({type:1,area:["js-send-msg"==$(this).attr("id")?"580px":"422px","auto"],title:!1,move:[".layer_notice",!0],border:!1,page:{dom:"#layer_sendmsg"}}),$(".xubox_main").addClass("pravtie_close")):a()}),$(".tipclose").click(function(){$(".addfriendtip").css("display","none")}),$(".suclose").click(function(){$(".successtip").css("display","none")}),$(I).delegate("#sendEmojiIcon","click",function(e){var c=$("#textInput");return c.focus(),$("#face_panel").length>0?$("#face_panel").toggle():a(e,c,"/static/img/smiley/"),!1}),y=function(){_()},_=function(){$(I).on("mouseenter",".user-pic .friend",v.iconHandlerIn),$(I).on("mouseleave",".user-pic .friend",v.iconHandlerOut),$(I).on("click",".js-u-add-follow",v.followIconClick),$(I).on("click",".js-u-already-follow",v.cancelFollowClick),$(I).on("click",".more-user-info",function(){v.showUserInfo()})};var O=0;v={iconHandlerIn:function(){var a=$(this),c=a.find(".u-info-tips");c.show()},iconHandlerOut:function(){var a=$(this),c=a.find(".u-info-tips");c.hide()},followIconClick:function(){{var a=$(this),c=a.data("type"),g=a.data("uid"),v=a.parent(),y=$(".js-u-already-follow"),_=$(".js-fans-msg");v.data("is-fans")}O||(OP_CONFIG.userInfo?(O=1,1==c&&h({url:"/u/"+OP_CONFIG.userInfo.uid+"/ajaxfollows",uid:g,type:1},function(){a.addClass("hide"),_.removeClass("hide"),y.removeClass("hide"),usersInfoCache&&usersInfoCache[g]&&(usersInfoCache[g].is_follows=1)})):require.async("login_sns",function(a){a.init()}))},cancelFollowClick:function(){var a=$(this),c=a.data("uid"),g=$(".js-fans-msg"),v=$(".js-u-add-follow");h({url:"/u/"+OP_CONFIG.userInfo.uid+"/ajaxfollows",uid:c,type:2},function(){a.addClass("hide"),g.addClass("hide"),v.removeClass("hide"),usersInfoCache&&usersInfoCache[c]&&(usersInfoCache[c].is_follows=0)})},showUserInfo:function(){$(this);$(".user-sign").hasClass("hide")?($(".user-sign").removeClass("hide"),$(".user-info").css("paddingBottom","48px")):($(".user-sign").addClass("hide"),$(".user-info").css("paddingBottom","0"))}},window.onload=function(){$.ajax({type:"post",url:"//coding.imooc.com/class/ajaxbigdatacourserecommend",dataType:"json",success:function(a){if(a.data){var c=a.data;$.each(c,function(a,c){var g;if(g=1==c.easy_type?"入门":2==c.easy_type?"初级":3==c.easy_type?"中级":4==c.easy_type?"高级":"专业",!(a>3)){var v='<li>                            <a href="//coding.imooc.com/class/'+c.id+'.html" class="recommend-item">                                <div class="img l">                                    <img width="216" height="120" src='+c.pic_url+' />                                </div>                                <div class="content">                                    <p class="title">'+c.name+'</p>                                    <div class="info">                                        <span>¥ '+c.price+"</span>                                        <span>"+g+'</span>                                        <span class="imv2-set-sns"></span>                                        <span>'+c.numbers+"</span>                                    </div>                                </div>                            </a>                        </li>";$("#js-load-recommentcourse").append(v)}})}else $("#js-load-recommentcourse").append("暂无数据")}})},h=function(a,c){var g={ok:{v:1101,_msg:"关注成功"},error:{v:1100,_msg:"关注失败"}},v={ok:{v:1201,_msg:"取消成功"},error:{v:1200,_msg:"取消失败"}},h={v:1103,_msg:"已经是好友了"};$.ajax({type:"post",url:a.url,dataType:"json",data:{uid:a.uid,type:a.type},success:function(a){a&&a.result&&(a.result==g.ok.v?c&&c():a.result==g.error.v?layer.msg(a.msg?a.msg:g.error._msg,1,1):a.result==v.ok.v?c&&c():a.result==v.error.v?layer.msg(a.msg?a.msg:v.error._msg,1,1):a.result==h.v?layer.msg(a.msg?a.msg:h._msg,1,1):layer.msg(a.msg,1,1))},error:function(){layer.msg("网络错误，请稍后再试",1,1)},complete:function(){O=0}})},y()});