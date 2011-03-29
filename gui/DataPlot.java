package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

import domain.Data;
import domain.DataManager;

public class DataPlot extends JComponent implements Observer{
	public DataPlot(){
		DataManager.getUniqueInstance().addObserver(this);
	}
	protected void paintComponent(Graphics g){
		int w=this.getWidth();
		int h=this.getHeight();
		g.drawLine(w/2,0,w/2,h);
		g.drawLine(0,h/2,w,h/2);

		g.setColor(Color.RED);

		double a=-Math.PI;
		double b=Math.PI;
		
		int gX0=w/2;
		int gY0=h/2;
		
		double biggestX=0;
		double biggestY=0;
		for(Data data:DataManager.getUniqueInstance().getDataList()){
			if(Math.abs(data.getX())>biggestX){
				biggestX=Math.abs(data.getX());
			}
			if(Math.abs(data.getY())>biggestY){
				biggestY=Math.abs(data.getY());
			}
		}
		double biggest=Math.max(biggestX,biggestY);
		double xScale=w/biggest/2;
		double yScale=-h/biggest/2;
		
		for(Data data:DataManager.getUniqueInstance().getDataList()){	
			int gx=(int)(data.getX()*xScale+gX0);
			int gy=(int)(data.getY()*yScale+gY0);
			g.drawLine(gx, gy, gx, gy);
		}	
	}
	public void update(Observable arg0, Object arg1) {
		this.repaint();
	}
}
