package com.esiea.logging;

import com.esiea.logging.bean.Logger;

public class LoggerFactory {

	public static Logger getLogger(Class<?> classLog) {
		return new Logger(classLog.getName());
	}

}
