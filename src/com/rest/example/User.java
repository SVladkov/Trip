package com.rest.example;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity
public class User {
	@Id private ObjectId id;
	private String email;
	private String password;
	
	public User(String initialEmail, String initialPassword) {
		this.email = initialEmail;
		this.password = initialPassword;
	}
	
	// This is needed in order to perform the queries!!!
	public User(){
	
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
}
