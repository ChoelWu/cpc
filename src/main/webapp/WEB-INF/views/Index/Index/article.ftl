<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="format-detection" content="telephone=no"><!-- 禁止IPHONE识别手机号码 -->
<meta name="viewport" content="width=device-width,user-scalable=no, initial-scale=1">
<title>卓越中学化学教师实验教学示范中心</title>
<!-- css -->
<link rel="stylesheet" type="text/css" href="/static/index/index/css/index.css">
<link rel="stylesheet" type="text/css" href="/static/index/index/css/aos.css">
<link rel="stylesheet" type="text/css" href="/static/index/index/css/style.css"> 
<!-- js -->
<script type="text/javascript" src="/static/index/index/js/jquery1.42.min.js"></script>
<script type="text/javascript" src="/static/index/index/js/jquery.SuperSlide.2.1.3.js"></script>
</head>

<body>	

<!-- 头部 -->
<div class="head_k">
	<div class="head_dw">
		<div class="head head_style">
			<div>
				<div class="shangb">
					<div class="logo"><a href="../index.html"><img src="/static/index/index/images/logo.png"></a></div>
				</div>
				<div class="dh">
					<ul>
						<#list navList as nav>
							<li>
								<a href="<#if nav.indexChannel.channelType='3'>${nav.indexChannel.channelUrl}<#else>/index/index/cate.do?no=${nav.indexChannel.channelNo}</#if>"><p>${nav.indexChannel.channelName?substring(0,2)}</p><p>${nav.indexChannel.channelName?substring(2,4)}</p></a>
								<#list nav.childChannelList as childNav>
									<dl><dd><a href="<#if childNav.channelType='3'>${childNav.channelUrl}<#else>/index/index/cate.do?no=${childNav.channelNo}</#if>">${childNav.channelName}</a></dd></dl>
								</#list>
							</li>
						</#list>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div class="zy_banner"><img src="/static/index/index/images/banner_gy.jpg"></div>
</div>
<script type="text/javascript">
$(document).ready(function(){
$(".head .dh ul li").hover(function(){
    $(this).find("dl").slideToggle(200);
    // $(this).find("dl").stop().slideToggle(200);
  });
});
</script> 
 

<!-- 子页内容 -->
<div class="px1200">
	<div class="ziye">
		<div class="left" aos="fade-up-right">
			<h3 class="biaot">${leftChannel.indexChannel.channelName}</h3>
			<ul>
				<#list leftChannel.childChannelList as channel>
					<li><a href="<#if channel.channelType='3'>${channel.channelUrl}<#else>/index/index/cate.do?no=${channel.channelNo}</#if>">${channel.channelName}</a></li>
				</#list>
			</ul>
		</div>
		<div class="right" aos="fade-up-left" style="min-height: 600px;">
			 <div class="right_biaot">
			 	<i></i>
			 	<h2>${indexChannel.channelName}</h2>
			 	<i class="right"></i>
			 </div>
			 <!-- 详情内容 -->
			 <div class="jianj_k">
				 <h2 style="text-align: center;padding-bottom: 30px;color: #666;">${indexArticle.articleName}</h2>
				 <span style="color: #666; display: block; font-size: 1em; line-height: 24px; border-bottom: 1px solid #CCC; padding-bottom: 5px; text-align: center; width: 100%;">[来源：${indexArticle.articleSource}&nbsp;&nbsp;&nbsp;&nbsp;发布时间：${indexArticle.publishTime?string('yyyy-MM-dd')}]</span>
				 <div style="padding-top: 20px;">${indexArticle.articleContent}</div>
			 </div>
			 <!-- 详情内容介绍 -->
		</div>
	</div>
</div>

<!-- footer -->
<div class="footerBeiJ"  aos="fade-up">
	<div class="px1200">
		<div class="ditu_t">
			<ul>
				<li><a href="http://chem.nwnu.edu.cn/2012/0420/c373a20203/page.htm"><p><img src="/static/index/index/images/quan.jpg"></p><span>权正军教授</span></a></li>
				<li><a href="http://chem.nwnu.edu.cn/2012/0420/c373a20191/page.htm"><p><img src="/static/index/index/images/yzw.jpg"></p><span>杨志旺教授</span></a></li>
				<li><a href="http://chem.nwnu.edu.cn/2012/0420/c373a20242/page.htm"><p><img src="/static/index/index/images/xzh.jpg"></p><span>薛中华教授</span></a></li>
				<li><a href="http://chem.nwnu.edu.cn/2019/0116/c373a103357/page.htm"><p><img src="/static/index/index/images/mhc.jpg"></p><span>马恒昌教授</span></a></li>
			</ul>
		</div>
	</div>
	<div class="footerZd">
		<div class="px1200">
			<div class="footerLogo"><img src="/static/index/index/images/logoFooter.png"></div>
			<div class="lianxDiz">
				<p>0931-7972626</p>
				<p class="p1">quanzj@nwnu.edu.cn</p>
				<p class="p2">西北师范大学新校区</p>
			</div>
		</div>
		<div class="beian">
			<div class="px1200">
				<p>西北师范大学-化工学院  Copyright © 2019-2030 All Rights Reserved    　陇ICP备17000462号-1        技术支持: Venus</p>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript"> 
$(document).ready(function(){

$(".footerZd .yq").hover(function(){
    $(".xl").slideToggle();
});

}); 
</script>


</body>
</html>
<script type="text/javascript" src="/static/index/index/js/jquery.swfobject.min.js"></script>
<script type="text/javascript" src="/static/index/index/js/highlight.min.js"></script>
<script type="text/javascript" src="/static/index/index/js/aos.js"></script>
<script type="text/javascript" src="/static/index/index/js/index.js"></script>

	<script>
		AOS.init({
			easing: 'ease-out-back',
			duration: 1000
		});
	</script>

	<script>
		hljs.initHighlightingOnLoad();

		$('.hero__scroll').click(function(e) {
			$('html, body').animate({
				scrollTop: $(window).height()
			}, 1200);
		});
	</script>