package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Properties;

import org.apache.log4j.Logger;

import persistence.db.CSV;
import persistence.db.Database;
import persistence.db.Dummy;
import persistence.db.MysqlConnector;

public class DataManager extends Observable{
	private static DataManager uniqueInstance;
	private List<Data> dataList = new ArrayList<Data>();
	private Database database;
	
	public static DataManager getUniqueInstance(){
		if(DataManager.uniqueInstance==null){
			DataManager.uniqueInstance=new DataManager();
		}
		return DataManager.uniqueInstance;
	}
	
	private DataManager(){
		try {
			this.database = MysqlConnector.getUniqueInstance();
		} catch (Exception e) {
			this.database = Dummy.getUniqueInstance();
			Logger.getLogger(DataManager.class).error("Die Ersatzdatenbank wird wegen Fehler gebraucht");
		}
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
			Logger.getLogger(DataManager.class).error("Fehler beim laden", e);
		}
		Logger.getLogger(DataManager.class).trace("DataList zurückgegeben");
		return this.dataList;
	}
	
	public void clear(){
		this.dataList=new ArrayList<Data>();
		try {
			this.database.clear();
		} catch (Exception e) {
			Logger.getLogger(DataManager.class).error("Fehler beim clearen", e);
		}
		this.setChanged();
		this.notifyObservers();
		Logger.getLogger(DataManager.class).trace("DataList gelöscht");
	}
	
	public void delete(Data data){
		try {
			this.database.delete(data);
		} catch (Exception e) {
			Logger.getLogger(DataManager.class).error("Fehler beim löschen", e);
		}
		Logger.getLogger(DataManager.class).trace("Ein Datensatz gelöscht");
	}
	
	
	public void saveToFile(Properties props){
		Database temp = this.database;
		this.database = CSV.getUniqueInstance();
		((CSV)this.database).setProperties(props);
		try {
			this.database.clear();
			for(Data data:temp.load()){
				this.database.add(data);
			}
		} catch (Exception e) {
			Logger.getLogger(DataManager.class).error("Fehler beim speichern in eine Datei", e);
		} finally {
			this.database = temp;
		}
		Logger.getLogger(DataManager.class).trace("In eine Datei gespeichert");
	}
	
	public void loadFromFile(Properties props){
		Database temp = this.database;
		this.database = CSV.getUniqueInstance();
		((CSV)this.database).setProperties(props);
		try {
			temp.clear();
			for(Data data:this.database.load()){
				temp.add(data);
			}
		} catch (Exception e) {
			Logger.getLogger(DataManager.class).error("Fehler beim Laden von einer Datei", e);
		} finally {
			this.database = temp;
		}
		Logger.getLogger(DataManager.class).trace("Von einer Datei geladen");
		this.setChanged();
		this.notifyObservers();
	}
	
	public void changeProperties(Properties props){
		try {
			this.database.setProperties(props);
		} catch (Exception e) {
			this.database = Dummy.getUniqueInstance();
			Logger.getLogger(DataManager.class).error("Die Ersatzdatenbank wird wegen Fehler gebraucht", e);
		}
	}
}
