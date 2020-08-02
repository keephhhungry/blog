package org.cxyxh.blogshow.controller;

import io.swagger.annotations.*;
import org.cxyxh.blogshow.Interceptor.LoginInterceptor;
import org.cxyxh.blogshow.exception.BlogRuntimeException;
import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.UserService;
import org.cxyxh.blogshow.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.controller
 * @ClassName: UserController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/21 23:05
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户数据接口")
public class UserController {

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    UserService userService;


    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "注册用户", notes = "注册用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户对象", required = true),
    })
    @PostMapping("/register")
    public RespBean register(@RequestBody User user) {
        Integer result = null;
        try {
            result = userService.register(user);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            String remark = "注册新用户失败，原因：" + e.getMessage();
            request.setAttribute("remark", remark);
            return RespBean.error(e.getMessage(), "");
        }
        if (result == 1) {
            String remark = "注册新用户成功，用户ID[{" + user.getIuser() + "}]";
            request.setAttribute("remark", remark);
            logger.warn(remark);
            return RespBean.ok("", result);
        } else {
            String remark = "注册新用户失败，原因：未知错误";
            request.setAttribute("remark", remark);
            logger.error(remark);
            return RespBean.error("未知错误", "");
        }
    }

    /**
     * 修改用户名时测试是否可用
     *
     * @param username 用户名
     * @return
     */
    @ApiOperation(value = "根据用户名查找用户", notes = "根据用户名查找用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true),
    })
    @GetMapping("/checkUsernameAvailable")
    public RespBean findUserByUsername(String username) {
        if (userService.findUserCountByUsername(username) != 0) {
            return RespBean.error("当前账户名已经被占用", -2);
        }
        return RespBean.ok("", 0);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @ApiOperation(value = "修改用户", notes = "修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户对象", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "修改成功"),
            @ApiResponse(code = 500, message = "修改失败")
    })
    @PostMapping("/updateUser")
    public RespBean updateUser(@RequestBody User user) {
        User currUser = UserUtils.getCurrentUser(request);
        String remark = "";
        if (userService.updateUser(user) == 1) {
            remark = "修改用户资料成功，用户ID[{" + user.getIuser() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.info(remark);
            return RespBean.ok("资料修改成功", 1);
        } else {
            remark = "修改用户资料失败，用户ID[{" + user.getIuser() + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
            request.setAttribute("remark", remark);
            logger.error(remark);
            return RespBean.error("资料修改失败", -2);
        }
    }


}
