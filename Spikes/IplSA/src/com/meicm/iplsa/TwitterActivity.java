package com.meicm.iplsa;

import java.util.ArrayList;

import com.example.iplsa.R;
import com.meicm.iplsa.Classes.HandlerNotifications;
import com.meicm.iplsa.Classes.FeedNotification;
import com.meicm.ipsla.database.IplsaDbAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class TwitterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitter);
		HandlerNotifications handlerNotifications = new HandlerNotifications();
		ArrayList<FeedNotification> tweets = new ArrayList<FeedNotification>();
		tweets = handlerNotifications.getLast20NotificationsFromSources();
		ListView listTweets = (ListView) this.findViewById(R.id.listTweets);
		NotificationAdapter notificationAdapter = new NotificationAdapter(this, R.layout.list_row_notice, tweets);
		listTweets.setAdapter(notificationAdapter);
		
		IplsaDbAdapter dbAdapter = new IplsaDbAdapter(this);
		
		for (FeedNotification feedNotification : tweets) 
		{
			dbAdapter.createNotification(feedNotification);	
		}	
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.twitter, menu);
		return true;
	}

	private class NotificationAdapter extends ArrayAdapter<FeedNotification> {

        private ArrayList<FeedNotification> tweets;

        public NotificationAdapter(Context context, int textViewResourceId, ArrayList<FeedNotification> tweets) {
                super(context, textViewResourceId, tweets);
                this.tweets = tweets;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
                View v = convertView;
                if (v == null) {
                    LayoutInflater vi = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.list_row_notice, null);
                }
                FeedNotification n = tweets.get(position);
                if (n != null) {
                        TextView tt = (TextView) v.findViewById(R.id.toptext);
                        TextView bt = (TextView) v.findViewById(R.id.bottomtext);
                        if (tt != null) {
                              tt.setText("Name: "+n.getSource());                            }
                        if(bt != null){
                              bt.setText("Status: "+ n.getBody());
                        }
                }
                return v;
        }
	}
	
}
