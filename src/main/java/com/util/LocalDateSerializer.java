package com.util;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class LocalDateSerializer implements JsonSerializer<LocalDate> {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy",Locale.ROOT);

	@Override
	public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
		return new JsonPrimitive(formatter.format(localDate));
	}
}
