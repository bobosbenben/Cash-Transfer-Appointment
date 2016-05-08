<%@ page language="java"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String realPath = "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+request.getServletPath().substring(0,request.getServletPath().lastIndexOf("/")+1); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">

	<jsp:include page="/pro/common/frame/incGridBill.jsp"/>
	<script type="text/javascript"
			src="<%=realPath%>/menuMgr.js?ver=V1.0"></script> 
			
	<script>
	var actionUrl =  "sys/menu";
	var pageNo    = "SYSMENU";
	var detailRole = "${detailRole}";
	</script>
	<style type="text/css">
	 <%-- 解决extjs6.0 toolbar 里buttontext 中文出...号的问题   --%>
	  .x-btn-inner-default-toolbar-small{
	  font:bold 12px/16px helvetica, arial, verdana, sans-serif;color:#666;padding:0 5px;max-width:100%; overflow:visible}
	 </style>
  </head>
  
  <body>
  
  </body>
</html>
