Prefences_Settings
==================

Example use Prefences Settings

דוגמא ליצרת תפריט הגדרות משתמש

![Alt desc](http://i58.tinypic.com/2m67e6s.jpg)

Objects xml

<?xml version="1.0" encoding="utf-8"?>
<PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android" >

    <!-- בשביל המיון של הרשימה -->
    <CheckBoxPreference 
        android:key="pref_order"
        android:title="Sorting order"
        android:summaryOn="Ascending sort order"
        android:summaryOff="Decending sort order"
        android:defaultValue="true"
        />
    <!-- שאנו נבנה פתיחת דיאלוג משלנו -->
    <Preference
        android:key="pref_custom"
        android:defaultValue="some value"
        android:title="Preference title Opening a dialogue"
        android:summary="Preference summary Opening a dialogue"/>
    
     <!-- פתיחת דיאלוג טקסט -->
    <EditTextPreference
        android:defaultValue="some value"
        android:key="pref_key_my_key"
        android:summary="Opening Edit Text a dialogue"
        android:title="Edit Text" />

    <!--string פתיחת רשימת בחירה מאפשרויות אשר ניצור בקובץ string-array -->
    <ListPreference
        android:key="pref_sync"
        android:title="My list pref title"
        android:summary="Opan My list City"
        android:dialogTitle="My list dialog title"
        android:entryValues="@array/aaa_entries"
        android:entries="@array/aaa_values"/>
    
    <!--לחיצה בכפתור יפתח לנו דף חדש של הגדרות  -->
    <PreferenceScreen
        android:key="button_voicemail_category_key"
        android:persistent="false"
        android:title="Opan New page Settings">
         <!-- מה שאנו רוצים בדף השני  -->
        <Preference
            android:key="button_voicemail_provider_key"
            android:summary="page 2   "
            android:title="page 2 Settings " />
    </PreferenceScreen>

      <!-- פתיחת קטגוריה בתוך דף הגדרות  -->
    <PreferenceCategory 
        android:title="Personal details category"
        android:key="pref_key_storage_settings">
        
        <SwitchPreference
                android:defaultValue="true"
                android:key="pref_key_Switch"
                android:summary="Switch summary"
                android:title="Switch title" />
        
         <!-- בשביל בחירה מרובה -->
        <MultiSelectListPreference
        android:key="store_select"
        android:title="Multi SelectList title"
        android:summary="Multi SelectList summary"
        android:dialogTitle="Multi SelectList dialog title"
        android:entries="@array/store_names"
        android:entryValues="@array/stores"
        android:defaultValue="@array/stores"
        />
        
        <!--פתיחת דיאלוג צילצול מצב שקט רטט  -->
        <RingtonePreference
        android:key="pref_tone"
        android:title="Pref tone"
        android:summary="pref tone summ"
        android:showDefault="true"
        android:showSilent="true"/>
        
	</PreferenceCategory>
	 
</PreferenceScreen>
