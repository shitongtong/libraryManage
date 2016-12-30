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
<title>登录</title>

<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<link href="css/font.css" rel='stylesheet' type='text/css' />
<script src="js/jquery.min.js"> </script>
</head>

<body>

		<div class="container">
			<div class="logo2"><a href="index.html">
			  <h1>NM<span>Payer</span></h1>
			 </a></div>
        </div>
	 
	 <div class="login">
  <div class="container bg">

  <div class="panel-body">
<form id="loginForm"  action="/login" method="post">
<div class="form-group"><h3>账户登录</h3></div>
  <div class="form-group">
    <input class="form-control input-username"  id="inputUsername" name="username" 
    value="" placeholder="请输入用户名">
  </div>
  <div class="form-group">
     <input type="password" id="hiddenPwd" style="display:none" autocomplete="off" />
    <input type="password" class="form-control input-pw" id="inputPassword" name="password" placeholder="请输入密码">
  </div>
  
  <!--<div class="form-group">
       
          <div class="col-md-6 ">
            <input name="captcha" id="validateCode" type="text" size="20" autocomplete="off"  data-minlength="3" maxlength="6" placeholder="请输入验证码"  class="form-control validatePwd" placeholder="请输入验证码">
          </div>
          <div class="col-md-6 ">
          <input type="hidden" id="ctxPath" value="" />
              <img src="ValidateCodeServlet" alt="验证码" width="60" height="30" class="verificationCode"
              onClick="this.src='ValidateCodeServlet?'+Math.random();" />
          </div>

        
    </div>
 


<div class="form-group">
    <div id="errMsg" class="message-tip" style="display: none;">
	请输入验证码
	</div>
</div>-->


   <div class="form-group"><button type="submit" id="loginBtn" class="btn btn-info btn-block">登录</button></div>
    <div class="form-group">
    <label ><a href="./register.jsp" target="_blank">注册账户</a></label> 
    <label class="pull-right"><a href="#" target="_blank">忘记密码</a></label>
    
  </div>
</form>
  </div>
</div>


</div>

  <footer class="body-footer login-footer">
    <div class="container">
      <ul class="list-inline">
  <li><a href="#">关于我们</a></li>
   <li>|</li>
    <li><a href="#">联系我们</a></li>
     <li>|</li>
      <li><a href="#">加入我们</a></li>
       
</ul>
    <p> Copyright  2016-2017 nmpayer.com. All
    Rights Reserved. </p> </div>
  </footer>


</body>
</html>
