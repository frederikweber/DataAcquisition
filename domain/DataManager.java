package domain;

import java.util.ArrayList;
import java.util.Observable;

import org.apache.log4j.Logger;

public class DataManager extends Observable{
	private static DataManager uniqueInstance;
	private ArrayList<Data> dataList=new ArrayList<Data>();
	
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
		this.setChanged();
		this.notifyObservers();
		Logger.getLogger(DataManager.class).trace("Neues Data Objekt im DataManager aufgenommen");
	}
	public ArrayList<Data> getDataList(){
		Logger.getLogger(DataManager.class).trace("DataList zurückgegeben");
		return this.dataList;
	}
	public void clear(){
		this.dataList=new ArrayList<Data>();
		this.setChanged();
		this.notifyObservers();
		Logger.getLogger(DataManager.class).trace("DataList gelöscht");
	}
}
