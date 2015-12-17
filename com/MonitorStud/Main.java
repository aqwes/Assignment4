package com.MonitorStud;

import com.Logic.Controller;

import javax.swing.text.Highlighter;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        GUIMonitor test = new GUIMonitor();
        Controller controller = new Controller(test);
        test.Start(controller);


    }
}
