package com.ig.qa.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.ig.qa.base.TestBase;

public class ExcelUtils {

	private final static Logger logger = Logger.getLogger(ExcelUtils.class);

	@DataProvider(name = "data")
	public static Object[] dataSupplier() {

		Object[] obj = null;
		try {

			File file = new File(TestBase.xlPath);

			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheetAt(0);

			wb.close();
			int lastRowNum = sheet.getLastRowNum();
			int lastCellNum = sheet.getRow(0).getLastCellNum();
			obj = new Object[lastRowNum];

			for (int i = 0; i < lastRowNum; i++) {

				Map<Object, Object> datamap = new HashMap<>();
				for (int j = 0; j < lastCellNum; j++) {

					String mapKey = sheet.getRow(0).getCell(j).getStringCellValue();
					String mapValue = "";
					Cell cell = sheet.getRow(i + 1).getCell(j);

					if (sheet.getRow(i + 1).getCell(j) == null) {
						continue;
					} else if (cell.getCellType() == CellType.STRING) {
						mapValue = sheet.getRow(i + 1).getCell(j).getStringCellValue();
						datamap.put(mapKey, mapValue);

					} else if (cell.getCellType() == CellType.NUMERIC) {
						double mapValue_num = sheet.getRow(i + 1).getCell(j).getNumericCellValue();
						int mapValueInt = (int) mapValue_num;
						datamap.put(mapKey, mapValueInt);
					}
				}
				obj[i] = datamap;

			}

		} catch (FileNotFoundException e) {

			logger.error(Constant.FILE_EXCEPTION + e);
		}

		catch (IOException e) {

			logger.error(Constant.IO_EXCEPTION + e);
		}

		/*
		 * catch (Exception e) { logger.error(Constant.EXCEPTION_MESSAGE + e); }
		 */
		return obj;

	}
}
