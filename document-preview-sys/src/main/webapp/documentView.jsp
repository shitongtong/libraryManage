<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016-09-06
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%--<%
    String swfFilePath=session.getAttribute("swfpath").toString();
    System.out.println("swfFilePath==="+swfFilePath);
    String rootPath =application.getRealPath("/");
%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <style type="text/css" media="screen">
        html, body  { height:100%; }
        body { margin:0; padding:0; overflow:auto; }
        #flashContent { display:none; }
    </style>

    <link rel="stylesheet" type="text/css" href="css/flexpaper.css" />
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/flexpaper.js"></script>
    <script type="text/javascript" src="js/flexpaper_handlers.js"></script>

    <title>文档在线预览系统</title>
<body>
    <div id="documentViewer" class="flexpaper_viewer" ></div>

    <script type="text/javascript">

       $('#documentViewer').FlexPaperViewer(
                {config: {
                    <%--SWFFile: escape('<%=swfFilePath%>'),--%>
                    <%--SwfFile: '<%=swfFilePath%>',--%>
//                    SwfFile: 'http://image.bblink.cn/201603-cf8bdbcc80254501b2ef52c4c0df773f.swf',
                    SwfFile: '/upload/准妈妈孕早期注意事项.swf',
                    Scale: 0.6,
                    ZoomTransition: 'easeOut',
                    ZoomTime: 0.5,
                    ZoomInterval: 0.2,
                    FitPageOnLoad: true,
                    FitWidthOnLoad: true,
                    FullScreenAsMaxWindow: false,
                    ProgressiveLoading: false,
                    MinZoomSize: 0.2,
                    MaxZoomSize: 5,
                    SearchMatchAll: false,
                    InitViewMode: 'Portrait',
                    RenderingOrder: 'flash',
                    StartAtPage: '',

                    ViewModeToolsVisible: true,
                    ZoomToolsVisible: true,
                    NavToolsVisible: true,
                    CursorToolsVisible: true,
                    SearchToolsVisible: true,
                    WMode: 'window',
                    localeChain: 'zh_CN'
                    <%--,--%>
                    <%--jsDirectory: "<%=rootPath%>/js/",--%>
                    <%--cssDirectory: "<%=rootPath%>/css/"--%>
                }}
        );
    </script>
</body>
</html>
