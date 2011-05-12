package persistence.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import domain.Data;

public class CSV implements Database{
	private String name;

	@Override
	public void add(Data data) throws Exception {
		File file=new File(name);
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
		new File(name).delete();
	}

	@Override
	public void delete(Data data) throws Exception {
		File file=new File(name);
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
		File file=new File(name);
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
	public void setName(String name) {
		this.name=name;
	}

}
