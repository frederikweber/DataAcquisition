package gui;

import domain.Data;
import domain.DataManager;
import org.apache.log4j.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.util.Observable;
import java.util.Observer;

public class DataManagerGui implements Observer {

    private JFrame frame;
    private JList txtField;
    private DefaultListModel defaultListModel = new DefaultListModel();

    public DataManagerGui() {
        this.initialize();
        DataManager.getUniqueInstance().addObserver(this);
        Logger.getLogger(DataManagerGui.class).trace("Neues DataManagerGui Objekt");
    }

    private JFrame getFrame() {
        if (this.frame == null) {
            this.frame = new JFrame("DataManager");
        }
        return this.frame;
    }

    private JList getTxtField() {
        if (this.txtField == null) {
            this.txtField = new JList(this.defaultListModel);
            this.txtField.setComponentPopupMenu(new DataManagerPopupMenu(this.txtField));
        }
        return this.txtField;
    }

    private void initialize() {
        this.getFrame().setSize(300, 300);
        this.getFrame().add(new JScrollPane(this.getTxtField()));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getFrame().setVisible(true);
        this.update(null, null);
    }

    public void update(Observable arg0, Object arg1) {
        this.defaultListModel.clear();
        for (Data data : DataManager.getUniqueInstance().getDataList()) {
            this.defaultListModel.addElement(data.getX() + ":" + data.getY());
        }
        Logger.getLogger(DataManagerGui.class).trace("Daten wurden wegen Update aktualisiert");
    }

}
