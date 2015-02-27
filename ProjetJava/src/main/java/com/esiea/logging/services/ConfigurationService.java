package com.esiea.logging.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.esiea.logging.model.Severity;
import com.esiea.logging.services.formatter.Formatter;
import com.esiea.logging.services.formatter.FormatterImpl;
import com.esiea.logging.services.target.Target;

public class ConfigurationService {

	// Properties file path
	private static final String PROPERTIES_FILE_PATH = "logger.properties";

	// Properties key
	private static final String KEY_SEPARATOR = ".";
	private static final String DEFAULT_PROPERTIES_PREFIX = "com.esiea.loggin";
	private static final String FORMATTER_PROPERTIES = "formatter";
	private static final String TARGET_PROPERTIES = "target";
	private static final String SEVERITY_KEY = "severity";
	private static final String CLASS_KEY = "class";
	
	Map<String, String> properties;
	
	public ConfigurationService(String className) {
		initProperties(className);
	}
	
	private void initProperties(String className) {
		Properties prop = loadProperties();

		Map<String, String> propertiesMap = new HashMap<String, String>();
		if (prop != null) {
			// get class properties
			for (String propertieName : prop.stringPropertyNames()) {
				String key = null;
				if (propertieName.startsWith(className)) {
					key = propertieName.replace(className + KEY_SEPARATOR, "");
				}
				
				if(key != null && !key.equals("")) {
					propertiesMap.put(key, prop.getProperty(propertieName));	
				}
			}
			
			// get default properties if needed
			if(propertiesMap.size() == 0) {
				for (String propertieName : prop.stringPropertyNames()) {
					String key = null;
					if(propertieName.startsWith(DEFAULT_PROPERTIES_PREFIX)) {
						key = propertieName.replace(DEFAULT_PROPERTIES_PREFIX + KEY_SEPARATOR, "");
					}
					
					if(key != null && !key.equals("")) {
						propertiesMap.put(key, prop.getProperty(propertieName));	
					}
				}
			}
		}
		this.properties = propertiesMap;
	}
	
	private Properties loadProperties() {

		Properties properties = new Properties();
		InputStream input = null;

		try {
			File file = new File(PROPERTIES_FILE_PATH);
			if (file.exists()) {
				input = new FileInputStream(file);
				properties.load(input);
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return properties;
	}

	/**
	 * Get severity from properties
	 * 
	 * @return
	 */
	public Severity getSeverity() {
		if(properties.get(SEVERITY_KEY) != null) {
			return Severity.getByName(properties.get(SEVERITY_KEY));
		}
		return null;
	}

	/**
	 * Get formatter from properties
	 * 
	 * @return
	 */
	public Formatter getFormatter() {
		
		// Create Formatter - FormatterImpl is default 
		Map<String, String> formatterProp = getPropertiesWithPrefix(properties, FORMATTER_PROPERTIES);
		Formatter formatter = getClassByName(formatterProp.get(CLASS_KEY));
		if (formatter == null) {
			formatter = new FormatterImpl();
		}

		// Set attributes to formatter
		for (Entry<String, String> attribute : formatterProp.entrySet()) {
			if(!CLASS_KEY.equals(attribute.getKey())) {
				formatter.setAttribute(attribute.getKey(), attribute.getValue());
			}
		}
		
		return formatter;
	}

	/**
	 * Get targets from properties
	 * 
	 * @return
	 */
	public List<Target> getTargets() {
		
		// Init target properties
		List<Target> targets = new ArrayList<>();
		Map<String, String> allTargetsProp = getPropertiesWithPrefix(properties, TARGET_PROPERTIES);

		// Get first target properties from file
		Integer targetIndex = 1;
		Map<String, String> targetProp = getPropertiesWithPrefix(allTargetsProp, targetIndex.toString());
		while (targetProp != null && targetProp.size() != 0) {
			
			// Create target
			Target target = getClassByName(targetProp.get(CLASS_KEY));
			
			// Set attributes to target
			for (Entry<String, String> attribute : targetProp.entrySet()) {
				if(!CLASS_KEY.equals(attribute.getKey())) {
					target.setAttribute(attribute.getKey(), attribute.getValue());
				}
			}
			
			// Add max severity to target
			if(targetProp.get(SEVERITY_KEY) != null) {
				target.setSeverityMax(Severity.getByName(targetProp.get(SEVERITY_KEY)));
			}
			targets.add(target);
			
			// Get next target properties from file
			targetIndex ++;
			targetProp = getPropertiesWithPrefix(allTargetsProp, targetIndex.toString());
		}
		
		return targets;
	}


	private Map<String, String> getPropertiesWithPrefix(
			Map<String, String> allProperties, String namePrefix) {

		Map<String, String> propertiesMap = new HashMap<String, String>();
		if (allProperties != null) {
			for (String propertieName : allProperties.keySet()) {
				String key = null;
				if (propertieName.startsWith(namePrefix)) {
					key = propertieName.replace(namePrefix + KEY_SEPARATOR, "");
				}
				
				if(key != null && !key.equals("")) {
					propertiesMap.put(key, allProperties.get(propertieName));	
				}
			}
		}
		return propertiesMap;
	}

	@SuppressWarnings("unchecked")
	private <T> T getClassByName(String className) {

		T result = null;

		if (className != null) {

			try {
				Class<?> clazz = Class.forName(className);
				result = (T) clazz.newInstance();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} 
		}

		return result;
	}
}
