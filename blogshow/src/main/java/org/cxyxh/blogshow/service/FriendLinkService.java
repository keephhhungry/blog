package org.cxyxh.blogshow.service;

import org.cxyxh.blogshow.model.FriendLink;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.service
 * @ClassName: FriendLinkService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/14 15:35
 * @Version: 1.0
 */
public interface FriendLinkService {

	/**
	 * 查询全部友情链接
	 *
	 * @return
	 */
	List<FriendLink> getAllFriendLink();
}
