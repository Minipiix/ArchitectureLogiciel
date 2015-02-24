package com.esiea.logging.bean;

/**
 * Logger class, log messages with given filter and target
 * @author Lucile
 */
public class Logger {
	
	private String className;
	
	public Logger(String className) {
		this.className = className;
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
		System.out.println("[" + severity + "] " + className + " " + message);
	}

}
