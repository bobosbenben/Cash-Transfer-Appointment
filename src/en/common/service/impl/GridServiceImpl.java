package en.common.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import en.common.copybean.ICopyBean;
import en.common.dao.IGridDao;
import en.common.dto.DTO;
import en.common.entity.BaseEntity;
import en.common.frame.shiro.SessionUser;
import en.common.util.helper.JacksonsUtil;
import en.common.util.helper.ListUtil;
import en.common.util.helper.LongUtil;
import en.common.util.helper.ResultEntity;
import en.common.util.helper.ResultPageInit;
import en.common.util.helper.StringUtil;

public abstract class GridServiceImpl extends BaseServiceImpl{
	
	protected ResultEntity createResultEntity(){
		return new ResultEntity();
	}
	protected String selects="";
	
	protected abstract ICopyBean getCopyBean() ;
	
	@Resource
	protected IGridDao gridDao; //注入gridDao

	/**
	 * 处理增删改查
	 * @param obj
	 */
	public  void save(Object obj) {
		gridDao.save(obj);		
	}
	/**
	 * 批量删除
	 * @param selectedIds
	 * @return
	 * @throws Exception
	 */
	public void delEntityByIds(String selectedIds) throws Exception {
		if(!StringUtil.isEmpty(selectedIds)){
			String hql = "DELETE "+getCopyBean().getEntityClass().getName()+" AS o WHERE o.id in ("+selectedIds+")";
			gridDao.executeHql(hql);
		}
	}
	
	
	public ResultEntity findPageResult(SessionUser sessionuser,String condstr, int start, int limit)
			throws Exception {
		ResultEntity	resultEntity = createResultEntity();
		condstr = dealTrueCondForSql(sessionuser, condstr, true);
		try{
		ResultPageInit page =  gridDao.findResultPage(getCopyBean().getEntityClass(), condstr,
				start, limit);
		String result = dealGridPage(sessionuser,page);
		resultEntity.setResultType(0);
		resultEntity.setResultDesc(result);
		//return result;
		}catch (Exception e) {
			 e.printStackTrace();
			 String err = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
			 resultEntity.setResultType(-3);
			resultEntity.setResultDesc("出现异常，代码为-3,错误信息为:"+err+"; 请联系系统管理员");
		}
		//System.out.println("===================="+resultEntity.getResultType());
		return resultEntity;
	}
	
	/*public <T> void add(T obj) {
		gridDao.add(obj);
		
	}

	public <T> void update(T obj) {
		gridDao.update(obj);
		
	}

	

	public <T> void delete(T obj) {
		gridDao.delete(obj);		
	}

	public <T> T get(Class<T> classz, Serializable id) {
		return gridDao.get(classz, id);
	}

	public List find(DetachedCriteria detachedCriteria) throws Exception{
		return gridDao.find(detachedCriteria);
	}
	
	 */
	
		public void updateBill(SessionUser sessionuser, String data) throws Exception{
			
			DTO dto = getCopyBean().createDTO();
			Date jacksonStart = new Date();
			List<Object> result = JacksonsUtil.me().json2List(data, dto);
		    updateDataByList(sessionuser, result);

			Date jacksonEnd = new Date();
			System.out.println("更新成功花费时间 :"
					+ (jacksonEnd.getTime() - jacksonStart.getTime()) + "ms");
		}
		//处理数据
		protected void updateDataByList(SessionUser sessionuser, List<Object> result)
				throws Exception {
			if (ListUtil.isEmpty(result))
				return;
			ICopyBean cb = getCopyBean();

			for (int i = 0; i < result.size(); i++) {
				DTO dto = (DTO) result.get(i);
				BaseEntity entity;
				checkValidBeforeUpdate(sessionuser,dto);
				if(LongUtil.isNewId(dto.getId())){
					//新增
				  try {
						entity = (BaseEntity) cb.getEntityClass().newInstance();
						cb.copyDTO2Entity(sessionuser, entity, dto);
					} catch (Exception e) {
						e.printStackTrace();
						String err = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
						throw new Exception("保存数据异常,"+err);
					}
					checkValid(sessionuser, entity);
					
					//gridDao.add(entity);
				}else{
					//更新
				 try {
						entity = (BaseEntity) gridDao.getById(cb.getEntityClass(), dto
								.getId());
						cb.copyDTO2Entity(sessionuser, entity, dto);
					} catch (Exception e) {
						e.printStackTrace();
						String err = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
						throw new Exception("copybean数据异常,"+err);
					}
					checkValid(sessionuser, entity);
				}
				 
				gridDao.save(entity);
			}
		}
		//copy 数据前对DTO的检测
		
		public void checkValidBeforeUpdate(SessionUser sessionuser, DTO billDTO) throws Exception{};
		
		//copy 数据后对entity的检测
				protected void checkValid(SessionUser sessionuser, BaseEntity entity)
						throws Exception {

					}
		
		public void updateBillByDTO(SessionUser sessionuser, DTO dto) throws Exception {
			ResultEntity re = createResultEntity();
			BaseEntity bill = null;
			ICopyBean cb = getCopyBean();
		    if (LongUtil.isNewId(dto.getId())) {
					bill = (BaseEntity) cb.getEntityClass().newInstance();
				} else {
					bill = (BaseEntity)gridDao.getById(cb.getEntityClass(), dto.getId());
				}
				cb.copyDTO2Entity(sessionuser, bill, dto);
				gridDao.save(bill);
				re.setResultType(0);
		}
		
		
		
	
	
	 //将查询结果赋值给dto对象
	 public DTO getDTOByBill(SessionUser  sessionuser,Object en) throws Exception{

			ICopyBean cb = getCopyBean();
			DTO dto = cb.createDTO();
			try {
				cb.copyEntity2DTO((BaseEntity)en, dto);
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("值拷贝错误");
			}
			return dto;

		}
	 /**
	 public String getJsonDTOById(SessionUser sessionuser,String selectId) throws Exception{
		 BaseEntity baseEn =  (BaseEntity)gridDao.getById(getCopyBean().getEntityClass(), Long.parseLong(selectId));
			ResultJsonUtil ext = new ResultJsonUtil(1L);
			ext.addContainers(JacksonsUtil.me().readAsString(getDTOByBill(sessionuser,baseEn)));
			String resultMsg = ext.getJsonStr().replace("\"", "'");
			System.out.println("load数据:"+resultMsg);
			return resultMsg;
	 }
	 
		
		
		public void subRepeatDWata(DetachedCriteria detachedCriteria, String errormssage) throws Exception{
			List list = gridDao.find(detachedCriteria);
			if (!ListUtil.isEmpty(list)) {
				throw new Exception(errormssage);
			}
		}
		*/
	 
	 
		public void remove(SessionUser sessionuser,String selectedId) throws Exception{
			if(!StringUtil.isEmpty(selectedId)){
				String[] strs = selectedId.split(",");
				for(String str :strs){
					BaseEntity obj = (BaseEntity)gridDao.getById(getCopyBean().getEntityClass(), Long.parseLong(str));
					if(obj !=null){ //System.out.println("找到了一个对象：status:"+obj.getStatus()+"  Id:"+obj.getId());
						gridDao.remove(sessionuser, obj);}
					
				}
			}
			
		}
		/**
		 * 使用hql查询分页
		 * @param sessionuser
		 * @param cond
		 * @param start
		 * @param limit
		 * @return
		 * @throws Exception
		 *//*
		public ResultEntity findPageResultForHql(SessionUser sessionuser, String cond,int start, int limit) throws Exception {
			ResultEntity	resultEntity = createResultEntity();
			try{
			ResultPageInit page =  gridDao.findResultPageForHql(sessionuser, getCopyBean().getEntityClass(), cond, start, limit);
			String result = dealGridPage(sessionuser,page);
			resultEntity.setResultType(0);
			resultEntity.setResultDesc(result);
			//return result;
			}catch (Exception e) {
				 e.printStackTrace();
				 String err = e.getCause() == null ? e.getMessage() : e.getCause().getMessage();
				 resultEntity.setResultType(-3);
				resultEntity.setResultDesc("出现异常，代码为-3,错误信息为:"+err+"; 请联系系统管理员");
			}
			System.out.println("===================="+resultEntity.getResultType());
			
			return resultEntity;
		}*/
		
		public List<?> findEntityByCond(Class<?> classz, String cond, SessionUser sessionuser) throws Exception {
			DetachedCriteria detachedCriteria = DetachedCriteria.forClass(classz);
			if(StringUtil.isEmpty(cond)){
				detachedCriteria.add(Restrictions.like("gsDm", sessionuser.getBigGsdm()+"%"));
				detachedCriteria.add(Restrictions.eq("status", true));
			}else{
				if(cond.indexOf("gsdm")<=0){
					detachedCriteria.add(Restrictions.like("gsDm", sessionuser.getBigGsdm()+"%"));
				}
				detachedCriteria.add(Restrictions.sqlRestriction(" 1=1 "+cond));
			}
			
			return gridDao.find(detachedCriteria);
			 
		}
}
