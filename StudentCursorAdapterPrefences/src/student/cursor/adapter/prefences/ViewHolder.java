package student.cursor.adapter.prefences;

import android.widget.TextView;

//מה שנרצה שהאטבטר שלנו יחזיק
public class ViewHolder 
{
	private TextView myTextView;//שם תלמיד
	private long studentId;// מספר לפי בסיס הנתונים
	
	
	public TextView getMyTextView() {
		return myTextView;
	}
	public void setMyTextView(TextView myTextView) {
		this.myTextView = myTextView;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	
	
	
}
