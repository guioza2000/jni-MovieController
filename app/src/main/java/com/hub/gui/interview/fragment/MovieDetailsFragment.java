package com.hub.gui.interview.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.gui.interview.R;
import com.hub.gui.interview.adapter.MovieDetailsAdapter;
import com.hub.gui.interview.manager.MovieManager;
import com.hub.gui.interview.model.MovieDetail;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MovieDetailsFragment extends Fragment {

    public static final String KEY_MOVIE = "movie";
    public static final String TAG ="MovieDetailsFragment";

    private String mMovieName = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            mMovieName = savedInstanceState.getString(KEY_MOVIE);
        }
        else if (getArguments() != null) {
            mMovieName = getArguments().getString(KEY_MOVIE);
        }

        Log.i(TAG,"movieName "+mMovieName);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movie_details,container,false);

        MovieManager manager = MovieManager.getInstance();
        MovieDetail details = manager.getMovieDetails(mMovieName);

        final MovieDetailsAdapter adapter = new MovieDetailsAdapter();
        adapter.setMovieDetail(details);

        RecyclerView recyclerView = v.findViewById(R.id.recylerview_actor);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch(adapter.getItemViewType(position)){
                    case MovieDetailsAdapter.TYPE_HEADER:
                        return 3;

                    case MovieDetailsAdapter.TYPE_ITEM:
                        return 1;

                    default:
                        return 1;
                }
            }
        });

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        return  v;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(KEY_MOVIE,mMovieName);
        super.onSaveInstanceState(outState);
    }
}
