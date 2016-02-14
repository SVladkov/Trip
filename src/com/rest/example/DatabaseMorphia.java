package com.rest.example;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

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
	
	public void registerUser(User user) {
		datastore.save(user);
	}
	
	public String getUserPass(User user) {
		Query q = datastore.createQuery(User.class).field("email").equal(user.getEmail());
		
		User user1 = (User)q.get();
		
		return user1.getPassword();
	}
	
	public void getSumOfDistance() {
		//Iterator<Author>
	}
}