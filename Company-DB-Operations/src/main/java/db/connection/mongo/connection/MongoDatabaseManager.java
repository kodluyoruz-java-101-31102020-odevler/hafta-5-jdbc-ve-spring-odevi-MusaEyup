package db.connection.mongo.connection;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.client.MongoDatabase;

public class MongoDatabaseManager {

	private static Map<String, MongoDatabase> mongoDatabases = new HashMap<String, MongoDatabase>();
	
	private static MongoDatabase createDB(String name) {
		
		if(!mongoDatabases.containsKey(name)) {
			
			MongoDatabase database = MongoDbConnection.getMongoDbConnection().getDatabase(name);
			if(database != null) {
				mongoDatabases.put(name, database);
			}
		}
		
		return mongoDatabases.get(name);
	}
	
	public static MongoDatabase getDB(String name) {
		
		createDB(name);
		
		return mongoDatabases.get(name);
	}
}
