package org.cxyxh.blogserver.service;

import org.cxyxh.blogserver.model.Log;
import org.cxyxh.blogserver.model.RespBean;
import org.cxyxh.blogserver.model.RespPageBean;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.service
 * @ClassName: LogService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/4 23:39
 * @Version: 1.0
 */
public interface LogService {


	/**
	 * 根据日志的类型ID,页码和关键字 查询相关的日志
	 *
	 * @param page            页码
	 * @param size            每页展示数量
	 * @param log             日志对象
	 * @param createDateScope 创建时间区间
	 * @return
	 */
	RespPageBean getLogByPage(Integer page, Integer size, Log log, Date[] createDateScope);


	/**
	 * 根据日志对象 新增日志
	 *
	 * @param log 日志对象
	 * @return
	 */
	Integer addLog(Log log);

}
