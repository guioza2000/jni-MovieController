package com.hub.gui.interview.model;

import java.util.List;

/**
 * This is a Java representation of the native movies::Actor
 */
public class Actor extends JniObject {

    public Actor(long ptr) {
        super(ptr);
    }

    public native int getAge();
    public native String getName();
    public native String getImageUrl();
}
