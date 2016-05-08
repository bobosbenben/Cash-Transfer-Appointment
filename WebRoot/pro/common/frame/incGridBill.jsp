<%@ page language="java"  pageEncoding="UTF-8"%>
<!--css: -->
<link rel="stylesheet" type="text/css" href="./pro/plugins/awesome/css/font-awesome.css?ver=V1.0" />
<script type="text/javascript">
document.write("<link rel='stylesheet' type='text/css' href='./pro/plugins/extjs/resources/css/${pageCss}.css?ver=V1.0'"); 
</script>
<!--class components:  -->
<script id="microloader" type="text/javascript" src="./pro/plugins/extjs/bootstrap.js"></script>
<script type="text/javascript" src="./pro/plugins/extjs/ext-locale-zh_CN.js?ver=V1.0"></script>

<!--class tools:  -->
<script src="./pro/common/js/util.js?ver=V1.0"></script>
<script src="./pro/common/js/base64.js?ver=V1.0"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/app/ux/ExtUtil.js"></script>
<!-- 取消后退键 -->
<script type="text/javascript">
	var detailRole ="";
	<%--var contextPath = <%=request.getContextPath()%>;--%>
	if(!isEmpty(Sys.firefox) || !isEmpty(Sys.opera))
		document.onkeypress=dokey;
	else
		document.onkeydown=dokey;
	function dokey(e){
		 var ev = e || window.event;//获取event对象
		 var obj = ev.target || ev.srcElement;//获取事件源
		 var t = obj.type || obj.getAttribute('type');//获取事件源类型
		 if(ev.keyCode == 8 ){
			if(t == "password" || t == "text" || t == "textarea" ){
			   if(obj.readOnly !=true)
				 return true
			}
			 ev.returnvalue=false;
			return false;
		  }
	}
</script>