package com.Logic;

import com.BoundedBuffer.BoundedBuffer;

import java.util.LinkedList;

/**
 * Created by Dennis on 2015-12-07.
 */
public class Writer {
    private BoundedBuffer buffer;
    private LinkedList<String> textToWrite = new LinkedList<>();

    public Writer(BoundedBuffer buffer, LinkedList<String> textToWrite) {
        this.buffer = buffer;
        this.textToWrite = textToWrite;
    }

    public void WriteLoop() {

    }
}
