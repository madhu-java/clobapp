package com.madhu.clobApp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.omg.CORBA.PRIVATE_MEMBER;

import com.madhu.JavaUtil.JavaUtil;
public class RetriveProcedure
{


private static final String sqlProcedurecall= "{call GET_PRODUCT_ByID(?,?,?,?)}";	

	public static void main(String[] args) {

Connection connection= null;
CallableStatement callableStatement=null;
int id =0;
ResultSet resultSet= null;


try {
	connection= JavaUtil.getJdbcConnection();
	
	if(connection!=null)
		callableStatement=connection.prepareCall(sqlProcedurecall);
	Scanner scanner = new Scanner(System.in);
	if(scanner!=null) {
	System.out.println("enter id:");
	id= scanner.nextInt();
	}
	
	if(callableStatement!=null) {
		
		//set the input values to query
		
	callableStatement.setInt(1, id);
	}
	//setting the dattype of out put values
	if(callableStatement!=null) {
		
	callableStatement.registerOutParameter(2, Types.VARCHAR);
	callableStatement.registerOutParameter(3, Types.INTEGER);
	callableStatement.registerOutParameter(4, Types.INTEGER);
		
	
	}
	//run the stored procedre
	if(callableStatement!=null) {
			callableStatement.execute();
		
	}
	//retrieve the result
	if(callableStatement!=null) {
		System.out.println("product name:"+callableStatement.getString(2));
		System.out.println("product cost:"+callableStatement.getInt(3));
		System.out.println("product quantity:"+callableStatement.getInt(4));
	
}
} catch (SQLException | IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}finally {
	try {
		JavaUtil.closeResources(connection, callableStatement, null);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	}

}


