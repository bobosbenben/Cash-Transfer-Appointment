/**
 * 
 * ClassName:登陆页面处理 内容:1.输入框内容为空 2.form表单重置 3.增加cookie保存登陆用户名
 * 
 */

$(document).ready(function() {
	initGsdm();
	/**
	 * event:回车事件处理
	 */
	$(document).on("keypress", "#gsDm", function(e) {
				if (e.keyCode == 13) {
					e.preventDefault();
					$("#loginName").focus();
				}
			})
	$(document).on("keypress", "#loginName", function(e) {
				if (e.keyCode == 13) {
					e.preventDefault();
					$("#password").focus();
				}
			})
	$(document).on("keypress", "#password", function(e) {
				if (e.keyCode == 13) {
					e.preventDefault();
					//$("#yzm").focus();
					$("#buttonLogin").focus();
				}
			})
	$("#buttLogin").click(function() {
				if (check()) {
					$("#buttLogin").attr("disabled", "disabled");
					$("#buttReset").attr("disabled", "disabled");
					$("#loginForm").attr("action", action);//在jsp中定义了：var action='login'
					$("#loginForm").submit();
					return true;
				} else {
					return false;
				}
			})

	$("#buttReset").click(function() {
				reset();
				return false;
			})
});
function initGsdm() {
	var sname = GetMyCookie("cookie_userName");
	if (sname.length >= 1) {
		//document.getElementById("loginName").value = sname;
		//document.getElementById('password').focus();
	} else {
		//document.getElementById('loginName').focus();
	}
};

function validMachineCode() {
	
	//try {
	//	var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
	//	if ((LODOP == null) || (typeof(LODOP.VERSION) == "undefined")) {
	//		document.getElementById("loginName").value = "";
	//		document.getElementById("loginName").disabled = "disabled";
	//		$("#buttLogin").attr("disabled", "disabled");
	//	} else {
	//		var bios = LODOP.GET_SYSTEM_INFO('BaseBoard.SerialNumber');
	//		var diskDrive = LODOP.GET_SYSTEM_INFO('DiskDrive.1.SerialNumber');
	//		var serial = LODOP.GET_SYSTEM_INFO('DiskDrive.1.SerialNumber');
	//		var machinecode = bios.MD5() + diskDrive.MD5() + serial.MD5();
	//		document.getElementById("hardware").value = machinecode;
	//	}
	//} catch (err) {
	//	document.getElementById("loginName").value = "";
	//	document.getElementById("loginName").disabled = "disabled";
	//	$("#buttLogin").attr("disabled", "disabled");
	//	alert("Error:本机未安装或需要升级!");
	//}

};

// 重置表格
function reset() {
	document.forms['loginForm'].reset();
	document.getElementById("loginName").focus();
};
/**
 * 表单提交前检查
 */
function check() {
	if ($("#loginName").val() == null || $("#loginName").val() == "") {
		alert("请输入你的登陆名！");
		$("#loginName").focus();
		return false;
	} else if ($("#password").val() == null || $("#password").val() == "") {
		alert("请输入你的密码！");
		$("#password").focus();
		return false;
	}
	//else if ($("#yzm").val() == null || $("#yzm").val() == "") {
		//alert("请输入验证码！");
		//"#yzm").focus();
	//}
	else {
		SetMyCookie("cookie_userName",
				document.getElementById("loginName").value);
		return true;
	}
};
//获得Cookie的原始值
function GetMyCookie(name) {
    var arg = name + "=";
    var alen = arg.length;
    var clen = document.cookie.length;
    var I = 0;
    while (I < clen)
    {
        var j = I + alen;
        if (document.cookie.substring(I, j) == arg)
            return GookieVal(j);
        I = document.cookie.indexOf(" ", I) + 1;
        if (I == 0) break;
    }
    return 0;
};


//设定Cookie值
function SetMyCookie(name, value) {
	if(value==0||value.length==0)
		return;
    var agv = SetMyCookie.arguments;
    var argc = SetMyCookie.arguments.length;
    var expires = (argc > 2) ? argv[2] : null;
    var path = (argc > 3) ? argv[3] : null;
    var domain = (argc > 4) ? argv[4] : null;
    var secure = (argc > 5) ? argv[5] : false;
    document.cookie = name + "=" + escape(value) + ((expires == null) ?"" : ("; expires=" + expires.toGMTString())) + ((path == null) ? "" :("; path=" + path)) + ((domain == null) ? "": ("; domain=" + domain)) + ((secure == true) ? "; secure" : "");
};

//获得Cookie解码后的值
function GookieVal(offset) {
    var endstr = document.cookie.indexOf(";", offset);
    if (endstr == -1)
        endstr = document.cookie.length;
    return unescape(document.cookie.substring(offset, endstr));
};