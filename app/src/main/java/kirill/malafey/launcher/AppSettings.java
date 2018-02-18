package kirill.malafey.launcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppSettings {
    public static final String APP_PREFERENCE_APP_THEME = "app_theme";
    public static final String APP_PREFERENCE_MODEL = "model";
    public static final String APP_PREFERENCE_IS_FIRST_START = "is_first_start";

    public static final String THEME_DARK = "dark_theme";
    public static final String THEME_LIGHT = "light_theme";

    public static final String MODEL_FULL = "full_model";
    public static final String MODEL_STANDARD = "standard_model";

    public static final int SPAN_COUNT_PORTRAIT_FULL_MODEL = 5;
    public static final int SPAN_COUNT_LANDSCAPE_FULL_MODEL = 7;
    public static final int SPAN_COUNT_PORTRAIT_STANDARD_MODEL = 4;
    public static final int SPAN_COUNT_LANDSCAPE_STANDARD_MODEL = 6;

    private String appTheme;
    private String model;
    private static AppSettings appSettings;
    private SharedPreferences sharedPreferences;


    private AppSettings(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static AppSettings getInstance(Context context) {
        if (appSettings == null) {
            appSettings = new AppSettings(context);
        }
        return appSettings;
    }

    public int getCurrentThemeResource() {
        appTheme = sharedPreferences.getString(APP_PREFERENCE_APP_THEME, THEME_DARK);
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
        model = sharedPreferences.getString(APP_PREFERENCE_MODEL, MODEL_STANDARD);
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
        model = sharedPreferences.getString(APP_PREFERENCE_MODEL, MODEL_STANDARD);
        switch (model) {
            case MODEL_FULL:
                return SPAN_COUNT_LANDSCAPE_FULL_MODEL;
            case MODEL_STANDARD:
                return SPAN_COUNT_LANDSCAPE_STANDARD_MODEL;
            default:
                return SPAN_COUNT_LANDSCAPE_STANDARD_MODEL;
        }
    }

    public void setAppTheme(String appTheme) {
        this.appTheme = appTheme;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(APP_PREFERENCE_APP_THEME, appTheme);
        editor.apply();
    }

    public void setModel(String model) {
        this.model = model;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(APP_PREFERENCE_MODEL, model);
        editor.apply();
    }

    public boolean isFirstStart() {
        return sharedPreferences.getBoolean(APP_PREFERENCE_IS_FIRST_START, true);
    }

    public void setFirstStart(boolean isFirstStart) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(APP_PREFERENCE_IS_FIRST_START, isFirstStart);
        editor.apply();
    }
}
