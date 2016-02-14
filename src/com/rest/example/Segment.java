package com.rest.example;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity("segments")
public class Segment {
	@Id private ObjectId id;
	private String startName;
	private String startCoordinates;
	private String startDate;
	private String transport;
	private String endName;
	private String endCoordinates;
	private String endDate;
	private float distance;
	
	public Segment(String initialStartName, String initialStartCoordinates, String initialStartDate,
			String initialTransport,
			String initialEndName, String initialEndCoordinates, String initialEndDate, float initialDistance) {
		this.startName = initialStartName;
		this.startCoordinates = initialStartCoordinates;
		this.startDate = initialStartDate;
		this.transport = initialTransport;
		this.endName = initialEndName;
		this.endCoordinates = initialEndCoordinates;
		this.endDate = initialEndDate;
		this.distance = initialDistance;
	}
	
	/*public String GetStartName() {
		return this.startName;
	}
	
	public String GetStartCoordinates() {
		return this.startCoordinates;
	}
	
	public String GetStartDate() {
		return this.startDate;
	}
	
	public String GetTransport() {
		return this.transport;
	}
	
	public String GetEndName() {
		return this.endName;
	}
	
	public String GetEndCoordinates() {
		return this.endCoordinates;
	}
	
	public String GetEndDate() {
		return this.endDate;
	}*/
}