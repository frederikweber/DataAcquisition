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
import domain.Generator;
import org.apache.log4j.Logger;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

public class DataGui extends JFrame {
    private JFrame frame;
    private JLabel lblX;
    private JLabel lblY;
    private JTextField txtX;
    private JTextField txtY;
    private JButton btnOk;
    private JButton btnSinus;
    private JButton btnRandom;
    private JButton btnClear;
    private JMenuBar topMenuBar;
    private JMenuItem mniSaveFile;
    private JMenuItem mniLoadFile;
    private JMenuItem mniDbSettings;
    private JMenuItem mniInfo;
    private JMenu menu;

    private JMenuBar getTopMenuBar() {
        if (this.topMenuBar == null) {
            this.topMenuBar = new JMenuBar();
            this.topMenuBar.add(this.getMenu());
        }
        return this.topMenuBar;
    }

    private JMenuItem getMniSaveFile() {
        if (this.mniSaveFile == null) {
            this.mniSaveFile = new JMenuItem("Speichere Datei");
            this.mniSaveFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.showSaveDialog(null);
                    Properties properties = new Properties();
                    properties.setProperty("file.name", fileChooser.getSelectedFile().getAbsolutePath());
                    DataManager.getUniqueInstance().saveToFile(properties);
                }
            });
        }
        return this.mniSaveFile;
    }

    private JMenuItem getMniLoadFile() {
        if (this.mniLoadFile == null) {
            this.mniLoadFile = new JMenuItem("Lade Datei");
            this.mniLoadFile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.showOpenDialog(null);
                    Properties properties = new Properties();
                    properties.setProperty("file.name", fileChooser.getSelectedFile().getAbsolutePath());
                    DataManager.getUniqueInstance().loadFromFile(properties);
                }
            });
        }
        return this.mniLoadFile;
    }

    private JMenuItem getMniDbSettings() {
        if (this.mniDbSettings == null) {
            this.mniDbSettings = new JMenuItem("Datenbankeinstellungen");
            this.mniDbSettings.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new DatabaseSettingsGui();
                }
            });
        }
        return this.mniDbSettings;
    }

    private JMenuItem getMniInfo() {
        if (this.mniInfo == null) {
            this.mniInfo = new JMenuItem("Info");
            this.mniInfo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new InfoGui();
                }
            });
        }
        return this.mniInfo;
    }

    private JMenu getMenu() {
        if (this.menu == null) {
            this.menu = new JMenu("Datei");
            this.menu.add(this.getMniSaveFile());
            this.menu.add(this.getMniLoadFile());
            this.menu.add(this.getMniDbSettings());
            this.menu.add(this.getMniInfo());
        }
        return this.menu;
    }

    private JButton getBtnOk() {
        if (this.btnOk == null) {
            this.btnOk = new JButton("ok");
            this.btnOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Logger.getLogger(DataGui.class).trace("Neue Daten werden hinzugefügt");
                    try {
                        DataManager.getUniqueInstance().addData(new Data(Double.parseDouble(getTxtX().getText()), Double.parseDouble(getTxtY().getText())));
                    } catch (NumberFormatException e1) {
                        Logger.getLogger(DataGui.class).info("Es wurden ungültige Werte eingegeben", e1);
                        JOptionPane.showMessageDialog(null, "Bitte geben Sie gültige Werte ein.", "Ungültige Werte eingegeben", JOptionPane.ERROR_MESSAGE);
                    }
                    getTxtY().setText("");
                    getTxtX().setText("");
                }
            });
        }
        return this.btnOk;
    }

    private JButton getBtnSinus() {
        if (this.btnSinus == null) {
            this.btnSinus = new JButton("Sinus");
            this.btnSinus.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Logger.getLogger(DataGui.class).trace("Sinus Daten werden hinzugefügt");
                    DataManager.getUniqueInstance().clear();
                    for (Data data : Generator.getSinusValues()) {
                        DataManager.getUniqueInstance().addData(data);
                    }
                }
            });
        }
        return this.btnSinus;
    }

    private JButton getBtnRandom() {
        if (this.btnRandom == null) {
            this.btnRandom = new JButton("Random");
            this.btnRandom.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Logger.getLogger(DataGui.class).trace("Random Daten werden hinzugefügt");
                    DataManager.getUniqueInstance().clear();
                    for (Data data : Generator.getRandomValues()) {
                        DataManager.getUniqueInstance().addData(data);
                    }
                }
            });
        }
        return this.btnRandom;
    }

    private JButton getBtnClear() {
        if (this.btnClear == null) {
            this.btnClear = new JButton("clear");
            this.btnClear.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Logger.getLogger(DataGui.class).trace("Daten werden entfernt");
                    DataManager.getUniqueInstance().clear();
                }
            });
        }
        return this.btnClear;
    }

    public DataGui() {
        this.initialize();
        Logger.getLogger(DataGui.class).trace("Neues DataGui Objekt");
    }

    private JFrame getFrame() {
        if (this.frame == null) {
            this.frame = new JFrame("DataGui");
            this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        return this.frame;
    }

    private JLabel getLblX() {
        if (this.lblX == null) {
            this.lblX = new JLabel("X:");
        }
        return this.lblX;
    }

    private JLabel getLblY() {
        if (this.lblY == null) {
            this.lblY = new JLabel("Y:");
        }
        return this.lblY;
    }

    private JTextField getTxtX() {
        if (this.txtX == null) {
            this.txtX = new JTextField();
        }
        return this.txtX;
    }

    private JTextField getTxtY() {
        if (this.txtY == null) {
            this.txtY = new JTextField();
        }
        return this.txtY;
    }

    private void initialize() {
        JPanel pnl = new JPanel();
        pnl.setLayout(new GridLayout(0, 2));
        this.getFrame().setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getFrame().add(this.getTopMenuBar(), BorderLayout.NORTH);
        this.getFrame().add(pnl, BorderLayout.CENTER);
        pnl.add(this.getLblX());
        pnl.add(this.getTxtX());
        pnl.add(this.getLblY());
        pnl.add(this.getTxtY());
        pnl.add(this.getBtnOk());
        pnl.add(this.getBtnClear());
        pnl.add(this.getBtnSinus());
        pnl.add(this.getBtnRandom());
        this.getFrame().setVisible(true);
        new DataPlotGui();
        new DataManagerGui();
    }


}
