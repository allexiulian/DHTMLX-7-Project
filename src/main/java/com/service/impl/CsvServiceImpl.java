package com.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.bean.Vacation;
import com.dao.VacationDao;
import com.dao.impl.VacationDaoImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.service.CsvService;
import com.servlet.LoginServlet;

public class CsvServiceImpl implements CsvService{
	
	private final Logger Log = Logger.getLogger(LoginServlet.class.getName());

	private final VacationDao vacationDao = new VacationDaoImpl();
	
	@Override
	public List<String> getAllVacations() throws IOException   {
		Log.info("Request to create csv");
		CsvMapper mapper = new CsvMapper();
		mapper.findAndRegisterModules();
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		CsvSchema schema = createSchema();
		List<String> csv = new ArrayList<>();
		csv.add(mapper.writer(schema).writeValueAsString(vacationDao.getAllVacations()));
		return csv;
	}
	
	public CsvSchema createSchema() throws IOException{
		
		return CsvSchema.builder()
				.addColumn("id", CsvSchema.ColumnType.NUMBER)
				.addColumn("vacationFrom", CsvSchema.ColumnType.NUMBER_OR_STRING)
				.addColumn("vacationTo", CsvSchema.ColumnType.NUMBER_OR_STRING)
				.addColumn("reason", CsvSchema.ColumnType.STRING)
				.addColumn("employeeId", CsvSchema.ColumnType.NUMBER)
				.build().withColumnSeparator(',')
				.withLineSeparator("\n")
				.withHeader();
	}

	@Override
	public boolean upload(InputStream fileInputStream) throws IOException {
		Log.info("Parse csv to object");
		CsvMapper mapper = new CsvMapper();
		mapper.findAndRegisterModules();
		mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
		CsvSchema schema = createSchema();
		MappingIterator<Vacation> list = mapper.readerWithTypedSchemaFor(Vacation.class).with(schema).readValues(fileInputStream);
		List<Vacation> result = list.readAll();
		return vacationDao.saveAll(result);
		
	}

}
