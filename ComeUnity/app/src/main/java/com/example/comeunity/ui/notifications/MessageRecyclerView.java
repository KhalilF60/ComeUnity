package com.example.comeunity.ui.notifications;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.comeunity.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageRecyclerView extends RecyclerView.Adapter<MessageRecyclerView.MyViewHolder>{
     Context context;
     ArrayList<String> messages;
     public MessageRecyclerView(Context context, ArrayList<String>messages){
         this.context = context;
         this.messages = messages;
     }
     @NonNull
     @Override
     public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.top_message_layout,parent,false);
        return new MyViewHolder(v);
     }

     @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position){
         holder.messages.setText(messages.get(position));
     }

     @Override
    public int getItemCount(){return messages.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder {
         TextView messages;
         public MyViewHolder(@NonNull View itemView){
             super(itemView);
             messages = itemView.findViewById(R.id.MessageInputLayout);
         }
    }
    }

