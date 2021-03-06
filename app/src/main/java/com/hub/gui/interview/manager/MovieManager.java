package com.hub.gui.interview.manager;

import com.hub.gui.interview.model.JniObject;
import com.hub.gui.interview.model.Movie;
import com.hub.gui.interview.model.MovieDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a Java representation of the native movies::MovieController
 */
public class MovieManager extends JniObject {

    private static MovieManager sInstance = null;

    public static MovieManager getInstance(){
        if(sInstance == null){
            sInstance = new MovieManager(jniCreateManager());
        }
        return sInstance;
    }

    public MovieManager(long ptr) {
        super(ptr);
    }

    /**
     * Request a list of movie from the native MovieController
     * @return a list of Java movie objects {@link Movie}
     */
    public List<Movie> getMovies(){
        List<Movie> result = new ArrayList<Movie>();
        long[] jniMovies = jniGetMovieList();

        if(jniMovies != null){
            for(long ptr: jniMovies){
                result.add(new Movie(ptr));
            }
        }
        return result;
    }

    /**
     * Request the details of the given movie from the native MovieController
     * @param movieName, the name of the movie to get details of
     * @return the movie details {@link MovieDetail}
     */
    public MovieDetail getMovieDetails(String movieName){
        if(movieName == null)
            return null;

        long ptr = jniGetMovieDetails(movieName);
        if(ptr == 0)
            return null;

        MovieDetail details = new MovieDetail(ptr);

        return details;
    }

    private static native long jniCreateManager();
    private native long[] jniGetMovieList();
    private native long jniGetMovieDetails(String movie);

    /**
     * Deallocate the native MovieController
     */
    public native void delete();
}
