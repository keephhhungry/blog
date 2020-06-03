package org.cxyxh.blogserver.mapper;

import org.apache.ibatis.annotations.Param;
import org.cxyxh.blogserver.model.Role;
import org.cxyxh.blogserver.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.mapper
 * @ClassName: UserMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 10:12
 * @Version: 1.0
 */
public interface UserMapper {

	/**
	 * 根据账号查询用户
	 *
	 * @param username 用户账号
	 * @return 用户对象
	 */
	User loadUserByUsername(String username);

	/**
	 * 登录成功后根据用户的ID 查询用户的角色
	 *
	 * @param iuser 用户ID
	 * @return
	 */
	List<Role> getUserRolesById(Integer iuser);

	/**
	 * 分页查询用户信息
	 *
	 * @param page            页码
	 * @param size            每页显示数量
	 * @param user            查询条件
	 * @param createDateScope 创建时间区间
	 * @return
	 */
	List<User> getUserByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("user") User user, @Param("createDateScope") Date[] createDateScope);

	/**
	 * 根据条件查询符合数据的条数
	 *
	 * @param user            条件
	 * @param createDateScope 创建时间区间
	 * @return 总数据条数
	 */
	Long getTotal(@Param("user") User user, @Param("createDateScope") Date[] createDateScope);

	/**
	 * 添加用户 用户对象
	 *
	 * @param user
	 * @return
	 */
	Integer addUser(User user);

	/**
	 * 根据ID删除用户
	 *
	 * @param iuser 用户ID
	 * @return
	 */
	Integer deleteUserById(Integer iuser);

	/**
	 * 根据ID数组，批量删除用户
	 *
	 * @param ids 用户ID数组
	 * @return
	 */
	Integer deleteUserByIds(@Param("ids") Integer[] ids);

	/**
	 * 根据用户ID，重置用户密码
	 *
	 * @param iuser 用户ID
	 * @return
	 */
	Integer resetPasswordById(@Param("iuser") Integer iuser, @Param("defaultPassword") String defaultPassword);

	/**
	 * 根据用户ID，修改用户信息
	 *
	 * @param user 用户对象
	 * @return
	 */
	Integer updateUser(User user);

	/**
	 * 获取所有的角色
	 *
	 * @return
	 */
	List<Role> getAllRole();
}
