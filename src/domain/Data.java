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

import org.apache.log4j.Logger;


public class Data {
    final private double x;
    final private double y;

    public Data(final double x, final double y) {
        this.x = x;
        this.y = y;
        Logger.getLogger(Data.class).trace("Neues Data Objekt " + this.toString());
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        Data data = (Data) o;

        if (Double.compare(data.x, this.x) != 0) {
            return false;
        }
        if (Double.compare(data.y, this.y) != 0) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Data{" +
                "x=" + this.x +
                ", y=" + this.y +
                '}';
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = this.x != +0.0d ? Double.doubleToLongBits(this.x) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = this.y != +0.0d ? Double.doubleToLongBits(this.y) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
