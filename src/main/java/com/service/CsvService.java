package com.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface CsvService {

	List<String> getAllVacations() throws IOException;

	String upload(InputStream fileInputStream) throws IOException;
	
}
