package org.cxyxh.blogserver.service;

import org.apache.ibatis.annotations.Param;
import org.cxyxh.blogserver.model.ProvinceDateDownload;
import org.cxyxh.blogserver.model.SingelUserDataDownload;
import org.cxyxh.blogserver.model.UserDataDownload;

import java.util.Date;
import java.util.List;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/12 23:13
 * @describetion :
 */
public interface DataDownloadService {

    /**
     * 用户数据下载
     *
     * @param createDateScope 时间区间
     * @return
     */
    List<UserDataDownload> userDataDownload( Date[] createDateScope);


    /**
     * 单个用户数据下载
     *
     * @param createDateScope 时间区间
     * @param username        用户登录名
     * @return
     */
    List<SingelUserDataDownload> singelUserDataDownload(Date[] createDateScope,  String username);

    /**
     * 省份访问数据下载
     *
     * @param createDateScope 时间区间
     * @return
     */
    List<ProvinceDateDownload> provinceDateDownload(Date[] createDateScope);
}
