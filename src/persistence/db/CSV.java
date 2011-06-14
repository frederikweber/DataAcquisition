package persistence.db;

import domain.Data;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CSV implements Database {
    private static CSV uniqueInstance;
    private Properties props;

    private CSV() {
        Logger.getLogger(CSV.class).trace("Neues CSV Objekt wurde erzeugt");
    }

    public static CSV getUniqueInstance() {
        if (CSV.uniqueInstance == null) {
            CSV.uniqueInstance = new CSV();
        }
        return CSV.uniqueInstance;
    }

    @Override
    public void add(Data data) throws Exception {
        File file = new File(this.props.getProperty("file.name"));
        file.createNewFile();
        FileInputStream inputStream = new FileInputStream(file);
        byte[] input = new byte[(int) file.length()];
        inputStream.read(input);
        inputStream.close();
        String newString = new String(input);
        newString += ";" + data.getX() + "," + data.getY();
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(newString.getBytes());
        outputStream.close();
        Logger.getLogger(CSV.class).trace("Datensatz der Datei hinzugefügt");
    }

    @Override
    public void clear() throws Exception {
        new File(this.props.getProperty("file.name")).delete();
        Logger.getLogger(CSV.class).trace("Datei wurde gelöscht");
    }

    @Override
    public void delete(Data data) throws Exception {
        File file = new File(this.props.getProperty("file.name"));
        FileInputStream inputStream = new FileInputStream(file);
        byte[] input = new byte[(int) file.length()];
        inputStream.read(input);
        inputStream.close();
        String newString = new String(input);
        newString = newString.replace(";" + data.getX() + "," + data.getY(), "");
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(newString.getBytes());
        outputStream.close();
        Logger.getLogger(CSV.class).trace("Ein Datensatz gelöscht");
    }

    @Override
    public List<Data> load() throws Exception {
        File file = new File(this.props.getProperty("file.name"));
        FileInputStream inputStream = new FileInputStream(file);
        byte[] input = new byte[(int) file.length()];
        inputStream.read(input);
        inputStream.close();
        String newString = new String(input);
        String[] splitted = newString.split(";");
        List<Data> returnData = new ArrayList<Data>();
        for (String data : splitted) {
            if (!data.equals("")) {
                String[] dataSet = data.split(",");
                returnData.add(new Data(new Double(dataSet[0]), new Double(dataSet[1])));
            }
        }
        Logger.getLogger(CSV.class).trace("Daten von Datei geladen");
        return returnData;
    }

    /**
     * Setzt neue Eigenschaften.
     *
     * @param props file.name für Dateiname
     */
    @Override
    public void setProperties(Properties props) {
        this.props = props;
        Logger.getLogger(CSV.class).trace("Neue Eigenschaften definiert");
    }

}
