package en.sys.service;

import java.util.List;

import en.common.frame.shiro.SessionUser;
import en.common.service.IGridSerivce;
import en.common.util.helper.ResultEntity;

public interface IRoleService extends IGridSerivce{
	
	/**
	 * 获取权限树设置
	 * @param menuList
	 * @param myMenuIdList
	 * @return
	 * @throws Exception
	 */
	public String getRoleDialogTreeViewData(List<?> menuList,List<?> myMenuIdList) throws Exception;
	
	/**
	 * 保存用户角色
	 * @param roleId
	 * @param menuIds
	 * @param sessioinuser
	 * @return
	 * @throws Exception
	 */
	
	public ResultEntity updateRoleMenu(final String roleId, final String menuIds,SessionUser sessioinuser) throws Exception;
	/**
	 * 保存独立用户权限
	 * @param roleId
	 * @param menuIds
	 * @param checkb
	 * @param sessioinuser
	 * @return
	 * @throws Exception
	 */
	public void updateIndependentLUserRole(final String roleId, final String menuIds,Boolean checkb,SessionUser sessioinuser) throws Exception;

}
