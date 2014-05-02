package student.cursor.adapter.prefences;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
Settings  prefences  תפריט שמירה נתונים שהמשתמש מחליט כמו מיון רשימה ועוד
http://developer.android.com/guide/topics/ui/settings.html
*/
public class MainActivity extends Activity 

/**
 * בשביל להאזין לשינוים בדף ההגדרות 
 * נירש מאזין
We'de like to update the list we see follow changes in settings
*/
	implements OnSharedPreferenceChangeListener {

	private ListView myListView = null;
	private StudentDatabaseHandler handler = null;
	private StudentCursorAdapter myAdapter = null;
	private Cursor cursor;
	private String str_studentId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_list_layout);

		myListView = (ListView) findViewById(R.id.myListView);

		handler = new StudentDatabaseHandler(this);

		cursor = handler.getAllStudents();
		myAdapter = new StudentCursorAdapter(this, cursor);

		myListView.setAdapter(myAdapter);

		// Note: Till here we've connected the cursor into the
		// StudentCursorAdapter.
		// Then we connected the StudentCursorAdapter into the myListView
		// cursor --> myAdapter --> myListView
		myListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int position, long arg3) {

				ViewHolder holder = (ViewHolder) view.getTag();

				long studentId = holder.getStudentId();

				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("This student id").setMessage(
						"ID  = " + studentId);

				AlertDialog dialog = builder.create();

				dialog.show();

			}
		});

		myListView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapterView,
					View view, int position, long arg3) {
				ViewHolder holder = (ViewHolder) view.getTag();

				long studentId = holder.getStudentId();
				str_studentId = String.valueOf(studentId);

				AlertDialog.Builder builder = new AlertDialog.Builder(
						MainActivity.this);
				builder.setTitle("Delete This student id").setMessage(
						"ID  = " + studentId);

				// Set up the buttons
				builder.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// קריאה למחיקה לפי מספר מזהה
								handler.deleteStudent(str_studentId);

								// רענון הרשימה לאחר מחיקה
								cursor = handler.getAllStudents();
								myAdapter.changeCursor(cursor);
								// myAdapter.notifyDataSetChanged();
								// myListView.deferNotifyDataSetChanged();
							}
						});

				builder.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();
							}
						});
				AlertDialog dialog = builder.create();
				dialog.show();

				return false;
			}
		});

		initAddButton();
		
		
		
		
		
	}

	protected void addStudent(String studentName) {
		// Step 3: insert into database the new student
		Student student = handler.addStudent(studentName);

		// cursor.requery();
		// בשביל לרענן את הרשימה לאחר הוספה
		cursor = handler.getAllStudents();
		myAdapter.changeCursor(cursor);

		// This is resamble to if/else in short writing
		// <-- IF true ELSE false
		String message = ((student == null) ? "NADA" : student.toString());

		Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
	}

	private void initAddButton() {
		Button addStudentButton = (Button) findViewById(R.id.addStudentButton);
		addStudentButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Step 1: create and input dialog and show it
				Dialog myInputDialog = onCreateDialog();
				myInputDialog.show();
			}
		});
	}

	public Dialog onCreateDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Add Student");

		// Set up the input
		final EditText input = new EditText(this);
		// Specify the type of input expected; this, for example, sets the input
		// as a password, and will mask the text
		input.setInputType(InputType.TYPE_CLASS_TEXT);

		// Set the dialog with the view you created
		builder.setView(input);

		// Set up the buttons
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Step 2: collect the student name from the input dialog if
				// user clicks the ok
				String studentName = input.getText().toString();
				addStudent(studentName);
			}
		});

		builder.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
					}
				});

		Dialog dialog = builder.create();

		return dialog;
	}

	
	
//בשביל הכפתורים בתפריט	
// קישור התפריט לדף תפריט בקמל
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);

		return true;
	}

	//כאשר המשתמש ילחץ באחד הכפתורים בתפריט
	//במקרה זה במקום תפריט יש כפתורים בבאר העליון
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		 //בחירה בתפריט עריכה
		case R.id.menu_settings:
	            Intent intent = new Intent(this, MySettingsActivity.class); 
	            startActivity(intent);
	            
	            return true;
	            
	         //להוספת תלמיד   
	        case R.id.menu_add_student:
	        	// Step 1: create and input dialog and show it
				Dialog myInputDialog = onCreateDialog();
				myInputDialog.show();
	            
				return true;
		
		
	
		default:
			return super.onOptionsItemSelected(item);
		}

	}

	
	
	/**
	 * פונקציה חזרה מלחיצת כפתור בתפריט הגדרות
	 * This method is a callback method for listener OnSharedPreferenceChangeListener
	 */
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //אם ילחץ כפתור מיון בתפריט הגדרות
		
		if (key.equals("pref_order")) 
        {
        	//getAllStudents  שאילתה נוסיף בפונקציה 
			//את הערך שנשמר בזכרון ולפי זה כל פעם שנקרא נדע מה העדפות של המשתמש
			
			//זה רק רעינון הרשימה 
			cursor =  handler.getAllStudents();
    		myAdapter.changeCursor(cursor);
    		myAdapter.notifyDataSetChanged();
        }
    }
	
	@Override
	protected void onResume() {
	    super.onResume();
	    /**
	     * הרשמה להאזין לשינוים
	     * Here we register our application preferences to tell this class that a chnage in the preferences 
	     * of the application took place
	     */
	    PreferenceManager.getDefaultSharedPreferences(this)
	            .registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    /**
	     * סגירת האזנה שהדף נהרס 
	     * Here we un-register our application preferences to stop(!!!) telling this class that a chnage in the preferences 
	     * of the application took place
	     */
	    PreferenceManager.getDefaultSharedPreferences(this)
	            .unregisterOnSharedPreferenceChangeListener(this);
	}
}
	
	
	

