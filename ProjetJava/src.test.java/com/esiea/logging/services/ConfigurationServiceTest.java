package com.esiea.logging.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.esiea.logging.model.Severity;
import com.esiea.logging.services.formatter.Formatter;
import com.esiea.logging.services.formatter.FormatterImpl;
import com.esiea.logging.services.target.Target;
import com.esiea.logging.services.target.TargetConsole;
import com.esiea.logging.services.target.TargetFile;

public class ConfigurationServiceTest {
	
	@Test
	public void testDefaultConfiguration() {
		
		ConfigurationService conf = new ConfigurationService("testClass");
		
		// Test severity
		Assert.assertEquals(Severity.INFO, conf.getSeverity());
		
		// Test Formatter
		Formatter formatter = conf.getFormatter(); 
		Assert.assertNotNull(formatter);
		Assert.assertTrue(formatter instanceof FormatterImpl);
		FormatterImpl formatterImpl = (FormatterImpl) formatter; 
		Assert.assertEquals("[#SEVERITY#] #DATE# at #CLASS# - #MESSAGE#", formatterImpl.getAttributs().get("pattern"));
		
		// Test Targets
		List<Target> targets = conf.getTargets(); 
		Assert.assertNotNull(targets);
		Assert.assertEquals(2, targets.size());
		Assert.assertTrue(targets.get(0) instanceof TargetConsole);
		Assert.assertTrue(targets.get(1) instanceof TargetFile);
		TargetFile targetFile = (TargetFile) targets.get(1); 
		Assert.assertEquals("testLogger.log", targetFile.getAttributs().get("file"));
	}
	
	@Test
	public void testClassConfiguration() {
		
		ConfigurationService conf = new ConfigurationService("testConfigClass");
		
		// Test severity
		Assert.assertEquals(Severity.DEBUG, conf.getSeverity());
		
		// Test Formatter
		Formatter formatter = conf.getFormatter(); 
		Assert.assertNotNull(formatter);
		Assert.assertTrue(formatter instanceof FormatterImpl);
		FormatterImpl formatterImpl = (FormatterImpl) formatter; 
		Assert.assertEquals("[#SEVERITY#] - #MESSAGE#", formatterImpl.getAttributs().get("pattern"));
		
		// Test Targets
		List<Target> targets = conf.getTargets(); 
		Assert.assertNotNull(targets);
		Assert.assertEquals(1, targets.size());
		Assert.assertTrue(targets.get(0) instanceof TargetFile);
		TargetFile targetFile = (TargetFile) targets.get(0); 
		Assert.assertEquals("testLogger2.log", targetFile.getAttributs().get("file"));
	}
}
