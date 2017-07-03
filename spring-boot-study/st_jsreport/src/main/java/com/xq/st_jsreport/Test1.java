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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Test1 {
	// 生成Jasper文件
	public static void witchJasperFile() {
		try {
			//jrxml->jasper
			JasperCompileManager.compileReportToFile("d://jrxml/test1.jrxml", "d:/demo1.jasper");
			File sourceFile1 = new File("d:/demo1.jasper");
			//jrxml->jasper
			String jasperF1=JasperCompileManager.compileReportToFile("d://jrxml/test1.jrxml");
			System.out.println("=====");
			System.out.println(jasperF1);
			File sourceFile2 = new File(jasperF1);
			//Jrxml流->Jasper流
			FileInputStream inputStream3 = new FileInputStream("d://jrxml/test1.jrxml");
			OutputStream outputStream3 = new FileOutputStream("d://demo3.jasper");
			JasperCompileManager.compileReportToStream(inputStream3, outputStream3);
			

			//jrxml->JasperDesign
			JasperDesign design4 = JRXmlLoader.load("d://jrxml/test1.jrxml");

			File sourceFile5 = new File("d://jrxml/test1.jrxml");
			JasperDesign design5 = JRXmlLoader.load(sourceFile5);

			FileInputStream inputStream6 = new FileInputStream("d://jrxml/test1.jrxml");
			JasperDesign design6 = JRXmlLoader.load(inputStream6);
			
			//JasperDesign->jasper文件
			JasperCompileManager.compileReportToFile(design4, "d:/demo4.jasper");
			//JasperDesign->jasper流
			OutputStream outputStream5 = new FileOutputStream("d://demo5.jasper");
			JasperCompileManager.compileReportToStream(design5, outputStream5);

			// JasperDesign->JasperReport
			JasperReport jasperReport1 = (JasperReport) JRLoader.loadObject(sourceFile1);
			JasperReport jasperReport2 = (JasperReport) JRLoader.loadObject(sourceFile2);
			JasperReport report4 = JasperCompileManager.compileReport(design4);
			JasperReport report5 = JasperCompileManager.compileReport(design5);
			JasperReport report6 = JasperCompileManager.compileReport(design6);

			// JasperReport->JasperPrint
			JasperPrint jasperPrint1 = JasperFillManager.fillReport(jasperReport1, new HashMap<String, Object>(),
					new JREmptyDataSource());
			JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport2, new HashMap<String, Object>(),
					new JREmptyDataSource());
			JasperPrint jasperPrint4 = JasperFillManager.fillReport(report4, new HashMap<String, Object>(),
					new JREmptyDataSource());
			JasperPrint jasperPrint5 = JasperFillManager.fillReport(report5, new HashMap<String, Object>(),
					new JREmptyDataSource());
			JasperPrint jasperPrint6 = JasperFillManager.fillReport(report6, new HashMap<String, Object>(),
					new JREmptyDataSource());
//			JasperPrint jasperPrint4 = JasperFillManager.fillReport(report4, new HashMap<String, Object>(),
//					new JREmptyDataSource());

			// JasperPrint->生成输出文件
			JasperExportManager.exportReportToPdfFile(jasperPrint1, "d://obj1.pdf");
			JasperExportManager.exportReportToPdfFile(jasperPrint2, "d://obj2.pdf");
			JasperExportManager.exportReportToPdfFile(jasperPrint4, "d://obj4.pdf");
			JasperExportManager.exportReportToPdfFile(jasperPrint5, "d://obj5.pdf");
			JasperExportManager.exportReportToPdfFile(jasperPrint6, "d://obj6.pdf");
//			JasperExportManager.exportReportToPdfFile(jasperPrint4, "d://obj4.pdf");
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


	// 生成Jasper report 对象
	public static void withJasperObject() {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			jasperReport = JasperCompileManager.compileReport("d://test1.jrxml"); // 生成JasperReport对象
			jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String, Object>(), new JREmptyDataSource());
			JasperExportManager.exportReportToPdfFile(jasperPrint, "d://obj1.pdf");
			JasperExportManager.exportReportToHtmlFile(jasperPrint, "d://obj1.html");

			JasperExportManager.exportReportToXmlFile(jasperPrint, "d://obj1.xml", false);
			String con = JasperExportManager.exportReportToXml(jasperPrint);
			System.out.println(con);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		witchJasperFile();
		// withJasperObject();
	}

}
