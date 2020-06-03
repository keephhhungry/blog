package org.cxyxh.blogserver.controller;

import org.cxyxh.blogserver.model.Menu;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.controller
 * @ClassName: MenuController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 10:33
 * @Version: 1.0
 */
@RestController
@RequestMapping("/permission/menu")
public class MenuController {

	@Autowired
	MenuService menuService;

	/**
	 * 根据关键字 查询菜单
	 *
	 * @param keyword 关键字
	 * @return
	 */
	@GetMapping("/")
	public RespBean getAllMenu(String keyword) {
		List<Menu> menus = menuService.getAllMenu(keyword);
		return RespBean.ok("", menus);
	}
}
