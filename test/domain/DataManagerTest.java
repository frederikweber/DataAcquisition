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

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

    @Before
    public void setUp() {
        DataManager.getUniqueInstance().clear();
    }

    @Test
    public void testGetUniqueInstance() {
        assertNotNull(DataManager.getUniqueInstance());
    }

    @Test
    public void testAddData() {
        DataManager.getUniqueInstance().addData(new Data(3.3, 3.3));
        assertEquals(DataManager.getUniqueInstance().getDataList().size(), 1);
        assertEquals(3.3, DataManager.getUniqueInstance().getDataList().get(0).getX(), 0.001);
        assertEquals(3.3, DataManager.getUniqueInstance().getDataList().get(0).getY(), 0.001);
    }

    @Test
    public void testGetDataList() {
        DataManager.getUniqueInstance().addData(new Data(3.3, 3.3));
        assertEquals(3.3, DataManager.getUniqueInstance().getDataList().get(0).getX(), 0.001);
        assertEquals(3.3, DataManager.getUniqueInstance().getDataList().get(0).getY(), 0.001);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testClear() {
        DataManager.getUniqueInstance().clear();
        assertNull(DataManager.getUniqueInstance().getDataList().get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDelete() {
        DataManager.getUniqueInstance().addData(new Data(3.3, 3.3));
        assertEquals(3.3, DataManager.getUniqueInstance().getDataList().get(0).getX(), 0.001);
        assertEquals(3.3, DataManager.getUniqueInstance().getDataList().get(0).getY(), 0.001);
        DataManager.getUniqueInstance().delete(new Data(3.3, 3.3));
        assertFalse(DataManager.getUniqueInstance().getDataList().get(0).equals(new Data(3.3, 3.3)));
    }

}
