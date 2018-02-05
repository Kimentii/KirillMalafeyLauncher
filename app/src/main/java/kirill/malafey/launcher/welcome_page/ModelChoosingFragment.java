package kirill.malafey.launcher.welcome_page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import kirill.malafey.launcher.R;

public class ModelChoosingFragment extends Fragment {
    private RadioButton rightRadioButton;

    public static ModelChoosingFragment newInstance() {
        ModelChoosingFragment fragment = new ModelChoosingFragment();
        return fragment;
    }

    private void initViews(View v) {
        rightRadioButton = v.findViewById(R.id.right_radio_button);
        rightRadioButton.setText(Html.fromHtml(getString(R.string.radio_button_text)));
    }

    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_model_choosing, container, false);
        initViews(v);
        return v;
    }
}
