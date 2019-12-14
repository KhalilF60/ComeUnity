package com.example.comeunity.ui.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comeunity.R;

import java.util.ArrayList;

public class FeedRecyclerView extends RecyclerView.Adapter<FeedRecyclerView.MyViewHolder> {
    Context context;
    ArrayList<String> posts;

    public FeedRecyclerView(Context context, ArrayList<String> posts){
        this.context = context;
        this.posts = posts;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v  = LayoutInflater.from(context).inflate(R.layout.post_item_layout_rv,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.postTile.setText(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView postTile;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            postTile = itemView.findViewById(R.id.imageView);
        }
    }
}
