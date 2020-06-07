package org.cxyxh.blogserver.service.impl;

import org.cxyxh.blogserver.mapper.LeaveMessageMapper;
import org.cxyxh.blogserver.model.LeaveMessage;
import org.cxyxh.blogserver.model.Log;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
public class LeaveMessageServiceImpl implements LeaveMessageService {

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
		List<LeaveMessage> leaveMessages = leaveMessageMapper.getLeaveMessageByPage(page, size, leaveMessage, createDateScope);
		Long total = leaveMessageMapper.getTotal(leaveMessage, createDateScope);
		RespPageBean bean = new RespPageBean();
		bean.setData(leaveMessages);
		bean.setTotal(total);
		return bean;
	}

	/**
	 * 查看该留言是否有子留言
	 *
	 * @param ileaveMessage 留言id
	 * @return
	 */
	@Override
	public Integer getLeaveMessageChildrenById(Integer ileaveMessage) {
		return leaveMessageMapper.getLeaveMessageChildrenById(ileaveMessage);
	}

	/**
	 * 根据ID删除留言
	 *
	 * @param ileaveMessage 留言id
	 * @return
	 */
	@Override
	public Integer deleteLeaveMessageById(Integer ileaveMessage) {
		return leaveMessageMapper.deleteLeaveMessageById(ileaveMessage);
	}


	/**
	 * 根据ID强制删除留言和子留言
	 * 通过递归实现
	 *
	 * @param ileaveMessage 留言id
	 * @return
	 */
	@Override
	public Integer enforceDeleteLeaveMessageById(Integer ileaveMessage) {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(ileaveMessage);
		//递归获取所有的子留言id集合
		for (int i = 0; i < ids.size(); i++) {
			List<Integer> leaveMessageChildren = getLeaveMessageChildren(ids.get(i));
			ids.addAll(leaveMessageChildren);
		}
		return leaveMessageMapper.deleteLeaveMessageByIds(ids);
	}

	/**
	 * 通过父id，查找子id
	 *
	 * @param ileaveMessage 父id
	 * @return
	 */
	public List<Integer> getLeaveMessageChildren(Integer ileaveMessage) {
		return leaveMessageMapper.getLeaveMessageChildren(ileaveMessage);
	}
}
