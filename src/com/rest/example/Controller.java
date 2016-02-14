package com.rest.example;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

@Path("/trips")
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
		String email = newUser.getString("email");
		String password = newUser.getString("password");
		
		User user = new User(email, HashPassword(password));
		
		DatabaseMorphia db = new DatabaseMorphia();
		db.registerUser(user);
		
		return "http://localhost:8080/SimpleREST/map.html";
		//return Response.status(201).entity(result).build();
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public String loginUser(String data){
		JSONObject newUser = new JSONObject(data);
		String email = newUser.getString("email");
		String password = newUser.getString("password");
		
		User user = new User(email, HashPassword(password));
		
		DatabaseMorphia db = new DatabaseMorphia();
		String pass = db.getUserPass(user);
		
		System.out.println(pass + " " + user.getPassword());
		
		if(pass.equals(user.getPassword()))
		{
			return "http://localhost:8080/SimpleREST/map.html";
		}
		else
		{
			return "http://localhost:8080/SimpleREST/notValid.html";
		}
		//return Response.status(201).entity(result).build();
	}
	
	private static String HashPassword(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes()); 
            byte[] bytes = md.digest();
            
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        System.out.println(generatedPassword);
        
        return generatedPassword;
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
			String distanceAsString = segmentAsArray.getString("distance");
			
			// get rid of the "km" in "123 km"
			float distance = Float.parseFloat(distanceAsString.split(" ")[0]);
			
			Segment segment = new Segment(startName, startCoordinate, startDate, transport, endName, endCoordinates, endDate, distance);
			
			segments.add(segment);
		}
				
		String name = masterObject.getString("name");
		Boolean isPublic = masterObject.getBoolean("isPublic");
		
		Trip trip = new Trip(name, isPublic, segments);
		
		DatabaseMorphia db = new DatabaseMorphia();
		db.addTrip(trip);
		
		return "happy";
	}
}
