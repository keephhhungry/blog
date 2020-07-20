package org.cxyxh.blogserver.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/20 22:57
 * @describetion :
 */
public class MyFileUtils {

    public static final String ROOT_PATH = "file";

    /**
     * 根据文件名字及excel生成excel文件
     *
     * @param fileName 文件名称
     * @param workbook excel
     */
    public static void genFileByWorkbook(String fileRootName, String fileName, HSSFWorkbook workbook, String Interval) {
        String path = null;
        String year = "2020";
        if (StringUtils.equals(Interval, MyDateUtils.DAY)) {
            path = ROOT_PATH + "/" + fileRootName + "/" + year + "/" + "每日汇总" + "/" + fileName;
        } else if (StringUtils.equals(Interval, MyDateUtils.WEEK)) {
            path = ROOT_PATH + "/" + fileRootName + "/" + year + "/" + "每周汇总" + "/" + fileName;
        } else if (StringUtils.equals(Interval, MyDateUtils.MONTH)) {
            path = ROOT_PATH + "/" + fileRootName + "/" + year + "/" + "每月汇总" + "/" + fileName;
        } else if (StringUtils.equals(Interval, MyDateUtils.QUARTER)) {
            path = ROOT_PATH + "/" + fileRootName + "/" + year + "/" + "季度汇总" + "/" + fileName;
        } else if (StringUtils.equals(Interval, MyDateUtils.HALF_YEAR)) {
            path = ROOT_PATH + "/" + fileRootName + "/" + year + "/" + "半年汇总" + "/" + fileName;
        } else if (StringUtils.equals(Interval, MyDateUtils.YEAR)) {
            path = ROOT_PATH + "/" + fileRootName + "/" + year + "/" + "年度汇总" + "/" + fileName;
        }
        try {
            FileOutputStream fos = new FileOutputStream(path + fileName + ".xls");
            workbook.write(fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
