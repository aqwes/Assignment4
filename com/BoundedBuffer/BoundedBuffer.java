package com.BoundedBuffer;

import sun.awt.Mutex;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.util.concurrent.Semaphore;

/**
 * Created by Dennis on 2015-12-07.
 */
public class BoundedBuffer {
    private String[] strArr;
    private BufferStatus[] status;

    private int max;
    private int writePos;
    private int readPos;
    private int findPos;
    private JTextPane rtxBox;
    private String findString;
    private String replaceString;
    private int start;
    private int nbrReplacements;
    private boolean notify;
    private Mutex lock;
    private Semaphore semEmpty;
    private Semaphore semFull;

    public BoundedBuffer(JTextPane rtxBox, boolean notify, String find, String strReplace, int element) {
        this.rtxBox = rtxBox;
        this.notify = notify;
        this.findString = find;
        this.replaceString = strReplace;
        this.max = element;
        strArr = new String[element];
        status = new BufferStatus[element];

        lock = new Mutex();
        semFull = new Semaphore(0);
        semEmpty = new Semaphore(element);


        for (int i = 0; i < status.length; i++) {
            status[i] = BufferStatus.Empty;
        }
    }



    public void Modify() {
        if (rtxBox.equals(findString)) {
            nbrReplacements++;

        }
    }

    public String ReadData() throws InterruptedException {

        semEmpty.acquire();

        synchronized (lock) {
            if (status[readPos] == BufferStatus.Checked) {

                status[readPos] = BufferStatus.Empty;
            }
            semFull.release();
            return strArr[readPos];
        }
    }

    public String ReplaceAt(String strSource, String strReplace, int pos, int size) throws BadLocationException {
        rtxBox.setText(strSource.replaceFirst(strSource, strReplace));

        return strSource;
    }

    public void Select() {

    }

    public void WriteData(String s) throws InterruptedException {

        semFull.acquire();

        strArr[writePos] = s;

        if (status[writePos] != BufferStatus.Empty) {
            synchronized (lock) {
                status[writePos + 1 % max] = BufferStatus.New;

            }
            status[writePos] = BufferStatus.Checked;
        }
        semEmpty.release();
    }

}
