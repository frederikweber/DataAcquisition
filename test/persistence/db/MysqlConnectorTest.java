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

import static org.junit.Assert.*;

import domain.Data;
import org.junit.Before;
import org.junit.Test;

public class MysqlConnectorTest {

    @Before
    public void setUp() throws Exception {
        MysqlConnector.getUniqueInstance().clear();
    }

    @Test
    public void testGetUniqueInstance() throws Exception {
        assertNotNull(MysqlConnector.getUniqueInstance());
    }

    @Test
    public void testAdd() throws Exception {
        Data data = new Data(3.3, 3.3);
        MysqlConnector.getUniqueInstance().add(data);
        assertEquals(data.getX(), MysqlConnector.getUniqueInstance().load().get(0).getX(), 0.001);
        assertEquals(data.getY(), MysqlConnector.getUniqueInstance().load().get(0).getY(), 0.001);
    }

    @Test
    public void testLoad() throws Exception {
        Data data = new Data(3.3, 3.3);
        MysqlConnector.getUniqueInstance().add(data);
        assertEquals(data.getX(), MysqlConnector.getUniqueInstance().load().get(0).getX(), 0.001);
        assertEquals(data.getY(), MysqlConnector.getUniqueInstance().load().get(0).getY(), 0.001);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDelete() throws Exception {
        Data data = new Data(3.3, 3.3);
        MysqlConnector.getUniqueInstance().add(data);
        MysqlConnector.getUniqueInstance().delete(data);
        assertFalse(data.equals(MysqlConnector.getUniqueInstance().load().get(0)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testClear() throws Exception {
        Data data = new Data(3.3, 3.3);
        MysqlConnector.getUniqueInstance().add(data);
        MysqlConnector.getUniqueInstance().clear();
        assertFalse(data.equals(MysqlConnector.getUniqueInstance().load().get(0)));
        assertEquals(0, MysqlConnector.getUniqueInstance().load().size());
    }

}
