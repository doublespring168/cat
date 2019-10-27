package org.unidal.test.mock;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class HttpServletResponseMock implements HttpServletResponse {
    private Locale m_locale;
    private String m_contentType;
    private String m_characterEncoding;
    private int m_bufferSize;

    public void addCookie(Cookie cookie) {
    }

    public boolean containsHeader(String name) {
        return false;
    }

    public String encodeURL(String url) {
        return url;
    }

    public String encodeRedirectURL(String url) {
        return url;
    }

    public String encodeUrl(String url) {
        return url;
    }

    public String encodeRedirectUrl(String url) {
        return url;
    }

    public void sendError(int sc, String msg) throws IOException {
    }

    public void sendError(int sc) throws IOException {
    }

    public void sendRedirect(String location) throws IOException {
    }

    public void setDateHeader(String name, long date) {
    }

    public void addDateHeader(String name, long date) {
    }

    public void setHeader(String name, String value) {
    }

    public void addHeader(String name, String value) {
    }

    public void setIntHeader(String name, int value) {
    }

    public void addIntHeader(String name, int value) {
    }

    public void setStatus(int sc) {
    }

    public void setStatus(int sc, String sm) {
    }

    public String getCharacterEncoding() {
        return m_characterEncoding;
    }

    public void flushBuffer() throws IOException {
    }

    public String getContentType() {
        return m_contentType;
    }

    public int getBufferSize() {
        return m_bufferSize;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    public PrintWriter getWriter() throws IOException {
        return null;
    }

    public void setContentType(String type) {
        m_contentType = type;
    }

    public Locale getLocale() {
        return m_locale;
    }

    public void setCharacterEncoding(String charset) {
        m_characterEncoding = charset;
    }

    public void setContentLength(int len) {
    }

    public boolean isCommitted() {
        return false;
    }

    public void reset() {
    }

    public void resetBuffer() {
    }

    public void setBufferSize(int size) {
        m_bufferSize = size;
    }


    public void setLocale(Locale locale) {
        m_locale = locale;
    }
}
