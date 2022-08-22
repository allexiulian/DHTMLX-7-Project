package com.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Stream;

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
				.addColumn("vacationFrom", CsvSchema.ColumnType.NUMBER_OR_STRING)
				.addColumn("vacationTo", CsvSchema.ColumnType.NUMBER_OR_STRING)
				.addColumn("reason", CsvSchema.ColumnType.STRING)
				.addColumn("employeeId", CsvSchema.ColumnType.NUMBER)
				.build().withColumnSeparator(',')
				.withLineSeparator("\n")
				.withHeader();
	}

	@Override
	public String upload(InputStream fileInputStream) throws IOException {
		Log.info("Parse csv to object");
		StringBuffer resp = new StringBuffer();
		CsvMapper mapper = new CsvMapper();
		mapper.findAndRegisterModules();
		mapper.configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false);
		CsvSchema schema = createSchema();
		MappingIterator<Vacation> list = mapper.readerWithTypedSchemaFor(Vacation.class).with(schema).readValues(fileInputStream);
		List<Vacation> result = list.readAll();
		if (result.size() == 0) {
			return resp.append("No file detected!").toString();
		}
		HashSet<String> set = new HashSet<>();
		AtomicInteger row = new AtomicInteger();
		List<Vacation> db = vacationDao.getAllVacations();
		result.forEach(e -> {
			row.getAndIncrement();

				if (!set.add(e.toString())) {
					resp.append("Duplicate - row: ").append(row.get() + 1).append("</br>");
				}
				if (dbCheckDuplicate(db, e)) {
					resp.append("Duplicate database - row: ").append(row.get() + 1).append("</br>");
				}			
		});
		if (resp.length() == 0) {
			resp.append(vacationDao.saveAll(result) ? "ok" : "Invalid data!");
		}
		return resp.toString();
	}

	

	private boolean dbCheckDuplicate(List<Vacation> db, Vacation v) {
		return db.stream()
				.anyMatch(e -> e.getVacationFrom().equals(v.getVacationFrom())
						&& e.getVacationTo().equals(v.getVacationTo()) && e.getReason().equals(v.getReason())
						&& Objects.equals(e.getEmployeeId(), v.getEmployeeId()));
	}


}
