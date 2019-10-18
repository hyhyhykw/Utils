package com.hy.utils;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * Created time : 2019-09-26 11:42.
 *
 * @author HY
 */
public final class MyViewUtils {

    public static void setClick(@Nullable View view, @Nullable View.OnClickListener listener) {
        setClick(view, listener, true);
    }

    /**
     * 防止重复点击
     *
     * @return true表示重复点击了
     */
    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(true);
    }

    /**
     * 防止重复点击
     *
     * @param isShowMessage 是否显示消息
     * @return true表示重复点击了
     */
    public static boolean isFastDoubleClick(boolean isShowMessage) {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;

        if (timeD < 500) {
            if (isShowMessage) {
                Context appContext = UtilsProvider.getAppContext();
                if (null!=appContext){
                    Toast.makeText(appContext, R.string.picker_str_click_too_fast, Toast.LENGTH_SHORT).show();
                }
            }
            return true;
        }
        lastClickTime = time;

        return false;
    }

    private static long lastClickTime = 0;

    /**
     * view设置监听 防重复点击
     *
     * @param view          view
     * @param isShowMessage 是否显示消息
     */
    public static void setClick(@Nullable View view, @Nullable View.OnClickListener listener, boolean isShowMessage) {
        if (view == null) return;
        if (null == listener) {
            view.setOnClickListener(null);
            return;
        }

        view.setOnClickListener(v -> {
            if (isFastDoubleClick(isShowMessage)) return;
            listener.onClick(v);
        });
    }


    public static void invisible(View... views) {
        for (View view : views) {
            if (null == view) continue;
            view.setVisibility(View.INVISIBLE);
        }
    }

    public static void visible(View... views) {
        for (View view : views) {
            if (null == view) continue;
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void gone(View... views) {
        for (View view : views) {
            if (null == view) continue;
            view.setVisibility(View.GONE);
        }
    }
}
