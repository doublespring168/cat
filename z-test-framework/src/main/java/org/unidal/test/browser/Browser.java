package org.unidal.test.browser;

import java.net.URL;

public interface Browser {
    void display(String html);

    void display(String html, String charset);

    void display(URL url);

    BrowserType getId();

    boolean isAvailable();
}
