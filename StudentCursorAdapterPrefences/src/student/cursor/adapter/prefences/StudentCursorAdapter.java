package student.cursor.adapter.prefences;

import android.content.Context;
import android.database.Cursor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

//static כדי שהמשתנים ה 
// יהיו זמנים לנו גם בקלאס הזה
import static student.cursor.adapter.prefences.StudentDatabaseHelper.*;


public class StudentCursorAdapter extends CursorAdapter
{
	private LayoutInflater myInflator = null;
	

	public StudentCursorAdapter(Context context, Cursor c) 
	{
		super(context, c,true);
		myInflator = LayoutInflater.from(context);
		
	}


	//חיבור הנתונים לשורה בקסמל
	@Override
	public void bindView(View myView, Context context, Cursor cursor) 
	{
		ViewHolder holder = (ViewHolder)myView.getTag();
		
		int columnIndexId = cursor.getColumnIndex(COLUMN_ID);
		int columnIndexName = cursor.getColumnIndex(COLUMN_NAME);
		
		long studentId = cursor.getInt(columnIndexId);
		String studentName = cursor.getString(columnIndexName);
		
		//נותנים למחזיק את מספר התלמיד מבסיס הנתונים אבל לא מציגים אותו
		holder.setStudentId(studentId);
		
	    //נונתים למחזיק איפה להציג ומה להציג
		holder.getMyTextView().setText(studentName);
		
	}
// כאשר אין שורה למחזר אז אומרים איך נבנה את השורה מאיזה אוביקטים
	@Override
	public View newView(Context arg0, Cursor arg1, ViewGroup arg2) 
	{
		//קישור לשורה של האקמל
		View myView = myInflator.inflate(R.layout.my_list_item, null);
		
		//מה נחזיק
		ViewHolder holder = new ViewHolder();
		//בשביל הטקסט
		TextView myTextView = (TextView)myView.findViewById(R.id.myTextView);
		holder.setMyTextView(myTextView);
		
		myView.setTag(holder);// נעשה כיס כדי לשמור שם את הנתונים
		
		return myView;
	}

}
