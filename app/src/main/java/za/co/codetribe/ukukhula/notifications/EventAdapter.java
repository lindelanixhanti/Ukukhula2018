package za.co.codetribe.ukukhula.notifications;

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


public class EventAdapter extends ArrayAdapter<Event> {



        private Activity context;
        private List<Event> eventList;

        public EventAdapter( Activity context,List<Event> eventList) {
            super(context, R.layout.activity_events,eventList);
            this.context = context;
            this.eventList=eventList;
        }



        @Override
        public int getCount()
        {
            return eventList.size();
        }


        @Override
        public Event getItem(int position)
        {
            return eventList.get(position);
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
                convertView=  LayoutInflater.from(context).inflate(R.layout.activity_customeventlist,parent,false);

            }


            TextView eName=(TextView)convertView.findViewById(R.id.eventName);
            TextView eDescription=(TextView)convertView.findViewById(R.id.eventDescription);
            TextView eDate=(TextView)convertView.findViewById(R.id.datePicer);

            final Event event=this.getItem(position);

            eName.setText(event.getEventName());
            eDescription.setText(event.getEventDiscription());
            eDate.setText(event.getDate());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,event.getEventName(),Toast.LENGTH_LONG).show();
                }
            });

            return convertView;
        }
    }


