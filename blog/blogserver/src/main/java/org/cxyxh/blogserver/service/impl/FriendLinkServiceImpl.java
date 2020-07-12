package org.cxyxh.blogserver.service.impl;

import org.cxyxh.blogserver.mapper.FriendLinkMapper;
import org.cxyxh.blogserver.model.FriendLink;
import org.cxyxh.blogserver.model.RespPageBean;
import org.cxyxh.blogserver.model.User;
import org.cxyxh.blogserver.service.FriendLinkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.service.impl
 * @ClassName: FriendLinkServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/1 23:35
 * @Version: 1.0
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService{

	private final static Logger logger = LoggerFactory.getLogger(FriendLinkServiceImpl.class);

	@Autowired
	FriendLinkMapper friendLinkMapper;

	/**
	 * 根据页码和关键字 查询友链
	 *
	 * @param page    页码
	 * @param size    每页展示数量
	 * @param keyword 关键字
	 * @return
	 */
	@Override
	public RespPageBean getFriendLinkByPage(Integer page, Integer size, String keyword) {
		if (page != null && size != null) {
			page = (page - 1) * size;
		}
		List<FriendLink> friendLinks = friendLinkMapper.getFriendLinkByPage( page,  size, keyword);
		Long total = friendLinkMapper.getTotal(keyword);
		RespPageBean bean = new RespPageBean();
		bean.setData(friendLinks);
		bean.setTotal(total);
		return bean;
	}

	/**
	 * 根据ID 删除友链
	 *
	 * @param ifriendLink 友链ID
	 * @return
	 */
	@Override
	public Integer deleteFriendLinkById(Integer ifriendLink) {
		return friendLinkMapper.deleteFriendLinkById(ifriendLink);
	}

	/**
	 * 根据用户对象 新增用户
	 *
	 * @param friendLink 用户对象
	 * @return
	 */
	@Override
	public Integer addFriendLink(FriendLink friendLink) {
		friendLink.setGmtCreate(new Date());
		friendLink.setGmtModified(new Date());
		return friendLinkMapper.addFriendLink(friendLink);
	}

	/**
	 * 根据友链ID 修改该对象
	 *
	 * @param friendLink 友链对象
	 * @return
	 */
	@Override
	public Integer updateFriendLinkById(FriendLink friendLink) {
		friendLink.setGmtModified(new Date());
		return friendLinkMapper.updateFriendLinkById(friendLink);
	}
}
