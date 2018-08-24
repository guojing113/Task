package service;

import pojo.User;

public interface LoginService {
	/**
	 * 查询用户
	 * @param uName 用户名
	 * @return
	 */
	User queryUser(String uName);
	/**
	 * 判断用户是否存在
	 * @param name
	 * @param password
	 * @return
	 */
	boolean judgeUser(String name, String password) throws Exception;

	/**
	 * 保存token
	 * @param name
	 * @return
	 */
	String saveToken(String name) throws Exception;
}
