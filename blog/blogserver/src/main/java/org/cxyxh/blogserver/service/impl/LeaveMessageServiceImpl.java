package org.cxyxh.blogserver.service.impl;

import org.cxyxh.blogserver.mapper.LeaveMessageMapper;
import org.cxyxh.blogserver.model.LeaveMessage;
import org.cxyxh.blogserver.model.Log;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.service.impl
 * @ClassName: LeaveMessageServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/6 21:44
 * @Version: 1.0
 */
@Service
public class LeaveMessageServiceImpl implements LeaveMessageService{

	@Autowired
	LeaveMessageMapper leaveMessageMapper;

	/**
	 * 根据页码，每页展示数量，创建时间和留言对象 查询相关的留言集合
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param leaveMessage    留言对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	@Override
	public RespPageBean getLeaveMessageByPage(Integer page, Integer size, LeaveMessage leaveMessage, Date[] createDateScope) {
		if (page != null && size != null) {
			page = (page - 1) * size;
		}
		List<LeaveMessage> leaveMessages = leaveMessageMapper.getLeaveMessageByPage(page, size, leaveMessage,createDateScope);
		Long total = leaveMessageMapper.getTotal(leaveMessage,createDateScope);
		RespPageBean bean = new RespPageBean();
		bean.setData(leaveMessages);
		bean.setTotal(total);
		return bean;
	}

	@Override
	public Integer deleteLeaveMessageById(Integer ileaveMessage) {
		return null;
	}
}
