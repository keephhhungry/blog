package org.cxyxh.blogshow.mapper;

import org.apache.ibatis.annotations.Param;
import org.cxyxh.blogshow.model.LeaveMessage;

import java.util.List;

/**
 * @ProjectName: blogshow
 * @Package: org.cxyxh.blogshow.mapper
 * @ClassName: LeaveMessageMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/20 10:25
 * @Version: 1.0
 */
public interface LeaveMessageMapper {

	/**
	 * 根据页码，每页展示数量 查询相关的留言集合
	 *
	 * @param page 页码
	 * @param size 每页展示数量
	 * @return
	 */
	List<LeaveMessage> getLeaveMessageByPage(@Param("page") Integer page, @Param("size") Integer size);

	/**
	 * 根据父id，查找子留言
	 *
	 * @param ileaveMessage 留言id
	 * @return
	 */
	List<LeaveMessage> getLeaveMessageByParentId(Integer ileaveMessage);

	/**
	 * 查看留言的数量
	 *
	 * @return
	 */
	Long getTotal();

	/**
	 * 新增留言
	 *
	 * @param leaveMessage
	 * @return
	 */
	Integer addLeaveMessage(LeaveMessage leaveMessage);
}
