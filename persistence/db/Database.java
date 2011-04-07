package persistence.db;

import domain.Data;

public interface Database {
	public void add(Data data);
	public ArrayList<Data> load();
}
