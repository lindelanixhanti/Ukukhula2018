package za.co.codetribe.ukukhula.learner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import za.co.codetribe.ukukhula.AdminProfile.ParentProfile;
import za.co.codetribe.ukukhula.R;
import za.co.codetribe.ukukhula.User;

import static android.R.attr.name;
import static android.R.attr.type;
import static android.R.attr.value;
import static za.co.codetribe.ukukhula.R.array.gender;
import static za.co.codetribe.ukukhula.R.drawable.address;
import static za.co.codetribe.ukukhula.R.id.className;

public class LearnrsActivity extends AppCompatActivity {
    FirebaseDatabase firebaseData;
    DatabaseReference roofdef, demodef;
    ListView listview;
    List<LearnerProfile> profLists;
//
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                Intent intent = new Intent(this, RegisterLearner.class);
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
        setContentView(R.layout.activity_profilekid);


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listview = (ListView) findViewById(R.id.listView);
        // save = (Button) findViewById(R.id.save);

        profLists = new ArrayList<>();

        roofdef = FirebaseDatabase.getInstance().getReference();
        demodef = roofdef.child("Children");

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
                    LearnerProfile profil = profilesShot.getValue(LearnerProfile.class);
                    profLists.add(profil);

                }

                LearnersAdapter adapter = new LearnersAdapter(LearnrsActivity.this, profLists);
                listview.setAdapter(adapter);

                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        Toast.makeText(LearnrsActivity.this, "am inside the list "+ position, Toast.LENGTH_LONG).show();
//                        final DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Children");
//                        database.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                Log.i("Ygritte", "Parent : " + dataSnapshot.toString());
//                                if (dataSnapshot.getValue() != null) {
//                                    String key= dataSnapshot.getKey();
//
//
//
//                                    //ParentProfile parentProfile = dataSnapshot.getValue(ParentProfile.class);
//                                    LearnerProfile user = dataSnapshot.getValue(LearnerProfile.class);
//
//                                    profLists.add(user);
//
//                                    Intent intent= new Intent(LearnrsActivity.this,RegisterLearner.class);
//                                    intent.putExtra("learners", String.valueOf(profLists));
//                                    startActivity(intent);
//
//                                    String name, surnam, allegie, dateofbirth, parentNam, contact, gende, classNam, favouriteMea;
//
//
//
//
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//
//                            }
//                        });
//                            Intent i = new Intent(LearnrsActivity.this, RegisterLearner.class);
//                            startActivity(i);
                        }


                        // Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_SHORT).show();


                });


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
