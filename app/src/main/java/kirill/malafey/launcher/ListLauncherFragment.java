package kirill.malafey.launcher;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListLauncherFragment extends Fragment {
    public static ListLauncherFragment newInstance() {
        ListLauncherFragment fragment = new ListLauncherFragment();
        return fragment;
    }

    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_launcher, container, false);
    }
}
