package com.hub.gui.interview.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hub.gui.interview.R;
import com.hub.gui.interview.activity.MainActivity;
import com.hub.gui.interview.adapter.MovieAdapter;
import com.hub.gui.interview.manager.MovieManager;
import com.hub.gui.interview.model.Movie;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.hub.gui.interview.fragment.MovieDetailsFragment.KEY_MOVIE;

public class ListFragment extends Fragment {

    private static final String TAG = "ListFragment";
    private RecyclerView mRecyclerView;
    private MovieAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_list,container,false);
        mRecyclerView = v.findViewById(R.id.recylerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mAdapter = new MovieAdapter(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {

                Movie movie = mAdapter.getMovie(position);
                if(movie == null)
                    return;

                Bundle bundle = new Bundle();
                bundle.putString(KEY_MOVIE,movie.getName());

                Fragment f = new MovieDetailsFragment();
                f.setArguments(bundle);
                getFragmentManager()
                        .beginTransaction()
                        .replace(MainActivity.MAIN_CONTAINER,f)
                        .addToBackStack(null)
                        .commit();

            }
        });

        mRecyclerView.setAdapter(mAdapter);

        loadMovies();
    }

    private void loadMovies(){
        MovieManager manager = MovieManager.getInstance();
        mAdapter.setMovieList(manager.getMovies());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
