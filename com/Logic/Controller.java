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
    private GUIMonitor test;
    private List<String> temps = new LinkedList<>();
    private String[] tempsArray;
    private boolean notify;
    private String find;
    private String strReplace;
    private int element;

    public Controller(GUIMonitor test) {
        this.test=test;

        this.writer = writer;
        this.reader = reader;
        this.modifier = modifier;
    }

    public void btnCreate() {
    }

    public void btnClear() {
    }

    public void readFile(String txtFile ) {
        String token1 = "";
        try {
            Scanner inFile1 = new Scanner(new File(txtFile)).useDelimiter(",\\s*");

            while (inFile1.hasNext()) {
                token1 = inFile1.next();
                temps.add(token1);
            }
            inFile1.close();

           tempsArray = temps.toArray(new String[0]);

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
        public String getFileText() {
            for (String s : tempsArray) {

                return s;
            }
            return String.valueOf(this);

        }

    public void run(){
        find= test.getTxtFind();
        strReplace = test.getTxtReplace();
        this.buffer= new BoundedBuffer(getFileText(), notify,find,strReplace,element);
    }
}
