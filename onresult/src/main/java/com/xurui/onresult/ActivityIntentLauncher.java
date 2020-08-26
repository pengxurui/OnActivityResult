package com.xurui.onresult;

import android.content.Intent;

/**
 * Created by pengxr on 2020/8/26.
 */
public interface ActivityIntentLauncher {

    Intent createIntent();

    void onActivityResult(int resultCode, Intent data);

}
