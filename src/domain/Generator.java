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

import org.apache.log4j.Logger;

public class Generator {

    private Generator() {

    }

    /**
     * Gibt eine Liste mit Sinuswerten zurück.
     *
     * @return Eine Liste mit Sinuswerten
     */
    public static ArrayList<Data> getSinusValues() {
        ArrayList<Data> dataList = new ArrayList<Data>();
        for (double i = 0; i < 2 * Math.PI; i += 0.1) {
            dataList.add(new Data(i, Math.sin(i)));
        }
        Logger.getLogger(Generator.class).trace("Sinus Daten zurückgegeben");
        return dataList;
    }

    /**
     * Gibt eine Liste mit Zufallswerten zurück.
     *
     * @return Eine Liste mit Zufallswerten
     */
    public static ArrayList<Data> getRandomValues() {
        ArrayList<Data> dataList = new ArrayList<Data>();
        for (int i = 0; i < 111; i++) {
            double negativ1 = Math.random();
            double negativ2 = Math.random();
            double zufall1 = Math.random();
            double zufall2 = Math.random();
            if (negativ1 < 0.5) {
                zufall1 -= 1;
            }
            if (negativ2 < 0.5) {
                zufall2 -= 1;
            }
            zufall1 *= 10;
            zufall2 *= 10;
            dataList.add(new Data(zufall1, zufall2));
        }
        Logger.getLogger(Generator.class).trace("Random Daten zurückgegeben");
        return dataList;
    }
}
