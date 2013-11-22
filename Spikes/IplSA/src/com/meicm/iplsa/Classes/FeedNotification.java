package com.meicm.iplsa.Classes;

import java.sql.Date;

public class FeedNotification {
	
	private String date;
	private int type;
	private String source;
	private String body;
	
	public FeedNotification(String date, int type, String source, String body) {
		super();
		this.date = date;
		this.type = type;
		this.source = source;
		this.body = body;
	}
	
	public String getDate(){
		return this.date;
	}
	
	public int getType(){
		return this.type;
	}
	
	public String getSource(){
		return this.source;
	}
	
	public String getBody(){
		return this.body;
	}

}

