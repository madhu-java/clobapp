package com.madhu.clobApp;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.madhu.JavaUtil.JavaUtil;

public class RetrieveStoredProcedure {

	private static final String  storedProcedureCall= "{call student.GET_PRODUCT_BY_NAME(?,?)}";
	public static void main(String[] args) {

Connection connection= null;
CallableStatement callableStatement = null;
ResultSet resultSet = null;
boolean recordAvailable= false;

try {
	connection=JavaUtil.getJdbcConnection();
	if(connection!=null)
		callableStatement= connection.prepareCall(storedProcedureCall);
	Scanner scanner= new Scanner(System.in);
	System.out.println("enter product name:");
	String name1= scanner.next();
	System.out.println("enter product name:");
	String name2= scanner.next();
	
	//set the input values
	if(callableStatement!=null) {
		callableStatement.setString(1, name1);
		callableStatement.setString(2, name2);
	}
	
	//run the stored procedure
	if(callableStatement!=null) {
		
		callableStatement.execute();
	}
	
	//retrieve the result
	if(callableStatement!=null) {
		resultSet = callableStatement.getResultSet();
	}
	//process the result set
	
	if(resultSet!=null) {
		System.out.println("id\tname\tprice\tqty");
		while(resultSet.next()) {
			int id = resultSet.getInt(1);
			String nameString = resultSet.getString(2);
			int price= resultSet.getInt(3);
			int qty= resultSet.getInt(4);
			
			System.out.println(id+"\t"+nameString+"\t"+price+"\t"+qty);
			recordAvailable=true;
		}
	}
	if(recordAvailable) {
		System.out.println("Records avaiable and displayed");
	}else {
		System.out.println("records not available");
	}

} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally {
	try {
		JavaUtil.closeResources(connection, callableStatement, resultSet);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

	}

}
