package org.cxyxh.blogshow.mapper;

import org.cxyxh.blogshow.model.FriendLink;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.mapper
 * @ClassName: FriendLinkMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/14 15:56
 * @Version: 1.0
 */
public interface FriendLinkMapper {

	/**
	 * 查询全部友情链接
	 *
	 * @return
	 */
	List<FriendLink> getAllFriendLink();
}
