package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import domain.Data;
import domain.DataManager;

public class DataGui extends JFrame {
	private JFrame frame;
	private JLabel lblX;
	private JLabel lblY;
	private JTextField txtX;
	private JTextField txtY;
	private JButton btnOk;
	
	private JButton getBtnOk() {
		if (btnOk==null){
			btnOk=new JButton("ok");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DataManager.getUniqueInstance().addData(new Data(Double.parseDouble((getTxtX().getText())), Double.parseDouble(getTxtY().getText())));
					getTxtY().setText("");
					getTxtX().setText("");
				}
			});
		}
		return btnOk;
	}
	public DataGui(){
		initialize();
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
		getFrame().add(getLblX());
		getFrame().add(getTxtX());
		getFrame().add(getLblY());
		getFrame().add(getTxtY());
		getFrame().add(getBtnOk());
		getFrame().setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new DataManagerGui();
		new DataPlotGui();
		
	}
	
	
}
