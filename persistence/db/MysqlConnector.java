package persistence.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import domain.Data;

public class MysqlConnector implements Database{
	private static final String connectionString="jdbc:mysql://localhost/datadb?user=root&password=";
	private static MysqlConnector uniqueInstance;
	private Connection connection;
	private String name;
	
	private MysqlConnector(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(MysqlConnector.connectionString);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static MysqlConnector getUniqueInstance(){
		if(MysqlConnector.uniqueInstance==null){
			MysqlConnector.uniqueInstance=new MysqlConnector();
		}
		return MysqlConnector.uniqueInstance;
	}
	
	@Override
	public void add(Data data) throws SQLException{
		Statement statement=connection.createStatement();
		statement.execute("INSERT INTO t_data(x,y) VALUES ('"+data.getX()+"','"+data.getY()+"');");
	}

	@Override
	public List<Data> load() throws SQLException{
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("SELECT * FROM t_data;");
		List<Data> arrayList=new ArrayList<Data>();
		while(resultSet.next()){
			arrayList.add(new Data((double)resultSet.getFloat(2),(double)resultSet.getFloat(3)));
		}
		return arrayList;
	}
	
	public void delete(Data data) throws SQLException{
		Statement statement=connection.createStatement();
		statement.execute("DELETE FROM t_data WHERE x="+data.getX()+" AND y="+data.getY()+";");
	}
	
	public void clear() throws SQLException{
		Statement statement=connection.createStatement();
		statement.execute("DELETE FROM t_data;");
	}

	@Override
	public void setName(String name) {
		this.name=name;		
	}
}
