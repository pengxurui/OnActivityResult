package com.xurui.onresult;

import androidx.annotation.NonNull;

/**
 * Created by pengxr on 2020/8/26.
 */
public interface Request {

    void start(@NonNull ActivityIntentLauncher launcher);

    void remove(@NonNull ActivityIntentLauncher launcher);
}
