package kirill.malafey.launcher.launcher;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import kirill.malafey.launcher.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        setPreferencesFromResource(R.xml.preferences, s);
    }

}
