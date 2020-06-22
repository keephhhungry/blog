package org.cxyxh.blogshow.mapper;

import org.apache.ibatis.annotations.Param;
import org.cxyxh.blogshow.model.User;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.mapper
 * @ClassName: LoginMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/22 21:30
 * @Version: 1.0
 */
public interface LoginMapper {

	/**
	 * 登录
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	User login(@Param("username") String username, @Param("password") String password);
}
