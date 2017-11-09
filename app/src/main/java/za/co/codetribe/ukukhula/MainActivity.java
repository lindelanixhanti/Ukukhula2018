package za.co.codetribe.ukukhula;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import za.co.codetribe.ukukhula.AdminProfile.ProfileActivity;
<<<<<<< HEAD
import za.co.codetribe.ukukhula.Children.Profile1Activity;
=======
import za.co.codetribe.ukukhula.Teacher.TeachersActivity;
import za.co.codetribe.ukukhula.learner.LearnrsActivity;
import za.co.codetribe.ukukhula.notifications.Eventhelper;
>>>>>>> 12ecbe10ed3f07fe86a9fa450d3f017114f51a2e

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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



//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.done) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.My_Profile) {
            Intent intent= new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent= new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);

<<<<<<< HEAD
        } else if (id == R.id.nav_school) {

        } else if (id == R.id.nav_teacher) {
=======
        } else if (id == R.id.nav_AddStaff) {
            Intent intent= new Intent(MainActivity.this, TeachersActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_Children) {
            Intent intent= new Intent(MainActivity.this,LearnrsActivity.class);
            startActivity(intent);
>>>>>>> 12ecbe10ed3f07fe86a9fa450d3f017114f51a2e

        } else if (id == R.id.nav_Children) {
            Intent i = new Intent(MainActivity.this, Profile1Activity.class);
            startActivity(i);
        } else if (id == R.id.nav_notifications) {
            Intent intent= new Intent(MainActivity.this, Eventhelper.class);
            startActivity(intent);

        } else if (id == R.id.nav_Contacts) {

        } else if (id == R.id.logout) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
