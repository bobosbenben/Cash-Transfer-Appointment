package en.sys.service;

import java.util.List;

import en.common.frame.shiro.SessionUser;
import en.common.service.IGridSerivce;

public interface IDataDictionaryService extends IGridSerivce{

	/**
	 * 根据数据字典数据类型返回数据list
	 * @param sessionuser
	 * @param dataType
	 * @param defaultString
	 * @param cond
	 * @return
	 */
	public List<?> getListByDataType(SessionUser sessionuser,String dataType,String defaultString,String cond) throws Exception;
	

	/**
	 * 根据数据字典数据类型返回数据list
	 * @param sessionuser
	 * @param dataType
	 * @param defaultString
	 * @param cond
	 * @return
	 */
	public List<?> getListDataType(SessionUser sessionuser) throws Exception;
}
