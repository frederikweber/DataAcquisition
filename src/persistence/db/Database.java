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
