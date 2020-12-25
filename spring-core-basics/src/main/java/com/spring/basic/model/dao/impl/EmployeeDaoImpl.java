package com.spring.basic.model.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.basic.model.dao.EmployeeDao;

@Repository("employeeDaoImpl")
public class EmployeeDaoImpl implements EmployeeDao{

	@Override
	public List<String> getNames() {
		
		return Arrays.asList("Ahmet", "Ali", "Murat");
	}

	

	
}
