<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			src="<%=realPath%>/hrMgr.js?ver=V2.0"></script> 
	<script>
	var actionUrl =  "basis/hr";
	var pageNo    = "HR";
	var detailRole = "${detailRole}";
	var listResignationType = [];
	var listPosts = [];
	var listDiploma = [];
	<c:forEach  items="${listpost}"  var="of">
	    listPosts.push("{ 'value' : '${of.data}' , 'name' : '${of.data}'}");
	</c:forEach>
	<c:forEach  items="${listResignationType}"  var="of">
	     listResignationType.push("{ value : '${of.data}' , name : '${of.data}'}");
    </c:forEach>
    <c:forEach  items="${listDiploma}"  var="of">
       listDiploma.push("{ value : '${of.data}' , name : '${of.data}'}");
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
