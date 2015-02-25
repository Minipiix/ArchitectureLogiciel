package com.esiea.logging;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.esiea.logging.services.Logger;

public class LoggerFactoryTest {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggerFactoryTest.class);
	
//	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
//	@Before
//	public void setUpStreams() {
//	    System.setOut(new PrintStream(outContent));
//	}
//
//	@After
//	public void cleanUpStreams() {
//	    System.setOut(null);
//	}

	@Test
	public void logTest() {

		logger.debug("ESIEA Message");
//		assertTrue(outContent.toString().contains("[debug] com.esiea.logging.LoggerFactoryTest ESIEA Message"));
		logger.info("ESIEA Message");
//		assertTrue(outContent.toString().contains("[info] com.esiea.logging.LoggerFactoryTest ESIEA Message"));
		logger.warn("ESIEA Message");
//		assertTrue(outContent.toString().contains("[warn] com.esiea.logging.LoggerFactoryTest ESIEA Message"));
		logger.error("ESIEA Message");
//		assertTrue(outContent.toString().contains("[error] com.esiea.logging.LoggerFactoryTest ESIEA Message"));
	}

}
