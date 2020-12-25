package com.spring.basic.model.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.basic.model.dao.EmployeeDao;
import com.spring.basic.model.service.EmployeeService;

@Service("employeeServiceImpl")
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public List<String> getAll() {
		
		return this.employeeDao.getNames();
	}

}
