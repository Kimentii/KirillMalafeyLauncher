package kirill.malafey.launcher.welcome_page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import kirill.malafey.launcher.R;

public class ThemeChoosingActivity extends AppCompatActivity {

    private Button nextButton;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, ThemeChoosingActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_choosing);
        nextButton = (Button) findViewById(R.id.activity_theme_choosing_next_button);

    }

    class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.activity_description_next_button:

                    break;
            }
        }
    }
}
