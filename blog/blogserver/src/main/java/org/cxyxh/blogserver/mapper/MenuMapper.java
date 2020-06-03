package org.cxyxh.blogserver.mapper;

import org.cxyxh.blogserver.model.Menu;

import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.mapper
 * @ClassName: MenuMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 10:24
 * @Version: 1.0
 */
public interface MenuMapper {

	/**
	 * 根据用户的ID，查询他的菜单项
	 *
	 * @param iuser 用户的ID
	 * @return 菜单集合
	 */
	List<Menu> getMenusByHrUser(Integer iuser);

	/**
	 * 根据用户访问的路径，查询该路径可以访问的角色
	 *
	 * @return 菜单集合
	 */
	List<Menu> getAllMenusWithRole();

	/**
	 * 根据关键字 查询菜单
	 *
	 * @param keyword 关键字
	 * @return
	 */
	List<Menu> getAllMenu(String keyword);
}
