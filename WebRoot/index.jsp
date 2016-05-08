<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML>
<html>
<head>
<script type="text/javascript"
	src="./pro/plugins/jquery/jquery-1.11.3.min.js">
	
</script>
<script type="text/javascript">
$(document).ready(function(){
	var ua = navigator.userAgent, isMobile = /Mobile(\/|\s)/.test(ua);//获取浏览器类型
	$("input:hidden").attr("value",isMobile);//是移动端则隐藏起来
	$("form").submit();
});
	</script>
</head>

<body>
	<form action="./index" method="post" id="page">
		<input type="hidden" name="isMobile">
	</form>
</body>

</html>
