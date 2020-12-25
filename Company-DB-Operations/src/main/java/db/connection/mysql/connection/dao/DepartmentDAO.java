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
		
		// T�m departman listesini �eken SQL komutunu a�a��daki sat�ra yaz�n�z.
		ResultSet resultSet = DbSQLQuery.select("select * from departments");
		
		try {
			
			// ResultSet i�inde veritaban�ndan gelen department kay�tlar� var.
			// ResultSet �zerinde sat�r sat�r ilerleyerek bir Department listesi olu�tur.
			// List<Department> departments bu listeye elemanlar� ekleyeceksiniz.
			
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
