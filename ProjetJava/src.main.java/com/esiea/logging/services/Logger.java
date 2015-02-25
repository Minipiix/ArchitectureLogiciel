package com.esiea.logging.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.esiea.logging.bean.Log;
import com.esiea.logging.model.Severity;
import com.esiea.logging.services.formatter.FormatterImpl;

/**
 * Logger class, log messages with given filter and target
 * @author Lucile
 */
public class Logger {
	
	private Class<?> className;
	private Severity MaxSeverity = Severity.INFO;
	private FormatterImpl formatter = new FormatterImpl();
	private List<Target> targets = new ArrayList<Target>();
	
	public Logger(Class<?> className) {		
		this.className = className;
	}
	
	public void setMaxSeverity(Severity maxSeverity) {
		MaxSeverity = maxSeverity;
	}
	
	public Severity getMaxSeverity() {
		return MaxSeverity;
	}
	
	public void setFormatter(FormatterImpl formatter) {
		this.formatter = formatter;
	}
	
	public void addTarget(Target target) {
		targets.add(target);
	}
	
	/**
	 * Log message with severity debug
	 * 
	 * @param message
	 */
	public void debug(String message) {
		log(Severity.DEBUG, message);
	}
	
	/**
	 * Log message with severity info
	 * 
	 * @param message
	 */
	public void info(String message) {
		log(Severity.INFO, message);
	}
	
	/**
	 * Log message with severity warn
	 * 
	 * @param message
	 */
	public void warn(String message) {
		log(Severity.WARN, message);
	}
	
	/**
	 * Log message with severity error
	 * 
	 * @param message
	 */
	public void error(String message) {
		log(Severity.ERROR, message);
	}
	
	/**
	 * Log message with severity choice
	 * 
	 * @param severity
	 * @param message
	 */
	private void log(Severity severity, String message){
		
		// Create log object 
		Log log = new Log();
		log.setMessage(message);
		log.setSeverity(severity);
		log.setClassName(className.getName());
		log.setDate(new Date());
				
		for (Target target : targets) {
			if(severity.getPriority() <= MaxSeverity.getPriority() 
					&& severity.getPriority() <= target.getSeverityMax().getPriority()) {
				target.write(formatter.format(log));
			}
		}
	}

}
