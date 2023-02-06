package com.madhu.clobApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

import com.madhu.JavaUtil.JavaUtil;

public class TranactionSavepointApp {

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
	
	
	//transaction begin
	connection.setAutoCommit(false);
	//connection.setAutoCommit(false);
	//prepare the transationsa a a single unit
	
	statement.executeUpdate("insert into politicians(`name`,`party`) values('Modi','Bjp')");
	statement.executeUpdate("insert into politicians(`name`,`party`) values('KCR','TRS')");
	Savepoint sp = connection.setSavepoint();
	statement.executeUpdate("insert into politicians(`name`,`party`) values('KTR','TRS')");
	
	System.out.println("oopss.. somethng went wrong....");
	connection.rollback(sp);
	System.out.println("operation are rolled back to sav point..");
	connection.commit();
	
	
	
	
} catch (SQLException | IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally {
	JavaUtil.closeResources(connection, statement, resultSet);
	JavaUtil.closeResources(null,null,rSet);
}
	}

}
