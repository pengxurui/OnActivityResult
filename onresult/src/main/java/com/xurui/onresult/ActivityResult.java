package com.xurui.onresult;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
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

    public static final String TAG = "OnActivityResult";

    private static volatile ActivityResult sActivityResult;

    private RequestRetriever mRequestRetriever = new RequestRetriever();

    @NonNull
    public static ActivityResult get() {
        if (sActivityResult == null) {
            synchronized (ActivityResult.class) {
                if (sActivityResult == null) {
                    sActivityResult = new ActivityResult();
                }
            }
        }
        return sActivityResult;
    }

    public void start(@NonNull FragmentActivity activity, @NonNull Intent intent, int requestCode, @NonNull OnResultListener listener) {
        Preconditions.checkNotNull(activity);
        SupportRequestFragment emptyFragment = mRequestRetriever.getRequestManagerFragment(activity.getSupportFragmentManager());
        emptyFragment.start(intent, requestCode, listener);
    }

    public void start(@NonNull Fragment fragment, @NonNull Intent intent, int requestCode, @NonNull OnResultListener listener) {
        Preconditions.checkNotNull(fragment);
        Preconditions.checkNotNull(fragment.getActivity(), "You cannot start a load on a fragment before it is attached");
        start(fragment.getActivity(), intent, requestCode, listener);
    }

    public void start(@NonNull Activity activity, @NonNull Intent intent, int requestCode, @NonNull OnResultListener listener) {
        Preconditions.checkNotNull(activity);
        RequestFragment emptyFragment = mRequestRetriever.getRequestManagerFragment(activity.getFragmentManager());
        emptyFragment.start(intent, requestCode, listener);
    }

    public void start(@NonNull android.app.Fragment fragment, @NonNull Intent intent, int requestCode, @NonNull OnResultListener listener) {
        Preconditions.checkNotNull(fragment);
        Preconditions.checkNotNull(fragment.getActivity(), "You cannot start a load on a fragment before it is attached");
        start(fragment.getActivity(), intent, requestCode, listener);
    }

    public void start(@NonNull View view, @NonNull Intent intent, int requestCode, @NonNull OnResultListener listener) {
        Preconditions.checkNotNull(view);
        Preconditions.checkNotNull(view.getContext(), "Unable to obtain a request fragment for a view without a Context");
        Activity activity = findActivity(view.getContext());

        // The view might be somewhere else, like a service.
        if (activity == null) {
            return;
        }

        if (activity instanceof FragmentActivity) {
            start((FragmentActivity) activity, intent, requestCode, listener);
        } else {
            start(activity, intent, requestCode, listener);
        }
    }

    @Nullable
    private Activity findActivity(@NonNull Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        } else if (context instanceof ContextWrapper) {
            return findActivity(((ContextWrapper) context).getBaseContext());
        } else {
            return null;
        }
    }
}
