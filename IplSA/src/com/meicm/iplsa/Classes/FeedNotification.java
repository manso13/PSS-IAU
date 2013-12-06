package com.meicm.iplsa.Classes;

import java.util.Date;
import java.text.SimpleDateFormat;

public class FeedNotification {
	
	private long date;
	private int type;
	private String source;
	private String body;
	
	public FeedNotification(long date, int type, String source, String body) {
		super();
		this.date = date;
		this.type = type;
		this.source = source;
		this.body = body;
	}
	
	public long getTimestamp(){
		return this.date;
	}
	
	public Date getDate(){
		return new Date(this.date);
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

