package en.basis.controller;

import java.util.List;

import javax.annotation.Resource;

import en.basisc.entity.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import en.basis.dto.UserDTO;
import en.basis.service.ICompanyService;
import en.basis.service.ILoginUserService;
import en.basis.service.IUserService;
import en.basisc.entity.User;
import en.common.controller.GridController;
import en.common.frame.shiro.SessionUser;
import en.common.util.helper.ChineseUtil;
import en.common.util.helper.JSONUtil;
import en.common.util.helper.ListUtil;
import en.common.util.helper.ResultEntity;
import en.common.util.helper.StringUtil;
import en.sys.entity.Role;
import en.sys.service.IDataDictionaryService;
import en.sys.service.IMenuService;
import en.sys.service.IRoleService;

@RestController
@RequestMapping("basis")
public class BasisController extends GridController{
	
	@Resource
	private ICompanyService companyService;
	@Resource
	private IUserService userService;
	@Resource
	private IDataDictionaryService dataDictionaryService;
	@Resource
	private ILoginUserService loginUserService;
	@Resource
	private IMenuService menuService;
	@Resource
	private IRoleService roleService;
	
	/**
	 * Company controller管理
	 * 
	 * @param model
	 */
	@RequestMapping("/company")
	public ModelAndView menuMgr(Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		super.setPageCss(currentUser, model);
		String str = getComRole(currentUser,"COMPANY");
		model.addAttribute("detailRole", str);
		return new ModelAndView("pro/jsp/basis/company");
	}

	@RequestMapping("/company/companytreedata")
	public void menuTreeData(Model model) {
		try{
           String  re = companyService.getCompanyTree(getSessionUser(), null);
            if(!StringUtil.isEmpty(re)){
            	re = "{children:["+re+"]}";
            }
		    this.setResultValue(re);
	}catch(Exception e){
		e.printStackTrace();
		String err = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
		this.setStoreErrorResult("出现异常，代码为-2,错误信息为:"+err+"; 请联系系统管理员");
	}
	}
	@RequestMapping("/company/update")
	public void updateCompanyInfo (String  jsonData,Model model) {
		 if(StringUtil.isEmpty(jsonData)){
			 this.setErrorResultValue("无法获取到要修改的数据");
			 return;
		 }
			
		 try{
			  companyService.updateBill(getSessionUser(), jsonData);
			   this.setResultValue("OK");
			}catch (Exception e) {
				e.printStackTrace();
				 this.setErrorResultValue(getErrMsg(e));
		}
		
	}
	
	@RequestMapping("/company/loaddata")
	public void loadCompanyData(String gsdm,String status,String anyField,int start,int limit,Model model) {
		
		try{
			String cond = "";
			if(!StringUtil.isEmpty(gsdm)){
				cond = cond+" and sj_gsdm = '"+gsdm+"'";
			}
			if(!StringUtil.isEmpty(status)){
				cond = cond+" and status = '"+status+"'";
			}
			if(!StringUtil.isEmpty(anyField)){
				cond = cond+" and name like '%"+ChineseUtil.getString(anyField)+"%' ";
			}
		   ResultEntity re =  companyService.findPageResult(getSessionUser(),cond, start, limit);
		   if(re.getResultType() == 0)
			   this.setResultValue(re.getResultDesc().toString());
		   else
			   this.setStoreErrorResult(re.getResultDesc().toString());
		   
		
		}catch (Exception e) {
			e.printStackTrace();
			 this.setStoreErrorResult(getErrMsg(e));
		}
		
	}
	
	/************************company controller   end**************************/
	/**
	 * HR controller管理
	 * 
	 * @param model
	 */
	@RequestMapping("/hr")
	public ModelAndView hrMgrJsp(Model model) {
		try{
		SessionUser sessionuser = getSessionUser();
		//List<Post> listpost = postService.getPostList(sessionuser);
		List<?> listpost = dataDictionaryService.getListByDataType(sessionuser, "岗位", null, null);
		List<?> listResignationType = dataDictionaryService.getListByDataType(sessionuser, "离职类型", null, null);
		List<?> listDiploma = dataDictionaryService.getListByDataType(sessionuser, "学历", null, null);
		model.addAttribute("listpost", listpost);
		model.addAttribute("listResignationType", listResignationType);
		model.addAttribute("listDiploma", listDiploma);
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorCode", getErrMsg(e)); 
		}
		Subject currentUser = SecurityUtils.getSubject();
		super.setPageCss(currentUser, model);
		
		String str = getComRole(currentUser,"HR");
		model.addAttribute("detailRole", str);
		return new ModelAndView("pro/jsp/basis/hrMgr");
	}
	
	@RequestMapping("/hr/updatewin")
	public void updateHRWin (@ModelAttribute UserDTO headDTO,Model model) {
		this.gridSerivce = userService;
		super.updateBillByDTO(headDTO);
		User user = null;
		try {
			List<?> users = userService.getUserByZjm(headDTO.getZjm());
			if (users.size()>1) throw new Exception("用户名重复!");
			else user = (User)users.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//关联为登录名，则将该条记录添加到xt_loginuser中
		if (headDTO.getRelationLogin().equals("true")){
			this.gridSerivce = companyService;
			String gsdm = null;
			try {
				gsdm = companyService.getGsxxByName(headDTO.getDept()).getGsDm();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("通过公司名称获取公司代码时出错");//在窗口上选择的是公司的名称，要得到公司代码存入xt_loginuser
			}

			this.gridSerivce = loginUserService;
			LoginUser loginUser = null;
			loginUser = UserDTO2LoginUser(headDTO,user);
			loginUser.setGsDm(gsdm);
			gridSerivce.save(loginUser);
		}
	}
	
	@RequestMapping("/hr/loaddata")
	public void loadHRData(String dept,String status,String anyField,String isResignation,int start,int limit,Model model) {
		
		try{
			String cond = "";
			SessionUser sessionUser = getSessionUser();
			if(!StringUtil.isEmpty(dept)){
				cond = cond+" and dept like'%"+ ChineseUtil.getString(dept) +"%'";
			}
			if(!StringUtil.isEmpty(status)){
				cond = cond+" and status = '"+status+"'";
			}
			if(!StringUtil.isEmpty(anyField)){
				cond = cond+" and (name like '%"+ChineseUtil.getString(anyField)+"%' or zjm = '"+ChineseUtil.getString(anyField)+"')";
			}
			if(!StringUtil.isEmpty(isResignation)){
				cond = cond+" and is_resignation = '"+isResignation+"'";
			}
			
			cond = cond+" and gsdm like '"+sessionUser.getHsgsDm()+"%'"; 
			
		   ResultEntity re =  userService.findPageResult(sessionUser,cond, start, limit);
		   if(re.getResultType() == 0)
			   this.setResultValue(re.getResultDesc().toString());
		   else
			   this.setStoreErrorResult(re.getResultDesc().toString());
		   
		
		}catch (Exception e) {
			e.printStackTrace();
			String err = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
			 this.setStoreErrorResult(err);
		}
		
	}
	
	@RequestMapping("/hr/update")
	public void updateHRInfo (String  jsonData,Model model) {
		 if(StringUtil.isEmpty(jsonData)){
			 this.setErrorResultValue("无法获取到要修改的数据");
			 return;
		 }
			
		 try{
			  userService.updateBill(getSessionUser(), jsonData);
			  this.setResultValue("OK");
			}catch (Exception e) {
				e.printStackTrace();
				 this.setErrorResultValue(getErrMsg(e));
		}
		
	}
	
	@RequestMapping("/hr/getHRInfo")
	public void getHRInfo(String  selectedId,Model model) {
		 if(StringUtil.isEmpty(selectedId) ){
			 this.setErrorResultValue("请选择相应数据");
			 return;
		 }
		 try{
			String str =   userService.getHRInfoById(selectedId);
					this.setResultValue(str);
			}catch (Exception e) {
				e.printStackTrace();
				this.setErrorResultValue(getErrMsg(e));
		}

	}

	@RequestMapping("/hr/remove")
	public void removeAppointmentSummaryData(String selectedId){
		if(StringUtil.isEmpty(selectedId)){
			this.setErrorResultValue("必需选择要删除的记录");
		}
		try{
			SessionUser sessionuser = getSessionUser();
			userService.remove(sessionuser,selectedId);
			this.setResultValue("OK");
		}catch (Exception e) {
			e.printStackTrace();
			this.setErrorResultValue(getErrMsg(e));
		}

		//删除相应的登录用户，在xt_loginuser表中将该用户的status修改为0
		User user=null;
		try {
				user = userService.getUserById(selectedId);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("通过UserId获取用户的时候失败");
			}

		LoginUser loginUser =null;
		try {
			 loginUser = loginUserService.getLoginUserByUserId(user);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("通过User的id来获取登录用户LoginUser的时候失败");
		}
		if (loginUser!=null){
			try{
				SessionUser sessionUser = getSessionUser();
				loginUserService.remove(sessionUser,loginUser.getId().toString());
			}catch (Exception e){
				e.printStackTrace();
				System.out.println("删除登录用户失败，登录用户的Id是："+loginUser.getId());
			}
		}
	}
	
	/************************hr controller   end**************************/
	
	/**
	 * 登陆用户   loginUser controller
	 * 
	 */
	@RequestMapping("/luser")
	public ModelAndView loginuserMgrJsp(Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		super.setPageCss(currentUser, model);
		String str = getComRole(currentUser,"LUSER");
		model.addAttribute("detailRole", str);
		try{
		List<?>  listrole = loginUserService.findEntityByCond(Role.class, null, getSessionUser());
		model.addAttribute("listRoles", listrole);
		}catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errCode", getErrMsg(e));
		}
		
		return new ModelAndView("pro/jsp/basis/loginUser");
	}
	
	@RequestMapping("/luser/getuser")
	public void getUserForLoginUser(String  userId,Model model) {
		 if(StringUtil.isEmpty(userId)){
				 this.setErrorResultValue("需要提供登陆用户名");
				 return;
			 }
		 try{
			 SessionUser sessionuser = getSessionUser();
			 List<?> list  = userService.getUserByZjm(sessionuser, userId);
			  if(ListUtil.isEmpty(list)){
				  this.setErrorResultValue("无此代码用户");
			  }else{
				  JSONUtil js = new JSONUtil();
				  User user = (User)list.get(0);
				  js.addJsonString("name", user.getName());
				  this.setResultValue(js.getString());
			  }
					 
			}catch (Exception e) {
				e.printStackTrace();
				 this.setErrorResultValue(getErrMsg(e));
		}
	}
	
	@RequestMapping("/luser/update")
	public void updateLoginUserInfo(String  jsonData,Model model) {
		 if(StringUtil.isEmpty(jsonData)){
			 this.setErrorResultValue("无法获取到要修改的数据");
			 return;
		 }
		 try{
			  loginUserService.updateBill(getSessionUser(), jsonData);
			    this.setResultValue("OK");
			}catch (Exception e) {
				e.printStackTrace();
				 this.setErrorResultValue(getErrMsg(e));
		}
	}
	
	@RequestMapping("/luser/loaddata")
	public void loadLoginUserData(String gsdm,String status,String anyField,int start,int limit,Model model) {
		
		try{
			String cond = "";
			if(!StringUtil.isEmpty(gsdm)){
				cond = cond+" and gsdm = '"+gsdm+"'";
			}
			if(!StringUtil.isEmpty(status)){
				cond = cond+" and status = '"+status+"'";
			}
			if(!StringUtil.isEmpty(anyField)){
				cond = cond+" and (name like '%"+ChineseUtil.getString(anyField)+"%' or login_account = '"+ChineseUtil.getString(anyField)+"')";
			}
		   ResultEntity re =  loginUserService.findPageResult(getSessionUser(),cond, start, limit);
		   if(re.getResultType() == 0)
			   this.setResultValue(re.getResultDesc().toString());
		   else
			   this.setStoreErrorResult(re.getResultDesc().toString());
		   
		
		}catch (Exception e) {
			e.printStackTrace();
			String err = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
			 this.setStoreErrorResult(err);
		}
		
	}
	
	@RequestMapping("/luser/updaterole")
	public void updateLoginUserrole(String  selectedId,String roleString,Model model) {
		 if(StringUtil.isEmpty(selectedId)|| StringUtil.isEmpty(roleString)){
			 this.setErrorResultValue("用户或选择角色为空");
			 return;
		 }
		 try{
			   loginUserService.updateLoginUserRole(selectedId, roleString, getSessionUser());
				this.setResultValue("OK");
			}catch (Exception e) {
				e.printStackTrace();
				 this.setErrorResultValue(getErrMsg(e));
		}
	}
	
	@RequestMapping("/luser/getindependentrole")
	public void getLoginUserIndependentRole(String  selectedId,Model model) {
		 if(StringUtil.isEmpty(selectedId)){
			 this.setErrorResultValue("用户为空");
			 return;
		 }
		 try{
			 SessionUser sUser = getSessionUser();
			 List<?> allMenuList = menuService.getAllGsMenus(sUser);
			 List<?> myMenuIdList = menuService.getMyMenusIdByLuserId(getSessionUser(),Long.parseLong(selectedId));
			  String re =  roleService.getRoleDialogTreeViewData(allMenuList, myMenuIdList);
			  this.setResultValue(re);
			}catch (Exception e) {
				e.printStackTrace();
				 this.setErrorResultValue(getErrMsg(e));
		}
		
	}
	
	@RequestMapping("/luser/updateindependentrole")
	public void gupdateLoginUserIndependentRole(String  selectedId,String jsonData,Boolean checkb,Model model) {
		 if(StringUtil.isEmpty(selectedId) || checkb == null){
			 this.setErrorResultValue("所需数据为空");
			 return;
		 }
		 try{
			  roleService.updateIndependentLUserRole(selectedId, jsonData,checkb, getSessionUser());
					this.setResultValue("OK");
			}catch (Exception e) {
				e.printStackTrace();
				 this.setErrorResultValue(getErrMsg(e));
		}
		
	}
	
}
