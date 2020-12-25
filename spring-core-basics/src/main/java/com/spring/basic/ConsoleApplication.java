package com.spring.basic;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.spring.basic.model.Salary;

import com.spring.basic.model.service.DepartmentService;
import com.spring.basic.model.service.EmployeeService;
import com.spring.basic.data.exporter.DataExporter;

@Component
public class ConsoleApplication implements CommandLineRunner{
	
	@Autowired
	private DataExporter dataExporter;
	
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void run(String... args) throws Exception {
		
		
		
		// dependency injection
		Salary salary1 = applicationContext.getBean("salary", Salary.class);
		salary1.setCreateDate(new Date());
		salary1.setValue(20000);
		String json = dataExporter.convertSalaryToJson(salary1);
		System.out.println(salary1);
		
		System.out.println(json);
		
		Salary salary2 = applicationContext.getBean("salary", Salary.class);
		salary2.setValue(15000);
		salary2.setCreateDate(new Date());
		System.out.println(salary2);
		
		json = dataExporter.convertSalaryToJson(salary2);
		System.out.println(json);
		
		
		System.out.println("Çalışan Listsi");
		System.out.println(employeeService.getAll());
		
		System.out.println("Department Listsi");
		System.out.println(departmentService.getAll());
		
		
	}
	public void sayHello() {
	System.out.println("Hello from the other side --> ConsoleApplication");	
	}
	}
