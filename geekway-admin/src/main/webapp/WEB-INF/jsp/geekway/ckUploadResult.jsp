<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page import="com.bruce.geekway.model.upload.UploadImageResult" %>
<%
String callback = (String)request.getAttribute("callback");
UploadImageResult imageUploadResult = (UploadImageResult)request.getAttribute("imageUploadResult");
if(imageUploadResult!=null&&imageUploadResult.getOriginalImage()!=null){
%>
<script type="text/javascript">
	window.parent.CKEDITOR.tools.callFunction("<%=callback%>",'<%=imageUploadResult.getOriginalImage().getUrl()%>','');    
</script>  
<%}%>