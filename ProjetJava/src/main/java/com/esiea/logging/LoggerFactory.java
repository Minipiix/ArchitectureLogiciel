package com.esiea.logging;

import com.esiea.logging.model.Severity;
import com.esiea.logging.services.ConfigurationService;
import com.esiea.logging.services.Logger;
import com.esiea.logging.services.target.Target;
import com.esiea.logging.services.target.TargetConsole;
import com.esiea.logging.services.target.TargetFile;

public class LoggerFactory {
	
	public static Logger getLogger(Class<?> logClass) {
		
		ConfigurationService conf = new ConfigurationService(logClass.getName());

		// Create targets
		Target target1 = new TargetConsole();
		target1.setSeverityMax(Severity.DEBUG);
		Target target2 = new TargetFile();
		target2.setSeverityMax(Severity.WARN);

		// Create logger
		Logger logger = new Logger(logClass);
		logger.setMaxSeverity(conf.getSeverity());
		logger.setFormatter(conf.getFormatter());
		logger.setTargets(conf.getTargets());

		return logger;
	}
}
