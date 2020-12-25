package db.connection.mysql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import db.connection.mongo.connection.MongoDbConnection;
import db.connection.mongo.connection.collection.MongoEmployeeCollectionDao;
import db.connection.mysql.connection.DbConnection;
import db.connection.mysql.connection.dao.EmployeeDAO;
import db.connection.mysql.connection.dao.SalaryDAO;
import db.connection.mysql.connection.model.Employee;
import db.connection.mysql.connection.model.EmployeeProfile;
import db.connection.mysql.connection.model.Salary;

public class MainSamples {

	public static void main(String[] args) throws SQLException {
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		EmployeeProfile employeeProfile = employeeDAO.loadEmployeeProfile(10002L);
		System.out.println(employeeProfile);
		
		
		Set<Employee> employees = employeeDAO.getAll();
		printEmployeeList(employees);
		
		MongoEmployeeCollectionDao mongoEmployeeCollectionDao = new MongoEmployeeCollectionDao("employee");
		
		for(Employee employee : employees) {
			
			EmployeeProfile currentEmployeeProfile = employeeDAO.loadEmployeeProfile(employee.getId());
			mongoEmployeeCollectionDao.save(currentEmployeeProfile);
		}
		
		
		Long maxEmpNo = employeeDAO.getMaxId();
		System.out.println("Max Employee ID: " + maxEmpNo);
		
		
		SalaryDAO salaryDAO = new SalaryDAO();
		List<Salary> salaries = salaryDAO.loadAllByEmployeeId(maxEmpNo);
		printSalaryListOfEmployee(salaries);
		
		Employee emp = new Employee();
		emp.setId(maxEmpNo + 1);
		emp.setName("Batuhan");
		emp.setLastName("Duzgun");
		emp.setGender("M");
		emp.setBirthDate(new Date());
		emp.setHireDate(new Date());
		emp = employeeDAO.save(emp);
		
		emp = employeeDAO.findEmployeeById(emp.getId());
		System.out.println(emp);

		emp.setLastName("Düzgün");
		emp.setHireDate(new Date());
		emp = employeeDAO.update(emp);
		
		emp = employeeDAO.findEmployeeById(emp.getId());
		System.out.println(emp);
		
		boolean result = employeeDAO.delete(emp.getId());
		System.out.println(emp.getId() + " record deleted : " + result);
		
		
		maxEmpNo = employeeDAO.getMaxId();
		maxEmpNo = maxEmpNo + 1;
		
		Employee emp2 = new Employee();
		emp2.setId(maxEmpNo);
		emp2.setName("Batuhan");
		emp2.setLastName("Duzgun");
		emp2.setGender("M");
		emp2.setBirthDate(new Date());
		emp2.setHireDate(new Date());
		
		maxEmpNo = maxEmpNo + 1;
		Employee emp3 = new Employee();
		emp3.setId(maxEmpNo);
		emp3.setName("Batuhan");
		emp3.setLastName("Duzgun");
		emp3.setGender("M");
		emp3.setBirthDate(new Date());
		emp3.setHireDate(new Date());
		
		List<Employee> employeesBulk = new ArrayList<Employee>();
		employeesBulk.add(emp2);
		employeesBulk.add(emp3);
		
		employeeDAO.saveAll(employeesBulk);
		
		DbConnection.getDbConnection().close();
		MongoDbConnection.getMongoDbConnection().close();
	}
	
	private static void printEmployeeList(Set<Employee> employees) {
		
		for(Employee employee : employees) {
			System.out.println(employee);
		}
		
	}
	
	private static void printSalaryListOfEmployee(List<Salary> salaries) {
		
		for(Salary salary : salaries) {
			System.out.println(salary);
		}
		
	}

}
