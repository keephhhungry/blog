package org.cxyxh.blogshow.service.impl;

import org.cxyxh.blogshow.mapper.LogMapper;
import org.cxyxh.blogshow.model.Log;
import org.cxyxh.blogshow.model.RespPageBean;
import org.cxyxh.blogshow.model.User;
import org.cxyxh.blogshow.service.LogService;
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

	/**
	 * 访问日志 做记录
	 *
	 * @param request
	 * @return
	 */
	@Override
	public Integer addLog(HttpServletRequest request) {
		//获取路径
		String url = request.getScheme()
				+"://" + request.getServerName()
				+ ":" +request.getServerPort()
				+ request.getServletPath();
		//获取参数
		String parameter = request.getQueryString();
		if (parameter != null){
			url += "?" + parameter;
		}
		//根据工具类获取数据
		String ip = UserAgentUtils.getIp(request);
		String browserName = UserAgentUtils.getBrowserName(request);
		String browserVersion = UserAgentUtils.getBrowserVersion(request);
		String osName = UserAgentUtils.getOsName(request);
		User user =(User) request.getSession().getAttribute("user");

		Log log = new Log();
		log.setAddress("");
		log.setIp(ip);
		log.setBrowserName(browserName);
		log.setBrowserVersion(browserVersion);
		log.setOperatingSystem(osName);
		if(user!=null){
			log.setIuser(user.getIuser());
		}
		log.setIuser(null);
		log.setGmtCreate(new Date());
		log.setGmtModified(new Date());
		log.setUrl(url);
		log.setOperationalParameter(parameter);
		log.setLogType(Log.FRONT_OPERATE_LOG);

		return addLog(log);
	}
}
