package com.Workers;

import com.BoundedBuffer.BoundedBuffer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dennis on 2015-12-07.
 * This class runs  a method that add lines to the stringlist from the BufferReader method.
 */
public class Reader extends Thread {
    private BoundedBuffer buffer;
    private int count;
    private List<String> stringList = new ArrayList<>();

    public Reader(BoundedBuffer buffer, int count) {
        this.buffer = buffer;
        this.count = count;
    }
    public void run() {
        try {
            while (stringList.size() < count) {
                stringList.add(buffer.ReadData());
            }
            buffer.show();

        } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    public List<String> getStringList() {
        return stringList;
    }


}

