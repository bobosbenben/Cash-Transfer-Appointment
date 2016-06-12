package en.basis.service;

import java.util.List;

import en.basisc.entity.User;
import en.common.frame.shiro.SessionUser;
import en.common.service.IGridSerivce;

public interface IUserService extends IGridSerivce{

	/**
	 * 根据注记码获取用户 信息
	 * @param sessionuser
	 * @param zjm
	 * @return
	 * @throws Exception
	 */
	public List<?> getUserByZjm(SessionUser sessionuser,String zjm) throws Exception;
	/**
	 * 根据ID获取人事信息
	 * @param selected
	 * @return
	 * @throws Exception
	 */
	public String getHRInfoById(String selected) throws Exception;

	/**
	 * 根据注记码获取用户 信息
	 * @param zjm
	 * @return
	 * @throws Exception
	 */
	public List<?> getUserByZjm(String zjm) throws Exception;
	/**
	 * 根据注记码获取用户 信息
	 * @param selected
	 * @return
	 * @throws Exception
	 */
	public User getUserById(String selected) throws Exception;
}
