package com.hy.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.SparseIntArray;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.Nullable;

/**
 * Created time : 2019-09-26 10:56.
 *
 * @author HY
 */
public final class SizeUtils {

    private SizeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    private static final SparseIntArray dps = new SparseIntArray();

    /**
     * dp转px
     *
     * @param dpValue dp值
     * @return px值
     */
    public static int dp2px(Context context, int dpValue) {
        int i = dps.get(dpValue, -1);
        if (i != -1) return i;

        final float scale = context.getResources().getDisplayMetrics().density;
        int round = Math.round(dpValue * scale);
        dps.put(dpValue, i);
        return round;
    }

    /**
     * px转dp
     *
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转px
     *
     * @param spValue sp值
     * @return px值
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * px转sp
     *
     * @param pxValue px值
     * @return sp值
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 各种单位转换
     * <p>该方法存在于TypedValue</p>
     *
     * @param unit    单位
     * @param value   值
     * @param metrics DisplayMetrics
     * @return 转换结果
     */
    public static float applyDimension(int unit, float value, DisplayMetrics metrics) {
        switch (unit) {
            case TypedValue.COMPLEX_UNIT_PX:
                return value;
            case TypedValue.COMPLEX_UNIT_DIP:
                return value * metrics.density;
            case TypedValue.COMPLEX_UNIT_SP:
                return value * metrics.scaledDensity;
            case TypedValue.COMPLEX_UNIT_PT:
                return value * metrics.xdpi * (1.0f / 72);
            case TypedValue.COMPLEX_UNIT_IN:
                return value * metrics.xdpi;
            case TypedValue.COMPLEX_UNIT_MM:
                return value * metrics.xdpi * (1.0f / 25.4f);
        }
        return 0;
    }


    private static int statusBarHeight = -1;

    /**
     * 获取状态栏高度
     *
     * @return 通知栏高度
     */
    public static int getStatusBarHeight(@Nullable Context context) {
        if (statusBarHeight != -1) return statusBarHeight;
        if (null == context) return 0;
        try {
            Resources resources = context.getResources();
            int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = resources.getDimensionPixelSize(resourceId);
            }
        } catch (Exception ignore) {
        }

        return statusBarHeight;
    }


    private static int screenHeight = -1;

    /**
     * 获取屏幕高度，包括底部导航栏
     */
    public static int getScreenHeight(Context context) {
        if (screenHeight != -1) return screenHeight;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenHeight = displayMetrics.heightPixels;
        return screenHeight;
    }


    private static int screenWidth = -1;

    /**
     * 获取屏幕宽度，不包括右侧导航栏
     */
    public static int getScreenWidth(Context context) {
        if (screenWidth != -1) return screenWidth;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenWidth = displayMetrics.widthPixels;
        return screenWidth;
    }


    private static int realScreenHeight = -1;

    public static int getScreenRealHeight(Context context) {
        if (realScreenHeight != -1) return realScreenHeight;
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager == null) return 0;

        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getRealSize(point);
        realScreenHeight = point.y;
        return realScreenHeight;
    }


}
