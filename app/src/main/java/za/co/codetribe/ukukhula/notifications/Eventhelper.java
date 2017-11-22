package za.co.codetribe.ukukhula.notifications;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import za.co.codetribe.ukukhula.AdminProfile.ProfileActivity;
import za.co.codetribe.ukukhula.MainActivity;
import za.co.codetribe.ukukhula.R;
import za.co.codetribe.ukukhula.gallery.GallaryActivity;
import za.co.codetribe.ukukhula.gallery.ImageDisplayActivity;

import static android.app.DatePickerDialog.*;

public class Eventhelper extends AppCompatActivity {

    EditText Ename, Edescription;
    EditText  select;
    String eName, eDescription,eDate;
    Button save;
    Boolean val = true;
    private static final String TAG=" HJHJKHJJH";


    FloatingActionButton add;
    FirebaseDatabase firebaseData;
    DatabaseReference roofdef, demodef;
    ListView listview;
    List<Event> eventList;

    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser user;
    private CalendarView mCalendarView;

    private DatePickerDialog.OnDateSetListener mDateSetListener;



    //ProfileActivityhelpers helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventhelper);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        save = (Button) findViewById(R.id.saveData);
        Ename = (EditText) findViewById(R.id.eventName);
        Edescription = (EditText) findViewById(R.id.eventDescription);
        select = (EditText) findViewById(R.id.date);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Events");

        eventList = new ArrayList<>();
        listview = (ListView) findViewById(R.id.listView);

//        select.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Calendar calendar= Calendar.getInstance();
//                int year=calendar.get(Calendar.YEAR);
//                int day=calendar.get(Calendar.DAY_OF_MONTH);
//                int month=calendar.get(Calendar.MONTH);
//
//                DatePickerDialog dialog= new DatePickerDialog(Eventhelper.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
//                dialog.show();
//
//
//            }
//        });
//        mDateSetListener= new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
//
//              month=month+1;
//                String selected= month + "/" + date +" / " + year;
//
//                select.setText(selected);
//            }
//        };


//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addEvent();
//            }
//        });
        add = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getBaseContext(), "I was clicked", Toast.LENGTH_LONG).show();
//                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                galleryIntent.setType("image/*");
//                startActivityForResult(galleryIntent, ACTION_CODE);

                Intent intent = new Intent(Eventhelper.this, EventActivity.class);
                startActivity(intent);
            }
        });
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
                String date = year + "/" + month + "/" + dayOfMonth;
                Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd:" + date);
                Intent intent = new Intent(Eventhelper.this, Event.class);
                intent.putExtra("date", date);
                startActivity(intent);

            }
        });

//    private void addEvent() {
//        Event event = new Event();
//        eName = Ename.getText().toString().trim();
//        eDescription = Edescription.getText().toString().trim();
//        eDate=select.getText().toString().trim();
//
//
//
//        if (!TextUtils.isEmpty(eName)) {
//            event.setEventName(eName);
//
//
//        } else {
//            Toast.makeText(Eventhelper.this, "Event not saved ", Toast.LENGTH_LONG).show();
//        }
//
//
//
//        if (!TextUtils.isEmpty(eDescription)) {
//            event.setEventDiscription(eDescription);
//
//
//        } else {
//            Toast.makeText(Eventhelper.this, "no desc", Toast.LENGTH_LONG).show();
//        }
//
//
//        if (!TextUtils.isEmpty(eDate)) {
//            event.setDate(eDate);
//
//
//        } else {
//            Toast.makeText(Eventhelper.this, "NO DATE", Toast.LENGTH_LONG).show();
//        }
//
//
//        Event eve = new Event(eName, eDescription,eDate);
//        mDatabase.push().setValue(eve);
//
//        Ename.setText(" ");
//        Edescription.setText(" ");
//        select.setText("");
//
//        try {
//
//
//        } catch (Exception e) {
//            val = false;
//        } finally {
//            if (val) {
//                Toast.makeText(Eventhelper.this, "Event saved ", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(Eventhelper.this, "Event not saved ", Toast.LENGTH_LONG).show();
//            }
//        }
//
//    }
//


        //fetch
    }
    @Override
    protected void onStart() {
        super.onStart();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                eventList.clear();

                Log.i(" f=====================", dataSnapshot.toString());

                for (DataSnapshot eventsShot : dataSnapshot.getChildren()) {
                    Log.i(" AVIWE", eventsShot.toString());
                    Event event = eventsShot.getValue(Event.class);
                    eventList.add(event);

                }

                EventAdapter adapter = new EventAdapter(Eventhelper.this, eventList);
                listview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
//    public void showUpdateDIALOG(String id, String eventName, String eventDescription) {
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//
//        final View dialogView = inflater.inflate(R.layout.dialog, null);
//        dialogBuilder.setView(dialogView);
//
//        final EditText Ename = (EditText) dialogView.findViewById(R.id.eventName);
//        final EditText Edescription = (EditText) dialogView.findViewById(R.id.eventDescription);
//        final Button update = (Button) dialogView.findViewById(R.id.update);
//
//        dialogBuilder.setTitle("Update Event" + id);
//
//        AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//
//    }


}






