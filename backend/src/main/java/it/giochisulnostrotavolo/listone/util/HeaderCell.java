package it.giochisulnostrotavolo.listone.util;

import java.util.List;

public class HeaderCell {

	private final String label;
	private final boolean readonly;

	public HeaderCell(String pLabel, boolean pReadonly) {
		this.label = (pLabel != null)? pLabel : "-";;
		this.readonly = pReadonly;
	}

	public HeaderCell(List<String> pLabelList, boolean pReadonly) {
		this.label = (pLabelList != null && !pLabelList.isEmpty())? pLabelList.get(0) : "-";
		this.readonly = pReadonly;
	}

	public String getLabel() {
		return label;
	}

	public boolean isReadonly() {
		return readonly;
	}
	
	
	
}
