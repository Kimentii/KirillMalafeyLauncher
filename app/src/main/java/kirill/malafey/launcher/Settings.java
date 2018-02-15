package kirill.malafey.launcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.Log;

public class Settings {
    public static final int THEME_DARK_INDEX = 0;
    public static final int THEME_LIGHT_INDEX = 1;

    public static final int MODEL_FULL_INDEX = 0;
    public static final int MODEL_STANDARD_INDEX = 1;

    public static final int SPAN_COUNT_PORTRAIT_FULL_MODEL = 5;
    public static final int SPAN_COUNT_LANDSCAPE_FULL_MODEL = 7;
    public static final int SPAN_COUNT_PORTRAIT_STANDARD_MODEL = 4;
    public static final int SPAN_COUNT_LANDSCAPE_STANDARD_MODEL = 6;


    private String appTheme;
    private String model;
    private static Settings settings;
    private SharedPreferences sharedPreferences;
    private Context context;


    private Settings(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        appTheme = sharedPreferences.getString(context.getResources().getString(R.string.app_preference_app_theme),
                context.getResources().getStringArray(R.array.themes_values)[THEME_DARK_INDEX]);
        model = sharedPreferences.getString(context.getResources().getString(R.string.app_preference_model),
                context.getResources().getStringArray(R.array.models_values)[MODEL_FULL_INDEX]);

    }

    public static Settings getInstance(Context context) {
        if (settings == null) {
            settings = new Settings(context);
        }
        return settings;
    }

    public void saveSettings() {
        /*SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.app_preference_app_theme),
                appTheme);
        editor.putString(context.getResources().getString(R.string.app_preference_model),
                model);
        editor.apply();*/
    }

    public int getCurrentThemeResource() {
        Resources res = context.getResources();
        if (appTheme.equals(res.getStringArray(R.array.themes_values)[THEME_DARK_INDEX])) {
            return R.style.AppThemeDark;
        } else {
            return R.style.AppThemeLight;
        }
            /*
        switch (appTheme) {
            case THEME_DARK:
                return R.style.AppThemeDark;
            case THEME_LIGHT:
                return R.style.AppThemeLight;
            default:
                return R.style.AppThemeLight;
        }*/
    }

    public int getSpanCountPortrait() {
        Log.d("TAG", "Model" + model);
        Resources res = context.getResources();
        if (model.equals(res.getStringArray(R.array.models_values)[MODEL_FULL_INDEX])) {
            return SPAN_COUNT_PORTRAIT_FULL_MODEL;
        } else {
            return SPAN_COUNT_PORTRAIT_STANDARD_MODEL;
        }
        /*switch (model) {
            case MODEL_FULL:
                return SPAN_COUNT_PORTRAIT_FULL_MODEL;
            case MODEL_STANDARD:
                return SPAN_COUNT_PORTRAIT_STANDARD_MODEL;
            default:
                return SPAN_COUNT_PORTRAIT_STANDARD_MODEL;
        }*/
    }

    public int getSpanCountLandscape() {
        Resources res = context.getResources();
        if (model.equals(res.getStringArray(R.array.models_values)[MODEL_FULL_INDEX])) {
            return SPAN_COUNT_LANDSCAPE_FULL_MODEL;
        } else {
            return SPAN_COUNT_LANDSCAPE_STANDARD_MODEL;
        }
        /*switch (model) {
            case res.getStringArray(R.array.models_values)[MODEL_FULL_INDEX]:
                return SPAN_COUNT_LANDSCAPE_FULL_MODEL;
            case MODEL_STANDARD:
                return SPAN_COUNT_LANDSCAPE_STANDARD_MODEL;
            default:
                return SPAN_COUNT_LANDSCAPE_STANDARD_MODEL;
        }*/
    }

    public void setAppTheme(String appTheme) {
        this.appTheme = appTheme;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.app_preference_app_theme),
                appTheme);
        editor.apply();
    }

    public void setModel(String model) {
        this.model = model;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.app_preference_model),
                model);
        editor.apply();
    }
}
