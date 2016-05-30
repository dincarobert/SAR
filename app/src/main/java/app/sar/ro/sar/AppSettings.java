package app.sar.ro.sar;

import android.content.Context;
import android.content.SharedPreferences;

public class AppSettings {

    SharedPreferences prefs;
    Context context;

    private AppSettings(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(
                "AppSettings", Context.MODE_PRIVATE);
    }

    public static AppSettings get(Context context) {
        AppSettings sett = new AppSettings(context);
        sett.context = context;
        return sett;
    }

    public int getFirstTime() {
        return prefs.getInt("FirstTime", 0);
    }

    public void updateFirstTime(int index) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putInt("FirstTime", index);
        edit.commit();
    }


    public String getString(String key, String value) {
        return prefs.getString(key, value);
    }

    public void updateString(String key, String value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public String getUserName() {
        return prefs.getString("User Name", "");
    }

    public void updateUserName(String value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("User Name", value);
        edit.commit();
    }

    public String getPasword() {
        return prefs.getString("Password", "");
    }

    public void updatePassword(String value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("Password", value);
        edit.commit();
    }

    public String getDeviceId() {
        return prefs.getString("DeviceID", "");
    }

    public void updateDeviceID(String value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("DeviceID", value);
        edit.commit();
    }

    public int getWeight() {
        return prefs.getInt("Weight", 0);
    }

    public void updateWeight(int value) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putInt("Weight", value);
        edit.commit();
    }

}
