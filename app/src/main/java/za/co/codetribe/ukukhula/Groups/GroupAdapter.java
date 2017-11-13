package za.co.codetribe.ukukhula.Groups;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import za.co.codetribe.ukukhula.R;
import za.co.codetribe.ukukhula.notifications.Event;


public class GroupAdapter extends ArrayAdapter<Group> {



        private Activity context;
        private List<Group> groupList;

        public GroupAdapter(Activity context, List<Group> groupList) {
            super(context, R.layout.activity_classes,groupList);
            this.context = context;
            this.groupList=groupList;
        }



        @Override
        public int getCount()
        {
            return groupList.size();
        }


        @Override
        public Group getItem(int position)
        {
            return groupList.get(position);
        }
        @Override
        public long getItemId(int position)
        {
            return position;
        }




        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView ==null)
            {
                convertView=  LayoutInflater.from(context).inflate(R.layout.activity_customgrouptlist,parent,false);

            }


            TextView eName=(TextView)convertView.findViewById(R.id.eventName);
            TextView eDescription=(TextView)convertView.findViewById(R.id.eventDescription);
            //TextView eDate=(TextView)convertView.findViewById(R.id.eventDate);

            final Group group=this.getItem(position);

            eName.setText(group.getGroupNane());
            eDescription.setText(group.getGroupTeacher());
           // eDate.setText(event.getDate());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,group.getGroupNane(),Toast.LENGTH_LONG).show();
                }
            });

            return convertView;
        }
    }


