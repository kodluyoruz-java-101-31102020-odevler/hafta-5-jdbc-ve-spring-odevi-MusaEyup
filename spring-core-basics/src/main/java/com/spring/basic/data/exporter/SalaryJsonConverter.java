package com.spring.basic.data.exporter;



import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.basic.model.Salary;

@Component("salaryJsonConverter")
public class SalaryJsonConverter {
	
	public String convertToJson(Salary salary) throws JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(salary);
	}

}
