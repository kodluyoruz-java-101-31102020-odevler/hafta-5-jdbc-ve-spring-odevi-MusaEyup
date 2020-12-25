package db.connection.mysql.connection.service;

import java.util.Set;

import db.connection.mongo.connection.collection.MongoEmployeeCollectionDao;
import db.connection.mysql.connection.dao.EmployeeDAO;
import db.connection.mysql.connection.model.Employee;
import db.connection.mysql.connection.model.EmployeeProfile;

public class EmployeeService {

	private EmployeeDAO employeeDAO;
	
	private MongoEmployeeCollectionDao employeeCollectionDao;
	
	
	public EmployeeService(EmployeeDAO employeeDAO, MongoEmployeeCollectionDao employeeCollectionDao) {
		this.employeeDAO = employeeDAO;
		this.employeeCollectionDao = employeeCollectionDao;
	}
	
	public Employee save(Employee employee) {
		
		 Long maxEmployeeId = this.employeeDAO.getMaxId();
		 employee.setId(maxEmployeeId + 1);
		 return this.employeeDAO.save(employee);
	}
	
	public boolean saveAsDraft(EmployeeProfile employeeProfile) {
		
		return this.employeeCollectionDao.save(employeeProfile);
	}
	
	public EmployeeProfile loadProfileAsDraft(Long empNo) {
		
		return this.employeeCollectionDao.findByMySqlId(empNo);
	}
	
	public Employee findById(Long empNo) {
		
		return this.employeeDAO.findEmployeeById(empNo);
	}
	
	public Set<Employee> findAll() {
		
		return this.employeeDAO.getAll();
	}
	
	public EmployeeProfile loadEmployeeProfile(Long empNo) {
		
		EmployeeProfile employeeProfile = this.employeeDAO.loadEmployeeProfile(empNo);
		return employeeProfile;
	}
	
	public Employee update(Long empNo, String name, String lastName) {
		
		Employee employee = this.employeeDAO.findEmployeeById(empNo);
		
		if(employee == null) {
			return employee;
		}
		
		employee.setName(name);
		employee.setLastName(lastName);
		return this.employeeDAO.update(employee);
	}
	
	public boolean delete(Long empNo) {
		
		return this.employeeDAO.delete(empNo);
	}
	
}
