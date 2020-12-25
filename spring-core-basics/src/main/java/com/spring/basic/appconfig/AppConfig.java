package com.spring.basic.appconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.spring.basic.ConsoleApplication;
import com.spring.basic.data.exporter.SalaryJsonConverter;


@Configuration

@ComponentScan(basePackages = {
		"com.spring.basic.model",
		"com.spring.basic.data.exporter",
		"com.spring.basic.model.dao.impl",
		"com.spring.basic.model.service.impl"
})
public class AppConfig {
	
	// @Bean etiketi ile tek tek tanımlama yapmak zorundayız
	@Bean("JsonConverter")
	public SalaryJsonConverter createSalaryJsonConverter() {
		return new SalaryJsonConverter();
	}
	@Bean("consoleApplication")
	public ConsoleApplication createConsoleApp() {
		return new ConsoleApplication();
	}

}
