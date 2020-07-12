package org.cxyxh.blogserver.service.impl;

import org.cxyxh.blogserver.mapper.DataDownloadMapper;
import org.cxyxh.blogserver.model.UserDataDownload;
import org.cxyxh.blogserver.service.DataDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/12 23:13
 * @describetion :
 */
@Service
public class DataDownloadImpl implements DataDownloadService {

    @Autowired
    private DataDownloadMapper dataDownloadMapper;

    /**
     * 用户数据下载
     *
     * @param createDateScope 时间区间
     * @return
     */
    @Override
    public List<UserDataDownload> userDataDownload(Date[] createDateScope) {
        return dataDownloadMapper.userDataDownload(createDateScope);
    }
}
