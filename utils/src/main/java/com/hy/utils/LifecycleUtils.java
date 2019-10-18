package com.hy.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * Created time : 2019-09-27 11:20.
 *
 * @author HY
 */
public class LifecycleUtils {

    public static boolean canLoadImage(Fragment fragment) {
        if (fragment == null) {
            return false;
        }

        FragmentActivity activity = fragment.getActivity();

        return canLoadImage(activity);
    }

    public static boolean canLoadImage(View view) {

        if (view == null) {
            return false;
        }
        Context context = view.getContext();

        return canLoadImage(context);
    }

    public static boolean canLoadImage(Context context) {
        if (context == null) {
            return false;
        }

        if (!(context instanceof Activity)) {
            return true;
        }

        Activity activity = (Activity) context;
        return canLoadImage(activity);
    }

    public static boolean canLoadImage(Activity activity) {
        if (activity == null) {
            return false;
        }

        boolean destroyed = activity.isDestroyed();

        return !destroyed && !activity.isFinishing();
    }
}
