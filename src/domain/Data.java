package domain;

import org.apache.log4j.Logger;

public class Data {
    private double x;
    private double y;

    public Data(double x, double y) {
        this.x = x;
        this.y = y;
        Logger.getLogger(Data.class).trace("Neues Data Objekt X:" + x + " Y:" + y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
