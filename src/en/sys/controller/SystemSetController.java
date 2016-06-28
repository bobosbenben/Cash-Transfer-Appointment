package en.sys.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import en.common.controller.GridController;
import en.common.frame.shiro.SessionUser;
import en.common.util.helper.ChineseUtil;
import en.common.util.helper.ResultEntity;
import en.common.util.helper.StringUtil;
import en.sys.service.IDataDictionaryService;
import en.sys.service.IMenuService;
import en.sys.service.IRoleService;

@RestController
@RequestMapping("sys")
public class SystemSetController extends GridController{
	
	@Resource
	private IMenuService menuService;
	@Resource
	private IRoleService roleService;
	@Resource
	private IDataDictionaryService dataDictionaryService;
	/**
	 * Menu controller管理
	 * 
	 * @param model
	 */
	@RequestMapping("/menumgr")
	public ModelAndView menuMgr(Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		super.setPageCss(currentUser, model);
		//获取功能权限
		String str = getComRole(currentUser,"MENU");
		 model.addAttribute("detailRole", str);
		return new ModelAndView("pro/jsp/sys/menuMgr");
	}

	@RequestMapping("/menu/menutreedata")
	public void menuTreeData(Model model) {
		try {
			SessionUser sessionUser = getSessionUser();
			ResultEntity re = menuService.getMenuTreeData(sessionUser.getBigGsdm());
			if (re.getResultType() < 0) 
				this.setStoreErrorResult(re.getResultDesc().toString());
			 else 
				this.setResultValue(re.getResultDesc().toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			this.setStoreErrorResult("出现异常，代码为-2,错误信息为:" + getErrMsg(e) + "; 请联系系统管理员");
		}
	}
	
	@RequestMapping("/menu/saveaddmenu")
	public void saveMenu(String parentId, String name, String url,
			String deepth, String menuIndex, String menuLimits, Model model) {

		try {
			SessionUser su = getSessionUser();
			menuService.AddMenu(parentId, name, url, deepth, menuIndex, menuLimits, model, su);
			this.setResultValue("{success:true,Msg:'添加成功'}");
		} catch (Exception e) {
			e.printStackTrace();
			this.setResultValue("{failure:true,Msg:'添加产生异常:" + getErrMsg(e) + "'}");
		}

	}
	
	@RequestMapping("/menu/delmenu")
	public void delMenu(String selectedIds, Model model) {

		try {
			  menuService.delEntityByIds(selectedIds);
			 this.setResultValue("OK");
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorResultValue(getErrMsg(e));
		}

	}
	
	@RequestMapping("/menu/loaddata")
	public void loadmenudata(String selectedId, int start, int limit,
			Model model) {

		try {
			
			String cond = "";
			SessionUser su = getSessionUser();
			if (!StringUtil.isEmpty(selectedId)) {
				cond = cond + " and tid in (" + selectedId + ")";
			}
			cond = cond + " and gsdm like '"+su.getBigGsdm()+"%' ";
			ResultEntity re = menuService.findPageResult(getSessionUser(),
					cond, start, limit);
			if (re.getResultType() == 0)
				this.setResultValue(re.getResultDesc().toString());
			else
				this.setStoreErrorResult(re.getResultDesc().toString());

		} catch (Exception e) {
			e.printStackTrace();
			this.setStoreErrorResult(getErrMsg(e));
		}

	}
	
	@RequestMapping("/menu/updatemenu")
	public void updateMenu(String jsonData, Model model) {
		if (StringUtil.isEmpty(jsonData)) {
			this.setErrorResultValue("无效的数据");
			return;
		}

		try {
			 menuService.updateBill(getSessionUser(), jsonData);
			 this.setResultValue("OK");
		} catch (Exception e) {
			e.printStackTrace();
			this.setErrorResultValue(getErrMsg(e));
		}

	}
	/************************Menu controller   end**************************/
	
	/**
	 * role controller 
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/role")
	public ModelAndView roleMgr(Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		super.setPageCss(currentUser, model);
		
		String str = getComRole(currentUser,"ROLE");
		model.addAttribute("detailRole", str);
		return new ModelAndView("pro/jsp/sys/roleMgr");
	}
	
		@RequestMapping("/role/update")
		public void updateRole(String jsonData,Model model) {
			if(StringUtil.isEmpty(jsonData)){
				this.setErrorResultValue("无效的数据");
				return;
			}
			try{
			     roleService.updateBill(getSessionUser(), jsonData);
				this.setResultValue("OK");
			}catch (Exception e) {
				e.printStackTrace();
				 this.setErrorResultValue(getErrMsg(e));
			}
		}
		
		@RequestMapping("/role/loaddata")
		public void loadRoleData(String anyField ,int start,int limit,Model model) {

			try {
				
				String cond = "";
				SessionUser su = getSessionUser();
				if (!StringUtil.isEmpty(anyField)){
					cond = cond+" and name like '%"+ ChineseUtil.getString(anyField)+"%'" ;
		        }
				cond = cond + " and gsdm like '"+su.getBigGsdm()+"%' and status = '1' ";
				ResultEntity re = roleService.findPageResult(getSessionUser(),
						cond, start, limit);
				if (re.getResultType() == 0)
					this.setResultValue(re.getResultDesc().toString());
				else
					this.setStoreErrorResult(re.getResultDesc().toString());

			} catch (Exception e) {
				e.printStackTrace();
				this.setStoreErrorResult(getErrMsg(e));
			}

		}
		
		@RequestMapping("/role/remove")
		public void removeRoleData(String selectedId ,Model model) {
			 if(StringUtil.isEmpty(selectedId)){
				 this.setErrorResultValue("必需选择要删除的角色");
				 return;
			 }
			 try{
				 SessionUser sessionuser = getSessionUser();
				  roleService.remove(sessionuser, selectedId);
				  this.setResultValue("OK");
				}catch (Exception e) {
					e.printStackTrace();
					 this.setErrorResultValue(getErrMsg(e));
			}
		}
		
		@RequestMapping("/role/getrolemenus")
		public void getRoleMenus(String selectedId ,Model model) {
			 if(StringUtil.isEmpty(selectedId)){
				 this.setErrorResultValue("必需选择角色");
				 return;
			 }
			 long begin = System.currentTimeMillis();
				
			 try{
				 SessionUser user = getSessionUser();
				 List<?> allMenuList = menuService.getAllGsMenus(user);
				 List<?> myMenuIdList = menuService.getMyMenusIdByRoleId(user,Long.parseLong(selectedId));
				  String re =  roleService.getRoleDialogTreeViewData(allMenuList, myMenuIdList);
					   System.out.println("后台读取权限菜单耗时时间:" + (System.currentTimeMillis() - begin));
					   System.out.println("查询结果:"+re);
						this.setResultValue(re);
				
				}catch (Exception e) {
					e.printStackTrace();
					 this.setErrorResultValue(getErrMsg(e));
			}
			
		}
		
		@RequestMapping("/role/updateroleforwin")
		public void updateroleforwin (String selectedId ,String  jsonData,Model model) {
			 if(StringUtil.isEmpty(selectedId)){
				 this.setErrorResultValue("必需选择角色");
				 return;
			 }
			 long begin = System.currentTimeMillis();
				
			 try{
				 ResultEntity re = roleService.updateRoleMenu(selectedId, jsonData, getSessionUser());
				 
				 System.out.println("后台保存权限菜单耗时时间:" + (System.currentTimeMillis() - begin));
					  if(re.getResultType() ==0)
						this.setResultValue(re.getResultDesc().toString());
				
				}catch (Exception e) {
					e.printStackTrace();
					String err = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
					 this.setErrorResultValue(err);
			}
			
		}
		
		/************************Role controller   end**************************/
		/**
		 * Data dictionayr controller管理
		 * 
		 * @param model
		 */
		@RequestMapping("/dd")
		public ModelAndView DataDictionary(Model model) {
			Subject currentUser = SecurityUtils.getSubject();
			super.setPageCss(currentUser, model);
			String str = getComRole(currentUser,"DD");
			//System.out.println(str);
			model.addAttribute("detailRole", str);

			try{
			List<?> dataTypeList = dataDictionaryService.getListDataType(getSessionUser());
				for (int i=0;i<dataTypeList.size();i++){
					System.out.println("dataType: "+dataTypeList.get(i));
				}
			   model.addAttribute("dataTypeList", dataTypeList);
			}catch(Exception e){
				e.printStackTrace();
				model.addAttribute("errorCode", getErrMsg(e)); 
			}
			
			return new ModelAndView("pro/jsp/sys/dataDictionary");
		}
		
		@RequestMapping("/dd/loaddata")
		public void loadDataDictionaryData(String gsdm,String anyField,Boolean status,String dataType,int start,int limit,Model model) {
			
			try{
				String cond = "";
				if(!StringUtil.isEmpty(gsdm)){
					cond = cond+" and gsdm = '"+gsdm+"'";
				}
				
				if(!StringUtil.isEmpty(anyField)){
					cond = cond+" and data_type like '%"+ChineseUtil.getString(anyField)+"%'";
				}
				if(status !=null ){
					cond = cond+" and status = '"+status+"'";
				}
				if(!StringUtil.isEmpty(dataType)){
					
					cond = cond+" and data_type = '"+ChineseUtil.getString(dataType)+"'";
				}
				
			   ResultEntity re =  dataDictionaryService.findPageResult(getSessionUser(),cond, start, limit);
			   if(re.getResultType() == 0)
				   this.setResultValue(re.getResultDesc().toString());
			   else
				   this.setStoreErrorResult(re.getResultDesc().toString());
			   
			
			}catch (Exception e) {
				e.printStackTrace();
				 this.setStoreErrorResult(getErrMsg(e));
			}
			
		}
		

		@RequestMapping("/dd/update")
		public void updateDataDictionaryInfo(String jsonData,Model model) {
			if(StringUtil.isEmpty(jsonData)){
				this.setErrorResultValue("无效的数据");
				return;
			}
			try{
			     dataDictionaryService.updateBill(getSessionUser(), jsonData);
				   this.setResultValue("OK");
			}catch (Exception e) {
				e.printStackTrace();
				 this.setErrorResultValue(getErrMsg(e));
			}
		}
		
		/************************DataDictionary controller   end**************************/
		
		/**
		 * Xtcs  controller管理
		 * 
		 * @param model
		 */
		@RequestMapping("/xtcs")
		public ModelAndView getXtcsJsp(Model model) {
			Subject currentUser = SecurityUtils.getSubject();
			super.setPageCss(currentUser, model);

			String str = getComRole(currentUser,"XTCS");
			model.addAttribute("detailRole", str);

			return new ModelAndView("pro/jsp/sys/xtcs");
		}

		@RequestMapping("/xtcs/update")
		public void updateXtcsInfo(String jsonData,Model model) {
			if(StringUtil.isEmpty(jsonData)){
				this.setErrorResultValue("无效的数据");
				return;
			}
			try{
				dataDictionaryService.updateBill(getSessionUser(), jsonData);
				this.setResultValue("OK");
			}catch (Exception e) {
				e.printStackTrace();
				this.setErrorResultValue(getErrMsg(e));
			}
		}
}
