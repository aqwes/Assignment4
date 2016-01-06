package com.Modifier;

import com.BoundedBuffer.BoundedBuffer;

import java.util.List;

/**
 * This class modifies the text
 * Created by Dennis on 2015-12-07.
 */
public class Modifier extends Thread {
    private BoundedBuffer buffer;
    private int count;


    public Modifier(BoundedBuffer buffer, List<String> inText) {
        this.buffer = buffer;
        this.count = inText.size();

    }


    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                buffer.Modify();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
