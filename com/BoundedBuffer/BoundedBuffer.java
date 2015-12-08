package com.BoundedBuffer;

import sun.awt.Mutex;

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
    private String rtxBox;
    private String findString;
    private String replaceString;
    private int start;
    private int nbrReplacements;
    private boolean notify;
    private Mutex lockObject;


    public BoundedBuffer(String rtxBox, boolean notify, String find, String strReplace, int element) {
        this.rtxBox = rtxBox;
        this.notify = notify;
        this.findString = find;
        this.replaceString = strReplace;
        this.max = element;

        System.out.println(find);
        System.out.println(rtxBox);
        System.out.println(strReplace);

    }


    public void Mark() {

    }

    public void Modify() {

    }

    public String ReadData() {

        return null;
    }

    public String ReplaceAt(String strSource, String strReplace, int pos, int size) {

        return strSource;
    }

    public void Select() {

    }

    public void WriteData(String s) {

    }


}
