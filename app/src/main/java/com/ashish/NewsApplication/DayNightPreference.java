package com.ashish.NewsApplication;

import android.content.Context;
import android.content.SharedPreferences;

public class DayNightPreference {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;

    public DayNightPreference(Context context) {
        this.context = context;
        sharedPreferences = (SharedPreferences) context.getSharedPreferences("filename", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

    }

    //this method will save night mode state true or false
    public void setNightModeState(Boolean state) {
        editor.putBoolean("NightMode", state);
        editor.commit();
    }

    //this method will load night mode state
    public boolean loadNightModeState() {
        return sharedPreferences.getBoolean("NightMode", false);

    }
}