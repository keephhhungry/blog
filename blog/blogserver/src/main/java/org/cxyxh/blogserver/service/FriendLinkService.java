package org.cxyxh.blogserver.service;

import org.cxyxh.blogserver.model.FriendLink;
import org.cxyxh.blogserver.model.RespPageBean;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.service
 * @ClassName: FrinendLinkService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/1 23:35
 * @Version: 1.0
 */
public interface FriendLinkService {

	/**
	 * 根据页码和关键字 查询友链
	 *
	 * @param page    页码
	 * @param size    每页展示数量
	 * @param keyword 关键字
	 * @return
	 */
	RespPageBean getFriendLinkByPage(Integer page, Integer size, String keyword);

	/**
	 * 根据ID 删除友链
	 *
	 * @param ifriendLink 友链ID
	 * @return
	 */
	Integer deleteFriendLinkById(Integer ifriendLink);

	/**
	 * 根据用户对象 新增用户
	 *
	 * @param friendLink 用户对象
	 * @return
	 */
	Integer addFriendLink(FriendLink friendLink);

	/**
	 * 根据友链ID 修改该对象
	 *
	 * @param friendLink 友链对象
	 * @return
	 */
	Integer updateFriendLinkById(FriendLink friendLink);
}
