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

import domain.Data;
import domain.DataManager;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataManagerPopupMenu extends JPopupMenu {
    public DataManagerPopupMenu(final JList list) {
        JMenuItem delete = new JMenuItem("Löschen");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Object actual : list.getSelectedValues()) {
                    String complete = (String) actual;
                    String[] splitted = complete.split(":");
                    Data data = new Data(Double.parseDouble(splitted[0]), Double.parseDouble(splitted[1]));
                    DataManager.getUniqueInstance().delete(data);
                }
            }
        });
        this.add(delete);
    }
}
