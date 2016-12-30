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
  
  <title>财务管理</title>

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
                        <li class="active"><a href="/app/balance">财务记录</a></li>
                        <li><a href="/app/payRecord">订单记录</a></li>
                        <li ><a href="/app/finance">提款管理</a></li>                     
                    </ul>
                </li>

                <li class="menu-list"><a href=""><i class="fa fa-cogs"></i> <span>支付配置</span></a>

                    <ul class="sub-menu-list">
                        <li><a href="/app/appManager"> 应用ID管理</a></li>
                        <li><a href="/app/qualification"> 支付通知url</a></li>
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
             	   <div class="col-md-4">
                    <!--more statistics box start-->
                    
                    <div class="panel deep-purple-box">
                        <div class="panel-body">
                            <div class="row state-overvie">
                                <div class="symboll">
                                    <i class="fa fa-gavel"></i>
                                </div>
                                <div class="state-valuel">
                                    <div class="value">${month} 元</div>
                                    <div class="title">当月收入</div>
                                </div>
                            </div>
                        
                    </div>
                    </div>
                    <!--more statistics box end-->
                </div>
                <div class="col-md-8">
                    <!--statistics start-->
                    <div class="row state-overview">
                        <div class="col-md-6 col-xs-12 col-sm-6">
                            <div class="panel purple">
                                <div class="symbol">
                                    <i class="fa fa-gavel"></i>
                                </div>
                                <div class="state-value">
                                    <div class="value">230 元</div>
                                    <div class="title">今日收入</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xs-12 col-sm-6">
                            <div class="panel red">
                                <div class="symbol">
                                    <i class="fa fa-tags"></i>
                                </div>
                                <div class="state-value">
                                    <div class="value">190 元</div>
                                    <div class="title">今日支付宝收入</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row state-overview">
                        <div class="col-md-6 col-xs-12 col-sm-6">
                            <div class="panel blue">
                                <div class="symbol">
                                    <i class="fa fa-money"></i>
                                </div>
                                <div class="state-value">
                                    <div class="value">22014 元</div>
                                    <div class="title">昨日收入</div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-xs-12 col-sm-6">
                            <div class="panel green">
                                <div class="symbol">
                                    <i class="fa fa-eye"></i>
                                </div>
                                <div class="state-value">
                                    <div class="value">390 元</div>
                                    <div class="title">今日微信收入</div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--statistics end-->
                </div>

            </div>
            <div class="row">
            	 <div class="col-md-12">
                    <div class="panel">
                    	   <div style="margin: 19px 80px;" class="btn-group">
                                <button class="btn btn-success" type="button">一星期</button>
                                <button class="btn btn-info" type="button">一个月</button>
                                <button class="btn btn-danger" type="button">三个月</button>
                            </div>
                        <div class="panel-body">
                              <div class="chart-body" id="payChart" style="height: 350px;">
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

<script src="http://assets2016.bmob.cn/js/plugins/echarts/echarts-all.js?45"></script>


<!--common scripts for all pages-->
<script src="js/scripts.js"></script>

</body>

<script>

var myChart = echarts.init(document.getElementById('payChart'));

// 指定图表的配置项和数据
var option = {

tooltip: {
trigger: 'axis'
},
xAxis: {
data: ["2016/11/10","2016/11/11","2016/11/12","2016/11/13","2016/11/14","2016/11/15"]
},
yAxis: {},
series: [{
name: '收入',
smooth: true,
type: 'line',
symbol:'emptyCircle',
symbolSize:[5,5],
itemStyle:
{
normal:
{
lineStyle:
{
color:'#6eaaee',
width:2
}
}
},
data: [438, 425, 442, 597, 651, 535]
}]


};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);




</script>
</html>
