package gui;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

public class DataPlotGui extends JFrame {
    private DataPlot dataPlot = new DataPlot();

    public DataPlotGui() {
        this.invalidate();
        this.add(new DataPlot());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setVisible(true);
        Logger.getLogger(DataPlotGui.class).trace("Neues DataPlotGui Objekt erzeugt");
    }
}
