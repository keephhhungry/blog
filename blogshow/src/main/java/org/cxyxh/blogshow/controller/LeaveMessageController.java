package org.cxyxh.blogshow.controller;

import org.cxyxh.blogshow.model.RespBean;
import org.cxyxh.blogshow.model.RespPageBean;
import org.cxyxh.blogshow.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.controller
 * @ClassName: LeaveMessageController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/20 10:24
 * @Version: 1.0
 */
@RestController
@RequestMapping("/leaveMessage")
public class LeaveMessageController {

	@Autowired
	LeaveMessageService leaveMessageService;

	/**
	 * 根据页码和每页展示数量 获取留言集合
	 *
	 * @param page 页码
	 * @param size 每页展示数量
	 * @return
	 */
	@GetMapping("/")
	public RespPageBean getLeaveMessage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
		return leaveMessageService.getLeaveMessageByPage(page, size);
	}

}
