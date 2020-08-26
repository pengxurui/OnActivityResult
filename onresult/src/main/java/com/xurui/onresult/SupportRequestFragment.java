package com.xurui.onresult;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * proxy support fragment
 * <p>
 * Created by pengxr on 2020/8/25.
 */
public class SupportRequestFragment extends Fragment implements Request {

    private final ActivityFragmentLifecycle lifecycle = new ActivityFragmentLifecycle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycle.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        lifecycle.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void start(@NonNull ActivityIntentLauncher launcher) {
        int requestCode = lifecycle.addListener(launcher);
        startActivityForResult(launcher.createIntent(), requestCode);
    }

    @Override
    public void remove(@NonNull ActivityIntentLauncher launcher) {
        lifecycle.removeListener(launcher);
    }
}
