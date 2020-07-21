package org.cxyxh.blogserver.scheduled;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.cxyxh.blogserver.model.ProvinceDateDownload;
import org.cxyxh.blogserver.model.UserDataDownload;
import org.cxyxh.blogserver.service.DataDownloadService;
import org.cxyxh.blogserver.utils.MyDateUtils;
import org.cxyxh.blogserver.utils.MyFileUtils;
import org.cxyxh.blogserver.utils.poi.ProvinceDataDownloadUtils;
import org.cxyxh.blogserver.utils.poi.UserDataDownloadUtils;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
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
        System.out.println("" + interval);
        try {
            //生成省份访问数据表
            genProvinceData(interval);
            //生成用户访问次数表
            genUserData(interval);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成省份访问数据表
     */
    public void genProvinceData(String Interval) throws ParseException {
        Date date = new Date();
        //根据间隔，获取时间范围
        Date[] createDateScope = MyDateUtils.getTimeInterval(date, Interval);
        List<ProvinceDateDownload> data = dataDownloadService.provinceDateDownload(createDateScope);
        HSSFWorkbook workbook = ProvinceDataDownloadUtils.getProvinceDateWorkbook(data, createDateScope);
        String[] dateArr = MyDateUtils.dateConverter(createDateScope);
        String fileName = dateArr[0] + "_" + dateArr[1] + "所有省份访问数据表";
        String fileRootName = "所有省份访问数据表";
        MyFileUtils.genFileByWorkbook(fileRootName, fileName, workbook, Interval);
    }

    /**
     * 生成用户访问次数表
     */
    public void genUserData(String Interval) {
        Date date = new Date();
        //根据间隔，获取时间范围
        Date[] createDateScope = MyDateUtils.getTimeInterval(date, Interval);
        List<UserDataDownload> data = dataDownloadService.userDataDownload(createDateScope);
        HSSFWorkbook workbook = UserDataDownloadUtils.getUserDateWorkbook(data, createDateScope);
        String[] dateArr = MyDateUtils.dateConverter(createDateScope);
        String fileName = dateArr[0] + "_" + dateArr[1] + "用户登录数据表";
        String fileRootName = "用户登录数据表";
        MyFileUtils.genFileByWorkbook(fileRootName, fileName, workbook, Interval);
    }

}
