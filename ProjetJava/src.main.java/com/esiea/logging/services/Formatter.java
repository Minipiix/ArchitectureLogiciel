package com.esiea.logging.services;

import com.esiea.logging.bean.Log;

public interface Formatter {

	/**
	 * Format log into a readable string
	 * 
	 * @param log Log object
	 * @return output string
	 */
	String format(Log log);

}
