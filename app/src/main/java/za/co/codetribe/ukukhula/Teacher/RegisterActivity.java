package za.co.codetribe.ukukhula.Teacher;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


import za.co.codetribe.ukukhula.Groups.Group;

import za.co.codetribe.ukukhula.R;


public class RegisterActivity extends AppCompatActivity {

    //Variebles
    private EditText Name, Surname, Email, password, Address, Contacts, Qualifications,IdNumber;
    private Spinner className;
    Spinner Gender;

    String userID;

    //add firebase data stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    private DatabaseReference mRef, mGroupRef;
    private ProgressDialog progressDialog;
    private StorageReference mStorageRef;
    private static final int GALLERY_INTENT = 2;
    private FirebaseAuth.AuthStateListener mAuthListener;
    String useremail, userpassword, gender;

    //group names
    ListView listview;
    List<String> groupList;
    List<String> groupIdsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerteachers);


        // Name,Surname,Email,password,Address,Contacts,Qualifications,gender
        Name = (EditText) findViewById(R.id.editname);
        Surname = (EditText) findViewById(R.id.editsurname);
        Gender = (Spinner) findViewById(R.id.editgender);
        Address = (EditText) findViewById(R.id.editaddress);
        Contacts = (EditText) findViewById(R.id.editParentC);
        IdNumber = (EditText) findViewById(R.id.editIdNumber);
        Qualifications = (EditText) findViewById(R.id.editQualifications);
        Email = (EditText) findViewById(R.id.editemail);
        password = (EditText) findViewById(R.id.editpassword);

        className = (Spinner) findViewById(R.id.className);

//spinner gender

        ArrayAdapter<CharSequence> adapter=  ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_list_item_1);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        Gender.setAdapter(adapter);





        progressDialog = new ProgressDialog(this);

        //initializing firebase instances
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mRef = mFirebaseDatabase.getReference().child("Teachers");
        //spinner
        mGroupRef = mFirebaseDatabase.getReference().child("Group");

        mGroupRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                groupList = new ArrayList<>();
                groupIdsList = new ArrayList<>();
                Log.i(" f=====================", dataSnapshot.toString());
                groupList.add("Select Creche Name");
                for (DataSnapshot groupsSnapshot : dataSnapshot.getChildren()) {
                    Log.i(" AVIWE", groupsSnapshot.toString());
                    Group group = groupsSnapshot.getValue(Group.class);
                    groupList.add(group.getGroupNane());
                    groupIdsList.add(groupsSnapshot.getKey());

                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this, android.R.layout.simple_list_item_1, groupList);
                className.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


//        image1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*" );
//                startActivityForResult(intent,GALLERY_INTENT);
//
//            }
//        });

        //dialog
//        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
//        builder1.setMessage("Do you want to sign up as? ");
//        builder1.setTitle("Registration");
//        builder1.setCancelable(true);
//
//        builder1.setNegativeButton(
//                "Client",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        onStart();

//                        edtName.setVisibility(View.VISIBLE);
//                        edtSurname.setVisibility(View.VISIBLE);
//                        female.setVisibility(View.VISIBLE);
//                        male.setVisibility(View.VISIBLE);
//                        edtCompanyName.setVisibility(View.INVISIBLE);
//                        editReg.setVisibility(View.INVISIBLE);
//                        edtQuantity.setVisibility(View.INVISIBLE);
//
//
//                    }
//
//                });
//
//        builder1.setNeutralButton("Sponsor",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//
//                        edtCompanyName.setVisibility(View.VISIBLE);
//                        edtQuantity.setVisibility(View.VISIBLE);
//                        editReg.setVisibility(View.VISIBLE);
//                        edtName.setVisibility(View.INVISIBLE);
//                        edtSurname.setVisibility(View.INVISIBLE);
//                        female.setVisibility(View.INVISIBLE);
//                        male.setVisibility(View.INVISIBLE);
//                        image1.setVisibility(View.INVISIBLE);
//
//                        // startActivity(new Intent(RegisterActivity.this, UpdateClientProfileActivity.class));
//
//                    }
//                });

//        AlertDialog alert11 = builder1.create();
//        alert11.show();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//
//
//                    // Name,Surname,Email,password,Address,Contacts,Qualifications, Gender,ClassName,IdNumber
//                    String name = Name.getText().toString();
//                    String surname = Surname.getText().toString();
//                    String address = Address.getText().toString();
//                    String contacts = Contacts.getText().toString();
//                    String passwords = password.getText().toString();
//                    String email = Email.getText().toString();
//                    String className = ClassName.getText().toString();
//                    String idNumber = IdNumber.getText().toString();
//                    String qualifications = Qualifications.getText().toString();
//                    String gender=Gender.getText().toString();
//
//
//                    userID = user.getUid();
//                    if (!name.equals("") & !surname.equals("")) {
//                        TeacherProfile teacherProfile = new TeacherProfile(name, surname,email,passwords,address,contacts,qualifications, gender,className,idNumber);
//                        //Log.v("dkjvdsjk", uinfo.getUserName().toString());
//
//                        mRef.child(userID).setValue(teacherProfile, new DatabaseReference.CompletionListener() {
//                            @Override
//                            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                                if (databaseError != null) {
//                                    Toast.makeText(RegisterActivity.this, "Data could not be saved " + databaseError.getMessage(),Toast.LENGTH_LONG).show();
//                                } else {
//                                    System.out.println("Data saved successfully.");
//                                    Toast.makeText(RegisterActivity.this, "Data saved successfully.",Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        });
//                        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
//                        progressDialog.dismiss();
//                    }
//                }
//            }
//        };
//
//
//        mStorageRef= FirebaseStorage.getInstance().getReference();
//
//
//
//
//
//    }
//    @Override
//    protected void onActivityResult(int requestCode,int resultCode,Intent data)
//    {
//        FirebaseUser user = mAuth.getCurrentUser();
//        if(requestCode==GALLERY_INTENT&& resultCode==RESULT_OK) {
//
//            progressDialog.setMessage("Uploading image...");
//            progressDialog.show();
//            Uri uri=data.getData();
//            StorageReference filepath=mStorageRef.child("Photo").child(uri.getLastPathSegment());
//            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    progressDialog.dismiss();
//                    Toast.makeText(RegisterActivity.this,"Uploading done",Toast.LENGTH_LONG).show();
//                    Uri downloadUri=taskSnapshot.getDownloadUrl();
//                    Picasso.with(RegisterActivity.this).load(downloadUri).fit().centerCrop().into(image1);
//                }
//            });
//        }
//    }
    }

    public void save(View view) {

        // Stores email and password entered
        final String email = Email.getText().toString().trim();
        final String passwords = password.getText().toString().trim();

//        if (TextUtils.isEmpty(email)) {
//            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (TextUtils.isEmpty(passwords)) {
//            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
//            return;
//        }

//        if (userpassword.length() < 6) {
//            Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
//            return;
//        }

//        if(male.isChecked())
//        {
//            gender = "Male";
//        }else if(female.isChecked()){
//            gender = "Female";
//        }

//        progressDialog.setMessage("Registering user please wait....");
//        progressDialog.show();
        //create user
        mAuth.createUserWithEmailAndPassword(email, passwords)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(RegisterActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        // progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "am after log.", Toast.LENGTH_LONG).show();
                            String name = Name.getText().toString();
                            String surname = Surname.getText().toString();
                            String address = Address.getText().toString();
                            String contacts = Contacts.getText().toString();
                            String passwords = password.getText().toString();
                            String email = Email.getText().toString();

                            String idNumber = IdNumber.getText().toString();
                            String qualifications = Qualifications.getText().toString();
                            String gender = Gender.getSelectedItem().toString();

                            if (className.getSelectedItemPosition() == 0) {
                                // Do not allow to save
                            }
                            String class_name = groupIdsList.get(className.getSelectedItemPosition());
                            //String className = ClassName.getText().toString();


                            Log.i("Dimples", class_name);
                            Toast.makeText(RegisterActivity.this, "after log." + name, Toast.LENGTH_LONG).show();


                            TeacherProfile uinfo = new TeacherProfile(name, surname, email, passwords, address, contacts, qualifications, gender, class_name, idNumber);
                            //String userid = user.getUid();
                            String userid = task.getResult().getUser().getUid();
                            mRef.child(userid).setValue(uinfo.toMap(), new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                    if (databaseError != null) {
                                        Toast.makeText(RegisterActivity.this, "Data could not be saved " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                                    } else {
                                        System.out.println("Data saved successfully.");
                                        Toast.makeText(RegisterActivity.this, "Data saved successfully.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            startActivity(new Intent(RegisterActivity.this, TeacherActivity.class));
                            finish();
                            // progressDialog.dismiss();
                        }


                    }
                });
    }
}

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId() )
//        {
//            case R.id.add:
//                Intent intent = new Intent(RegisterActivity.this,RegisterActivity.class);
//                startActivity(intent);
//
//        }
//        finish();
//        return super.onOptionsItemSelected(item);
//    }
//
//    //menu
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (mAuthListener != null) {
//            mAuth.removeAuthStateListener(mAuthListener);
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if (mAuthListener != null) {
//            mAuth.addAuthStateListener(mAuthListener);
//        }
//    }
