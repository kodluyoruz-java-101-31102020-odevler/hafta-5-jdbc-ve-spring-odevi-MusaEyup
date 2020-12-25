package db.connection.mysql.connection.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import db.connection.mysql.connection.DbSQLQuery;
import db.connection.mysql.connection.model.Department;


public class DepartmentDAO {

	private static final Logger logger = Logger.getLogger(DepartmentDAO.class);

	
	public List<Department> getAll() {
		
		List<Department> departments = new ArrayList<Department>();
		
		// Tüm departman listesini çeken SQL komutunu aþaðýdaki satýra yazýnýz.
		ResultSet resultSet = DbSQLQuery.select("select * from departments");
		
		try {
			
			// ResultSet içinde veritabanýndan gelen department kayýtlarý var.
			// ResultSet üzerinde satýr satýr ilerleyerek bir Department listesi oluþtur.
			// List<Department> departments bu listeye elemanlarý ekleyeceksiniz.
			
			// Kodlar ... :)
			
			if (resultSet == null) {
				return departments;
			}
			
			while (resultSet.next()) {
				departments.add(createDepartment(resultSet));
			}

			
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return departments;
	}
	
	private Department createDepartment(ResultSet rs) throws SQLException {

		return new Department(rs.getString("dept_no"), rs.getString("dept_name"));
	}
	
}
