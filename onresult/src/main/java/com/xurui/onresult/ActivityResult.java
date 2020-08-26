package com.xurui.onresult;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.Preconditions;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * Created by pengxr on 2020/8/25.
 */
public class ActivityResult {

    static final String TAG = "OnActivityResult";

    private static volatile ActivityResult sActivityResult;

    private RequestRetriever mRequestRetriever = new RequestRetriever();

    @NonNull
    private static ActivityResult get() {
        if (sActivityResult == null) {
            synchronized (ActivityResult.class) { // DCL
                if (sActivityResult == null) {
                    sActivityResult = new ActivityResult();
                }
            }
        }
        return sActivityResult;
    }

    public static Request with(@NonNull FragmentActivity activity) {
        Preconditions.checkNotNull(activity);

        return get().mRequestRetriever.getRequestManagerFragment(activity.getSupportFragmentManager());
    }

    public static Request with(@NonNull Fragment fragment) {
        Preconditions.checkNotNull(fragment);
        Preconditions.checkNotNull(fragment.getActivity(), "You cannot start a load on a proxy fragment before it is attached");

        return with(fragment.getActivity());
    }

    public static Request with(@NonNull Activity activity) {
        Preconditions.checkNotNull(activity);

        return get().mRequestRetriever.getRequestManagerFragment(activity.getFragmentManager());
    }

    public static Request with(@NonNull android.app.Fragment fragment) {
        Preconditions.checkNotNull(fragment);
        Preconditions.checkNotNull(fragment.getActivity(), "You cannot start a load on a proxy fragment before it is attached");

        return with(fragment.getActivity());
    }

    public static Request with(@NonNull View view) {
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(view.getContext(), "Unable to obtain a proxy fragment for a view without a Context");

        Activity activity = findActivity(view.getContext());

        // The view might be somewhere else, like a service.
        Preconditions.checkNotNull(activity);

        if (activity instanceof FragmentActivity) {
            return with((FragmentActivity) activity);
        } else {
            return with(activity);
        }
    }

    @Nullable
    private static Activity findActivity(@NonNull Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        } else if (context instanceof ContextWrapper) {
            return findActivity(((ContextWrapper) context).getBaseContext());
        } else {
            return null;
        }
    }
}
