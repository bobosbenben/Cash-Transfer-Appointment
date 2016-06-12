package en.basis.service;

import java.util.List;

import en.basisc.entity.LoginUser;
import en.basisc.entity.User;
import en.common.frame.shiro.SessionUser;
import en.common.service.IGridSerivce;
import en.common.util.helper.RoleListsEntity;

public interface ILoginUserService extends IGridSerivce{
	
	 /*public ResultEntity updateHardWare(SessionUser sessionuser,String hardWareMsg,String gsDm,Long lrrdm) throws Exception;
	 
	 
	 public ResultEntity updateLoginUserStoreLimits(String selectedId,String storeString,SessionUser sessionuser) throws Exception;
	 
	 */
	/**
	 * 更改密码
	 * @param newpwd
	 * @param oldpwd
	 * @param sessionuser
	 * @return
	 * @throws Exception
	 */
	public void updateLoginPwd(String newpwd,String oldpwd,SessionUser sessionuser) throws Exception;
	
	 /**
	  * 获取用户权限明细
	  * @param luser
	  * @param gsdm
	  * @return
	  * @throws Exception
	  */
	 public List<RoleListsEntity> getDetailMenus(LoginUser luser,String gsdm) throws Exception;
	/**
	 * 添加登陆用户 角色
	 * @param selectedId
	 * @param roleString
	 * @param sessionuser
	 * @throws Exception
	 */
	public void updateLoginUserRole(String selectedId,String roleString,SessionUser sessionuser) throws Exception;
	/**
	 * 根据用户名密码获取用户
	 * @param loginName
	 * @param password
	 * @param gsDm
	 * @return
	 * @throws Exception
	 */
	public LoginUser getUserByLoginNamePassword(String loginName,String password,String gsDm) throws Exception;
	/**
	 * 根据用户名获取登陆用户
	 * @param loginName
	 * @param gsDm
	 * @return
	 * @throws Exception
	 */
	public LoginUser getUserByLoginName(String loginName,String gsDm) throws Exception;

	/**
	 * 根据用户(其实是Id)获取登陆用户
	 * @param user
	 * @throws Exception
	 */
	public LoginUser getLoginUserByUserId(User user) throws Exception;

//	/**
//	 * 添加登陆用户 角色
//	 * @param selectedId
//	 * @param roleString
//	 * @param sessionuser
//	 * @throws Exception
//	 */
//	public void updateLoginUserRole(String roleString,SessionUser sessionuser) throws Exception;
}
