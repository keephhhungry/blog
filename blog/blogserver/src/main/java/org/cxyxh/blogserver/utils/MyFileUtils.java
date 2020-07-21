package org.cxyxh.blogserver.utils;

import com.mchange.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
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
        String type = null;
        String year = MyDateUtils.getYear();
        //根据创建的文件类型放入不同的文件夹
        if (StringUtils.equals(Interval, MyDateUtils.DAY)) {
            type = "每日汇总";
        } else if (StringUtils.equals(Interval, MyDateUtils.WEEK)) {
            type = "每周汇总";
        } else if (StringUtils.equals(Interval, MyDateUtils.MONTH)) {
            type = "每月汇总";
        } else if (StringUtils.equals(Interval, MyDateUtils.QUARTER)) {
            type = "季度汇总";
        } else if (StringUtils.equals(Interval, MyDateUtils.HALF_YEAR)) {
            type = "半年汇总";
        } else if (StringUtils.equals(Interval, MyDateUtils.YEAR)) {
            type = "年度汇总";
        }
        String directory = ROOT_PATH + "/" + year + "/" + fileRootName + "/" + type;
        boolean flag = genDirectory(directory);
        if (flag) {
            try {
                FileOutputStream fos = new FileOutputStream(directory + "/" + fileName + ".xls");
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

    /**
     * 创建目录
     *
     * @param path 路径
     * @return
     */
    public static boolean genDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            //创建目录
            boolean flag = directory.mkdirs();
            if (flag) {
                System.out.println("创建目录" + directory + "成功！");
                return true;
            } else {
                System.out.println("创建目录" + directory + "失败！");
                return false;
            }
        }
        return true;
    }


}
