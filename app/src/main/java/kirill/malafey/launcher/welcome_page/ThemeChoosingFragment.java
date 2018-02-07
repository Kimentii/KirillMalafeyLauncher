package kirill.malafey.launcher.welcome_page;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import kirill.malafey.launcher.R;

public class ThemeChoosingFragment extends Fragment {

    private RadioButton lightThemeRadioButton;
    private RadioButton darkThemeRadioButton;
    private FrameLayout lightThemeRbWrapperFl;
    private FrameLayout darkThemeRbWrapperFl;

    public static ThemeChoosingFragment newInstance() {
        ThemeChoosingFragment fragment = new ThemeChoosingFragment();
        return fragment;
    }

    private void initViews(View v) {
        lightThemeRadioButton = v.findViewById(R.id.radio_button_light_theme);
        darkThemeRadioButton = v.findViewById(R.id.radio_button_dark_theme);
        final RadioButtonListener radioButtonListener = new RadioButtonListener();
        lightThemeRadioButton.setOnClickListener(radioButtonListener);
        darkThemeRadioButton.setOnClickListener(radioButtonListener);
        lightThemeRbWrapperFl = v.findViewById(R.id.fl_light_theme_rb_wrapper);
        darkThemeRbWrapperFl = v.findViewById(R.id.fl_dark_theme_rb_wrapper);

        lightThemeRbWrapperFl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return lightThemeRadioButton.onTouchEvent(motionEvent);
            }
        });
        darkThemeRbWrapperFl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return darkThemeRadioButton.onTouchEvent(motionEvent);
            }
        });
    }

    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_theme_choosing, container, false);
        initViews(v);
        return v;
    }

    class RadioButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.radio_button_light_theme:
                    lightThemeRadioButton.setChecked(true);
                    darkThemeRadioButton.setChecked(false);
                    break;
                case R.id.radio_button_dark_theme:
                    lightThemeRadioButton.setChecked(false);
                    darkThemeRadioButton.setChecked(true);
                    break;
            }
        }
    }
}
