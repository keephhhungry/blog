package org.cxyxh.blogserver.service.impl;

import org.cxyxh.blogserver.mapper.LogMapper;
import org.cxyxh.blogserver.model.*;
import org.cxyxh.blogserver.service.LogService;
import org.cxyxh.blogserver.utils.IPConvertUtils;
import org.cxyxh.blogserver.utils.UserAgentUtils;
import org.cxyxh.blogserver.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired
    private LogMapper logMapper;

    /**
     * 根据日志的类型ID,页码，每页展示数量和日志对象 查询相关的日志集合
     *
     * @param page            页码
     * @param size            每页展示数量
     * @param log             日志对象
     * @param createDateScope 创建时间
     * @return
     */
    @Override
    public RespPageBean getLogByPage(Integer page, Integer size, Log log, Date[] createDateScope) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Log> logs = logMapper.getLogByPage(page, size, log, createDateScope);
        Long total = logMapper.getTotal(log, createDateScope);
        RespPageBean bean = new RespPageBean();
        bean.setData(logs);
        bean.setTotal(total);
        return bean;
    }

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
