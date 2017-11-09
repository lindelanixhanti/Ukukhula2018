package za.co.codetribe.ukukhula.AdminProfile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import za.co.codetribe.ukukhula.MainActivity;
import za.co.codetribe.ukukhula.R;




public class ProfileActivity extends AppCompatActivity {

    EditText name, surname, address, gender, parentName, parentContact, dateofbirth, parentEmail ,type;
    String nam, surnam, addres, gende, parentNam, parentContac, dateofbirt, email;
    Button save;
    CircleImageView profilePic;
    ImageView profileImgPallete;
    Button view;

    //authntification fields

    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser user;
    // FirebaseAuth.AuthStateListener mAuthListerner;

//FIREBASE DATABASE FIELDS
    //DatabaseReference userDatabase;
    // StorageReference mStorageRef;


    //images
    Uri imageUri;
    FloatingActionButton upload;
    private final int PICK_IMAGE_REQUEST = 71;
    private static final int ACTION_CODE = 1;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toast.makeText(getBaseContext(), "I was clicked", Toast.LENGTH_LONG).show();
        //BACK ARROW
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //assign the firebase auth instaances

        mAuth = FirebaseAuth.getInstance();

//
        storageReference = FirebaseStorage.getInstance().getReference();


        mDatabase = FirebaseDatabase.getInstance().getReference();
//



        //ASSING  ID S
        save = (Button) findViewById(R.id.saveData);
       // view = (Button) findViewById(R.id.view);

        profilePic = (CircleImageView) findViewById(R.id.imageButton);
      //  profileImgPallete = (ImageView) findViewById(R.id.image_palette);

        name = (EditText) findViewById(R.id.editname);
        surname = (EditText) findViewById(R.id.editsurname);
        address = (EditText) findViewById(R.id.editaddress);
//        gender = (EditText) findViewById(R.id.editgender);
//        parentName = (EditText) findViewById(R.id.editParentN);
        parentContact = (EditText) findViewById(R.id.editParentC);
        parentEmail = (EditText) findViewById(R.id.editParentE);
        dateofbirth = (EditText) findViewById(R.id.editdate);


        user = mAuth.getCurrentUser();

        //to add picture on storage
        if (user != null) {

            if (user.getPhotoUrl() != null) {
                String parent_url = user.getPhotoUrl().toString();
                displayProfilePic(parent_url);
            }

            //LearnerProfile parentProfile =
            parentEmail.setText(user.getEmail());



            final DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Admin").child(user.getUid());
            database.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.i("Ygritte", "Parent : " + dataSnapshot.toString());
                    if (dataSnapshot.getValue() != null) {

                        ParentProfile parentProfile = dataSnapshot.getValue(ParentProfile.class);
                        name.setText(parentProfile.getNames());
                        surname.setText(parentProfile.getSurname());
                        address.setText(parentProfile.getAddress());
                        parentContact.setText(parentProfile.getContact_number());
                        dateofbirth.setText(parentProfile.getDate_of_birth());

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        } else {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
        }
        upload = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "I was clicked", Toast.LENGTH_LONG).show();
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, ACTION_CODE);
            }
        });


//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addLearners();
//
//                Toast.makeText(RegisterLearner.this, " data is saving", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(RegisterLearner.this, MainActivity.class);
//                startActivity(intent);
//            }
//
//
//        });



    }

    public void displayProfilePic(String imageUrl) {
        //.transform(new CircleTransform(getBaseContext))
        if (!"".equals(imageUrl)) {
            Glide.with(getBaseContext())
                    .load(imageUrl)
                    .asBitmap()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new BitmapImageViewTarget(profilePic) {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
                            super.onResourceReady(bitmap, anim);
                            Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(Palette palette) {
                                    // Here's your generated palette
                                    int bgColor = palette.getMutedColor(getBaseContext().getResources().getColor(android.R.color.darker_gray));
                                    //profileImgPallete.setImageResource(bgColor);
                                }
                            });
                        }
                    });
        }
    }

//to save extra info using current user id

    private void addLearners() {

        if (user != null) {

            nam = name.getText().toString().trim();
            surnam = surname.getText().toString().trim();
            addres = address.getText().toString();
            parentContac = parentContact.getText().toString();
            dateofbirt = dateofbirth.getText().toString();
            String type = "admin";

            ParentProfile parentProfile = new ParentProfile(nam, surnam, parentContac, dateofbirt, addres,type);
            Map<String, Object> parentProfileValues = parentProfile.toMap();
            String userId = user.getUid();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/Admin/" + userId, parentProfileValues);
            mDatabase.updateChildren(childUpdates);

            Toast.makeText(ProfileActivity.this, "data saved ", Toast.LENGTH_LONG).show();
        }

        try {

        } catch (Exception e) {
            e.getMessage();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTION_CODE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            uploadImage();
            //profilePic.setImageURI(imageUri);
        }
    }


    public void uploadImage() {

        //khuthadzo should be replaced by user.getUid();
        StorageReference filePath = storageReference.child("profile_pics").child(user.getUid() + ".jpeg");
        filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String uriImage = taskSnapshot.getDownloadUrl().toString();
                displayProfilePic(uriImage);
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setPhotoUri(taskSnapshot.getDownloadUrl())
                        .build();

                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("Ygritte", "User profile updated.");
                                }
                            }
                        });
            }
        });


    }
  //  BACK ARROW

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId() )
        {
            case R.id.done:
                addLearners();
                Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(intent);

        }
        finish();
        return super.onOptionsItemSelected(item);
    }

    //menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}







