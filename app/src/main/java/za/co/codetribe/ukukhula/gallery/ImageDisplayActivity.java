package za.co.codetribe.ukukhula.gallery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import za.co.codetribe.ukukhula.MainActivity;
import za.co.codetribe.ukukhula.R;
import za.co.codetribe.ukukhula.learner.RegisterLearner;

public class ImageDisplayActivity extends AppCompatActivity {
    DatabaseReference databaseReference;
    List<ImagePojo> imgList;
    GridView listView;
    ImagePojo imagePojo;
    ProgressDialog pd;

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        finish();
//        return super.onOptionsItemSelected(item);
//
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallarylist);

       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        listView = (GridView) findViewById(R.id.listImages);

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
                    imagePojo = dataSnapshot1.getValue(ImagePojo.class);
                    imgList.add(imagePojo);


                }


                ImageAdapter adapter = new ImageAdapter(ImageDisplayActivity.this, R.layout.activity_gallarylist, imgList);
                listView.setAdapter(adapter);
                listView.setDrawSelectorOnTop(true);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent intent =new Intent(ImageDisplayActivity.this,ViewImageActivity.class);
                        intent.putExtra("imagePojo",imagePojo);
                        startActivity(intent);
                        Toast.makeText(ImageDisplayActivity.this, imagePojo.getUrl(), Toast.LENGTH_LONG).show();
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                pd.dismiss();

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId() )
        {
            case R.id.add:
                Intent intent = new Intent(ImageDisplayActivity.this,GallaryActivity.class);
                startActivity(intent);

        }
        finish();
        return super.onOptionsItemSelected(item);
    }

    //menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_learners, menu);
        return true;
    }

}
