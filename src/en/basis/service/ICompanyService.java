package en.basis.service;

import java.util.List;

import en.common.frame.shiro.SessionUser;
import en.common.service.IGridSerivce;
import en.dm.entity.Company;


public interface ICompanyService extends IGridSerivce {

	/**
	  * 根据公司代码获取公司名称
	  * @param dm
	  * @return
	  *//*
	 public Company getGsxxByGsDm(String dm,String gsDm) throws Exception;
	 
	 *//**
	  * 根据查询条件查询公司信息
	  * @param cond
	  * @return
	  *//*
	 public List<Company> getCompanyList(SessionUser sessionUser,String cond)  throws Exception;
	 
	 *//**
	  * 获取公司树
	  * @param cond
	  * @return
	  */
	 public String getCompanyTree(SessionUser sessionUser,String cond)  throws Exception;
	 
	 /**
	  * 根据查询条件查询公司信息
	  * @param cond
	  * @return
	  */
	 public List<?> getCompanyList(SessionUser sessionUser,String cond)  throws Exception;
	 /**
	  * 根据需要获取报价公司
	  * @param sessionUser
	  * @param cond
	  * @return
	  * @throws Exception
	  *//*
	 public String getCompanyTreeForBj(SessionUser sessionUser,String cond)  throws Exception;*/
	 /**
	  * 获取核算公司
	  * @param sessionUser
	  * @param bz
	  * @return
	  * @throws Exception
	  */
	 public List<?> getHsCompanys(SessionUser sessionUser,Boolean bz)  throws Exception; 
	 /**
	  * 根据公司代码获取公司信息
	  * @param dm
	  * @param gsDm
	  * @return
	  * @throws Exception
	  */
	 public Company getGsxxByGsDm(String dm,String gsDm) throws Exception;
	/**
	 * 根据网点号获取网点信息
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public Company getGsxxByName(String name) throws Exception;

	/**
	 * 获取所有状态正常的公司信息（包括子公司和母公司）
	 * @return
	 * @throws Exception
	 */
	public List<Company> getCompaniesList() throws Exception;
}
