package persistence.db;

import java.util.List;
import java.util.Properties;

import domain.Data;

public interface Database {

    /**
     * Setzt neue Eigenschaften.
     *
     * @param props Die neuen Eigenschaften
     * @throws Exception
     */
    public void setProperties(Properties props) throws Exception;

    /**
     * Fügt der Datenbank einen neuen Datensatz hinzu.
     *
     * @param data Der neue Datensatz
     * @throws Exception
     */
    public void add(Data data) throws Exception;

    /**
     * Lädt alle Daten von der Datenbank.
     *
     * @return Eine Liste mit allen Daten in der Datenbank
     * @throws Exception
     */
    public List<Data> load() throws Exception;

    /**
     * Löscht einen einzelnen Datensatz aus der Datenbank.
     *
     * @param data Der zu löschende Datensatz
     * @throws Exception
     */
    public void delete(Data data) throws Exception;

    /**
     * Löscht alle Daten in der Datenbank.
     *
     * @throws Exception
     */
    public void clear() throws Exception;
}
