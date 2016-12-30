<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <base href="<%=basePath%>">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="ThemeBucket">
  <link rel="shortcut icon" href="#" type="image/png">

  <title>订单记录</title>

  <link href="css/style.css" rel="stylesheet">
  <link href="css/style-responsive.css" rel="stylesheet">

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="js/html5shiv.js"></script>
  <script src="js/respond.min.js"></script>
  <![endif]-->
</head>

<body class="sticky-header">

<section>
    <!-- left side start-->
    <div class="left-side sticky-left-side">

        <!--logo and iconic logo start-->
        <div class="logo">
            <a href="index.html"><img src="images/logo.png" alt=""></a>
        </div>

        <div class="logo-icon text-center">
            <a href="index.html"><img src="images/logo_icon.png" alt=""></a>
        </div>
        <!--logo and iconic logo end-->


        <div class="left-side-inner">

            <!-- visible to small devices only -->
            <div class="visible-xs hidden-sm hidden-md hidden-lg">
                <div class="media logged-user">
                    <img alt="" src="images/photos/user-avatar.png" class="media-object">
                    <div class="media-body">
                        <h4><a href="#">${name}</a></h4>
                        <span>"Hello There..."</span>
                    </div>
                </div>

                <h5 class="left-nav-title">Account Information</h5>
                <ul class="nav nav-pills nav-stacked custom-nav">
                    <li><a href="/logout"><i class="fa fa-sign-out"></i> <span>Sign Out</span></a></li>
                </ul>
            </div>

            <!--sidebar nav start-->
            <ul class="nav nav-pills nav-stacked custom-nav">
                <li><a href="/app/balance"><i class="fa fa-home"></i> <span>控制台首页</span></a></li>
                <li class="menu-list nav-active"><a href=""><i class="fa fa-laptop"></i> <span>财务管理</span></a>
                    <ul class="sub-menu-list">
                        <li ><a href="/app/balance">财务记录</a></li>
                        <li class="active"><a href="/app/payRecord">订单记录</a></li>
                        <li ><a href="/app/finance">提款管理</a></li>                       
                    </ul>
                </li>

                <li class="menu-list"><a href=""><i class="fa fa-cogs"></i> <span>支付配置</span></a>

                    <ul class="sub-menu-list">
                        <li><a href="/app/appManager"> 应用ID管理</a></li>
                        <li><a href="/app/urlManager"> 支付通知url</a></li>
                    </ul>

                </li>

                <li><a href="/app/message"><i class="fa fa-bullhorn"></i> <span>消息通知</span></a></li>
                
                <li><a href="/app/qualification"><i class="fa fa-envelope"></i> <span>实名认证</span></a></li>

               

            </ul>
            <!--sidebar nav end-->

        </div>
    </div>
    <!-- left side end-->
    
    <!-- main content start-->
    <div class="main-content" >

        <!-- header section start-->
        <div class="header-section">
        <!--toggle button start-->

        <div class="menu-right">
            <ul class="notification-menu">
              
                <li>
                    <a href="#" class="btn btn-default dropdown-toggle" data-toggle="dropdown">

                       ${name}
                        <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-usermenu pull-right">

                        <li><a href="/logout"><i class="fa fa-sign-out"></i> Log Out</a></li>
                    </ul>
                </li>

            </ul>
        </div>
        <!--notification menu end -->

        </div>


        <!--body wrapper start-->
        <div class="wrapper">
            <div class="row">
            	 <div class="col-md-12">
                    <div class="panel">
                    	   <div class="panel-body">
                                  <div class="row" style="margin: 10px;">
                                  	<form class="form-inline" role="form">
                                  		<select class="form-control">
                                        <option value ="volvo">最近一周</option>
                                        <option value ="saab">最近一个月</option>
                                        <option value="opel">最近三个月</option>
                                       </select class="form-control">
                                       <select class="form-control">
                                        <option value ="volvo">全部渠道</option>
                                        <option value ="saab">微信支付</option>
                                        <option value="opel">支付宝</option>
                                       </select class="form-control">
                                       <select class="form-control">
                                        <option value ="volvo">全部</option>
                                        <option value ="saab">支付成功</option>
                                        <option value="opel">未支付</option>
                                       </select class="form-control">
                                        <select class="form-control">
                                        <option value ="volvo">搜索项</option>
                                        <option value ="saab">交易流水号</option>
                                        <option value="opel">商品名称</option>
                                        <option value="opel">商品描述</option>
                                       </select>
                            <div class="form-group">
                                <label class="sr-only" for="exampleInputEmail2">搜索项</label>
                                <input class="form-control" id="exampleInputEmail2" placeholder="搜索项">
                            </div>
                          
                         
                            <button type="submit" class="btn btn-info">查询</button>
                        </form>
                                  	
                                  	
                                  	 
                                  </div>
                                  
                           <div class="row" style="margin: 10px;">
                                  	<table class="display table table-bordered table-striped"">
                                             <thead>
               
                                                 <td>交易单号</td>
                                                  <td>交易时间</td>
                                                  <td>系统平台</td>
                                                  <td>商品名称</td>
                                                  <td>商品描述</td>
                                                  <td>订单金额(元)</td>
                                                  <td>实收金额(元)</td>
                                                  <td>订单状态</td>
                                                  <td>交易渠道</td>
                                                  <thead>
                                                  <tbody>
                                                  	 <c:forEach var="payRecord" items="${payRecordList}">  
                                                  	  <tr class="gradeX">
                                                     <td>${payRecord.order_id}</td>
                                                     <td>${payRecord.pay_time}</td>
                                                     <td>${payRecord.pingtai} </td>
                                                     <td>${payRecord.name} </td>
                                                     <td>${payRecord.body}</td>
                                                     <td>${payRecord.money}</td>
                                                     <td>${payRecord.fact_money}</td>
                                                     <td>${payRecord.order_statue}</td>
                                                     <td>${payRecord.pay_qudao} </td>
                                                     </tr>
                                                    </c:forEach>  
                                                
                                                  </tbody>
                                    </table>
                           </div>
                           <div class="row" style="margin: 10px;">
                           	共有2868条，每页显示10条
                           	 <div style="float: right;">
                                        <ul class="pagination">
                                            <li><a href="#">«</a></li>
                                            <li><a href="#">1</a></li>
                                            <li class="active"><a href="#">2</a></li>
                                            <li><a href="#">3</a></li>
                                            <li><a href="#">4</a></li>
                                            <li><a href="#">5</a></li>
                                            <li><a href="#">»</a></li>
                                        </ul>
                                    </div>
                           </div>
                           	
                                  
                          </div>
                    </div>
                </div>
            	</div>
            

        </div>
        <!--body wrapper end-->

        <!--footer section start-->
        <footer class="sticky-footer">
            2016 &copy; NMPayer 
        </footer>
        <!--footer section end-->


    </div>
    <!-- main content end-->
</section>



<!-- Placed js at the end of the document so the pages load faster -->
<script src="js/jquery-1.10.2.min.js"></script>
<script src="js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="js/jquery-migrate-1.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/modernizr.min.js"></script>
<script src="js/jquery.nicescroll.js"></script>


<!--common scripts for all pages-->
<script src="js/scripts.js"></script>

</body>


</html>
