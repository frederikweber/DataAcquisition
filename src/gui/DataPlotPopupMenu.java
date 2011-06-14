package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataPlotPopupMenu extends JPopupMenu {
    public DataPlotPopupMenu(final DataPlot dataPlot) {
        JMenuItem green = new JMenuItem("Grün");
        JMenuItem red = new JMenuItem("Rot");
        JMenuItem blue = new JMenuItem("Blau");

        green.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataPlot.setColor(Color.green);
            }
        });
        red.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataPlot.setColor(Color.red);
            }
        });
        blue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataPlot.setColor(Color.blue);
            }
        });

        this.add(green);
        this.add(red);
        this.add(blue);
    }
}
