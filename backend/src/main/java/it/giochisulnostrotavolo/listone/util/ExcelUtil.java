package it.giochisulnostrotavolo.listone.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class ExcelUtil {

	 private static final Logger LOG = LoggerFactory.getLogger(ExcelUtil.class);
	 private static final int ALPHABET_LETTER_NUM = 26;
	 
	public static Workbook buildExcelWorkbook(String pSheetName, List<HeaderCell> pHeader, List<List<String>> pDataMatrix) throws Exception {
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook();
			XSSFSheet sheet = (XSSFSheet) workbook.createSheet((pSheetName != null) ? pSheetName : "sheet");
			CellStyle headerStyle = getStyle(workbook, true, false, false, false, true);
			CellStyle readonlyStyle = getStyle(workbook, false, true, false, false, true);
			CellStyle contentStyle = getStyle(workbook, false, false, false, false, false);

			HashSet<Integer> readonlyCols = new HashSet<Integer>();
			int colNumber = 0;
			int rowNumber = 0;
			// Costruzione header
			Row headerRow = sheet.createRow(rowNumber);
			for (HeaderCell theHeaderCell : pHeader) {
				if (theHeaderCell.isReadonly()) {
					readonlyCols.add(colNumber);
				}
				Cell cell = headerRow.createCell(colNumber);
				cell.setCellStyle(headerStyle);
				cell.setCellValue(theHeaderCell.getLabel());
				colNumber++;
			}
			if (!readonlyCols.isEmpty()) {
				// se esiste almeno una colonna readonly allora proteggo  il foglio
				// resta comunque consentito ridimensionare e applicare filtri
				sheet.protectSheet("mas4dm1n");
				sheet.lockFormatColumns(false);
				sheet.lockFormatRows(false);
				sheet.lockFormatCells(false);
				sheet.lockAutoFilter(false);
				sheet.lockSort(false);
			}

			for (List<String> theRow : pDataMatrix) {
				rowNumber++;
				Row contentRow = sheet.createRow(rowNumber);
				colNumber = 0;
				for (String theContentCell : theRow) {
					CellStyle styleToApply = readonlyCols.contains(colNumber) ? readonlyStyle : contentStyle;
					Cell cell = contentRow.createCell(colNumber);
					cell.setCellStyle(styleToApply);
					cell.setCellValue(theContentCell);
					colNumber++;
				}
			}

			for (int i = 0; i < colNumber; i++) {
				sheet.autoSizeColumn(i);
			}

		} catch (Exception e) {
			throw e;
		}
		return workbook;
	}
	
	
	private static CellStyle getStyle(Workbook workbook, boolean isHeader, boolean isHighlighted, boolean isBold, boolean isCentered, boolean isLocked) {

		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();

		if (isHeader) {
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
			font.setBold(true);
			font.setItalic(true);
			style.setFont(font);
		} else if (isHighlighted) {
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		}
		if (isBold) {
			font.setBold(true);
		}
		style.setAlignment((isHeader || isCentered) ? HorizontalAlignment.CENTER : HorizontalAlignment.LEFT);
		style.setVerticalAlignment(VerticalAlignment.CENTER);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setWrapText(true);
		style.setLocked(isLocked);
		return style;
	}
	
	public static CellMatrix excelToMatrix(File pFile) {
		CellMatrix retval = new CellMatrix();
		try {
			ZipSecureFile.setMinInflateRatio(-1.0d);
			Workbook workbook = WorkbookFactory.create(new FileInputStream(pFile));
			Sheet sheet = workbook.getSheetAt(0);  //Get first sheet from the workbook
			Iterator<Row> rowIterator = sheet.iterator();
			if (rowIterator.hasNext()) {
				Row headerRow = rowIterator.next();
				Iterator<Cell> headerCellIterator = headerRow.cellIterator();
				while (headerCellIterator.hasNext()) {
					String theHeaderValue = cellToString(headerCellIterator.next());
					retval.addHeaderCell(theHeaderValue);
				}
			}

			while (rowIterator.hasNext()) {
				Row contentRow = rowIterator.next();
				List<String> retLine = new ArrayList<String>();
				Iterator<Cell> contentCellIterator = contentRow.cellIterator();
				while (contentCellIterator.hasNext()) {
					String contentCellValue = cellToString(contentCellIterator.next());
					retLine.add(contentCellValue);
				}
				retval.addRow(retLine);
			}
		} catch (Exception e) {
			//logger.warn("", e);
		}
		return retval;
	}

	private static String cellToString(Cell pCell) {
		String retval = "";
		if (pCell != null) {
			switch (pCell.getCellType()) {
				case BOOLEAN:
					retval = "" + pCell.getBooleanCellValue();
					break;
				case NUMERIC:
					retval = "" + pCell.getNumericCellValue();
					break;
				case STRING:
					retval = pCell.getStringCellValue();
					break;
				case FORMULA:
					switch (pCell.getCachedFormulaResultType() ) {
						case NUMERIC:
							retval = "" + pCell.getNumericCellValue();
							break;
						case STRING:
							retval = "" + pCell.getRichStringCellValue();
							break;
					default:
						break;
					}
					break;
			default:
				break;
			}
		}
		return retval;
	}
	
	public static List<List<Object>> readExcelDocument(InputStream filename, String excelExt) throws Exception {
		Workbook workbook = null;

		try {
			ZipSecureFile.setMinInflateRatio(-1.0d);
			workbook = WorkbookFactory.create(filename);

		} catch (FileNotFoundException e) {
			LOG.warn("", e);
		} catch (IOException e) {
			LOG.warn("", e);
		}

		return browseExcelDocument(workbook);
	}
	
	public static List<List<Object>> browseExcelDocument(Workbook workbook) throws Exception {
		List<List<Object>> retval = new ArrayList<List<Object>>();
		//Get first sheet from the workbook
		Sheet sheet = workbook.getSheetAt(0);
		int columnNumber = 10;//sheet.getRow(0).getLastCellNum();

		//Iterate through each rows from first sheet
		int rownum = 0;
		int colnum;
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			rownum++;
			Row row = rowIterator.next();
			List<Object> columns = new ArrayList<Object>();
			columns.add(Boolean.TRUE);
			columns.add(Integer.toString(rownum));

			//For each row, iterate through each columns
			colnum = 0;
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				colnum++;
				Object value = "";
				Cell cell = cellIterator.next();
				switch (cell.getCellType()) {
					case BOOLEAN:
						value = cell.getBooleanCellValue();
						break;
					case NUMERIC:
						value = cell.getNumericCellValue();
						break;
					case STRING:
						value = cell.getStringCellValue();
						break;
					case FORMULA:
						switch (cell.getCachedFormulaResultType()) {
							case NUMERIC:
								value = cell.getNumericCellValue();
								break;
							case STRING:
								value = cell.getRichStringCellValue();
								break;
						default:
							break;
						}
						break;
				default:
					break;
				}
				columns.add(value);
			}
			// Riempio eventuali lacune in caso di righe con meno celle
			for (int i = colnum; i < columnNumber; i++) {
				columns.add("");
			}
			if (colnum < columnNumber) {
				LOG.info("Riga " + rownum + " - il numero di colonne era " + colnum + " --> e' stato necessario portarlo a " + columnNumber);
			}

			retval.add(columns);
		}
		return retval;
	}
	
	public static String getExcelColumnName(int pColNumber) {
		String firstChar = getCharForNumber((pColNumber - 1) / ALPHABET_LETTER_NUM);
		String lastChar = getCharForNumber(((pColNumber - 1) % ALPHABET_LETTER_NUM) + 1);
		return firstChar + lastChar;
	}
	
	private static String getCharForNumber(int pIndex) {
		return (pIndex > 0 && pIndex <= ALPHABET_LETTER_NUM) ? String.valueOf((char) (pIndex + 64)) : "";
	}

	
	

	public static Workbook buildExcelWorkbookForPricePack(String filename, String modelName, boolean protectedDodument, Map<String, List<CellExcel>> pData) throws Exception {
		LOG.info("filename = " + filename + " - modelName = " + modelName + " - protectedDodument = " + protectedDodument);
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(modelName);

		if (protectedDodument) {
			sheet.protectSheet("password");
		}

		int columnsNumber = maxColumns(pData);
		Map<String, CellStyle> stylesMap = new HashMap<String, CellStyle>();
		Set<String> keyset = pData.keySet();
		for (String key : keyset) {
			Row row = sheet.createRow(Integer.parseInt(key));
			List<CellExcel> objList = pData.get(key);
			int rowNumber = Integer.parseInt(key);
			int colnum = 0;
			for (CellExcel masCell : objList) {
				Cell cell = row.createCell(colnum);
				String theStyleKey = masCell.getStyleKey();
				CellStyle style = stylesMap.get(theStyleKey);
				if (style == null) {
					LOG.info("riga/colonna " + rowNumber + "/" + colnum + " - stile assente, da creare - " + theStyleKey);
					style = getStyle(workbook, masCell);
					stylesMap.put(theStyleKey, style);
				} else {
					LOG.debug("riga/colonna " + rowNumber + "/" + colnum + " - stile esistente, riuso   - " + theStyleKey);
				}
				cell.setCellStyle(style);

				if (masCell.isMerged()) {
					CellRangeAddress cra = new CellRangeAddress(rowNumber, rowNumber, 0, columnsNumber);
					sheet.addMergedRegion(cra);
				}

				setValue(masCell, cell);
				colnum++;
			}
		}

		//Aggiunto per autodimensionare tutte le colonne
		for (int ind = 0; ind < keyset.size(); ind++) {
			sheet.autoSizeColumn(ind + 1);
		}
		return workbook;
	}

	public static void setValue(CellExcel masCell, Cell cell) {
		if (masCell.getValue() instanceof Date) {
			cell.setCellValue((Date) masCell.getValue());
		} else if (masCell.getValue() instanceof Boolean) {
			cell.setCellValue((Boolean) masCell.getValue());
		} else if (masCell.getValue() instanceof String) {
			cell.setCellValue((String) masCell.getValue());
		} else if (masCell.getValue() instanceof Double) {
			cell.setCellValue((Double) masCell.getValue());
		} else if (masCell.getValue() instanceof Integer) {
			cell.setCellValue((Integer) masCell.getValue());
		}

		if (masCell.getFormula() != null) {
			cell.setCellFormula(masCell.getFormula());
		}
	}
	
	public static int maxColumns(Map<String, List<CellExcel>> dataOut) {
		int maxColumns = 0;
		Set<String> keyset = dataOut.keySet();
		for (String key : keyset) {
			List<CellExcel> objList = dataOut.get(key);
			int retval = objList.size() - 1;

			if (retval > maxColumns) {
				maxColumns = retval;
			}
		}
		return maxColumns;
	}
	
	
	public static CellStyle getStyle(Workbook workbook, CellExcel masCell) {

		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();

		if (masCell.isHead()) {
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			font.setBold(true);
			font.setItalic(true);
			style.setFont(font);
			switch (masCell.getSection()) {
				case CellExcel.SECTION_EQUIPMENTS:
//					System.out.println("riga/colonna " + rowNumber + "/" + colNumber + " --> LIGHT_BLUE");
					style.setFillForegroundColor(IndexedColors.AQUA.getIndex());
					break;
				case CellExcel.SECTION_BASE_PRICE:
//					System.out.println("riga/colonna " + rowNumber + "/" + colNumber + " --> LIGHT_ORANGE");
					style.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
					break;
				case CellExcel.SECTION_IMPORTED:
//					System.out.println("riga/colonna " + rowNumber + "/" + colNumber + " --> LIGHT_GREEN");
					style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
					break;
				case CellExcel.SECTION_NOT_IMPORTED:
//					System.out.println("riga/colonna " + rowNumber + "/" + colNumber + " --> LIGHT_YELLOW");
					style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
					break;
				default:
					break;
			}
		} else if (masCell.isHighlighted()) {
//			System.out.println("riga/colonna " + rowNumber + "/" + colNumber + " --> LIGHT_YELLOW");
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		}
//		if (colNumber == 2 || rowNumber == 13 || rowNumber == 14 || rowNumber == 15 || rowNumber == 16) {
//			System.out.println("riga/colonna " + rowNumber + "/" + colNumber + " - isHighlighted = " + masCell.isHighlighted()+ " - isLocked = " + masCell.isLocked() + " --> VALUE = " + masCell.getValue());
//		}

		if (masCell.isBold()) {
//			System.out.println("riga/colonna " + rowNumber + "/" + colNumber + " - isBold");
			font.setBold(true);
		}
		if (masCell.isCentered()) {
//			System.out.println("riga/colonna " + rowNumber + "/" + colNumber + " - isCentered");
			style.setAlignment(HorizontalAlignment.CENTER);
		}
		if (masCell.isPercentage()) {
//			System.out.println("riga/colonna " + rowNumber + "/" + colNumber + " - isPercentage");
			style.setDataFormat(workbook.createDataFormat().getFormat("0.00%"));
		}
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setLocked(masCell.isLocked());
//		style.setLocked(false);
		return style;
	}
}
