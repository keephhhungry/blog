package org.cxyxh.blogserver.scheduled;

import org.cxyxh.blogserver.model.ProvinceDateDownload;
import org.cxyxh.blogserver.model.SingelUserDataDownload;
import org.cxyxh.blogserver.model.UserDataDownload;
import org.cxyxh.blogserver.service.DataDownloadService;
import org.cxyxh.blogserver.utils.MyDateUtils;
import org.cxyxh.blogserver.utils.poi.ProvinceDataDownloadUtils;
import org.cxyxh.blogserver.utils.poi.SingelUserDataDownloadUtils;
import org.cxyxh.blogserver.utils.poi.UserDataDownloadUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/13 23:38
 * @describetion :
 */
@Component
public class DateDownload extends QuartzJobBean {

    @Autowired
    private DataDownloadService dataDownloadService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap map = context.getJobDetail().getJobDataMap();
        String interval = map.getString("Interval");
        //生成省份访问数据表
        genProvinceData(interval);
        //生成用户访问次数表
        genUserData(interval);
    }

    /**
     * 生成省份访问数据表
     */
    public void genProvinceData(String Interval) {
        Date date = new Date();
        Date[] createDateScope = MyDateUtils.getTimeInterval(date, Interval);
        System.out.println("" + Arrays.toString(createDateScope));
        List<ProvinceDateDownload> data = dataDownloadService.provinceDateDownload(createDateScope);
//        ProvinceDataDownloadUtils.userDataDownload(data,createDateScope);
    }

    /**
     * 生成用户访问次数表
     */
    public void genUserData(String Interval) {
        Date date = new Date();
        Date[] createDateScope = MyDateUtils.getTimeInterval(date, Interval);
        System.out.println("" + Arrays.toString(createDateScope));
        List<UserDataDownload> data = dataDownloadService.userDataDownload(createDateScope);
//        UserDataDownloadUtils.userDataDownload(data, createDateScope);
    }

}
