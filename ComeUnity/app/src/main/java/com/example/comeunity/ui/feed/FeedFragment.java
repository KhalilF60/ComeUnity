package com.example.comeunity.ui.feed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comeunity.PostDetails;
import com.example.comeunity.R;
import com.example.comeunity.ui.post.Post;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class FeedFragment extends Fragment {

    FirestoreRecyclerAdapter adapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView rv = view.findViewById(R.id.rv_post_list);
        Log.d("UIFireTest","Entering adapter");

        Query query = FirebaseFirestore.getInstance()
                .collection("Posts")
                .limit(50);
        Log.d("UIFireTest","After query");

        FirestoreRecyclerOptions<Post> options = new FirestoreRecyclerOptions.Builder<Post>()
                .setQuery(query, Post.class)
                .build();


        adapter = new FirestoreRecyclerAdapter<Post, PostHolder>(options) {
            @Override
            public void onBindViewHolder(PostHolder holder, int position, Post model) {
                // Bind the Chat object to the ChatHolder

                holder.titleVeiw.setText(model.getTitle());
                holder.locationView.setText(model.getLocationName());
                holder.dateView.setText(model.getDate() + " " + model.getStartTime());
                holder.descriptionView.setText(model.getInformation());
               // holder.locationView.setText(model.getEventType()+" local test");
                Log.d("UIFireTest",model.getInformation());
            }

            @Override
            public PostHolder onCreateViewHolder(ViewGroup group, int i) {
                // Create a new instance of the ViewHolder, in this case we are using a custom
                // layout called R.layout.message for each item
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.post_item_layout_rv, group, false);

                return new PostHolder(view);
            }
        };
        adapter.startListening();

        GridLayoutManager manager = new GridLayoutManager(getContext(),2);
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_feed, container, false);


        return root;
    }



    private class PostHolder extends RecyclerView.ViewHolder {

        TextView titleVeiw, descriptionView, locationView, dateView;

        public PostHolder(@NonNull View itemView) {
            super(itemView);
            titleVeiw = itemView.findViewById(R.id.textView_title);
            locationView = itemView.findViewById(R.id.textView_location_name);
            descriptionView = itemView.findViewById(R.id.textView_postDescription);
            dateView = itemView.findViewById(R.id.textView_date);

            descriptionView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(), PostDetails.class));
                }
            });
        }
    }

    @Override
    public void onStart() {
        adapter.startListening();
        super.onStart();
    }

    @Override
    public void onStop() {
        adapter.stopListening();
        super.onStop();
    }
}