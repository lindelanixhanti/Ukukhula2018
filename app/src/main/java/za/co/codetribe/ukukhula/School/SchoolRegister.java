package za.co.codetribe.ukukhula.School;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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
import za.co.codetribe.ukukhula.AdminProfile.ProfileActivity;
import za.co.codetribe.ukukhula.MainActivity;
import za.co.codetribe.ukukhula.R;

import za.co.codetribe.ukukhula.StartActivity;


public class SchoolRegister extends AppCompatActivity {


    String yea, school_nam, school_addr, school_manage, contact_detail, email_addres;

    EditText year;
    EditText school_name;
    EditText school_address;
    //= (EditText) findViewById(R.id.school_address);
    EditText school_manager;
    EditText contact_details;
    EditText email_address;
    Button save;
    CircleImageView profilePic;
    ImageView profileImgPallete;
    Button view;

    //authntification fields

    DatabaseReference roofdef, demodef, mDatabase, userDatabase;
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseAuth.AuthStateListener mAuthListerner;

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
        setContentView(R.layout.schoolregister);
        // Toast.makeText( getBaseContext(), "I was clicked", Toast.LENGTH_LONG ).show();
        //BACK ARROW
        //getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        //assign the firebase auth instaances

        mAuth = FirebaseAuth.getInstance();

        mAuthListerner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                } else {
                    finish();
                    Intent intent = new Intent(SchoolRegister.this, StartActivity.class);
                    startActivity(intent);
                }
            }
        };


        //ASSIGN FIREBASE DATABASE INSTANCES
        storageReference = FirebaseStorage.getInstance().getReference();
        //userDatabase = FirebaseDatabase.getInstance().getReference().child( "Users" ).child(  );

        mDatabase = FirebaseDatabase.getInstance().getReference();

        //IMAGES
        profilePic = (CircleImageView) findViewById(R.id.imageButton);
        //upload = (FloatingActionButton) findViewById( R.id.floatingActionButton );


        //ADDING  ID SaveData
        Button save = (Button) findViewById(R.id.saveData);
        // view = (Button) findViewById(R.id.view);

        profilePic = (CircleImageView) findViewById(R.id.imageButton);
        //  profileImgPallete = (ImageView) findViewById(R.id.image_palette);

        year = (EditText) findViewById(R.id.year);
        school_name = (EditText) findViewById(R.id.school_name);
        school_address = (EditText) findViewById(R.id.school_address);
        school_manager = (EditText) findViewById(R.id.school_manager);
        contact_details = (EditText) findViewById(R.id.contact_details);
        email_address = (EditText) findViewById(R.id.emal_address);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("SchoolRegister");
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("school", "Parent : " + dataSnapshot.toString());
                if (dataSnapshot.getValue() != null) {


                    School school = dataSnapshot.getValue(School.class);
                    //ParentProfile parentProfile = dataSnapshot.getValue( ParentProfile.class );
                    year.setText(school.getYear());
                    school_name.setText(school.getSchoolname());
                    school_address.setText(school.getSchooladdress());
                    school_manager.setText(school.getSchoolmanager());
                    contact_details.setText(school.getContactdetails());
                    email_address.setText(school.getEmailaddress());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        /*upload = findViewById( R.id.floatingActionButton );
        upload.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( getBaseContext(), "I was clicked", Toast.LENGTH_LONG ).show();
                Intent galleryIntent = new Intent( Intent.ACTION_GET_CONTENT );
                galleryIntent.setType( "image/*" );
                startActivityForResult( galleryIntent, ACTION_CODE );
            }
        } );
        */
        save =(Button) findViewById( R.id.save);

        save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLearners();


                Intent intent;
                intent = new Intent( SchoolRegister.this, MainActivity.class );
                startActivity( intent );
                Toast.makeText( SchoolRegister.this, " data is saving", Toast.LENGTH_LONG ).show();
            }


        } );

       /* view.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( SchoolRegister.this, ProfileActivity.class );
                startActivity( intent );
            }
        });
        */
    }

    private static int year() {
        return 0;
    }

    static void getcontact_details() {
    }

//    public void displayProfilePic(String imageUrl) {
//        //.transform(new CircleTransform(getBaseContext))
//        if (!"".equals( imageUrl )) {
//            Glide.with( getBaseContext() )
//                    .load( imageUrl )
//                    .asBitmap()
//                    .diskCacheStrategy( DiskCacheStrategy.ALL )
//                    .into( new BitmapImageViewTarget( profilePic ) {
//                        @Override
//                        public void onResourceReady(Bitmap bitmap, GlideAnimation anim) {
//                            super.onResourceReady( bitmap, anim );
//                            Palette.generateAsync( bitmap, new Palette.PaletteAsyncListener() {
//                                @Override
//                                public void onGenerated(Palette palette) {
//                                    // Here's your generated palette
//                                    int bgColor = palette.getMutedColor( getBaseContext().getResources().getColor( android.R.color.darker_gray ) );
//                                    //profileImgPallete.setImageResource(bgColor);
//                                }
//                            } );
//                        }
//                    } );
//        }
//    }
//PROFILE\
    /*
    public  void saveProfile() {
        final String nam, surnam, addres, gende, parentNam, parentContac, dateofbirt;

        nam = name.getText().toString().trim();
        surnam = surname.getText().toString().trim();
        addres = address.getText().toString();
        gende = gender.getText().toString();
        parentNam = parentName.getText().toString();
        parentContac = parentContact.getText().toString();
        dateofbirt = dateofbirth.getText().toString();

        if (!TextUtils.isEmpty((nam)) && !TextUtils.isEmpty((surnam)) && !TextUtils.isEmpty((addres)) && !TextUtils.isEmpty((gende)) && !TextUtils.isEmpty((parentNam)) && !TextUtils.isEmpty((parentContac)) && !TextUtils.isEmpty((dateofbirt))) {
            if (imageUri != null) {
                StorageReference mChildstorage = mStorageRef.child(("user_profile")).child(imageUri.getLastPathSegment());
                String profileUri = imageUri.getLastPathSegment();


                mChildstorage.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        final Uri uri=taskSnapshot.getDownloadUrl();

                        userDatabase.child("name").setValue(nam);
                        userDatabase.child("surname").setValue(surnam);

                        userDatabase.child("adress").setValue(addres);

                        userDatabase.child("gender").setValue(gende);

                        userDatabase.child("parentName").setValue(parentNam);

                        userDatabase.child("parentContact").setValue(dateofbirt);

                        userDatabase.child("userid").setValue(mAuth.getCurrentUser().getUid());
                        userDatabase.child("imsageUri").setValue(imageUri.toString());



                    }
                });

            }

        }
    }
*/
//to save extra info using current user id

    private void addLearners() {



            yea = year.getText().toString().trim();
            school_nam = school_name.getText().toString().trim();
            school_addr = school_address.getText().toString();
            school_manage = school_manager.getText().toString();
            contact_detail = contact_details.getText().toString();
            email_addres = email_address.getText().toString();

            School scchoolR = new School( yea, school_nam, school_addr, school_manage, contact_detail, email_addres );
            Map<String, Object> scchoolRValues = scchoolR.toMap();

            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put( "/SchoolRegister/" ,scchoolRValues );
            mDatabase.updateChildren( childUpdates );

            Toast.makeText( SchoolRegister.this, "data saved ", Toast.LENGTH_LONG ).show();
        }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult( requestCode, resultCode, data );

        if (requestCode == ACTION_CODE && resultCode == RESULT_OK) {
            imageUri = data.getData();
            uploadImage();
            //profilePic.setImageURI(imageUri);
        }
    }


    public void uploadImage() {

        //khuthadzo should be replaced by user.getUid();
        StorageReference filePath = storageReference.child( "profile_pics" ).child( user.getUid() + ".jpeg" );
        filePath.putFile( imageUri ).addOnSuccessListener( new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String uriImage = taskSnapshot.getDownloadUrl().toString();
//                displayProfilePic( uriImage );
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setPhotoUri( taskSnapshot.getDownloadUrl() )
                        .build();

                user.updateProfile( profileUpdates )
                        .addOnCompleteListener( new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d( "Ygritte", "User profile updated." );
                                }
                            }
                        } );
            }
        } );


    }

    //  BACK ARROW

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId() )
        {
            case R.id.done:
                Toast.makeText( SchoolRegister.this, "add is press ", Toast.LENGTH_LONG ).show();
                addLearners();
                Intent intent = new Intent(SchoolRegister.this,MainActivity.class);
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







