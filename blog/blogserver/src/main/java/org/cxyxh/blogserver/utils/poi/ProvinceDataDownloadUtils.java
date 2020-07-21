package org.cxyxh.blogserver.utils.poi;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.cxyxh.blogserver.model.ProvinceDateDownload;
import org.cxyxh.blogserver.utils.MyDateUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/13 18:19
 * @describetion :
 */
public class ProvinceDataDownloadUtils {

    public static final String ROOT_FILE_NAME = "所有省份访问数据表";

    /* 1.一个sheet如何设置多种样式
     *  2.把时间的问题修正一下
     *  3.把列宽调整一下
     *  4.针对表格的样式做调整
     *
     * */

    /**
     * 定时任务，将文件保存到本地
     *
     * @param data
     * @param createDateScope
     * @return
     */
    public static HSSFWorkbook getProvinceDateWorkbook(List<ProvinceDateDownload> data, Date[] createDateScope) {
        //0.先把日期格式化
        String[] dateStringArray = MyDateUtils.dateConverter(createDateScope);
        //1.创建一个excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成文档基本信息
        genExcelBaseInfo(workbook);
        // 生成数据
        genExcelSheet(workbook, data, dateStringArray);
        return workbook;
    }


    /**
     * 页面下载文件
     *
     * @param data            数据
     * @param createDateScope 日期
     * @return
     */
    public static ResponseEntity<byte[]> ProvinceDataDownload(List<ProvinceDateDownload> data, Date[] createDateScope) {
        //0.先把日期格式化
        String[] dateStringArray = MyDateUtils.dateConverter(createDateScope);
        //1.获取workbook
        HSSFWorkbook workbook = getProvinceDateWorkbook(data, createDateScope);
        // 数据格式转换
        ResponseEntity<byte[]> responseEntity = dataFormatConversion(workbook, dateStringArray);
        return responseEntity;
    }

    /**
     * 生成文档基本信息
     *
     * @param workbook
     */
    public static void genExcelBaseInfo(HSSFWorkbook workbook) {
        //2.创建文档摘要
        workbook.createInformationProperties();
        //3.获取并配置文档信息
        DocumentSummaryInformation docInfo = workbook.getDocumentSummaryInformation();
        //3.1文档类别 文档管理员 公司
        docInfo.setCategory(ROOT_FILE_NAME);
        docInfo.setManager("程序员小黄");
        docInfo.setCompany("org.cxyxh");
        //4.获取文档摘要信息
        SummaryInformation summInfo = workbook.getSummaryInformation();
        //4.1 文档标题 文档备注
        summInfo.setTitle(ROOT_FILE_NAME);
        summInfo.setComments("本文档由 程序员小黄 提供");
    }

    /**
     * 生成sheet
     *
     * @param workbook
     * @param data
     * @param dateStringArray
     */
    private static void genExcelSheet(HSSFWorkbook workbook, List<ProvinceDateDownload> data, String[] dateStringArray) {
        HSSFSheet sheet = workbook.createSheet(ROOT_FILE_NAME);
        //0.设置宽度
        genExcelColumWidth(sheet);
        //1.创建标题行
        genExcelTitleRow(workbook, sheet, dateStringArray);
        //2.创建表头行
        genExcelHeaderRow(workbook, sheet);
        //3.创建数据行
        genExcelDataRow(workbook, sheet, data);
    }

    /**
     * 设置excel表格宽度
     *
     * @param sheet
     */
    private static void genExcelColumWidth(HSSFSheet sheet) {
        sheet.setColumnWidth(0, 15 * 256);
        sheet.setColumnWidth(1, 25 * 256);
        sheet.setColumnWidth(2, 15 * 256);
    }

    /**
     * 创建标题行数据
     *
     * @param workbook
     * @param sheet
     */
    public static void genExcelTitleRow(HSSFWorkbook workbook, HSSFSheet sheet, String[] dateStringArray) {
        //设置标题样式
        HSSFCellStyle titleStyle = POIUtils.genHeadStyle(workbook, (byte) 255, (byte) 255, (byte) 204, true);
        //设置标题数据
        HSSFRow r0 = sheet.createRow(0);
        HSSFCell c0 = r0.createCell(0);
        String title = dateStringArray[0] + "_" + dateStringArray[1] + ROOT_FILE_NAME;
        c0.setCellValue(title);
        c0.setCellStyle(titleStyle);
        //合并单元格
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
    }

    /**
     * 创建表头行数据
     *
     * @param workbook
     * @param sheet
     */
    public static void genExcelHeaderRow(HSSFWorkbook workbook, HSSFSheet sheet) {
        //设置表头样式
        HSSFCellStyle headerStyle = POIUtils.genHeadStyle(workbook, (byte) 255, (byte) 255, (byte) 204, true);
        String[] fieldName = {"序号", "省份名称", "访问次数"};
        HSSFRow r1 = sheet.createRow(1);
        for (int idx = 0; idx < fieldName.length; idx++) {
            HSSFCell headCell = r1.createCell(idx);
            headCell.setCellValue(fieldName[idx]);
            headCell.setCellStyle(headerStyle);
        }
    }

    /**
     * 创建数据行
     *
     * @param workbook
     * @param sheet
     */
    public static void genExcelDataRow(HSSFWorkbook workbook, HSSFSheet sheet, List<ProvinceDateDownload> dataList) {
        //设置数据样式
        HSSFFont font = workbook.createFont();
        // 黑色字体，水平居中
        HSSFCellStyle dataStyle = POIUtils.genStyle(workbook, font, HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

        Integer rowindex = 2;
        for (int i = 0; i < dataList.size(); i++) {
            ProvinceDateDownload data = dataList.get(i);
            HSSFRow row = sheet.createRow(i + rowindex);

            HSSFCell c0 = row.createCell(0);
            c0.setCellValue(data.getIprovince());
            c0.setCellStyle(dataStyle);

            HSSFCell c1 = row.createCell(1);
            c1.setCellValue(data.getProvinceName());
            c1.setCellStyle(dataStyle);

            HSSFCell c2 = row.createCell(2);
            Integer visit = data.getVisits() == null ? 0 : data.getVisits();
            c2.setCellValue(visit);
            c2.setCellStyle(dataStyle);
        }
    }


    /**
     * 数据转换
     *
     * @param workbook
     * @return
     */
    private static ResponseEntity<byte[]> dataFormatConversion(HSSFWorkbook workbook, String[] dateStringArray) {
        String title = dateStringArray[0] + "_" + dateStringArray[1] + ROOT_FILE_NAME;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setContentDispositionFormData("attachment", new String((title + ".xls").getBytes("UTF-8"), "ISO-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            workbook.write(baos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.CREATED);
        return responseEntity;
    }

}
