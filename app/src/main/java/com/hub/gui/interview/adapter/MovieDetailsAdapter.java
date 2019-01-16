package com.hub.gui.interview.adapter;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hub.gui.interview.R;
import com.hub.gui.interview.model.Actor;
import com.hub.gui.interview.model.MovieDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieDetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;

    public static final String TAG = "MovieDetailsAdapter";
    private List<Actor> mActorList = null;
    private MovieDetail mMovieDetail;

    public static class ViewHolderHeader extends RecyclerView.ViewHolder {

        TextView mMovieName;
        TextView mScore;
        TextView mDescription;

        public ViewHolderHeader(@NonNull View itemView) {
            super(itemView);
            mMovieName = itemView.findViewById(R.id.txt_movie_name);
            mScore = itemView.findViewById(R.id.score);
            mDescription = itemView.findViewById(R.id.description);
        }
    }

    public static class ViewHolderActor extends RecyclerView.ViewHolder {
        TextView mTextView;
        ImageView mImageView;

        public ViewHolderActor(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.txt_actor_name);
            mImageView = itemView.findViewById(R.id.img_actor_picture);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actor, parent, false);
            ViewHolderActor viewHolder = new ViewHolderActor(v);
            return viewHolder;
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_details_header, parent, false);
            return new ViewHolderHeader(v);
        }

        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolderActor) {
            ViewHolderActor holderActor = (ViewHolderActor) holder;

            Actor actor = getActor(position);

            Log.i(TAG, "onBindView");
            if (getActor(position) == null) {
                //reset the view and return
                holderActor.mTextView.setText("");
                holderActor.mImageView.setImageResource(R.drawable.ic_android_black_24dp);
            }


            String txt = actor.getName() + ", " + actor.getAge();
            holderActor.mTextView.setText(txt);

            String url = actor.getImageUrl();
            Log.i(TAG, "url " + url);
            if (url == null || url.length() == 0) {
                holderActor.mImageView.setImageResource(R.drawable.ic_android_black_24dp);
            } else {
                Picasso.with(holderActor.itemView.getContext()).load(actor.getImageUrl())
                        .placeholder(R.drawable.ic_android_black_24dp)
                        .error(R.drawable.ic_android_black_24dp)
                        .into(holderActor.mImageView);
            }
        } else if (holder instanceof ViewHolderHeader) {
            ViewHolderHeader holderHeader = (ViewHolderHeader) holder;

            if(mMovieDetail == null){
                //clear view and return
                holderHeader.mMovieName.setText("");
                holderHeader.mDescription.setText("");
                holderHeader.mScore.setText("");
                return;
            }

            Context context = holderHeader.itemView.getContext();

            holderHeader.mMovieName.setText(mMovieDetail.getName());
            String txt = context.getString(R.string.score, mMovieDetail.getScore());
            holderHeader.mScore.setText(Html.fromHtml(txt));
            txt = context.getString(R.string.description, mMovieDetail.getDescription());
            holderHeader.mDescription.setText(Html.fromHtml(txt));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private Actor getActor(int position) {
        if(mActorList == null)
            return null;

        return mActorList.get(position - 1); //-1 because the header at the 1st position
    }

    @Override
    public int getItemCount() {
        if (mActorList == null)
            return 0;

        return mActorList.size() +1;//+1 for the header
    }

    public void setMovieDetail(MovieDetail details) {
        this.mMovieDetail = details;
        if(details == null) {
            this.mActorList = null;
        }else {
            this.mActorList = details.getActorsList();
        }
        notifyDataSetChanged();
    }
}
