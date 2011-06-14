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
