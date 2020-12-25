package db.connection.mysql.connection.service;

import java.util.List;

import db.connection.mysql.connection.dao.SalaryDAO;

public class SalaryService {

	private SalaryDAO salaryDAO;
	
	public SalaryService(SalaryDAO salaryDAO) {
		this.salaryDAO = salaryDAO;
	}
	
	public List<Long> getSalaries(Long empNo) {
		
		return this.salaryDAO.loadSalaryListOfEmployee(empNo);
	}
	
}
