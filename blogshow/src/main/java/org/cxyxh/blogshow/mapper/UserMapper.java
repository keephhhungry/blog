package org.cxyxh.blogshow.mapper;

import org.cxyxh.blogshow.model.User;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.mapper
 * @ClassName: UserMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/21 23:08
 * @Version: 1.0
 */
public interface UserMapper {

	/**
	 * 根据用户名查找用户数量
	 *
	 * @param username 用户名
	 * @return
	 */
	Integer findUserCountByUsername(String username);


	/**
	 * 根据用户名查找用户数量
	 *
	 * @param username 用户名
	 * @return
	 */
	User findUserByUsername(String username);

	/**
	 * 注册用户
	 *
	 * @param user 用户对象
	 * @return
	 */
	Integer register(User user);

	/**
	 * 修改用户
	 *
	 * @param user
	 * @return
	 */
	Integer updateUser(User user);
}
