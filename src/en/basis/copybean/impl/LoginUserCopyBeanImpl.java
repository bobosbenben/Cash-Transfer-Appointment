package en.basis.copybean.impl;



import java.util.List;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import en.basis.dto.LoginUserDTO;
import en.basisc.entity.LoginUser;
import en.basisc.entity.User;
import en.common.copybean.ICopyBean;
import en.common.copybean.impl.GridCopyBeanImpl;
import en.common.dto.DTO;
import en.common.entity.BaseEntity;
import en.common.frame.shiro.SessionUser;
import en.common.util.helper.Base64;
import en.common.util.helper.ListUtil;
import en.common.util.helper.LongUtil;
import en.sys.entity.Role;

public class LoginUserCopyBeanImpl extends GridCopyBeanImpl implements ICopyBean{

	public Class<?> getEntityClass() {
		return LoginUser.class;
	}

	public DTO createDTO() {
		return new LoginUserDTO();
	}
	public void copyDTO2Entity(SessionUser sessionUser, BaseEntity entity, DTO dto) throws Exception {
		LoginUserDTO udto = (LoginUserDTO) dto;
		LoginUser lu = (LoginUser) entity;
		String pwd = udto.getLoginPassword();
		if (pwd.equals("********")) {
			pwd = lu.getLoginPassword();
		} else {
			pwd = new Base64().encode64(pwd);
		}
		udto.setLoginPassword(pwd);
		//检测：因操作人代码取loginuser的id，故不允许在id不为空时修改助记码
		if(!LongUtil.isNewId(udto.getId())){
			LoginUser luser  = (LoginUser)gridDao.getById(getEntityClass(), udto.getId());
			if(luser != null && !luser.getLoginAccount().equals(udto.getLoginAccount()))
				throw new Exception("不允许修改使用用户名,如需要添加请点击新增");
		}
		
		super.copyDTO2Entity(sessionUser, lu, udto);
		
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
			detachedCriteria.add(Restrictions.eq("zjm",lu.getLoginAccount()));
			detachedCriteria.add(Restrictions.like("gsDm",sessionUser.getBigGsdm()+"%"));
			detachedCriteria.add(Restrictions.eq("status",true));
		   List<?> list =gridDao.find(detachedCriteria);
		   if(ListUtil.isEmpty(list) || list.size() > 1)
			   throw new Exception("无效的用户 信息或此用户已离职");
		   else{
			   User user  = (User)list.get(0);
			   lu.setUser(user);
		     }
		   
			
    }
	
	public void copyEntity2DTO(BaseEntity entity, DTO dto) throws Exception {
		super.copyEntity2DTO(entity, dto);
		LoginUserDTO udto = (LoginUserDTO) dto;
		LoginUser u = (LoginUser) entity;
		udto.setLoginPassword("********");
		Set<Role> set = u.getRoles();
		String roleString = "";
		if (set != null && set.size() > 0) {
			for(Role role : set){
				roleString = roleString+","+role.getId();
			}
			udto.setRoleString(roleString.substring(1));
		}
		
    }

}
