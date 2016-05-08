<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>systemerror</title>
    <STYLE type=text/css>BODY {
	FONT-SIZE: 9pt; COLOR: #842b00; LINE-HEIGHT: 16pt; FONT-FAMILY: "Tahoma", "宋体"; TEXT-DECORATION: none;
}
TABLE {
	FONT-SIZE: 9pt; COLOR: #842b00; LINE-HEIGHT: 16pt; FONT-FAMILY: "Tahoma", "宋体"; TEXT-DECORATION: none;
}
TD {
	FONT-SIZE: 9pt; COLOR: #842b00; LINE-HEIGHT: 16pt; FONT-FAMILY: "Tahoma", "宋体"; TEXT-DECORATION: none
}
BODY {
	SCROLLBAR-HIGHLIGHT-COLOR: buttonface; SCROLLBAR-SHADOW-COLOR: buttonface; SCROLLBAR-3DLIGHT-COLOR: buttonhighlight; SCROLLBAR-TRACK-COLOR: #eeeeee; BACKGROUND-COLOR: #ffffff
}
A {
	FONT-SIZE: 9pt; COLOR: #842b00; LINE-HEIGHT: 16pt; FONT-FAMILY: "Tahoma", "宋体"; TEXT-DECORATION: none
}
A:hover {
	FONT-SIZE: 9pt; COLOR: #0188d2; LINE-HEIGHT: 16pt; FONT-FAMILY: "Tahoma", "宋体"; TEXT-DECORATION: underline
}
H1 {
	FONT-SIZE: 9pt; FONT-FAMILY: "Tahoma", "宋体"
}
</STYLE>
</head>
<BODY topMargin=20>
<div align="center">
<TABLE >
  <TBODY>
  <TR>
    <TD vAlign=top align="center"><IMG height=100 src="<%=request.getContextPath()%>/pro/common/img/404.jpg" 
      width=100 border=0> 
    <TD>
    <TD><!--------System Return Begin------------>
      <H1><font color="#666666">无法完成相应请求</font></H1>
      <font color="#666666">HTTP 错误 404：您没有相应的权限获取搜索的页面。 
      </font> 
      <HR noShade SIZE=0>

      <P><font color="#666666">请尝试以下操作：</font></P>
      <UL>
       
        <LI><font color="#666666">联系系统管理员增加相应的权限。 
        </font> 
         
      </UL>
      <P>
      <HR noShade SIZE=0>

     <font color="#C0C0C0"> 
      <BR>
      &nbsp;&nbsp;&nbsp;</font><BR>
      <!------------End this!---------------></TD></TR></TBODY></TABLE>
</div>

</body>
</html>