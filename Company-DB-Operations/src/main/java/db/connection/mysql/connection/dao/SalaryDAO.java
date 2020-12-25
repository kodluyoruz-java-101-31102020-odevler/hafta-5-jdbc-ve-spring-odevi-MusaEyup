package db.connection.mysql.connection.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import db.connection.mysql.connection.DbSQLQuery;
import db.connection.mysql.connection.model.Salary;

public class SalaryDAO {

	private static final Logger logger = Logger.getLogger(SalaryDAO.class);
	
	public List<Salary> loadAllByEmployeeId(Long empNo) {
		
		String sql = "SELECT * FROM salaries WHERE emp_no = " + empNo;
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		List<Salary> salaries = new ArrayList<Salary>();
		
		try {
			
			if(resultSet == null) {
				return salaries;
			}
			
			while(resultSet.next()) 
			{
				salaries.add(createSalary(resultSet));
			}
			
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return salaries;
	}
	
	public List<Long> loadSalaryListOfEmployee(Long empNo) {
		
		String sql = "SELECT salary FROM salaries WHERE emp_no = " + empNo;
		ResultSet resultSet = DbSQLQuery.select(sql);
		
		List<Long> salaries = new ArrayList<Long>();
		
		try {
			
			if(resultSet == null) {
				return salaries;
			}
			
			while(resultSet.next()) {
				
				salaries.add(resultSet.getLong("salary"));
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return salaries;
	}
	
	
	private Salary createSalary(ResultSet resultSet) throws SQLException {
		
		Salary salary = new Salary();
		
		salary.setEmpNo(resultSet.getLong("emp_no"));
		salary.setSalary(resultSet.getInt("salary"));
		salary.setFromDate(resultSet.getDate("from_date"));
		salary.setToDate(resultSet.getDate("to_date"));
		return salary;
	}
	
}
