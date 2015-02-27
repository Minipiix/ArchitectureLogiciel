package com.esiea.logging.services.formatter;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.esiea.logging.bean.Log;

public class FormatterImpl implements Formatter {
	
	// Formatter attributes keys
	private static final String PATTERN = "pattern";
	
	// Formatter keys regex
	private static final String KEY_SEVERITY = "#SEVERITY#";
	private static final String KEY_MESSAGE = "#MESSAGE#";
	private static final String KEY_DATE = "#DATE(.*?)#";
	private static final String KEY_CLASS = "#CLASS#";
	
	// Default date format
	private static final String DEFAULT_DATE_FORMAT ="dd/MM/yyyy - hh:mm:ss";
	
	// Default pattern
	private static final String DEFAULT_PATTERN ="#DATE# [#SEVERITY#] #CLASS# - #MESSAGE#";
	
	private String pattern;
	
	public FormatterImpl() {
		pattern = DEFAULT_PATTERN;
	}
	
	public FormatterImpl(String pattern) {
		this();
		if(pattern != null && !pattern.equals("")) {
			this.pattern = pattern;
		}
	}

	@Override
	public String format(Log log) {
		
		String formattedLog = pattern;
		formattedLog = setSeverity(formattedLog, log);
		formattedLog = setMessage(formattedLog, log);
		formattedLog = setClass(formattedLog, log);
		formattedLog = setDate(formattedLog, log);
		return formattedLog;
	}
	
	@Override
	public void setAttribute(String attributeName, Object attribute) {
		if(PATTERN.equals(attributeName)) {
			pattern = (String) attribute;
		}
	}
	
	public String getPattern() {
		return pattern;
	}
	
	private String setSeverity(String stringLog, Log log) {
		return stringLog.replaceAll(KEY_SEVERITY, log.getSeverity().name());
	}
	
	private String setMessage(String stringLog, Log log) {
		return stringLog.replaceAll(KEY_MESSAGE, log.getMessage());
	}
	
	private String setClass(String stringLog, Log log) {
		return stringLog.replaceAll(KEY_CLASS, log.getClassName());
	}
	
	private String setDate(String stringLog, Log log) {
		
		// get date format
		String dateFormat = DEFAULT_DATE_FORMAT;
		Pattern datePattern = Pattern.compile(KEY_DATE);
		Matcher dateMatcher = datePattern.matcher(stringLog);
		if(dateMatcher.find()) {
			if(dateMatcher.group(1) != null && !dateMatcher.group(1).equals("")) {
				dateFormat = dateMatcher.group(1);		
			}
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			return dateMatcher.replaceAll(sdf.format(log.getDate()));
		}
		
		return stringLog;
	}

}
