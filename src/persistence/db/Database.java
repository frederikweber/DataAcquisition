package persistence.db;

import java.util.List;
import java.util.Properties;

import domain.Data;

public interface Database {
	public void setProperties(Properties props);
	public void add(Data data) throws Exception;
	public List<Data> load() throws Exception;
	public void delete(Data data) throws Exception;
	public void clear() throws Exception;
}
