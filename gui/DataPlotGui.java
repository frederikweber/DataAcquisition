package gui;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

public class DataPlotGui extends JFrame{
	private DataPlot dataPlot=new DataPlot();
	public DataPlotGui(){	
		this.invalidate();
		this.add(new DataPlot());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300,300);
		this.setVisible(true);
	}
}
