package com.retripver.global.dto;

public class CompareResult {
	private double similarity;
	private boolean isSimilar;
	
	public CompareResult(double similarity, boolean isSimilar) {
		this.similarity = similarity;
		this.isSimilar = isSimilar;
	}

	public double getSimilarity() {
		return similarity;
	}


	public void setSimilarity(double similarity) {
		this.similarity = similarity;
	}


	public boolean isSimilar() {
		return isSimilar;
	}


	public void setSimilar(boolean isSimilar) {
		this.isSimilar = isSimilar;
	}


	@Override
	public String toString() {
		return "CompareResult [similarity=" + similarity + ", isSimilar=" + isSimilar + "]";
	}
	
}
