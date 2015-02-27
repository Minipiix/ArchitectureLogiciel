package com.esiea.logging.services.target;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.esiea.logging.model.Severity;

public class TargetFile implements Target {

	// Formatter attributes keys
	private static final String FILE = "file";

	// Default output file name/path
	private static final String DEFAULT_FILE_PATH = "logging.log";

	private Severity severityMax = Severity.INFO;

	private Map<String, String> attributs;

	public TargetFile() {
		attributs = new HashMap<String, String>();
		attributs.put(FILE, DEFAULT_FILE_PATH);
	}

	public TargetFile(String filePath) {
		this();
		if (filePath != null && !filePath.equals("")) {
			attributs.put(FILE, filePath);
		}
	}

	public void write(String message) {
		try {

			File file = new File(attributs.get(FILE));

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			FileWriter fileWritter = new FileWriter(file.getName(), true);
			BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
			bufferWritter.write(message);
			bufferWritter.newLine();
			bufferWritter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Severity getSeverityMax() {
		return severityMax;
	}

	@Override
	public void setSeverityMax(Severity severityMax) {
		this.severityMax = severityMax;
	}

	@Override
	public void setAttribute(String attributeName, Object attribute) {
		if (FILE.equals(attributeName)) {
			attributs.put(FILE, (String) attribute);
		}
	}
	
	public Map<String, String> getAttributs() {
		return attributs;
	}
}
