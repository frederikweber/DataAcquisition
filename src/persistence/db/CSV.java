package persistence.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Data;

public class CSV implements Database{
	private static CSV uniqueInstance;
	private Properties props;

	public static CSV getUniqueInstance(){
		if(CSV.uniqueInstance==null){
			CSV.uniqueInstance=new CSV();
		}
		return CSV.uniqueInstance;
	}
	
	@Override
	public void add(Data data) throws Exception {
		File file=new File(this.props.getProperty("file.name"));
		FileInputStream inputStream=new FileInputStream(file);
		byte[] input = null;
		inputStream.read(input);
		String newString = new String(input);
		newString += "\n"+data.getX()+","+data.getY();
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(newString.getBytes());
	}

	@Override
	public void clear() throws Exception {
		new File(this.props.getProperty("file.name")).delete();
	}

	@Override
	public void delete(Data data) throws Exception {
		File file=new File(this.props.getProperty("file.name"));
		FileInputStream inputStream=new FileInputStream(file);
		byte[] input = null;
		inputStream.read(input);
		String newString = new String(input);
		newString.replace("\n"+data.getX()+","+data.getY(), "");
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(newString.getBytes());		
	}

	@Override
	public List<Data> load() throws Exception {
		File file=new File(this.props.getProperty("file.name"));
		FileInputStream inputStream=new FileInputStream(file);
		byte[] input = null;
		inputStream.read(input);
		String newString = new String(input);
		String[] splitted=newString.split("\n");
		List<Data> returnData=new ArrayList<Data>();
		for(String data:splitted){
			String[] dataSet=data.split(",");
			returnData.add(new Data(new Double(dataSet[0]), new Double(dataSet[1])));
		}
		return returnData;
	}

	@Override
	public void setProperties(Properties props) {
		this.props = props;
	}

}
