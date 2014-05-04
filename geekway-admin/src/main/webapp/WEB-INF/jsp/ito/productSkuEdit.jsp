<%@page import="java.util.Map.Entry"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.bruce.geekway.model.*"%>
<%@page import="com.bruce.geekway.utils.*"%>

<%@ include file="../inc/include_tag.jsp" %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ITO管理平台</title>
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/londinium-theme.min.css" rel="stylesheet"
	type="text/css">
<link href="${pageContext.request.contextPath}/css/styles.min.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/icons.min.css" rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&amp;subset=latin,cyrillic-ext"
	rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/1.10.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/1.10.2/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/charts/sparkline.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/uniform.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/select2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/inputmask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/autosize.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/inputlimit.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/listbox.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/multiselect.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/tags.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/forms/switch.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/forms/uploader/plupload.full.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/forms/uploader/plupload.queue.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/interface/daterangepicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/interface/fancybox.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/prettify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/moment.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/jgrowl.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/interface/datatables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/plugins/interface/colorpicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/interface/fullcalendar.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/interface/timepicker.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/plugins/interface/collapsible.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/application.js"></script>
</head>
<body class="sidebar-wide">

	<jsp:include page="../inc/header.jsp"></jsp:include>

	<!-- Page container -->
	<div class="page-container">

		<jsp:include page="../inc/leftSidebar.jsp"></jsp:include>

		<!-- Page content -->
		<div class="page-content">
			<!-- Page header -->
			<div class="page-header">
				<div class="page-title">
					<h3>
						商品SKU数据
					</h3>
				</div>
			</div>
			<!-- /page header -->
			<!-- Breadcrumbs line -->
			<div class="breadcrumb-line">
				<ul class="breadcrumb">
					<li><a href="javascript:void(0)">首页</a></li>
					<li class="active">商品SKU数据</li>
				</ul>
				<div class="visible-xs breadcrumb-toggle">
					<a class="btn btn-link btn-lg btn-icon" data-toggle="collapse"
						data-target=".breadcrumb-buttons"><i class="icon-menu2"></i></a>
				</div>
			</div>
			<!-- /breadcrumbs line -->
			
			<div class="callout callout-info fade in">
				<button type="button" class="close" data-dismiss="alert">×</button>
				<h5>功能介绍</h5>
				<p>
					1、xxxxxxxxxx<br/>
				</p>
			</div>

			<%
			ItoProduct product = (ItoProduct)request.getAttribute("product");
			%>

			<form id="validate" action="<s:url value='./saveProductSku'/>" method="post"  class="form-horizontal form-bordered">

				<!-- Basic inputs -->
				<div class="panel panel-default">
					<div class="panel-heading">
						<h6 class="panel-title">
							<i class="icon-bubble4"></i>填写商品SKU数据
						</h6>
					</div>
					<div class="panel-body">
						
						<div class="form-group">
							<label class="col-sm-2 control-label text-right">商品信息: <span class="mandatory">*</span></label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="title" id="title" value="${product.title}" readonly="readonly"/>
								<input type="hidden" name="productId" id="productId" value="${product.id}" readonly="readonly"/>
							</div>
						</div>
						
						<%
						/* HashMap<Integer, List<ItoSkuPropValue>> skuGroupMap = (HashMap<Integer, List<ItoSkuPropValue>>)request.getAttribute("skuGroupMap");
						
						List<ItoSkuPropValue> sizeSkuList = new ArrayList<ItoSkuPropValue>();
						List<ItoSkuPropValue> colorSkuList = new ArrayList<ItoSkuPropValue>();

						if(skuGroupMap!=null&&skuGroupMap.size()==2){
							sizeSkuList = skuGroupMap.get(1);
							colorSkuList = skuGroupMap.get(2);
						}

						List<String> skuCombineTitleList = new ArrayList<String>();
						List<String> skuCombineValueList = new ArrayList<String>();
						
						for(ItoSkuPropValue sizeSkuPropValue: sizeSkuList){
							for(ItoSkuPropValue colorSkuPropValue: colorSkuList){
								skuCombineTitleList.add(sizeSkuPropValue.getName()+"+"+colorSkuPropValue.getName());
								skuCombineValueList.add(sizeSkuPropValue.getId()+"_"+colorSkuPropValue.getId());
							}
						} */
						
						List<String> skuCombineLabelList = (List<String>)request.getAttribute("skuCombineLabelList");
						List<String> skuCombineValueList = (List<String>)request.getAttribute("skuCombineValueList");
						List<String> skuCodeList = (List<String>)request.getAttribute("skuCodeList");
						
						
						
						for(int i=0;i<skuCombineLabelList.size();i++){
							String skuCombineTitle = skuCombineLabelList.get(i);
							String skuCombineValue = skuCombineValueList.get(i);
							String skuCode = skuCodeList.get(i);
						%>
						<div class="form-group has-error sku-info">
							<label class="col-sm-2 control-label text-right">SKU(<%=skuCombineTitle%>): <span class="mandatory">*</span>
							</label>
							
							<input type="hidden" class="form-control" name="skuName_<%=skuCombineValue%>" value="<%=skuCombineTitle%>">
							<input type="hidden" class="form-control" name="skuCombines" value="<%=skuCombineValue%>">
							<input type="hidden" class="form-control" name="skuCode_<%=skuCombineValue%>" value="<%=skuCode%>">
							
							<div class="col-sm-3">
								<div class="input-group">
									<span class="input-group-btn">
										<button class="btn btn-default" type="button">售价(元)</button>
									</span>
									<input type="text" class="form-control" name="skuPrice_<%=skuCombineValue%>">
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="input-group">
									<span class="input-group-btn">
										<button class="btn btn-default" type="button">数量(个)</button>
									</span>
									<input type="text" class="form-control" name="skuQuality_<%=skuCombineValue%>">
								</div>
							</div>
							
							<div class="col-sm-3">
								<div class="input-group">
									<span class="input-group-btn">
										<button class="btn btn-default" type="button">SN</button>
									</span>
									<input type="text" class="form-control" name="skuSn_<%=skuCombineValue%>">
								</div>
							</div>
						</div>
						<%}%>
						
						<!-- <div class="form-group">
							<label class="col-sm-2 control-label text-right">状 态: <span class="mandatory">*</span>
							</label>
							<div class="col-sm-4">
								<form:select path="product.status" class="select-liquid">
									<form:option value="0"  label="下架"/>
									<form:option value="1"  label="上架"/>
								</form:select>
							</div>
						</div> -->
						
						
						<div class="form-actions text-right">
							<input type="reset" value="重 置" class="btn btn-danger">
							<input type="submit" value="提 交" class="btn btn-primary">
						</div>
					</div>
					
					
					
				</div>
				
			</form>

			<jsp:include page="../inc/footer.jsp"></jsp:include>

		</div>
		<!-- /page content -->
	</div>
	<!-- /page container -->
	
	<script type="text/javascript">
		//checkbox操作时
		$('.sku-prop').click(function(){
			//删除原dom
			$('.sku-info').remove();
			//创建新dom
			
			
		})
	
	</script>
	
</body>
</html>
