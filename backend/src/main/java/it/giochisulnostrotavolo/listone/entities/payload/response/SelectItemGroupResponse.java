package it.giochisulnostrotavolo.listone.entities.payload.response;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SelectItemGroupResponse implements Serializable {

	private static final long serialVersionUID = -7463395528705569948L;

	private String label;
	
	private List<SelectItemResponse> items = new LinkedList<SelectItemResponse>();
	
	public SelectItemGroupResponse(String label, List<SelectItemResponse> items) {
		this.label = label;
		this.items = items;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<SelectItemResponse> getItems() {
		return items;
	}

	public void setItems(List<SelectItemResponse> items) {
		this.items = items;
	}
}
