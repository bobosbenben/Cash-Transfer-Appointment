package en.common.frame.engine;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import en.common.util.helper.Base64;
import en.common.util.helper.DatetimeUtil;
import en.common.util.helper.MD5;
import en.common.util.helper.MachineUtil;
import en.common.util.helper.StringUtil;

public class AppEngine {

	private static AppEngine inst;
	
	Base64 scape = new Base64();
	
//	private String localString = "";

	public StringBuffer checkValid(HttpSession session) throws Exception {
		
		ResourceBundle  resources = ResourceBundle.getBundle("system", Locale.getDefault());
		if(null == resources)
			throw new Exception("无效的配置文件");
		
//		String liscence = resources.getString("liscence");
//		String maxclient =scape.decode64(resources.getString("maxclient"));
//		String maxdate = scape.decode64(resources.getString("maxdate"));
//		List<?> online = (List<?>) session.getAttribute("onlineUserList");
//		//在线用户 控制
//				if (online != null) {
//					if (online.size() > (Integer.parseInt(maxclient) + 1))
//						throw new Exception("在线用户数已到达软件允许用户数");
//				}
//		localString = MachineUtil.getCpuId() + "&&"
//						+ MachineUtil.getMotherboardSN() + "&&" + MachineUtil.getMac()
//						+ "&&" + maxclient + "&&" + maxdate;
//		String mac = new MD5().getMD5ofStr(localString);
//		System.out.println("check liscence:" + localString+"|"+mac+"|"+liscence);
		
//		if (mac.equals(liscence)) {
//			if (DatetimeUtil.getCurrentDateString().compareTo(maxdate) > 0)
//				throw new Exception("注册文件有效期已到，请联系软件供应商");
//		}else
//			throw new Exception("注册文件失效，请联系软件供应商");
		/***************获取配置信息******************/
		String sys_name = scape.decode64(resources.getString("system.name"));
		String sys_edition = scape.decode64(resources.getString("system.edition"));
		String sys_version = scape.decode64(resources.getString("system.version"));
		String sys_iconUrl = resources.getString("system.iconurl");
		sys_iconUrl = StringUtil.isEmpty(sys_iconUrl) ? "" :  scape.decode64(sys_iconUrl);
		StringBuffer re = new StringBuffer(); 
		/***************组装系统信息******************/
		re.append("system:").append("{").append("sys_name:'").append(sys_name).append("',sys_edition:'").append(sys_edition).append("',sys_version:'").append(sys_version).append("',sys_iconUrl:'").append(sys_iconUrl).append("'}");
		String service_company = scape.decode64(resources.getString("service.company"));
		String service_tel = scape.decode64(resources.getString("service.tel"));
		String service_qq = scape.decode64(resources.getString("service.qq"));
		String service_email= scape.decode64(resources.getString("service.email"));
		String service_copyright= scape.decode64(resources.getString("service.copyright"));
		re.append(",service:").append("{").append("service_company:'").append(service_company).append("',service_tel:'").append(service_tel)
		.append("',service_qq:'").append(service_qq).append("',service_email:'").append(service_email).append("',service_copyright:'").append(service_copyright).append("'}");
	  return re;	
	}
	public static AppEngine getInstance() {
		if (inst == null) {
			inst = new AppEngine();
		}
		return inst;
	}
}
