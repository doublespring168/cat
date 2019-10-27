package org.unidal.test.browser;

import org.unidal.test.env.Platform;

import java.io.File;

public class OperaBrowser extends AbstractBrowser {
    @Override
    public String[] getCommandLine(String url) {
        return new String[]{getInstallPath().toString(), url};
    }

    private File getInstallPath() {
        return Platform.getProgramFile("opera/opera.exe");
    }

    public BrowserType getId() {
        return BrowserType.OPERA;
    }

    public boolean isAvailable() {
        return getInstallPath().exists();
    }
}
