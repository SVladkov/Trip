package com.rest.example;

import java.sql.*;

public class Database {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/EMP";

   static final String USER = "pesho";
   static final String PASS = "napeshoparolata123";
   
   public static void addUser(String username, String password) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      Class.forName("com.mysql.jdbc.Driver");

	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql;
	      sql = "SELECT id, first, last, age FROM Employees";
	      
	      String insert;
	      insert = "INSERT INTO trips.users VALUES (42, '" + username + "', '" + password +"')";
	      stmt.executeUpdate(insert);

	      }catch(SQLException se){
		      se.printStackTrace();
	      }catch(Exception e){
		      e.printStackTrace();
	      }finally{
	    	  try{
	    		  if(stmt!=null)
	    			  stmt.close();
	    	  }catch(SQLException se2){
	    	  }
	    	  try{
	    		  if(conn!=null)
	    			  conn.close();
	    	  }catch(SQLException se){
	    		  se.printStackTrace();
	  }
	   }
	   System.out.println("Goodbye, be!");
   }
   
   public static void addTrip(String startPoint, String startCoordinates, String endPoint, String endCoordinates) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
	      Class.forName("com.mysql.jdbc.Driver");

	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);

	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      
	      String insert;
	      insert = "INSERT INTO trips.trips (startPoint, endPoint, startCoordinates, endCoordinates) VALUES ('" + startPoint + "', '" + endPoint + "', '" + startCoordinates + "', '" + endCoordinates + "')";
	      stmt.executeUpdate(insert); 

	      }catch(SQLException se){
		      se.printStackTrace();
	      }catch(Exception e){
		      e.printStackTrace();
	      }finally{
	    	  try{
	    		  if(stmt!=null)
	    			  stmt.close();
	    	  }catch(SQLException se2){
	    	  }
	    	  try{
	    		  if(conn!=null)
	    			  conn.close();
	    	  }catch(SQLException se){
	    		  se.printStackTrace();
	  }
	   }
	   System.out.println("Goodbye, be!");
   }
   
   public static void main(String[] args) {
	   addUser("sda", "da");
}//end main
}//end FirstExample