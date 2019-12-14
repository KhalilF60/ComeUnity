package com.example.comeunity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class PostDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);


        final ImageView image = findViewById(R.id.imageView2);
        final TextView profilename = findViewById(R.id.textView);
        final TextView date= findViewById(R.id.textView6);
        final TextView location = findViewById(R.id.textView10);
        final TextView discription = findViewById(R.id.textView11);
        Button interested = findViewById(R.id.IButton);
        Button CheckIn = findViewById(R.id.checkin);
        Button CheckOut = findViewById(R.id.checkout);

        interested.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String image2  = image.getText().toString();
                String profilename2 =profilename.getText().toString();
                String date2  = date.getText().toString();
                String location2 = location.getText().toString();
                String discription2 = discription.getText().toString();
            }


    });
    }
}
