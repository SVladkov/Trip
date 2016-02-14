package com.rest.example;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

class DatabaseMorphia {
	Morphia morphia;
	Datastore datastore;
	
	public DatabaseMorphia() {
		morphia = new Morphia();
		morphia.mapPackage("com.rest.example");
		
		datastore = morphia.createDatastore(new MongoClient(), "newDb");
		datastore.ensureIndexes();
	}
	
	public void addSegment(Segment segment) {
		datastore.save(segment);
	}
	
	public void addTrip(Trip trip) {
		datastore.save(trip);
	}
}