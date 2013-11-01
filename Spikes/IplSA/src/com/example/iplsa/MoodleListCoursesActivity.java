package com.example.iplsa;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MoodleListCoursesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moodle_lista_cadeiras);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.moodle_lista_cadeiras, menu);
		return true;
	}

}
