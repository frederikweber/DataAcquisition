package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Properties;

import org.apache.log4j.Logger;

import persistence.db.CSV;
import persistence.db.Database;
import persistence.db.MysqlConnector;

public class DataManager extends Observable{
	private static DataManager uniqueInstance;
	private List<Data> dataList=new ArrayList<Data>();
	private Database database = MysqlConnector.getUniqueInstance();
	
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
			this.database.add(data);
		} catch (Exception e) {
			Logger.getLogger(DataManager.class).error("Fehler beim speichern", e);
		}
		this.setChanged();
		this.notifyObservers();
		Logger.getLogger(DataManager.class).trace("Neues Data Objekt im DataManager aufgenommen");
	}
	
	public List<Data> getDataList(){
		try {
			this.dataList=this.database.load();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Logger.getLogger(DataManager.class).trace("DataList zurückgegeben");
		return this.dataList;
	}
	
	public void clear(){
		this.dataList=new ArrayList<Data>();
		try {
			this.database.clear();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setChanged();
		this.notifyObservers();
		Logger.getLogger(DataManager.class).trace("DataList gelöscht");
	}
	
	public void delete(Data data){
		try {
			this.database.delete(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void saveToFile(Properties props){
		Database temp = this.database;
		this.database = new CSV();
		this.database.setProperties(props);
		try {
			for(Data data:temp.load()){
				this.database.add(data);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.database = temp;
	}
}
