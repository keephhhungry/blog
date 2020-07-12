package org.cxyxh.blogserver.controller.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.cxyxh.blogserver.model.Menu;
import org.cxyxh.blogserver.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller.config
 * @ClassName: SystemConfigController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 22:25
 * @Version: 1.0
 */
@RestController
@RequestMapping("/system/config")
@Api(tags = "查询用户菜单数据接口")
public class SystemConfigController {

    @Autowired
    MenuService menuService;

    @GetMapping("/menu")
    @ApiOperation(value = "查询用户菜单", notes = "查询用户菜单")
    public List<Menu> getMenusByHrUser() {
        return menuService.getMenusByHrUser();
    }
}
