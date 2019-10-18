package com.hy.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import java.util.Formatter;

import androidx.annotation.NonNull;

/**
 * Log工具，类似android.util.Log  tag自动产生，格式:
 * customTagPrefix:className.methodName(Line:lineNumber),
 * customTagPrefix为空时只输出：className.methodName(Line:lineNumber)。
 * http://blog.csdn.net/finddreams
 */
@SuppressWarnings({"unused"})
public final class Logger {

    private Logger() {
    }

    /**
     * 容许打印日志的类型，默认是true，设置为false则不打印
     * Allowed to print the type of log, the default is true, set to false does not print
     */
    public static boolean DEBUG = true;


    @SuppressLint("DefaultLocale")
    private static String generateTag(StackTraceElement caller) {
        String tag = "%s.%s(Line:%d)"; // 占位符
        String callerClazzName = caller.getClassName(); // 获取到类名
        callerClazzName = callerClazzName.substring(callerClazzName
                .lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(),
                caller.getLineNumber()); // 替换
        String customTagPrefix = UtilsProvider.getAppName();
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":"
                + tag;
        return tag;
    }

    private static void logLarge(int logLevel, String tag, String log, Throwable tr) {
        int i = 0;
        String msg = log;
        switch (logLevel) {
            case Log.INFO:
                while (msg.length() >= MAX_LENGTH || msg.length() > 0) {
                    if (msg.length() > MAX_LENGTH) {
                        String part = msg.substring(0, MAX_LENGTH);

                        msg = msg.substring(MAX_LENGTH);

                        Log.i(tag, "[Log片段" + i + ":]" + part);
                    } else {
                        Log.i(tag, "[Log片段" + i + ":]" + msg, tr);
                        break;
                    }
                    i++;
                }
                break;
            case Log.VERBOSE:
                while (msg.length() >= MAX_LENGTH || msg.length() > 0) {
                    if (msg.length() > MAX_LENGTH) {
                        String part = msg.substring(0, MAX_LENGTH);
                        msg = msg.substring(MAX_LENGTH);

                        Log.v(tag, "[Log片段" + i + ":]" + part);
                    } else {
                        Log.v(tag, "[Log片段" + i + ":]" + msg, tr);
                        break;
                    }
                    i++;
                }
                break;
            case Log.DEBUG:
                while (msg.length() >= MAX_LENGTH || msg.length() > 0) {
                    if (msg.length() > MAX_LENGTH) {
                        String part = msg.substring(0, MAX_LENGTH);

                        msg = msg.substring(MAX_LENGTH);
                        Log.d(tag, "[Log片段" + i + ":]" + part);
                    } else {
                        Log.d(tag, "[Log片段" + i + ":]" + msg, tr);
                        break;
                    }
                    i++;
                }

                break;
            case Log.WARN:
                while (msg.length() >= MAX_LENGTH || msg.length() > 0) {
                    if (msg.length() > MAX_LENGTH) {
                        String part = msg.substring(0, MAX_LENGTH);
                        msg = msg.substring(MAX_LENGTH);

                        Log.w(tag, "[Log片段" + i + ":]" + part);
                    } else {
                        Log.w(tag, "[Log片段" + i + ":]" + msg, tr);
                        break;
                    }
                    i++;
                }
                break;
            case Log.ERROR:
                while (msg.length() >= MAX_LENGTH || msg.length() > 0) {
                    if (msg.length() > MAX_LENGTH) {
                        String part = msg.substring(0, MAX_LENGTH);

                        msg = msg.substring(MAX_LENGTH);
                        Log.e(tag, "[Log片段" + i + ":]" + part);
                    } else {
                        Log.e(tag, "[Log片段" + i + ":]" + msg, tr);
                        break;
                    }
                    i++;
                }

                break;
            case Log.ASSERT:
                while (msg.length() >= MAX_LENGTH || msg.length() > 0) {

                    if (msg.length() > MAX_LENGTH) {
                        String part = msg.substring(0, MAX_LENGTH);

                        msg = msg.substring(MAX_LENGTH);
                        Log.wtf(tag, "[Log片段" + i + ":]" + part);
                    } else {
                        Log.wtf(tag, "[Log片段" + i + ":]" + msg, tr);
                        break;
                    }
                    i++;
                }
                break;
        }
    }

    private static void logLarge(int logLevel, String tag, String log) {

        int i = 1;
        String msg = log;

        switch (logLevel) {
            case Log.INFO:
                while (msg.length() >= MAX_LENGTH || msg.length() > 0) {
                    if (msg.length() > MAX_LENGTH) {
                        String part = msg.substring(0, MAX_LENGTH);

                        msg = msg.substring(MAX_LENGTH);
                        Log.i(tag, "[Log片段" + i + ":]" + part);
                    } else {
                        Log.i(tag, "[Log片段" + i + ":]" + msg);
                        break;
                    }
                    i++;
                }
                break;
            case Log.VERBOSE:
                while (msg.length() >= MAX_LENGTH || msg.length() > 0) {
                    if (msg.length() > MAX_LENGTH) {
                        String part = msg.substring(0, MAX_LENGTH);

                        msg = msg.substring(MAX_LENGTH);
                        Log.v(tag, "[Log片段" + i + ":]" + part);
                    } else {
                        Log.v(tag, "[Log片段" + i + ":]" + msg);
                        break;
                    }
                    i++;
                }
                break;
            case Log.DEBUG:
                while (msg.length() >= MAX_LENGTH || msg.length() > 0) {
                    if (msg.length() > MAX_LENGTH) {
                        String part = msg.substring(0, MAX_LENGTH);

                        msg = msg.substring(MAX_LENGTH);
                        Log.d(tag, "[Log片段" + i + ":]" + part);
                    } else {
                        Log.d(tag, "[Log片段" + i + ":]" + msg);
                        break;
                    }
                    i++;
                }

                break;
            case Log.WARN:
                while (msg.length() >= MAX_LENGTH || msg.length() > 0) {
                    if (msg.length() > MAX_LENGTH) {
                        String part = msg.substring(0, MAX_LENGTH);

                        msg = msg.substring(MAX_LENGTH);
                        Log.w(tag, "[Log片段" + i + ":]" + part);
                    } else {
                        Log.w(tag, "[Log片段" + i + ":]" + msg);
                        break;
                    }
                    i++;
                }
                break;
            case Log.ERROR:
                while (msg.length() >= MAX_LENGTH || msg.length() > 0) {
                    if (msg.length() > MAX_LENGTH) {
                        String part = msg.substring(0, MAX_LENGTH);

                        msg = msg.substring(MAX_LENGTH);
                        Log.e(tag, "[Log片段" + i + ":]" + part);
                    } else {
                        Log.e(tag, "[Log片段" + i + ":]" + msg);
                        break;
                    }
                    i++;
                }

                break;
            case Log.ASSERT:
                while (msg.length() >= MAX_LENGTH || msg.length() > 0) {
                    if (msg.length() > MAX_LENGTH) {
                        String part = msg.substring(0, MAX_LENGTH);

                        msg = msg.substring(MAX_LENGTH);
                        Log.wtf(tag, "[Log片段" + i + ":]" + part);
                    } else {
                        Log.wtf(tag, "[Log片段" + i + ":]" + msg);
                        break;
                    }
                    i++;
                }
                break;
        }
    }

    private static final int MAX_LENGTH = 3900;

    public static void d(String log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        String msg = String.valueOf(log);
        boolean isLarge = msg.length() >= MAX_LENGTH;
        if (!isLarge) {

            Log.d(tag, msg);
            return;
        }
        logLarge(Log.DEBUG, tag, msg);
    }

    public static void d(String log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        String msg = String.valueOf(log);
        boolean isLarge = msg.length() >= MAX_LENGTH;
        if (!isLarge) {
            Log.d(tag, msg, tr);
            return;
        }
        logLarge(Log.DEBUG, tag, msg, tr);
    }

    public static void d(int log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.d(tag, String.valueOf(log));
    }

    public static void d(int log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        Log.d(tag, String.valueOf(log), tr);
    }

    public static void d(byte log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.d(tag, String.valueOf(log));
    }

    public static void d(byte log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        Log.d(tag, String.valueOf(log), tr);
    }

    public static void d(short log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.d(tag, String.valueOf(log));
    }

    public static void d(short log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        Log.d(tag, String.valueOf(log), tr);
    }

    public static void d(long log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.d(tag, String.valueOf(log));
    }

    public static void d(long log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        Log.d(tag, String.valueOf(log), tr);
    }

    public static void d(boolean log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.d(tag, String.valueOf(log));
    }

    public static void d(boolean log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        Log.d(tag, String.valueOf(log), tr);
    }

    public static void d(char log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.d(tag, String.valueOf(log));
    }

    public static void d(char log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        Log.d(tag, String.valueOf(log), tr);
    }

    public static void d(Object log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;
        if (!isLarge) {
            Log.d(tag, value);
            return;
        }
        logLarge(Log.DEBUG, tag, value);

    }

    public static void d(Object log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;
        if (!isLarge) {
            Log.d(tag, value, tr);
            return;
        }
        logLarge(Log.DEBUG, tag, value, tr);

//        Log.d(tag, String.valueOf(log), tr);
    }

    public static void d(float log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.d(tag, String.valueOf(log));
    }

    public static void d(float log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        Log.d(tag, String.valueOf(log), tr);
    }

    public static void d(double log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.d(tag, String.valueOf(log));
    }

    public static void d(double log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        Log.d(tag, String.valueOf(log), tr);
    }
//#########################################################################################################

    public static void e(String log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.e(tag, value);
            return;
        }
        logLarge(Log.ERROR, tag, value);

    }

    public static void e(String log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);


        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.e(tag, value, tr);
            return;
        }
        logLarge(Log.ERROR, tag, value, tr);

    }

    public static void e(int log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(int log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(short log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);


    }

    public static void e(short log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(byte log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(byte log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(long log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(long log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(float log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(float log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(double log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(double log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(char log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(char log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(boolean log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(boolean log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String msg = String.valueOf(log);
        Log.e(tag, msg);

    }

    public static void e(Object log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);


        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.e(tag, value);
            return;
        }
        logLarge(Log.ERROR, tag, value);
    }

    public static void e(Object log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.e(tag, value, tr);
            return;
        }
        logLarge(Log.ERROR, tag, value, tr);

    }

    public static void i(String log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.i(tag, value);
            return;
        }
        logLarge(Log.INFO, tag, value);

    }

    public static void i(String log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.i(tag, value, tr);
            return;
        }
        logLarge(Log.INFO, tag, value, tr);

//        Log.i(tag, String.valueOf(log), tr);
    }

    public static void i(int log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log));
    }

    public static void i(int log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log), tr);
    }

    public static void i(short log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log));
    }

    public static void i(short log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log), tr);
    }

    public static void i(byte log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log));
    }

    public static void i(byte log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log), tr);
    }

    public static void i(long log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log));
    }

    public static void i(long log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log), tr);
    }

    public static void i(boolean log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log));
    }

    public static void i(boolean log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log), tr);
    }

    public static void i(char log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log));
    }

    public static void i(char log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log), tr);
    }

    public static void i(float log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log));
    }

    public static void i(float log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log), tr);
    }

    public static void i(double log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log));
    }

    public static void i(double log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.i(tag, String.valueOf(log), tr);
    }

    public static void i(Object log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.i(tag, value);
            return;
        }
        logLarge(Log.INFO, tag, value);

//        Log.i(tag, String.valueOf(log));
    }

    public static void i(Object log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.i(tag, value, tr);
            return;
        }
        logLarge(Log.INFO, tag, value, tr);
//        Log.i(tag, String.valueOf(log), tr);
    }


    public static void v(String log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.v(tag, value);
            return;
        }
        logLarge(Log.VERBOSE, tag, value);

//        Log.v(tag, String.valueOf(log));
    }

    public static void v(String log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.v(tag, value, tr);
            return;
        }
        logLarge(Log.VERBOSE, tag, value, tr);
//        Log.v(tag, String.valueOf(log), tr);
    }

    public static void v(int log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log));
    }

    public static void v(int log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log), tr);
    }

    public static void v(short log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log));
    }

    public static void v(short log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log), tr);
    }

    public static void v(byte log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log));
    }

    public static void v(byte log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log), tr);
    }

    public static void v(long log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log));
    }

    public static void v(long log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log), tr);
    }

    public static void v(boolean log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log));
    }

    public static void v(boolean log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log), tr);
    }

    public static void v(char log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log));
    }

    public static void v(char log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log), tr);
    }

    public static void v(float log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log));
    }

    public static void v(float log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log), tr);
    }

    public static void v(double log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log));
    }

    public static void v(double log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.v(tag, String.valueOf(log), tr);
    }

    public static void v(Object log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.v(tag, value);
            return;
        }
        logLarge(Log.VERBOSE, tag, value);
//        Log.v(tag, String.valueOf(log));
    }

    public static void v(Object log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.v(tag, value, tr);
            return;
        }
        logLarge(Log.VERBOSE, tag, value, tr);
//        Log.v(tag, String.valueOf(log), tr);
    }

    public static void w(String log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);


        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.w(tag, value);
            return;
        }
        logLarge(Log.WARN, tag, value);
//        Log.w(tag, String.valueOf(log));
    }

    public static void w(String log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.w(tag, value, tr);
            return;
        }
        logLarge(Log.WARN, tag, value, tr);
//        Log.w(tag, String.valueOf(log), tr);

    }

    public static void w(int log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log));
    }

    public static void w(int log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log), tr);
    }

    public static void w(short log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log));
    }

    public static void w(short log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log), tr);
    }

    public static void w(byte log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log));
    }

    public static void w(byte log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log), tr);
    }

    public static void w(long log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log));
    }

    public static void w(long log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log), tr);
    }

    public static void w(boolean log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log));
    }

    public static void w(boolean log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log), tr);
    }

    public static void w(char log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log));
    }

    public static void w(char log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log), tr);
    }

    public static void w(float log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log));
    }

    public static void w(float log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log), tr);
    }

    public static void w(double log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log));
    }

    public static void w(double log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.w(tag, String.valueOf(log), tr);
    }

    public static void w(Object log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);
        if (log instanceof Throwable) {
            Throwable tr = (Throwable) log;
            Log.w(tag, tr);
        } else {
            String value = String.valueOf(log);
            boolean isLarge = value.length() >= MAX_LENGTH;

            if (!isLarge) {
                Log.w(tag, value);
                return;
            }
            logLarge(Log.WARN, tag, value);

//            Log.w(tag, String.valueOf(log));
        }
    }

    public static void w(Object log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.w(tag, value, tr);
            return;
        }
        logLarge(Log.WARN, tag, value, tr);

//        Log.w(tag, String.valueOf(log), tr);
    }

    public static void wtf(String log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.wtf(tag, value);
            return;
        }
        logLarge(Log.ASSERT, tag, value);
//        Log.wtf(tag, String.valueOf(log));
    }

    public static void wtf(String log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.wtf(tag, value, tr);
            return;
        }
        logLarge(Log.ASSERT, tag, value, tr);
//        Log.wtf(tag, String.valueOf(log), tr);
    }

    public static void wtf(int log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log));
    }

    public static void wtf(int log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log), tr);
    }

    public static void wtf(short log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log));
    }

    public static void wtf(short log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log), tr);
    }

    public static void wtf(byte log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log));
    }

    public static void wtf(byte log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log), tr);
    }

    public static void wtf(long log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log));
    }

    public static void wtf(long log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log), tr);
    }

    public static void wtf(char log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log));
    }

    public static void wtf(char log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log), tr);
    }

    public static void wtf(boolean log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log));
    }

    public static void wtf(boolean log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log), tr);
    }

    public static void wtf(float log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log));
    }

    public static void wtf(float log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log), tr);
    }

    public static void wtf(double log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log));
    }

    public static void wtf(double log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        Log.wtf(tag, String.valueOf(log), tr);
    }

    public static void wtf(Object log) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        if (log instanceof Throwable) {
            Throwable tr = (Throwable) log;
            Log.wtf(tag, tr);
        } else {
            String value = String.valueOf(log);
            boolean isLarge = value.length() >= MAX_LENGTH;

            if (!isLarge) {
                Log.wtf(tag, value);
                return;
            }
            logLarge(Log.ASSERT, tag, value);

//            Log.wtf(tag, String.valueOf(log));
        }
    }

    public static void wtf(Object log, Throwable tr) {
        if (!DEBUG)
            return;
        StackTraceElement caller = getCallerStackTraceElement();
        String tag = generateTag(caller);

        String value = String.valueOf(log);
        boolean isLarge = value.length() >= MAX_LENGTH;

        if (!isLarge) {
            Log.wtf(tag, value, tr);
            return;
        }
        logLarge(Log.ASSERT, tag, value, tr);
//        Log.wtf(tag, String.valueOf(log), tr);
    }

    private static StackTraceElement getCallerStackTraceElement() {
        return Thread.currentThread().getStackTrace()[4];
    }


    /**
     * A little trick to reuse a formatter in the same thread
     */
    private static class ReusableFormatter {

        private final Formatter formatter;
        private final StringBuilder builder;

        ReusableFormatter() {
            builder = new StringBuilder();
            formatter = new Formatter(builder);
        }

        String format(String msg, Object... args) {
            formatter.format(msg, args);
            String s = builder.toString();
            builder.setLength(0);
            return s;
        }
    }

    private static final ThreadLocal<ReusableFormatter> thread_local_formatter = new ThreadLocal<ReusableFormatter>() {
        @NonNull
        protected ReusableFormatter initialValue() {
            return new ReusableFormatter();
        }
    };

    public static String format(String msg, Object... args) {
        ReusableFormatter formatter = thread_local_formatter.get();
        if (formatter == null) return "";
        return formatter.format(msg, args);
    }


}
