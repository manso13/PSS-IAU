package com.meicm.iplsa;

import java.util.ArrayList;



import com.example.iplsa.R;
import com.meicm.iplsa.Classes.RowItem;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MenuFeedActivity extends Activity {

	private Runnable getInfoFromWebservices;
	
	private ArrayList<RowItem> feedRows = new ArrayList<RowItem>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_feed);
		
		//Chamar thread para consumir webservices
		
		fillFeed();
		
	}

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
	
	public void tweetOnClick(View v)
	{
		Intent intent = new Intent(this,TwitterActivity.class);
		startActivity(intent);
	}
	
	
	public boolean fillFeed()
	{
				
		//TODO
		
		return true;
	}

}
