package com.Main;

import com.Workers.Controller;

public class Main {

    public static void main(String[] args) {
        GUIMonitor test = new GUIMonitor();
        Controller controller = new Controller(test);
        test.Start(controller);

    }
}
