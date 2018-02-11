package kirill.malafey.launcher;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {
    private static final String APP_PREFERENCES = "settings";
    private static final String APP_PREFERENCES_APP_THEME = "app_theme";
    private static final String APP_PREFERENCES_MODEL = "model";

    private String appTheme;
    private String model;
    private static Settings settings;
    private SharedPreferences sharedPreferences;


    private Settings(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static Settings getInstance(Context context) {
        if (settings == null) {
            settings = new Settings(context);
        }
        return settings;
    }

    public void saveSettings() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(APP_PREFERENCES_APP_THEME, appTheme);
        editor.putString(APP_PREFERENCES_MODEL, model);
        editor.apply();
    }

    public String getAppTheme() {
        return appTheme;
    }

    public void setAppTheme(String appTheme) {
        this.appTheme = appTheme;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
