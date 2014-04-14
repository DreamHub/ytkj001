package com.ytkj.ytkj001.tool;

import java.io.OutputStream;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtil {
	public static Boolean createExcel(OutputStream os, String[] name,
			String[][] datas) {
		try {
			// 创建工作薄
			WritableWorkbook workbook = Workbook.createWorkbook(os);
			// 创建新的一页
			WritableSheet sheet = workbook.createSheet("Sheet1", 0);
			// 创建要显示的列名
			WritableFont color = new WritableFont(WritableFont.ARIAL);// 选择字体
			color.setColour(Colour.BLACK);// 设置字体颜色为黑色
			WritableCellFormat colorFormat = new WritableCellFormat(color);
			for (int i = 0; i < name.length; i++) {
				Label columnname = new Label(i, 0, name[i], colorFormat);
				sheet.setColumnView(i, 28);
				sheet.addCell(columnname);
			}
			WritableFont format = new WritableFont(WritableFont.ARIAL);// 选择字体
			format.setColour(Colour.GRAY_50);// 设置字体颜色为灰色
			WritableCellFormat Format = new WritableCellFormat(format);
			for (int i = 0; i < datas.length; i++) {
				Boolean Flag = false;
				for (int j = datas[1].length - 1; j < datas[1].length; j++) {
					if ("1".equals(datas[i][j]))
						Flag = true;
					else
						Flag = false;
				}
				if (Flag) {
					for (int j = 0; j < datas[1].length - 1; j++) {
						Label columnname = new Label(j, i + 1, datas[i][j],
								colorFormat);
						sheet.addCell(columnname);
					}
				} else {
					for (int j = 0; j < datas[1].length - 1; j++) {
						Label columnname = new Label(j, i + 1, datas[i][j],
								Format);
						sheet.addCell(columnname);
					}
				}
			}
			// WritableFont font2 = new WritableFont(WritableFont.ARIAL, 14,
			// WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
			// Colour.GRAY_50);
			// WritableCellFormat cellFormat2 = new WritableCellFormat(font2);
			// Label example = new Label(0, 2, "数据示例", cellFormat2);
			// sheet.addCell(example);
			// // 浮点数据
			// // 设置下划线
			// WritableFont underline = new WritableFont(WritableFont.ARIAL,
			// WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD, false,
			// UnderlineStyle.SINGLE);
			// WritableCellFormat greyBackground = new
			// WritableCellFormat(underline);
			// greyBackground.setBackground(Colour.GRAY_25);// 设置背景颜色为灰色
			// Number number = new Number(1, 2, 3.1415926535, greyBackground);
			// sheet.addCell(number);
			// // 整形数据
			// WritableFont boldNumber = new WritableFont(WritableFont.ARIAL,
			// 10,
			// WritableFont.BOLD);// 黑体
			// WritableCellFormat boldNumberFormate = new WritableCellFormat(
			// boldNumber);
			// Number ints = new Number(2, 2, 15042699, cellFormat2);
			// sheet.addCell(ints);
			// // 布尔型数据
			// // Boolean bools = new Boolean(3, 2, cellFormat2);
			// // sheet.addCell(bools);
			// // 日期型数据
			// // 设置黑体和下划线
			// WritableFont boldDate = new WritableFont(WritableFont.ARIAL,
			// WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD, false,
			// UnderlineStyle.SINGLE);
			// WritableCellFormat boldDateFormate = new
			// WritableCellFormat(boldDate,
			// DateFormats.FORMAT1);
			// Calendar c = Calendar.getInstance();
			// Date date = c.getTime();
			// DateTime dt = new DateTime(4, 2, date, cellFormat2);
			// sheet.addCell(dt);
			// 把创建的内容写入到输出流中，并关闭输出流
			workbook.write();
			workbook.close();
			os.flush();
			os.close();
			return true;
		} catch (Exception e) {
			return false;
		}

	}
}
