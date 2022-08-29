package com.util;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy",Locale.ROOT);
	
	@Override
	public LocalDate deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) {
		return LocalDate.parse(json.getAsJsonPrimitive().getAsString(),formatter);
	}

}