package com.madhu.clobApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.madhu.JavaUtil.JavaUtil;

public class ScrollableApp {

	public static void main(String[] args) throws SQLException {

Connection connection= null;
ResultSet resultSet= null;
Statement statement= null;
Scanner scanner= null;
ResultSet rSet=null;
 try {
	connection= JavaUtil.getJdbcConnection(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
	if(connection!=null)
		statement= connection.createStatement();
	
	System.out.println("printing in forward direction....");
	resultSet=statement.executeQuery("Select id,name,age,address from student");
	System.out.println("id\tname\tage\taddress");
	while(resultSet.next()) {
		System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
		
	}
	
	
//	System.out.println("printing in backward direction....");
//	while(resultSet.previous()) {
//		System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
//		
//	}
	
	System.out.println("printing fist record...");
	resultSet.first();
	System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
	

	System.out.println("printing last record...");
	resultSet.last();
	System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
	
	

	System.out.println("printing third record...");
	resultSet.absolute(3);
	System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
	

	System.out.println("printing seconf  record current podition...");
	resultSet.relative(2);
	System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
	
	
} catch (SQLException | IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally {
	JavaUtil.closeResources(connection, statement, resultSet);
	JavaUtil.closeResources(null,null,rSet);
}
	}

}
