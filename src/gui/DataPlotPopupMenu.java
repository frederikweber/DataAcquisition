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
