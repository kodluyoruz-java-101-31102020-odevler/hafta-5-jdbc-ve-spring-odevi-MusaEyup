package db.connection.mysql.connection.service;

import java.util.List;

import db.connection.mysql.connection.dao.DepartmentDAO;
import db.connection.mysql.connection.model.Department;

public class DepartmentService {

	private DepartmentDAO departmentDAO;
	
	public DepartmentService(DepartmentDAO departmentDAO) {
		this.departmentDAO = departmentDAO;
	}
	
	// burada tüm departmanlarý listeleyen fonksiyonu yazýnýz.
	
	public List<Department> findAll(){
		return this.departmentDAO.getAll();
	}
}
