package com.spring.basic.model.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.basic.model.dao.DepartmentDao;
import com.spring.basic.model.service.DepartmentService;

@Service("departmentServiceImpl")
public class DepartmentServiceImpl implements DepartmentService{
	
	@Autowired
	private DepartmentDao departmentDao;

	@Override
	public List<String> getAll() {
		
		return this.departmentDao.getNames();
	}
	

}
