/*
 *  UUBridge  - http://www.uubridge.com
 *  Technical support by Bluemeeting Team - http://bluemeeting.cn
 *  Authors: 
 *  Blueram(blueram@qq.com)  http://blueram.cn
 *  Northeagel(hnzhangshi@163.com)
 *
 *  Copyright 2006-2014 by respective authors. All rights reserved.
 * 
 */
package com.stt.document2image.office2pdf;

import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;
import org.artofsolving.jodconverter.office.OfficeUtils;

import java.io.File;
import java.io.IOException;

/**
 * 报错
 */
@Deprecated
public class JodOpenOfficeTopdf{

	public boolean isConverting = false;
	private static JodOpenOfficeTopdf instance = null;

	public static JodOpenOfficeTopdf getInstance() {
		if (instance == null) {
			instance = new JodOpenOfficeTopdf();
		}
		return instance;
	}

	public synchronized void convert(String srcFileName, String destFileName)
			throws IOException {
		OfficeManager officeManager = null;
		File home = OfficeUtils.getDefaultOfficeHome();
		if (home == null) {
			officeManager = new DefaultOfficeManagerConfiguration()
					.setOfficeHome("C:\\Program Files (x86)\\OpenOffice 4")
					.buildOfficeManager();
		} else {
			officeManager = new DefaultOfficeManagerConfiguration()
					.buildOfficeManager();
		}

		try {
			officeManager.start();

			OfficeDocumentConverter converter = new OfficeDocumentConverter(
					officeManager);
			isConverting = true;
			converter.convert(new File(srcFileName), new File(destFileName));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			officeManager.stop();
			isConverting = false;
		}
	}

	public boolean isConverting() {
		return isConverting;
	}

	public void setConverting(boolean isConverting) {
		this.isConverting = isConverting;
	}

	public static void main(String[] args) throws IOException {
		JodOpenOfficeTopdf op = new JodOpenOfficeTopdf();
		// String srcFileName=args[0];
		// String destFileName=args[1];
		String srcFileName = "d:/testoffice2pdf/test1.docx";
		String destFileName = "d:/testoffice2pdf/test1_1.pdf";
		op.convert(srcFileName, destFileName);
	}
}