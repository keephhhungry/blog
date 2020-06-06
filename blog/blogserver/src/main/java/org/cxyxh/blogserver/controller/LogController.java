package org.cxyxh.blogserver.controller;

import org.cxyxh.blogserver.model.ArticleType;
import org.cxyxh.blogserver.model.Log;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.service.ArticleTypeService;
import org.cxyxh.blogserver.service.LogService;
import org.cxyxh.blogserver.utils.DefaultParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller
 * @ClassName: LogController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/4 23:28
 * @Version: 1.0
 */
@RestController
@RequestMapping("/system/log")
public class LogController {

	@Autowired
	LogService logService;

	/**
	 * 根据页码，每页展示数量和日志对象 查询相关的日志集合
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param log             日志对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	@GetMapping("/")
	public RespPageBean getLoginLogByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Log log, Date[] createDateScope) {
		System.out.println("log="+log.toString());
		if(createDateScope!=null){
			System.out.println("createDateScope="+createDateScope.toString());

		}
		return logService.getLogByPage(page, size, log, createDateScope);
	}

}
