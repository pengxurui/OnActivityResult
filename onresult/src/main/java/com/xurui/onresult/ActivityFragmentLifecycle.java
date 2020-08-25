package com.xurui.onresult;

import android.content.Intent;

import androidx.annotation.NonNull;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pengxr on 2020/8/25.
 */
class ActivityFragmentLifecycle {

    private final Map<Integer, WeakReference<OnResultListener>> lifecycleListeners = new HashMap<>(); // Weak

    public void addListener(int requestCode, @NonNull OnResultListener listener) {
        lifecycleListeners.put(requestCode, new WeakReference<>(listener));
    }

    public void removeListener(int requestCode, @NonNull OnResultListener listener) {
        lifecycleListeners.remove(requestCode);
    }

    void onDestroy() {

        for (Map.Entry<Integer, WeakReference<OnResultListener>> entry : lifecycleListeners.entrySet()) {
            lifecycleListeners.clear();
        }
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (Map.Entry<Integer, WeakReference<OnResultListener>> entry : lifecycleListeners.entrySet()) {
            WeakReference<OnResultListener> ref = entry.getValue();
            if (null != ref && null != ref.get()) {
                ref.get().onActivityResult(resultCode, data);
            }
            // 移除
            lifecycleListeners.remove(requestCode);
        }
    }
}
