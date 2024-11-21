package com.retripver.attraction.dto;

import java.util.List;

public class SidoResponse {
	private int sidoCode;
	private String sidoName;
	private List<GugunResponse> guguns;
	
	public int getSidoCode() {
		return sidoCode;
	}
	public void setSidoCode(int sidoCode) {
		this.sidoCode = sidoCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	public List<GugunResponse> getGuguns() {
		return guguns;
	}
	public void setGuguns(List<GugunResponse> guguns) {
		this.guguns = guguns;
	}
}
