package org.cxyxh.blogserver.controller;

import org.cxyxh.blogserver.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller
 * @ClassName: LoginController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/5/28 14:47
 * @Version: 1.0
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("尚未登录");
    }
}
