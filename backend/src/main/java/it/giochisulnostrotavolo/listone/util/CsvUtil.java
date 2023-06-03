package it.giochisulnostrotavolo.listone.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CsvUtil {

    private static final Logger LOG = LoggerFactory.getLogger(CsvUtil.class);
    
	public static String matrixToCsv(List<HeaderCell> pHeader, List<List<String>> pDataMatrix, String pSeparator) {
		StringBuilder retval = new StringBuilder();
//		StringJoiner headerRowJoiner = new StringJoiner(pSeparator);
		StringBuilder headerRow = new StringBuilder();
		for (HeaderCell theHeaderCell : pHeader) {
//			headerRowJoiner.add(csvSingleLineEncode(theHeaderCell.getLabel()));
			headerRow.append(csvSingleLineEncode(theHeaderCell.getLabel())).append(pSeparator);
		}
//		retval.append(headerRowJoiner.toString()).append("\n");
		retval.append(headerRow.substring(0, headerRow.length() - pSeparator.length())).append("\n");

		for (List<String> theRow : pDataMatrix) {
//			StringJoiner contentRowJoiner = new StringJoiner(pSeparator);
			StringBuilder contentRow = new StringBuilder();
			for (String theContentCell : theRow) {
//				contentRowJoiner.add(csvSingleLineEncode(theContentCell));
				contentRow.append(csvSingleLineEncode(theContentCell)).append(pSeparator);
			}
//			retval.append(contentRowJoiner.toString()).append("\n");
			retval.append(contentRow.substring(0, contentRow.length() - pSeparator.length())).append("\n");
		}
		return retval.toString();
	}
	
	public static CellMatrix csvToMatrix(File pFile) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(pFile), "UTF8"));

		CellMatrix retval = new CellMatrix();
		String line = in.readLine();
		if (line != null) {
			for (String theHeaderValue : line.split("\\|", -1)) {
				retval.addHeaderCell(theHeaderValue);
			}
		}
		while ((line = in.readLine()) != null) {
			List<String> retLine = new ArrayList<String>();
			for (String retField : line.split("\\|", -1)) {
				retLine.add(csvSingleLineDecode(retField));
			}
			retval.addRow(retLine);
		}
		in.close();
		return retval;
	}
	
	private static String csvSingleLineEncode(String pText) {
		String escChr = "\\";
		String theText = (pText != null) ? pText : "";
		return theText.replace(escChr, escChr + escChr).replace("\r", escChr + "r").replace("\n", escChr + "n");
	}

	private static String csvSingleLineDecode(String pLine) {
		String escChr = "\\";
		String theLine = (pLine != null) ? pLine : "";
		return theLine.replace(escChr + "r", "\r").replace(escChr + "\r", escChr + "r").replace(escChr + "n", "\n").replace(escChr + "\n", escChr + "n");
	}
	
	public static List<List<Object>> readCsvDocument(InputStream filestream, String pSeparator) throws Exception {
		return browseCsvDocument(new BufferedReader(new InputStreamReader(filestream, "UTF-8")), pSeparator);
	}

	public static List<List<Object>> readCsvDocument(String filename, String pSeparator) throws Exception {
		return browseCsvDocument(new BufferedReader(new FileReader(filename)), pSeparator);
	}
	public static List<List<Object>> browseCsvDocument(BufferedReader br, String pSeparator) throws Exception {
		List<List<Object>> retval = new ArrayList<List<Object>>();
		try {
			String strLine;
			StringTokenizer st;
			int lineNumber = 0;

			//read comma separated file line by line
			while ((strLine = br.readLine()) != null) {
				lineNumber++;

				List<Object> columns = new ArrayList<Object>();

				columns.add(Boolean.TRUE);
				columns.add(lineNumber);

				//break comma separated line using ","
				st = new StringTokenizer(strLine, pSeparator);

				while (st.hasMoreTokens()) {
					//display csv values
					String code = st.nextToken();
					columns.add(code);
				}
				//reset token number
				retval.add(columns);
			}
		} catch (IOException e) {
			LOG.error("", e);
			throw new Exception("an error occurred");
		}
		return retval;
	}

}
