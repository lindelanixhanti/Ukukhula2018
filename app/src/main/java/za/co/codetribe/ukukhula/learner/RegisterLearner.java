package za.co.codetribe.ukukhula.learner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import de.hdodenhof.circleimageview.CircleImageView;
import za.co.codetribe.ukukhula.MainActivity;
import za.co.codetribe.ukukhula.R;


public class RegisterLearner extends AppCompatActivity {

    EditText names, surname, allegies, date_of_birth, parentName, contacts, gender, className, favouriteMeal;
    String name, surnam, allegie, dateofbirth, parentNam, contact, gende, classNam, favouriteMea;

    CircleImageView profilePic;
    ImageView profileImgPallete;


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
        setContentView(R.layout.kidsprofile);

        Toast.makeText(getBaseContext(), "I was clicked", Toast.LENGTH_LONG).show();
        //BACK ARROW


        //assign the firebase auth instaances

//        mAuth = FirebaseAuth.getInstance();
//        storageReference = FirebaseStorage.getInstance().getReference();

        mDatabase = FirebaseDatabase.getInstance().getReference();
//


        //ASSING  ID S

        Button save = (Button) findViewById(R.id.save);

        //profilePic = (CircleImageView) findViewById(R.id.imageButton);
        //  profileImgPallete = (ImageView) findViewById(R.id.image_palette);

        names = (EditText) findViewById(R.id.editname);
        surname = (EditText) findViewById(R.id.editsurname);
        allegies = (EditText) findViewById(R.id.editallergies);
        gender = (EditText) findViewById(R.id.editgender);
        parentName = (EditText) findViewById(R.id.editparentName);
        contacts = (EditText) findViewById(R.id.editContacts);
        className = (EditText) findViewById(R.id.editClassName);
        date_of_birth = (EditText) findViewById(R.id.editdate);
        favouriteMeal = (EditText) findViewById(R.id.editfavmeal);


//        user = mAuth.getCurrentUser();
//
//        //to add picture on storage
//        if (user != null) {
//
//            if (user.getPhotoUrl() != null) {
//                String parent_url = user.getPhotoUrl().toString();
//                displayProfilePic(parent_url);
//            }

            //LearnerProfile parentProfile =



        mDatabase = FirebaseDatabase.getInstance().getReference().child("Children");
//            database.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    Log.i("Ygritte", "Parent : " + dataSnapshot.toString());
//                    if (dataSnapshot.getValue() != null) {
//
//                        LearnerProfile parentProfile = dataSnapshot.getValue(LearnerProfile.class);
//                        names.setText(parentProfile.getNames());
//                        surname.setText(parentProfile.getSurname());
//                        allegies.setText(parentProfile.getAllegies());
//                        parentName.setText(parentProfile.getParentName());
//                        date_of_birth.setText(parentProfile.getDate_of_birth());
//                        gender.setText(parentProfile.getGender());
//                        favouriteMeal.setText(parentProfile.getFavouriteMeal());
//                        className.setText(parentProfile.getClassName());
//                        contacts.setText(parentProfile.getContacts());



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLearners();

                Toast.makeText(RegisterLearner.this, " data is saving", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterLearner.this, LearnrsActivity.class);
                startActivity(intent);
            }


        });


    }


//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });
//
//        }

//
//        upload = (FloatingActionButton) findViewById(R.id.floatingActionButton);
//        upload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getBaseContext(), "I was clicked", Toast.LENGTH_LONG).show();
//                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                galleryIntent.setType("image/*");
//                startActivityForResult(galleryIntent, ACTION_CODE);
//            }
//        });





//
//    public void displayProfilePic(String imageUrl) {
//        //.transform(new CircleTransform(getBaseContext))
//        if (!"".equals(imageUrl)) {
//            Glide.with(getBaseContext())
//                    .load(imageUrl)
//                    .asBitmap()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(new BitmapImageViewTarget(profilePic) {
//                        @Override
//                        public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
//                            super.onResourceReady(bitmap, anim);
//                            Palette.generateAsync(bitmap, new Palette.PaletteAsyncListener() {
//                                @Override
//                                public void onGenerated(Palette palette) {
//                                    // Here's your generated palette
//                                    int bgColor = palette.getMutedColor(getBaseContext().getResources().getColor(android.R.color.darker_gray));
//                                    //profileImgPallete.setImageResource(bgColor);
//                                }
//                            });
//                        }
//                    });
//        }
//    }

//to save extra info using current user id

    private void addLearners() {


        name = names.getText().toString().trim();
        surnam = surname.getText().toString().trim();
        allegie = allegies.getText().toString();
        parentNam = parentName.getText().toString();
        dateofbirth = date_of_birth.getText().toString();
        contact = contacts.getText().toString();
        gende = gender.getText().toString();
        classNam = className.getText().toString();
        favouriteMea = favouriteMeal.getText().toString();


        LearnerProfile parentProfile = new LearnerProfile(name, surnam, allegie, dateofbirth, parentNam, contact, gende, classNam, favouriteMea);
//        Map<String, Object> parentProfileValues = parentProfile.toMap();
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/Children/", parentProfileValues);
//        mDatabase.push().setValue(childUpdates);
//        mDatabase.updateChildren(childUpdates);



        mDatabase.push().setValue(parentProfile);

        Toast.makeText(RegisterLearner.this, "data saved ", Toast.LENGTH_LONG).show();
    }


    //foto

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == ACTION_CODE && resultCode == RESULT_OK) {
//            imageUri = data.getData();
//            uploadImage();
//            //profilePic.setImageURI(imageUri);
//        }
//    }

//
//    public void uploadImage() {
//
//        //khuthadzo should be replaced by user.getUid();
//        StorageReference filePath = storageReference.child("profile_pics").child(user.getUid() + ".jpeg");
//        filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                String uriImage = taskSnapshot.getDownloadUrl().toString();
//                displayProfilePic(uriImage);
//                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
//                        .setPhotoUri(taskSnapshot.getDownloadUrl())
//                        .build();
//
//                user.updateProfile(profileUpdates)
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                if (task.isSuccessful()) {
//                                    Log.d("Ygritte", "User profile updated.");
//                                }
//                            }
//                        });
//            }
//        });
//
//
//    }
  //  BACK ARROW

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId() )
        {
            case R.id.done:
                addLearners();
                Intent intent = new Intent(RegisterLearner.this,MainActivity.class);
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

    //retreive

}







