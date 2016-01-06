package com.BoundedBuffer;


import com.Workers.Controller;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by Dennis on 2015-12-07.
 * This class handles the write, modifier and reader threads.
 * I´ve created three condition variables that controls these three methods.
 * There´s also a lock that ensures mutual exclusion.

 */
public class BoundedBuffer {
    private String[] strArr;
    private BufferStatus[] status;
    private static final int max = 15;
    private int writePos;
    private int readPos;
    private int findPos;
    private String findString;
    private String replaceString;

    private Controller controller;


    public BoundedBuffer(String find, String strReplace, Controller controller) {
        this.controller = controller;
        this.findString = find;
        this.replaceString = strReplace;
        strArr = new String[max];
        status = new BufferStatus[max];


        for (int i = 0; i < status.length; i++) {
            status[i] = BufferStatus.Empty;
        }
    }

    /**
     * This method checks every NEW string and replace the findString with the replaceString if needed.
     *
     * @throws InterruptedException
     */
    public synchronized void Modify() throws InterruptedException {


        while (status[findPos] != BufferStatus.New) {
            wait();
        }
        if (strArr[findPos].contains(findString)) {
            String s = strArr[findPos].replaceAll(findString, replaceString);
            strArr[findPos] = s;
        }
        status[findPos] = BufferStatus.Checked;
        findPos = (findPos + 1) % max;

        notifyAll();

    }

    /**
     * This method writes to the buffer if the object has BufferStatus empty.
     *
     * @param s
     * @throws InterruptedException
     */

    public synchronized void WriteData(String s) throws InterruptedException {


        while (status[writePos] != BufferStatus.Empty) {
            wait();
        }
        strArr[writePos] = s;
        status[writePos] = BufferStatus.New;
        writePos = (writePos + 1) % max;

        notifyAll();

    }

    /**
     * This method reads from the buffer if the object has BufferStatus checked.
     *
     * @return
     * @throws InterruptedException
     */
    public synchronized String ReadData() throws InterruptedException {


        while (status[readPos] != BufferStatus.Checked) {
            wait();
        }
        String s = strArr[readPos];
        status[readPos] = BufferStatus.Empty;
        readPos = (readPos + 1) % max;
        notifyAll();
        return s;


    }

    /**
     * This method appens the destination text to the source for another modification.
     */
    public void show() {
        controller.showDestination();
    }
}


