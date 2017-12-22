package cn.stevenjack.dormitory.Utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * 共分为六部完成根据模板导出excel操作：
 * 第一步、设置excel模板路径（setSrcPath）
 * 第二步、设置要生成excel文件路径（setDesPath）
 * 第三步、设置模板中哪个Sheet列（setSheetName）
 * 第四步、获取所读取excel模板的对象（getSheet）
 * 第五步、设置数据（分为6种类型数据：setCellStrValue、setCellDateValue、setCellDoubleValue、setCellBoolValue、setCellCalendarValue、setCellRichTextStrValue）<br/>
 * 第六步、完成导出 （exportToNewFile）
 */
class Template2Excel {
    private String srcXlsPath = "";
    private String desXlsPath = "";
    private String sheetName = "";
    private Workbook wb = null;
    private Sheet sheet = null;

    /**
     * 第一步、设置excel模板路径
     *
     * @param srcXlsPath
     */
    public void setSrcPath(String srcXlsPath) {
        this.srcXlsPath = srcXlsPath;
    }

    /**
     * 第二步、设置要生成excel文件路径
     *
     * @param desXlsPath
     */
    public void setDesPath(String desXlsPath) {
        this.desXlsPath = desXlsPath;
    }

    /**
     * 第三步、设置模板中哪个Sheet列
     *
     * @param sheetName
     */
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    /**
     * 第四步、获取所读取excel模板的对象
     */
    public void getSheet() throws IOException, InvalidFormatException {
        File fi = new File(srcXlsPath);
        if (!fi.exists()) {
            //System.out.println("模板文件:"+srcXlsPath+"不存在!");
            return;
        }
        if (srcXlsPath.endsWith(".xls")) {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(fi));
            wb = new HSSFWorkbook(fs);
        } else if (srcXlsPath.endsWith(".xlsx")) {
            wb = new XSSFWorkbook(fi);
        } else return;
        sheet = wb.getSheet(sheetName);
    }

    /**
     * 第五步、设置字符串类型的数据
     *
     * @param rowIndex --行值
     * @param cellnum  --列值
     * @param value    --字符串类型的数据
     */
    public void setCellStrValue(int rowIndex, int cellnum, String value) {
        Cell cell = sheet.getRow(rowIndex).getCell(cellnum);
        cell.setCellValue(value);
    }

    /**
     * 第五步、设置日期/时间类型的数据
     *
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--日期/时间类型的数据
     */
    public void setCellDateValue(int rowIndex, int cellnum, Date value) {
        Cell cell = sheet.getRow(rowIndex).getCell(cellnum);
        cell.setCellValue(value);
    }

    /**
     * 第五步、设置浮点类型的数据
     *
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--浮点类型的数据
     */
    public void setCellDoubleValue(int rowIndex, int cellnum, double value) {
        Cell cell = sheet.getRow(rowIndex).getCell(cellnum);
        cell.setCellValue(value);
    }

    /**
     * 第五步、设置Bool类型的数据
     *
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--Bool类型的数据
     */
    public void setCellBoolValue(int rowIndex, int cellnum, boolean value) {
        Cell cell = sheet.getRow(rowIndex).getCell(cellnum);
        cell.setCellValue(value);
    }

    /**
     * 第五步、设置日历类型的数据
     *
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--日历类型的数据
     */
    public void setCellCalendarValue(int rowIndex, int cellnum, Calendar value) {
        Cell cell = sheet.getRow(rowIndex).getCell(cellnum);
        cell.setCellValue(value);
    }

    /**
     * 第五步、设置富文本字符串类型的数据。可以为同一个单元格内的字符串的不同部分设置不同的字体、颜色、下划线
     *
     * @param rowIndex--行值
     * @param cellnum--列值
     * @param value--富文本字符串类型的数据
     */
    public void setCellRichTextStrValue(int rowIndex, int cellnum,
                                        RichTextString value) {
        Cell cell = sheet.getRow(rowIndex).getCell(cellnum);
        cell.setCellValue(value);
    }

    /**
     * 第六步、完成导出
     */
    public byte[] exportToNewFile() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        wb.write(byteArrayOutputStream);
//        out.close();
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 获取指定单元格的值
     *
     * @param rowIndex
     * @param cellnum
     * @return
     */
    public String getCellValue(int rowIndex, int cellnum) {
        Cell cell = sheet.getRow(rowIndex).getCell(cellnum);
        return cell.getStringCellValue();
    }
}