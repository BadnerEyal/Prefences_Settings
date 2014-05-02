package student.cursor.adapter.prefences;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ExampleSQLiteHelper extends SQLiteOpenHelper 
{
	public static final String TABLE_COMMENTS = "comments";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_COMMENT = "comment";

	  private static final String DATABASE_NAME = "commments.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_COMMENTS + "(" + COLUMN_ID
	      + " integer primary key autoincrement, " + COLUMN_COMMENT
	      + " text not null);";

	  public ExampleSQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	    for(int i=0;i<100;i++)
	    {
	        database.execSQL("INSERT INTO "+TABLE_COMMENTS+" ( "+COLUMN_ID+", "+COLUMN_COMMENT+") VALUES (0, 'Comment "+String.valueOf(i)+"');");
	    }
	  }
	  
	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(ExampleSQLiteHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
	    onCreate(db);
	  }

	  public Cursor getComments()
	  {
		  SQLiteDatabase db = getReadableDatabase();
		  
		  return db.query(TABLE_COMMENTS, new String[]{COLUMN_ID, COLUMN_COMMENT}, null, null, null, null, null);
	  }
}
