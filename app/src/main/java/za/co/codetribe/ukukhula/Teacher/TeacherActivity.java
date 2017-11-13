package za.co.codetribe.ukukhula.Teacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import za.co.codetribe.ukukhula.R;
import za.co.codetribe.ukukhula.learner.LearnerProfile;
import za.co.codetribe.ukukhula.learner.LearnersAdapter;
import za.co.codetribe.ukukhula.learner.RegisterLearner;

public class TeacherActivity extends AppCompatActivity {
    FirebaseDatabase firebaseData;
    DatabaseReference roofdef, demodef;
    ListView listview;
    List<TeacherProfile> profLists;
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                return true;
//
//            finish();
            default:
            return super.onOptionsItemSelected(item);

        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileteacher);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listview = (ListView) findViewById(R.id.listView);
        // save = (Button) findViewById(R.id.save);

        profLists = new ArrayList<>();

        roofdef = FirebaseDatabase.getInstance().getReference();
        demodef = roofdef.child("Teachers");

    }

    //fetch
//
    @Override
    protected void onStart() {
        super.onStart();
        demodef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                profLists.clear();

                Log.i(" f=====================", dataSnapshot.toString());

                for (DataSnapshot profilesShot : dataSnapshot.getChildren()) {
                    Log.i(" AVIWE", profilesShot.toString());
                    TeacherProfile profil = profilesShot.getValue(TeacherProfile.class);
                    profLists.add(profil);

                }

                TeacherAdapter adapter = new TeacherAdapter(TeacherActivity.this, profLists);
                listview.setAdapter(adapter);

//                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        LearnerProfile profList = profLists.get(position);
//                        Intent i = new Intent(LearnrsActivity.this, RegisterLearners.class);
//                        startActivity(i);
//                        // Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();
//                    }
//
//                });


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learners, menu);

        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    //int id = item.getItemId();
//
//        switch (item.getItemId()) {
//            case R.id.Reg:
//                Intent intent = new Intent(this, RegisterLearners.class);
//                startActivity(intent);
//                return true;
//
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
}
