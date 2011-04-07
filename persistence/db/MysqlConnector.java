package persistence.db;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import domain.Data;

public class MysqlConnector implements Database{
	private static final connectionString="jdbc:mysql://localhost/datadb?user=root&password=";
	private static MysqlConnector uniqueInstance;
	private Connection connection;
	
	private MysqlConnector(){
		Class.forName("com.mysql.jdbc.Driver").getInstance();
		connection = DriverManager.getConnection(MysqlConnector.connectionString);
	}

	public static MysqlConnector getUniqueInstance(){
		if(MysqlConnector.uniqueInstance==null){
			MysqlConnector.uniqueInstance=new MysqlConnector();
		}
		return MysqlConnector.uniqueInstance;
	}
	
	@Override
	public void add(Data data) {
		
	}

	@Override
	public ArrayList<Data> load() {
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("SELECT * FROM t_data");
		ArrayList<Data> arrayList=new ArrayList<Data>;
		while(resultSet.next()){
			arrayList.add(new Data((double)resultSet.getFloat(2),(double)resultSet.getFloat(3)));
		}
		return arrayList;
	}
	
}
