package org.cxyxh.blogserver.mapper;

import org.apache.ibatis.annotations.Param;
import org.cxyxh.blogserver.model.Log;
import org.cxyxh.blogserver.model.UserDataDownload;

import java.util.Date;
import java.util.List;

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
     * 根据日志的类型ID,页码，每页展示数量和日志对象 查询相关的日志集合
     *
     * @param page            页码
     * @param size            每页展示数量
     * @param log             日志对象
     * @param createDateScope 创建时间
     * @return
     */
    List<Log> getLogByPage(@Param("page") Integer page, @Param("size") Integer size, @Param("log") Log log, @Param("createDateScope") Date[] createDateScope);

    /**
     * 根据日志类型，日志对象，创建时间，查看日志的数量
     *
     * @param log             日志对象
     * @param createDateScope 创建时间
     * @return
     */
    Long getTotal(@Param("log") Log log, @Param("createDateScope") Date[] createDateScope);

    /**
     * 根据日志对象，新增日志
     *
     * @param log 日志对象
     * @return
     */
    Integer addLog(Log log);

}
