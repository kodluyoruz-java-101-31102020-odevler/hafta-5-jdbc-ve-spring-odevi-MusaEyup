package db.connection.mongo.connection;

import com.mongodb.MongoClient;

public class MongoDbConnection {

	private static String hostName = "localhost";
	private static int portNo = 27017;
	private static MongoClient mongoClient;
	
	public static MongoClient getMongoDbConnection() {
		
		
		if(mongoClient == null) {
			mongoClient = new MongoClient(hostName, portNo);
		}
		return mongoClient;
	}
	
}
