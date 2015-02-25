package com.esiea.logging;

import com.esiea.logging.model.Severity;
import com.esiea.logging.services.Logger;
import com.esiea.logging.services.Target;
import com.esiea.logging.services.formatter.FormatterImpl;
import com.esiea.logging.services.target.TargetConsole;
import com.esiea.logging.services.target.TargetFile;

public class LoggerFactory {

	public static Logger getLogger(Class<?> logClass) {
		// TODO get all data in properties file
		
		// Create formatter
		FormatterImpl formatter = new FormatterImpl();
		
		// Create targets
		Target target1 = new TargetConsole();
		target1.setSeverityMax(Severity.DEBUG);
		Target target2 = new TargetFile();
		target2.setSeverityMax(Severity.WARN);
		
		// Create logger
		Logger logger = new Logger(logClass);
		logger.setMaxSeverity(Severity.INFO);
		logger.setFormatter(formatter);
		logger.addTarget(target1);
		logger.addTarget(target2);
				
		return logger;
	}

}
