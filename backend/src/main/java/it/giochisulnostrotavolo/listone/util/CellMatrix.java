package it.giochisulnostrotavolo.listone.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;



public class CellMatrix {

	private final List<String> headerList;
	private final List<List<String>> contentMatrix;

	public CellMatrix() {
		this.headerList = new LinkedList<String>();
		this.contentMatrix = new ArrayList<List<String>>();
	}

	public int getHeaderFieldNumber() {
		return headerList.size();
	}

	public int getHeaderPosition(String pHeaderValue) {
		return headerList.indexOf(pHeaderValue);
	}

	public int getHeaderPosition(List<String> pHeaderValueList) {
		int retval = -1;
		for (String headerVal : pHeaderValueList) {
			retval = headerList.indexOf(headerVal);
			if (retval >= 0) {
				break;
			}
		}
		return retval;
	}

	public List<Object> getHeaderMissingColumnList(List<Object> pHeaderValueList) {
		return this.getFilteredHeadeCols(pHeaderValueList, false);
	}

//	public List<String> getHeaderExistingColumnList(List<String> pHeaderValueList) {
//		return this.getFilteredHeadeCols(pHeaderValueList, false);
//	}
	@SuppressWarnings("unchecked")
	private List<Object> getFilteredHeadeCols(List<Object> pHeaderValueList, boolean pExistingOnly) {
		List<Object> retval = new ArrayList<Object>();
		for (Object headerVal : pHeaderValueList) {
			boolean headerColExists;
			if (headerVal instanceof String) {
				headerColExists = (this.getHeaderPosition((String) headerVal) >= 0);
			} else if (headerVal instanceof List) {
				headerColExists = (this.getHeaderPosition((List<String>) headerVal) >= 0);
			} else {
				headerColExists = false;
			}
			if ((pExistingOnly && headerColExists) || (!pExistingOnly && !headerColExists)) {
				retval.add(headerVal);
			}
		}
		return retval;
	}

	public void addHeaderCell(String pHeaderValue) {
//		System.out.println("addHeaderCell [" + pHeaderValue + "]");
		this.headerList.add(pHeaderValue);
	}

	public List<List<String>> getContentMatrix() {
		return contentMatrix;
	}

	public void addRow(List<String> pRow) {
		this.contentMatrix.add(pRow);
	}

	public static String getCellvalue(List<String> pRow, int pIndex) {
		String retval = null;
		try {
			retval = (pIndex >= 0) ? pRow.get(pIndex) : "";
		} catch (Exception e) {
			retval = "";
		}
		return retval;
	}
}
