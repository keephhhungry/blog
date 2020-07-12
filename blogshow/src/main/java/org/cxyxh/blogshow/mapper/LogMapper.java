package org.cxyxh.blogshow.mapper;

import org.cxyxh.blogshow.model.Log;


/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.mapper
 * @ClassName: LogMapper
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/4 23:38
 * @Version: 1.0
 */
public interface LogMapper {



	/**
	 * 根据日志对象，新增日志
	 *
	 * @param log 日志对象
	 * @return
	 */
	Integer addLog(Log log);
}
