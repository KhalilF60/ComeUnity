package com.example.comeunity.ui.post;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
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

public class PostActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        final TextInputEditText titleEditText = findViewById(R.id.textInputEditText_post_title);
        //   final TextInputEditText locationEditText= findViewById(R.id.textInputEditText_Location);
        final EditText decscriptionEditText = findViewById(R.id.editText_post_description);
        final TextView timeEditText = findViewById(R.id.editText_start_time);
        final TextView endTimeView = findViewById(R.id.editText_end_time);
        Button postButton = findViewById(R.id.button_post);
        final Spinner spinner = findViewById(R.id.spinner_event_location);
        final TextView datePickerView = findViewById(R.id.textView_date);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TextView viewDatePicker = findViewById(R.id.textView_date);
                viewDatePicker.setText(""+(month+ 1)+"/"+dayOfMonth+"/"+year);
            }
        };

        timeEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(PostActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        timeEditText.setText( selectedHour + ":" + selectedMinute +"");
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        endTimeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(PostActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        endTimeView.setText( selectedHour + ":" + selectedMinute +"");
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });


        datePickerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(PostActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        String[] arraySpinner = new String[]{
                "JCSU Bookstore", "James B. Duke Library", "New Science Center", "Jack S. BrayBoy Gymnasium"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String location = locationEditText.getText().toString();
//                String decription = decscriptionEditText.getText().toString();
//                String time = timeEditText.getText().toString();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String title = titleEditText.getText().toString();
                String description = decscriptionEditText.getText().toString();
                String location = spinner.getSelectedItem().toString();
                String date = datePickerView.getText().toString();
                String startTime = timeEditText.getText().toString();
                String endTime = endTimeView.getText().toString();

                String lat = getLatFromUserPick(location);
                String lon = getLongFromUserPick(location);

                Post post = new Post(user.getUid(),date,startTime,endTime,title, "workshop", lat, lon, location,description);
                storeData(post);
                finish();
                Log.d("TESTT", "this is the stest i want");
            }
        });
    }

    private String getLatFromUserPick(String location) {
        if(location.equals("JCSU Bookstore")){
            return "35.243446";
        }
        if(location.equals("James B. Duke Library")){
            return "35.242896";
        }
        if(location.equals("New Science Center")){
            return "35.242294";
        }

        if(location.equals("Jack S. BrayBoy Gymnasium")){
            return "35.241801";
        }
        return "80.000";
    }
    private String getLongFromUserPick (String location){
        if(location.equals("JCSU Bookstore")){
            return "-80.855930";
        }
        if(location.equals("James B. Duke Library")){
            return "-80.8572986";
        }
        if(location.equals("New Science Center")){
            return "-80.857488";
        }

        if(location.equals("Jack S. BrayBoy Gymnasium")){
            return "-80.854408";
        }
        return "80.000";
    }

    private void storeData(Post post) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Posts").add(post).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(PostActivity.this, "Success posting", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PostActivity.this, "Error posting", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        TextView datePickerView = findViewById(R.id.textView_date);
        datePickerView.setText(""+month +"/" +dayOfMonth + "/" + year);
    }

}
