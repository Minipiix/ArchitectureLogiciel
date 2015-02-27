package com.esiea.logging;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.esiea.logging.services.Logger;

public class LoggerProjectTest {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggerProjectTest.class);

	@Test
	public void logTest() {

		logger.debug("ESIEA Message");
		logger.info("ESIEA Message");
		logger.warn("ESIEA Message");
		logger.error("ESIEA Message");
	}

}
