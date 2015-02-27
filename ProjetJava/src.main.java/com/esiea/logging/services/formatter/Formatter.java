package com.esiea.logging.services.formatter;

import com.esiea.logging.bean.Log;

public interface Formatter {

	/**
	 * Format log into a readable string
	 * 
	 * @param log Log object
	 * @return output string
	 */
	String format(Log log);
	
	/**
	 * Add any attribute needed for formatter
	 * 
	 * @param attributeName
	 * @param attribute
	 */
	void setAttribute(String attributeName, Object attribute);

}
