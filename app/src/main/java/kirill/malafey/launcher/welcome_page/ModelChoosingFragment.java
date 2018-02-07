package kirill.malafey.launcher.welcome_page;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import kirill.malafey.launcher.LauncherActivity;
import kirill.malafey.launcher.R;

public class ModelChoosingFragment extends Fragment {
    private RadioButton standardModelRadioButton;
    private RadioButton fullModelRadioButton;
    private FrameLayout standardModelRbWrapperFl;
    private FrameLayout fullModelRbWrapperFl;
    private Button nextButton;

    public static ModelChoosingFragment newInstance() {
        ModelChoosingFragment fragment = new ModelChoosingFragment();
        return fragment;
    }

    private void initViews(View v) {
        standardModelRadioButton = v.findViewById(R.id.radio_button_standard_model);
        fullModelRadioButton = v.findViewById(R.id.radio_button_full_model);
        final RadioButtonListener radioButtonListener = new RadioButtonListener();
        standardModelRadioButton.setOnClickListener(radioButtonListener);
        fullModelRadioButton.setOnClickListener(radioButtonListener);
        standardModelRbWrapperFl = v.findViewById(R.id.fl_standard_model_rd_wrapper);
        fullModelRbWrapperFl = v.findViewById(R.id.fl_full_model_rd_wrapper);
        standardModelRbWrapperFl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return standardModelRadioButton.onTouchEvent(motionEvent);
            }
        });
        fullModelRbWrapperFl.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return fullModelRadioButton.onTouchEvent(motionEvent);
            }
        });

        nextButton = v.findViewById(R.id.button_next);
        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final Intent nextActivity = LauncherActivity.newIntent(getContext());
                startActivity(nextActivity);
            }
        });
    }

    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_model_choosing, container, false);
        initViews(v);
        return v;
    }

    class RadioButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.radio_button_full_model:
                    fullModelRadioButton.setChecked(true);
                    standardModelRadioButton.setChecked(false);
                    break;
                case R.id.radio_button_standard_model:
                    fullModelRadioButton.setChecked(false);
                    standardModelRadioButton.setChecked(true);
                    break;
            }
        }
    }
}
