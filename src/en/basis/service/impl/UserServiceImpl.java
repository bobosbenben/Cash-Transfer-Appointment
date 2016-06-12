package en.basis.service.impl;

import java.util.List;

import en.common.util.helper.StringUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import en.basis.copybean.impl.UserCopyBeanImpl;
import en.basis.dto.UserDTO;
import en.basis.service.IUserService;
import en.basisc.entity.User;
import en.common.copybean.ICopyBean;
import en.common.dto.DTO;
import en.common.frame.shiro.SessionUser;
import en.common.service.impl.GridServiceImpl;
import en.common.util.helper.JacksonsUtil;
import en.common.util.helper.ListUtil;
import en.common.util.helper.LongUtil;

@Service
public class UserServiceImpl extends GridServiceImpl implements IUserService{

	@Override
	protected ICopyBean getCopyBean() {
		return new UserCopyBeanImpl();
	}

	public void updateBill(SessionUser sessionuser, String data) throws Exception{
		 super.updateBill(sessionuser, data);
	}
	
	public void checkValidBeforeUpdate(SessionUser sessionuser, DTO billDTO) throws Exception{
		UserDTO hrdto = (UserDTO) billDTO;
		if (LongUtil.isNewId(hrdto.getId())) {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
			detachedCriteria.setProjection(Projections.rowCount());
			detachedCriteria.add(Restrictions.eq("zjm", hrdto.getZjm()));
			detachedCriteria.add(Restrictions.like("gsDm", sessionuser.getBigGsdm()+"%"));
			List<?> list = gridDao.find(detachedCriteria);
			if(!ListUtil.isEmpty(list) && LongUtil.getDoubleByObject(list.get(0)) > 0){
				throw new Exception("此工号已经存在!");
			}
		}
	};
	
	public void updateBillByDTO(SessionUser sessionuser, DTO dto) throws Exception {
		 super.updateBillByDTO(sessionuser, dto);
//		/*
//		UserDTO udto = (UserDTO)dto;
//		try{
//		if (LongUtil.isNewId(udto.getId())) {
//			if(udto.getRelationLogin() !=null){
//				System.out.println("自动关联登陆信息处理开始,工号:"+udto.getZjm());
//				DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(getCopyBean().getEntityClass());
//				detachedCriteria1.add(Restrictions.eq("zjm", udto.getZjm()));
//				detachedCriteria1.add(Restrictions.eq("status", true));
//				detachedCriteria1.add(Restrictions.like("gsDm", sessionuser.getBigGsdm()+"%"));
//				List<?> userlist = gridDao.find(detachedCriteria1);
//				if(ListUtil.isEmpty(userlist)){
//					re.setResultType(-2);
//					re.setResultDesc("无效的工号,请先在人事资料里录入相关工号信息");
//					return re;
//				}
//				else{
//					User user  = (User)userlist.get(0);
//				DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LoginUser.class);
//				detachedCriteria.add(Restrictions.eq("loginAccount", udto.getLoginName()));
//				detachedCriteria.add(Restrictions.like("gsDm", sessionuser.getBigGsdm()+"%"));
//				List<?> relist = gridDao.find(detachedCriteria);
//				if(ListUtil.isEmpty(relist)){
//					LoginUser loginuser = new LoginUser();
//					loginuser.setLoginAccount(udto.getLoginName());
//					loginuser.setName(udto.getName());
//					loginuser.setLoginPassword(new Base64().encode64(udto.getLoginpwd()));
//					loginuser.setLrrq(DatetimeUtil.getCurrentDateTimeString());
//					loginuser.setLrrDm(sessionuser.getLoginId());
//					loginuser.setStatus(true);
//
//					if(!StringUtil.isEmpty(udto.getDept()) && udto.getDept().indexOf(",")>=0)
//					   loginuser.setGsDm(sessionuser.getHsgsDm());
//					else{
//						String dept = udto.getDept();
//					    String gsdm = 	companyService.getCompanyIdByCompanyName(sessionuser, dept);
//						if(StringUtil.isEmpty(gsdm))
//							loginuser.setGsDm(sessionuser.getHsgsDm());
//						else
//							loginuser.setGsDm(gsdm);
//					}
//
//					loginuser.setSearchDay(0);
//					loginuser.setUser(user);
//					gridDao.save(loginuser);
//				}
//			  }
//			}
//		}
//		}catch (Exception e) {
//			e.printStackTrace();
//			String err = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
//			re.setResultType(-3);
//			re.setResultDesc("出现异常，代码为-3,错误信息为:"+err+"; 请联系系统管理员");
//		}
//		return re;*/
	}

	@Override
	public List<?> getUserByZjm(SessionUser sessionuser, String zjm)
			throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
		detachedCriteria.add(Restrictions.eq("zjm", zjm));
		detachedCriteria.add(Restrictions.eq("status", true));
		detachedCriteria.add(Restrictions.like("gsDm", sessionuser.getBigGsdm()+"%"));
		
		return gridDao.find(detachedCriteria);
	}

	public void remove(SessionUser sessionUser,String selectedId) throws Exception{

		//System.out.println("调用删除方法");
		//更新rolemenu
		selectedId = StringUtil.realStr(selectedId);
		//String rolemenusql = "delete from xt_rolemenu where roleid  in("+selectedId+")";
		//gridDao.executeSql(rolemenusql);
		//更新role
		//String menusql = "delete from xt_role where tid in("+selectedId+")";
		//gridDao.updateSql(menusql);
		super.remove(sessionUser, selectedId);
	}

	@Override
	public String getHRInfoById(String selected) throws Exception {
		User user = (User)gridDao.getById(User.class, Long.parseLong(selected));
		if(user == null)
		return null;
		else{
			return JacksonsUtil.me().readAsString(user);
		}
	}

	@Override
	public User getUserById(String selected) throws Exception {
		User user = (User)gridDao.getById(User.class, Long.parseLong(selected));
		if(user == null)
			return null;
		else{
			return user;
		}
	}

	@Override
	public List<?> getUserByZjm(String zjm)
			throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
		detachedCriteria.add(Restrictions.eq("zjm", zjm));
		detachedCriteria.add(Restrictions.eq("status", true));

		return gridDao.find(detachedCriteria);
	}
}
