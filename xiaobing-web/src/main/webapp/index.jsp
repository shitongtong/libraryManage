<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>柠檬支付</title>
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="css/font.css" rel='stylesheet' type='text/css' />
<script src="js/jquery.min.js"> </script>
</head>
<div id="home" class="header">
	<div class="top-header">
		<div class="container">
			<div class="logo"><a href="index.html">
			  <h1>NM<span>Payer</span></h1>
			  </a></div>
			<div class="top-menu">
			<span class="menu"></span>
			<ul class="cl-effect-16">
			<li><a class="active" href="index.html" data-hover="首页">首页</a></li>
			<li><a  href="down.html" data-hover="sdk下载">sdk下载</a></li>
			<li><a href="html5.html" data-hover="电脑端支付">电脑端支付</a></li>
			<li><a href="http://www.kancloud.cn/liunian17/nmpayer/247691" target="_blank" data-hover="接入文档">接入文档</a></li>
			<!--<li><a href="about.html" data-hover="关于我们">关于我们</a></li>-->
			<!-- script-for-menu -->
			<script>
				$("span.menu").click(function(){
					$(".top-menu ul").slideToggle("slow" , function(){
							});});
			</script>
			
			<!-- script-for-menu -->
			<%
				String uid = (String)request.getSession().getAttribute("userId");
				String name = (String)request.getSession().getAttribute("name");
				if(uid!=null&&name!=null)
				{
			%>
			 <li><a href="/app/balance">我的控制台</a></li>
			 <%
			   }
			 	else
			 	{
			 %>
			 <div class="login-register">
                 <a class="" href="./login.jsp">登录</a>
                 <a class="" href="./register.jsp">注册</a>
            </div>
            <%}%>
            
	      </div>
			
			
			
			<div class="clearfix"></div>
			</div>
			
			<div class="section1 section1-min-hidden">
    
        <h1 class="text-center">柠檬支付致力于解决个人和公司接入支付难的问题</h1>
        <p class="text-center">支付接入不再为各种证书和材料烦恼</p>
    </div>
	
	           
</div>
		</div>
	</div>
</div>

<div class="container section2">
  <div class="jiesao">支付场景介绍</div>
<div class="row index-features-intro-col">
    <div class="col-md-6 col-sm-6">
      <div class="col-md-4 col-sm-4 col-xs-4">
        <img src="./images/yen36.png" class="center-block" width="80">
      </div>
      <div class="col-md-8 col-sm-8 col-xs-8 index-features-intro-col-content">
        <h2>手机支付</h2>
        <p>移动支付为iOS/Android原生/H5 App提供全套支付解决方案： 支持微信支付、支付宝支付、银联手机支付、快捷wap支付等</p>
      </div>
    </div>
    <div class="col-md-6 col-sm-6">
      <div class="col-md-4 col-sm-4 col-xs-4">
        <img src="./images/laptop118.png" class="center-block" width="80">
      </div>
      <div class="col-md-8 col-sm-8 col-xs-8 index-features-intro-col-content">
        <h2>PC电脑支付</h2>
        <p>电脑支付为PC网页提供全套支付解决方案： 支持支付宝网页支付、支付宝扫码支付、 微信扫码支付、网银网页支付等。 </p>
      </div>
    </div>
  </div>
  <div class="row index-features-intro-col">
    <div class="col-md-6">
      <div class="col-md-4 col-sm-4 col-xs-4">
        <img src="./images/yen40.png" class="center-block" width="80">
      </div>
      <div class="col-md-8 col-sm-8 col-xs-8 index-features-intro-col-content">
        <h2>账户间支付</h2>
        <p>账户间支付为用户只需要输入对方已注册的用户ID或邮箱即可完成支付流程，方便、快捷、高效。
          <p>
          </div>
        </div>
        <div class="col-md-6">
          <div class="col-md-4 col-sm-4 col-xs-4">
            <img src="./images/yens6.png" class="center-block" width="80">
          </div>
          <div class="col-md-8 col-sm-4 col-xs-8 index-features-intro-col-content">
            <h2>缴费支付业务</h2>
            <p>缴费业务为手机固话费充值、游戏缴费充值，宽带业务缴费、水电煤缴费，用户可无需东奔西跑、只需要输入缴费账号等即可快速完成缴费。</p>
          </div>
        </div>
      </div>
    </div>

</div>


<div class="section3">
     <div class="container">
	    <div class="jiesao">他们正在使用柠檬支付</div>
        <div class="banxin exhibition">
            <ul>
                <li>
                        <img src="http://assets2016.bmob.cn/css/images/th1.png" alt=""/>
                        <span>短工邦</span>
                </li>
                <li>
                        <img src="http://assets2016.bmob.cn/css/images/th2.png" alt=""/>
                        <span>PKball</span>
                </li>
                <li>
                        <img src="http://assets2016.bmob.cn/css/images/th3.png" alt=""/>
                        <span>酷签</span>
                </li>
                <li>
                        <img src="http://assets2016.bmob.cn/css/images/th4.png" alt=""/>
                        <span>别踩白块儿</span>
                </li>
                <li>
                        <img src="http://assets2016.bmob.cn/css/images/th5.png" alt=""/>
                        <span>礼品帮</span>
                </li>
                <li>
                        <img src="http://assets2016.bmob.cn/css/images/th6.png" alt=""/>
                        <span>EasyTouch</span>
                </li>
                <li>
                        <img src="http://assets2016.bmob.cn/css/images/th7.png" alt=""/>
                        <span>Circle圈子</span>
                </li>
                <li>
                        <img src="http://assets2016.bmob.cn/css/images/th8.png" alt=""/>
                        <span>奇妙的拼音之旅</span>
                </li>
            </ul>
        </div>
	 
	 </div>
      

</div>

<body>
</body>
</html>
