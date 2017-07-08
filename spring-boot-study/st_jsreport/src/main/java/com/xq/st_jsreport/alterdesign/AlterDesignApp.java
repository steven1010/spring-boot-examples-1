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
package com.xq.st_jsreport.alterdesign;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.xq.st_jsreport.XView;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRectangle;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class AlterDesignApp 
{
	
	
	/**
	 *
	 */
	public static void main(String[] args)
	{
		InputStream input=AlterDesignApp.class.getResourceAsStream("/alterdesign/AlterDesignReport.jrxml");
		try {
			OutputStream outputStream;
			outputStream = new FileOutputStream("d://jrxml_out/AlterDesignReport.jasper");
			JasperCompileManager.compileReportToStream(input, outputStream);
			outputStream.close();

			
			fill();
//			print();
			pdf();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void fill() throws JRException
	{
		long start = System.currentTimeMillis();
		File sourceFile = new File("d://jrxml_out/AlterDesignReport.jasper");
		System.err.println(" : " + sourceFile.getAbsolutePath());
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject(sourceFile);
		
		JRRectangle rectangle = (JRRectangle)jasperReport.getTitle().getElementByKey("first.rectangle");
		rectangle.setForecolor(new Color((int)(16000000 * Math.random())));
		rectangle.setBackcolor(new Color((int)(16000000 * Math.random())));

		rectangle = (JRRectangle)jasperReport.getTitle().getElementByKey("second.rectangle");
		rectangle.setForecolor(new Color((int)(16000000 * Math.random())));
		rectangle.setBackcolor(new Color((int)(16000000 * Math.random())));

		rectangle = (JRRectangle)jasperReport.getTitle().getElementByKey("third.rectangle");
		rectangle.setForecolor(new Color((int)(16000000 * Math.random())));
		rectangle.setBackcolor(new Color((int)(16000000 * Math.random())));

		JRStyle style = jasperReport.getStyles()[0];
		style.setFontSize(16f);
		style.setItalic(true);

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, (JRDataSource)null);
		
		File destFile = new File(sourceFile.getParent(), jasperReport.getName() + ".jrprint");
		JRSaver.saveObject(jasperPrint, destFile);
		
		System.err.println("Filling time : " + (System.currentTimeMillis() - start));
	}


	/**
	 * 打印机打印
	 */
	public static void print() throws JRException
	{
		long start = System.currentTimeMillis();
		JasperPrintManager.printReport("d://jrxml_out/AlterDesignReport.jrprint", true);
		System.err.println("Printing time : " + (System.currentTimeMillis() - start));
	}

	
	/**
	 *
	 */
	public static void  pdf() throws JRException
	{
		long start = System.currentTimeMillis();
		JasperExportManager.exportReportToPdfFile("d://jrxml_out/AlterDesignReport.jrprint");
		System.err.println("PDF creation time : " + (System.currentTimeMillis() - start));
	}

	
}
