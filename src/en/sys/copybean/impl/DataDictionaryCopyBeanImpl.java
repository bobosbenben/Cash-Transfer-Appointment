package en.sys.copybean.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import en.common.copybean.ICopyBean;
import en.common.copybean.impl.GridCopyBeanImpl;
import en.common.dto.DTO;
import en.common.entity.BaseEntity;
import en.common.util.helper.ListUtil;
import en.common.util.helper.StringUtil;
import en.dm.entity.Company;
import en.sys.dto.DataDictionaryDTO;
import en.sys.entity.DataDictionary;

public class DataDictionaryCopyBeanImpl extends GridCopyBeanImpl implements ICopyBean{

	@Override
	public Class<?> getEntityClass() {
		return DataDictionary.class;
	}

	@Override
	public DTO createDTO() {
		return new DataDictionaryDTO();
	}

	
	@Override
	public void copyEntity2DTO(BaseEntity entity, DTO dto) throws Exception {
		DataDictionaryDTO ddo = (DataDictionaryDTO) dto;
		DataDictionary myentity = (DataDictionary) entity;
		super.copyEntity2DTO(entity, dto);

		if (!StringUtil.isEmpty(myentity.getGsDm())) {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Company.class);
			detachedCriteria.add(Restrictions.sqlRestriction(" gsdm = '"+myentity.getGsDm()+"' and status = 'true'"));
		   List<?> list =gridDao.find(detachedCriteria);
		   if(!ListUtil.isEmpty(list)){
			   Company company  = (Company)list.get(0);
			   ddo.setGsMc(company.getShortName());
		   }
		}
	}
}
