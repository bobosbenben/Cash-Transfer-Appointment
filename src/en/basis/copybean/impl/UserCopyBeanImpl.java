package en.basis.copybean.impl;

import en.basis.dto.UserDTO;
import en.basisc.entity.User;
import en.common.copybean.ICopyBean;
import en.common.copybean.impl.GridCopyBeanImpl;
import en.common.dto.DTO;
import en.common.entity.BaseEntity;
import en.common.frame.shiro.SessionUser;
import en.common.util.helper.DatetimeUtil;
import en.common.util.helper.LongUtil;
import en.common.util.helper.StringUtil;

public class UserCopyBeanImpl extends GridCopyBeanImpl implements ICopyBean{
	

	public Class<?> getEntityClass() {
		return User.class;
	}

	public DTO createDTO() {
		return new UserDTO();
	}

	
	public void copyDTO2Entity(SessionUser sessionUser, BaseEntity entity, DTO dto) throws Exception {
		super.copyDTO2Entity(sessionUser, entity, dto);
		User bill = (User)entity;
		UserDTO userdto = (UserDTO)dto; 
		if(!LongUtil.isNewId(userdto.getId()) && !userdto.getZjm().equals(bill.getZjm()))
			bill.setBz(bill.getBz()+"/"+DatetimeUtil.getCurrentDateString() +" "+sessionUser.getId()+"将"+bill.getZjm()+"更改为:"+userdto.getZjm()+"");
		
		bill.setGsDm(StringUtil.isEmpty(sessionUser.getHsgsDm()) ? sessionUser.getGsdm() : sessionUser.getHsgsDm());
    }
}
