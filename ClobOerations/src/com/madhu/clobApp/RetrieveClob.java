package com.madhu.clobApp;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

import com.madhu.JavaUtil.JavaUtil;

public class RetrieveClob {

	public static void main(String[] args) {
		Connection connection = null;
		PreparedStatement preparedStatement =null;
		ResultSet resultSet= null;
		 try {
			connection = JavaUtil.getJdbcConnection();
			String sqlSelectQueryString = "select id, name,history from person where id=?";
			if(connection!=null)
				preparedStatement = connection.prepareStatement(sqlSelectQueryString);
			if(preparedStatement!=null) {
				int id = 1;
				preparedStatement.setInt(1, id);
				resultSet=preparedStatement.executeQuery();
			}
			if(resultSet!=null) {
				if(resultSet.next()) {
					int id = resultSet.getInt(1);
					String nameString = resultSet.getString(2);
					Reader fr = resultSet.getCharacterStream(3);
					
					File file = new File("history-copied.txt");
					FileWriter fWriter = new FileWriter(file);
					
					//copyinf data from reader to writer
					IOUtils.copy(fr, fWriter);
					fWriter.close();
					
					System.out.println("id\tname\taddress");
					System.out.println(id+"\t"+nameString+"\t"+file.getAbsolutePath());
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
