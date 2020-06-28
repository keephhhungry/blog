package org.cxyxh.blogshow.service;

import org.cxyxh.blogshow.model.LeaveMessage;
import org.cxyxh.blogshow.model.RespPageBean;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service
 * @ClassName: LeaveMessageService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/20 10:25
 * @Version: 1.0
 */
public interface LeaveMessageService {

	/**
	 * 根据页码和每页展示数量 获取留言集合
	 *
	 * @param page 页码
	 * @param size 每页展示数量
	 * @return
	 */
	RespPageBean getLeaveMessageByPage(Integer page, Integer size);

	/**
	 * 新增留言
	 *
	 * @param request
	 * @param leaveMessage
	 * @return
	 */
	Integer addLeaveMessage(HttpServletRequest request, LeaveMessage leaveMessage);
}
