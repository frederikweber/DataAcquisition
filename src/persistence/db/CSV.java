/*
 * DataAcquisition
 * Copyright (C) 2011 Frederik Weber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
