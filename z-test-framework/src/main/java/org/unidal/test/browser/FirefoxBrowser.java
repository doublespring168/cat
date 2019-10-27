package org.unidal.test.browser;

import org.unidal.test.env.Platform;

import java.io.File;

public class FirefoxBrowser extends AbstractBrowser {
    @Override
    public String[] getCommandLine(String url) {
        return new String[]{getInstallPath().toString(), url};
    }

    private File getInstallPath() {
        return Platform.getProgramFile("mozilla firefox/firefox.exe");
    }

    public BrowserType getId() {
        return BrowserType.FIREFOX;
    }

    public boolean isAvailable() {
        return getInstallPath().exists();
    }
}
