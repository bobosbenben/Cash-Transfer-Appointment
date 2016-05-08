package en.common.copybean;

import en.common.dto.DTO;
import en.common.entity.BaseEntity;
import en.common.frame.shiro.SessionUser;

public interface  ICopyBean {
	
	   public Class<?> getEntityClass();
		
		public DTO createDTO();
		
		/**
	     * 深度copy 表头 具体单据类的特有属性，entity——〉DTO
	     */	 
	   public void copyEntity2DTO(BaseEntity bill, DTO headDTO) throws Exception;
	   /**
	    * 深度copy 表头 具体单据类的特有属性，DTO——〉entity
	    */ 
	   public void copyDTO2Entity(SessionUser sessionuser, BaseEntity bill, DTO dto) throws Exception;
	}
