package com.rest.example;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class DatabaseMongo {
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;	
	private MongoCollection<Document> collection;
	
	public DatabaseMongo() {
		mongoClient = new MongoClient();
		mongoDatabase = mongoClient.getDatabase("newDb");
		collection = mongoDatabase.getCollection("test");
	} 
	
	public void addTrip(String startPoint, String startCoordinates, String endPoint, String endCoordinates) {
		Document doc = new Document("startPoint", startPoint)
				.append("startCoordinates", startCoordinates)
				.append("endPoint", endPoint)
				.append("endCoordinates", endCoordinates);
				
		collection.insertOne(doc);
	}
	
	/*public static void main(String [] args) {
		DatabaseMongo.addTrip("asd", "das", "gr", "hrtg");
	}*/
}
