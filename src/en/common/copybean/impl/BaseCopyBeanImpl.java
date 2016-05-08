package en.common.copybean.impl;

import org.springframework.beans.BeanUtils;

import en.common.dto.DTO;
import en.common.entity.BaseEntity;
import en.common.frame.shiro.SessionUser;
import en.common.util.helper.DatetimeUtil;
import en.common.util.helper.LongUtil;
import en.common.util.helper.StringUtil;

public class BaseCopyBeanImpl {

	public void copyDTO2Entity(SessionUser sessionUser, BaseEntity entity, DTO dto) throws Exception {

        if (sessionUser == null) {
            throw new Exception("sessionUser传入为空");
        }
        
        try {
            BeanUtils.copyProperties(dto,entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("copyProperties错误");
        }

        if (LongUtil.isNewId(entity.getId())) {
        	 entity.setId(null);
             entity.setLrrDm(sessionUser.getLoginId());

             entity.setLrrq(DatetimeUtil.getCurrentDateTimeString());
	         if (StringUtil.isEmpty(entity.getGsDm()))
                 entity.setGsDm(sessionUser.getGsdm());
	      }
        entity.setXgrq(DatetimeUtil.getCurrentDateTimeString());
        entity.setXgrDm(sessionUser.getLoginId());
        
        if (dto.getStatus() == null)
            entity.setStatus(true);
        else
            entity.setStatus(dto.getStatus());
        
    }

    public void copyEntity2DTO(BaseEntity entity, DTO dto) throws Exception {
        try {
            BeanUtils.copyProperties(entity,dto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("copyProperties错误");
        }
    }
}
