package en.sys.copybean.impl;

import en.common.copybean.ICopyBean;
import en.common.copybean.impl.GridCopyBeanImpl;
import en.common.dto.DTO;
import en.common.entity.BaseEntity;
import en.common.frame.shiro.SessionUser;
import en.common.util.helper.StringUtil;
import en.sys.dto.RoleDTO;
import en.sys.entity.Role;

public class RoleCopyBeanImpl extends GridCopyBeanImpl implements ICopyBean{

	@Override
	public Class<?> getEntityClass() {
		return Role.class;
	}

	@Override
	public DTO createDTO() {
		return new RoleDTO();
	}

	public void copyDTO2Entity(SessionUser sessionUser, BaseEntity entity, DTO dto) throws Exception {
		RoleDTO mydto = (RoleDTO)dto;
		Role role = (Role)entity;
		super.copyDTO2Entity(sessionUser, entity, dto);
		if(StringUtil.isEmpty(mydto.getGsDm()))
			role.setGsDm(sessionUser.getBigGsdm());
	}
}
