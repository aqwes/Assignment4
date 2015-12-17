package com.Monitor;

import com.BoundedBuffer.BoundedBuffer;
import com.Logic.Controller;

/**
 * Created by Dennis on 2015-12-07.
 */
public class Modifier implements Runnable {
    private BoundedBuffer buffer;
    private int count;
    private boolean runnning;
    private Controller controller;

    public Modifier(BoundedBuffer buffer, String[] inText, Controller controller) {
        this.buffer = buffer;
        this.count = inText.length;
        this.controller = controller;
    }



    @Override
    public void run() {

        for (int i = 0; i < count; i++) {
                buffer.Modify();
            }
        }

}
