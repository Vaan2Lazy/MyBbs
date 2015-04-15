package cn.suse.bbs.service;

import cn.suse.bbs.base.DaoSupport;
import cn.suse.bbs.domain.User;

public interface UserService extends DaoSupport<User> {

	/**
	 * 根据登录名与密码查询用户
	 * 
	 * @param loginName
	 * @param password
	 *            明文密码
	 * @return
	 */
	User findByLoginNameAndPassword(String loginName, String password);

}
