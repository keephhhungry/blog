package org.cxyxh.blogserver.utils.poi;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/**
 * @ahthor : cxyxh
 * @date : 2020/7/13 19:52
 * @describetion :
 */
public class POIUtils {

    /**
     * @param workbook
     * @param r
     * @param g
     * @param b
     * @param isBorder 是否需要边框
     * @return
     */
    public static HSSFCellStyle genHeadStyle(HSSFWorkbook workbook, byte r, byte g, byte b, Boolean isBorder) {
        HSSFCellStyle headStyle = workbook.createCellStyle();
        HSSFFont boldFont = workbook.createFont();
        boldFont.setBold(true);// 粗体
        headStyle.setAlignment(HorizontalAlignment.CENTER);
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        HSSFPalette customPalette = workbook.getCustomPalette();
        customPalette.setColorAtIndex(IndexedColors.GREY_25_PERCENT.index, r, g, b);
        headStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headStyle.setWrapText(true);
        headStyle.setFont(boldFont);
        if (isBorder) {
            setBorder(headStyle);
        }
        return headStyle;
    }

    /**
     * 设置边框
     *
     * @param style
     */
    public static void setBorder(HSSFCellStyle style) {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }

    /**
     * 设置style字体，居中，边框
     *
     * @param workbook
     * @param font
     * @param align    水平对齐
     * @param valign   垂直对齐
     * @return
     */
    public static HSSFCellStyle genStyle(HSSFWorkbook workbook, HSSFFont font, HorizontalAlignment align, VerticalAlignment valign) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(align);
        style.setVerticalAlignment(valign);
        style.setFont(font);
        setBorder(style);
        return style;
    }


}
