package com.github.maquina1995.maquilegends.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootConfiguration
public class JacksonConfig {

	@Primary
	public ObjectMapper jacksonBuilder() {
		return new Jackson2ObjectMapperBuilder().indentOutput(false)
				.serializationInclusion(JsonInclude.Include.NON_NULL)
				.serializationInclusion(JsonInclude.Include.NON_EMPTY)
				.build();
	}

}
