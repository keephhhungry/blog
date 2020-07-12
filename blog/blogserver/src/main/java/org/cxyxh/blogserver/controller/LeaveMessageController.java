package org.cxyxh.blogserver.controller;

import io.swagger.annotations.*;
import org.cxyxh.blogserver.model.*;
import org.cxyxh.blogserver.service.LeaveMessageService;
import org.cxyxh.blogserver.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@Api(tags = "留言数据接口")
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
	@ApiOperation(value = "查询留言", notes = "根据页码，每页显示数量，留言对象和创建时间查询留言")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = false),
			@ApiImplicitParam(name = "size", value = "每页展示数量", defaultValue = "10", required = false),
			@ApiImplicitParam(name = "leaveMessage", value = "留言对象", required = false),
			@ApiImplicitParam(name = "createDateScope", value = "创建时间", required = false)
	})
	@GetMapping("/")
	public RespPageBean getLeaveMessageByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, LeaveMessage leaveMessage, Date[] createDateScope) {
		return leaveMessageService.getLeaveMessageByPage(page, size, leaveMessage, createDateScope);
	}


	/**
	 * 根据留言ID，删除留言
	 *
	 * @param ileaveMessage 留言ID
	 * @return
	 */
	@ApiOperation(value = "删除留言", notes = "根据留言ID，删除留言")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ileaveMessage", value = "留言ID", required = true),
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "删除成功"),
			@ApiResponse(code = 500, message = "删除失败")
	})
	@DeleteMapping("/{ileaveMessage}")
	public RespBean deleteLeaveMessageById(@PathVariable Integer ileaveMessage, HttpServletRequest request) {
		User user = UserUtils.getCurrentUser();
		String remark = "";
		int num = leaveMessageService.getLeaveMessageChildrenById(ileaveMessage);
		//如果没有子留言
		if(num == 0 ){
			if (leaveMessageService.deleteLeaveMessageById(ileaveMessage) == 1) {
				remark = "删除留言成功，留言ID[{" + ileaveMessage + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
				request.setAttribute("remark", remark);
				return RespBean.ok("删除成功!");
			}
			remark = "删除留言失败，留言ID[{" + ileaveMessage + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
			request.setAttribute("remark", remark);
			return RespBean.error("删除失败!");
		}else{
			remark = "删除失败，该留言下有回复，留言ID[{" + ileaveMessage + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
			request.setAttribute("remark", remark);
			return RespBean.error("删除失败，该留言下有回复",-1);
		}

	}

	/**
	 * 根据留言id，不管是否有下级，强制删除留言
	 * @param ileaveMessage
	 * @return
	 */
	@ApiOperation(value = "强制删除留言", notes = "根据留言id，不管是否有下级，强制删除留言")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ileaveMessage", value = "留言ID", required = true),
	})
	@ApiResponses({
			@ApiResponse(code = 200, message = "删除成功"),
			@ApiResponse(code = 500, message = "删除失败")
	})
	@DeleteMapping("/enforce/{ileaveMessage}")
	public RespBean enforceDeleteLeaveMessageById(@PathVariable Integer ileaveMessage, HttpServletRequest request) {
		User user = UserUtils.getCurrentUser();
		String remark = "";
		int num = leaveMessageService.enforceDeleteLeaveMessageById(ileaveMessage);
		if (num != 0) {
			remark = "删除留言成功[{"+num+"}]，父留言ID[{" + ileaveMessage + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
			request.setAttribute("remark", remark);
			return RespBean.ok("成功删除"+num+"条数据");
		}
		remark = "删除留言失败，留言ID[{" + ileaveMessage + "}],操作人ID[{" + user.getIuser() + "}],操作人名字[{" + user.getUsername() + "}]";
		request.setAttribute("remark", remark);
		return RespBean.error("删除失败!");
	}


}
