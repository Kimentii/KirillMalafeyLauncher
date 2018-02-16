package kirill.malafey.launcher.welcome_page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import kirill.malafey.launcher.R;
import kirill.malafey.launcher.Settings;
import kirill.malafey.launcher.launcher.LauncherActivity;

public class WelcomePageActivity extends AppCompatActivity {
    private static final int NUMBER_OF_FRAGMENTS = 4;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, WelcomePageActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (!Settings.getInstance(getApplicationContext()).isFirstStart()) {
            Intent nextActivity = LauncherActivity.newIntent(getApplicationContext());
            startActivity(nextActivity);
            finish();
        }
        setTheme(Settings.getInstance(this).getCurrentThemeResource());
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_welcome_page);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager = findViewById(R.id.welcome_page_view_pager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return HelloFragment.newInstance();
                    case 1:
                        return DescriptionFragment.newInstance();
                    case 2:
                        return ThemeChoosingFragment.newInstance();
                    case 3:
                        return ModelChoosingFragment.newInstance();
                }
                return HelloFragment.newInstance();
            }

            @Override
            public int getCount() {
                return NUMBER_OF_FRAGMENTS;
            }
        });

        tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
