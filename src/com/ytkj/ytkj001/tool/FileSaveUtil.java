package com.ytkj.ytkj001.tool;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileSaveUtil {
	private static String filesave = null;// 用于存放打开文件地址 和文件名
	private static String filesavename = null;

	public static void save(Frame parent, String[] name, String[][] datas) {
		FileDialog filedialog_save = new FileDialog(parent, "保存文件");
		filedialog_save.setFile(".xls");
		filedialog_save.setMode(FileDialog.SAVE);
		filedialog_save.setVisible(true);
		filesave = filedialog_save.getDirectory();// 返回文件对话框中显示的文件所属的目录
		filesavename = filedialog_save.getFile();// 返回当前文件对话框中显示的文件名的字符串表示
		Boolean flag = false;
		if (filesave != null && filesavename != null) {
			try {
				File file = new File(filesave + filesavename + ".xls");
				if (file.exists()) {
					file.delete();
				}
				flag = ExcelUtil.createExcel(new FileOutputStream(file), name,
						datas);
			} catch (Exception e) {
				flag = false;
			}
		}
		if (!flag) {
			return;
		} else {
			JOptionPane.showMessageDialog(null, "导出成功");
		}
	}
}
