package com.ytkj.ytkj001.tool;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFrame;


public class Tool {
	public static void showFrameCenter(JFrame frame){
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		
		Dimension frameSize=frame.getSize();
		if(frameSize.height>screenSize.height){
			frameSize.height=screenSize.height;
		}
		if(frameSize.width>screenSize.width){
			frameSize.width=screenSize.width;
		}
		frame.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2 );
		//frame.setVisible(true);
	}
	public static void showDialogCenter(JDialog frame){
		Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
		
		Dimension frameSize=frame.getSize();
		if(frameSize.height>screenSize.height){
			frameSize.height=screenSize.height;
		}
		if(frameSize.width>screenSize.width){
			frameSize.width=screenSize.width;
		}
		frame.setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2 );
		//frame.setVisible(true);
	}
	public static Date stringToDate(String strDate) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	public static String dateToString(Date date) {

		return new SimpleDateFormat("yyyy��MM��dd��").format(date);
	}

}
