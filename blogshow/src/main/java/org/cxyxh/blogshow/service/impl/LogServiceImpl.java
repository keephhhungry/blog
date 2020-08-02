package org.cxyxh.blogshow.service.impl;

import org.cxyxh.blogshow.mapper.LogMapper;
import org.cxyxh.blogshow.model.Address;
import org.cxyxh.blogshow.model.Log;
import org.cxyxh.blogshow.model.RespPageBean;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.LogService;
import org.cxyxh.blogshow.utils.IPConvertUtils;
import org.cxyxh.blogshow.utils.UserAgentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.service.impl
 * @ClassName: LogService
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/4 23:39
 * @Version: 1.0
 */
@Service
public class LogServiceImpl implements LogService {


	@Autowired
	private HttpServletRequest request;

	@Autowired
	private LogMapper logMapper;


	/**
	 * 根据日志对象 新增日志
	 *
	 * @param log 日志对象
	 * @return
	 */
	@Override
	public Integer addLog(Log log) {
		return logMapper.addLog(log);
	}


}
