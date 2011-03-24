package gui;

import java.awt.Graphics;

import javax.swing.JComponent;

import domain.Data;
import domain.DataManager;

public class DataPlot extends JComponent{
	protected void paintComponent(Graphics g){
		int w=this.getWidth();
		int h=this.getHeight();
		g.drawLine(w/2,0,w/2,h);
		g.drawLine(0,h/2,w,h/2);

		double a=-Math.PI;
		double b=Math.PI;
		
		int gX0=w/2;
		int gY0=h/2;
		
		double xScale=w/2/b;
		double yScale=-h/2;
		
		for(Data data:DataManager.getUniqueInstance().getDataList()){	
			int gx=(int)(data.getX()*xScale);
			int gy=(int)(data.getY()*yScale);
			System.out.println(gx+","+gy);
			g.drawLine(gx, gy, gx, gy);
		}	
	}
}
