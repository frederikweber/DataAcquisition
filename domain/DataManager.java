package domain;

import java.util.ArrayList;
import java.util.Observable;

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
		
	}
	public void addData(Data data){
		this.dataList.add(data);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Data> getDataList(){
		return this.dataList;
	}
	public void clear(){
		this.dataList=new ArrayList<Data>();
		this.setChanged();
		this.notifyObservers();
	}
}
