package org.cxyxh.blogserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.cxyxh.blogserver.Interceptor.LoginInterceptor;
import org.cxyxh.blogserver.model.*;
import org.cxyxh.blogserver.service.DataDownloadService;
import org.cxyxh.blogserver.service.LogService;
import org.cxyxh.blogserver.utils.poi.ProvinceDataDownloadUtils;
import org.cxyxh.blogserver.utils.poi.SingelUserDataDownloadUtils;
import org.cxyxh.blogserver.utils.poi.UserDataDownloadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ProjectName: blog
 * @Package: org.cxyxh.blogserver.controller
 * @ClassName: LogController
 * @Author: Administrator
 * @Description: ${description}
 * @Date: 2020/6/4 23:28
 * @Version: 1.0
 */
@RestController
@RequestMapping("/system/log")
@Api(tags = "日志数据接口")
public class LogController {

    private final static Logger logger = LoggerFactory.getLogger(LogController.class);

    @Autowired
    private LogService logService;

    @Autowired
    private DataDownloadService dataDownloadService;

    /**
     * 根据页码，每页显示数量，日志对象和创建时间查询日志
     *
     * @param page            页码
     * @param size            每页展示数量
     * @param log             日志对象
     * @param createDateScope 创建时间
     * @return
     */
    @ApiOperation(value = "查询日志", notes = "根据页码，每页显示数量，日志对象和创建时间查询日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", defaultValue = "1", required = false),
            @ApiImplicitParam(name = "size", value = "每页展示数量", defaultValue = "10", required = false),
            @ApiImplicitParam(name = "log", value = "日志对象", required = false),
            @ApiImplicitParam(name = "createDateScope", value = "创建时间", required = false)
    })
    @GetMapping("/")
    public RespPageBean getLoginLogByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, Log log, Date[] createDateScope) {
        return logService.getLogByPage(page, size, log, createDateScope);
    }

    /**
     * 用户数据下载
     * @param createDateScope
     */
    @GetMapping("/userDataDownload")
    public ResponseEntity<byte[]> userDataDownload(Date[] createDateScope) {
        List<UserDataDownload> data = dataDownloadService.userDataDownload(createDateScope);
        return UserDataDownloadUtils.userDataDownload(data,createDateScope);
    }

    /**
     * 单个用户数据下载
     * @param createDateScope
     */
    @GetMapping("/singelUserDataDownload")
    public ResponseEntity<byte[]> singelUserDataDownload(Date[] createDateScope,String username) {
        List<SingelUserDataDownload> data = dataDownloadService.singelUserDataDownload(createDateScope,username);
        return SingelUserDataDownloadUtils.userDataDownload(data,createDateScope,username);
    }


    /**
     * 省份数据下载
     * @param createDateScope
     */
    @GetMapping("/provinceDateDownload")
    public ResponseEntity<byte[]> provinceDataDownload(Date[] createDateScope) {
        List<ProvinceDateDownload> data = dataDownloadService.provinceDateDownload(createDateScope);
        return ProvinceDataDownloadUtils.ProvinceDataDownload(data,createDateScope);
    }

}
