package com.hub.gui.interview.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hub.gui.interview.R;
import com.hub.gui.interview.model.Movie;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    public static final String TAG = "MovieAdapter";
    private List<Movie> mMovieList = null;
    private OnItemClickListener mListener;


    public MovieAdapter(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView mTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.txt_name);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        final ViewHolder viewHolder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null)
                    mListener.onItemClick(v,viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i(TAG,"onBindView");
        if(mMovieList == null || mMovieList.get(position) == null){
            //reset the view and return
            holder.mTextView.setText("");
        }

        Movie movie = mMovieList.get(position);
        holder.mTextView.setText(movie.getName());
    }

    @Override
    public int getItemCount() {
        if(mMovieList == null)
            return 0;

        return mMovieList.size();
    }

    public void setMovieList(List<Movie> list){
        this.mMovieList = list;
        notifyDataSetChanged();
    }

    public Movie getMovie(int position){
        if(mMovieList == null  || position >= mMovieList.size()|| position <0)
            return  null;

        return  mMovieList.get(position);
    }

    public interface OnItemClickListener{
        public void onItemClick(View v, int position);
    }
}
