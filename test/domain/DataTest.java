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

import static org.junit.Assert.*;

import org.junit.Test;

public class DataTest {

    @Test
    public void testData() {
        Data data = new Data(3.3, 3.3);
        assertNotNull(data);
        assertEquals(3.3, data.getX(), 0.001);
        assertEquals(3.3, data.getY(), 0.001);
        assertTrue(data.equals(new Data(3.3, 3.3)));
    }

}
