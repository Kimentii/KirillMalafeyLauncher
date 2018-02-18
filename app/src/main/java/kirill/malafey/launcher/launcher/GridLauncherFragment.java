package kirill.malafey.launcher.launcher;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import kirill.malafey.launcher.App;
import kirill.malafey.launcher.AppSettings;
import kirill.malafey.launcher.AppStore;
import kirill.malafey.launcher.R;

public class GridLauncherFragment extends Fragment {
    private String TAG = "TAG";
    private AppAdapter appAdapter;
    private RecyclerView recyclerView;
    private static Observable appsListReadyObservable;

    public static GridLauncherFragment newInstance(Observable appsListReadyObserver) {
        GridLauncherFragment.appsListReadyObservable = appsListReadyObserver;
        GridLauncherFragment fragment = new GridLauncherFragment();
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid_launcher, container, false);
        List<App> list = AppStore.getInstance().getApps();
        Log.d(TAG, "apps list size(GridLauncherFragment): " + list.size());
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_grid);
        if (getActivity().getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                    AppSettings.getInstance(getContext()).getSpanCountPortrait()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(),
                    AppSettings.getInstance(getContext()).getSpanCountLandscape()));
        }
        updateUI();

        return view;
    }

    private void updateUI() {
        AppStore appStore = AppStore.getInstance();
        List<App> appsList = appStore.getApps();
        if (appAdapter == null) {
            appAdapter = new AppAdapter(appsList);
            recyclerView.setAdapter(appAdapter);
            appsListReadyObservable.addObserver(new Observer() {
                @Override
                public void update(Observable observable, Object o) {
                    System.out.println("FUCK");
                    Log.d(TAG, "Observer work");
                    AppStore appStore = AppStore.getInstance();
                    List<App> appsList = appStore.getApps();
                    appAdapter.setApps(appsList);
                    appAdapter.notifyDataSetChanged();
                }
            });
            Log.d("TAG", "Set observer");

        } else {
            appAdapter.setApps(appsList);
            appAdapter.notifyDataSetChanged();
        }
    }


    private class AppHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private App app;
        private TextView appNameTextView;
        private ImageView appIconImageView;

        public void bindApp(App app, int position) {
            this.app = app;
            Log.d(TAG, "showing list.");
            appNameTextView.setText(app.getAppName());
            appIconImageView.setImageDrawable(app.getAppIcon());
        }

        public AppHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            appNameTextView = itemView.findViewById(R.id.tv_app_name);
            appIconImageView = itemView.findViewById(R.id.iv_app_icon);
        }

        @Override
        public void onClick(View view) {
            Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(app.getPackageName());
            startActivity(intent);
        }

        @Override
        public boolean onLongClick(View view) {
            showPopup(view, app);
            return false;
        }
    }

    private class AppAdapter extends RecyclerView.Adapter<AppHolder> {
        private List<App> appsList;

        public AppAdapter(List<App> appsList) {
            this.appsList = appsList;
        }

        @Override
        public GridLauncherFragment.AppHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.grid_item_app, parent, false);
            return new AppHolder(view);
        }

        @Override
        public void onBindViewHolder(AppHolder holder, int position) {
            App app = appsList.get(position);
            holder.bindApp(app, position);
        }

        public void setApps(List<App> appsList) {
            this.appsList = appsList;
        }

        @Override
        public int getItemCount() {
            return appsList.size();
        }
    }

    public void showPopup(View v, App app) {
        PopupMenu popup = new PopupMenu(getContext(), v);
        popup.setOnMenuItemClickListener(new PopupMenuItemListener(app));
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu_items, popup.getMenu());
        popup.show();
    }

    private class PopupMenuItemListener implements PopupMenu.OnMenuItemClickListener {
        private static final String PACKAGE_PREFIX_URI = "package:";
        private App app;

        PopupMenuItemListener(App app) {
            this.app = app;
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            Intent intent;
            switch (item.getItemId()) {
                case R.id.menu_delete:
                    intent = new Intent(Intent.ACTION_DELETE);
                    intent.setData(Uri.parse(PACKAGE_PREFIX_URI + app.getPackageName()));
                    startActivity(intent);
                    break;
                case R.id.menu_frequency:

                    break;
                case R.id.menu_info:
                    intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    intent.setData(Uri.parse(PACKAGE_PREFIX_URI + app.getPackageName()));
                    startActivity(intent);
                    break;
            }
            return false;
        }
    }
}
