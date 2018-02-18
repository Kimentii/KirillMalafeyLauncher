package kirill.malafey.launcher;

import android.graphics.drawable.Drawable;

public class App {
    private String appName;
    private String packageName;
    private Drawable appIcon;
    private long installationDateMS;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }

    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    public long getInstallationDateMS() {
        return installationDateMS;
    }

    public void setInstallationDateMS(long installationDateMS) {
        this.installationDateMS = installationDateMS;
    }
}
