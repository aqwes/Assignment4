package com.Logic;

import com.BoundedBuffer.BoundedBuffer;
import com.BoundedBuffer.BufferStatus;

import java.util.List;

/**
 * Created by Dennis on 2015-12-07.
 */
public class Writer implements Runnable {

    private BoundedBuffer buffer;
    private List<String> textIn;

    public Writer(BoundedBuffer buffer, List<String> textIn) {
        this.buffer = buffer;
        this.textIn = textIn;
    }


    @Override
    public void run() {

        for (String s : textIn) {
            try {
                buffer.WriteData(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


