package com.esiea.logging.services.target;

import com.esiea.logging.model.Severity;

public class TargetConsole implements Target{
	
	private Severity severityMax = Severity.INFO;
	
	public TargetConsole() {
		// nothing to do
	}

	@Override
	public void write(String log){
		System.out.println(log);
	}
	
	@Override
	public Severity getSeverityMax() {
		return severityMax;
	}

	@Override
	public void setSeverityMax(Severity severityMax) {
		this.severityMax = severityMax;
	}
	
	@Override
	public void setAttribute(String attributeName, Object attribute) {
		// Nothing to do
	}

}
