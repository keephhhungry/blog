package org.cxyxh.blogserver.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/13 18:23
 * @describetion :
 */
public class MyDateUtils {

    /* 日、周、月、季度、半年、年 */
    public static final String DAY = "DAY";
    public static final String WEEK = "WEEK";
    public static final String MONTH = "MONTH";
    public static final String QUARTER = "QUARTER";
    public static final String HALF_YEAR = "HALF_YEAR";
    public static final String YEAR = "YEAR";

    /**
     * 把日期数组转换成字符串日期数组
     *
     * @param createDateScope
     * @return
     */
    public static String[] dateConverter(Date[] createDateScope) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String[] dateStringArray = new String[2];
        for (int i = 0; i < createDateScope.length; i++) {
            dateStringArray[i] = sdf.format(createDateScope[i]);
        }
        return dateStringArray;
    }


    /**
     * 获取时间间隔字符串
     *
     * @return
     */
    public static Date[] getTimeInterval(Date date, String Interval) {
        Date beginDate = null;
        if (StringUtils.equals(Interval, DAY)) {
            beginDate = DateUtils.addWeeks(date, -1);
        } else if (StringUtils.equals(Interval, WEEK)) {
            beginDate = DateUtils.addWeeks(date, -1);
        } else if (StringUtils.equals(Interval, MONTH)) {
            beginDate = DateUtils.addMonths(date, -1);
        } else if (StringUtils.equals(Interval, QUARTER)) {
            beginDate = DateUtils.addMonths(date, -3);
        } else if (StringUtils.equals(Interval, HALF_YEAR)) {
            beginDate = DateUtils.addMonths(date, -6);
        } else if (StringUtils.equals(Interval, YEAR)) {
            beginDate = DateUtils.addYears(date, -1);
        }
        Date endDate = DateUtils.addDays(date, -1);
        Date[] createDateScope = new Date[2];
        createDateScope[0] = beginDate;
        createDateScope[1] = endDate;
        return createDateScope;
    }

}
