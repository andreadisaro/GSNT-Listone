package it.giochisulnostrotavolo.listone.entities.payload.response;

import java.io.Serializable;

public class SelectItemResponse implements Serializable {
	
	private static final long serialVersionUID = -684441086142032286L;

	private String value;
	
	private String value2;
	
	private String label;
	
	private boolean disabled = false;
	
	public SelectItemResponse(String value, String label) {
		this.value = value;
		this.label = label;
		this.disabled = false;
	}
	
	public SelectItemResponse(String value, String label, boolean disabled) {
		this.value = value;
		this.label = label;
		this.disabled = disabled;
	}
	
	public SelectItemResponse(String value, String label, String value2) {
		this.value = value;
		this.label = label;
		this.value2 = value2;
		this.disabled = false;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

}
