package za.co.codetribe.ukukhula;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import za.co.codetribe.ukukhula.AdminProfile.ProfileActivity;
import za.co.codetribe.ukukhula.Groups.ClassesActivitys;
import za.co.codetribe.ukukhula.School.SchoolRegister;
import za.co.codetribe.ukukhula.Teacher.RegisterActivity;
import za.co.codetribe.ukukhula.Teacher.TeacherActivity;
import za.co.codetribe.ukukhula.gallery.GallaryActivityParent;
import za.co.codetribe.ukukhula.gallery.ImageAdapter;
import za.co.codetribe.ukukhula.gallery.ImageDisplayActivity;
import za.co.codetribe.ukukhula.gallery.ImagePojo;
import za.co.codetribe.ukukhula.learner.LearnrsActivity;
import za.co.codetribe.ukukhula.notifications.Eventhelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    DatabaseReference databaseReference;
    List<ImagePojo> imgList;
    ListView listView;

    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listView = (ListView) findViewById(R.id.listImages);

        imgList = new ArrayList<>();

        pd = new ProgressDialog(this);
        pd.setMessage(" please wait ....");
        pd.show();

        databaseReference = FirebaseDatabase.getInstance().getReference("imagess");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pd.dismiss();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Log.i(" AVIWE", dataSnapshot.toString());
                    ImagePojo imagePojo = (ImagePojo) dataSnapshot1.getValue(ImagePojo.class);
                    imgList.add(imagePojo);

                }


                ImageAdapter adapter = new ImageAdapter(MainActivity.this, R.layout.activity_gallarylist, imgList);
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                pd.dismiss();

            }
        });
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.done) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.My_Profile) {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(MainActivity.this, ImageDisplayActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_teacher) {
            Intent intent = new Intent(MainActivity.this, TeacherActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Children) {
            Intent intent = new Intent(MainActivity.this, LearnrsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_notifications) {
            Intent intent = new Intent(MainActivity.this, Eventhelper.class);
            startActivity(intent);

        } else if (id == R.id.nav_school) {
            Intent intent = new Intent(MainActivity.this, SchoolRegister.class);
            startActivity(intent);

        } else if (id == R.id.nav_classes) {
            Intent intent = new Intent(MainActivity.this, ClassesActivitys.class);
            startActivity(intent);

        } else if (id == R.id.logout) {
            Intent intent = new Intent(MainActivity.this, StartActivity.class);
            startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
