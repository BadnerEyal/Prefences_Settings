package student.cursor.adapter.prefences;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.preference.PreferenceManager;


/**
 * A static import will inject the entire public\protect methods and parameters of the import subjuct
 */
import static student.cursor.adapter.prefences.StudentDatabaseHelper.*;

public class StudentDatabaseHandler 
{
	private StudentDatabaseHelper helper = null;
	private Context context = null;
	
	public StudentDatabaseHandler(Context context)
	{
		helper = new StudentDatabaseHelper(context);
		this.context = context;
	}
	
	/**
	 * ����� ����� ����� ������� ���� 
	 * ���� �� ���� ��� ����� �������
	 * We always query follow order that is set in application preferences "pref_order"
	 * @return
	 */
	public Cursor getAllStudents()
	{
		
		/**
		 * ���� ���� ������� ��� ��� ���� �� �����
		 * ��� ������ ��� ������ ��� 
		 * ���� ����� ������ ���� �� ���� ��� ���� ����
		 * ���� �� ���� �� ����� ���� ���� ���� 
		 */
		boolean ascendingOrder = PreferenceManager.getDefaultSharedPreferences(context).
				getBoolean("pref_order", true);
    	
    	
		SQLiteDatabase db = helper.getReadableDatabase();
		 //��� ���� �����
		//COLUMN_NAME+(ascendingOrder?" ASC":" DESC"));
		Cursor c =  db.query(TABLE_STUDENTS, new String[]{COLUMN_ID, COLUMN_NAME}, 
				null, null, null, null, COLUMN_NAME+(ascendingOrder?" ASC":" DESC"));
		
		return c;
		  
	}
	//����� �����
	public Student addStudent(String name)
	{
		
		SQLiteDatabase db = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put(COLUMN_NAME, name);
		
		long id = db.insert(TABLE_STUDENTS, null, values);
		
		if(id == -1)
		{
			return null;
		}
		
		Student student = new Student(id, name);
		
		return student;
	}
	
	//����� ����� 
		//id 
		public void deleteStudent(String id){
			
			SQLiteDatabase db = helper.getWritableDatabase();
			
			String [] values  = {id};
			db.delete(TABLE_STUDENTS, "_id=?", values);
			db.close();
		
			
			
		}
}



























