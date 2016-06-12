package en.common.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import en.common.entity.BaseEntity;
import en.common.frame.shiro.SessionUser;
import en.common.util.helper.DatetimeUtil;
import en.common.util.helper.ResultPageInit;
import en.common.util.helper.StringUtil;

public abstract class BaseDaoImpl {

	@Resource
	public SessionFactory sessionFactory;

	public Session getSession() {

		return sessionFactory.getCurrentSession();
	}

	public void save(Object  obj) {
		getSession().saveOrUpdate(obj);
	}
	
	public void executeHql(final String hql) throws Exception{
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}
	
	public List<?> find(DetachedCriteria detachedCriteria) throws Exception {

		Criteria criteria = detachedCriteria
				.getExecutableCriteria(getSession());

		return criteria.list();
	}
	
	public void executeSql(String sql) throws Exception{ 
		
		Query query = getSession().createSQLQuery(sql); 
		query.executeUpdate(); 
	}
	
	public List<?> executeSqlREList (String sql) throws Exception{ 
		Query query = getSession().createSQLQuery(sql); 
		return query.list(); 
	}
	/*
	 * public <T> void add(T obj) { getSession().save(obj); }
	 *
	 * public <T> void update(T obj) {
	 *
	 * getSession().update(obj); }
	 *
	 *
	 *
	 * public <T> void delete(T obj) {
	 *
	 * getSession().delete(obj); }
	 *
	 * public Object get(Class<?> classz, Serializable id) {
	 *
	 * return getSession().get(classz, id); }
	 *
	 *
	 *
	 * public List<?> findForLimit(DetachedCriteria detachedCriteria,int limit)
	 * throws Exception{ Criteria criteria =
	 * detachedCriteria.getExecutableCriteria(getSession());
	 * criteria.setMaxResults(limit); return criteria.list(); }
	 *
	 *
	 *
	 *
	 *
	 * public List<?> gets(Class<?> clazz, String condition) { String
	 * queryClassName; if (clazz == null) queryClassName =
	 * this.getClass().getName(); else queryClassName = clazz.getName();
	 *
	 * if (condition.indexOf("status") < 0) { condition = " & status<>'0' " +
	 * condition; } String hqlCondition =
	 * HibernateCondition.getRealCondition(condition); List<?> results = null;
	 * try { Query query = getSession().createQuery( "From " + queryClassName +
	 * " o where 0=0 " + hqlCondition); results = query.list(); } catch
	 * (Exception e) { e.printStackTrace(); } return results; }
	 * */
	  public void remove(SessionUser su,final Object entity) throws Exception {
		  BaseEntity en = (BaseEntity) entity;

		  //System.out.println("修改前：status: "+en.getStatus()+"  修改人代码："+en.getXgrDm());

		  en.setStatus(false);
		  en.setXgrDm(su.getId());
		  en.setXgrq(DatetimeUtil.getCurrentDateTimeString());
		  getSession().update(en);
	  
	  }
	  
	 
	 public Object getById(Class<?> clazz, Long id) throws Exception{ 
		return	  getSession().get(clazz, id); 
	 }
	 
	 public List<?> getsCacheEntityList(DetachedCriteria detachedCriteria)throws Exception{ 
		 Criteria criteria =detachedCriteria.getExecutableCriteria(getSession());
	     criteria.setCacheable(true); 
	     return criteria.list(); 
	     }
	  public ResultPageInit findResultPage(Class<?> clazz, String condition,int start, int limit) throws Exception{ 
		  ResultPageInit page = new ResultPageInit() ;
	 /**
	 * criteria:Restrictions 
	 * eq : = all Eq:利用Map来进行多个等于的限制 
	 * ne : <> 
	 * gt : > 
	 * ge : >= 
	 * lt : < 
	 * le : <= 
	 * between: BETWEEN 
	 * like : LIKE 
	 * in : in 
	 * and: and 
	 * or : or
	 * sqlRestriction :增加sql限制
	 * 排序 detachedCriteria.addOrder(Order.desc("fsrq")); 
	 * 
	 */
	  //search 
	 Criteria criteria = getSession().createCriteria(clazz);
	  if(!StringUtil.isEmpty(condition)){ 
		  if(condition.indexOf("1=1") < 0)
	           condition = " 1=1 "+condition; //where 1=1 and *，其实是方便后边加and
	   criteria.add(Restrictions.sqlRestriction(condition));
	   }
	   criteria.setProjection(Projections.rowCount()); 
	   Long totalcount =(Long)criteria.uniqueResult(); 
	   if(totalcount > 0){
		   page.setRowCount(totalcount); 
		   //清除投影 
		   criteria.setProjection(null);
		   criteria.setFirstResult(start);
		   criteria.setMaxResults(limit);
	      List<?> result = criteria.list();
	      //查询得到结果集
	      page.setResult(result);
	    }
	      return page;
	  }
	  
	 /* public ResultPageInit findResultPageForHql(SessionUser
	 * sessionuser,Class<?> clazz,String queryCondition, int start, int limit)
	 * throws Exception{
	 * 
	 * ResultPageInit page = new ResultPageInit() ; Query q = null; if
	 * (queryCondition.indexOf("status") < 0) { queryCondition =
	 * " & status =true " + queryCondition; } String hqlCondition =
	 * HibernateCondition .getRealCondition(queryCondition); String query =
	 * "From " + clazz.getName() + " o where 0=0 " + hqlCondition; String
	 * countHql = "select count(*) "+query;
	 * 
	 * Object rowCount = getSession().createQuery(countHql).uniqueResult();
	 * //totalelements = q.list().size(); //page.setRowCount(rowCount)
	 * //q.setCacheable(true); 数据有变量 所以不执行缓存 // 查寻总记录数 if(rowCount == null ||
	 * (Long)rowCount <= 0) page.setRowCount(0L); else{
	 * page.setRowCount((Long)rowCount); q =
	 * getSession().createQuery(query).setFirstResult
	 * (start).setMaxResults(limit); page.setResult(q.list());
	 * //page.setRowCount(rowCount) } return page; };
	 */
}
