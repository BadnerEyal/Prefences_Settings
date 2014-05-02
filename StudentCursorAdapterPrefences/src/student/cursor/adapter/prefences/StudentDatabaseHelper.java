package student.cursor.adapter.prefences;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * StudentDatabase
 * 
 * Maintains a table of students id and name
 *  
 * @author gabriel
 *
 */
public class StudentDatabaseHelper extends SQLiteOpenHelper
{

	public static final String TABLE_STUDENTS = "students";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_NAME = "name";

	  private static final String DATABASE_NAME = "students.db";
	  private static final int DATABASE_VERSION = 1;

	  // Create SQL query that create the studnets table
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_STUDENTS + "(" + COLUMN_ID
	      + " integer primary key autoincrement, " + COLUMN_NAME
	      + " text not null);";

	  
	
	public StudentDatabaseHelper(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	 @Override
	 public void onCreate(SQLiteDatabase database) 
	 {
		 /**
		  * Here we generate the students table
		  */
	    database.execSQL(DATABASE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	public static void mamamia(){}
}
