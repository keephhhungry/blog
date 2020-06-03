package org.cxyxh.blogserver.mapper;

import org.apache.ibatis.annotations.Param;
import org.cxyxh.blogserver.model.Role;

import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.mapper
 * @ClassName: RoleMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 10:24
 * @Version: 1.0
 */
public interface RoleMapper {

	/**
	 * 获取所有的角色
	 *
	 * @param keyword 搜索关键字
	 * @return
	 */
	List<Role> getAllRole(@Param("keyword") String keyword);

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
