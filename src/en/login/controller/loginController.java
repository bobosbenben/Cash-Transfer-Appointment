package en.login.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import en.basis.service.ICompanyService;
import en.basis.service.ILoginUserService;
import en.basisc.entity.LoginUser;
import en.common.frame.controller.BaseController;
import en.common.frame.engine.AppEngine;
import en.common.frame.engine.IConstants;
import en.common.frame.shiro.SessionUser;
import en.common.util.helper.Base64;
import en.common.util.helper.ExtjsCssEnum;
import en.common.util.helper.StringUtil;
import en.dm.entity.Company;
import en.sys.service.IMenuService;

//通过新的@RestController指定在控制器上，这样就不需要在每个@RequestMapping方法上加 @ResponseBody了

@RestController
public class loginController extends BaseController {
	
	@Resource //J2EE专用，大概等同于@Autowired
	private ICompanyService companyService;
	@Resource
	private ILoginUserService loginUserService;
	@Resource
	private IMenuService menuService;
	
	@RequestMapping("/index")
	public ModelAndView getLoginPage(String isMobile, Model model) {
		model.addAttribute("isMobile", isMobile);
		if (StringUtil.isEmpty(isMobile))
			return new ModelAndView("/index");
		if ("true".equals(isMobile))
			return new ModelAndView("pro/jsp/login/mobile");
		else
			return new ModelAndView("pro/jsp/login/index");

	}

	/**
	 * login 登陆 controller
	 * 
	 * @param model
	 */
	@RequestMapping("/login")
	public ModelAndView login(String gsDm, String loginName, String password,
			String yzm, String hardware,String isMobile, Model model) {
		// 初始化错误信息
		errorCode = "";
		boolean isSuccess = false;
		HttpSession session = request.getSession(true);
		// 检测验证码
		String veriCode = (String) session.getAttribute("_RANDOM_LOGIN");
	
		if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(password))
			errorCode = "请提供有效的用户名及密码!";
		else {
//			if (StringUtil.isEmpty(veriCode))
//				errorCode = "请从首页重新登陆，输入完整用户、密码以及验证码!";
//			else{
//				if (!veriCode.equals(yzm))
//					errorCode = "验证码错误，请重新登陆!";
//				else{

					/**********处理其他业务**********/
					try { 
					/*************** 检测注册文件 ***************/
					StringBuffer sys_info =  AppEngine.getInstance().checkValid(session);
					/*********************** 检测登陆用户 **********************/
					UsernamePasswordToken token = new UsernamePasswordToken(
							loginName, new Base64().encode64(password));

					//直接从后台获取到用户的公司代码，通过用户名(login_account)在xt_loginuser表中获取
					LoginUser loginUser = loginUserService.getLoginUserByLoginName(loginName);
					gsDm = new String(loginUser.getGsDm());
					//因系统采用公司代码权限,故此处将公司代码存于host属性
					token.setHost(gsDm);
					token.setRememberMe(true);
					// 获取当前的Subject
					System.out.println("=============token=============="+token);
					Subject currentUser = SecurityUtils.getSubject();
					logger.info("对用户[" + loginName + "]进行登录验证..验证开始");
					currentUser.login(token);
					logger.info("对用户[" + loginName + "]进行登录验证..验证通过");
					if(currentUser.isAuthenticated()){
						/**
						 * 处理sessionUser
						 */
						LoginUser luser = loginUserService.getUserByLoginNamePassword(currentUser.getPrincipal().toString(), password, gsDm);
						//gsDm = luser.getGsDm();//这里就不去判断用户填写的机构是否正确，直接从后台取得用户的机构

						Company company =companyService.getGsxxByGsDm(luser.getGsDm(), gsDm);
						
						SessionUser sessionUser  = new SessionUser(session.getId(), luser, gsDm, company);
						
						//获取权限
						List<?> listdetailmenu =  loginUserService.getDetailMenus(luser, gsDm);
						
						/**************** 添加在线用户 *****************/
						//setUserInSession(sessionUser,currentUser);
						
						/************移出旧的sys_info,添加新的sys_info到shiro session***************/
						String user = "user :{ name:'"+luser.getName()+"',company :'"+company.getName()+"',department:''}";
						sys_info.append(",").append(user);
						currentUser.getSession().removeAttribute(IConstants.SESSION_SYS_INFO);
						currentUser.getSession().setAttribute(IConstants.SYSTEM+"_ISMOBILE", isMobile);
						
						//添加sessionuser
						currentUser.getSession().setAttribute(IConstants.SESSION_USER_KEY, sessionUser);
						//添加明细权限
						currentUser.getSession().setAttribute(IConstants.SESSION_USER_MENU, listdetailmenu);
						//获取shrio认证权限
						Collection<String> con = menuService.getShiroMenus(listdetailmenu);
						 //获取缓存数据
						   List<?> list = companyService.getCompanyList(sessionUser, null);
						   List<?> listHsGsAndJT = companyService.getHsCompanys(sessionUser, true);
						   String strtree  = menuService.getMainTreeMenusForCom(sessionUser, luser);
						   sys_info.append(",systemMenu:").append(strtree.replace("children", "items"));
						   System.out.println("==============strtree==========="+strtree);
						   model.addAttribute("listCompanys", list);
						   model.addAttribute("companyHsAndJTdata", listHsGsAndJT);
						   
						  // Collection<String>  s = new HashSet<String>();
						  // s.add("menu:add");
						   currentUser.getSession().setAttribute(IConstants.SHIRO_ROLE_INFO, con);
						   
						   currentUser.getSession().setAttribute(IConstants.SESSION_SYS_INFO, sys_info);
					}
					isSuccess =true;
					/**
					 *  设置css样式
					 */
					String css =  ExtjsCssEnum.mobileNeptune.getValue();
					if(!"true".equals(isMobile))
						css = ExtjsCssEnum.pcNeptune.getValue();
					   //传给本页面
					    model.addAttribute("pageCss", css);
					   //保存到session
					   currentUser.getSession().setAttribute(IConstants.SYSTEM+"_PAGECSS", css);
					
					  
					}catch (UnknownAccountException uae) {
						logger.info("对用户[" + loginName + "]进行登录验证..验证未通过,未知账户");
						errorCode = "未知账户";
					} catch (IncorrectCredentialsException ice) {
						logger.info("对用户[" + loginName
								+ "]进行登录验证..验证未通过,错误的凭证");
						errorCode = "用户名或密码不正确";
					} catch (LockedAccountException lae) {
						logger.info("对用户[" + loginName
								+ "]进行登录验证..验证未通过,账户已锁定");
						errorCode = "账户已锁定";
					} catch (ExcessiveAttemptsException eae) {
						logger.info("对用户[" + loginName
								+ "]进行登录验证..验证未通过,错误次数过多");
						errorCode = "错误次数过多";
					} catch (AuthenticationException ae) {
						logger.info("对用户[" + loginName
								+ "]进行登录验证..验证未通过,堆栈轨迹如下");
						ae.printStackTrace();
						errorCode = "用户名或密码不正确";
					}
					 catch (Exception e) {
							e.printStackTrace();
							errorCode = e.getMessage();
						}
//				}
//			}
		}
		if(isSuccess)
		  return new ModelAndView("pro/jsp/admin/main");
		else{

			model.addAttribute("errorCode", errorCode);
			
			if("true".equals(isMobile))
				return new ModelAndView("pro/jsp/login/mobile");
			else
				return new ModelAndView("pro/jsp/login/index");
		}
	}
	
	/**
	 * main 主界面 controller
	 * 主界面只设及到数据的获取,不涉及页面,故无需增controller
	 * @param model
	 */
	@RequestMapping("/main/getappinfo")
	public void getApplicationInfo(Model model) {
		Subject sub =  SecurityUtils.getSubject();
		Object str =sub.getSession().getAttribute(IConstants.SESSION_SYS_INFO);
		//SessionUser seUser = (SessionUser)sub.getSession().getAttribute(IConstants.SESSION_USER_KEY);
		/**************获取功能菜单*****************/
		//String sys_menu = "systemMenu:[{text : '系统设置',glyph :'xf0f7',description : '',items : [{id:'2',text : '菜单管理',glyph :'xf039',url:'sys/menumgr'}, {id:'1',text : '角色管理',glyph:'xf21d',url:'sys/role'},{id:'3',text : '辅助信息',glyph:'xf1c0',url:'sys/dd'}]}," +
		//		"{text : '基础信息',glyph :'xf0f7',description : '',items : [{id:'5',text : '组织架构',glyph :'xf19c',url:'basis/company'}, {id:'6',text : '人事资料',glyph:'xf0c0',url:'basis/hr'}, {id:'7',text : '登陆用户',glyph:'xf007',url:'basis/luser'}]}]";
		//String sys_menu = loginUserService.getMainTreeMenusForCom(seUser, luser)
		// str = str+","+sys_menu;
						
		//String re = "["
		//System.out.println("被授权的状态是： "+sub.isPermitted("APPSUMMARY:print"));

		this.setResultValue("{"+str.toString()+"}");
	}
	
	@RequestMapping("/main/changepwd")
    public void changePwdInMain(String oldPwd,String newPwd,Model model) {
		
		if(StringUtil.isEmpty(newPwd)|| StringUtil.isEmpty(oldPwd)){
			this.setErrorResultValue("无效的登陆密码");
		}else{
			try{
			    loginUserService.updateLoginPwd(newPwd,oldPwd,getSessionUser());
				this.setResultValue("OK");
			}catch (Exception e) {
				e.printStackTrace();
				  this.setErrorResultValue(getErrMsg(e));
			}
		}
	}
	
	@RequestMapping("/logout")
	public ModelAndView logOut(Model model) {
		Subject currentUser =  SecurityUtils.getSubject();
		String isMobile = (String)currentUser.getSession().getAttribute(IConstants.SYSTEM+"_ISMOBILE");
		SecurityUtils.getSubject().logout();
		 HttpSession session = request.getSession();
		 session.invalidate();
		if ("true".equals(isMobile))
			return new ModelAndView("pro/jsp/login/mobile");
		else
			return new ModelAndView("pro/jsp/login/index");

	}
	
	@RequestMapping("/unauthorized")
	public ModelAndView unauthorized(Model model) {
		Subject currentUser =  SecurityUtils.getSubject();
		model.addAttribute("pageCss",currentUser.getSession().getAttribute(IConstants.SYSTEM+"_PAGECSS"));
		return new ModelAndView("pro/jsp/error/unauthorized");
	}
	
	
}
