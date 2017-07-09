/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2016 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.xq.st_jsreport;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

public class XView {

	// 生成Jasper report 对象
	public static void viewJRXML(String jrxml) {
		InputStream input = XView.class.getResourceAsStream(jrxml);
		try {
			// JasperDesignViewer xViewer = new JasperDesignViewer(jrxml, true);
			JasperDesignViewer xViewer = new JasperDesignViewer(input, true);
			xViewer.setVisible(true);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public static void viewJRXMLFillData(String jrxml) {
		try {
			InputStream input = XView.class.getResourceAsStream(jrxml);
			OutputStream outputStream;
			outputStream = new FileOutputStream("d://jrxml_out/view.jasper");
			JasperCompileManager.compileReportToStream(input, outputStream);
			outputStream.close();

			JasperFillManager.fillReportToFile("d://jrxml_out/view.jasper", "d://jrxml_out/view.jrprint", null,
					new JREmptyDataSource(1));


			// 预览报表，false代表不是使用XML文件。
			JasperViewer view = new JasperViewer("d://jrxml_out/view.jrprint", false);
			//view.pack();
			view.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

//		viewJRXML("/view.jrxml");
//		viewJRXMLFillData("/view.jrxml");
//		viewJRXMLFillData("/antcompile/BarReport.jrxml");
//		viewJRXMLFillData("/antcompile/FooReport.jrxml");
		viewJRXMLFillData("/barbecue/BarbecueReport.jrxml");
	}

}
