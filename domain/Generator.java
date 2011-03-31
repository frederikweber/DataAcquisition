package domain;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class Generator {
	private static Generator uniqueInstance;
	private Generator(){
		Logger.getLogger(Generator.class).trace("Neues Generator Objekt");
	}
	public static Generator getUniqueInstance(){
		if(Generator.uniqueInstance==null){
			Generator.uniqueInstance=new Generator();
		}
		return Generator.uniqueInstance;
	}
	public ArrayList<Data> getSinusValues(){
		ArrayList<Data> dataList=new ArrayList<Data>();
		for(double i=0;i<2*Math.PI;i+=0.1){
			dataList.add(new Data(i,Math.sin(i)));
		}
		Logger.getLogger(Generator.class).trace("Sinus Daten zurückgegeben");
		return dataList;
	}
	public ArrayList<Data> getRandomValues(){
		ArrayList<Data> dataList=new ArrayList<Data>();
		for(int i=0;i<111;i++){
			double negativ1=Math.random();
			double negativ2=Math.random();
			double zufall1=Math.random();
			double zufall2=Math.random();
			if(negativ1<0.5){
				zufall1-=1;
			}
			if(negativ2<0.5){
				zufall2-=1;
			}
			zufall1*=10;
			zufall2*=10;
			dataList.add(new Data(zufall1,zufall2));		
		}
		Logger.getLogger(Generator.class).trace("Random Daten zurückgegeben");
		return dataList;
	}
}
