<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016-09-28
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery.Jcrop.js"></script>
    <script src="js/jquery-form.js"></script>
    <link rel="stylesheet" href="css/jquery.Jcrop.css" type="text/css" />
    <style type="text/css">
        /* 控制预览区域大小*/
        #preview-pane .preview-container {
            width: 110px;
            height: 110px;
            overflow: hidden;
        }
        #targetDiv{
            width: 400px;
            height: 400px;
            background-color:#f7fdff;
        }
    </style>
    <title>Title</title>
</head>
<body>
<dl class="dialogBox D_uploadLogo">
    <dt class="dialogHeader">
        <span class="title">头像上传</span>
    </dt>

    <dd class="dialogBody">
        <dl class="bisinessLogo">
            <dt class="title">预览</dt>
            <dd class="img">
                <div id="preview-pane">
                    <div class="preview-container">
                        <img src="" id="target2" class="jcrop-preview" alt="未选择图片" />
                    </div>
                </div>
            </dd>
            <dd class="tc">尺寸：110*110px</dd>
        </dl>
        <dl class="bisinessInfo">
            <dt class="btnBox02">
            <form id="fileUp" action="/file/img/upload" method="post" enctype="multipart/form-data" target="ifm">
                <a class="btnGray" href="javascript:;">
                    <span class="text" id="format">选择图片</span>
                    <b class="bgR"></b>
                    <input type="file" id="file_upload" class="inputFile" name="userphoto"/>
                    <input type="hidden" id="w" name="w"/>
                    <input type="hidden" id="h" name="h"/>
                    <input type="hidden" id="x" name="x"/>
                    <input type="hidden" id="y" name="y"/>
                </a>
            </form>
            </dt>
            <dd class="info">

                请从本地选择一张照片，支持jpg,png格式    <span id="msg"></span>
                <div id="targetDiv">
                    <img src="" id="target" width="400" height="400" alt="未选择图片"/>
                </div>
            </dd>
        </dl>
    </dd>
    <input type="hidden" id="filePathInput" value=""/>

    <dd class="dialogBottom">
        <a class="btnBlue btn_confirm" href="javascript:;" onclick="photoSummit();"><span class="text">确定</span><b class="bgR"></b></a>
        <a class="btnGray btn_cancel" href="javascript:;" onclick="hideDialog();"><span class="text">取消</span><b class="bgR"></b></a>
    </dd>
</dl>
<script>
    $("#fileUp").ajaxSubmit({
                type: "POST",
                url:"/file/img/upload",
                dataType: "json",
                contentType:"application/json",
                success: function(parameter){
                    $("#target2").attr('src','/upload/'+parameter.fileName);
                    $("#filePathInput").val('/upload/'+parameter.fileName);
                    if($("#format").text()=="重新上传"){
                        jcrop_api.destroy()
                    }
                    $("#format").text("重新上传");
                    //启动jcrop支持
                    openJcrop('/upload/'+parameter.fileName);
                },
                error : function(data) {
                    alert("ajax传输发生错误！！！");
                }
            });
</script>
</body>
</html>
