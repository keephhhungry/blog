package org.cxyxh.blogserver.mapper;

import org.apache.ibatis.annotations.Param;
import org.cxyxh.blogserver.model.FriendLink;

import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.mapper
 * @ClassName: FriendLinkMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/1 23:36
 * @Version: 1.0
 */
public interface FriendLinkMapper {

	/**
	 * 根据页码和关键字 查询友链
	 *
	 * @param page    页码
	 * @param size    每页展示数量
	 * @param keyword 关键字
	 * @return
	 */
	List<FriendLink> getFriendLinkByPage(@Param("page") Integer page, @Param("size")Integer size, @Param("keyword")String keyword);

	/**
	 * 根据关键字 查询数据条数
	 *
	 * @param keyword 关键字
	 * @return
	 */
	Long getTotal(String keyword);

	/**
	 * 根据ID 删除友链
	 *
	 * @param ifriendLink 友链ID
	 * @return
	 */
	Integer deleteFriendLinkById(Integer ifriendLink);

	/**
	 * 根据友链对象 新增友链
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
