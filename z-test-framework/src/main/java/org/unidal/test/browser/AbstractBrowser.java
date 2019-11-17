package org.unidal.test.browser;

import com.doublespring.log.LogUtil;
import org.codehaus.plexus.logging.LogEnabled;
import org.codehaus.plexus.logging.Logger;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.CommandLineUtils.StringStreamConsumer;
import org.codehaus.plexus.util.cli.Commandline;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

public abstract class AbstractBrowser implements Browser, LogEnabled {
    private Logger m_logger;

    public void display(String html) {
        display(html, "utf-8");
    }

    public void display(String html, String charset) {
        URL url = saveToTemporaryFile(html, false, charset);

        display(url);
    }

    public void display(URL url) {
        if (!isAvailable()) {
            throw new RuntimeException(getId() + " is unavailable.");
        }

        try {
            String[] commandLine = getCommandLine(url.toExternalForm());
            Commandline cmdLine = new Commandline();
            StringStreamConsumer consumer = new StringStreamConsumer();

            cmdLine.addArguments(commandLine);

            CommandLineUtils.executeCommandLine(cmdLine, consumer, consumer);

            String output = consumer.getOutput();

            if (output != null && output.length() > 0) {
                LogUtil.info(output);
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error when display page(" + url.toExternalForm() + ")", e);
        }
    }

    private URL saveToTemporaryFile(String html, boolean deleteOnExit, String charset) {
        try {
            File tempFile = File.createTempFile("test", ".html");

            if (deleteOnExit) {
                tempFile.deleteOnExit();
            }

            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(tempFile), charset);

            out.write(html);
            out.close();

            return tempFile.getCanonicalFile().toURI().toURL();
        } catch (Exception e) {
            throw new RuntimeException("Error when writing to temporary file: " + e.getMessage(), e);
        }
    }

    public abstract String[] getCommandLine(String url);

    public void enableLogging(Logger logger) {
        m_logger = logger;
    }

    protected Logger getLogger() {
        return m_logger;
    }
}
