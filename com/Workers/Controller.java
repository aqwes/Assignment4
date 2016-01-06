package com.Workers;

import com.BoundedBuffer.BoundedBuffer;
import com.Main.GUIMonitor;
import com.Modifier.Modifier;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Dennis on 2015-12-07.
 * This class opens and reads the textfile. It also starts the threads.
 */
public class Controller extends JFrame {
    private Reader reader;
    private Writer writer;
    private BoundedBuffer buffer;
    private Modifier modifier;
    private GUIMonitor gui;
    private List<String> temps = new ArrayList<>();
    private String txtFile;

    public Controller(GUIMonitor gui) {
        this.gui = gui;
    }

    /**
     * Filechooser that enables us to choose wich file we want to work with.
     * Supported format is: txt;
     */
    public void open() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Txt", "txt");
        fileChooser.setFileFilter(filter);

        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            txtFile = file.toString();
            readFile();

        }
    }

    /**
     * Reads the txt file and add each line to the temps list
     */
    public void readFile() {
        temps = new ArrayList<>();
        Scanner s = null;
        try {
            s = new Scanner(new File(txtFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (s.hasNextLine()) {
            String st = s.nextLine();
            temps.add(st + "\n");
            gui.setTxtPaneSource(st + "\n");
        }
    }

    /**
     * Start the threads
     */
    public void run() {
        buffer = new BoundedBuffer(gui.getTxtFind(), gui.getTxtReplaceString(), this);
        writer = new Writer(buffer, temps);
        reader = new Reader(buffer, temps.size());
        modifier = new Modifier(buffer, temps);
        start();
    }

    public void run2() {
        temps = reader.getStringList();
        buffer = new BoundedBuffer(gui.getTxtFind(), gui.getTxtReplaceString(), this);
        writer = new Writer(buffer, temps);
        reader = new Reader(buffer, temps.size());
        modifier = new Modifier(buffer, temps);
        start();

    }

    public void start() {
        writer.start();
        modifier.start();
        reader.start();
    }


    public List<String> getReaderList() {
        return reader.getStringList();

    }

    public void showDestination() {
        gui.appeandDestination();
    }
}
