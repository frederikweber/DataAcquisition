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
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;


public class DummyTest {

    @Before
    public void setUp() throws Exception {
        Dummy.getUniqueInstance().clear();
    }

    @Test
    public void testGetUniqueInstance() throws Exception {
        assertNotNull(Dummy.getUniqueInstance());
    }

    @Test
    public void testAdd() throws Exception {
        Data data = new Data(12.0, 12.0);
        Dummy.getUniqueInstance().add(data);
        assertEquals(Dummy.getUniqueInstance().load().size(), 1);
        assertEquals(Dummy.getUniqueInstance().load().get(0), data);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testClear() throws Exception {
        Data data = new Data(12.0, 12.0);
        Dummy.getUniqueInstance().clear();
        assertFalse(Dummy.getUniqueInstance().load().get(0).equals(data));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDelete() throws Exception {
        Data data = new Data(12.0, 12.0);
        Dummy.getUniqueInstance().delete(data);
        assertFalse(Dummy.getUniqueInstance().load().get(0).equals(data));
    }

    @Test
    public void testLoad() throws Exception {
        Data data = new Data(12.0, 12.0);
        Dummy.getUniqueInstance().add(data);
        assertEquals(Dummy.getUniqueInstance().load().get(0), data);
    }
}
