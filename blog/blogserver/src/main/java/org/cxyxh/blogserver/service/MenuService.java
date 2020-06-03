package org.cxyxh.blogserver.service;

import org.cxyxh.blogserver.model.Menu;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.service
 * @ClassName: MenuService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 10:31
 * @Version: 1.0
 */
public interface MenuService {

	/**
	 * 根据用户访问的路径，查询该路径可以访问的角色
	 *
	 * @return 菜单集合
	 */
	List<Menu> getAllMenusWithRole();

	List<Menu> getMenusByHrUser();

	/**
	 * 根据关键字 获取所有菜单
	 *
	 * @param keyword 关键字
	 * @return
	 */
	List<Menu> getAllMenu(String keyword);
}
