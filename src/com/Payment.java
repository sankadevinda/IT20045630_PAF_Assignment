package com;

import java.sql.*;

public class Payment {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String readPayments() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			
			output = "<table class='table table-hover'><tr><th>Customer Name</th><th>Account Number</th><th>Date</th>"
					+ "<th>Payment Type</th><th>Total Price</th><th>Update</th><th>Remove</th></tr>";

			String query = "select * from payments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String customer_name = rs.getString("customer_name");
				String account_number = rs.getString("account_number");
				String date = rs.getString("date");
				String payment_type = rs.getString("payment_type");
				String total_price = rs.getString("total_price");
				// Add into the html table
				output += "<tr><td><input id='hididUpdate' name='hididUpdate' type='hidden' value='" + id
						+ "'>" + customer_name + "</td>";
				output += "<td>" + account_number + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + payment_type + "</td>";
				output += "<td>" + total_price + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
						+ id + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payments.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String insertPayments(String customer_name, String account_number, String date, String payment_type,
			String total_price) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payments(`id`,`customer_name`,`account_number`,`date`,`payment_type`,`total_price`)"
					+ "values(?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			System.out.println(total_price);
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customer_name);
			preparedStmt.setString(3, account_number);
			preparedStmt.setString(4, date);
			preparedStmt.setString(5, payment_type);
			preparedStmt.setString(6, total_price);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUse = readPayments();
			output = "{\"status\":\"success\", \"data\": \"" +newUse+ "\"}";
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Users.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayments(String id,String customer_name, String account_number, String date, String payment_type,
			String total_price) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE payments SET customer_name=?,account_number=?,date=?,payment_type=?,total_price=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			
		
			preparedStmt.setString(1, customer_name);
			preparedStmt.setString(2, account_number);
			preparedStmt.setString(3, date);
			preparedStmt.setString(4, payment_type); 
			preparedStmt.setString(5, total_price);
			preparedStmt.setInt(6, Integer.parseInt(id));
	
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUse =readPayments();
			output = "{\"status\":\"success\", \"data\": \"" +newUse+ "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deletePayments(String id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from payments where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newUse = readPayments();
			output = "{\"status\":\"success\", \"data\": \"" +newUse + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readPaymentsonly() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			
			output = "<table class='table table-hover'><tr><th>Customer Name</th><th>Account Number</th><th>Date</th>"
					+ "<th>Payment Type</th><th>Total Price</th><th>Remove</th></tr>";

			String query = "select * from payments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String customer_name = rs.getString("customer_name");
				String account_number = rs.getString("account_number");
				String date = rs.getString("date");
				String payment_type = rs.getString("payment_type");
				String total_price = rs.getString("total_price");
				// Add into the html table
				output += "<tr><td><input id='hididUpdate' name='hididUpdate' type='hidden' value='" + id
						+ "'>" + customer_name + "</td>";
				output += "<td>" + account_number + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + payment_type + "</td>";
				output += "<td>" + total_price + "</td>";
				// buttons
				output += "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"
						+ id + "'>" + "</td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Payments.";
			System.err.println(e.getMessage());
		}
		return output;
	}

}