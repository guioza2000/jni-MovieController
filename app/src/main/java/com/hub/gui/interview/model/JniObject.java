package com.hub.gui.interview.model;


/**
 * This Class represent a Default native object.
 * {@link JniObject#nativeHandle} is the pointer to this object
 */
public class JniObject {
    private long nativeHandle;

    public JniObject(long ptr){
        this.nativeHandle = ptr;
    }

    public long getNativeHandle(){
        return nativeHandle;
    }
}
