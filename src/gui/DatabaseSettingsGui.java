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

import domain.DataManager;
import org.apache.log4j.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class DatabaseSettingsGui extends JFrame {

    public DatabaseSettingsGui() {
        final JTextField inputHost = new JTextField();
        final JTextField inputTable = new JTextField();
        final JTextField inputUser = new JTextField();
        final JTextField inputPassword = new JTextField();
        final JTextField inputDb = new JTextField();
        JButton button = new JButton("ok");
        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 2));
        this.setSize(300, 300);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Properties props = new Properties();
                props.setProperty("db.host", inputHost.getText());
                props.setProperty("db.db", inputDb.getText());
                props.setProperty("db.table", inputTable.getText());
                props.setProperty("db.user", inputUser.getText());
                props.setProperty("db.password", inputPassword.getText());
                Logger.getLogger(DatabaseSettingsGui.class).trace("Die Datenbankeinstellungen werden neu gesetzt");
                DataManager.getUniqueInstance().changeProperties(props);
                invisible();
            }
        });

        panel.add(new JLabel("Host"));
        panel.add(inputHost);
        panel.add(new JLabel("DB"));
        panel.add(inputDb);
        panel.add(new JLabel("Tabelle"));
        panel.add(inputTable);
        panel.add(new JLabel("User"));
        panel.add(inputUser);
        panel.add(new JLabel("Passwort"));
        panel.add(inputPassword);
        this.add(panel, BorderLayout.CENTER);
        this.add(button, BorderLayout.SOUTH);

        this.setVisible(true);
        Logger.getLogger(DatabaseSettingsGui.class).trace("Neues DatabaseSettingsGui Objekt");
    }

    /**
     * Macht das Fenster unsichtbar.
     */
    private void invisible() {
        this.setVisible(false);
    }
}
