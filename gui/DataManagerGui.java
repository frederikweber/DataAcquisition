package gui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import domain.Data;
import domain.DataManager;

public class DataManagerGui implements Observer{

	private JFrame frame;
	private JList txtField;
	private DefaultListModel defaultListModel = new DefaultListModel();
	
	public DataManagerGui(){
		initialize();
		DataManager.getUniqueInstance().addObserver(this);
		Logger.getLogger(DataManagerGui.class).trace("Neues DataManagerGui Objekt erstellt");
	}
	private JFrame getFrame() {
		if(frame==null){
			frame=new JFrame("DataManager");
		}
		return frame;
	}
	private JList getTxtField() {
		if(txtField==null){
			txtField=new JList(this.defaultListModel);
		}
		return txtField;
	}
	
	private void initialize(){
		getFrame().setSize(300,300);
		getFrame().add(new JScrollPane(getTxtField()));
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setVisible(true);
	}

	public void update(Observable arg0, Object arg1) {
		this.defaultListModel.clear();
		for(Data data:DataManager.getUniqueInstance().getDataList()){
			this.defaultListModel.addElement(data.getX()+":"+data.getY());
		}	
		Logger.getLogger(DataManagerGui.class).trace("Daten wurden wegen Update aktualisiert");
	}
}
