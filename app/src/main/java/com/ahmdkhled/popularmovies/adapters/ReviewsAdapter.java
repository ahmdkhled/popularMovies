package com.ahmdkhled.popularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ahmdkhled.popularmovies.R;
import com.ahmdkhled.popularmovies.model.Review;

import java.util.ArrayList;

/**
 * Created by Ahmed Khaled on 2/18/2018.
 */

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewHolder> {

    private ArrayList<Review> reviewsList;
    private Context context;

    public ReviewsAdapter(Context context,ArrayList<Review> reviewsList) {
        this.context = context;
        this.reviewsList = reviewsList;
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(context).inflate(R.layout.review_row,parent,false);
        return new ReviewHolder(row);
    }

    @Override
    public void onBindViewHolder(ReviewHolder holder, int position) {
        holder.author.setText(reviewsList.get(position).getAuthor());
        holder.content.setText(reviewsList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        if (reviewsList==null){
            return 0;
        }
        else {
            return reviewsList.size();
        }
    }

    class ReviewHolder extends RecyclerView.ViewHolder{

        TextView author,content;

        ReviewHolder(View itemView) {
            super(itemView);
            author=itemView.findViewById(R.id.review_author);
            content=itemView.findViewById(R.id.review_content);
        }
    }
}
