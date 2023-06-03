package it.giochisulnostrotavolo.listone.util;

public class CellExcel {

	public final static int NO_SECTION = 0;
	public final static int SECTION_BASE_PRICE = 1;
	public final static int SECTION_EQUIPMENTS = 2;
//	public final static int SECTION_OPTIONALS = 2;
//	public final static int SECTION_ACCESSORIES = 3;
	public final static int SECTION_IMPORTED = 4;
	public final static int SECTION_NOT_IMPORTED = 5;

	private int lineNumber = 0;
	private Object value;
	private int section = 0;
	private boolean head = false;
	private boolean bold = false;
	private boolean centered = false;
	private boolean locked = false;
	private boolean merged = false;
	private String formula = null;
	private boolean percentage = false;
	private boolean highlighted = false;

	public CellExcel(String value) {
		this.value = value;
	}

	public CellExcel(Object value, int section) {
		this.value = value;
		this.section = section;
	}

	public CellExcel(Object value, int section, boolean head, boolean locked, boolean merged) {
		this.value = value;
		this.section = section;
		this.head = head;
		this.locked = locked;
		this.merged = merged;
	}

	public CellExcel(Object value, int section, boolean head, boolean locked, boolean merged, String formula) {
		this.value = value;
		this.section = section;
		this.head = head;
		this.locked = locked;
		this.merged = merged;
		this.formula = formula;
	}

	public CellExcel(Object value, int section, boolean head, boolean locked, boolean merged, boolean percentage) {
		this.value = value;
		this.section = section;
		this.head = head;
		this.locked = locked;
		this.merged = merged;
		this.percentage = percentage;
	}

	public CellExcel(Object value, int section, boolean head, boolean locked, boolean merged, boolean percentage, boolean highlighted) {
		this.value = value;
		this.section = section;
		this.head = head;
		this.locked = locked;
		this.merged = merged;
		this.percentage = percentage;
		this.highlighted = highlighted;
	}

	public CellExcel(Object value, Boolean head) {
		this.value = value;
		this.head = head;
	}

	public CellExcel(Object value, Boolean head, Boolean centered, Boolean bold) {
		this.value = value;
		this.head = head;
		this.centered = centered;
		this.bold = bold;
	}

	public CellExcel(Object value, Boolean bold, Boolean centered) {
		this.value = value;
		this.bold = bold;
		this.centered = centered;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public boolean isHead() {
		return head;
	}

	public void setHead(boolean head) {
		this.head = head;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public boolean isCentered() {
		return centered;
	}

	public void setCentered(boolean centered) {
		this.centered = centered;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isMerged() {
		return merged;
	}

	public void setMerged(boolean merged) {
		this.merged = merged;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public boolean isPercentage() {
		return percentage;
	}

	public void setPercentage(boolean percentage) {
		this.percentage = percentage;
	}

	public boolean isHighlighted() {
		return highlighted;
	}

	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}

	public String getStyleKey() {
		return "s_" + section + "hd_" + head + "_b_" + bold + "_c_" + centered + "_l_" + locked + "_m_" + merged + "_p_" + percentage + "_hl_" + highlighted;
	}
}
