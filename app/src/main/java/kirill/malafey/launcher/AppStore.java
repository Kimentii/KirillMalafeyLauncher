package kirill.malafey.launcher;

import java.util.ArrayList;
import java.util.List;

public class AppStore {
    private static AppStore appStore;
    private ArrayList<App> apps;

    private AppStore() {
        apps = new ArrayList();
    }

    public static AppStore getInstance() {
        if (appStore == null) {
            appStore = new AppStore();
        }
        return appStore;
    }

    public void addApp(App app) {
        apps.add(app);
    }

    public List<App> getApps() {
        return apps;
    }
}
