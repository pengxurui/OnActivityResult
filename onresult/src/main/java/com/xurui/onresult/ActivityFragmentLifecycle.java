package com.xurui.onresult;

import android.content.Intent;
import android.util.SparseArray;

import java.lang.ref.WeakReference;
import java.util.Random;

/**
 * Created by pengxr on 2020/8/25.
 */
class ActivityFragmentLifecycle {

    private final SparseArray<WeakReference<ActivityIntentLauncher>> lifecycleListeners = new SparseArray<>(); // Weak

    /**
     * @return requestCode for startActivity
     */
    int addListener(ActivityIntentLauncher launcher) {
        int requestCode = generateRequestCode();
        lifecycleListeners.put(requestCode, new WeakReference<>(launcher));
        return requestCode;
    }

    void removeListener(ActivityIntentLauncher launcher) {
        for (int index = 0; index < lifecycleListeners.size(); index++) {
            WeakReference ref = lifecycleListeners.valueAt(index);
            if (null == ref.get() || ref.get() == launcher) {
                // already gc or match launcher
                lifecycleListeners.removeAt(index);
            }
        }
    }

    /**
     * generate requestCode for startActivity
     */
    private int generateRequestCode() {
        Random random = new Random();
        for (; ; ) {
            int code = random.nextInt(65536);
            if (null == lifecycleListeners.get(code)) {
                return code;
            }
        }
    }

    void onDestroy() {
        lifecycleListeners.clear();
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        WeakReference<ActivityIntentLauncher> ref = lifecycleListeners.get(requestCode);
        if (null != ref) {
            if (null != ref.get()) {
                ref.get().onActivityResult(resultCode, data);
            }
            // remove it anyway
            lifecycleListeners.remove(requestCode);
        }
    }

}
