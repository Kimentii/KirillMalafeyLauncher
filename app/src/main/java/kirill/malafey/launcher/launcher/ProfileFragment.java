package kirill.malafey.launcher.launcher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kirill.malafey.launcher.R;

public class ProfileFragment extends Fragment {

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, ProfileFragment.class);
        return intent;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }
}
