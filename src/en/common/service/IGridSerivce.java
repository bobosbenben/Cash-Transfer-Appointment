package en.common.service;

import java.util.List;

import en.common.dto.DTO;
import en.common.frame.shiro.SessionUser;
import en.common.util.helper.ResultEntity;

public interface IGridSerivce extends IBaseService {

	/**
	 * 同时删除多条信息
	 * 
	 * @param selectedIds
	 * @return
	 * @throws Exception
	 */
	public void delEntityByIds(String selectedIds) throws Exception;

	/**
	 * 根据json解析为javabean对象
	 * 
//	 * @param billActor
	 * @param data
	 */
	public void updateBill(SessionUser sessionuser, String data)
			throws Exception;

	/**
	 * 根据实体类处理保存
	 * 
//	 * @param billActor
//	 * @param data
	 */
	public void updateBillByDTO(SessionUser sessionuser, DTO dto)
			throws Exception;

	/**
	 * 保存前检测
	 * 
	 * @param sessionuser
	 * @param billDTO
	 * @throws Exception
	 */
	public void checkValidBeforeUpdate(SessionUser sessionuser, DTO billDTO)
			throws Exception;

	/**
	 * 保存前检测
	 * 
	 * @param sessionuser
	 * @param billDTO
	 * @throws Exception
	 */
	/*
	 * public String getJsonDTOById(SessionUser sessionuser,String
	 * selectedId)throws Exception;
	 */
	/**
	 * 加载
	 * 
	 * @param classz
//	 * @param id
	 * @return
	 */
	
	 public List<?> findEntityByCond(Class<?> classz,String cond,SessionUser sessionuser) throws Exception;
	/**
	 * remove
	 * 
	 * @param sessionuser
	 * @param selectedId
	 * @throws Exception
	 */
	public void remove(SessionUser sessionuser, String selectedId)
			throws Exception;

	/**
	 * hql查询
	 * 
	 * @param sessionuser
	 * @param cond
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	/*
	 * public ResultEntity findPageResultForHql(SessionUser sessionuser, String
	 * cond,int start, int limit) throws Exception ;
	 */
	/**
	 * 查询
	 * 
	 * @param sessionuser
	 * @param condstr
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public ResultEntity findPageResult(SessionUser sessionuser, String condstr,
			int start, int limit) throws Exception;
}
