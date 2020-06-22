package org.cxyxh.blogshow.service;

import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.model.User;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service
 * @ClassName: LoginService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/22 21:30
 * @Version: 1.0
 */
public interface LoginService {

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	User login(String username, String password);
}
