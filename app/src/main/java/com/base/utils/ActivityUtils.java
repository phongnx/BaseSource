package com.base.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Phong on 5/29/2017.
 */

public class ActivityUtils {
    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager, String tag,
                                             @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, tag);
        transaction.commitAllowingStateLoss();
    }

    public static void pushFragment(Fragment newFragment, boolean callBack, String tag, FragmentManager fragmentManager, int frameID) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameID, newFragment, tag);
        if (callBack) {
            transaction.addToBackStack(tag);
        } else {
            clearBackStack(fragmentManager);
        }
        transaction.commit();
    }

    public static void removeFragment(Fragment fragment, FragmentManager fragmentManager) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.remove(fragment);
        ft.commit();
        fragmentManager.popBackStack();
    }

    public static void addFragment(Fragment newFragment, boolean callBack, String sTag, FragmentManager fragmentManager, int frameID) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(frameID, newFragment, sTag);
        if (callBack) {
            ft.addToBackStack(null);
        } else {
            clearBackStack(fragmentManager);
        }
        ft.commit();
    }

    public static void clearBackStack(FragmentManager manager) {
        for(int i = 0; i < manager.getBackStackEntryCount(); ++i) {
            manager.popBackStackImmediate();
        }

        if (manager.getBackStackEntryCount() > 0) {
            manager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

}
