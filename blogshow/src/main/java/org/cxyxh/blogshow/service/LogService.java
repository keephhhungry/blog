package org.cxyxh.blogshow.service;


import org.cxyxh.blogshow.model.Log;

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
	 * 根据日志对象 新增日志
	 *
	 * @param log 日志对象
	 * @return
	 */
	Integer addLog(Log log);

	/**
	 * 访问日志 做记录
	 * @param request
	 * @return
	 */
	Integer addLog(HttpServletRequest request,String remark);
}
