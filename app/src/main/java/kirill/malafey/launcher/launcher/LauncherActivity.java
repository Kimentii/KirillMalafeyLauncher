package kirill.malafey.launcher.launcher;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.List;

import kirill.malafey.launcher.App;
import kirill.malafey.launcher.AppStore;
import kirill.malafey.launcher.R;
import kirill.malafey.launcher.Settings;

public class LauncherActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private PackageManager packageManager;

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, LauncherActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(Settings.getInstance(this).getCurrentThemeResource());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        packageManager = getPackageManager();
        setupDrawerContent(navigationView);
        (new AppLoader()).execute();
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        Fragment fragment = null;
        Class fragmentClass;
        switch (menuItem.getItemId()) {
            case R.id.nv_item_grid:
                fragmentClass = GridLauncherFragment.class;
                break;
            case R.id.nv_item_list:
                fragmentClass = ListLauncherFragment.class;
                break;
            case R.id.nv_item_profile:
                fragmentClass = ProfileFragment.class;
                break;
            case R.id.nv_item_settings:
                fragmentClass = SettingsFragment.class;
                break;
            default:
                fragmentClass = GridLauncherFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {

            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fl_launcher_activity, fragment).commit();

        menuItem.setChecked(true);
        setTitle(menuItem.getTitle());
        drawerLayout.closeDrawers();
    }

    private class AppLoader extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog = null;

        @Override
        protected Void doInBackground(Void... params) {
            List<ApplicationInfo> appsInfo = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
            for (ApplicationInfo info : appsInfo) {
                try {
                    if (packageManager.getLaunchIntentForPackage(info.packageName) != null) {
                        App app = new App();
                        app.setAppName(info.loadLabel(packageManager).toString());
                        app.setPackageName(info.packageName);
                        app.setAppIcon(info.loadIcon(packageManager));
                        AppStore.getInstance().addApp(app);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            progressDialog.dismiss();
            super.onPostExecute(result);
        }

        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(LauncherActivity.this, null, "Loading apps info...");
            super.onPreExecute();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Settings.getInstance(this).saveSettings();
    }
}
