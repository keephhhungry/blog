package org.cxyxh.blogserver.service;

import org.cxyxh.blogserver.model.LeaveMessage;
import org.cxyxh.blogserver.model.RespPageBean;

import java.util.Date; /**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.service
 * @ClassName: LeaveMessageService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/6 21:44
 * @Version: 1.0
 */
public interface LeaveMessageService {

	/**
	 * 根据页码，每页展示数量和留言对象 查询相关的留言集合
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param leaveMessage    留言对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	RespPageBean getLeaveMessageByPage(Integer page, Integer size, LeaveMessage leaveMessage, Date[] createDateScope);



	Integer deleteLeaveMessageById(Integer ileaveMessage);
}
