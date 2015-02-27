package com.esiea.logging.services.formatter;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.esiea.logging.bean.Log;
import com.esiea.logging.model.Severity;
import com.esiea.logging.services.formatter.FormatterImpl;

public class FormatterImplTest {
	
	private Log log;
	
	@Before
	public void init() {
		log = new Log();
		log.setMessage("test message");
		log.setSeverity(Severity.ERROR);
		log.setClassName(FormatterImplTest.class.getName());
		log.setDate(new Date());
	}
	
	@Test
	public void testDefaultPatternWithNull() {
		
		Formatter formatter = new FormatterImpl(null);
		String result = formatter.format(log);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		assertEquals(sdf.format(log.getDate()) +" [ERROR] com.esiea.logging.services.formatter.FormatterImplTest - test message", result);
	}
	
	@Test
	public void testDefaultPatternWithEmpty() {
		
		Formatter formatter = new FormatterImpl("");
		String result = formatter.format(log);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		assertEquals(sdf.format(log.getDate()) +" [ERROR] com.esiea.logging.services.formatter.FormatterImplTest - test message", result);
	}
	
	@Test
	public void testCustomPatternDefaultDate() {
		
		Formatter formatter = new FormatterImpl("[#SEVERITY#] #DATE# - #MESSAGE#");
		String result = formatter.format(log);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - hh:mm:ss");
		assertEquals("[ERROR] "+sdf.format(log.getDate()) +" - test message", result);
	}
	
	@Test
	public void testCustomPatternCustomDate() {
		
		Formatter formatter = new FormatterImpl("[#SEVERITY#] #DATE yyyy-MM-dd# - #MESSAGE#");
		String result = formatter.format(log);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals("[ERROR]  "+sdf.format(log.getDate()) +" - test message", result);
	}

}
