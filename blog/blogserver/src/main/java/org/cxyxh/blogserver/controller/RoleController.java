package org.cxyxh.blogserver.controller;

import org.cxyxh.blogserver.model.Menu;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.Role;
import org.cxyxh.blogserver.service.MenuService;
import org.cxyxh.blogserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.controller
 * @ClassName: RoleController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 0:36
 * @Version: 1.0
 */
@RestController
@RequestMapping("/permission/role")
public class RoleController {

	@Autowired
	RoleService roleService;

	@Autowired
	MenuService menuService;

	/**
	 * 获取所有的角色
	 *
	 * @return
	 */
	@GetMapping("/")
	public RespBean getAllRole(String keyword) {
		List<Role> roles = roleService.getAllRole(keyword);
		return RespBean.ok("", roles);
	}

	/**
	 * 获取全部的菜单
	 *
	 * @return
	 */
	@GetMapping("/menu")
	public RespBean getAllMenu() {
		List<Menu> menus = menuService.getAllMenu("");
		return RespBean.ok("", menus);
	}

	/**
	 * 添加角色
	 *
	 * @param role 角色对象
	 * @return
	 */
	@PostMapping("/")
	public RespBean addRole(@RequestBody Role role) {
		if (roleService.addRole(role) == 1) {
			return RespBean.ok("添加成功");
		} else {
			return RespBean.ok("添加失败");
		}
	}

	/**
	 * 删除角色
	 *
	 * @param irole 角色ID
	 * @return
	 */
	@DeleteMapping("/{irole}")
	public RespBean deleteRole(@PathVariable Integer irole) {
		if (roleService.deleteRole(irole) == 1) {
			return RespBean.ok("删除成功");
		} else {
			return RespBean.ok("删除失败");
		}
	}

	/**
	 * 更新角色
	 *
	 * @param role 角色对象
	 * @return
	 */
	@PutMapping("/")
	public RespBean updateRole(@RequestBody Role role) {
		if (roleService.updateRole(role) == 1) {
			return RespBean.ok("更新成功");
		} else {
			return RespBean.ok("更新失败");
		}
	}

}
