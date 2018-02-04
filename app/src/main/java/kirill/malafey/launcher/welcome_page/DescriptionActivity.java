package kirill.malafey.launcher.welcome_page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import kirill.malafey.launcher.R;

public class DescriptionActivity extends AppCompatActivity {

    private Button nextButton;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, DescriptionActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        final Listener listener = new Listener();
        nextButton = (Button) findViewById(R.id.activity_description_next_button);
        nextButton.setOnClickListener(listener);
    }

    class Listener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.activity_description_next_button:
                    final Intent nextActivityIntent = ThemeChoosingActivity.newIntent(getApplicationContext());
                    startActivity(nextActivityIntent);
                    break;
            }
        }
    }
}
