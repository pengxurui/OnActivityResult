package com.xurui.onresult;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

/**
 * Created by pengxr on 2020/8/25.
 */
public class ActivityResult {

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
        SupportRequestFragment emptyFragment = mRequestRetriever.getRequestManagerFragment(activity.getSupportFragmentManager());
        emptyFragment.start(intent, requestCode, listener);
    }

    public void start(@NonNull Fragment fragment, @NonNull Intent intent, int requestCode, @NonNull OnResultListener listener) {
        if (null == fragment.getActivity()) {
            return;
        }
        start(fragment.getActivity(), intent, requestCode, listener);
    }
}
