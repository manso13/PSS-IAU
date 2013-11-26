package com.meicm.iplsa;

import java.util.ArrayList;



import java.util.List;
import com.example.iplsa.R;
import com.meicm.iplsa.Classes.FeedNotification;
import com.meicm.iplsa.Classes.HandlerNotifications;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MenuFeedActivity extends Activity {
	
	private ArrayList<FeedNotification> feedRows = new ArrayList<FeedNotification>();
	private  NotificationAdapter notificationAdapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_feed);
				



	    ListView listTweets = (ListView) this.findViewById(R.id.feedListView);
		//Chamar thread para consumir webservices
	    notificationAdapter = new NotificationAdapter(this, R.layout.list_row_notice, feedRows);
	    listTweets.setAdapter(notificationAdapter);
	    
	    new Thread(new Runnable() {
	        @Override
	        public void run() { 
	        	HandlerNotifications handlerNotifications = new HandlerNotifications();
		    	ArrayList<FeedNotification> tweets = new ArrayList<FeedNotification>();
		    	
		    	tweets = handlerNotifications.getLast20NotificationsFromSources();
	               
		    	feedRows.addAll(tweets);
	            
	        handler.sendEmptyMessage(0);
	        }
	    }).start();
	    


		
	}


	@SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		
	    public void handleMessage(android.os.Message msg) {
	        if (msg.what == 0) {
	        	notificationAdapter.notifyDataSetChanged();
	        	runOnUiThread(new Runnable() {
	        	    public void run() {
	        	    	notificationAdapter.notifyDataSetChanged();
	        	    }  
	        	});
	        }
	    };
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_feed, menu);
		return true;
	}
	
	public void configButtonPressed(View v)
	{
//		EditText username= (EditText)findViewById(R.id.editText1);
//		String user = username.getText().toString();
//		
//		EditText password= (EditText)findViewById(R.id.editText2);
//		String pass = password.getText().toString();
//		
		Intent intent = new Intent(this,ConfigOptionsActivity.class);
		startActivity(intent);	
	}
	
	public void listCoursesButtonPressed(View v)
	{
		Intent intent = new Intent(this,MoodleListCoursesActivity.class);
		startActivity(intent);
	}
	
//	public void tweetOnClick(View v)
//	{
//		Intent intent = new Intent(this,TwitterActivity.class);
//		startActivity(intent);
//	}
	
	
	public boolean fillFeed(ArrayList<FeedNotification> feedNotifications)
	{
				
		//TODO
		
		return true;
	}

	
	//FROM http://stackoverflow.com/questions/4238921/android-detect-whether-there-is-an-internet-connection-available
	private boolean isNetworkAvailable() {
	    ConnectivityManager connectivityManager 
	          = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
