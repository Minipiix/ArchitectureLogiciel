package com.esiea.logging.services;

import com.esiea.logging.model.Severity;

public interface Target {
	
	/**
	 * Write log in target
	 * 
	 * @param message
	 */
	public void write(String message);
	
	/**
	 * Get max severity of the Target
	 * 
	 * @return
	 */
	public Severity getSeverityMax();
	
	/**
	 * Set max severity of the Target
	 * 
	 * @return
	 */
	void setSeverityMax(Severity severityMax);

}
