package za.co.codetribe.ukukhula.Teacher;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import za.co.codetribe.ukukhula.R;
import za.co.codetribe.ukukhula.learner.LearnerProfile;

public class TeacherAdapter extends ArrayAdapter<TeacherProfile> {

    private Activity context;
    private List<TeacherProfile> profList;

    public TeacherAdapter(Activity context, List<TeacherProfile> profList) {
        super(context, R.layout.activity_profileteacher,profList);
        this.context = context;
        this.profList = profList;
    }



    @Override
    public int getCount()
    {
        return profList.size();
    }


    @Override
    public TeacherProfile getItem(int position)
    {
        return profList.get(position);
    }
    @Override
    public long getItemId(int position)
    {
        return position;
    }




    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // View listItemView = convertView;
        if (convertView ==null)
        {
            convertView=  LayoutInflater.from(context).inflate(R.layout.customprofilelist,parent,false);

        }


        TextView eName=(TextView)convertView.findViewById(R.id.profileName);
        TextView eSurname=(TextView)convertView.findViewById(R.id.profilesurname);
        TextView eDescription=(TextView)convertView.findViewById(R.id.profileDob);
//        ImageView ePic = (ImageView)convertView.findViewById(R.id.pic);
//        TextView eName=(TextView)convertView.findViewById(R.id.eventName);
//        TextView eName=(TextView)convertView.findViewById(R.id.eventName);
//        TextView eName=(TextView)convertView.findViewById(R.id.eventName);
//        TextView eName=(TextView)convertView.findViewById(R.id.eventName);
//        TextView eName=(TextView)convertView.findViewById(R.id.eventName);
//        TextView eName=(TextView)convertView.findViewById(R.id.eventName);
//        TextView eName=(TextView)convertView.findViewById(R.id.eventName);


        final TeacherProfile profList=this.getItem(position);

//        eDescription.setText(profList.getChildsSurname());
//        eName.setText(profList.getChildsAge());
        eDescription.setText(profList.getName());
        eName.setText(profList.getClassName());
        eSurname.setText(profList.getSurname());

//        eName.setText(profList.getChildsAddress());
//        eName.setText(profList.getChildsAllergies());
//        eName.setText(profList.getChildsFavmeal());
//        eName.setText(profList.getChildsPname());
//        eName.setText(profList.getChildsPcontact());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
        return convertView;
    }
}
