package en.basis.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import en.basis.copybean.impl.LoginUserCopyBeanImpl;
import en.basis.service.ILoginUserService;
import en.basisc.entity.LoginUser;
import en.common.copybean.ICopyBean;
import en.common.frame.shiro.SessionUser;
import en.common.service.impl.GridServiceImpl;
import en.common.util.helper.Base64;
import en.common.util.helper.ListUtil;
import en.common.util.helper.RoleListsEntity;
import en.common.util.helper.StringUtil;
import en.sys.entity.LoginUserMenu;
import en.sys.entity.Menus;
import en.sys.entity.Role;
import en.sys.entity.RoleMenu;


@Service
public class LoginUserServiceImpl extends GridServiceImpl implements ILoginUserService{

	//新增记录
//	public void createBill(LoginUser user){
//		gridDao.save(user);
//	}

	public void updateBill(SessionUser sessionuser, String data) throws Exception{
		 super.updateBill(sessionuser, data);
	}


	
	@Override
	protected ICopyBean getCopyBean() {
		LoginUserCopyBeanImpl sp = new LoginUserCopyBeanImpl();
		sp.setGridDao(gridDao);
		return sp;
	}

	

	@Override
	public LoginUser getUserByLoginNamePassword(String loginName,String password, String gsDm) throws Exception {
		
		 DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
		 detachedCriteria.add(Restrictions.sqlRestriction(" login_account ='"+loginName+"' and login_password ='"+new Base64().encode64(password)+"' and status = 1"));
		 detachedCriteria.add(Restrictions.like("gsDm", gsDm));
		 List<?> list =   gridDao.find(detachedCriteria);
				if(ListUtil.isEmpty(list)){
					return null;
				}else{
					LoginUser loginuser = (LoginUser)list.get(0);
					return loginuser;
				   }
	}

	
	/*public ResultEntity updateLoginUserStoreLimits(String selectedId,
			String storeString, SessionUser sessionuser) throws Exception {
		ResultEntity re = createResultEntity();
		try{
		if(StringUtil.isEmpty(selectedId)){
			re.setResultType(-2);
		    re.setResultDesc("没有相应的登陆用户 或角色");
		}else{
			LoginUser luser = (LoginUser)gridDao.getById(getCopyBean().getEntityClass(), Long.parseLong(selectedId));
			if(luser == null || !luser.getStatus()){
				re.setResultType(-3);
			    re.setResultDesc("已无该用户或该用户 已被禁用");
			}else{
				luser.setLimitsCompanys(storeString);
				gridDao.save(luser);
				re.setResultType(0);
			    re.setResultDesc("OK");
			}
		}
		}catch (Exception e) {
			e.printStackTrace();
			  String err = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
			  re.setResultType(-3);
			  re.setResultDesc("出现异常，代码为-3,错误信息为:"+err+"; 请联系系统管理员");
		}
		return re;
	}
*/
	public void updateLoginPwd(String newpwd, String oldpwd,SessionUser sessionuser) throws Exception {
		LoginUser luer = getUserByLoginNamePassword(sessionuser.getLoginAccount(), new Base64().decode64(oldpwd), sessionuser.getBigGsdm());
		if(luer == null)
			throw new Exception("用户密码错误!");
		else{
			luer.setLoginPassword(newpwd);
			gridDao.save(luer);
		}
	}
	
	public void updateLoginUserRole(String selectedId,String roleString, SessionUser sessionuser) throws Exception {
			String[] strs = roleString.split(",");
			LoginUser luser = (LoginUser)gridDao.getById(getCopyBean().getEntityClass(), Long.parseLong(selectedId));
			Set<Role> set = new HashSet<Role>();
			for(String str : strs){
				Role role = (Role)gridDao.getById(Role.class, Long.parseLong(str));
				if(role!=null){
					set.add(role);
				}
			}
			if(set.size()>0){
				luser.setRoles(set);
				gridDao.save(luser);
			}
		}

	public LoginUser getUserByLoginName(String loginName, String gsDm)	throws Exception {
		
		 DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
		 detachedCriteria.add(Restrictions.sqlRestriction(" login_account ='"+loginName+"' and status = 1"));
		 detachedCriteria.add(Restrictions.like("gsDm", gsDm));
		 List<?> list =   gridDao.find(detachedCriteria);
				if(ListUtil.isEmpty(list))
					return null;
				else
					return (LoginUser)list.get(0);
				   
	}
	
	@Override
	public List<RoleListsEntity> getDetailMenus(LoginUser luser,String gsDm) throws Exception {
		  List<RoleListsEntity> listrole = new ArrayList<RoleListsEntity>();
		  if(luser.getIsIndependentRole() != null && luser.getIsIndependentRole()){
			  DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LoginUserMenu.class);
			  detachedCriteria.add(Restrictions.like("gsDm",gsDm+"%"));

			  detachedCriteria.add(Restrictions.sqlRestriction(" loginuser_id ='"+luser.getId()+"' and status = 1"));
			  List<?> list = gridDao.find(detachedCriteria);
			  for(int i =0,nsize = list.size();i < nsize;i++){
				  LoginUserMenu lum  = (LoginUserMenu)list.get(i);
				  RoleListsEntity rey = new RoleListsEntity();
				  rey.setOperateAdd(lum.getOperateAdd());
				  rey.setOperateDelete(lum.getOperateDelete());
				  rey.setOperateEdit(lum.getOperateEdit());
				  rey.setOperatePrint(lum.getOperatePrint());
				  rey.setOperateSearch(lum.getOperateSearch());
				  rey.setOperateAudit(lum.getOperateAudit());
				  rey.setOperateUndoAudit(lum.getOperateUndoAudit());
				  rey.setBz(lum.getMenu().getBz());
				  rey.setBz2(StringUtil.getNullStr(lum.getMenu().getBz2()));
				  listrole.add(rey);
			  }
		  }else{
			  List<Object> temp = new ArrayList<Object>(); 
			  Set<Role> roles = luser.getRoles();
			  if(roles != null && roles.size() >0){
				  for(Role role : roles){
					  DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RoleMenu.class);
					  //detachedCriteria.add(Restrictions.like("gsDm",gsDm+"%"));
					  //这里存在bug，xt_role_menu里的gsdm都是肯定都是超级管理员给的，而超级管理员属于最上级公司，所以gsdm都是最上级公司的代码
					  //导致下级公司员工的按钮显示不出来
					  detachedCriteria.add(Restrictions.sqlRestriction(" role_id ='"+role.getId()+"' and status = 1"));
					  List<?> listrm = gridDao.find(detachedCriteria);
					  if(!ListUtil.isEmpty(listrm)){
						  for(int i =0,nsize = listrm.size();i < nsize;i++){ 
							  RoleMenu rm = (RoleMenu)listrm.get(i);
							  Menus menu = rm.getMenu();
							  //list里已有此MenuId
							  if(temp.contains(menu.getId())){
								  //循环list
								  for(RoleListsEntity ren : listrole){
									  //如果新的权限为Y,则更新已有list里相信权限为Y
									  if(ren.getMenuId()== menu.getId()){
										  if(rm.getOperateAdd().equals("Y"))
											  ren.setOperateAdd("Y");
										  if(rm.getOperateDelete().equals("Y"))
											  ren.setOperateDelete("Y");
										  if(rm.getOperateEdit().equals("Y"))
											  ren.setOperateEdit("Y");
										  if(rm.getOperatePrint().equals("Y"))
											  ren.setOperatePrint("Y");
										  if(rm.getOperateSearch().equals("Y"))
											  ren.setOperateSearch("Y");
										  if(rm.getOperateAudit().equals("Y"))
											  ren.setOperateAudit("Y");
										  if(rm.getOperateUndoAudit().equals("Y"))
											  ren.setOperateUndoAudit("Y");
									  }
								  }
							  }else{
								  //不含此MenuId,为新增
								  temp.add(menu.getId());
								  RoleListsEntity rey = new RoleListsEntity();
								  rey.setOperateAdd(rm.getOperateAdd());
								  rey.setOperateDelete(rm.getOperateDelete());
								  rey.setOperateEdit(rm.getOperateEdit());
								  rey.setOperatePrint(rm.getOperatePrint());
								  rey.setOperateSearch(rm.getOperateSearch());
								  rey.setOperateAudit(rm.getOperateAudit());
								  rey.setOperateUndoAudit(rm.getOperateUndoAudit());
								  rey.setBz(menu.getBz());
								  rey.setBz2(menu.getBz2());
								  listrole.add(rey);
								  
							  }
						  }
					  }
					  
				  }
			  }
		  }
		return listrole;
	}
}
