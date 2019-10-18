package com.hy.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created time : 2019-09-26 11:02.
 *
 * @author HY
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public class AppUtils {
    private static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    public static void postDelay(Runnable action, long delay) {
        MAIN_HANDLER.postDelayed(action, delay);
    }

    public static void post(Runnable action) {
        postDelay(action, 0);
    }


    //隐藏键盘
    public static void hideInput(Activity activity) {
        View currentFocus = activity.getCurrentFocus();

        InputMethodManager systemService = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != systemService && null != currentFocus) {
            systemService.hideSoftInputFromWindow(
                    currentFocus.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS
            );
        }
    }

    /**
     * 显示键盘
     */
    public static void showInput(View view) {
        InputMethodManager im = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null == im) return;
        im.showSoftInput(view, 0);
    }

    /**
     * 隐藏虚拟键盘
     *
     * @param view view
     */
    public static void hideInput(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null == imm) return;
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
    }

    /**
     * 关闭dialog中打开的键盘
     */
    public static void hideInput(Dialog dialog) {
        Window window = dialog.getWindow();
        if (window == null) return;
        View view = window.peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) dialog.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * 判断网络是否连接
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == manager) return false;
        @SuppressLint("MissingPermission")
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    /**
     * 打开网页
     *
     * @param fragment fragment
     * @param address  url
     */
    public static void startWeb(Fragment fragment, @Nullable String address) {
        Context context = fragment.getContext();
        if (context == null) return;
        startWeb(context, address);
    }

    /**
     * 打开网页 部分手机（三星等） 无法直接打开网页，需要做一些处理
     *
     * @param context context
     * @param address url
     */
    public static void startWeb(Context context, @Nullable String address) {
        if (address == null || address.isEmpty()) return;
        if (!Patterns.WEB_URL.matcher(address).matches()) return;

        Uri uri = Uri.parse(address);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                .addCategory(Intent.CATEGORY_BROWSABLE);

        try {
            context.startActivity(intent);
        } catch (Exception ignore) {
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> list = packageManager.queryIntentActivities(intent, 0);
            for (ResolveInfo resolveInfo : list) {
                String activityName = resolveInfo.activityInfo.name;
                if (activityName.contains("BrowserActivity")) {
                    Intent intentForPackage =
                            packageManager.getLaunchIntentForPackage(resolveInfo.activityInfo.packageName);
                    if (intentForPackage == null) return;
                    ComponentName comp = new ComponentName(
                            resolveInfo.activityInfo.packageName,
                            resolveInfo.activityInfo.name
                    );
                    intentForPackage.setAction(Intent.ACTION_VIEW);
                    intentForPackage.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intentForPackage.addCategory(Intent.CATEGORY_BROWSABLE);
                    intentForPackage.setComponent(comp);
                    intentForPackage.setData(uri);
                    context.startActivity(intentForPackage);
                    break;
                }
            }

//        CommonUtils.getReward()
        }

    }


    // 判断是否安装指定app
    public static boolean isInstallApp(Context context, String pkg) {
        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pInfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pInfo.size(); i++) {
            String pn = pInfo.get(i).packageName;
            if (TextUtils.equals(pkg, pn)) {
                return true;
            }
        }
        return false;
    }


    //设置状态栏透明 字体黑色
    public static void setStatusTransparent(Activity activity) {
        Window window = activity.getWindow();
        window.getAttributes().flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //兼容5.0及以上支持全透明
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                @SuppressLint("PrivateApi")
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(window.getDecorView(), Color.TRANSPARENT);//改为透明
                field.setAccessible(false);
            } catch (Exception ignore) {
            }
        }

    }

    //设置状态栏透明 字体白色
    @SuppressWarnings("JavaReflectionMemberAccess")
    public static void setStatusTransparentLight(Activity activity) {
        Window window = activity.getWindow();
        window.getAttributes().flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        String brand = Build.BRAND;
        if ("Xiaomi".equalsIgnoreCase(brand)) {
            //针对小米
            Class clazz = window.getClass();
            try {
                int darkModeFlag;

                @SuppressLint("PrivateApi")
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");

                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");

                darkModeFlag = field.getInt(layoutParams);

                @SuppressWarnings("unchecked")
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);

                extraFlagField.invoke(window, darkModeFlag, darkModeFlag);

            } catch (Exception e) {
                if (BuildConfig.DEBUG) {
                    Logger.v(e.getMessage(), e);
                }
            }

        } else if ("Meizu".equalsIgnoreCase(brand)) {
            //针对魅族
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                value = value | bit;
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
            } catch (Exception e) {
                if (BuildConfig.DEBUG) {
                    Logger.v(e.getMessage(), e);
                }
            }

        }
        if ("Xiaomi".equalsIgnoreCase(brand)) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                @SuppressLint("PrivateApi")
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(window.getDecorView(), Color.TRANSPARENT); //改为透明
                field.setAccessible(false);
            } catch (Exception ignore) {
            }

        }
    }

    /**
     * 改变状态栏字体颜色为黑色, 要求MIUI6以上
     *
     * @param lightStatusBar 为真时表示黑色字体
     */

    public static void processMIUI(Activity activity, boolean lightStatusBar) {
        processMIUI(activity, lightStatusBar, Color.WHITE);
    }

    /**
     * 改变状态栏字体颜色为黑色, 要求MIUI6以上
     *
     * @param lightStatusBar 为真时表示黑色字体
     */
    @SuppressWarnings({"JavaReflectionMemberAccess", "unchecked"})
    public static void processMIUI(Activity activity, boolean lightStatusBar, int color) {

        Window window = activity.getWindow();
        //针对安卓6.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && lightStatusBar) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        String brand = Build.BRAND;
        if ("Xiaomi".equalsIgnoreCase(brand)) {
            //针对小米
            Class clazz = window.getClass();
            try {
                int darkModeFlag;

                @SuppressLint("PrivateApi")
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");

                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");

                darkModeFlag = field.getInt(layoutParams);

                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);

                extraFlagField.invoke(window, lightStatusBar ? darkModeFlag : 0, darkModeFlag);

            } catch (Exception e) {
                if (BuildConfig.DEBUG) {
                    Logger.v(e.getMessage(), e);
                }
            }
        } else if ("Meizu".equalsIgnoreCase(brand)) {
            //针对魅族
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                value = lightStatusBar ? value | bit : value & bit;
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
            } catch (Exception e) {
                if (BuildConfig.DEBUG) {
                    Logger.v(e.getMessage(), e);
                }
            }

        }
    }



    public static final String BUNDLE_KEY = "bundle";


    public static void toNewActivity(Fragment fragment, Class<? extends Activity> cla, @Nullable Bundle bundle) {
        Intent intent = new Intent(fragment.getContext(), cla);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (null != bundle) {
            intent.putExtra(BUNDLE_KEY, bundle);
        }
        fragment.startActivity(intent);
    }


    public static void toNewActivity(Fragment fragment, Class<? extends Activity> cla) {
        toNewActivity(fragment, cla,null);
    }

    public static void toNewActivity(Context context, Class<? extends Activity> cla, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, cla);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        if (null != bundle) {
            intent.putExtra(BUNDLE_KEY, bundle);
        }
        context.startActivity(intent);
    }


    public static void toNewActivity(Context context, Class<? extends Activity> cla) {
        toNewActivity(context, cla,null);
    }

}
