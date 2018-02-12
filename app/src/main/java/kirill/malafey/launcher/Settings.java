package kirill.malafey.launcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Settings {
    private static final String APP_PREFERENCES = "settings";
    private static final String APP_PREFERENCES_APP_THEME = "app_theme";
    private static final String APP_PREFERENCES_MODEL = "model";

    public static final String THEME_DARK = "dark_theme";
    public static final String THEME_LIGHT = "light_theme";

    public static final String MODEL_STANDARD = "standard_model";
    public static final String MODEL_FULL = "full_model";

    public static final int SPAN_COUNT_PORTRAIT_FULL_MODEL = 5;
    public static final int SPAN_COUNT_LANDSCAPE_FULL_MODEL = 7;
    public static final int SPAN_COUNT_PORTRAIT_STANDARD_MODEL = 4;
    public static final int SPAN_COUNT_LANDSCAPE_STANDARD_MODEL = 6;


    private String appTheme;
    private String model;
    private static Settings settings;
    private SharedPreferences sharedPreferences;


    private Settings(Context context) {
        sharedPreferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        appTheme = sharedPreferences.getString(APP_PREFERENCES_MODEL, THEME_DARK);
        model = sharedPreferences.getString(APP_PREFERENCES_APP_THEME, MODEL_STANDARD);

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

    public int getCurrentThemeResource() {
        switch (appTheme) {
            case THEME_DARK:
                return R.style.AppThemeDark;
            case THEME_LIGHT:
                return R.style.AppThemeLight;
            default:
                return R.style.AppThemeLight;
        }
    }

    public int getSpanCountPortrait() {
        Log.d("TAG", "Model" + model);
        switch (model) {
            case MODEL_FULL:
                return SPAN_COUNT_PORTRAIT_FULL_MODEL;
            case MODEL_STANDARD:
                return SPAN_COUNT_PORTRAIT_STANDARD_MODEL;
            default:
                return SPAN_COUNT_PORTRAIT_STANDARD_MODEL;
        }
    }

    public int getSpanCountLandscape() {
        switch (model) {
            case MODEL_FULL:
                return SPAN_COUNT_LANDSCAPE_FULL_MODEL;
            case MODEL_STANDARD:
                return SPAN_COUNT_LANDSCAPE_STANDARD_MODEL;
            default:
                return SPAN_COUNT_LANDSCAPE_STANDARD_MODEL;
        }
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
