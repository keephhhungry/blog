package org.cxyxh.blogshow.service.impl;

import org.cxyxh.blogshow.mapper.LeaveMessageMapper;
import org.cxyxh.blogshow.model.LeaveMessage;
import org.cxyxh.blogshow.model.RespPageBean;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.LeaveMessageService;
import org.cxyxh.blogshow.utils.UserAgentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service.impl
 * @ClassName: LeaveMessageServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/20 10:25
 * @Version: 1.0
 */
@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {

	@Autowired
	LeaveMessageMapper leaveMessageMapper;

	/**
	 * 根据页码和每页展示数量 获取留言集合
	 *
	 * @param page 页码
	 * @param size 每页展示数量
	 * @return
	 */
	@Override
	public RespPageBean getLeaveMessageByPage(Integer page, Integer size) {
		if (page != null && size != null) {
			page = (page - 1) * size;
		}
		//父留言
		List<LeaveMessage> leaveMessages = leaveMessageMapper.getLeaveMessageByPage(page, size);
		//获取子留言
		for (LeaveMessage leaveMessage : leaveMessages) {
			List<LeaveMessage> children = leaveMessageMapper.getLeaveMessageByParentId(leaveMessage.getIleaveMessage());
			leaveMessage.setChildren(children);
		}
		Long total = leaveMessageMapper.getTotal();
		RespPageBean bean = new RespPageBean();
		bean.setData(leaveMessages);
		bean.setTotal(total);
		return bean;
	}

	/**
	 * 新增留言
	 *
	 * @param request
	 * @param leaveMessage
	 * @return
	 */
	@Override
	public Integer addLeaveMessage(@RequestBody  HttpServletRequest request, LeaveMessage leaveMessage) {
		//根据工具类获取数据
		String ip = UserAgentUtils.getIp(request);
		String browserName = UserAgentUtils.getBrowserName(request);
		String browserVersion = UserAgentUtils.getBrowserVersion(request);
		String osName = UserAgentUtils.getOsName(request);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//赋值
		leaveMessage.setAddress("");
		leaveMessage.setIp(ip);
		leaveMessage.setBrowserName(browserName);
		leaveMessage.setBrowserVersion(browserVersion);
		leaveMessage.setOperatingSystem(osName);
		leaveMessage.setIuser(user.getIuser());
		leaveMessage.setGmtCreate(new Date());
		leaveMessage.setGmtModified(new Date());
		return leaveMessageMapper.addLeaveMessage(leaveMessage);
	}
}
