package com.madhu.clobApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.madhu.JavaUtil.JavaUtil;

public class SensitiveResultset {

	public static void main(String[] args) throws SQLException {

Connection connection= null;
ResultSet resultSet= null;
Statement statement= null;
Scanner scanner= null;
ResultSet rSet=null;
 try {
	connection= JavaUtil.getJdbcConnection();
	if(connection!=null)
		statement= connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE   ,ResultSet.CONCUR_UPDATABLE);
	
	System.out.println("printing in forward direction....");
	resultSet=statement.executeQuery("Select id,name,age,address from student");
	System.out.println("id\tname\tage\taddress");
	while(resultSet.next()) {
		System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
		
	}
	
	resultSet.moveToInsertRow();
	resultSet.updateInt(1, 101);
	resultSet.updateString(2, "hope");
	resultSet.updateInt(3, 12);
	resultSet.updateString(4, "Dl");
	//resultSet.insertRow();
	resultSet.last();
	//resultSet.deleteRow();
	System.out.println("after inserting first row....");
	resultSet.beforeFirst();
	//update address to "MI" whose age is >40"
	
	while(resultSet.next()) {
		
		int age = resultSet.getInt(3);
		if(age>40) {
			resultSet.updateString(4, "MI");
			resultSet.updateRow();
		}
	}
	System.out.println("updated....");
	System.out.println("id\tname\tage\taddress");
	while(resultSet.next()) {
		System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
		
	}
	
//	System.out.println("printing in backward direction....");
//	while(resultSet.previous()) {
//		System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
//		
//	}
	
	
	
} catch (SQLException | IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally {
	JavaUtil.closeResources(connection, statement, resultSet);
	JavaUtil.closeResources(null,null,rSet);
}
	}

}

