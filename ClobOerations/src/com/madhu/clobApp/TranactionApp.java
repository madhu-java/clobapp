package com.madhu.clobApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.madhu.JavaUtil.JavaUtil;

public class TranactionApp {

	public static void main(String[] args) throws SQLException {

Connection connection= null;
ResultSet resultSet= null;
Statement statement= null;
Scanner scanner= null;
ResultSet rSet=null;
 try {
	connection= JavaUtil.getJdbcConnection();
	if(connection!=null)
		statement= connection.createStatement();
	resultSet=statement.executeQuery("Select name, balance from accounts");
	System.out.println("name\tbalance");
	while(resultSet.next()) {
		System.out.println(resultSet.getString(1)+"\t"+resultSet.getInt(2));
	}
	
	//transaction begin
	connection.setAutoCommit(false);
	//prepare the transationsa a a single unit
	
	statement.executeUpdate("update accounts set balance = balance-5000 where name='rohi'");
	statement.executeUpdate("update accounts set balance = balance+5000 where name='raki'");
	System.out.println("can you confirm transaction of 5000INR..Yes/NO");
	scanner=new Scanner(System.in);
	String in=scanner.next();
	if(in.equalsIgnoreCase("yes")) {
		connection.commit();
	}else {
		connection.rollback();
	}
	System.out.println("data after transaction...");
 rSet = statement.executeQuery("Select name, balance from accounts");
	System.out.println("name\tbalance");
	while(rSet.next()) {
		System.out.println(rSet.getString(1)+"\t"+rSet.getInt(2));
	}
	
	
	
	
} catch (SQLException | IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally {
	JavaUtil.closeResources(connection, statement, resultSet);
	JavaUtil.closeResources(null,null,rSet);
}
	}

}
