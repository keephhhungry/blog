package org.cxyxh.blogserver.service.impl;

import org.cxyxh.blogserver.mapper.MenuMapper;
import org.cxyxh.blogserver.model.Menu;
import org.cxyxh.blogserver.model.User;
import org.cxyxh.blogserver.service.MenuService;
import org.cxyxh.blogserver.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blog.service.impl
 * @ClassName: MenuService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 10:31
 * @Version: 1.0
 */
@Service
public class MenuServiceImpl implements MenuService {

    private final static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Autowired
    MenuMapper menuMapper;

    /**
     * 根据用户访问的路径，查询该路径可以访问的角色
     *
     * @return 菜单集合
     */
    @Override
    public List<Menu> getAllMenusWithRole() {
        return menuMapper.getAllMenusWithRole();
    }


    @Override
    public List<Menu> getMenusByHrUser() {
        User user = UserUtils.getCurrentUser();
        return menuMapper.getMenusByHrUser(user.getIuser());
    }

    /**
     * 根据关键字 查询菜单
     *
     * @param keyword 关键字
     * @return
     */
    @Override
    public List<Menu> getAllMenu(String keyword) {
        return menuMapper.getAllMenu(keyword);
    }


}
