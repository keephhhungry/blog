package org.cxyxh.blogshow.service.impl;

import org.cxyxh.blogshow.mapper.UserMapper;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.UserService;
import org.cxyxh.blogshow.utils.DefaultParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service.impl
 * @ClassName: UserServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/21 23:08
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;


	@Override
	public Integer register(User user) {
		//五个正则判断
		boolean usernameMatches = user.getUsername().matches(DefaultParams.USERNAME_PATTERN);
		if (!usernameMatches) {
			return -2;
		}
		boolean nameMatches = user.getName().matches(DefaultParams.NAME_PATTERN);
		if (!usernameMatches) {
			return -2;
		}
		boolean passwordMatches = user.getPassword().matches(DefaultParams.PASSWORD_PATTERN);
		if (!usernameMatches) {
			return -2;
		}
		boolean emailMatches = user.getEmail().matches(DefaultParams.EMAIL_PATTERN);
		if (!usernameMatches) {
			return -2;
		}
		boolean telephoneMatches = user.getTelephone().matches(DefaultParams.TELEPHONE_PATTERN);
		if (!usernameMatches) {
			return -2;
		}
		//判断用户名重复问题
		Integer num = userMapper.findUserByUsername(user.getUsername());
		if (num != 0) {
			return -1;
		}
		//密码加密


		user.setRemark("");
		user.setEnabled(true);
		user.setGmtCreate(new Date());
		user.setGmtModified(new Date());
		//插入数据
		return userMapper.register(user);
	}
}
