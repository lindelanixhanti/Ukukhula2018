package za.co.codetribe.ukukhula.gallery;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import za.co.codetribe.ukukhula.R;

public class GallaryActivityParent extends AppCompatActivity {

    DatabaseReference databaseReference;
    List<ImagePojo> imgList;
    ListView listView;

    ProgressDialog pd;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallarys);

        listView=(ListView) findViewById(R.id.listImages);

        imgList =new ArrayList<>();

        pd =new ProgressDialog(this);
        pd.setMessage(" please wait ....");
        pd.show();

        databaseReference= FirebaseDatabase.getInstance().getReference("imagess");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pd.dismiss();

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Log.i(" AVIWE",dataSnapshot.toString());
                    ImagePojo imagePojo =(ImagePojo) dataSnapshot1.getValue(ImagePojo.class);
                    imgList.add(imagePojo);

                }





                ImageAdapter adapter = new ImageAdapter(GallaryActivityParent.this, R.layout.activity_gallarylist,imgList);
                listView.setAdapter(adapter);




            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                pd.dismiss();

            }
        });




    }



//    private static final int ACTION_CODE = 1;
//    private StorageReference storageReference;
//    DatabaseReference databaseReference;
//    ImageButton imageBView;
//    EditText imageName;
//    Button btnUploadImage;
//    Uri imageUri;
//
//    public static final String Storage_Path ="image/";
//    public static final String Database_Path ="image";
//
//    public static final int Request_code =1234;
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        finish();
//        return super.onOptionsItemSelected(item);
//
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_gallary);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//
//
//        imageName = (EditText) findViewById(R.id.txtImageName);
//        imageBView = (ImageButton) findViewById(R.id.imgbImage);
//        btnUploadImage = (Button) findViewById(uploadImage);
//
//        databaseReference = FirebaseDatabase.getInstance().getReference();
//
//        storageReference = FirebaseStorage.getInstance().getReference();
//
//        imageBView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                galleryIntent.setType("image/*");
//                startActivityForResult(galleryIntent, ACTION_CODE);
//            }
//        });
//
//        btnUploadImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                uploadImage();
//            }
//        });
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == ACTION_CODE && resultCode == RESULT_OK){
//            imageUri = data.getData();
//            imageBView.setImageURI(imageUri);
//        }
//    }
//
//    public void uploadImage(){
//
//        final String name = imageName.getText().toString().trim();
//
//        if (!TextUtils.isEmpty(name)){
//
//            StorageReference filePath = storageReference.child("imagess").child(imageUri.getLastPathSegment());
//            filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    @SuppressWarnings("VisibleForTests") Uri uriImage = taskSnapshot.getDownloadUrl();
//
//
//                    ImagePojo image = new ImagePojo();
//                    image.setName(name);
//                    image.setUrl(uriImage.toString());
//                    databaseReference.child("image").push().setValue(image);
//
//
//                }
//            });
//
//        }
//
//    }
//
//    public void show(View view)
//    {
//        Intent intent =new Intent(GallaryActivityParent.this,ImageDisplayActivity.class);
//        startActivity(intent);
//    }

}
