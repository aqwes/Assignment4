package com.Monitor;

import com.BoundedBuffer.BoundedBuffer;

/**
 * Created by Dennis on 2015-12-07.
 */
public class Modifier implements Runnable {
    private BoundedBuffer buffer;
    private int count;

    public Modifier(BoundedBuffer buffer, String[] inText) {
        this.buffer = buffer;
        this.count = inText.length;
    }

    public void ModifierLoop() {

    }

    @Override
    public void run() {

        for (int i = 0; i < count; i++) {
            try {
                buffer.Modify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
