package kg.geektech.taskapp37;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Prefs {

    private SharedPreferences sharedPreferences;

    public Prefs(Context context) {
        sharedPreferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveBoardState() {
        sharedPreferences.edit().putBoolean("isShown", true).apply();
    }

    public boolean isBoardShown() {
        return sharedPreferences.getBoolean("key1", false);
    }

    public void saveUserName(String name) {
        sharedPreferences.edit().putString("key2", name).apply();
    }

    public String getUsername() {
        return sharedPreferences.getString("key2", null);
    }

    public void saveImage(Uri uri){
        sharedPreferences.edit().putString("key3", uri.toString()).apply();

    }

    public String getImage(){
         return sharedPreferences.getString("key3", "");
    }


}
