package db.connection.mysql.connection.service;

import java.util.List;

import db.connection.mysql.connection.dao.ManagerDAO;
import db.connection.mysql.connection.model.Manager;

public class ManagerService {

	private ManagerDAO managerDAO;
	
	public ManagerService(ManagerDAO managerDAO) {
		this.managerDAO = managerDAO;
	}
	
	// buraya aktif y�neticileri listeleyen bir fonksiyon yaz�n�z.
	
	public List<Manager> listAllManager(){
		return this.managerDAO.loadAllActiveManagers();
	}
	
}
