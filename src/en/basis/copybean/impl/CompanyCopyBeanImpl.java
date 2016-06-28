package en.basis.copybean.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;

import en.basis.dto.CompanyDTO;
import en.common.copybean.ICopyBean;
import en.common.copybean.impl.GridCopyBeanImpl;
import en.common.dto.DTO;
import en.common.entity.BaseEntity;
import en.common.frame.shiro.SessionUser;
import en.common.util.helper.DatetimeUtil;
import en.common.util.helper.ListUtil;
import en.common.util.helper.LongUtil;
import en.common.util.helper.StringUtil;
import en.dm.entity.Company;

public class CompanyCopyBeanImpl extends GridCopyBeanImpl implements ICopyBean{

	public Class<?> getEntityClass() {
		return Company.class;
	}

	public DTO createDTO() {
		return new CompanyDTO();
	}

	@Override
	 public void copyEntity2DTO(BaseEntity entity, DTO dto) throws Exception {
		   super.copyEntity2DTO(entity, dto);
	       Company company = (Company)entity;
	       CompanyDTO cdto = (CompanyDTO)dto;
	       if(!StringUtil.isEmpty(company.getSjgsDm())){
	    	   DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());//确定使用哪一个Entity
	    	   detachedCriteria.setProjection(Projections.property("shortName"));
	    	   detachedCriteria.add(Restrictions.eq("gsDm", company.getSjgsDm()));
	    	   detachedCriteria.add(Restrictions.eq("status", true));
	    	   List<?> list = gridDao.find(detachedCriteria);
	    	   if(!ListUtil.isEmpty(list) && list.get(0) != null){
	    		   cdto.setSjgsMc(list.get(0).toString());
	    	   }
	       }
	    }
	
	public void copyDTO2Entity(SessionUser sessionUser, BaseEntity entity, DTO dto) throws Exception {
		 Company company = (Company)entity;
	     CompanyDTO cdto = (CompanyDTO)dto;
	     boolean isdlhs = (company.getIsIndependentAccounting() == null) ? false : company.getIsIndependentAccounting();
	     if((!cdto.getIsIndependentAccounting() || !dto.getStatus()) && isdlhs)
	    	company.setBz2("changeHs");
	     super.copyDTO2Entity(sessionUser, entity, dto);
	     if(LongUtil.isNewId(cdto.getId())){
	    	 DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getEntityClass());
	    	   detachedCriteria.setProjection(Projections.max("gsDm"));
	    	   detachedCriteria.add(Restrictions.eq("sjgsDm", cdto.getSjgsDm()));
	    	   List<?> list = gridDao.find(detachedCriteria);
	    	   String gsDm = "0000";
	    	   if(ListUtil.isEmpty(list))
	    		   gsDm = cdto.getSjgsDm()+"001";
	    	   else
	    		   gsDm = gsDm+String.valueOf(Integer.valueOf(list.get(0).toString())+1);
	    	   company.setGsDm(gsDm);
	     }
       
        
    }
}
