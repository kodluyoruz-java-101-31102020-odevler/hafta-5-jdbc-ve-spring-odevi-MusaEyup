package com.spring.basic.data.exporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.spring.basic.ConsoleApplication;
import com.spring.basic.model.Salary;


public class DataExporter {
	// 1.yöntem
	// bir nesneyi dependency olarak eklemek için @Autowired etiketini kullanabiliriz
	// @Autowired
	// private SalaryJsonConverter salaryJsonConverter;
	
	
	// 2. yöntem 
	@Autowired
	private ApplicationContext applicationContext;
	
	
	public String convertSalaryToJson(Salary salary) {
		
		try {
			
			// 2. yöntem
			ConsoleApplication consoleApplication = applicationContext.getBean(ConsoleApplication.class);
			consoleApplication.sayHello();
			SalaryJsonConverter sJsonConverter = applicationContext.getBean("JsonConverter", SalaryJsonConverter.class);
			
			return sJsonConverter.convertToJson(salary);
		}
		catch(JsonProcessingException e) {
			e.printStackTrace();
		}
		return "";
	}
}
