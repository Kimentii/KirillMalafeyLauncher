package kirill.malafey.launcher.welcome_page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import kirill.malafey.launcher.R;

public class WelcomePageActivity extends AppCompatActivity {
    private static final int NUMBER_OF_FRAGMENTS = 4;

    private ViewPager viewPager;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, WelcomePageActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager = findViewById(R.id.welcome_page_view_pager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return HelloFragment.newInstance();
                }
                return HelloFragment.newInstance();
            }

            @Override
            public int getCount() {
                return NUMBER_OF_FRAGMENTS;
            }
        });
    }
}
