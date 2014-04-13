package com.ytkj.ytkj001.tool;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class UIStyleTransform {
	private static class SupportedLaF {

		private final String name;
		private final LookAndFeel laf;

		SupportedLaF(String name, LookAndFeel laf) {
			this.name = name;
			this.laf = laf;
		}

		@Override
		public String toString() {
			return name;
		}
	}
	private static final List<SupportedLaF> supportedLaFs = new ArrayList<SupportedLaF>();
	public static final String NIMBUS_LAF_NAME = "Windows";//Windows  Nimbus
	private static SupportedLaF nimbusLaF;
	public static void initUI(JFrame frame) {
		UIManager.LookAndFeelInfo[] installedLafs = UIManager
				.getInstalledLookAndFeels();
		for (UIManager.LookAndFeelInfo lafInfo : installedLafs) {
			try {
				Class<?> lnfClass = Class.forName(lafInfo.getClassName());
				LookAndFeel laf = (LookAndFeel) (lnfClass.newInstance());
				if (laf.isSupportedLookAndFeel()) {
					String name = lafInfo.getName();
					SupportedLaF supportedLaF = new SupportedLaF(name, laf);
					supportedLaFs.add(supportedLaF);
					if (NIMBUS_LAF_NAME.equals(name)) {
						nimbusLaF = supportedLaF;
						UIManager.setLookAndFeel(laf);
						SwingUtilities.updateComponentTreeUI(frame);

						frame.pack();
						break;
					}
				}

			} catch (Exception ignored) {
				// If ANYTHING weird happens, don't add this L&F
			}
		}
	}

}
