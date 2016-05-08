package en.sys.service.impl;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import en.basisc.entity.LoginUser;
import en.common.copybean.ICopyBean;
import en.common.dto.DTO;
import en.common.frame.shiro.SessionUser;
import en.common.service.impl.GridServiceImpl;
import en.common.util.helper.DatetimeUtil;
import en.common.util.helper.ListUtil;
import en.common.util.helper.LongUtil;
import en.common.util.helper.ResultEntity;
import en.common.util.helper.RoleListsEntity;
import en.common.util.helper.StringUtil;
import en.sys.copybean.impl.MenuCopyBeanImpl;
import en.sys.dto.MenuDTO;
import en.sys.entity.LoginUserMenu;
import en.sys.entity.Menus;
import en.sys.entity.Role;
import en.sys.entity.RoleMenu;
import en.sys.service.IMenuService;


@Service
public class MenuServiceImpl extends GridServiceImpl implements IMenuService {

	@Override
	public ResultEntity getMenuTreeData(String gsDm) throws Exception {

		ResultEntity re = createResultEntity();

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(getCopyBean().getEntityClass());
		detachedCriteria.add(Restrictions.like("gsDm", gsDm + "%"));
		detachedCriteria.add(Restrictions.eq("status", true));
		detachedCriteria.addOrder(Order.asc("menuIndex"));

		try {
			List<?> list = gridDao.find(detachedCriteria);
			StringBuffer sb = getJsonMenuTree(list,0);
			re.setResultType(0);
			re.setResultDesc(sb);
		} catch (Exception e) {
			e.printStackTrace();
			String err = e.getCause() == null ? e.getMessage() : e.getCause()
					.getMessage();
			re.setResultType(-3);
			re.setResultDesc("出现异常，代码为-3,错误信息为:" + err + "; 请联系系统管理员");
		}
		return re;
	}

	@Override
	protected ICopyBean getCopyBean() {
		MenuCopyBeanImpl cp = new MenuCopyBeanImpl();
		cp.setGridDao(gridDao);
		return cp;
	}
	
	private StringBuffer getJsonMenuTree(List<?> listmenus,int depth) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		boolean first = true;
		if (!ListUtil.isEmpty(listmenus)) {
			for (int i = 0; i < listmenus.size(); i++) {
				Menus menu = (Menus) listmenus.get(i);
				if(menu.getDeepth() - depth == 0){
					if (first) {
						first = false;
						sb.append("{");
					} else {
						sb.append(",{");
					}
					sb.append("text:'").append(menu.getName()).append("',id:'")
							.append(menu.getId()).append("',url:'")
							.append(StringUtil.getNullStr(menu.getUrl()))
							.append("',deepth:'").append(menu.getDeepth())
							.append("',menuIndex:'")
							.append(menu.getMenuIndex()).append("'")
							.append(",glyph:'").append(menu.getGlyph()).append("'");
					StringBuffer s = buildJsonByParent(listmenus, menu.getId());
					if (!s.toString().equals("[]")) {
						sb.append(",children:").append(s);
					} else {
						sb.append(",leaf:true");
					}
					sb.append("}");
				}

			}
		}
		sb.append("]");
		return sb;
	}

	private StringBuffer buildJsonByParent(List<?> list, Long pid) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		boolean first = true;
		for (int i = 0; i < list.size(); i++) {
			Menus sonMenu = (Menus) list.get(i);
			if (sonMenu != null && !LongUtil.isNewId(sonMenu.getParentId())
					&& sonMenu.getStatus() && (sonMenu.getParentId() - pid ==0)) {
				if (first) {
					first = false;
				} else {
					sb.append(",");
				}
				sb.append("{text:'").append(sonMenu.getName()).append("',id:'")
						.append(sonMenu.getId()).append("',url:'")
						.append(StringUtil.getNullStr(sonMenu.getUrl()))
						.append("',deepth:'").append(sonMenu.getDeepth())
						.append("',menuIndex:'").append(sonMenu.getMenuIndex())
						.append("'")
						.append(",glyph:'").append(sonMenu.getGlyph()).append("'");

				StringBuffer s = buildJsonByParent(list, sonMenu.getId());
				if (!s.toString().equals("[]")) {
					sb.append(",children:").append(s);
				} else {
					sb.append(",leaf:true");
				}
				sb.append("}");
			}
		}
		sb.append("]");
		return sb;
	}
	
	public List<?> getAllGsMenus(SessionUser sessionuser) throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(getCopyBean().getEntityClass());
		detachedCriteria.add(Restrictions.like("gsDm", sessionuser.getBigGsdm()
				+ "%"));
		detachedCriteria.add(Restrictions.eq("status", true));
		List<?> menus = gridDao.find(detachedCriteria);
		return menus;
	}

	public List<?> getMyMenusIdByRoleId(SessionUser sessionuser, Long roleid)
			throws Exception {
		String sql = "SELECT    menu_id, CASE WHEN operate_add = 'Y' THEN 10000 + menu_id ELSE 0 END AS operate_add, "
				+ " CASE WHEN operate_delete = 'Y' THEN 20000 + menu_id ELSE 0 END AS operate_delete,"
				+ " CASE WHEN operate_edit = 'Y' THEN 30000 + menu_id ELSE 0 END AS opera_edit, "
				+ " CASE WHEN operate_print = 'Y' THEN 40000 + menu_id ELSE 0 END AS operate_print, "
				+ " CASE WHEN operate_search = 'Y' THEN 50000 + menu_id ELSE 0 END AS operates_earch, "
				+ " CASE WHEN operate_audit = 'Y' THEN 60000 + menu_id ELSE 0 END AS operate_audit, "
				+ " CASE WHEN operate_undo_audit = 'Y' THEN 70000 + menu_id ELSE 0 END AS operate_undo_audit "
				+ " FROM  xt_role_menu where role_id = '"
				+ roleid
				+ "' and gsdm  ='"
				+ sessionuser.getBigGsdm()
				+ "' and status = 'true'";
		List<?> list = gridDao.executeSqlREList(sql);
		if (ListUtil.isEmpty(list))
			return list;
		List<Object> resultList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Object[] objs = (Object[]) list.get(i);
			for (Object obj : objs) {
				if (!"0".equals(obj.toString())) {
					resultList.add(obj.toString());
				}
			}
		}
		return resultList;
	}
	
	public void AddMenu(String parentId, String name, String url,
			String deepth, String menuIndex, String menuLimits, Model model,
			SessionUser sessionuser) throws Exception {
		Menus menu = new Menus();
			if (!"root".equals(parentId))
				menu.setParentId(Long.parseLong(parentId));
			menu.setName(name);
			menu.setUrl(url);
			menu.setDeepth(Integer.parseInt(deepth));
			menu.setMenuIndex(menuIndex);
			menu.setMenuLimits(menuLimits);
			menu.setLrrq(DatetimeUtil.getCurrentDateTimeString());
			menu.setLrrDm(sessionuser.getLoginId());
			menu.setStatus(true);
			menu.setGsDm(sessionuser.getBigGsdm());
			this.save(menu);
			
			
	};
	
	public void updateBill(SessionUser sessionuser, String data) throws Exception {
		 super.updateBill(sessionuser, data);
	};
	
	
	public void checkValidBeforeUpdate(SessionUser sessionuser, DTO billDTO) throws Exception{
		MenuDTO dto = (MenuDTO) billDTO;
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(getCopyBean().getEntityClass());
		detachedCriteria.setProjection(Projections.rowCount());
		detachedCriteria.add(Restrictions.eq("name", dto.getName()));
		if(!LongUtil.isNewId(dto.getId()))
		   detachedCriteria.add(Restrictions.ne("id", dto.getId()));
		List<?> list = gridDao.find(detachedCriteria);
		if (!ListUtil.isEmpty(list) && LongUtil.getDoubleByObject(list.get(0)) > 0) {
			throw new Exception("已有菜单名为:" + dto.getName() + ",请更改名称");
		}
	};
	

	public List<?> getMyMenusIdByLuserId(SessionUser sessionuser, Long luserId)
			throws Exception {
		String sql = "SELECT    menu_id, CASE WHEN operate_add = 'Y' THEN 10000 + menu_id ELSE 0 END AS operate_add, "
				+ " CASE WHEN operate_delete = 'Y' THEN 20000 + menu_id ELSE 0 END AS operate_delete,"
				+ " CASE WHEN operate_edit = 'Y' THEN 30000 + menu_id ELSE 0 END AS opera_edit, "
				+ " CASE WHEN operate_print = 'Y' THEN 40000 + menu_id ELSE 0 END AS operate_print, "
				+ " CASE WHEN operate_search = 'Y' THEN 50000 + menu_id ELSE 0 END AS operates_earch, "
				+ " CASE WHEN operate_audit = 'Y' THEN 60000 + menu_id ELSE 0 END AS operate_audit, "
				+ " CASE WHEN operate_undo_audit = 'Y' THEN 70000 + menu_id ELSE 0 END AS operate_undo_audit "
				+ " FROM  xt_loginuser_menu where loginuser_id = '"
				+ luserId
				+ "' and gsdm  ='"
				+ sessionuser.getBigGsdm()
				+ "' and status = 'true'";
		List<?> list = gridDao.executeSqlREList(sql);
		if (ListUtil.isEmpty(list))
			return list;
		List<Object> resultList = new ArrayList<Object>();
		for (int i = 0; i < list.size(); i++) {
			Object[] objs = (Object[]) list.get(i);
			for (Object obj : objs) {
				if (!"0".equals(obj.toString())) {
					resultList.add(obj.toString());
				}
			}
		}
		return resultList;
	}

	public String getMainTreeMenusForCom(SessionUser sessionuser,LoginUser luser)throws Exception {
		StringBuffer str = new StringBuffer();
		if (luser.getIsIndependentRole() != null && luser.getIsIndependentRole()) {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LoginUserMenu.class);
			detachedCriteria.add(Restrictions.sqlRestriction("  1=1  and loginuser_id ='"+ luser.getId() + "'"));
			List<?> listlusermenus = gridDao.find(detachedCriteria);
			if (!ListUtil.isEmpty(listlusermenus)) {
				List<Object> listmenus = new ArrayList<Object>();
				List<Object> temp = new ArrayList<Object>();
				for(int i=0,nsize = listlusermenus.size();i<nsize;i++){
					LoginUserMenu lusermenu = (LoginUserMenu)listlusermenus.get(i);
					Menus menu = lusermenu.getMenu();
					if (!temp.contains(menu.getId())) {
						listmenus.add(menu);
						temp.add(menu.getId());
					}
				}
				str = getJsonMenuTree(listmenus,0);
			}
		} else {
			Set<Role> roles = luser.getRoles();
			if (roles == null || roles.size() <= 0) {
			} else {
				String rolestring = "";
				for (Role role : roles) {
					rolestring = rolestring + "," + role.getId();
				}
				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RoleMenu.class);
				detachedCriteria.add(Restrictions.sqlRestriction("  1=1  and role_id in ("+ rolestring.substring(1) + ")"));
				List<?> listrolemenus = gridDao.find(detachedCriteria);
				if (!ListUtil.isEmpty(listrolemenus)) {
					List<Menus> listmenus = new ArrayList<Menus>();
					List<Object> temp = new ArrayList<Object>();
					for(int i=0,nsize = listrolemenus.size();i<nsize;i++){
						RoleMenu rm = (RoleMenu)listrolemenus.get(i);
						Menus menu = rm.getMenu();
						if (!temp.contains(menu.getId())) {
							listmenus.add(menu);
							temp.add(menu.getId());
						}
					}
					str = getJsonMenuTree(listmenus,0);
				}
			}
		}
		return str.toString();
	}

	@Override
	public Collection<String> getShiroMenus(List<?> listmenus) throws Exception {
		Collection<String> re = new HashSet<String>();
		if(!ListUtil.isEmpty(listmenus)){
			for(int i=0,nsize = listmenus.size();i<nsize;i++){
				RoleListsEntity item = (RoleListsEntity)listmenus.get(i);
				if("Y".equals(item.getOperateAdd()))
					re.add(item.getBz()+":add");
				if("Y".equals(item.getOperateDelete()))
					re.add(item.getBz()+":del");
				if("Y".equals(item.getOperateEdit()))
					 re.add(item.getBz()+":update");
				if("Y".equals(item.getOperatePrint()))
					 re.add(item.getBz()+":print");
				if("Y".equals(item.getOperateSearch()))
					 re.add(item.getBz()+":search");
				if("Y".equals(item.getOperateAudit()))
					 re.add(item.getBz()+":audit");
				if("Y".equals(item.getOperateAudit()))
					 re.add(item.getBz()+":undoaudit");
					
				}
		}
		return re;
	}

}
