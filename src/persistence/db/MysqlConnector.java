package persistence.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Data;

public class MysqlConnector implements Database{
	private static MysqlConnector uniqueInstance;
	private Connection connection;
	private Properties props;
	
	private MysqlConnector(){
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Properties properties = new Properties();
			properties.setProperty("db.host", "localhost");
			properties.setProperty("db.db", "datadb");
			properties.setProperty("db.table", "t_data");
			properties.setProperty("db.user", "root");
			properties.setProperty("db.password", "");
			this.setProperties(properties);
			connection = DriverManager.getConnection("jdbc:mysql://"
					+this.props.getProperty("db.host")
					+"/"
					+this.props.getProperty("db.db")
					+"?user="
					+this.props.getProperty("db.user")
					+"&password="
					+this.props.getProperty("db.password"));
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
		statement.execute("INSERT INTO "+this.props.getProperty("db.table")+"(x,y) VALUES ('"+data.getX()+"','"+data.getY()+"');");
	}

	@Override
	public List<Data> load() throws SQLException{
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("SELECT * FROM "+this.props.getProperty("db.table")+";");
		List<Data> arrayList=new ArrayList<Data>();
		while(resultSet.next()){
			arrayList.add(new Data((double)resultSet.getFloat(2),(double)resultSet.getFloat(3)));
		}
		return arrayList;
	}
	
	public void delete(Data data) throws SQLException{
		Statement statement=connection.createStatement();
		statement.execute("DELETE FROM "+this.props.getProperty("db.table")+" WHERE x="+data.getX()+" AND y="+data.getY()+";");
	}
	
	public void clear() throws SQLException{
		Statement statement=connection.createStatement();
		statement.execute("DELETE FROM "+this.props.getProperty("db.table")+";");
	}

	@Override
	public void setProperties(Properties props) {
		this.props = props;		
	}
}
