package org.cxyxh.blogshow.controller;

import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class UserController {

    @Autowired
    UserService userService;


    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    @PostMapping("/register")
    public RespBean register(@RequestBody User user) {
        Integer result = userService.register(user);
        return RespBean.ok("", result);
    }

    /**
     * 修改用户名时测试是否可用
     *
     * @param username 用户名
     * @return
     */
    @GetMapping("/checkUsernameAvailable")
    public RespBean findUserByUsername(String username) {
		Integer result = userService.findUserCountByUsername(username);
		return  RespBean.ok("",result);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @PostMapping("/updateUser")
    public RespBean updateUser(@RequestBody User user) {
        System.out.println("user="+user);
        if (userService.updateUser(user) == 1) {
            return RespBean.ok("资料修改成功",1);
        } else {
            return RespBean.error("资料修改失败",0);
        }
    }


}
