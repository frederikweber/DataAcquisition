package tester;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import gui.DataGui;
import gui.DataPlotGui;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DOMConfigurator.configure("log.xml");
		DataGui dataGui=new DataGui();
		Logger.getLogger(Test.class).info("Programm gestartet");
	}

}
