package com.hub.gui.interview.model;

public class JniObject {
    private long nativeHandle;

    public JniObject(long ptr){
        this.nativeHandle = ptr;
    }

    public long getNativeHandle(){
        return nativeHandle;
    }
}
