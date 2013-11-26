package com.meicm.iplsa.Classes;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.text.format.DateFormat;
import android.webkit.DateSorter;

public class HandlerNotifications {
	
	
	
	public HandlerNotifications() {
		super();
		
	}

	public ArrayList<FeedNotification> getLast20NotificationsFromSources(){
		
// ISTO JA NAO DEVE SER PRECISO		
//		 if (android.os.Build.VERSION.SDK_INT > 9) {
//             StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//             StrictMode.setThreadPolicy(policy);
//           }
		
		 //CONSUMIR TWITTER
	 ConfigurationBuilder cb = new ConfigurationBuilder();
     cb.setDebugEnabled(true)
             .setOAuthConsumerKey("BSIwYtclxdDnUVrDqyTg")
             .setOAuthConsumerSecret(
                     "8uo8ICYR7xeZyApDA6mJae8yAjGDoB8E8RM0cw5AsM")
             .setOAuthAccessToken(
                     "1093045885-5GxIU1hhvKbOj1hdKYo7XCedCN6ZBMSfK19ZJhe")
             .setOAuthAccessTokenSecret(
                     "az6y2oaHZaYb12TntxbRULJgKBwDHoQJP7yAl73jzqhZG");
     TwitterFactory tf = new TwitterFactory(cb.build());
     Twitter twitter = tf.getInstance();
     ArrayList<FeedNotification> tweets = new ArrayList<FeedNotification>();
     try {
     	
         ResponseList<User> users;
         String[] sources = new String[]{"ESTG_EI","thor_this","parashell"};         
         users = twitter.lookupUsers(sources);
         for (User user : users) {
             System.out.println("Friend's Name " + user.getName() + " id ->" + user.getId()); // this print my friends name
                 if (user.getStatus() != null) 
                 {
                 	System.out.println("Friend timeline");
                 	List<Status> statuses = twitter.getUserTimeline(user.getId());
                 for (Status tweetText : statuses) 
                  {
                        tweets.add(new FeedNotification(tweetText.getCreatedAt().toString(), 1, tweetText.getUser().getName(), tweetText.getText()));
                        System.out.println("Tipo - 1 From - " + tweetText.getUser().getName() + " Body - " + tweetText.getText() + " Date - " +tweetText.getCreatedAt().toString());
                  }
                 }
           }
           
          
     } catch (TwitterException te) {
         te.printStackTrace();
     }
     
     //CONSUMIR MOODLE
     
     
     
     //ORDENAR POR DATA
     
     Collections.sort(tweets, new Comparator<FeedNotification>() {
         @SuppressLint("SimpleDateFormat")
		@Override public int compare(FeedNotification f1, FeedNotification f2) {
        	 SimpleDateFormat format = new SimpleDateFormat("EEE MMM d k:m:s ZZZ yyyy");
        	 java.util.Date f1D = null;
        	 java.util.Date f2D = null;
        	 try 
        	 {
				f1D = (java.util.Date) format.parse(f1.getDate());
				f2D = (java.util.Date) format.parse(f2.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	 
        	 //Tue Nov 19 08:42:33 EST 2013  
        	 	
        	 return f2D.compareTo(f1D);
         }

     });
     
     return tweets;
     
	}

}