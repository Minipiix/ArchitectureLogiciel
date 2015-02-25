package com.esiea.logging.services;


import java.util.Date;

import com.esiea.logging.bean.Log;
import com.esiea.logging.model.Severity;

/**
 * Logger class, log messages with given filter and target
 * @author Lucile
 */
public class Logger {
	
	private Formatter formatter;	
	private Class<?> className;
	
	public Logger(Class<?> className, Formatter formater) {
		this.className = className;
		this.formatter = formater;
	}
	
	/**
	 * Log message with severity debug
	 * 
	 * @param message
	 */
	public void debug(String message) {
		System.out.println("[" +Severity.DEBUG + "] " + className + " " + message);
	}
	
	/**
	 * Log message with severity info
	 * 
	 * @param message
	 */
	public void info(String message) {
		System.out.println("[" +Severity.INFO + "] " + className + " " + message);
	}
	
	/**
	 * Log message with severity warn
	 * 
	 * @param message
	 */
	public void warn(String message) {
		System.out.println("[" +Severity.WARN + "] " + className + " " + message);
	}
	
	/**
	 * Log message with severity error
	 * 
	 * @param message
	 */
	public void error(String message) {
		System.out.println("[" +Severity.ERROR + "] " + className + " " + message);
	}
	
	/**
	 * Log message with severity choice
	 * 
	 * @param severity
	 * @param message
	 */
	private void log(Severity severity, String message){
		
		Log log = new Log();
		log.setMessage(message);
		log.setSeverity(severity);
		log.setClassName(className.getName());
		log.setDate(new Date());
		
		
				
		//String out = formatter.format(log);
		//System.out.println(out);
	}

}
