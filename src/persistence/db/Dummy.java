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

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
