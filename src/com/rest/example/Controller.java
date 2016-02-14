package com.rest.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bson.Document;
import org.json.*;

import com.mongodb.MongoClient;
import com.rest.example.*;

@Path("/hello")
public class Controller {
	
	/*@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
		String output = "Welcome: " + msg;
		return Response.status(200).entity(output).build();
	}*/
	
	@GET
	@Path("/welcome")
	@Produces(MediaType.TEXT_HTML)
	public String getMsg() {
		return "Hello from the otter site";
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerUser(String data){
		JSONObject newUser = new JSONObject(data);
		String username = newUser.getString("username");
		String password = newUser.getString("password");
		
		String result = username + " " + password;
		
		System.out.println(result);
		
		Database.addUser(username, password);
		
		return "http://localhost:8080/SimpleREST/Gogel.html";
		//return Response.status(201).entity(result).build();
	}
	
	@POST
	@Path("/trip")
	@Consumes(MediaType.APPLICATION_JSON)
	public String addTrip(String data){

		//DatabaseMongo db = new DatabaseMongo();
		//db.addTrip(data);

		JSONObject masterObject = new JSONObject(data);
		JSONArray arrayOfSegments = masterObject.getJSONArray("array");
		
		List<Segment> segments = new ArrayList<Segment>();
		
		for(int i=0; i<arrayOfSegments.length(); i++) { 
			JSONObject segmentAsArray = arrayOfSegments.getJSONObject(i);
			
			String startName = segmentAsArray.getString("startName");
			String startCoordinate = segmentAsArray.getString("startCoordinates");
			String startDate = segmentAsArray.getString("startDate");
			String transport = segmentAsArray.getString("transportation");
			String endName = segmentAsArray.getString("endName");
			String endCoordinates = segmentAsArray.getString("endCoordinates");
			String endDate = segmentAsArray.getString("endDate");
			
			Segment segment = new Segment(startName, startCoordinate, startDate, transport, endName, endCoordinates, endDate);
			
			segments.add(segment);
		}
				
		String name = masterObject.getString("name");
		Boolean isPublic = masterObject.getBoolean("isPublic");
		
		Trip trip = new Trip(name, isPublic, segments);
		
		//Document doc = new Document("name", name)
		// 		.append("isPublic", isPublic);
		
		DatabaseMorphia db = new DatabaseMorphia();
		
		db.addTrip(trip);
		
		return "happy";
	}
}
