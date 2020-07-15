package org.cxyxh.blogserver.scheduled;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/13 23:38
 * @describetion :
 */
@Component
public class DateDownload extends QuartzJobBean {


    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        //生成省份访问数据表

        //生成用户访问次数表

        //生成单一用户访问情况表
    }
}
