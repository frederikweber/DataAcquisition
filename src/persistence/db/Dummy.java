package persistence.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import domain.Data;

public class Dummy implements Database {
    private static Dummy uniqueInstance;
    private List<Data> dataList = new ArrayList<Data>();

    private Dummy() {

    }

    public static Dummy getUniqueInstance() {
        if (Dummy.uniqueInstance == null) {
            Dummy.uniqueInstance = new Dummy();
        }
        return Dummy.uniqueInstance;
    }

    @Override
    public void add(Data data) throws Exception {
        this.dataList.add(data);
    }

    @Override
    public void clear() throws Exception {
        this.dataList.clear();
    }

    @Override
    public void delete(Data data) throws Exception {
        this.dataList.remove(data);
    }

    @Override
    public List<Data> load() throws Exception {
        return this.dataList;
    }

    @Override
    public void setProperties(Properties props) {
        //No properties available
    }

}
