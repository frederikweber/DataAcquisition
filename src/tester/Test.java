package tester;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import gui.DataGui;
import gui.DataPlotGui;

public class Test {
    public static void main(String[] args) {
        DOMConfigurator.configure("log.xml");
        new DataGui();
        Logger.getLogger(Test.class).info("Programm gestartet");
    }

}
