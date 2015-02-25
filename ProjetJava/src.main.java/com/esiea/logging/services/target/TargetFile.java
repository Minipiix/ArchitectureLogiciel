package com.esiea.logging.services.target;

import com.esiea.logging.model.Severity;
import com.esiea.logging.services.Target;

public class TargetFile implements Target{
	
	private Severity severityMax = Severity.INFO;
	
	//TODO recuperer le path du fichier 
	
	public void write(String message){
		// TODO write in file
		System.out.println("cacafile " + message);
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
