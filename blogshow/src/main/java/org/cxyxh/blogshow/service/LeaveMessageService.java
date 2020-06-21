package org.cxyxh.blogshow.service;

import org.cxyxh.blogshow.model.RespPageBean;

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
}
