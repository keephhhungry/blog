package org.cxyxh.blogserver.scheduled.config;

import org.cxyxh.blogserver.scheduled.DateDownload;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.util.Date;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/15 21:39
 * @describetion :
 */
@Configuration
public class DataDownloadConfig {

    @Autowired
    private SpringJobFactory springJobFactory;

    @Bean
    JobDetailFactoryBean jobDetailFactoryBean() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(DateDownload.class);
        return bean;
    }

    @Bean
    SimpleTriggerFactoryBean simpleTriggerFactoryBean() {
        SimpleTriggerFactoryBean bean = new SimpleTriggerFactoryBean();
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        jobDetailFactoryBean().getJobDataMap().put("Interval","DAY");
        bean.setStartTime(new Date());
//        bean.setRepeatInterval(5000);
//        bean.setRepeatCount(2);
        return bean;
    }

    /**
     * 每天凌晨4:00的任务
     *
     * @return
     */
    @Bean
    CronTriggerFactoryBean everyDayCronTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        jobDetailFactoryBean().getJobDataMap().put("Interval","DAY");
        bean.setCronExpression("0 0 4 * * ?");
        return bean;
    }

    /**
     * 每周凌晨4:10的任务
     *
     * @return
     */
    @Bean
    CronTriggerFactoryBean everyWeekCronTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        jobDetailFactoryBean().getJobDataMap().put("Interval","WEEK");
        bean.setCronExpression("0 10 4 ? * MON");
        return bean;
    }

    /**
     * 每月凌晨4:20的任务
     *
     * @return
     */
    @Bean
    CronTriggerFactoryBean everyMonthCronTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        jobDetailFactoryBean().getJobDataMap().put("Interval","MONTH");
        bean.setCronExpression("0 20 4 1 * ?");
        return bean;
    }

    /**
     * 每季度4:30的任务
     *
     * @return
     */
    @Bean
    CronTriggerFactoryBean everyQuarterCronTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        jobDetailFactoryBean().getJobDataMap().put("Interval","QUARTER");
        bean.setCronExpression("0 30 4 1 4,7,10,1 ?");
        return bean;
    }

    /**
     * 每半年凌晨4:40的任务
     *
     * @return
     */
    @Bean
    CronTriggerFactoryBean everyHalfYearCronTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        jobDetailFactoryBean().getJobDataMap().put("Interval","HALF_YEAR");
        bean.setCronExpression("0 40 4 1 7,1 ? ");
        return bean;
    }

    /**
     * 每年凌晨4:50的任务
     *
     * @return
     */
    @Bean
    CronTriggerFactoryBean everyYearCronTriggerFactoryBean() {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setJobDetail(jobDetailFactoryBean().getObject());
        jobDetailFactoryBean().getJobDataMap().put("Interval","YEAR");
        bean.setCronExpression("0 50 4 1 1 ?");
        return bean;
    }

    /**
     * 定时任务添加过来
     *
     * @return
     */
    @Bean
    SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setTriggers(everyDayCronTriggerFactoryBean().getObject(),
                everyWeekCronTriggerFactoryBean().getObject(),
                everyMonthCronTriggerFactoryBean().getObject(),
                everyQuarterCronTriggerFactoryBean().getObject(),
                everyHalfYearCronTriggerFactoryBean().getObject(),
                everyYearCronTriggerFactoryBean().getObject(),
                simpleTriggerFactoryBean().getObject());
        bean.setJobFactory(springJobFactory);
        return bean;
    }


}
