package org.cxyxh.blogshow.service.impl;

import org.cxyxh.blogshow.mapper.LoginMapper;
import org.cxyxh.blogshow.mapper.UserMapper;
import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service.impl
 * @ClassName: LoginServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/22 21:30
 * @Version: 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginMapper loginMapper;

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public User login(String username, String password) {
		return loginMapper.login(username,password);
	}
}
