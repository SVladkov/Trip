package com.rest.example;

import java.util.List;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.bson.types.ObjectId;

@Entity
public class Trip {
	@Id private ObjectId id;
	private String name;
	private boolean isPublic;
	private List<Segment> segments;

	public Trip(String initialName, boolean initialIsPublic, List<Segment> initialSegments) {
		this.name = initialName;
		this.isPublic = initialIsPublic;
		this.segments = initialSegments;
	}
}
