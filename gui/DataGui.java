package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import domain.Data;
import domain.DataManager;
import domain.Generator;

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
	
	private JButton getBtnOk() {
		if (btnOk==null){
			btnOk=new JButton("ok");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Logger.getLogger(DataGui.class).trace("Neue Daten werden hinzugefügt");
					DataManager.getUniqueInstance().addData(new Data(Double.parseDouble(getTxtX().getText()), Double.parseDouble(getTxtY().getText())));
					getTxtY().setText("");
					getTxtX().setText("");
				}
			});
		}
		return btnOk;
	}
	private JButton getBtnSinus(){
		if(btnSinus==null){
			btnSinus=new JButton("Sinus");
			btnSinus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Logger.getLogger(DataGui.class).trace("Sinus Daten werden hinzugefügt");
					DataManager.getUniqueInstance().clear();
					for(Data data:Generator.getUniqueInstance().getSinusValues()){
						DataManager.getUniqueInstance().addData(data);
					}
				}
			});
		}
		return btnSinus;
	}
	private JButton getBtnRandom(){
		if(btnRandom==null){
			btnRandom=new JButton("Random");
			btnRandom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Logger.getLogger(DataGui.class).trace("Random Daten werden hinzugefügt");
					DataManager.getUniqueInstance().clear();
					for(Data data:Generator.getUniqueInstance().getRandomValues()){
						DataManager.getUniqueInstance().addData(data);
					}
				}
			});
		}
		return btnRandom;
	}
	private JButton getBtnClear(){
		if(btnClear==null){
			btnClear=new JButton("clear");
			btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Logger.getLogger(DataGui.class).trace("Daten werden entfernt");
					DataManager.getUniqueInstance().clear();
				}
			});
		}
		return btnClear;
	}
	public DataGui(){
		initialize();
		Logger.getLogger(DataGui.class).trace("Neues DataGui Objekt erzeugt");
	}
	private JFrame getFrame() {
		if(frame==null){
			frame=new JFrame("DataGui");
		}
		return frame;
	}
	private JLabel getLblX() {
		if(lblX==null){
			lblX=new JLabel("X:");
		}
		return lblX;
	}
	private JLabel getLblY() {
		if(lblY==null){
			lblY=new JLabel("Y:");
		}
		return lblY;
	}
	private JTextField getTxtX() {
		if(txtX==null){
			txtX=new JTextField();
		}
		return txtX;
	}
	private JTextField getTxtY() {
		if(txtY==null){
			txtY=new JTextField();
		}
		return txtY;
	}
	private void initialize(){
		getFrame().setLayout(new GridLayout(0,2));
		getFrame().setSize(300,150);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().add(getLblX());
		getFrame().add(getTxtX());
		getFrame().add(getLblY());
		getFrame().add(getTxtY());
		getFrame().add(getBtnOk());
		getFrame().add(getBtnClear());
		getFrame().add(getBtnSinus());
		getFrame().add(getBtnRandom());
		getFrame().setVisible(true);
		new DataManagerGui();
		new DataPlotGui();
		
	}
	
	
}
