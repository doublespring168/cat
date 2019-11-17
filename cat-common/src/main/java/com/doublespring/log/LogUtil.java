package com.doublespring.log;

import java.util.function.Supplier;

/**
 * 日志管理, 使用此 utils 获取 log, 不要在类中使用 LoggerFactory.getLogger 的方式!
 */
public final class LogUtil {


    //public static final Logger ROOT_LOG = LoggerFactory.getLogger("root");
    public static final CatLogger ROOT_LOG = CatLogger.getInstance();


    /**
     * 功能说明：重载方法
     * Author：zts
     * Date：2017-12-15 11:53
     */
    public static void info(String tip, Object... msgs) {

        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        int lineNumber = stackTraceElement.getLineNumber();
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();

        StringBuilder tmp = new StringBuilder();

        tmp.append(className);
        tmp.append(".");
        tmp.append(methodName);
        tmp.append(",");

        tmp.append(lineNumber);
        tmp.append(":");

        tmp.append(tip);

        for (Object msg : msgs) {
            if (msg != null) {
                tmp.append(",");
                tmp.append(msg instanceof Supplier ? ((Supplier) msg).get() : msg);
            }
        }

        ROOT_LOG.info(tmp.toString());

    }


}
