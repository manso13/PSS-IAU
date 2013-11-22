
package com.meicm.ipsla.database;
import java.security.acl.NotOwnerException;

import com.meicm.iplsa.Classes.FeedNotification;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.ExifInterface;
import android.provider.OpenableColumns;
import android.util.Log;

/**
 * Simple notes database access helper class. Defines the basic CRUD operations
 * for the notepad example, and gives the ability to list all notes as well as
 * retrieve or modify a specific note.
 * 
 * This has been improved from the first version of this tutorial through the
 * addition of better error handling and also using returning a Cursor instead
 * of using a collection of inner classes (which is less scalable and not
 * recommended).
 */
public class TicketsDbAdapter {

	public static final String TABLE_NAME = "notifications";
	public static final String COLUMN_ROWID = "_id";
	public static final String COLUMN_DATE = "date";
	public static final String COLUMN_TYPE = "type";
	public static final String COLUMN_BODY = "body";
	public static final String COLUMN_SOURCE = "source";
	
    private static final String TAG = "NotificationsDbAdapter";
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    /**
     * Database creation sql statement
     */
    private static final String DATABASE_CREATE =
            "create table "+ TABLE_NAME  +" (" + COLUMN_ROWID + " integer primary key , "
            + COLUMN_TYPE + "  text not null, " + COLUMN_SOURCE + " text not null, " + COLUMN_BODY + "text not null,"
            		+ COLUMN_DATE + "  text not null" +
            "); ";
  
    private static final String DATABASE_NAME = "data.db";
//    private static final String DATABASE_TABLE_TICKETS = "tickets";
//    private static final String DATABASE_TABLE_COMMENTS = "comments";
    private static final int DATABASE_VERSION = 2;
    private static boolean openFail=false;

    private final Context mCtx;

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
            //	openFail
            	
            	
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME );
            //db.execSQL("DROP TABLE IF EXISTS comments");
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx the Context within which to work
     */
    public TicketsDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the notes database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public TicketsDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
    	 if (mDbHelper != null) {
    	        mDbHelper.close();
    	        }
    }

    public void dropAllInfo(){
    	mDb.delete(TABLE_NAME, "?>-1", new String[]{COLUMN_ROWID});
    }
    /**
     * Create a new note using the title and user provided. If the note is
     * successfully created return the new rowId for that note, otherwise return
     * a -1 to indicate failure.
     * 
     * @param title the title of the note
     * @param user the user of the note
     * @return rowId or -1 if failed
     */
    public long createNotification(FeedNotification notification) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(COLUMN_TYPE, notification.getType());
        initialValues.put(COLUMN_SOURCE, notification.getSource());
        initialValues.put(COLUMN_BODY, notification.getBody());
        initialValues.put(COLUMN_DATE, notification.getDate());
        return mDb.replace(TABLE_NAME, null, initialValues);
    }
    
  public Cursor fetchAllNotifications() {

      return mDb.query(TABLE_NAME, new String[] {COLUMN_ROWID, COLUMN_TYPE,
              COLUMN_SOURCE, COLUMN_BODY,COLUMN_DATE}, null, null, null, null, null);
  }
  
public Cursor fetchNotifications(long rowId) throws SQLException {

    Cursor mCursor =

        mDb.query(true, TABLE_NAME, new String[] {"*"}, COLUMN_ROWID + "=" + rowId, null,
                null, null, null, null);
    if (mCursor != null) {
        mCursor.moveToFirst();
    }
    return mCursor;

}

//    public long createUserTicket(Ticket ticket )  {
//        ContentValues initialValues = new ContentValues();
//        initialValues.put(COLUMN_ROWID, ticket.getID());
//        initialValues.put(COLUMN_TITLE, ticket.getTitle());
//        if (ticket.getOriginName()==null)
//        	ticket.setOriginInName("guestipl");        		
//        initialValues.put(COLUMN_USER, ticket.getOriginName());
//        initialValues.put(COLUMN_STATUS, ticket.getStatus());
//        initialValues.put(COLUMN_IMPACT,ticket.getImpact());
//        initialValues.put(COLUMN_URGENCY,ticket.getUrgency());
//        initialValues.put(COLUMN_DESCRIPTION,ticket.getDescription());
//        initialValues.put(COLUMN_TICKET_TYPE,ticket.getTypeName());
//        initialValues.put(COLUMN_START_DATE,ticket.getStartDate());
//        
//        return mDb.replace(DATABASE_TABLE_TICKETS, null, initialValues);
//       /* assert(check!=-1);
//        	
//        initialValues.clear();
//        
//        Cursor cu = mDb.rawQuery("SELECT _id FROM " + DATABASE_TABLE_TICKETS + " order by _id desc limit 1", null);
//        
//        cu.moveToFirst();
//        if(cu!=null)
//        	long id=DatabaseUtils.queryNumEntries(mDb,DATABASE_TABLE_TICKETS);
//                
//        initialValues.put(COLUMN_TICKETID, id);
//        initialValues.put(COLUMN_USER, ticket.getUser());
//        initialValues.put(COLUMN_CONTENT, ticket.getCommentByNumber(0).getContent());
//        
//        return mDb.insert(DATABASE_TABLE_COMMENTS, null, initialValues);*/
//        
//    }
//    
//    public long CreateTicketComments(Comment comment){
//    	ContentValues initialValues = new ContentValues();
//    	initialValues.put(COLUMN_CONTENT,comment.getContent());
//    	initialValues.put(COLUMN_TICKETID, comment.getTicketId());
//    	initialValues.put(COLUMN_USER, comment.getUser());
//    	
//    	return mDb.replace(DATABASE_TABLE_COMMENTS, null, initialValues);
//    	
//    	
//    /*	rawQuery("Insert Into " +  DATABASE_TABLE_COMMENTS + "(ticketId, user, content) Values (?, ?, ?) ", 
//    			new String [] {comment.getTicketId().toString(),comment.getUser(),comment.getContent()});*/
//    	
//    }
//    
//
//    /**
//     * Delete the note with the given rowId
//     * 
//     * @param rowId id of note to delete
//     * @return true if deleted, false otherwise
//     */
//    public boolean deleteTicket(long rowId) {
//    
//        return mDb.delete(DATABASE_TABLE_TICKETS, COLUMN_ROWID + "=" + rowId, null) > 0;
//    }
//
//    /**
//     * Return a Cursor over the list of all notes in the database
//     * 
//     * @return Cursor over all notes
//     */

//
//
//    /**
//     * Return a Cursor positioned at the note that matches the given rowId
//     * 
//     * @param rowId id of note to retrieve
//     * @return Cursor positioned to matching note, if found
//     * @throws SQLException if note could not be found/retrieved
//     */

//    
//    public Cursor fetchTicketComments(long iD) throws SQLException{
//    	
//    	String iDt=String.valueOf(iD);
//    	Cursor mCursor=
//    		mDb.rawQuery("SELECT * from " + DATABASE_TABLE_COMMENTS + " c INNER JOIN " + DATABASE_TABLE_TICKETS
//    				 + " t on c.ticketID=t._id where c.ticketID = ? ", new String[]{iDt});
//        if (mCursor != null) {
//            mCursor.moveToFirst();
//        }
//    	return mCursor;
//    }
//    
//    
//    
//    public Cursor fetchAllTicketsByStatus(String status){
//    	
//    	Cursor mCursor=	mDb.query(true, DATABASE_TABLE_TICKETS, new String[] {"*"}, 
//    					COLUMN_STATUS + "=" + "\"" + status + "\"", null,
//    					null, null, null, null);
////    			mDb.rawQuery("SELECT * " +	" from " + DATABASE_TABLE_TICKETS + " WHERE status = ? ",
////    					status);
//    	 if (mCursor != null) { 
//             mCursor.moveToFirst();
//         }
//    	return mCursor;
//    }
//
//    /**
//     * Update the note using the details provided. The note to be updated is
//     * specified using the rowId, and it is altered to use the title and user
//     * values passed in
//     * 
//     * @param rowId id of note to update
//     * @param title value to set note title to
//     * @param user value to set note user to
//     * @return true if the note was successfully updated, false otherwise
//     */
//    public boolean updateTicket(long rowId, String title, String user, String status, String urgency, String assignedTo,
//    		String impact) {
//        ContentValues args = new ContentValues();
//        args.put(COLUMN_TITLE, title);
//        args.put(COLUMN_USER, user);
//        args.put(COLUMN_STATUS, status);
//        args.put(COLUMN_IMPACT, impact);
//        args.put(COLUMN_ASSIGNED_TO, assignedTo);
//        args.put(COLUMN_URGENCY, urgency);
//
//        return mDb.update(DATABASE_TABLE_TICKETS, args, COLUMN_ROWID + "=" + rowId, null) > 0;
//    }
//    
//    public int getRowMyUnsolvedCount(){
//    	Cursor cur=fetchAllTicketsByStatus(MobileSMInicioImplementacaoBDActivity.ESTADO_UNSOLVED);
//    	
//    	return cur.getCount();
//    }
//    
//    public int getRowLastSolvedCount(){
//    	Cursor cur=fetchAllTicketsByStatus(MobileSMInicioImplementacaoBDActivity.ESTADO_SOLVED);
//    	return cur.getCount();
//    }
//    
//    public int getRowNeedMyAttentionCount(){
//    	Cursor cur=fetchAllTicketsByStatus(MobileSMInicioImplementacaoBDActivity.ESTADO_NEED_MY_ATTENTION);
//    	
//    	return cur.getCount();
//    }
}
