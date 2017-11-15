package za.co.codetribe.ukukhula;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Set;

import za.co.codetribe.ukukhula.AdminProfile.Register;

import static android.R.attr.value;
import static android.os.Build.VERSION_CODES.M;


public class StartActivity extends AppCompatActivity {

    TextView text;
    EditText edtemail;
    EditText edtpassword;

    ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    String rolotype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Toast.makeText(getApplicationContext(), "Aviwe modise", Toast.LENGTH_LONG).show();

        progressDialog = new ProgressDialog(this);

        edtemail = (EditText) findViewById(R.id.edtEmail);
        edtpassword = (EditText) findViewById(R.id.edtPassword);
        text = (TextView) findViewById(R.id.txtSign);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener =new FirebaseAuth.AuthStateListener() {
            @Override
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

               FirebaseUser user= firebaseAuth.getCurrentUser();
//                if(user!=null)
//               {
//                    finish();
//                    Intent moveToHoe=new Intent(MainActivity.this,HomeActivity.class);
//                   startActivity(moveToHoe);
//                }
           }
       };




//
//        String value = "Hello World!";
//        Intent intent = new Intent(getApplicationContext(), NewActivity.class);
//        intent.putExtra("sample_name", value);
//        startActivity(intent);
//        Get Data

       // Intent intent = getIntent();
        //String role = intent.getDataString("Role");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            rolotype = bundle.getString("Role");
        }
        Toast.makeText(getApplicationContext(), "Role "+ rolotype, Toast.LENGTH_LONG).show();
       Log.i("avo" ,rolotype);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "Aviwe modise", Toast.LENGTH_LONG).show();
        if (mAuthListener != null) {
            mAuth.addAuthStateListener(mAuthListener);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    public void login(View view) {
        loginUser();
    }

    public void loginUser() {
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
        Toast.makeText(getApplicationContext(), "i am succesful " , Toast.LENGTH_LONG).show();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                           // Toast.makeText(getApplicationContext(), "i am succesful " , Toast.LENGTH_LONG).show();

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "User not successful registered...please try again ", Toast.LENGTH_LONG).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error " + e.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }


    public void register(View view) {


        if (rolotype.equals("Parent")) {
            String role="Parent";
            Intent intent = new Intent(StartActivity.this, Register.class);
            intent.putExtra("Role", role);
            startActivity(intent);
        }else
        {
            String role="Admin/Teacher";
            Intent intent = new Intent(StartActivity.this, Register.class);
            intent.putExtra("Role", role);
            startActivity(intent);
        }

    }

    public void passwordForget(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
    public void loginAdmin(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
