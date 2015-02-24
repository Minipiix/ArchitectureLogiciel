package com.esiea.logging.bean;
/**
 * 
 * @author Lucile
 *
 */
public enum Severity {
	DEBUG ("debug"),
	INFO ("info"),
	WARN ("warn"),
	ERROR ("error");
	
	private String name;

	private Severity(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}

}
