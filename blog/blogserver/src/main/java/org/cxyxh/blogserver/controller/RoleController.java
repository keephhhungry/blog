package org.cxyxh.blogserver.controller;

import io.swagger.annotations.*;
import org.cxyxh.blogserver.Interceptor.LoginInterceptor;
import org.cxyxh.blogserver.model.Menu;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.Role;
import org.cxyxh.blogserver.model.User;
import org.cxyxh.blogserver.service.MenuService;
import org.cxyxh.blogserver.service.RoleService;
import org.cxyxh.blogserver.service.UserAndRoleService;
import org.cxyxh.blogserver.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@Api(tags = "角色数据接口")
public class RoleController {

    private final static Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserAndRoleService userAndRoleService;

    @Autowired
    private MenuService menuService;

    /**
     * 查询所有的角色
     *
     * @return
     */
    @ApiOperation(value = "查询角色", notes = "查询所有的角色")
    @GetMapping("/")
    public RespBean getAllRole(String keyword) {
        List<Role> roles = roleService.getAllRole(keyword);
        return RespBean.ok("", roles);
    }

    /**
     * 查询所有的菜单
     *
     * @return
     */
    @ApiOperation(value = "查询菜单", notes = "查询所有的菜单")
    @GetMapping("/menu")
    public RespBean getAllMenu() {
        List<Menu> menus = menuService.getAllMenu("");
        return RespBean.ok("", menus);
    }

    /**
     * 根据角色对象，添加角色
     *
     * @param role 角色对象
     * @return
     */
    @ApiOperation(value = "添加角色", notes = "根据角色对象，添加角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "角色对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功"),
            @ApiResponse(code = 500, message = "添加失败")
    })
    @PostMapping("/")
    public RespBean addRole(@RequestBody Role role, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        if (roleService.addRole(role) == 1) {
            remark = "添加角色成功，角色ID[{" + role.getIrole() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.info(remark);
            return RespBean.ok("添加成功");
        } else {
            remark = "添加角色失败，角色ID[{" + role.getIrole() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.error(remark);
            return RespBean.ok("添加失败");
        }
    }

    /**
     * 根据角色ID，删除角色
     *
     * @param irole 角色ID
     * @return
     */
    @ApiOperation(value = "删除角色", notes = "根据角色ID，删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "irole", value = "角色ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    @DeleteMapping("/{irole}")
    public RespBean deleteRole(@PathVariable Integer irole, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        Integer num = userAndRoleService.getUserNumByRoleId(irole);
        if (num == 0) {
            if (roleService.deleteRole(irole) == 1) {
                remark = "删除角色成功，角色ID[{" + irole + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
                request.setAttribute("remark", remark);
                logger.info(remark);
                return RespBean.ok("删除成功");
            } else {
                remark = "删除角色失败，角色ID[{" + irole + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
                request.setAttribute("remark", remark);
                logger.error(remark);
                return RespBean.ok("删除失败");
            }
        } else {
            remark = "删除角色失败，仍有" + num + "个用户拥有该角色，请先取消这些用户的角色,操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.warn(remark);
            return RespBean.ok("删除失败，该角色下仍有用户");
        }

    }

    /**
     * 修改角色
     *
     * @param role 角色对象
     * @return
     */
    @ApiOperation(value = "修改角色", notes = "根据角色对象，修改角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "role", value = "角色对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 500, message = "修改失败")
    })
    @PutMapping("/")
    public RespBean updateRole(@RequestBody Role role, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        if (roleService.updateRole(role) == 1) {
            remark = "修改角色成功，角色ID[{" + role.getIrole() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.info(remark);
            return RespBean.ok("修改成功");
        } else {
            remark = "修改角色失败，角色ID[{" + role.getIrole() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.error(remark);
            return RespBean.ok("修改失败");
        }
    }

}
