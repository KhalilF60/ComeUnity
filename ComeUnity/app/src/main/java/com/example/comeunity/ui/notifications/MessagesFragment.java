package com.example.comeunity.ui.notifications;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.comeunity.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MessagesFragment extends Fragment {

    FirestoreRecyclerAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        RecyclerView rv = view.findViewById(R.id.rv_message_list);

        Log.d("UIFireTest", "Entering adapter");

        Query query = FirebaseFirestore.getInstance()
                .collection("Messages")
                .limit(15);
        Log.d("UIFireTest", "After query");

        FirestoreRecyclerOptions<MessagePost> options = new FirestoreRecyclerOptions.Builder<MessagePost>()
                .setQuery(query, MessagePost.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<MessagePost, MessageHolder>(options) {
            @Override
            public void onBindViewHolder(MessageHolder holder, int position, MessagePost model) {
                Log.d("Test3", model.getMessage());
                Log.d("Test3", model.getDate());
                Log.d("Test3", model.getReceiver());
                holder.message.setText(model.getMessage());
                holder.receiver.setText(model.getReceiver());
                holder.date.setText(model.getDate());
                Log.d("UIFireTest", model.getReceiver());
            }

            @Override
            public MessageHolder onCreateViewHolder(ViewGroup group, int i) {

                View view = LayoutInflater.from(group.getContext()).inflate(R.layout.top_message_layout, group, false);
                return new MessageHolder(view);
            }
        };
        adapter.startListening();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setAdapter(adapter);
        rv.setLayoutManager(manager);
        Button newMessage = view.findViewById(R.id.newMessage);
        newMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),MessageActivity.class));
            }
        });
        final TextView textView = view.findViewById(R.id.MessageInput);
    }



//        notificationsViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);





        return root;
    }

    private class MessageHolder extends RecyclerView.ViewHolder{
        TextView receiver, message, date;

        public MessageHolder(@NonNull View messageView) {
            super(messageView);
            message = messageView.findViewById(R.id.messageView);
            receiver = messageView.findViewById(R.id.contact);
            date = messageView.findViewById(R.id.timesent);
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