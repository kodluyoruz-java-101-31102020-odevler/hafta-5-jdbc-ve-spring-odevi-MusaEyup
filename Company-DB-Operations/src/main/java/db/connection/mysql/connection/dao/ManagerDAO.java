package db.connection.mysql.connection.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.connection.mysql.connection.DbSQLQuery;
import db.connection.mysql.connection.model.Employee;
import db.connection.mysql.connection.model.Manager;

public class ManagerDAO {

	public List<Manager> loadAllActiveManagers() {
		
		List<Manager> managers = new ArrayList<Manager>();
		
		// Burada halen aktif olarak yöneticilik yapan tüm çalýþanlarý departman isimleriyle birlikte liste halinde hazýrlayýnýz.
		// Manager sýnýfýný sizin oluþturdum. 
		// Ýpucu: Bu sorgunun bir benzerini derste anlatmýþtým. Hatta "sql_query_samples.sql" dosyasý içinde benzeri mevcut.
		String sql = "select emp.*, dm.*, dp.* from employees emp left join dept_manager dm on dm.emp_no = emp.emp_no"
				+ " left join departments dp on dp.dept_no = dm.dept_no "
				+ " where dm.to_date >= NOW()";
		try {
			
			ResultSet resultSet = DbSQLQuery.select(sql);
			
			while(resultSet.next()) {
				
				Manager manager = createManager(resultSet);
				managers.add(manager);
		}
			
		}
			catch (SQLException e) {
					
					e.printStackTrace();
				}
				

		
		return managers;
	}

	private Manager createManager(ResultSet rs) throws SQLException {
		
		Employee emp = new Employee();
		emp.setId(rs.getLong("emp_no"));
		emp.setName(rs.getString("first_name"));
		emp.setLastName(rs.getString("last_name"));
		emp.setGender(rs.getString("gender"));
		emp.setBirthDate(rs.getDate("birth_date"));
		emp.setHireDate(rs.getDate("hire_date"));
		
		Manager tempManager = new Manager(emp, rs.getString("dept_name"));

		return tempManager;
	}
	
}
