package kirill.malafey.launcher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GridLauncherFragment extends Fragment {
    public static GridLauncherFragment newInstance() {
        GridLauncherFragment fragment = new GridLauncherFragment();
        return fragment;
    }

    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_grid_launcher, container, false);
    }
}
