<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%> <!-- 如果web.xml中配置了就不需要这个了，这里为了演示用 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>上传成功页面</title>
</head>
<body>
剪裁后的图：<img src="<%=request.getContextPath()%>/${imgCut}"/>
<hr/>
原图：<img src="<%=request.getContextPath()%>/${imgSrc}"/>
</body>
</html>