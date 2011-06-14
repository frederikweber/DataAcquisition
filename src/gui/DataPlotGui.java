package gui;

import org.apache.log4j.Logger;

import javax.swing.JFrame;

public class DataPlotGui extends JFrame {
    public DataPlotGui() {
        this.invalidate();
        this.add(new DataPlot());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setVisible(true);
        Logger.getLogger(DataPlotGui.class).trace("Neues DataPlotGui Objekt");
    }
}
