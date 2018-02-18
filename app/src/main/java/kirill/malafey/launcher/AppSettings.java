package kirill.malafey.launcher;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
import android.util.Log;

public class AppSettings {
    public static final String TAG = "TAG";
    public static final String APP_PREFERENCE_APP_THEME = "app_theme";
    public static final String APP_PREFERENCE_MODEL = "model";
    public static final String APP_PREFERENCE_IS_FIRST_START = "is_first_start";
    public static final String APP_PREFERENCE_SORT = "sort";

    public static final String THEME_DARK = "dark_theme";
    public static final String THEME_LIGHT = "light_theme";

    public static final String MODEL_FULL = "full_model";
    public static final String MODEL_STANDARD = "standard_model";

    public static final String SORT_BY_DATE = "sort_by_data";
    public static final String SORT_BY_ALPHABET = "sort_by_alphabet";
    public static final String SORT_BY_INVERSE_ALPHABET = "sort_by_inverse_alphabet";
    public static final String SORT_BY_USE_FREQUENCY = "sort_by_use_frequency";


    public static final int SPAN_COUNT_PORTRAIT_FULL_MODEL = 5;
    public static final int SPAN_COUNT_LANDSCAPE_FULL_MODEL = 7;
    public static final int SPAN_COUNT_PORTRAIT_STANDARD_MODEL = 4;
    public static final int SPAN_COUNT_LANDSCAPE_STANDARD_MODEL = 6;

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
        String appTheme = sharedPreferences.getString(APP_PREFERENCE_APP_THEME, THEME_DARK);
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
        String model = sharedPreferences.getString(APP_PREFERENCE_MODEL, MODEL_STANDARD);
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
        String model = sharedPreferences.getString(APP_PREFERENCE_MODEL, MODEL_STANDARD);
        switch (model) {
            case MODEL_FULL:
                return SPAN_COUNT_LANDSCAPE_FULL_MODEL;
            case MODEL_STANDARD:
                return SPAN_COUNT_LANDSCAPE_STANDARD_MODEL;
            default:
                return SPAN_COUNT_LANDSCAPE_STANDARD_MODEL;
        }
    }

    public SortedListAdapterCallback<App> getSortedListAdapterCallback(RecyclerView.Adapter adapter) {
        String sort = sharedPreferences.getString(APP_PREFERENCE_SORT, SORT_BY_ALPHABET);
        SortedListAdapterCallback<App> sortedListAdapterCallback = null;
        Log.d(TAG, "sort: " + sort);
        switch (sort) {
            case SORT_BY_DATE:
                Log.d(TAG, "Sort by date");
                sortedListAdapterCallback = new SortedListAdapterCallback<App>(adapter) {

                    @Override
                    public int compare(App app1, App app2) {
                        return (int) (app1.getInstallationDateMS() - app2.getInstallationDateMS());
                    }

                    @Override
                    public boolean areContentsTheSame(App oldItem, App newItem) {
                        return compare(oldItem, newItem) == 0;
                    }

                    @Override
                    public boolean areItemsTheSame(App item1, App item2) {
                        return compare(item1, item2) == 0;
                    }
                };
                break;
            case SORT_BY_ALPHABET:
                Log.d(TAG, "sort by alp");
                sortedListAdapterCallback = new SortedListAdapterCallback<App>(adapter) {

                    @Override
                    public int compare(App app1, App app2) {
                        return app1.getAppName().compareTo(app2.getAppName());
                    }

                    @Override
                    public boolean areContentsTheSame(App oldItem, App newItem) {
                        return compare(oldItem, newItem) == 0;
                    }

                    @Override
                    public boolean areItemsTheSame(App item1, App item2) {
                        return compare(item1, item2) == 0;
                    }
                };
                break;
            case SORT_BY_INVERSE_ALPHABET:
                Log.d(TAG, "sort by inv alp");
                sortedListAdapterCallback = new SortedListAdapterCallback<App>(adapter) {
                    @Override
                    public int compare(App app1, App app2) {
                        return app2.getAppName().compareTo(app1.getAppName());
                    }

                    @Override
                    public boolean areContentsTheSame(App oldItem, App newItem) {
                        return compare(oldItem, newItem) == 0;
                    }

                    @Override
                    public boolean areItemsTheSame(App item1, App item2) {
                        return compare(item1, item2) == 0;
                    }
                };
                break;
            case SORT_BY_USE_FREQUENCY:
                Log.d(TAG, "sort by use freq");
                sortedListAdapterCallback = new SortedListAdapterCallback<App>(adapter) {
                    @Override
                    public int compare(App o1, App o2) {
                        return 0;
                    }

                    @Override
                    public boolean areContentsTheSame(App oldItem, App newItem) {
                        return false;
                    }

                    @Override
                    public boolean areItemsTheSame(App item1, App item2) {
                        return false;
                    }
                };
                break;
        }
        return sortedListAdapterCallback;
    }

    public void setAppTheme(String appTheme) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(APP_PREFERENCE_APP_THEME, appTheme);
        editor.apply();
    }

    public void setModel(String model) {
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
