package com.madhu.clobApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.madhu.JavaUtil.JavaUtil;

public class ClobApp {
	public static void main(String[] args) {

Connection connection= null;
PreparedStatement preparedStatement= null;
String nameString =null;
String fileLocString= null;

try {
	connection= JavaUtil.getJdbcConnection();
	 String sqlIInserStatement= "insert into person(`name`,`history`) values(?,?) ";	
	if(connection!=null)
		preparedStatement = connection.prepareStatement(sqlIInserStatement);
	if(preparedStatement!=null) {
		Scanner scanner = new Scanner(System.in);
		if(scanner!=null) {
		System.out.println("enter name:");
		nameString = scanner.next();
		System.out.println("Enter pdf file locatuon:");
		fileLocString= scanner.next(); 
		}
		//set the input values to query
		
		preparedStatement.setString(1, nameString);
		preparedStatement.setCharacterStream(2, new FileReader(new File(fileLocString)));
		
		//execute the query
		
		int rowsaffected = preparedStatement.executeUpdate();
		System.out.println("no of rows affected:"+rowsaffected);
	}
} catch (SQLException | IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally {
	try {
		JavaUtil.closeResources(connection, preparedStatement, null);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	}

}
