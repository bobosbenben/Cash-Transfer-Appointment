package en.sys.service;

import java.util.Collection;
import java.util.List;

import org.springframework.ui.Model;

import en.basisc.entity.LoginUser;
import en.common.frame.shiro.SessionUser;
import en.common.service.IGridSerivce;
import en.common.util.helper.ResultEntity;

public interface IMenuService extends IGridSerivce{
	/**
	 * 获取菜单管理功能右侧树数据
	 * @param gsDm
	 * @return
	 * @throws Exception
	 */
	public ResultEntity getMenuTreeData(String gsDm) throws Exception;
    
	
	public void AddMenu(String parentId, String name, String url,
			String deepth, String menuIndex, String menuLimits, Model model,SessionUser sessionuser) throws Exception;
	
	/**
	 * 获取所有的公司菜单
	 * @param ba
	 * @param detailMenu
	 * @return
	 */
	 public List<?>  getAllGsMenus(SessionUser sessionuser) throws Exception;
	 
	 /**
	  * 获取自身所援用菜单ID List
	  * @param roleid
	  * @param detailMenu
	  * @return
	  */
	 public List<?> getMyMenusIdByRoleId(SessionUser sessionuser,Long roleid) throws Exception;
	 
	 /**
	  * 获取自身独立援用菜单ID List
	  * @param roleid
	  * @param detailMenu
	  * @return
	  */
	 public List<?> getMyMenusIdByLuserId(SessionUser sessionuser,Long roleid) throws Exception;
	
	 /**
	  * 获取viewport 功能树
	  * @param sessionuser
	  * @return
	  * @throws Exception
	  */
	 public String getMainTreeMenusForCom(SessionUser sessionuser,LoginUser luser) throws Exception;
	 /**
	  * 获取shrio权限
	  * @param sessionuser
	  * @param luser
	  * @return
	  * @throws Exception
	  */
	 public Collection<String> getShiroMenus(List<?> listmenus) throws Exception;
}
