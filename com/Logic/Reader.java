package com.Logic;

import com.BoundedBuffer.BoundedBuffer;

import java.util.LinkedList;

/**
 * Created by Dennis on 2015-12-07.
 */
public class Reader {
    private BoundedBuffer buffer;
    private int count;
    private LinkedList<String> stringList = new LinkedList<>();

    public Reader(BoundedBuffer buffer, int count) {
        this.buffer = buffer;
        this.count = count;
    }

    public void ReadLoop() {

    }
}

