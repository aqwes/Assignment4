package com.Logic;

import com.BoundedBuffer.BoundedBuffer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dennis on 2015-12-07.
 */
public class Reader implements Runnable {
    private BoundedBuffer buffer;
    private int count;
    private List<String> stringList;

    public Reader(BoundedBuffer buffer, int count) {
        this.buffer = buffer;
        this.count = count;
    }


    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                stringList.add(buffer.ReadData());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<String> getStringList() {
        return stringList;
    }


}

