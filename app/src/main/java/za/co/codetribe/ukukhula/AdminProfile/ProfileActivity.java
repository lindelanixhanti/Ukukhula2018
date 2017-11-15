package za.co.codetribe.ukukhula.AdminProfile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import za.co.codetribe.ukukhula.MainActivity;
import za.co.codetribe.ukukhula.R;
import za.co.codetribe.ukukhula.User;
import za.co.codetribe.ukukhula.notifications.Eventhelper;

import static android.R.attr.value;


public class ProfileActivity extends AppCompatActivity {

    EditText name, surname, address, gender, contacts, parentEmail ,type,qualification;
    TextView dateSelected,dateofbirth;
    String nam, surnam, addres, gende, types, contact, dateSelecte, email,qualificatio;
    Button save;
    CircleImageView profilePic;
    ImageView profileImgPallete;
    Button view;
    String value;

    //authntification fields

    DatabaseReference mDatabase;
    FirebaseAuth mAuth;
    FirebaseUser user;
    // FirebaseAuth.AuthStateListener mAuthListerner;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

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
        type = (EditText) findViewById(R.id.editRole);
        contacts = (EditText) findViewById(R.id.editContacts);
        parentEmail = (EditText) findViewById(R.id.editemail);
        dateSelected= (TextView) findViewById(R.id.editdate);
        gender=(EditText) findViewById(R.id.editgender);
        qualification=(EditText) findViewById(R.id.editQualifications);

        //get intent
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            value = bundle.getString("ROLE");
        }


//to select the date
        dateSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar= Calendar.getInstance();
                int year=calendar.get(Calendar.YEAR);
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                int month=calendar.get(Calendar.MONTH);

                DatePickerDialog dialog= new DatePickerDialog(ProfileActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable((Color.TRANSPARENT)));
                dialog.show();


            }
        });
        mDateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                month=month+1;
                String selected= month + "/" + date +"  " + year;

          dateSelected.setText(selected);
            }
        };


        user = mAuth.getCurrentUser();

        //to add picture on storage
        if (user != null) {

            if (user.getPhotoUrl() != null) {
                String parent_url = user.getPhotoUrl().toString();
                displayProfilePic(parent_url);
            }

            //LearnerProfile parentProfile =
            parentEmail.setText(user.getEmail());
            type.setText(value);



            final DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
            database.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.i("Ygritte", "Parent : " + dataSnapshot.toString());
                    if (dataSnapshot.getValue() != null) {

                        //ParentProfile parentProfile = dataSnapshot.getValue(ParentProfile.class);
                        User user = dataSnapshot.getValue(User.class);
                        // address,className,contacts,email,gender,id_number,name,password,qualifications,surname,user_role
                        name.setText((user.getName()));
                        surname.setText(user.getSurname());
                        address.setText(user.getAddress());
                        contacts.setText(user.getContacts());
                        gender.setText(user.getGender());
                        qualification.setText(user.getQualifications());
                        dateSelected.setText(user.getDateofbirth());
                        type.setText(value);


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
            contact = contacts.getText().toString();
            gende = gender.getText().toString();
            dateSelecte = dateSelected.getText().toString();
            qualificatio = qualification.getText().toString();
            type.getText().toString();



                            //address,contacts,email,gender,name,dateofbirth,qualifications,surname,user_role
            User users = new User( addres,contact,email,gende,nam,dateSelecte,qualificatio,surnam,value);
            Map<String, Object> parentProfileValues = users.toMap();
            String userId = user.getUid();
            Map<String, Object> childUpdates = new HashMap<>();
            childUpdates.put("/users/" + userId, parentProfileValues);
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







