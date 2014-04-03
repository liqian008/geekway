<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.WxArticle"%>

<%@ include file="../inc/include_tag.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
<title>Pannonia - premium responsive admin template by Eugene Kopyov</title>
<link href="../css/bootstrap.css" rel="stylesheet" type="text/css" />
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<!--[if IE 8]><link href="css/ie8.css" rel="stylesheet" type="text/css" /><![endif]-->
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700' rel='stylesheet' type='text/css'>

<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>

<script type="text/javascript"src="../js/plugins/charts/jquery.sparkline.min.js"></script> 

<script type="text/javascript"src="../js/plugins/ui/jquery.easytabs.min.js"></script>
<script type="text/javascript"src="../js/plugins/ui/jquery.collapsible.min.js"></script>
<script type="text/javascript"src="../js/plugins/ui/jquery.mousewheel.js"></script>

<script type="text/javascript"src="../js/plugins/forms/jquery.uniform.min.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.autosize.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.inputlimiter.min.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.tagsinput.min.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.inputmask.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.select2.min.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.listbox.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.validation.js"></script>
<script type="text/javascript"src="../js/plugins/forms/jquery.validationEngine.zh-CN.js"></script>

<script type="text/javascript"src="../js/globalize/globalize.js"></script>
<script type="text/javascript"src="../js/globalize/globalize.culture.de-DE.js"></script>
<script type="text/javascript"src="../js/globalize/globalize.culture.ja-JP.js"></script>


<!-- <script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="../js/functions/wysiwyg.js"></script> -->

<script type="text/javascript"src="../js/files/bootstrap.min.js"></script>

<script type="text/javascript"src="../js/functions/forms.js"></script>

<!-- <script type="text/javascript">
$(function()
	{CKEDITOR.replace('editor');
});
</script> -->

</head>

<body>

	<jsp:include page="../inc/header.jsp"></jsp:include>

	<!-- Content container -->
	<div id="container">

		<jsp:include page="../inc/leftSidebar.jsp"></jsp:include>
		
		<!-- Content -->
		<div id="content">

			<!-- Content wrapper -->
		    <div class="wrapper">

	            <!-- Form validation -->
	            <h5 class="widget-name"><i class="icon-th-list"></i>文章管理</h5>
				
				<form id="validate" action="<s:url value='./saveUser'/>" method="post"  class="form-horizontal">
	                <fieldset>
		                    	<!-- Form validation -->
				                <div class="widget">
									<div class="navbar"><div class="navbar-inner"><h6>文章详细资料</h6></div></div>
			                    	<div class="well row-fluid">
			                    		
	                                    <div class="alert margin">xxxxx</div>
	                            		
			                            <div class="control-group">
			                                <label class="control-label">标 题: <span class="text-error">*</span></label>
			                                <div class="controls">
			                                    <input type="text" class="span4" name="title" id="title" value="${article.title}"/> 
			                                </div>
			                            </div>
			                            
			                            
			                            <div class="form-actions align-left">
			                                <button type="submit" class="btn btn-info">修 改</button>
			                                <button type="reset" class="btn">重 置</button>
			                                <!-- <button type="button" class="btn btn-danger">冻结设计师</button> -->
			                            </div>
			                        </div>
			                        
								</div>
								
								<!-- /Form validation -->
	                </fieldset>
				</form>
				<!-- /form validation -->
				
		    </div>
		    <!-- /content wrapper -->

		</div>
		<!-- /content -->

	</div>
	<!-- /content container -->

	<jsp:include page="../inc/footer.jsp"></jsp:include>

</body>
</html>