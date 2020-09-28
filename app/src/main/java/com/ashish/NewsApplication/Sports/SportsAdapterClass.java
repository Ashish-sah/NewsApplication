package com.ashish.NewsApplication.Sports;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashish.NewsApplication.R;
import com.ashish.NewsApplication.newsExplorer;
import com.bumptech.glide.Glide;

public class SportsAdapterClass extends RecyclerView.Adapter<SportsAdapterClass.MyViewHolder> {
    private Context context;

    public SportsAdapterClass(Context context) {
        this.context = context;
    }

    //this method is used to create object of each viewHolder
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    //to display the data or assign
    //or bind the actual data with the layout
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.titleView.setText(SportsActivity.list.get(position).getTitle());
        holder.detailsView.setText(SportsActivity.list.get(position).getDetails());
        //loading image
        Glide.with(context).load(SportsActivity.list.get(position).getImageUrl()).into(holder.imgView);

        holder.explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(v.getContext(),"Layout not made", Toast.LENGTH_LONG).show();
               Intent myIntent = new Intent(v.getContext(), newsExplorer.class);
                myIntent.putExtra("newsUrl",SportsActivity.list.get(position).getNewsUrl());
                v.getContext().startActivity(myIntent);
            }
        });

        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                //set the type for the sharing thing ie format
                sharingIntent.setType("text/plain");
                String shareBody = "This news is shared using Ashish News app :: " + "\n\n" +SportsActivity.list.get(position)
                        .getNewsUrl() + "\n\n Follow him on GitHub : https://github.com/Ashish-sah";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                v.getContext().startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public int getItemCount() {
        //define the size of recycler view
        return SportsActivity.list.size();

    }

    //MyViewHolder is represents for each item in the recycler view

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgView;   //this for image
        TextView titleView;  //this for title
        TextView detailsView; //this for detail view
        TextView hiddenUrl;
        Button explore;    //this for content
        Button share;      //this for newsUrl

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = itemView.findViewById(R.id.imgID);
            titleView = itemView.findViewById(R.id.titleTextViewID);
            detailsView = itemView.findViewById(R.id.detailsTextViewID);
            explore = itemView.findViewById(R.id.exploreButtonId);
            share = itemView.findViewById(R.id.shareButtonId);
            hiddenUrl = itemView.findViewById(R.id.hiddenUrl);
        }
    }
}
