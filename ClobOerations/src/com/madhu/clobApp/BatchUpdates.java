package com.madhu.clobApp;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.madhu.JavaUtil.JavaUtil;

public class BatchUpdates {

	
	public static void main(String[] args) throws ParseException {

Connection connection= null;
PreparedStatement preparedStatement= null;


try {
	connection=JavaUtil.getJdbcConnection();
	String sqlInertQueryString="Insert into users(`name`,`dob`,`dom`) values(?,?,?)";
	
	if(connection!=null)
		preparedStatement= connection.prepareStatement(sqlInertQueryString);
	Scanner scanner= new Scanner(System.in);
	if(preparedStatement!=null) {
	while(true) {
//	System.out.println("enter product id:");
//	int id= scanner.nextInt();
	System.out.println("enter  name:");
	String name2= scanner.next();
	System.out.println("enter dob in (dd-mm-yyyy)format");
	String dateString = scanner.next();
	SimpleDateFormat sDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	java.util.Date date = sDateFormat.parse(dateString);
	long value= date.getTime();
	java.sql.Date dob = new java.sql.Date(value);
	System.out.println("enter date in (yyyy-MM-dd) format");
	java.sql.Date dom=java.sql.Date.valueOf(scanner.next());
	
	//preparedStatement.setInt(1, id);
	preparedStatement.setString(1, name2);
	preparedStatement.setDate(2, dob);
	preparedStatement.setDate(3, dom);
	
	//query added to the batch
	preparedStatement.addBatch();
	
	System.out.println("do you want to add one more record YES/NO");
	String inputString= scanner.next();
	if(inputString.equalsIgnoreCase("no")) {
		break;
	}
	}
	//executing the records from the batch
	preparedStatement.executeBatch();
	
	System.out.println("Records inserted succesfully...");
	}

} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally {
	try {
		JavaUtil.closeResources(connection,preparedStatement, null);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}	
}
}
