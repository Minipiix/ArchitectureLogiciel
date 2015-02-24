package com.esiea.logging;

import com.esiea.logging.services.Formatter;
import com.esiea.logging.services.Logger;

public class LoggerFactory {

	public static Logger getLogger(Class<?> logClass) {
		
		// Create formatter
		Formatter formatter = new Formatter("");
		// TODO get default pattern in properties
		
		return new Logger(logClass, formatter);
	}

}
