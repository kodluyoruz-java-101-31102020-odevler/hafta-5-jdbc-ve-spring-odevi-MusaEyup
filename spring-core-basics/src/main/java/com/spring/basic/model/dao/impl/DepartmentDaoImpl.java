package com.spring.basic.model.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.basic.model.dao.DepartmentDao;

 @Repository("departmentDaoImpl")
public class DepartmentDaoImpl implements DepartmentDao{

	@Override
	public List<String> getNames() {
		
		return Arrays.asList("Insan kaynakları", "Mühendislik", "Pazarlama");
	}
	
	

}
