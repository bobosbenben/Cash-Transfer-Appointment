package en.sys.copybean.impl;

import en.common.copybean.ICopyBean;
import en.common.copybean.impl.GridCopyBeanImpl;
import en.common.dto.DTO;
import en.common.entity.BaseEntity;
import en.common.util.helper.LongUtil;
import en.sys.dto.MenuDTO;
import en.sys.entity.Menus;

public class MenuCopyBeanImpl extends GridCopyBeanImpl implements ICopyBean {

	public Class<?> getEntityClass() {
		
		return Menus.class;
	}

	public DTO createDTO() {
		return new MenuDTO();
	}
	
	@Override
	 public void copyEntity2DTO(BaseEntity entity, DTO dto) throws Exception {
		   super.copyEntity2DTO(entity, dto);
	       Menus menu = (Menus)entity;
	       MenuDTO mdto = (MenuDTO)dto;
	       if(LongUtil.isNewId(menu.getParentId())){
	    	   mdto.setParentName("根菜单");
	       }else{
	    	   Menus menus = (Menus)gridDao.getById(Menus.class, menu.getParentId());
	    	   mdto.setParentName(menus.getName());
	       }
	       
	       
	    }

}
