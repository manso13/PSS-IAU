package com.example.iplsa;

import java.util.ArrayList;

import com.example.iplsa.Classes.HandlerTwitter;
import com.example.iplsa.Classes.Notice;

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
		HandlerTwitter handlerTwitter = new HandlerTwitter();
		ArrayList<Notice> tweets = new ArrayList<Notice>();
		tweets = handlerTwitter.getLast20TweetsFromSources();
		ListView listTweets = (ListView) this.findViewById(R.id.listTweets);
		TweetAdapter tweetAdapter = new TweetAdapter(this, R.layout.list_row_notice, tweets);
		listTweets.setAdapter(tweetAdapter);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.twitter, menu);
		return true;
	}

	private class TweetAdapter extends ArrayAdapter<Notice> {

        private ArrayList<Notice> tweets;

        public TweetAdapter(Context context, int textViewResourceId, ArrayList<Notice> tweets) {
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
                Notice n = tweets.get(position);
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
