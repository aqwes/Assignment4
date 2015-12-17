package com.Logic;

import com.BoundedBuffer.BoundedBuffer;
import com.Monitor.Modifier;
import com.MonitorStud.GUIMonitor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Dennis on 2015-12-07.
 */
public class Controller {
    private BoundedBuffer buffer;
    private Writer writer;
    private Reader reader;
    private Modifier modifier;
    private GUIMonitor gui;
    private List<String> temps;
    private boolean notify;
    private String find;
    private String strReplace;


    public Controller(GUIMonitor gui) {
        this.gui = gui;


    }

    public void readFile(String txtFile ) {
        temps = new LinkedList<>();
        String token1 = "";
        try {
            Scanner inFile1 = new Scanner(new File(txtFile)).useDelimiter(" ,\\s*");

            while (inFile1.hasNext()) {
                token1 = inFile1.next();
                temps.add(token1);
            }
            inFile1.close();


        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
        public String getFileText() {
            for (String s : temps) {
                return s;
            }
            return String.valueOf(this);

        }

    public void run(){
        find = gui.getTxtFind();
        strReplace = gui.getTxtReplace();
        buffer = new BoundedBuffer(gui.getTxtPaneSource(), notify, gui.getTxtFind(), gui.getTxtReplace(), 15);
        writer = new Writer(buffer, temps);
        reader = new Reader(buffer, 15);
        writer.run();
        reader.run();
    }
}
