package com.esiea.logging.model;
/**
 * 
 * @author Lucile
 *
 */
public enum Severity {
	DEBUG ("DEBUG"),
	INFO ("INFO"),
	WARN ("WARN"),
	ERROR ("ERROR");
	
	private String name;

	private Severity(String name) {
		this.name = name;
	}
	
	public String toString(){
		return name;
	}

}
