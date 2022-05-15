package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class Item 
{ //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.cj.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/world", "root", "kavinda@12345"); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertItem(String CustomerName, String AccountNo, String BillDate, String BillTime){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into cusservice (`BillID`,`CustomerName`,`AccountNo`,`BillDate`,`BillTime`, )"
								 + " values ( ?, ?, ?, ?, ?, ?)";  
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						 preparedStmt.setInt(1, 0); 
						 preparedStmt.setString(2, CustomerName); 
						 preparedStmt.setString(3, AccountNo); 
						 preparedStmt.setString(4, BillDate); 
						 preparedStmt.setString(5, BillTime);						 
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						
						String newItems = readItems(); 
						output = "{\"status\":\"success\",\"data\":\""+newItems+"\"}"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}"; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
		public String readItems() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the html table to be displayed
		 /*output = "<table border='1'><tr><th>AccountNoCustomerName</th>" +"<th>Address</th>"
		 		    + "<th>AccountNO</th>"
		    		+ "<th>BillDate</th>" 
				    + "<th>BillTime</th>"
		    		+ "<th>Update</th><th>Remove</th></tr>"; */
		 
		 String query = "select * from cusservice"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		
		 output = "<table border='1'><tr><th>AccountNo</th>" +"<th>Address</th>"
		    + "<th>Inquiry</th>"
 		+ "<th>Status</th>" 
		    + "<th>TelNo</th>"
 		+ "<th>Update</th><th>Remove</th></tr>"; 
		 
		 
		 // iterate through the rows in the result set
		 
		 while (rs.next()) 
		 { 
			 String BillId = Integer.toString(rs.getInt("BillId")); 
			 String CustomerName = rs.getString("CustomerName"); 
			 String AccountNo = rs.getString("AccountNo"); 
			 String BillDate = rs.getString("BillDate");
			 String BillTime = rs.getString("BillTime");		
		 // Add into the html table
		 output +=  "<tr><td>" + CustomerName + "</td>";
		 output += "<td>" + AccountNo + "</td>"; 
		 output += "<td>" + BillDate + "</td>"; 
		 output += "<td>" + BillTime + "</td>"; 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-sm btn-secondary' data-serviceid='" + BillId + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btn btn-sm btn-danger btnRemove' data-serviceid='" + BillId + "'></td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the html table
		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

			
			
			public String updateItem(String BillId, String CustomerName, String AccountNo, String BillDate, String BillTime){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							 String query = "UPDATE cusservice SET AccountNo=?, Address=?, Inquiry=?, Status=?, TelNo=? WHERE ServiceId=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							 preparedStmt.setString(1, CustomerName); 
							 preparedStmt.setString(2, AccountNo); 
							 preparedStmt.setString(3, BillDate); 
							 preparedStmt.setString(4, BillTime); ;
							 preparedStmt.setInt(6, Integer.parseInt(BillId));
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							String newItems = readItems(); 
							output = "{\"status\":\"success\",\"data\":\""+newItems+"\"}"; 

					} 
					
					catch (Exception e){ 
						
						output = "{\"status\":\"error\",\"data\":\"Error while updating the item.\"}"; 

						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deleteItem(String BillId){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						 String query = "delete from cusservice where ServiceId=?";
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(BillId)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newItems = readItems(); 
						 output = "{\"status\":\"success\",\"data\":\""+newItems+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the item.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
