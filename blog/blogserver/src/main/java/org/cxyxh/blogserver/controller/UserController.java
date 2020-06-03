package org.cxyxh.blogserver.controller;

import org.apache.ibatis.annotations.Delete;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.model.Role;
import org.cxyxh.blogserver.model.User;
import org.cxyxh.blogserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.controller
 * @ClassName: UserController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 0:36
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user/user")
public class UserController {

	@Autowired
	UserService userService;

	/**
	 * 分页查询用户信息
	 *
	 * @param page            页码
	 * @param size            每页显示数量
	 * @param user            查询条件
	 * @param createDateScope 创建时间区间
	 * @return
	 */
	@GetMapping("/")
	public RespPageBean getUserByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, User user, Date[] createDateScope) {
		return userService.getUserByPage(page, size, user, createDateScope);
	}

	/**
	 * 添加用户 用户对象
	 *
	 * @param user
	 * @return
	 */
	@PostMapping("/")
	public RespBean addUser(@RequestBody User user) {
		if (userService.addUser(user) == 1) {
			return RespBean.ok("添加成功!");
		}
		return RespBean.error("添加失败!");
	}

	/**
	 * 根据ID删除用户
	 *
	 * @param iuser 用户ID
	 * @return
	 */
	@DeleteMapping("/{iuser}")
	public RespBean deleteUserById(@PathVariable Integer iuser) {
		if (userService.deleteUserById(iuser) == 1) {
			return RespBean.ok("删除成功!");
		}
		return RespBean.error("删除失败!");
	}

	/**
	 * 根据ID数组，批量删除用户
	 *
	 * @param ids 用户ID数组
	 * @return
	 */
	@DeleteMapping("/")
	public RespBean deleteUserByIds(Integer[] ids) {
		if (userService.deleteUserByIds(ids) == ids.length) {
			return RespBean.ok("删除成功!");
		}
		return RespBean.error("删除失败!");
	}

	/**
	 * 根据用户ID，重置用户密码
	 *
	 * @param iuser 用户ID
	 * @return
	 */
	@PostMapping("/{iuser}")
	public RespBean resetPasswordById(@PathVariable Integer iuser) {
		if (userService.resetPasswordById(iuser) == 1) {
			return RespBean.ok("重置密码成功!");
		}
		return RespBean.error("重置密码失败!");
	}

	/**
	 * 根据用户ID，修改用户信息
	 *
	 * @param user 用户对象
	 * @return
	 */
	@PutMapping("/")
	public RespBean updateUser(@RequestBody User user) {
//		System.out.println("user="+user.toString());
		if (userService.updateUser(user) == 1) {
			return RespBean.ok("更新成功!");
		}
		return RespBean.error("更新失败!");
	}

	/**
	 * 获取所有的角色
	 * @return
	 */
	@GetMapping("/role")
	public RespBean getAllRole(){
		List<Role> roles = userService.getAllRole();
		return RespBean.ok("",roles);
	}
}
