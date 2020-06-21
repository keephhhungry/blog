package org.cxyxh.blogshow.controller;

import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	 * @param user
	 * @return
	 */
	@PostMapping("/register")
	public RespBean register(@RequestBody User user){
		Integer result = userService.register(user);
		return RespBean.ok("",result);
	}

}
