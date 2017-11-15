package za.co.codetribe.ukukhula.AdminProfile;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import za.co.codetribe.ukukhula.MainActivity;
import za.co.codetribe.ukukhula.R;


public class Register extends AppCompatActivity {


    Button registerButton;
    TextView text;
    EditText edtemail;
    EditText edtpassword;

    RadioButton rb;
    Context context;


    private FirebaseAuth firebase;

    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser user;

    String roletpe;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // user = mAuth.getCurrentUser();



        progressDialog = new ProgressDialog(this);

        registerButton = (Button) findViewById(R.id.btnRegister);
        edtemail = (EditText) findViewById(R.id.edtEmail);
        edtpassword = (EditText) findViewById(R.id.edtPassword);
        text = (TextView) findViewById(R.id.txtSign);



        firebase = FirebaseAuth.getInstance();
//get intent Extra
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            roletpe = bundle.getString("Role");
        }
        Log.i("avo" ,roletpe);

    }

    public void save(View view) {


        registerUser();

        //popup dialogne3y




    }

    public void registerUser() {
        String email = edtemail.getText().toString().trim();
        String password = edtpassword.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter your email  ", Toast.LENGTH_LONG).show();

            return;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter your password  ", Toast.LENGTH_LONG).show();
            return;
        }
//        progressDialog.setMessage("Registering user......");
//        progressDialog.show();

        firebase.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();


                        if (task.isSuccessful()) {


                            Toast.makeText(getApplicationContext(), "User successful registered ", Toast.LENGTH_LONG).show();

                            AlertDialog.Builder builder1 = new AlertDialog.Builder(Register.this);
                            builder1.setMessage("please update your personal details now or later?");
                            builder1.setCancelable(true);

                            builder1.setPositiveButton(
                                    "now",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            if(roletpe.equals("Parent")) {
                                                String type="Parent";
                                                Intent intent = new Intent(Register.this, ProfileActivity.class);
                                                intent.putExtra("ROLE",type);
                                                startActivity(intent);
                                            }
                                            else
                                            {
                                                String type="Admin/Teacher";
                                                Intent intent = new Intent(Register.this, ProfileActivity.class);
                                                intent.putExtra("ROLE",type);
                                                startActivity(intent);
                                            }
                                            // dialog.cancel();
                                        }
                                    });

                            builder1.setNegativeButton(
                                    "later",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {

                                            if(roletpe.equals("Parent")) {
                                                String type="Parent";
                                                Intent intent = new Intent(Register.this, MainActivity.class);
                                                intent.putExtra("ROLE",type);
                                                startActivity(intent);
                                            }
                                            else
                                            {
                                                String type="Admin/Teacher";
                                                Intent intent = new Intent(Register.this, MainActivity.class);
                                                intent.putExtra("ROLE",type);
                                                startActivity(intent);
                                            }

                                            //dialog.cancel();
                                        }
                                    });

                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                        } else {
                            Toast.makeText(getApplicationContext(), "User not successful registered...please try again ", Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }







}

