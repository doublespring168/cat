package org.unidal.test.browser;

import org.unidal.test.env.Platform;

import java.io.File;

public class InternetExplorerBrowser extends AbstractBrowser {
    @Override
    public String[] getCommandLine(String url) {
        return new String[]{getInstallPath().toString(), url};
    }

    private File getInstallPath() {
        return Platform.getProgramFile("Internet Explorer/iexplore.exe");
    }

    public BrowserType getId() {
        return BrowserType.INTERNET_EXPLORER;
    }

    public boolean isAvailable() {
        return getInstallPath().exists();
    }
}
