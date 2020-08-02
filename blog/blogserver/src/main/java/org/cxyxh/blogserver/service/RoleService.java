package org.cxyxh.blogserver.service;

import org.cxyxh.blogserver.model.Role;

import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.service
 * @ClassName: RoleService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 10:31
 * @Version: 1.0
 */
public interface RoleService {


	/**
	 * 获取所有的角色
	 *
	 * @param keyword 搜索关键字
	 * @return
	 */
	List<Role> getAllRole(String keyword);

	/**
	 * 添加角色
	 *
	 * @param role 角色对象
	 * @return
	 */
	Integer addRole(Role role);

	/**
	 * 删除角色
	 *
	 * @param irole 角色ID
	 * @return
	 */
	Integer deleteRole(Integer irole);

	/**
	 * 更新角色
	 *
	 * @param role 角色对象
	 * @return
	 */
	Integer updateRole(Role role);

}
