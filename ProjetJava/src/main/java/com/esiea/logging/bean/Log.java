package com.esiea.logging.bean;


import java.util.Date;

import com.esiea.logging.model.Severity;

public class Log {
	
	private String message;
	private String className;
	private Severity severity;
	private Date date;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public Severity getSeverity() {
		return severity;
	}
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	@Override
	public String toString() {
		return "Log [message=" + message + ", className=" + className
				+ ", severity=" + severity + ", date=" + date + "]";
	}
	
	

}
