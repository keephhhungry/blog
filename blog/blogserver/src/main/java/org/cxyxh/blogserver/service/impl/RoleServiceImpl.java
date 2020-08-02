package org.cxyxh.blogserver.service.impl;

import org.cxyxh.blogserver.mapper.RoleMapper;
import org.cxyxh.blogserver.model.Role;
import org.cxyxh.blogserver.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.service.impl
 * @ClassName: RoleService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 10:30
 * @Version: 1.0
 */
@Service
public class RoleServiceImpl implements RoleService {

	private final static Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	RoleMapper roleMapper;

	/**
	 * 获取所有的角色
	 *
	 * @param keyword 搜索关键字
	 * @return
	 */
	@Override
	public List<Role> getAllRole(String keyword) {
		return roleMapper.getAllRole(keyword);
	}

	/**
	 * 添加角色
	 *
	 * @param role 角色对象
	 * @return
	 */
	@Override
	public Integer addRole(Role role) {
		role.setGmtCreate(new Date());
		role.setGmtModified(new Date());
		String name = role.getName();
		if(!name.startsWith("ROLE_")){
			name = "ROLE_"+name;
			role.setName(name);
		}
		return roleMapper.addRole(role);
	}

	/**
	 * 删除角色
	 *
	 * @param irole 角色ID
	 * @return
	 */
	@Override
	public Integer deleteRole(Integer irole) {
		return roleMapper.deleteRole(irole);
	}

	/**
	 * 更新角色
	 *
	 * @param role 角色对象
	 * @return
	 */
	@Override
	public Integer updateRole(Role role) {
		role.setGmtModified(new Date());
		String name = role.getName();
		if(!name.startsWith("ROLE_")){
			name = "ROLE_"+name;
			role.setName(name);
		}
		return roleMapper.updateRole(role);
	}

}
