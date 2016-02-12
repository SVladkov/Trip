package com.rest.example;

import java.io.IOException;
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
import org.json.*;
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
		JSONObject masterObject = new JSONObject(data);
		JSONArray arrayOfSegments = masterObject.getJSONArray("array");

		for(int i=0; i<arrayOfSegments.length(); i++) { 
			JSONObject segment = arrayOfSegments.getJSONObject(i);
			
			String startName = segment.getString("startName");
			String startCoordinates = segment.getString("startCoordinates");
			String endName = segment.getString("endName");
			String endCoordinates = segment.getString("endCoordinates");
						
			System.out.println(startName + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			
			DatabaseMongo db = new DatabaseMongo();
			db.addTrip(startName, startCoordinates, endName, endCoordinates);
		}
		//return "http://localhost:8080/SimpleREST/Gogel.html";
		//return Response.status(201).entity(result).build();
		return "happy";
	}
}
