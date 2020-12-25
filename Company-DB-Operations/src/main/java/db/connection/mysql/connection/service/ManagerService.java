package db.connection.mysql.connection.service;

import java.util.List;

import db.connection.mysql.connection.dao.ManagerDAO;
import db.connection.mysql.connection.model.Manager;

public class ManagerService {

	private ManagerDAO managerDAO;
	
	public ManagerService(ManagerDAO managerDAO) {
		this.managerDAO = managerDAO;
	}
	
	// buraya aktif yöneticileri listeleyen bir fonksiyon yazýnýz.
	
	public List<Manager> listAllManager(){
		return this.managerDAO.loadAllActiveManagers();
	}
	
}
