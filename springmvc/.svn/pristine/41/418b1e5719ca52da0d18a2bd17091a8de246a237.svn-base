package com.tepusoft.utils;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.tepusoft.dto.StudentInfoDto;
import com.topic.dto.TopicDto;

/**
 * 功能：导出EXCEL工具类，适用于单行表头的表格
 * 
 * @author Lixiangmao
 * @since 2014/01/03
 */
public class ExportExcelUtils {
	/**
	 * @Title: exportExcel
	 * @Description: 导出Excel的方法
	 * @author: Lixiangmao
	 * @param workbook
	 * @param sheetNum
	 * @param sheetTitle
	 * @param headers
	 * @param result
	 * @param out
	 * @throws Exception
	 */

	public void exportExcel(HSSFWorkbook workbook, int sheetNum,
			String sheetTitle, String[] headers, List<TopicDto> result,
			OutputStream out) throws Exception {
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(sheetNum, sheetTitle);
		// 设置表格默认列宽度为20个字节
		sheet.setDefaultColumnWidth((short) 20);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 指定当单元格内容显示不下时自动换行
		style.setWrapText(true);
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell((short) i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text.toString());
		}
		// 遍历集合数据，产生数据行
		if (result != null) {
			int index = 1;
			for (TopicDto t : result) {
				row = sheet.createRow(index);
					HSSFCell cell0  = row.createCell((short) 0);
					cell0.setCellValue(t.getTopicName());
					HSSFCell cell1  = row.createCell((short) 1);
					cell1.setCellValue(t.getTopicType());
					HSSFCell cell2  = row.createCell((short) 2);
					cell2.setCellValue(t.getTopicMajorNames());
					HSSFCell cell3  = row.createCell((short) 3);
					cell3.setCellValue(t.getTopicPersonName());
					HSSFCell cell4  = row.createCell((short) 4);
					cell4.setCellValue(t.getTopicPersonTitle());
					HSSFCell cell5  = row.createCell((short) 5);
					cell5.setCellValue(t.getTeacherPhone());
					HSSFCell cell6  = row.createCell((short) 6);
					cell6.setCellValue(t.getTeacherEmail());
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String date = simpleDateFormat.format(t.getTopicCreateTime());
					HSSFCell cell7  = row.createCell((short) 7);
					cell7.setCellValue(date);
				index++;
			}
		}
	}
	public void exportExcel1(HSSFWorkbook workbook, int sheetNum,
			String sheetTitle, String[] headers, List<StudentInfoDto> result,
			OutputStream out) throws Exception {
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet();
		workbook.setSheetName(sheetNum, sheetTitle);
		// 设置表格默认列宽度为20个字节
		sheet.setDefaultColumnWidth((short) 20);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 指定当单元格内容显示不下时自动换行
		style.setWrapText(true);
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell((short) i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text.toString());
		}
		// 遍历集合数据，产生数据行
		if (result != null) {
			int index = 1;
			for (StudentInfoDto t : result) {
				row = sheet.createRow(index);
					HSSFCell cell0  = row.createCell((short) 0);
					cell0.setCellValue(t.getUserName());
					HSSFCell cell1  = row.createCell((short) 1);
					cell1.setCellValue(t.getStuName());
					HSSFCell cell2  = row.createCell((short) 2);
					cell2.setCellValue(t.getStuSex());
					HSSFCell cell3  = row.createCell((short) 3);
					cell3.setCellValue(t.getStuAcademy());
					HSSFCell cell4  = row.createCell((short) 4);
					cell4.setCellValue(t.getStuMajor());
					HSSFCell cell5  = row.createCell((short) 5);
					cell5.setCellValue(t.getStuSubject());
					HSSFCell cell6  = row.createCell((short) 6);
					cell6.setCellValue(t.getStuGrade());
					HSSFCell cell7  = row.createCell((short) 7);
					cell7.setCellValue(t.getStuClass());
					HSSFCell cell8  = row.createCell((short) 8);
					cell8.setCellValue(t.getStuPhone());
				    index++;
			}
		}
	}
}
