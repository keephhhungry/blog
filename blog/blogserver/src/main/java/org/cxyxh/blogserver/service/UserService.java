package org.cxyxh.blogserver.service;

import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.model.Role;
import org.cxyxh.blogserver.model.User;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.service
 * @ClassName: UserService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 0:12
 * @Version: 1.0
 */
public interface UserService {


	/**
	 * 分页查询用户信息
	 * @param page 页码
	 * @param size 每页显示数量
	 * @param user 查询条件
	 * @param createDateScope 创建时间区间
	 * @return
	 */
    RespPageBean getUserByPage(Integer page, Integer size, User user, Date[] createDateScope);

    /**
     * 添加用户 用户对象
     * @param user
     * @return
     */
    Integer addUser(User user);

	/**
	 * 根据ID删除用户
	 * @param iuser 用户ID
	 * @return
	 */
	Integer deleteUserById(Integer iuser);

	/**
	 * 根据ID数组，批量删除用户
	 * @param ids 用户ID数组
	 * @return
	 */
	Integer deleteUserByIds(Integer[] ids);

	/**
	 * 根据用户ID，重置用户密码
	 * @param iuser 用户ID
	 * @return
	 */
	Integer resetPasswordById(Integer iuser);

	/**
	 * 根据用户ID，修改用户信息
	 * @param user 用户对象
	 * @return
	 */
	Integer updateUser(User user);

	/**
	 * 获取所有的角色
	 * @return
	 */
	List<Role> getAllRole();
}
