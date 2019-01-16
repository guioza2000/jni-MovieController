package com.hub.gui.interview.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Java representation of the native movies::MovieDetail
 */
public class MovieDetail extends JniObject {

    private List<Actor> mActorList;

    public MovieDetail(long ptr) {
        super(ptr);
    }

    public List<Actor> getActorsList(){
        if(mActorList == null)
            loadActors();

        return mActorList;
    }

    private void loadActors(){
        List<Actor> temp = new ArrayList<Actor>();
        long[] jniActors = getActors();

        if(jniActors != null){
            for(long ptr: jniActors){
                temp.add(new Actor(ptr));
            }
        }

        mActorList = temp;
    }

    public native String getName();
    public native float getScore();
    private native long[] getActors();
    public native String getDescription();
}
