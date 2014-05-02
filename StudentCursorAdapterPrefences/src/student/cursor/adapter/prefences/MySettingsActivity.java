package student.cursor.adapter.prefences;


import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceClickListener;

//PreferenceActivity אקטיבטי הגדרות יורש את
public class MySettingsActivity extends PreferenceActivity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//קישור לקובץ הגדרות בתקיה קמל שיצרנו
		addPreferencesFromResource(R.xml.my_settings);	
		
		Preference customPref = findPreference("pref_custom");
		
		customPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick(Preference preference) {

				AlertDialog.Builder builder = new AlertDialog.Builder(MySettingsActivity.this);
				Dialog d = builder.setTitle("my dialig title").setMessage("rfgdfg dfg fasdgad adfd").create();
				
				d.show();

				return true;
			}
		});
    }

	
	
}

