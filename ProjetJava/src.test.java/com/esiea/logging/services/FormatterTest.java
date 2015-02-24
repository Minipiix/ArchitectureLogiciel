package com.esiea.logging.services;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.esiea.logging.bean.Log;
import com.esiea.logging.model.Severity;

public class FormatterTest {
	
	private Log log;
	
	@Before
	public void init() {
		log = new Log();
		log.setMessage("test message");
		log.setSeverity(Severity.ERROR);
		log.setClassName(FormatterTest.class.getName());
		log.setDate(new Date());
	}
	
	@Test
	public void testDefaultPatternWithNull() {
		
		Formatter formatter = new Formatter(null);
		String result = formatter.format(log);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		assertEquals(sdf.format(log.getDate()) +" [ERROR] com.esiea.logging.services.FormatterTest - test message", result);
	}
	
	@Test
	public void testDefaultPatternWithEmpty() {
		
		Formatter formatter = new Formatter("");
		String result = formatter.format(log);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		assertEquals(sdf.format(log.getDate()) +" [ERROR] com.esiea.logging.services.FormatterTest - test message", result);
	}
	
	@Test
	public void testCustomPatternDefaultDate() {
		
		Formatter formatter = new Formatter("[#SEVERITY#] #DATE# - #MESSAGE#");
		String result = formatter.format(log);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		assertEquals("[ERROR] "+sdf.format(log.getDate()) +" - test message", result);
	}
	
	@Test
	public void testCustomPatternCustomDate() {
		
		Formatter formatter = new Formatter("[#SEVERITY#] #DATE yyyy-MM-dd# - #MESSAGE#");
		String result = formatter.format(log);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals("[ERROR]  "+sdf.format(log.getDate()) +" - test message", result);
	}

}
