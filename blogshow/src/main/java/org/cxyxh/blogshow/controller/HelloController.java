package org.cxyxh.blogshow.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: blogshow
 * @Package: com.cxyxh.blogshow.controller
 * @ClassName: HelloController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/14 13:14
 * @Version: 1.0
 */
@RestController
public class HelloController {


	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}
}
