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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Properties;

import static org.junit.Assert.*;

public class CSVTest {

    @Before
    public void setUp() {
        Properties props = new Properties();
        props.put("file.name", "testCsvFile.csv");
        CSV.getUniqueInstance().setProperties(props);
    }

    @Test
    public void testGetUniqueInstance() {
        assertNotNull(CSV.getUniqueInstance());
    }

    @Test
    public void testAdd() throws Exception {
        Data data = new Data(9.9, 9.9);
        CSV.getUniqueInstance().add(data);
        assertEquals(CSV.getUniqueInstance().load().get(0), data);
    }

    @Test(expected = FileNotFoundException.class)
    public void testClear() throws Exception {
        Data data = new Data(9.9, 9.9);
        CSV.getUniqueInstance().add(data);
        CSV.getUniqueInstance().clear();
        assertFalse(CSV.getUniqueInstance().load().get(0).equals(data));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDelete() throws Exception {
        Data data = new Data(9.9, 9.9);
        CSV.getUniqueInstance().add(data);
        CSV.getUniqueInstance().delete(data);
        assertFalse(CSV.getUniqueInstance().load().get(0).equals(data));
    }

    @Test
    public void testLoad() throws Exception {
        Data data = new Data(9.9, 9.9);
        CSV.getUniqueInstance().add(data);
        assertEquals(CSV.getUniqueInstance().load().get(0), data);
    }

    @After
    public void tearDown() throws Exception {
        CSV.getUniqueInstance().clear();
    }

}
