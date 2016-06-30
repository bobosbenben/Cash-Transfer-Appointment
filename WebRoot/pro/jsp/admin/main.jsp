<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String realPath = "http://"
		+ request.getServerName()
		+ ":"
		+ request.getServerPort()
		+ request.getContextPath()
		+ request.getServletPath().substring(0,
		request.getServletPath().lastIndexOf("/") + 1);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
     <title>大额预约管理系统</title>
    <base href="<%=basePath%>">
    <%--<title>My JSP 'index.jsp' starting page</title>--%>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="ERP">
	<meta http-equiv="description" content="现金、大额预约系统">
    <jsp:include page="/pro/common/frame/incGridBill.jsp"/>
    <script type="text/javascript"
			src="<%=realPath%>main.js"></script>
	<script>
	 var actionUrl =  "main";
	 var contextpath = "<%=path %>";
	 var listCompanys = [];
	 var listCompanysHsAndJT = [];
	 <c:forEach  items="${listCompanys}"  var="of">
	  listCompanys.push("{ value : '${of.gsDm}' , name : '${of.shortName}'}");
	</c:forEach>
	<c:forEach  items="${companyHsAndJTdata}"  var="of">
	    listCompanysHsAndJT.push("{ value : '${of.gsDm}' , name : '${of.shortName}'}");
	</c:forEach>
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
