package org.cxyxh.blogserver.controller;

import io.swagger.annotations.*;
import org.apache.ibatis.annotations.Delete;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.model.Role;
import org.cxyxh.blogserver.model.User;
import org.cxyxh.blogserver.service.UserService;
import org.cxyxh.blogserver.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@Api(tags = "用户数据接口")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 根据页码，每页显示数量，用户对象和创建时间查询用户
     *
     * @param page            页码
     * @param size            每页显示数量
     * @param user            查询条件
     * @param createDateScope 创建时间区间
     * @return
     */
    @ApiOperation(value = "查询用户", notes = "根据页码，每页显示数量，用户对象和创建时间查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = false),
            @ApiImplicitParam(name = "size", value = "每页展示数量", defaultValue = "10", required = false),
            @ApiImplicitParam(name = "user", value = "用户对象", required = false),
            @ApiImplicitParam(name = "createDateScope", value = "创建时间", required = false)
    })
    @GetMapping("/")
    public RespPageBean getUserByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, User user, Date[] createDateScope) {
        return userService.getUserByPage(page, size, user, createDateScope);
    }

    /**
     * 根据用户对象，添加用户
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "添加用户", notes = "根据用户对象，添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "添加成功"),
            @ApiResponse(code = 500, message = "添加失败")
    })
    @PostMapping("/")
    public RespBean addUser(@RequestBody User user, HttpServletRequest request) {
        User currentUser = UserUtils.getCurrentUser();
        String remark = "";
        if (userService.addUser(user) == 1) {
            remark = "添加用户成功，用户ID[{" + currentUser + "}],操作人ID[{" + currentUser.getIuser() + "}],操作人名字[{" + currentUser.getUsername() + "}]";
            request.setAttribute("remark", remark);
            return RespBean.ok("添加成功!");
        }
        remark = "添加用户失败，操作人ID[{" + currentUser.getIuser() + "}],操作人名字[{" + currentUser.getUsername() + "}]";
        request.setAttribute("remark", remark);
        return RespBean.error("添加失败!");
    }

    /**
     * 根据用户ID，删除用户
     *
     * @param iuser 用户ID
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "根据用户ID，删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "iuser", value = "用户ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    @DeleteMapping("/{iuser}")
    public RespBean deleteUserById(@PathVariable Integer iuser, HttpServletRequest request) {
        User currentUser = UserUtils.getCurrentUser();
        String remark = "";
        if (userService.deleteUserById(iuser) == 1) {
            remark = "删除用户成功，用户ID[{" + iuser + "}],操作人ID[{" + currentUser.getIuser() + "}],操作人名字[{" + currentUser.getUsername() + "}]";
            request.setAttribute("remark", remark);
            return RespBean.ok("删除成功!");
        }
        remark = "删除用户失败，用户ID[{" + iuser + "}],操作人ID[{" + currentUser.getIuser() + "}],操作人名字[{" + currentUser.getUsername() + "}]";
        request.setAttribute("remark", remark);
        return RespBean.error("删除失败!");
    }

    /**
     * 根据ID数组，批量删除用户
     *
     * @param ids 用户ID数组
     * @return
     */
    @ApiOperation(value = "批量删除用户", notes = "根据用户ID集合，批量删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "用户ID集合", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    @DeleteMapping("/")
    public RespBean deleteUserByIds(Integer[] ids, HttpServletRequest request) {
        User user = UserUtils.getCurrentUser();
        String remark = "";
        if (userService.deleteUserByIds(ids) == ids.length) {
            remark = "批量删除用户成功，用户ID[{" + Arrays.toString(ids) + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            return RespBean.ok("删除成功!");
        }
        remark = "批量删除用户失败，用户ID[{" + Arrays.toString(ids) + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
        request.setAttribute("remark", remark);
        return RespBean.error("删除失败!");
    }

    /**
     * 根据用户ID，重置用户密码
     *
     * @param iuser 用户ID
     * @return
     */
    @ApiOperation(value = "重置密码", notes = "根据用户ID，重置用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "iuser", value = "用户ID", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "重置密码成功"),
            @ApiResponse(code = 500, message = "重置密码失败")
    })
    @PostMapping("/{iuser}")
    public RespBean resetPasswordById(@PathVariable Integer iuser, HttpServletRequest request) {
        User currentUser = UserUtils.getCurrentUser();
        String remark = "";
        if (userService.resetPasswordById(iuser) == 1) {
            remark = "重置密码成功，用户ID[{" + iuser + "}],操作人ID[{" + currentUser.getIuser() + "}],操作人名字[{" + currentUser.getUsername() + "}]";
            request.setAttribute("remark", remark);
            return RespBean.ok("重置密码成功!");
        }
        remark = "重置密码失败，用户ID[{" + iuser + "}],操作人ID[{" + currentUser.getIuser() + "}],操作人名字[{" + currentUser.getUsername() + "}]";
        request.setAttribute("remark", remark);
        return RespBean.error("重置密码失败!");
    }

    /**
     * 根据用户对象，修改用户
     *
     * @param user 用户对象
     * @return
     */
    @ApiOperation(value = "修改用户", notes = "根据用户对象，修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 500, message = "修改失败")
    })
    @PutMapping("/")
    public RespBean updateUser(@RequestBody User user, HttpServletRequest request) {
        User currentUser = UserUtils.getCurrentUser();
        String remark = "";
        if (userService.updateUser(user) == 1) {
            remark = "修改用户信息成功，用户ID[{" + user.getIuser() + "}],操作人ID[{" + currentUser.getIuser() + "}],操作人名字[{" + currentUser.getUsername() + "}]";
            request.setAttribute("remark", remark);
            return RespBean.ok("更新成功!");
        }
        remark = "修改用户信息失败，用户ID[{" + user.getIuser() + "}],操作人ID[{" + currentUser.getIuser() + "}],操作人名字[{" + currentUser.getUsername() + "}]";
        request.setAttribute("remark", remark);
        return RespBean.error("更新失败!");
    }

    /**
     * 查询所有的角色
     *
     * @return
     */
    @ApiOperation(value = "查询角色", notes = "查询所有的角色")
    @GetMapping("/role")
    public RespBean getAllRole() {
        List<Role> roles = userService.getAllRole();
        return RespBean.ok("", roles);
    }
}
