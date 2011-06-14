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
