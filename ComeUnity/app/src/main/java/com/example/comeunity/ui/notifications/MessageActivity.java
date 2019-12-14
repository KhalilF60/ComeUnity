package com.example.comeunity.ui.notifications;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.comeunity.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_message);

        final TextInputEditText messageEditText = findViewById(R.id.MessageInputLayout);
        Button sendMessage = findViewById(R.id.sendMessage);

        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message = messageEditText.getText().toString();
                Date currentTime = Calendar.getInstance().getTime();
                String Date = currentTime.toString();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String Receiver = messageEditText.getText().toString();
                MessagePost messagePost = new MessagePost(user.getUid(), Date, message, Receiver);

                storeData(messagePost);
                finish();
            }


        });
    }
    private void storeData(MessagePost messagePost){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(("Messages")).add(messagePost).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(MessageActivity.this, "Success posting", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MessageActivity.this, "Error posting", Toast.LENGTH_SHORT).show();

            }
        });
}
    }
