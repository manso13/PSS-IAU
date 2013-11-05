package com.example.iplsa;

import java.util.ArrayList;
import java.util.List;


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
	
	//CODIGO ESOFT 2
//	 protected void onCreate(Bundle savedInstanceState) 
//	    {
//	        super.onCreate(savedInstanceState);
//	        WebServiceClient client = new WebServiceClient(WebServiceClient.METHOD_AUTHENTICATE,
//	        		MobileSMInicioImplementacaoBDActivity.user,MobileSMInicioImplementacaoBDActivity.password);
//	        setContentView(R.layout.tickets_list);
//	        ArrayList<String> listLines = new ArrayList<String>();
//	        Bundle extras = getIntent().getExtras();
//	        List<Comment> listaComments= new ArrayList<Comment>();
//	        
//	        
//	        
//	        try {
//				isAvailable=
//						(MobileSMInicioImplementacaoBDActivity.derp.equals(client.isAvailable()));
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//	        //isAvailable=false;
//	        if(isAvailable)
//	        {
//		        try {
//					client.connect();
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//					isAvailable=false;
//				}
//		        if(isAvailable){
//		        	
//		        	try {
//						listaComments=client.getTicketCommentsNeedingMyAttention();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//		        	
//			        if(listaComments!=null){
//			        	for (Comment comment : listaComments) {
//				        		listLines.add("#" + comment.getTicketId() + "->" + comment.getDate()
//				        				+ "\n" +	comment.getContent());
//				        	}
//			        	
//			        	listLines.add("OH Yeah! No more comments!!");
//				        setListAdapter(new ArrayAdapter<String>(this, R.layout.tickets_row, listLines));
//				        final ListView lv= (ListView) getListView();
//				        
//				        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//					      	  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
//
//					      		//converter em string  
//					      	    String str = (String) lv.getItemAtPosition(position);
//					      	    
//					      	    if(str != "OH Yeah! No more comments!!"){
//					      	    
//						      	    //cortar para achar o numero entre "#" e "-"
//						      	    String[] temp;
//						      	    String delimiter = "#";
//						      	    
//						      	    temp = str.split(delimiter);
//						      	    delimiter = "-";
//						      	    temp = temp[1].split(delimiter);
//						      	    
//						      	    //converter em inteiro e ta feito GG RIOT
//						      	    int id_ticket = Integer.parseInt(temp[0]);
//						      	    
//							        Intent propTicket = new Intent();
//							        propTicket.setClass(getApplicationContext(), PropriedadeDoTicket.class);
//							    	propTicket.putExtra("id do ticket", id_ticket);
//							    	//propTicket.putExtra("sessionKey", "")
//							    	startActivity(propTicket);
//
//					      	    }
//					      	    				      	    
//					      	  }
//					      	});
//			        }	        	
//		        	
//		        }
//		        
//		        
//		        
//	        }
//	    }

}
