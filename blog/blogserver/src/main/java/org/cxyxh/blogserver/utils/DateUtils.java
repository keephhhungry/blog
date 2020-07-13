package org.cxyxh.blogserver.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/13 18:23
 * @describetion :
 */
public class DateUtils {

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

}
