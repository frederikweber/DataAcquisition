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

package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Properties;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.apache.log4j.Logger;

import persistence.db.CSV;
import persistence.db.Database;
import persistence.db.Dummy;
import persistence.db.MysqlConnector;

public class DataManager extends Observable {
    private static DataManager uniqueInstance;
    private List<Data> dataList = new ArrayList<Data>();
    private Database database;

    public static DataManager getUniqueInstance() {
        if (DataManager.uniqueInstance == null) {
            DataManager.uniqueInstance = new DataManager();
        }
        return DataManager.uniqueInstance;
    }

    private DataManager() {
        try {
            this.database = MysqlConnector.getUniqueInstance();
        } catch (Exception e) {
            this.database = Dummy.getUniqueInstance();
            Logger.getLogger(DataManager.class).error("Die Ersatzdatenbank wird wegen Fehler gebraucht");
        }
        Logger.getLogger(DataManager.class).trace("Neues DataManager Objekt");
    }

    /**
     * Fügt neue Daten hinzu.
     *
     * @param data Das Data Objekt
     */
    public void addData(Data data) {
        try {
            this.database.add(data);
        } catch (Exception e) {
            Logger.getLogger(DataManager.class).error("Fehler beim speichern", e);
        }
        this.setChanged();
        this.notifyObservers();
        Logger.getLogger(DataManager.class).trace("Neues Data Objekt im DataManager aufgenommen " + data.toString());
    }

    /**
     * Liefert die DataList zurück.
     *
     * @return Eine Liste mit allen Daten.
     */
    public List<Data> getDataList() {
        try {
            this.dataList = this.database.load();
        } catch (Exception e) {
            Logger.getLogger(DataManager.class).error("Fehler beim laden", e);
        }
        Logger.getLogger(DataManager.class).trace("DataList zurückgegeben");
        Logger.getLogger(DataManager.class).trace("Anzahl Einträge in DataList: " + this.dataList.size());
        return this.dataList;
    }

    /**
     * Löscht alle Daten in der Liste.
     */
    public void clear() {
        try {
            this.database.clear();
        } catch (Exception e) {
            Logger.getLogger(DataManager.class).error("Fehler beim clearen", e);
        }
        this.setChanged();
        this.notifyObservers();
        Logger.getLogger(DataManager.class).trace("DataList gelöscht");
    }

    /**
     * Löscht ein spezielles Datenobjekt.
     *
     * @param data Das zu löschende Datenobjekt
     */
    public void delete(Data data) {
        try {
            this.database.delete(data);
        } catch (Exception e) {
            Logger.getLogger(DataManager.class).error("Fehler beim löschen", e);
        }
        this.setChanged();
        this.notifyObservers();
        Logger.getLogger(DataManager.class).trace("Ein Datensatz gelöscht");
    }

    /**
     * Speichert die Daten in eine Datei.
     *
     * @param props Die Eigenschaften der Datei
     */
    public void saveToFile(Properties props) {
        Database temp = this.database;
        this.database = CSV.getUniqueInstance();
        ((CSV) this.database).setProperties(props);
        try {
            this.database.clear();
            for (Data data : temp.load()) {
                this.database.add(data);
            }
        } catch (Exception e) {
            Logger.getLogger(DataManager.class).error("Fehler beim speichern in eine Datei", e);
        } finally {
            this.database = temp;
        }
        Logger.getLogger(DataManager.class).trace("In eine Datei gespeichert");
    }

    /**
     * Lädt Daten aus einer Datei.
     *
     * @param props Die Eigenschaften der Datei
     */
    public void loadFromFile(Properties props) {
        Database temp = this.database;
        this.database = CSV.getUniqueInstance();
        ((CSV) this.database).setProperties(props);
        try {
            temp.clear();
            for (Data data : this.database.load()) {
                temp.add(data);
            }
        } catch (Exception e) {
            Logger.getLogger(DataManager.class).error("Fehler beim Laden von einer Datei", e);
        } finally {
            this.database = temp;
        }
        Logger.getLogger(DataManager.class).trace("Von einer Datei geladen");
        this.setChanged();
        this.notifyObservers();
    }

    /**
     * Ändert die Eigenschaften der Datenbank welche vom DataManager gebraucht wird.
     *
     * @param props Die neuen Eigenschaften
     */
    public void changeProperties(Properties props) {
        try {
            this.database.setProperties(props);
        } catch (Exception e) {
            this.database = Dummy.getUniqueInstance();
            Logger.getLogger(DataManager.class).error("Die Ersatzdatenbank wird wegen Fehler gebraucht", e);
        }
    }
}
