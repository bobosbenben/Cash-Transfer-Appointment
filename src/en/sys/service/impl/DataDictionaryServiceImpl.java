package en.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import en.common.copybean.ICopyBean;
import en.common.dto.DTO;
import en.common.entity.Dmxx;
import en.common.frame.shiro.SessionUser;
import en.common.service.impl.GridServiceImpl;
import en.common.util.helper.ListUtil;
import en.common.util.helper.LongUtil;
import en.common.util.helper.StringUtil;
import en.sys.copybean.impl.DataDictionaryCopyBeanImpl;
import en.sys.dto.DataDictionaryDTO;
import en.sys.entity.DataDictionary;
import en.sys.service.IDataDictionaryService;
@Service
public class DataDictionaryServiceImpl extends GridServiceImpl implements
		IDataDictionaryService {

	@Override
	protected ICopyBean getCopyBean() {
		DataDictionaryCopyBeanImpl cp = new DataDictionaryCopyBeanImpl();
		cp.setGridDao(gridDao);
		return cp ;
	}
	
	 public void updateBill(SessionUser sessionuser, String data) throws Exception{
		 
			super.updateBill(sessionuser, data);
			
		}

	public List<?> getListByDataType(SessionUser sessionuser,String dataType, String defaultString, String cond)
			throws Exception {
		List<?> list ;
		String  sql ="";
		String sqltemp = "";
		if(StringUtil.isEmpty(cond)){
			String gsdmtemp =  sessionuser.getGsdm().equals(sessionuser.getBigGsdm()) ? sessionuser.getBigGsdm() : sessionuser.getHsgsDm() ;
			sql = sql+" 1=1  and data_type = '"+dataType+"' and gsdm like'"+gsdmtemp+"%' and status = 'true' order by data_type";
			sqltemp = sqltemp+" 1=1 and data_type = '"+dataType+"' and gsdm like'"+sessionuser.getBigGsdm()+"%' and status = 'true' order by data_type";
		}else{
			if(cond.indexOf("gsdm") <0){
				sql = sql+" and data_type = '"+dataType+"'  and gsdm like'"+sessionuser.getHsgsDm()+"%'";
				sqltemp = sqltemp+" and data_type = '"+dataType+"'  and gsdm like'"+sessionuser.getBigGsdm()+"%'";
			}
			sql= cond + sql;
			sqltemp = cond + sqltemp;
		}
		  DetachedCriteria detachedCriteria = DetachedCriteria.forClass(getCopyBean().getEntityClass());
		  detachedCriteria.add(Restrictions.sqlRestriction(sql));
		  list = gridDao.getsCacheEntityList(detachedCriteria);
		  if(ListUtil.isEmpty(list)){
			  DetachedCriteria detachedCriteria1 = DetachedCriteria.forClass(getCopyBean().getEntityClass());
			  detachedCriteria1.add(Restrictions.sqlRestriction(sqltemp));
			  list = gridDao.getsCacheEntityList(detachedCriteria1);
			  if(ListUtil.isEmpty(list)){
				  if(defaultString != null){
					List<DataDictionary> newlist = new ArrayList<DataDictionary>();
					DataDictionary dmxx = new DataDictionary();
					dmxx.setData(defaultString);
				   newlist.add(dmxx);
			  }
		    }
		  }
		return list;
	}
	 
	 public void checkValidBeforeUpdate(SessionUser sessionuser, DTO billDTO) throws Exception{
			DataDictionaryDTO dto = (DataDictionaryDTO) billDTO;
			DetachedCriteria detachedCriteria = DetachedCriteria
					.forClass(getCopyBean().getEntityClass());
			detachedCriteria.setProjection(Projections.rowCount());
			detachedCriteria.add(Restrictions.eq("gsDm", dto.getGsDm()));
			detachedCriteria.add(Restrictions.eq("dataType", dto.getDataType()));
			detachedCriteria.add(Restrictions.eq("data", dto.getData()));
			if(!LongUtil.isNewId(dto.getId()))
			   detachedCriteria.add(Restrictions.ne("id", dto.getId()));
			List<?> list = gridDao.find(detachedCriteria);
			if (!ListUtil.isEmpty(list) && LongUtil.getDoubleByObject(list.get(0)) > 0) {
				throw new Exception("已有此辅助信息:"+dto.getData()+",如没有找到可查询未启用的数据");
			}
		};

	public List<?> getListDataType(SessionUser sessionuser) throws Exception {
			String sql = "select distinct data_type from xt_datadictionary where status='true' and gsdm like '"
					+ sessionuser.getBigGsdm() + "%' order by data_type";
			List<?> list = gridDao.executeSqlREList(sql);
			List<Dmxx> newList = new ArrayList<Dmxx>();
			if(!ListUtil.isEmpty(list)){
				for(int i=0,size = list.size();i<size;i++){
					Dmxx dmxx = new Dmxx(); 
					Object temp = list.get(i);
					dmxx.setName(temp.toString());
					newList.add(dmxx);
				}
			}
			return newList;
	}
}
