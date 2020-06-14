package org.cxyxh.blogshow.service.impl;

import org.cxyxh.blogshow.mapper.FriendLinkMapper;
import org.cxyxh.blogshow.model.FriendLink;
import org.cxyxh.blogshow.service.FriendLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service.impl
 * @ClassName: FriendLinkServiceImpl
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/14 15:35
 * @Version: 1.0
 */
@Service
public class FriendLinkServiceImpl implements FriendLinkService{

	@Autowired
	FriendLinkMapper friendLinkMapper;

	/**
	 * 查询全部友情链接
	 *
	 * @return
	 */
	@Override
	public List<FriendLink> getAllFriendLink() {
		return friendLinkMapper.getAllFriendLink();
	}
}
