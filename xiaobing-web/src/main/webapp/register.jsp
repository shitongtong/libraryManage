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
<title>注册</title>
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

<div class="bgcolor">
   <div class="container">
       <div class="panel panel-default regist">
	      <div >
		      <form class="form-horizontal" action="/regist" method="post">
			     <div class="form-group">
				    <h2 class="text-center">注册账户 
						 </h2>
                   
						 
						
              </div>
				 </div>
				 
				  <div class="form-group">
                <div class="row">
                  <div class="col-md-4 col-md-offset-4">

                   <input class="form-control" id="username" placeholder="请输入用户名" name="username">
                   <!-- <div class="message-tip">邮箱地址错误</div> -->
                  </div>
                </div>
              </div>
             <div class="form-group">
                <div class="row">
                  <div class="col-md-4 col-md-offset-4">

                   <input class="form-control" id="password" placeholder="请输入密码" name="password">
                   <!-- <div class="message-tip">邮箱地址错误</div> -->
                  </div>
                </div>
              </div>
				 
				 <div class="form-group" style="margin-top:50px;">
                 <div class="row">
                  <div class="col-md-4 col-md-offset-4">
                   <input type="submit" class="btn btn-info btn-block"  value="发送"  />
                  </div>
                </div>
               </div>

                <div class="form-group">
                <p class="text-center">已有账号&nbsp;?&nbsp;<a href="./login.jsp">立即登录</a></p>
              </div>
			  
			  </form>
		  
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
   
   </div>
   
   
 
   
</div>

</body>





</html>
