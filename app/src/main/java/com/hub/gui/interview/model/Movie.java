package com.hub.gui.interview.model;

/**
 * This is a Java representation of the native movies::Movie
 */
public class Movie extends JniObject {

    public Movie(long ptr) {super(ptr);}

    public native String getName();
    public native int getLastUpdate();
}
