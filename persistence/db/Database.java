package persistence.db;

import java.util.List;

import domain.Data;

public interface Database {
	public void setName(String name);
	public void add(Data data) throws Exception;
	public List<Data> load() throws Exception;
	public void delete(Data data) throws Exception;
	public void clear() throws Exception;
}
