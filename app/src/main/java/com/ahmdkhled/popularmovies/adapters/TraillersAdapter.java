package com.ahmdkhled.popularmovies.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmdkhled.popularmovies.R;
import com.ahmdkhled.popularmovies.model.Trailer;

import java.util.ArrayList;

/**
 * Created by Ahmed Khaled on 2/21/2018.
 */

public class TraillersAdapter extends RecyclerView.Adapter<TraillersAdapter.TraillerHolder> {

    private ArrayList<Trailer> trailers;
    private Context context;

    public TraillersAdapter(Context context,ArrayList<Trailer> trailers) {
        this.context=context;
        this.trailers = trailers;
    }

    @Override
    public TraillerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.trailer_row,parent,false);
        return new TraillerHolder(view);
    }

    @Override
    public void onBindViewHolder(TraillerHolder holder, int position) {
        holder.name.setText(trailers.get(position).getName());
        holder.size.setText(trailers.get(position).getSize());
    }

    @Override
    public int getItemCount() {
        if (trailers==null){
            return 0;
        }
        return trailers.size();
    }

    class TraillerHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView name,size;
        TraillerHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.trailer_name);
            size=itemView.findViewById(R.id.trailer_size);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            String url=trailers.get(getAdapterPosition()).getUrl();
            Intent openIntent=new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            if(openIntent.resolveActivity(context.getPackageManager())!=null){
                context.startActivity(openIntent);
            }else{
                Toast.makeText(context,"sorry there is no app installed to open trailler ",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
