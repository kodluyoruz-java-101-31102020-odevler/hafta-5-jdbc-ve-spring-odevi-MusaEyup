package db.connection.mysql.connection.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import db.connection.mysql.connection.DbSQLQuery;
import db.connection.mysql.connection.model.Employee;
import db.connection.mysql.connection.model.EmployeeProfile;

public class EmployeeDAO {

	private static final Logger logger = Logger.getLogger(EmployeeDAO.class);

	public Set<Employee> getAll() {
		
		Set<Employee> employees = new TreeSet<Employee>();
		ResultSet resultSet = DbSQLQuery.select("SELECT * FROM employees");
		
		try {
			
			if(resultSet == null) {
				return employees;
			}
			
			while(resultSet.next()) {
				
				Employee emp = createEmployee(resultSet);
				employees.add(emp);
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return employees;
	}
	
	public EmployeeProfile loadEmployeeProfile(Long empNo) {
		
		String sql = "SELECT e.*, dp.dept_name as department_name FROM employees e LEFT JOIN dept_emp de ON e.emp_no = de.emp_no " +
		"LEFT JOIN departments dp ON dp.dept_no = de.dept_no " +  " WHERE e.emp_no=" + empNo;
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			resultSet.first();
			
			EmployeeProfile employeeProfile = new EmployeeProfile();
			Employee employee = createEmployee(resultSet);
			employeeProfile.setEmployee(employee);
			employeeProfile.setDepartmentName(resultSet.getString("department_name"));
			return employeeProfile;
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return null;
	}
	
	public Employee findEmployeeById(Long empNo) {
		
		String sql = "SELECT * FROM employees WHERE emp_no=" + empNo;
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		try {
			
			if(resultSet == null) {
				return null;
			}
			
			while(resultSet.next()) {
				return createEmployee(resultSet);
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	public Long getMaxId() {
		
		ResultSet resultSet = DbSQLQuery.select("SELECT MAX(emp_no) FROM employees");
		try {
			if(resultSet.next()) {
				return resultSet.getLong(1);
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return 0L;
	}
	
	public Employee save(Employee employee) {
		
		String sql = "INSERT INTO employees (emp_no, first_name, last_name, gender, birth_date, hire_date) VALUES(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			preparedStatement.setLong(1, employee.getId());
			preparedStatement.setString(2, employee.getName());
			preparedStatement.setString(3, employee.getLastName());
			preparedStatement.setString(4, employee.getGender());
			preparedStatement.setDate(5, new java.sql.Date(employee.getBirthDate().getTime()));
			preparedStatement.setDate(6, new java.sql.Date(employee.getHireDate().getTime()));
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return employee;
	}
	
	public void saveAll(List<Employee> employees) {
	
		String sql = "INSERT INTO employees (emp_no, first_name, last_name, gender, birth_date, hire_date) VALUES(?,?,?,?,?,?)";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			
			Iterator<Employee> iterator = employees.iterator();
			
			while(iterator.hasNext()) {
				
				Employee employee = iterator.next();
				preparedStatement.setLong(1, employee.getId());
				preparedStatement.setString(2, employee.getName());
				preparedStatement.setString(3, employee.getLastName());
				preparedStatement.setString(4, employee.getGender());
				preparedStatement.setDate(5, new java.sql.Date(employee.getBirthDate().getTime()));
				preparedStatement.setDate(6, new java.sql.Date(employee.getHireDate().getTime()));
				preparedStatement.addBatch();            
			}
			
			int[] effectedRows = preparedStatement.executeBatch();
			System.out.println(effectedRows.length + " rows effected in employees table!");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public Employee update(Employee employee) {
		
		String sql = "UPDATE employees SET first_name = ?, last_name = ?, gender = ?, birth_date = ?, hire_date = ? WHERE emp_no = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getGender());
			preparedStatement.setDate(4, new java.sql.Date(employee.getBirthDate().getTime()));
			preparedStatement.setDate(5, new java.sql.Date(employee.getHireDate().getTime()));
			preparedStatement.setLong(6, employee.getId());
			preparedStatement.executeUpdate();
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return employee;
	}
	
	public int updateAll(Set<Employee> employees) {
		
		String sql = "UPDATE employees SET first_name = ?, last_name = ?, gender = ?, birth_date = ?, hire_date = ? WHERE emp_no = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			
			Iterator<Employee> iterator = employees.iterator();
			while(iterator.hasNext()) {
				
				Employee employee = iterator.next();
				
				preparedStatement.setString(1, employee.getName());
				preparedStatement.setString(2, employee.getLastName());
				preparedStatement.setString(3, employee.getGender());
				preparedStatement.setDate(4, new java.sql.Date(employee.getBirthDate().getTime()));
				preparedStatement.setDate(5, new java.sql.Date(employee.getHireDate().getTime()));
				preparedStatement.setLong(6, employee.getId());
				preparedStatement.addBatch();
			}
			
			int[] uptadedRows = preparedStatement.executeBatch();
			
			System.out.println(uptadedRows.length + " rows updated!");
			return uptadedRows.length;
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return -1;
	}
	
	public boolean delete(Long empNo) {
		
		String sql = "DELETE FROM employees WHERE emp_no = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			
			preparedStatement.setLong(1, empNo);
			preparedStatement.executeUpdate();
			return true;
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	
	public int deleteAll(Set<Long> empNoList) {
		
		String sql = "DELETE FROM employees WHERE emp_no = ?";
		PreparedStatement preparedStatement = DbSQLQuery.createPreparedStatement(sql);
		
		try {
			
			Iterator<Long> iterator = empNoList.iterator();
			
			while(iterator.hasNext()) {
				
				Long empNo = iterator.next();
				preparedStatement.setLong(1, empNo);
				preparedStatement.addBatch();
			}
			
			int[] deletedRows = preparedStatement.executeBatch();
			System.out.println(deletedRows.length + " rows deleted!");
			
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		return -1;
	}
	
	private Employee createEmployee(ResultSet resultSet) throws SQLException {
		
		Employee employee = new Employee();
		
		employee.setId(resultSet.getLong("emp_no"));
		employee.setName(resultSet.getString("first_name"));
		employee.setLastName(resultSet.getString("last_name"));
		employee.setGender(resultSet.getString("gender"));
		employee.setBirthDate(resultSet.getDate("birth_date"));
		employee.setHireDate(resultSet.getDate("hire_date"));
		
		return employee;
	}
	
}
