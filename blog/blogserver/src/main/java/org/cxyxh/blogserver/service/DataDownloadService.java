package org.cxyxh.blogserver.service;

import org.apache.ibatis.annotations.Param;
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
    List<UserDataDownload> userDataDownload(@Param("createDateScope") Date[] createDateScope);
}
