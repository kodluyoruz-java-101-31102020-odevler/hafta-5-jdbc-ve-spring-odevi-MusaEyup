package db.connection.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import db.connection.mongo.connection.MongoDatabaseManager;
import db.connection.mongo.connection.MongoDbConnection;

public class Main {

	public static String dbName = "employee";
	
	
	
	public static void main(String[] args) {
		
		// Get all database names
		
		MongoDbConnection.getMongoDbConnection().getDatabaseNames().stream().forEach(System.out::println);
		
		MongoDatabaseManager.getDB(dbName);
		
		MongoDatabaseManager.getDB(dbName).createCollection("kodluyoruz_demo_collection");

		MongoDbConnection.getMongoDbConnection().close();
		
	}

}
