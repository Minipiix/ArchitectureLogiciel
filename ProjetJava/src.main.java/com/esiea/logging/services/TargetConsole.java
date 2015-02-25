package com.esiea.logging.services;

public class TargetConsole implements Target{
	
	public void write(String log){
		System.out.println(log);
	}

}
