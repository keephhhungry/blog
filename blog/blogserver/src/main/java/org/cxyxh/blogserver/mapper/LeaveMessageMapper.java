package org.cxyxh.blogserver.mapper;

import org.apache.ibatis.annotations.Param;
import org.cxyxh.blogserver.model.LeaveMessage;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.mapper
 * @ClassName: LeaveMessageMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/6 21:45
 * @Version: 1.0
 */
public interface LeaveMessageMapper {

	/**
	 * 根据页码，每页展示数量，创建时间和留言对象 查询相关的留言集合
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param leaveMessage    留言对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	List<LeaveMessage> getLeaveMessageByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("leaveMessage") LeaveMessage leaveMessage, @Param("createDateScope") Date[] createDateScope);

	/**
	 * 根据留言对象，创建时间，查看留言的数量
	 *
	 * @param leaveMessage    留言对象
	 * @param createDateScope 创建时间
	 * @return
	 */
	Long getTotal(@Param("leaveMessage") LeaveMessage leaveMessage, @Param("createDateScope") Date[] createDateScope);

	/**
	 * 查看该留言是否有子留言
	 *
	 * @param ileaveMessage 留言id
	 * @return
	 */
	Integer getLeaveMessageChildrenById(Integer ileaveMessage);

	/**
	 * 根据ID删除留言
	 *
	 * @param ileaveMessage 留言id
	 * @return
	 */
	Integer deleteLeaveMessageById(Integer ileaveMessage);


	/**
	 * 通过父id，查找子id
	 *
	 * @param ileaveMessage 父id
	 * @return
	 */
	List<Integer> getLeaveMessageChildren(Integer ileaveMessage);

	/**
	 * 根据留言id，批量删除留言
	 *
	 * @param ids
	 * @return
	 */
	Integer deleteLeaveMessageByIds(@Param("ids") List<Integer> ids);


}
