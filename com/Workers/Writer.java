package com.Workers;

import com.BoundedBuffer.BoundedBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 2015-12-07.
 * This class write strings to the buffer
 */
public class Writer extends Thread {

    private BoundedBuffer buffer;
    private List<String> textIn = new ArrayList<>();

    public Writer(BoundedBuffer buffer, List<String> textIn) {
        this.buffer = buffer;
        this.textIn = textIn;

    }


    @Override
    public void run() {
        try {
            for (String s : textIn) {
                buffer.WriteData(s);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}



