package com.esiea.logging.services.target;

import com.esiea.logging.model.Severity;
import com.esiea.logging.services.Target;

public class TargetConsole implements Target{
	
	private Severity severityMax = Severity.INFO;

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

}
