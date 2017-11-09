package za.co.codetribe.ukukhula;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SlaspActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Button school= (Button) findViewById(R.id.school);
        Button parent= (Button) findViewById(R.id.parent);

        school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String role="Admin/Teacher";
                Intent intent= new Intent(SlaspActivity.this,StartActivity.class);
                intent.putExtra("Role",role);
                startActivity(intent);
            }
        });

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String role="Parent";
                Intent intent= new Intent(SlaspActivity.this,StartActivity.class);
                intent.putExtra("Role",role);
                startActivity(intent);
            }
        });
    }
}
