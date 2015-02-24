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
	 * Log message with severity info
	 * 
	 * @param message
	 */
	public void info(String message) {
		System.out.println("[info] " + className + " " + message);
	}
	
	/**
	 * Log message with severity warn
	 * 
	 * @param message
	 */
	public void warn(String message) {
		System.out.println("[warn] " + className + " " + message);
	}
	
	/**
	 * Log message with severity error
	 * 
	 * @param message
	 */
	public void error(String message) {
		System.out.println("[error] " + className + " " + message);
	}
	
	// TODO private method for logging (severity in param)

}
