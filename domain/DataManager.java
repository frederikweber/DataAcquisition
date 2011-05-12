package domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.apache.log4j.Logger;

import persistence.db.MysqlConnector;

public class DataManager extends Observable{
	private static DataManager uniqueInstance;
	private List<Data> dataList=new ArrayList<Data>();
	
	public static DataManager getUniqueInstance(){
		if(DataManager.uniqueInstance==null){
			DataManager.uniqueInstance=new DataManager();
		}
		return DataManager.uniqueInstance;
	}
	private DataManager(){
		Logger.getLogger(DataManager.class).trace("Neues DataManager Objekt");
	}
	public void addData(Data data){
		this.dataList.add(data);
		try {
			MysqlConnector.getUniqueInstance().add(data);
		} catch (SQLException e) {
			Logger.getLogger(DataManager.class).error("Fehler beim speichern", e);
		}
		this.setChanged();
		this.notifyObservers();
		Logger.getLogger(DataManager.class).trace("Neues Data Objekt im DataManager aufgenommen");
	}
	public List<Data> getDataList(){
		try {
			this.dataList=MysqlConnector.getUniqueInstance().load();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Logger.getLogger(DataManager.class).trace("DataList zurückgegeben");
		return this.dataList;
	}
	public void clear(){
		this.dataList=new ArrayList<Data>();
		try {
			MysqlConnector.getUniqueInstance().clear();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setChanged();
		this.notifyObservers();
		Logger.getLogger(DataManager.class).trace("DataList gelöscht");
	}
	public void delete(Data data){
		try {
			MysqlConnector.getUniqueInstance().delete(data);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
