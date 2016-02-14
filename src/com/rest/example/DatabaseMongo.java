package com.rest.example;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.*;

public class DatabaseMongo {
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;	
	private MongoCollection<Document> collection;
	
	public DatabaseMongo() {
		mongoClient = new MongoClient();
		mongoDatabase = mongoClient.getDatabase("newDb");
		collection = mongoDatabase.getCollection("test");
	} 
	
	public void addTrip(String data) {
		JSONObject masterObject = new JSONObject(data);
		JSONArray arrayOfSegments = masterObject.getJSONArray("array");
		
		String name = masterObject.getString("name");
		Boolean isPublic = masterObject.getBoolean("isPublic");
		
		Document doc = new Document("name", name)
				.append("isPublic", isPublic);
		
		List<Document> segments = new ArrayList<Document>();
		
		for(int i=0; i<arrayOfSegments.length(); i++) { 
			JSONObject segment = arrayOfSegments.getJSONObject(i);
			
			Document segmentDocument = new Document();	
			
			segmentDocument.append("startName", segment.getString("startName"))
				.append("startCoordinates", segment.getString("startCoordinates"))
				.append("startDate", segment.getString("startDate"))
				.append("transportation", segment.getString("transportation"))
				.append("endName", segment.getString("endName"))
				.append("endCoordinate", segment.getString("endCoordinates"))
				.append("endDate", segment.getString("endDate"));
			
			segments.add(segmentDocument);
		}
		
		doc.append("segments", segments);
				
		collection.insertOne(doc);
	}
	
	/*public static void main(String [] args) {
		DatabaseMongo.addTrip("asd", "das", "gr", "hrtg");
	}*/
}
