package db.connection.mongo.connection.collection;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;


import db.connection.mongo.connection.MongoDatabaseManager;
import db.connection.mysql.connection.model.Employee;
import db.connection.mysql.connection.model.EmployeeProfile;

public class MongoEmployeeCollectionDao {

	private MongoDatabase database;
	private String collectionName = "employees_profile_document";
	
	public MongoEmployeeCollectionDao(String dbName) {
		
		database = MongoDatabaseManager.getDB(dbName);
		
		createNewCollection(collectionName);
	}
	
	public void createNewCollection(String collectionName) {
		
		if(database.getCollection(collectionName) == null) 
		{
			database.createCollection(collectionName);
		}
	}
	
	public boolean save(EmployeeProfile employeeProfile) {
	
		try {
			
			MongoCollection<Document> collection = database.getCollection(collectionName);
			Employee employee = employeeProfile.getEmployee();
			
			
			Document employeeDoc = new Document();
			employeeDoc.put("mysql_id", employee.getId());
			employeeDoc.put("name", employee.getName());
			employeeDoc.put("last_name", employee.getLastName());
			employeeDoc.put("gender", employee.getGender());
			employeeDoc.put("birthdate", employee.getBirthDate());
			employeeDoc.put("hiredate", employee.getHireDate());
			employeeDoc.put("salaries", employeeProfile.getSalaries());
			employeeDoc.put("department_name", employeeProfile.getDepartmentName());
			
			collection.insertOne(employeeDoc);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean update(EmployeeProfile employeeProfile) {
		
		try {
			
			MongoCollection<Document> collection = database.getCollection(collectionName);
			
			Employee employee = employeeProfile.getEmployee();
			
			BasicDBObject query = new BasicDBObject();
			query.put("mysql_id", employee.getId());
			
			Document employeeDoc = new Document();
			employeeDoc.put("mysql_id", employee.getId());
			employeeDoc.put("name", employee.getName());
			employeeDoc.put("last_name", employee.getLastName());
			employeeDoc.put("gender", employee.getGender());
			employeeDoc.put("birthdate", employee.getBirthDate());
			employeeDoc.put("hiredate", employee.getHireDate());
			employeeDoc.put("salaries", employeeProfile.getSalaries());
			employeeDoc.put("department_name", employeeProfile.getDepartmentName());
			
			collection.updateOne(query, employeeDoc);
			
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public EmployeeProfile findByMySqlId(Long empNo) {
		
		MongoCollection<Document> collection = database.getCollection(collectionName);

		BasicDBObject query = new BasicDBObject();
		query.put("mysql_id", empNo);
		
		FindIterable<Document> cursor = collection.find(query);
		
		MongoCursor<Document> mongoCollectionIterator = cursor.iterator();
		Document document = mongoCollectionIterator.next();
		
		EmployeeProfile employeeProfile = new EmployeeProfile();
		
		Employee employee = new Employee();
		employee.setId(document.getLong("mysql_id"));
		employee.setName(document.getString("name"));
		employee.setLastName(document.getString("last_name"));
		employee.setGender(document.getString("gender"));
		employee.setBirthDate(document.getDate("birthdate"));
		employee.setHireDate(document.getDate("hiredate"));
		
		employeeProfile.setEmployee(employee);
		employeeProfile.setDepartmentName(document.getString("department_name"));
		
		@SuppressWarnings("unchecked")
		List<Long> salaries = document.get("salaries", new ArrayList<Long>().getClass());
		employeeProfile.setSalaries(salaries);
		
		return employeeProfile;
	}
	
}
