package com.meicm.iplsa;

import java.util.ArrayList;
import java.util.List;






import com.example.iplsa.R;
import com.meicm.iplsa.Classes.WebServiceClient;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MoodleListCoursesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moodle_list_courses);
		ArrayList<String> listLines = new ArrayList<String>();
		ListView listCourses = (ListView) this.findViewById(R.id.cadeirasMoodleListView);
		//TODO
		//Declarar a lista de cadeiras
		WebServiceClient webClient = new WebServiceClient(WebServiceClient.METHOD_IS_WEBSERVICE_ENABLED, "", "");
		
		try {
			webClient.connect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			listLines.add(e.toString());
			e.printStackTrace();
		}
		//Conectar ao webservice
		
		//Chamar a função que devolve as cadeiras
		
		//Editar o recebido para ficar conforme o tipo de ROW da lista
		
    	listLines.add("We require additional pylons");
    	listCourses.setAdapter(new ArrayAdapter<String>(this, R.layout.list_row_with_image, listLines));
        //final ListView lv=  listCourses.getListView();
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.moodle_lista_cadeiras, menu);
		return true;
	}
	

}
