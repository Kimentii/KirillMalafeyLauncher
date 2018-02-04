package kirill.malafey.launcher.welcome_page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kirill.malafey.launcher.R;

public class ThemeChoosingFragment extends Fragment {
    public static ThemeChoosingFragment newInstance() {
        ThemeChoosingFragment fragment = new ThemeChoosingFragment();
        return fragment;
    }

    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_theme_choosing, container, false);
    }
}
