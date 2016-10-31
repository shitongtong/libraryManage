<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">    
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    	
	<script src="/scripts/clearFile.js" type="text/javascript"></script>
	<script type="text/javascript">
	
		/**检查图片上传类型*/		
		 function checkImgType(obj){
		 
		  var imgFile = '';	
		  //获取图片的全路径
		  var imgFilePath = getImgFullPath(obj);	 
		  var endIndex = imgFilePath.lastIndexOf("\\");
		  var lastIndex = imgFilePath.length-endIndex-1;
		  if (endIndex != -1)
		     imgFile= imgFilePath.substr(endIndex+1,lastIndex);
		  else
		  	 imgFile = imgFilePath;		
		  	
		  var tag = true;	 	  	 
		  endIndex = imgFilePath.lastIndexOf(".");			 
		  if (endIndex == -1) 
		    tag = false;
		    
		  var ImgName = imgFilePath.substr(endIndex+1,lastIndex);
		  ImgName = ImgName.toUpperCase();		  
		 
		  if (ImgName !="GIF" && ImgName !="JPG" && ImgName !="PNG" && ImgName !="BMP"){ 
		      tag=false;
		  }
		  if (!tag) {
		      alert("上传图片的文件类型必须为: *.gif,*.jpg,*.png,*.bmp,请重新选择!")
		      Upload.clear(obj);			      	 
		 	  return false;
		  }   
		  document.form1.submit();
	  }
		
		function getImgFullPath(obj) {
		    if (obj) {  
		       //ie  
		       if (window.navigator.userAgent.indexOf("MSIE") >= 1) {  
		           obj.select();  
		           return document.selection.createRange().text;  
		       }  
		       //firefox  
		       else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {  
		           if (obj.files) {  
		               return obj.files.item(0).getAsDataURL();  
		           }  
		           return obj.value;  
		       }         
		       return obj.value;  
		   }  
		} 
	</script>
	
  </head>  
  <body>
    <form name=form1 method=post enctype="multipart/form-data" action="<%=path%>/imgUpload">
    	<div style="margin-left: 35%;margin-top: 20%">
    	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >    	   
          <tr>              
               <td >&nbsp;图片上传<font color='red'>*.gif,*.jpg,*.png,*.bmp</font></td>              
           </tr>  
           <tr>   
               <td width="80%">            	
		         <input type="file" name="imgFile" id="imgFile" maxlength="160" onchange="checkImgType(this);" width="300px"/>&nbsp;	                     
		       </td>    
           </tr>               
    	</table>  
       </div>
    </form>
  </body>
</html>
