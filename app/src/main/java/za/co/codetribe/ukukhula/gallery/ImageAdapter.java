package za.co.codetribe.ukukhula.gallery;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import za.co.codetribe.ukukhula.R;

public class ImageAdapter extends ArrayAdapter<ImagePojo> {


    private Activity context;
    private List<ImagePojo> imageList;
    int resource;

    public ImageAdapter(Activity context, int resource, List<ImagePojo> imageList) {
        super(context, resource, imageList);
        this.context = context;
        this.imageList = imageList;
        this.resource = resource;
    }


    @Override
    public int getCount() {
        return imageList.size();
    }


    @Override
    public ImagePojo getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//            if (convertView ==null)
//            {
        //convertView=  LayoutInflater.from(context).inflate(R.layout.activity_customeventlist,parent,false);
        // convertView=  LayoutInflater.from(context).inflate(resource,parent,false);


//            LayoutInflater layoutInflater=context.getLayoutInflater();
//            View v=layoutInflater.inflate(resource,null);
        convertView = LayoutInflater.from(context).inflate(R.layout.imageitems, parent, false);


        ImageView images = (ImageView) convertView.findViewById(R.id.images);
        TextView imageDescription = (TextView) convertView.findViewById(R.id.txtImageNane);

        final ImagePojo imagePojo = this.getItem(position);


        Glide.with(context).load(imageList.get(position).getUrl()).into(images);
        imageDescription.setText(imagePojo.getName());

//
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, imagePojo.getName(), Toast.LENGTH_LONG).show();
//            }
//        });

        return convertView;
    }
}


