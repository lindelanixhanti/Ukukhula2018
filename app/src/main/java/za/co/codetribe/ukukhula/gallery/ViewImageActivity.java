package za.co.codetribe.ukukhula.gallery;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import za.co.codetribe.ukukhula.R;

public class ViewImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);

        ImageView imageView = (ImageView) findViewById(R.id.image_view1);
        TextView textView = (TextView) findViewById(R.id.image_desc);

        Intent intent = getIntent();
        ImagePojo imagePojo = (ImagePojo) intent.getSerializableExtra("imagePojo");
//        imageView.setImageURI();
        Picasso.with(ViewImageActivity.this).load(Uri.parse(imagePojo.getUrl())).into(imageView);
        textView.setText(imagePojo.getName());
    }
}




