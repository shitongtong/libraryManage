<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016-09-27
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
    <title>上传用户头像</title>
    <link href="${pageContext.request.contextPath}/js/cutImg/css/master.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/js/cutImg/css/upload.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/js/cutImg/css/jquery.rollbar.css" rel="stylesheet" />
    <link href="${pageContext.request.contextPath}/js/cutImg/css/jquery.Jcrop.css" rel="stylesheet" />
</head>
<body>
<div class="user_upload">
    <form id="uploadImgFrom" enctype="multipart/form-data" method="post">
        <div class="upload">
            <div class="upload_left">
                <div class="pic">
                    <div class="pic2" id="pic2" >
                        <div id="uploadFile">
                            <div class="upload_btn" style="margin-left: 75px;">
                                <a href="javascript:;" id="upText" style="color:red;">上传头像</a>
                            </div>
                            <p>
                                支持jpg、png格式<br />
                                图片小于2M
                            </p>
                        </div>
                        <!--回显示图片-->
                        <img src="" name="photo" id="originalImg" alt=""/>
                    </div>
                </div>
                <p class="up_reload" id="up_reload" style="display: none;">
                    <a href="javascript:;" style="color: red">重新上传</a>
                </p>
                <input type="hidden" name="picval" id="picval" />
                <input type="hidden" name="origpicval" id="origpicval" value="" />
                <input type="hidden" name="temporigpic" id="temporigpic" />
                <input type="hidden" name="fileID" id="fileID" size="100" />
                <input type="hidden" name="fileUrl" id="fileUrl" size="100" />
                <input type="hidden" name="imgHeight" id="imgHeight" size="100" />
                <input type="hidden" name="imgWidth" id="imgWidth" size="100" />

            </div>
            <div class="upload_right">
                <div class="upload_view_pic" style="width: 300px; height: 230px; border: solid 1px #ccc; overflow: hidden;">
                    <img id="preview_1" src="${pageContext.request.contextPath}/images/4-3.jpg"/>
                </div>
                <p>
                    400 x 300像素
                </p>
                <%--<div class="upload_view_pic" style="width: 80px; height: 80px; border: solid 1px #ccc; overflow: hidden;">
                  <img id="preview_2" src=${pageContext.request.contextPath}/images/4-3.jpg />
                </div>
                <p>
                  80 x 80像素
                </p>--%>
                <p>
                    拖拽或缩放，生成自己满意的头像
                </p>
            </div>
            <%-- <div id="img_description">
              <textarea
            </div>--%>
            <div class="upload_b">
                <font id="upalert"></font>
            </div>
        </div>
        <input type="hidden" name="x" id="x" size="5" />
        <input type="hidden" name="y" id="y" size="5" />
        <input type="hidden" name="w" id="w" size="5" />
        <input type="hidden" name="h" id="h" size="5" />
        <input type="hidden" name="imgUrl" id="imgUrl"/>
        <input type="hidden" name="imgId" id="imgId"/>
        <div class="upload_submit_contain">
            <div class="upload_submit" id="saveImage">
                <a href="javascript:;" style="color: red">保存头像</a>
            </div>
        </div>
    </form>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/check.js"></script>
<script src="${pageContext.request.contextPath}/js/cutImg/js/jquery-1.7.1.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/cutImg/js/jquery.form.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/cutImg/js/jquery-custom-file-input.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/cutImg/js/jquery.Jcrop.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/cutImg/js/jquery.rollbar.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/cutImg/js/upload.ui.photos.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/dialog/dialog.js?lib=false"></script>
<script type="text/javascript">
    photos.JcorpWidth = 400;//裁剪选择框宽度
    photos.JcorpHeight = 300;//裁剪框选择高度
    //  photos.Widths = ["200", "80"];// 图片大小“宽”数组，根据图片张数由大到小定义不同图片的宽度
    //  photos.Heights = ["200", "80"];// 图片大小“高”数组，根据图片张数由大到小定义不同图片的高度
    photos.UpLoad_FileID = "uploadFile";//上传组件属性ID
    photos.UpLoad_FileTextID = "upText";//上传组件按钮文本ID
    photos.UpLoad_AlertID = "upalert";//提示文本ID
    photos.UpLoad_AlertClassName = "error";//提示文本增加的样式
    photos.Upload_FormID = "uploadImgFrom";//上传组件表单ID
    photos.UpLoad_OriginFileID = "originalImg";//底图图片ID
    photos.UpLoad_ReLoadID = "up_reload";//重新上传组件ID
    photos.UpLoad_PicValueID = "picval";//上传的底图片内容ID
    photos.UpLoad_PicFileID = "fileID";////上传图片id
    photos.UpLoad_PicFileUrl = "fileUrl";////上传图片url
    photos.UpLoad_PicImgHeight="imgHeight";//上传的底图高
    photos.UpLoad_PicImgWidth="imgWidth";//上传的底图宽

    photos.UpLoad_TempOriginValueID = "temporigpic";//本页面重新上传时的原图片内容ID
    photos.UpLoad_OriginValueID = "origpicval";//其他页面进入时的原图片内容ID
    photos.Upload_RollbarID = "pic2";//自定义滚动条ID
    photos.UpLoad_SaveID = "saveImage";//图片保存按钮ID
    photos.UpLoad_PreviewPreFix = "preview_";//多个不同尺寸小图的ID前缀名称，多个小图ID格式“前缀”+数字编号（从1开始），例：id="photos_preview1" ...
    photos.ParentFileImgID = "imgUrl";//父级图片控件ID
    photos.ParentFileValueID = "imgId";//父级图片地址控件ID
    photos.XID = "x";//x
    photos.YID = "y";//y
    photos.WID = "w";//w
    photos.HID = "h";//h
    photos.UpText_Format = "抱歉，暂只支持 jpg、png、bmp 格式";//图片格式验证文本
    /* photos.UpText_Size = "抱歉，图片大小不能超过2M";*///图片大小验证文本
    /*photos.UpText_Error = "图片上传出错";//上传出错文本*/
    photos.Url_Check = "/Enterprise/filescheck";//文件验证大小的地址
    photos.Url_UpLoad = "/Enterprise/uploadphoto?up=original";//上传图片载入底图的地址
    photos.Url_ReLoad = "";//重新上传的地址
    photos.Url_Save = "/Enterprise/saveimg?up=save";//保存图片的地址
</script>
</html>
