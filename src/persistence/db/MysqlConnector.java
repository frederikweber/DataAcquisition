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


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import domain.Data;

public class MysqlConnector implements Database {
    private static MysqlConnector uniqueInstance;
    private Connection connection;
    private Properties props;

    private MysqlConnector() throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Properties properties = new Properties();
            properties.setProperty("db.host", "localhost");
            properties.setProperty("db.db", "datadb");
            properties.setProperty("db.table", "t_data");
            properties.setProperty("db.user", "root");
            properties.setProperty("db.password", "");
            this.setProperties(properties);
        } catch (ClassNotFoundException e) {
            Logger.getLogger(MysqlConnector.class).error("Die Klasse kann nicht gefunden werden", e);
        } catch (CommunicationsException e) {
            throw e;
        } catch (MySQLSyntaxErrorException e) {
            throw e;
        } catch (Exception e) {
            Logger.getLogger(MysqlConnector.class).error("Fehler beim erstellen des Objekt", e);
        }
    }

    public static MysqlConnector getUniqueInstance() throws Exception {
        if (MysqlConnector.uniqueInstance == null) {
            MysqlConnector.uniqueInstance = new MysqlConnector();
        }
        return MysqlConnector.uniqueInstance;
    }

    @Override
    public void add(Data data) throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.execute("INSERT INTO " + this.props.getProperty("db.table") + "(x,y) VALUES ('" + data.getX() + "','" + data.getY() + "');");
    }

    @Override
    public List<Data> load() throws SQLException {
        Statement statement = this.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + this.props.getProperty("db.table") + ";");
        List<Data> arrayList = new ArrayList<Data>();
        while (resultSet.next()) {
            arrayList.add(new Data((double) resultSet.getFloat(2), (double) resultSet.getFloat(3)));
        }
        return arrayList;
    }

    @Override
    public void delete(Data data) throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.execute("DELETE FROM " + this.props.getProperty("db.table") + " WHERE x=" + data.getX() + " AND y=" + data.getY() + ";");
    }

    @Override
    public void clear() throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.execute("DELETE FROM " + this.props.getProperty("db.table") + ";");
    }

    /**
     * Setzt neue Eigenschaften.
     *
     * @param props db.host für Datenbankhost, db.db für Datenbank, db.user für User, db.password für Passwort, db.table für Tabelle
     * @throws Exception
     */
    @Override
    public void setProperties(Properties props) throws Exception {
        this.props = props;
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://"
                    + this.props.getProperty("db.host")
                    + "/"
                    + this.props.getProperty("db.db")
                    + "?user="
                    + this.props.getProperty("db.user")
                    + "&password="
                    + this.props.getProperty("db.password"));
            this.connection.createStatement().executeQuery("SELECT * FROM "
                    + this.props.getProperty("db.table")
                    + ";");
        } catch (CommunicationsException e) {
            Logger.getLogger(MysqlConnector.class).error("Keine Verbindung zu der Datenbank möglich", e);
            throw e;
        } catch (MySQLSyntaxErrorException e) {
            Logger.getLogger(MysqlConnector.class).error("Testquery erzeugte Fehler", e);
            throw e;
        } catch (SQLException e) {
            Logger.getLogger(MysqlConnector.class).error("Ein SQL Fehler ist aufgetreten", e);
        }
    }
}
