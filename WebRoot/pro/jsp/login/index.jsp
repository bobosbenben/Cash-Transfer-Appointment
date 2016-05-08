<%@ page language="java" pageEncoding="utf-8"%>
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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title>大额预约管理系统</title>
<base href="<%=basePath%>">

<jsp:include page="IncIndex.jsp" />
        <script type="text/javascript"
			src="<%=realPath%>index.js?ver=V1.0">
		</script>
		<script>
	    	var action = 'login';
        </script>
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px;
	COLOR: #ffffff;
	FONT-FAMILY: 宋体;
	text-align: center;
}

TABLE {
	FONT-SIZE: 12px;
	border: 0px;
}

TD {
	FONT-SIZE: 12px;
	COLOR: #ffffff;
	FONT-FAMILY: 宋体
}

.loginmaintable {
	width: 815px;
	margin: auto;
	border: 0px;
}

#loginFoot {
	BACKGROUND: #ddd;
	TEXT-ALIGN: center;
	FONT: 14px
}

#copyright {
	MARGIN: 0px auto;
	FONT: 0.7em/300% Verdana;
}

</STYLE>
</head>
<body leftMargin=0 topMargin=0 marginheight="0" marginwidth="0">
	<table height="100%" width="100%" border="0">
		<tbody>
			<tr>
				<td align="center" valign="middle">



					<TABLE class="loginmaintable">
						<tbody>
							<tr>
								<td align="center" valign="middle">

									<TABLE class="loginmaintable">
										<TBODY>
											<!-----title图片结束------->
											<!-----center图片结束------->
											<TR>
												<TD background="./pro/common/img/bg_login_center.png"
													height=414>
													<TABLE>
														<TBODY>
															<TR>
																<TD style="width:80%;"  rowSpan=2></TD>
																<TD vAlign=top>
																	<form name=loginForm id=loginForm style="WIDTH: 260px"
																		action="" method="post"><!--由js动态载入地址-->
																		<TABLE>
																			<TBODY>
																				<TR>
																					<TD><LABEL>公司代码:</LABEL></TD>
																					<TD><INPUT id=gsDm style="width:110px" size=12
																						name=gsDm value='00001'>
																					</TD>
																				</TR>
																				<tr>
																					<td><br></td>
																				</tr>
																				<TR>
																					<TD><LABEL> 用 户 名: </LABEL></TD>
																					<TD class="loginTextTd"><INPUT id="hardware"
																						type="hidden" name="hardware" /> <INPUT
																						id=loginName tabIndex=2 style="width:110px"
																						maxLength=12 name=loginName></TD>
																				</TR>
																				<tr>
																					<td><br></td>
																				</tr>
																				<TR>
																					<TD><LABEL> 密&nbsp;&nbsp;码: </LABEL></TD>
																					<TD class="loginTextTd"><INPUT id=password
																						tabIndex=2 style="width:110px" maxLength=12
																						type="password" name=password></TD>
																				</TR>
																				<tr>
																					<td><br></td>
																				</tr>
																				<TR>
																					<TD><LABEL> 验 证 码: </LABEL></TD>
																					<TD class="loginTextTd"><INPUT id=yzm
																						style="WIDTH: 40px" tabIndex=4 maxLength=4
																						name=yzm
																						onkeyup="this.value=this.value.replace(/\D/g,'')"
																						>
																						<IMG height=20
																						src="./pro/common/jsp/captchaImageCommon.jsp"
																						width=57 align="top" border=1></TD>
																				</TR>
																				<tr>
																					<td><input type="hidden" name="isMobile" value="${isMobile}" /> </td>
																				</tr>
																				<tr>
																					<td align="center"><INPUT id=buttLogin
																						style="BORDER-RIGHT: 0px; BORDER-TOP: 0px; BORDER-LEFT: 0px; BORDER-BOTTOM: 0px; "
																						tabIndex=4 type=image
																						src="./pro/common/img/btn_login_submit.jpg">
																					</td>
																					<td align="center"><INPUT id=buttReset
																						style="BORDER-RIGHT: 0px; BORDER-TOP: 0px; BORDER-LEFT: 0px; BORDER-BOTTOM: 0px; "
																						tabIndex=4 type=image
																						src="./pro/common/img/btn_login_rest.jpg">
																					</td>
																				</tr>
																			</TBODY>
																		</TABLE>
																	</form></TD>
															</TR>
														</TBODY>
													</TABLE></TD>
											</TR>
											<!-----center图片结束------->
										</TBODY>
									</TABLE></td>
							</tr>
						</tbody>
					</TABLE></td>
			</tr>
		</tbody>
	</table>
</body>
<!-----显示错误信息------->
<SCRIPT type="text/javascript">
var ec =  "${errorCode}";
    if(ec.length >0){
        alert(ec);
    }
window.onload = validMachineCode;
</SCRIPT>
<script type="text/javascript"
	src="./pro/common/js/delHrefCommon.js?ver=V1.0">
</script>

</html>