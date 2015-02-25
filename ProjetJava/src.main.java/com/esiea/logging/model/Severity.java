package com.esiea.logging.model;
/**
 * 
 * @author Lucile
 *
 */
public enum Severity {
	DEBUG ("DEBUG", 4),
	INFO ("INFO", 3),
	WARN ("WARN", 2),
	ERROR ("ERROR", 1);

	private String name;
	private Integer priority;

	private Severity(String name, Integer priority) {
		this.name = name;
		this.priority = priority;
	}
	
	public String toString(){
		return name;
	}
	
	public Integer getPriority() {
		return priority;
	}

}
