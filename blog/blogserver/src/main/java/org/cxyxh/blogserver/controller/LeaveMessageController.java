package org.cxyxh.blogserver.controller;

import org.cxyxh.blogserver.model.LeaveMessage;
import org.cxyxh.blogserver.model.Log;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller
 * @ClassName: LeaveMessageController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/2 23:36
 * @Version: 1.0
 */
@RestController
@RequestMapping("/system/leavemessage")
public class LeaveMessageController {

	@Autowired
	LeaveMessageService leaveMessageService;

	/**
	 * 根据页码，每页展示数量和留言对象 查询相关的留言集合
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param leaveMessage    留言对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	@GetMapping("/")
	public RespPageBean getLeaveMessageByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, LeaveMessage leaveMessage, Date[] createDateScope) {
		System.out.println("leaveMessage=" + leaveMessage.toString());
		if (createDateScope != null) {
			System.out.println("createDateScope=" + createDateScope.toString());

		}
		return leaveMessageService.getLeaveMessageByPage(page, size, leaveMessage, createDateScope);
	}

	/**
	 * 根据留言ID，删除留言
	 *
	 * @param ileaveMessage 留言ID
	 * @return
	 */
	@DeleteMapping("/{ileaveMessage}")
	public RespBean deleteLeaveMessageById(@PathVariable Integer ileaveMessage) {
		if (leaveMessageService.deleteLeaveMessageById(ileaveMessage) == 1) {
			return RespBean.ok("删除成功!");
		}
		return RespBean.error("删除失败!");
	}


}
