package en.common.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import en.common.frame.shiro.SessionUser;
import en.common.util.helper.ResultPageInit;

public interface IBaseDao {
	/**
	 * 执行hql语句
	 * @param hql
	 * @throws Exception
	 */
	public void executeHql(String hql) throws Exception;
	/**
	 * 执行sql语句
	 * @param sql
	 * @throws Exceptions
	 */
	public void executeSql(String sql) throws Exception;
	
	/**
	  * 执行有返回结果的sql语句
	  * @param clazz
	  * @param id
	  * @return
	  */
	 public List<?> executeSqlREList (String sql) throws Exception;
	 /**
	  * 根据条件返回实体list
	  * @param clazz
	  * @param condition
	  * @return
	  *//*
	 public List<?> gets(Class<?> clazz, String condition);

	*//**
	 * 增加
	 * @param obj
	 *//*
	public <T> void add(T obj);
	
	*//**
	 * 修改
	 * @param obj
	 *//*
	public <T> void update(T obj);
	*//**
	 * 增加,修改
	 * @param obj
	 */
	public <T> void save(T obj);
	
	
	/**
	 * 加载
	 * @param classz
	 * @param id
	 * @return
	 *//*
	public Object get(Class<?> classz,Serializable id);
	*//**
	 * 查询所有
	 * @param detachedCriteria
	 * @return
	 */
	public  List<?> find(DetachedCriteria detachedCriteria) throws Exception;
	
	/**
	  * 根据ID返回clazz类
	  * @param clazz
	  * @param id
	  * @return
	  */
	 public Object getById(Class<?> clazz, Long id) throws Exception;
	 /**
	  * 启用缓存查询
	  * @param clazz
	  * @param condition
	  * @return
	  */
	 public List<?> getsCacheEntityList(DetachedCriteria detachedCriteria) throws Exception;
	 
	 /**
	  * remove:只更新为N
	  * @param bill
	  * @throws DaoException
	  */
	  
	 public void remove(SessionUser sessionUser,Object bill) throws Exception;
	 
	 /**
	  * Ext模式的查询
	  * @param clazz
	  * @param condition
	  * @param start
	  * @param limit
	  * @return
	  * @throws Exception
	  */
	 public ResultPageInit findResultPage(Class<?> clazz, String condition, int start,
	            int limit) throws Exception;
	 /**
	  * 返回指定行数的记录
	  * @param detachedCriteria
	  * @param limit
	  * @return
	  * @throws Exception
	  *//*
	 public  List<?> findForLimit(DetachedCriteria detachedCriteria,int limit) throws Exception; 
	 
	 *//**
	  * HQL分页
	  * @param sessionuser
	  * @param clazz
	  * @param queryCondition
	  * @param start
	  * @param limit
	  * @return
	  * @throws Exception
	  *//*
	 public ResultPageInit findResultPageForHql(SessionUser sessionuser,Class<?> clazz,String queryCondition, int start,
	            int limit) throws Exception;*/
}
